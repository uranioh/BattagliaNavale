import javax.swing.*;
import java.awt.*;

public class Singleplayer extends UI {
    int explodedCells_Player = 0;
    int explodedCells_CPU = 0;
    ImageIcon explosion = new ImageIcon("src/assets/explosion.png");
    ImageIcon close = new ImageIcon("src/assets/prova.png");
    int[][] mat;
    MainFrame frame;
    CPU cpu = new CPU(this);

    public Singleplayer(MainFrame frame) {
        this.frame = frame;
        this.playGameTitle.setText("CPU");
        printMat(enemyGrid);
    }

//    controlla se dentro la matrice del player ci sta na barca nelle coordinate piazzate in input
    public boolean getResponseAttackPlayer(int x, int y) {
        boolean a = false;
        if (explodedCells_CPU < 18) {
            if (playerGrid.gridItems[x][y].getLinkedShip() != null) {
                a = true;
                explodedCells_CPU++;

                if (explodedCells_CPU == 18) {
                    playGameTitle.setText("Hai perso");
                }
            }
        }
        return a;
    }

    @Override
    public void AddListenerPlayGameButton() {
        playGame.addActionListener(e -> {
            for (int c = 0; c < 6; c++) {
                int x = 0;
                int y = 0;
                x = (ships[c].getLocation().x - playerGrid.getLocation().x);
                y = (ships[c].getLocation().y - playerGrid.getLocation().y);


                remove(ships[c]);
                ships[c].setBounds(x, y, ships[c].getIcon().getIconWidth(), ships[c].getIcon().getIconHeight());
                playerGrid.add(ships[c]);

            }
            playerGrid.resetGridItemBorder();


            for (int i = 0; i < 6; i++) {
                remove(ships_bg[i]);
            }
            remove(resetShips_Button);
            remove(playGame);
            remove(playerGrid);
            remove(enemyGrid);

            setLayout(new BorderLayout());

            gridPanel.setOpaque(false);
            add(playGameTitle, BorderLayout.NORTH);
            add(gridPanel, BorderLayout.SOUTH);


            playGameTitle.setForeground(Color.WHITE);
            playGameTitle.setFont(new Font("Arial", Font.BOLD, 40));
            gridPanel.add(playerGrid);
            gridPanel.add(enemyGrid);
            playerGrid.setPreferredSize(new Dimension(playerGrid.getIcon().getIconWidth(), playerGrid.getIcon().getIconHeight()));
            revalidate();
            repaint();
            mat = getPlayerMat();
            printMat(playerGrid);
            setPlaying(true);
        });
    }

    public void sendAttack(int x, int y) {
        if (explodedCells_Player < 18) {
            boolean response = cpu.getResponseAttackCPU(x, y);
            System.out.println("RISPOSTA:   " + response);
            if (response) {
                enemyGrid.gridItems[x][y].setIcon(explosion);
                explodedCells_Player++;
                if (explodedCells_Player == 18) {
                    playGameTitle.setText("Hai vinto");
                }
            } else {
                enemyGrid.gridItems[x][y].setIcon(close);
            }
            enemyGrid.gridItems[x][y].setPlaying(false);
            cpu.sendAttack();
        }

    }

    public void setPlaying(boolean t) {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                enemyGrid.gridItems[row][col].setPlaying(t);
                enemyGrid.gridItems[row][col].addSingleplayer(this);
            }
        }

    }

    public void printMat(Grid grid) {
        System.out.println();

//        for (int row = 0; row < 10; row++) {
//            for (int col = 0; col < 10; col++) {
//                System.out.print(mat[row][col] + "\t");
//            }
//            System.out.println();
//        }
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                if (grid.gridItems[row][col].getLinkedShip() == null) {
                    System.out.print("n" + "\t");
                }
                else {
                    System.out.print(grid.gridItems[row][col].getLinkedShip().getShipSize() + "\t");
                }
            }
            System.out.println();
        }
    }

    public int[][] getPlayerMat() {
        int[][] mat = new int[10][10];

        for (Ship ship : ships) {
            for (Component comp : playerGrid.getComponents()) {
                if (comp instanceof GridItem) {
                    Rectangle compBounds = SwingUtilities.convertRectangle(comp.getParent(), comp.getBounds(), this);
                    Rectangle shipBounds = SwingUtilities.convertRectangle(ship.getParent(), ship.getBounds(), this);

                    if (shipBounds.intersects(compBounds)) {
                        mat[((GridItem) comp).getRelativeX()][((GridItem) comp).getRelativeY()] = 1;
                    }
                }
            }
        }
        return mat;
    }
}
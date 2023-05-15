import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Singleplayer extends UI {
    Random r = new Random();
    private Timer timer;


    int explodedCells_Player = 0;
    int explodedCells_CPU = 0;

    ImageIcon explosion = new ImageIcon("src/assets/explosion.png");
    ImageIcon close = new ImageIcon("src/assets/prova.png");

    int[][] mat;
    CPU cpu = new CPU(this);

    public Singleplayer() {
        this.playGameTitle.setText("CPU");
        printMat(Globals.enemyGrid);
    }

//    controlla se dentro la matrice del player ci sta na barca nelle coordinate piazzate in input
    public boolean getResponseAttackPlayer(int x, int y) {
        boolean a = false;
        if (explodedCells_CPU < 18) {
            if (Globals.playerGrid.gridItems[x][y].getLinkedShip() != null) {
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
                x = (Globals.playerShips[c].getLocation().x - Globals.playerGrid.getLocation().x);
                y = (Globals.playerShips[c].getLocation().y - Globals.playerGrid.getLocation().y);


                remove(Globals.playerShips[c]);
                Globals.playerShips[c].setBounds(x, y, Globals.playerShips[c].getIcon().getIconWidth(), Globals.playerShips[c].getIcon().getIconHeight());
                Globals.playerGrid.add(Globals.playerShips[c]);

            }
            Globals.playerGrid.resetGridItemBorder();


            for (int i = 0; i < 6; i++) {
                remove(ships_bg[i]);
            }
            remove(resetShips_Button);
            remove(playGame);
            remove(Globals.playerGrid);
            remove(Globals.enemyGrid);

            setLayout(new BorderLayout());

            gridPanel.setOpaque(false);
            add(playGameTitle, BorderLayout.NORTH);
            add(gridPanel, BorderLayout.SOUTH);


            playGameTitle.setForeground(Color.WHITE);
            playGameTitle.setFont(new Font("Arial", Font.BOLD, 40));
            gridPanel.add(Globals.playerGrid);
            gridPanel.add(Globals.enemyGrid);
            Globals.playerGrid.setPreferredSize(new Dimension(Globals.playerGrid.getIcon().getIconWidth(), Globals.playerGrid.getIcon().getIconHeight()));
            revalidate();
            repaint();
            mat = getPlayerMat();
            printMat(Globals.playerGrid);
            setPlaying(true);
        });
    }

    public void sendAttack(int x, int y) {
        if (explodedCells_Player < 18) {
            boolean response = cpu.getResponseAttackCPU(x, y);
            System.out.println("RISPOSTA:   " + response);
            if (response) {
                Globals.enemyGrid.gridItems[x][y].setIcon(explosion);
                explodedCells_Player++;
                if (explodedCells_Player == 18) {
                    playGameTitle.setText("Hai vinto");
                }
            } else {
                Globals.enemyGrid.gridItems[x][y].setIcon(close);
            }
            Globals.enemyGrid.gridItems[x][y].setPlaying(false);


            Globals.enemyGrid.setGridStatus(false);
            int delay = r.nextInt(1000, 5000);
            timer = new Timer(delay, e -> {
                cpu.sendAttack();
                Globals.enemyGrid.setGridStatus(true);
                timer.stop();
            });
            timer.start();
        }

    }

    public void setPlaying(boolean t) {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                Globals.enemyGrid.gridItems[row][col].setPlaying(t);
                Globals.enemyGrid.gridItems[row][col].addSingleplayer(this);
            }
        }

    }

    public void printMat(Grid grid) {
        System.out.println();

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

        for (Ship ship : Globals.playerShips) {
            for (Component comp : Globals.playerGrid.getComponents()) {
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
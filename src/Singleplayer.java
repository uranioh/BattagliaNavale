import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Singleplayer extends UI {
    Random r = new Random();
    int explodedCells_Player = 0;
    int explodedCells_CPU = 0;
    ImageIcon explosion = new ImageIcon("src/assets/explosion.png");
    ImageIcon close = new ImageIcon("src/assets/prova.png");
    int[][] mat;
    CPU cpu = new CPU(this);
    private Timer timer;

    public Singleplayer() {
        this.playGameTitle.setText("CPU");
        ShipPositioner.positionShips(Globals.enemyGrid, Globals.enemyShips);
        printMat(Globals.enemyGrid);
    }

    //    controlla se dentro la matrice del player ci sta na barca nelle coordinate piazzate in input
    public boolean checkAttack(int x, int y) {
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
    public void enablePlayButton() {
        playGame.addActionListener(e -> {
            playGame();
            mat = getPlayerMat();
            printMat(Globals.playerGrid);
            setPlaying(true);
        });
    }

    public void sendAttack(int x, int y) {
        if (explodedCells_Player < 18) {
            boolean response = cpu.checkAttack(x, y);
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


//            Disable enemy grid
            Globals.enemyGrid.setGridState(false);

//            Fake CPU delay
            int delay = r.nextInt(1000, 5000);
            timer = new Timer(delay, e -> {
                cpu.sendAttack();
//                Enable enemy grid
                Globals.enemyGrid.setGridState(true);
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
                } else {
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
import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Random;

public class Singleplayer extends UI {
    Timer timer;
    Random r = new Random();
    CPU cpu = new CPU(this);

    //    Counters
    int explodedCells_Player = 0;
    int explodedCells_CPU = 0;

    //    Icons
    ImageIcon explosion = new ImageIcon("src/assets/explosion.png");
    ImageIcon close = new ImageIcon("src/assets/prova.png");

    public Singleplayer() {
        this.playGameTitle.setText("CPU");
        ShipPositioner.positionShips(Globals.enemyGrid, Globals.enemyShips);
        printMat(Globals.enemyGrid);
    }

    //    controlla se dentro la matrice del player ci sta na barca nelle coordinate piazzate in input
    public boolean checkAttack(int x, int y) {
        if (explodedCells_CPU < 18) {
            if (Globals.playerGrid.gridItems[x][y].getLinkedShip() != null) {
                explodedCells_CPU++;

                if (explodedCells_CPU == 18) {
                    playGameTitle.setText("Hai perso");
                }

                return true;
            }
        }

        return false;
    }

    @Override
    public void enablePlayButton() {
        playGame.addActionListener(e -> {
            playGame();
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

                GridItem g = Globals.enemyGrid.gridItems[x][y];
                g.setAttacked();


                checkShipDestroyed(g);
//                System.out.println("x: " + x + " y: " + y);

                explodedCells_Player++;
                if (explodedCells_Player == 18) {
                    playGameTitle.setText("Hai vinto");
                }

            } else {
//                Section gives the turn to the CPU only if the player misses the attack

                Globals.enemyGrid.gridItems[x][y].setIcon(close);

                turnLabel.setText("Turno CPU");
//                Disable enemy grid
                Globals.enemyGrid.setGridState(false);

//                Fake CPU delay
                int delay = r.nextInt(1000, 5000);
                timer = new Timer(delay, e -> {
                    cpu.sendAttack();
//                    Enable enemy grid
                    Globals.enemyGrid.setGridState(true);
                    timer.stop();
                });
                timer.start();
            }
        }

    }

    public void checkShipDestroyed(GridItem g) {
        Ship ship = g.getLinkedShip();

        for (GridItem sel : ship.selectedCells) {
            if (!sel.isAttacked()) {
                System.out.println("Nave non distrutta");
                return;
            }
        }

        for (GridItem sel : ship.selectedCells) {
            sel.setBackground(new Color(255, 0, 0, 128));
            sel.setOpaque(true);
            sel.repaint();
//            sel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            System.out.println("Nave distrutta");
        }


    }

    public void setPlaying(boolean t) {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                Globals.enemyGrid.gridItems[row][col].setPlaying(t);
                Globals.enemyGrid.gridItems[row][col].linkPlayer(this);
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
}
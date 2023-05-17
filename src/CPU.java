import javax.swing.*;
import java.util.Random;

public class CPU {
    Timer timer;

    Random r = new Random();
    ImageIcon explosion = new ImageIcon("src/assets/explosion.png");
    ImageIcon close = new ImageIcon("src/assets/prova.png");
    int difficulty;
    Random rand = new Random();
    Singleplayer player;

    public CPU(Singleplayer player) {
        this.player = player;
        difficulty = 0;
    }

    public void sendAttack() {
        GridItem g;
        int x, y;
        boolean status;

        if (difficulty == 0) {
            do {
                status = false;

                x = rand.nextInt(0, 10);
                y = rand.nextInt(0, 10);

                g = Globals.playerGrid.gridItems[x][y];

//                Avoids to attack the same cell twice
                if (g.isAttacked()) {
                    status = true;
                }
//                Repeat the cycle if the cell is already attacked
            } while (status);

//            Check if the attack hit a ship
            boolean response = player.checkAttack(x, y);


            if (response) {
                g.setIcon(explosion);
                player.checkShipDestroyed(g);

                int delay = r.nextInt(1000, 5000);
                timer = new Timer(delay, e -> {
                    sendAttack();
                    player.turnLabel.setText("È il tuo turno");
                    timer.stop();
                });
                timer.start();
            } else {
                g.setIcon(close);
                player.turnLabel.setText("È il tuo turno");
            }

            g.setAttacked();
        }
    }

    public boolean checkAttack(int x, int y) {
        return Globals.enemyGrid.gridItems[x][y].getLinkedShip() != null;
    }
}

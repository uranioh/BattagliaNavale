import javax.swing.*;
import java.util.Random;

public class CPU {
    ImageIcon explosion = new ImageIcon("src/assets/explosion.png");
    ImageIcon close = new ImageIcon("src/assets/prova.png");
    GenerateCpuMatrix generate = new GenerateCpuMatrix();
    int difficulty;
    int[][] mat;
    Random rand = new Random();
    Singleplayer player;

    public CPU(Singleplayer player) {
        this.player = player;
        this.mat = generate.getMat();
        difficulty = 0;
    }

    public void sendAttack() {
        int x, y;
        boolean status;

        if (difficulty == 0) {
            do {
                status = false;

                x = rand.nextInt(0, 10);
                y = rand.nextInt(0, 10);


                if (Globals.playerGrid.gridItems[x][y].isAttacked()) {
                    status = true;
                }
            } while (status);

            boolean response = player.checkAttack(x, y);


            if (response) {
                Globals.playerGrid.gridItems[x][y].setIcon(explosion);
            } else {
                Globals.playerGrid.gridItems[x][y].setIcon(close);
            }

            Globals.playerGrid.gridItems[x][y].setAttacked();
        }
    }

    public boolean checkAttack(int x, int y) {
        return mat[x][y] != 0;
    }
}

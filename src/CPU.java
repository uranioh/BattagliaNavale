import javax.swing.*;
import java.util.Random;

public class CPU {
    ImageIcon explosion = new ImageIcon("src/assets/explosion.png");
    ImageIcon close = new ImageIcon("src/assets/prova.png");
    generateCpuMatrix generate = new generateCpuMatrix();
    int level;
    int[][] mat;
    Random rand = new Random();
    Singleplayer single;

    public CPU(Singleplayer single) {
        this.single = single;
        this.mat = generate.getMat();
        level = 0;
    }

    public void sendAttack() {
        int x, y;
        boolean status;

        if (level == 0) {
            do {
                status = false;

                x = rand.nextInt(0, 10);
                y = rand.nextInt(0, 10);


                if (single.playerGrid.gridItems[x][y].isAttacked()) {
                    status = true;
                }
            } while (status);

            boolean response = single.getResponseAttackPlayer(x, y);


            if (response) {
                single.playerGrid.gridItems[x][y].setIcon(explosion);
            } else {
                single.playerGrid.gridItems[x][y].setIcon(close);
            }

            single.playerGrid.gridItems[x][y].setAttacked();
        }
    }

    public boolean getResponseAttackCPU(int x, int y) {
        return mat[x][y] != 0;
    }
}

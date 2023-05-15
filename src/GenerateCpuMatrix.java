import java.util.Arrays;
import java.util.Random;

public class GenerateCpuMatrix {
    int gridDimension = 10;
    int[][] mat = new int[gridDimension][gridDimension];
    int[][] mat2 = new int[gridDimension][gridDimension];

    Random rand = new Random();

    public GenerateCpuMatrix() {

        //0 su, 1 destra, 2 giù, 3 sinistra


        resetMat(mat2);
        resetMat(mat);
        mat2to1();
        //printMat(mat);

        generateShip(3);
        generateShip(3);
        generateShip(4);
        generateShip(4);
        generateShip(2);
        generateShip(2);


        //printMat(mat);

    }

    public int[][] getMat() {
        return mat;
    }

    public void generateShip(int lenght) {
        int choose = rand.nextInt(0, 4);
        switch (choose) {
            case 0 -> upControl(lenght);
            case 1 -> rightControl(lenght);
            case 2 -> downControl(lenght);
            case 3 -> leftControl(lenght);
            default -> System.out.println("errore scelta generazione cpu barche");
        }
    }

    public void resetMat(int[][] mat) {
        for (int row = 0; row < gridDimension; row++) {
            Arrays.fill(mat[row], 0);
        }
    }

    public void mat1to2() {
        for (int row = 0; row < gridDimension; row++) {
            System.arraycopy(mat[row], 0, mat2[row], 0, gridDimension);
        }
    }

    public void mat2to1() {
        for (int row = 0; row < gridDimension; row++) {
            System.arraycopy(mat2[row], 0, mat[row], 0, gridDimension);
        }
    }

    public void upControl(int num) {
        int t, result, x, y;

        do {
            do {
                x = rand.nextInt(0, gridDimension);
                y = rand.nextInt(0, gridDimension);
            } while (mat[x][y] != 0);

            mat[x][y] = num;
            result = 0;
            t = 0;
            if (x > num - 2) {
                x -= num - 1;
                for (int i = x; i < x + num - 1; i++) {
                    if (mat[i][y] != 0) {
                        t = 1;

                        break;
                    }
                }
                if (t == 0) {
                    for (int i = x; i < x + num - 1; i++) {
                        mat[i][y] = num;
                    }
                } else {
                    result = 1;
                }
            } else {
                result = 1;
            }
            if (result == 1) {
                mat2to1();
            } else {
                mat1to2();
            }
        } while (result != 0);
    }

    public void rightControl(int num) {
        int t, result, x, y;

        do {
            do {
                x = rand.nextInt(0, gridDimension);
                y = rand.nextInt(0, gridDimension);
            } while (mat[x][y] != 0);

            mat[x][y] = num;
            result = 0;
            t = 0;
            if (y + num - 1 < gridDimension) {
                int c = y + num;
                for (int i = y + 1; i < c; i++) {
                    if (mat[x][i] != 0) {
                        t = 1;
                        break;
                    }
                }
                if (t == 0) {
                    for (int i = y + 1; i < c; i++) {
                        mat[x][i] = num;
                    }
                } else {
                    result = 1;
                }
            } else {
                result = 1;
            }

            if (result == 1) {
                mat2to1();
            } else {
                mat1to2();
            }
        } while (result != 0);
    }

    public void leftControl(int num) {
        int t, result, x, y;

        do {
            do {
                x = rand.nextInt(0, gridDimension);
                y = rand.nextInt(0, gridDimension);
            } while (mat[x][y] != 0);

            mat[x][y] = num;
            result = 0;
            t = 0;
            if (y - num + 1 >= 0) {
                for (int i = y - 1; i > y - num; i--) {
                    if (mat[x][i] != 0) {
                        t = 1;
                        break;
                    }
                }
                if (t == 0) {
                    for (int i = y - 1; i > y - num; i--) {
                        mat[x][i] = num;
                    }
                } else {
                    result = 1;
                }
            } else {
                result = 1;
            }

            if (result == 1) {
                mat2to1();
            } else {
                mat1to2();
            }
        } while (result != 0);
    }

    public void downControl(int num) {
        int t, result, x, y;

        do {
            do {
                x = rand.nextInt(0, gridDimension);
                y = rand.nextInt(0, gridDimension);
            } while (mat[x][y] != 0);

            mat[x][y] = num;
            result = 0;
            t = 0;
            if (x + num - 1 < gridDimension) {
                for (int i = x + 1; i < x + num; i++) {
                    if (mat[i][y] != 0) {
                        t = 1;

                        break;
                    }
                }
                if (t == 0) {
                    for (int i = x + 1; i < x + num; i++) {
                        mat[i][y] = num;
                    }
                } else {
                    result = 1;
                }
            } else {
                result = 1;
            }
            if (result == 1) {
                mat2to1();
            } else {
                mat1to2();
            }
        } while (result != 0);
    }
}

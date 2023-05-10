import javax.swing.*;
import java.util.Random;
import java.util.Vector;

public class CPU {
    Vector<Integer> vector = new Vector<Integer>();
    ImageIcon explosion= new ImageIcon("src/assets/explosion.png");
    ImageIcon close= new ImageIcon("src/assets/prova.png");
    generateCpuMatrix generate= new generateCpuMatrix();
    int level;
    int[][] mat;
    Random rand= new Random();
    Singleplayer single;
    public CPU(Singleplayer single){
        this.single=single;
        this.mat= generate.getMat();
        level=0;
    }
    public void sendAttack(){

        //controllo ripetizione casuali
        if(level==0){
            boolean xyfound=false;
            int x=0;
            int y=0;
            do{
                xyfound=false;
                x=rand.nextInt(0,10);
                y=rand.nextInt(0,10);
                String t= String.valueOf(x)+String.valueOf(y);
                System.out.println(t);
                int t1=Integer.parseInt(t);
                for (int i=0; i<vector.size(); i++){
                    if(t1==vector.elementAt(i)){
                        xyfound=true;
                    }
                }
            }while (xyfound);

            boolean response=single.getResponseAttackPlayer(x,y);

            
            if(response){
                single.playerGrid.gridItems[x][y].setIcon(explosion);
            }else{
                single.playerGrid.gridItems[x][y].setIcon(close);
            }
        }

    }
    public boolean getResponseAttackCPU(int x, int y){
        boolean a=false;
        if(mat[x][y]==0){
            a=false;
        }else{
            a=true;
        }
        return a;
    }
    public void printMat(int mat[][]) {
        System.out.println();
        for (int row = 0; row < generate.gridDimension; row++) {
            for (int col = 0; col < generate.gridDimension; col++) {
                System.out.print(mat[row][col] + "\t");
            }
            System.out.println();
        }
    }


}

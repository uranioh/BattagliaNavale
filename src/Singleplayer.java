import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Singleplayer extends UI {
    int contSingle=0;
    int contCPU=0;
    ImageIcon explosion= new ImageIcon("src/assets/explosion.png");
    ImageIcon close= new ImageIcon("src/assets/prova.png");
    boolean next=false;
    int [][] mat;
    MainFrame frame;
    CPU cpu=new CPU(this);

    public Singleplayer(MainFrame frame) {
        this.frame = frame;
        this.playGameTitle.setText("CPU");
        printMat(cpu.mat);


    }
    public boolean getResponseAttackPlayer(int x,int y){
        boolean a=false;
        if(contCPU<18){

            if(mat[x][y]==0){
                a=false;
            }else{
                a=true;
                contCPU++;
                if(contCPU==18){
                    playGameTitle.setText("Hai perso!!");
                }
            }

        }
        return a;
    }

    public void AddListenerPlayGameButton() {
        playGame.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

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
                mat=getPlayerMat();
                printMat(mat);
                setPlaying(true);
            }
        });
    }
    public void sendAttack(int x, int y){
        if(contSingle<18){
            boolean response=cpu.getResponseAttackCPU(x,y);
            System.out.println("RISPOSTAAA:   "+response);
            if(response){
                enemyGrid.gridItems[x][y].setIcon(explosion);
                contSingle++;
                if(contSingle==18){
                    playGameTitle.setText("Hai vinto!!");
                }
            }else{
                enemyGrid.gridItems[x][y].setIcon(close);
            }
            enemyGrid.gridItems[x][y].setPlaying(false);
            cpu.sendAttack();
        }

    }
    public void setPlaying(boolean t){
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                enemyGrid.gridItems[row][col].setPlaying(t);
                enemyGrid.gridItems[row][col].addSingleplayer(this);
            }
        }

    }
    public void printMat(int[][] mat) {
        System.out.println();

        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                System.out.print(mat[row][col] + "\t");
            }
            System.out.println();
        }
    }
    public int[][] getPlayerMat(){
        int[][] mat= new int[10][10];
        for (int i=0; i<ships.length; i++){
            int c=0;
            for (Component comp : playerGrid.getComponents()) {
                if (comp instanceof GridItem gridItem) {
                    Rectangle compBounds = SwingUtilities.convertRectangle(comp.getParent(), comp.getBounds(), this);
                    Rectangle shipBounds = SwingUtilities.convertRectangle(ships[i].getParent(), ships[i].getBounds(), this);

                    if (shipBounds.intersects(compBounds)) {
                        c++;
                        mat[((GridItem) comp).getRelativeX()][((GridItem) comp).getRelativeY()]=1;
                    }

                }
            }


        }        //printMat(mat);

        return mat;
    }
}
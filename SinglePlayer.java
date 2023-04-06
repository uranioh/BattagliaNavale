import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SinglePlayer extends JFrame {
    JLabel main_panel= new JLabel();
    JLabel[] barca_pan= new JLabel[8];
    JPanel map_panel= new JPanel();
    JPanel map_enemy_panel= new JPanel();
    JPanel[][] map = new JPanel[10][10];
    JPanel[][] map_enemy = new JPanel[10][10];
    ImageIcon sfondo= new ImageIcon("template.JPG");
    ImageIcon[] barca_img= new ImageIcon[8];


    public SinglePlayer(){

        Container c= getContentPane();
        main_panel.setIcon(sfondo);


        for(int i=0; i<8; i++){
            String t="barche/barca"+(i)+".png";
            barca_img[i]= new ImageIcon(t);
            barca_pan[i]=new JLabel();
            barca_pan[i].setIcon(barca_img[i]);
            final int v=i;
            barca_pan[i].addMouseMotionListener(new MouseMotionAdapter() {

                public void mouseDragged(MouseEvent e) {
                    super.mouseDragged(e);
                    int x = e.getX() + barca_pan[v].getX();
                    int y = e.getY() + barca_pan[v].getY();
                    int maxX = barca_pan[v].getParent().getWidth() - barca_pan[v].getWidth();
                    int maxY = barca_pan[v].getParent().getHeight() - barca_pan[v].getHeight();
                    x = Math.max(0, Math.min(x, maxX));
                    y = Math.max(0, Math.min(y, maxY));
                    barca_pan[v].setLocation(x, y);
                }
            });
            barca_pan[v].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    super.mousePressed(e);
                    barca_pan[v].setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    super.mouseReleased(e);
                    barca_pan[v].setCursor(Cursor.getDefaultCursor());
                }
            });
        }

        reset_boats_position();


        for(int i=0; i<8; i++){
            main_panel.add(barca_pan[i]);
        }

        c.add(main_panel);
        setSize(1773,898);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }


    public void reset_boats_position(){
        barca_pan[0].setBounds(99,68,258,45);
        barca_pan[1].setBounds(99,128,206,45);
        barca_pan[2].setBounds(99,190,154,45);
        barca_pan[3].setBounds(99,250,99,37);
        barca_pan[4].setBounds(102,337,45,258);
        barca_pan[5].setBounds(170,334,45,154);
        barca_pan[6].setBounds(105,625,37,99);
        barca_pan[7].setBounds(172,517,45,206);
    }
}

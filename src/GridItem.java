import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GridItem extends JLabel implements MouseListener {
    Singleplayer single;
    public boolean state;
    private int x, y;
    boolean statePlay=false;

    public GridItem() {
        this.addMouseListener(this);
        state = false;
    }

    public boolean getState() {
        return this.state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public void setRelativeX(int x) {
        this.x = x;
    }

    public void setRelativeY(int y) {
        this.y = y;
    }

    public int getRelativeX() {
        return this.x;
    }

    public int getRelativeY() {
        return this.y;
    }

    public void setPlaying(boolean stateCommand){
        statePlay=stateCommand;
    }
    public void addSingleplayer(Singleplayer single){
        this.single=single;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (statePlay){
//            System.out.println("X: " + x + " Y: " + y);
            sendAttack( x,  y);
        }

    }
    public void sendAttack(int x, int y){
        single.sendAttack(x, y);

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

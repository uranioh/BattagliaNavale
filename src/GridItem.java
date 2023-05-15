import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GridItem extends JLabel implements MouseListener {
    Singleplayer single;
    boolean statePlay = false;
    private int x, y;
    //    gameplay attributes
    private Ship linkedShip = null;
    private boolean isAttacked = false;

    public GridItem() {
        this.addMouseListener(this);
    }

    public int getRelativeX() {
        return this.x;
    }

    public void setRelativeX(int x) {
        this.x = x;
    }

    public int getRelativeY() {
        return this.y;
    }

    public void setRelativeY(int y) {
        this.y = y;
    }

    public void setPlaying(boolean stateCommand) {
        statePlay = stateCommand;
    }

    public void addSingleplayer(Singleplayer single) {
        this.single = single;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (statePlay) {
//            System.out.println("X: " + x + " Y: " + y);
            sendAttack(x, y);
        }

    }

    public void sendAttack(int x, int y) {
        single.sendAttack(x, y);
    }

    public Ship getLinkedShip() {
        return this.linkedShip;
    }

    public void setLinkedShip(Ship ship) {
        this.linkedShip = ship;
    }

    public void setAttacked() {
        isAttacked = true;
    }

    public boolean isAttacked() {
        return isAttacked;
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

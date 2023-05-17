import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GridItem extends JLabel implements MouseListener {
    Singleplayer player;
    boolean statePlay = false;
    private int x, y;
    //    gameplay attributes
    private Ship linkedShip = null;
    private boolean isAttacked = false;

    public GridItem() {
        this.addMouseListener(this);
    }

    public void linkPlayer(Singleplayer player) {
        this.player = player;
    }

    //    Relative grid coordinates
    public void setRelativeX(int x) {
        this.x = x;
    }

    public void setRelativeY(int y) {
        this.y = y;
    }

    //    Check if the cell can be clicked or not
    public void setPlaying(boolean stateCommand) {
        statePlay = stateCommand;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        System.out.println(linkedShip);
        if (statePlay) {
            player.sendAttack(x, y);
        }

    }

    //    Linked ship methods
    public Ship getLinkedShip() {
        return this.linkedShip;
    }

    public void setLinkedShip(Ship ship) {
        this.linkedShip = ship;
    }

    //    Attack methods
    public void setAttacked() {
        isAttacked = true;
    }

    public boolean isAttacked() {
        return isAttacked;
    }

    //    Useless methods
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

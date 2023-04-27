import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GridItem extends JPanel implements MouseListener {
    public boolean state;
    private int x, y;

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


    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("X: " + x + " Y: " + y);
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

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GridItem extends JPanel implements MouseListener {
    public boolean state;
    int x;
    int y;

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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
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

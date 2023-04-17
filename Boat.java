import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Boat extends JLabel implements MouseListener {
    public Boat() {
        this.addMouseListener(this);
    }

    public int getBoatSize() {
        int size;
        if (this.getWidth() > this.getHeight()) {
            size = Math.round((float) this.getWidth() / 50);
        } else {
            size = Math.round((float) this.getHeight() / 50);
        }
        return size;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        setCursor(Cursor.getDefaultCursor());

        int size = getBoatSize();

        System.out.println(size);
        System.out.println("Boat released at: " + this.getX() + ", " + this.getY());
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }
}

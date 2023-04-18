import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Ship extends JLabel implements MouseListener {
    Multiplayer multiplayer;
    static int counter = 0;

    int id, size;
    char orientation;

    public Ship(Multiplayer multiplayer) {
        this.multiplayer = multiplayer;
        this.addMouseListener(this);
        this.id = counter++;
    }

    public void setProperties() {
        if (this.getWidth() > this.getHeight()) {
            size = Math.round((float) this.getWidth() / 50);
            orientation = 'h';
        } else {
            size = Math.round((float) this.getHeight() / 50);
            orientation = 'v';
        }
    }

    public int getShipSize() {
        return size;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
        System.out.printf("Ship %d (%d blocks, %c) pressed at: %d, %d%n", id, size, orientation, this.getX(), this.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        setCursor(Cursor.getDefaultCursor());
        System.out.printf("Ship %d (%d blocks, %c) released at: %d, %d%n", id, size, orientation, this.getX(), this.getY());

        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                multiplayer.gridItems_player[row][col].setOpaque(false);
                multiplayer.gridItems_player[row][col].repaint();
            }
        }
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

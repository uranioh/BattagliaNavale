import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Ship extends JLabel implements MouseListener, MouseMotionListener {
    Multiplayer multiplayer;

    static int counter = 0;
    int id, size;
    char orientation;

    boolean collided = false;

    public Ship(Multiplayer multiplayer) {
        this.multiplayer = multiplayer;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
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

        if (collided) {
            System.out.println("Ship collided");
            this.setLocation(0, 0);
        }

        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                multiplayer.playerGrid.gridItems[row][col].setOpaque(false);
                multiplayer.playerGrid.gridItems[row][col].repaint();
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        multiplayer.playerGrid.checkIfOverLabel(Ship.this);
        checkCollisions(Ship.this);

        int x = e.getXOnScreen() - getParent().getLocationOnScreen().x - getWidth() / 2;
        int y = e.getYOnScreen() - getParent().getLocationOnScreen().y - getHeight() / 2;
        int maxX = getParent().getWidth() - getWidth();
        int maxY = getParent().getHeight() - getHeight();


        x = Math.max(0, Math.min(x, maxX));
        y = Math.max(0, Math.min(y, maxY));

        if (x > 200 && y > 150)
            setLocation((Math.round((float) x / 54) * 54) - 14, (Math.round((float) y / 54) * 54) - 23);
        else {
            setLocation(x, y);
        }
    }

    public void checkCollisions(Ship draggingShip) {
        int counter = 0;
        final int totalShips = multiplayer.ships.length;

        for (Ship ship : multiplayer.ships) {
            if (ship != draggingShip && checkCollision(draggingShip, ship)) {
                System.out.println("Collision detected");
                this.collided = true;
            } else {
                counter++;
                if (counter == totalShips) {
                    System.out.println("No collision detected");
                    this.collided = false;
                }
            }
        }
    }

    public boolean checkCollision(Ship ship1, Ship ship2) {
        int x1 = ship1.getX();
        int y1 = ship1.getY();
        int w1 = ship1.getWidth();
        int h1 = ship1.getHeight();
        int x2 = ship2.getX();
        int y2 = ship2.getY();
        int w2 = ship2.getWidth();
        int h2 = ship2.getHeight();
        return (x1 < x2 + w2 && x1 + w1 > x2 && y1 < y2 + h2 && y1 + h1 > y2);
    }

//    public void checkIfOverLabel(JLabel draggingShip) {
//        for (JLabel ship : multiplayer.ships) {
//            if (ship != draggingShip && checkCollision(draggingShip, ship)) {
//                // handle collision
//            }
//        }
//    }


    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashSet;

public class Ship extends JLabel implements MouseListener, MouseMotionListener {
    //TODO:decremento counterPositioned al riposizionamento di una singola barca
    static int counterPositioned=0;
    static int counter = 0;
    UI _ui;
    int id, size, defaultX, defaultY;
    char orientation;

    boolean collided = false;
    boolean validPosition = false;
    boolean positioned = false;

    HashSet<GridItem> selectedCells = new HashSet<>();

    public Ship(UI ui) {
        this._ui = ui;
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

    public void setDefaultPosition(int x, int y) {
        this.defaultX = x;
        this.defaultY = y;
        this.setLocation(x, y);
    }

    public void resetPosition() {
        this.setLocation(defaultX, defaultY);
        this.positioned = false;
        counterPositioned=0;
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

        if(counterPositioned==5){
            System.out.println("ciao");
            _ui.addPlayGameButton();
        }
        System.out.println("counter"+counterPositioned);
        setCursor(Cursor.getDefaultCursor());
        System.out.printf("Ship %d (%d blocks, %c) released at: %d, %d%n", id, size, orientation, this.getX(), this.getY());

        if (positioned) {
            System.out.println("Ship already placed - moving to new position");
            for (GridItem gridItem : selectedCells) {
                gridItem.setBorder(null);
            }
            selectedCells.clear();
        }

        if (validPosition && !collided) {
            System.out.println("Ship placed to valid position");
            positioned = true;
            for (GridItem gridItem : Grid.selectedCells) {
                gridItem.setBorder(BorderFactory.createLineBorder(Color.RED, 4));
                selectedCells.add(gridItem);
            }
            counterPositioned++;
        }

        if (collided || !validPosition) {
            System.out.println("Ship collided or placed to invalid position");
            this.resetPosition();
        }

        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                _ui.playerGrid.gridItems[row][col].setOpaque(false);
                _ui.playerGrid.gridItems[row][col].repaint();
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int xGrid = _ui.playerGrid.getBounds().x;
        int yGrid = _ui.playerGrid.getBounds().y;
        int xShip = Ship.this.getBounds().x;
        int yShip = Ship.this.getBounds().y;


        _ui.playerGrid.checkIfOverLabel(Ship.this);
        checkCollisions(Ship.this);

        int x = e.getXOnScreen() - getParent().getLocationOnScreen().x - getWidth() / 2;
        int y = e.getYOnScreen() - getParent().getLocationOnScreen().y - getHeight() / 2;
        int maxX = getParent().getWidth() - getWidth();
        int maxY = getParent().getHeight() - getHeight();

        x = Math.max(0, Math.min(x, maxX));
        y = Math.max(0, Math.min(y, maxY));

        if (_ui.playerGrid.checkIfOverLabel(Ship.this) > 0 || xShip > xGrid - 54 && xShip < xGrid + 594 && yShip > yGrid - 54 && yShip < yGrid + 594)
            setLocation((Math.round((float) x / 54) * 54) - 14, (Math.round((float) y / 54) * 54) - 23);
        else {
            setLocation(x, y);
        }
    }

    public void checkCollisions(Ship draggingShip) {
        int counter = 0;
        final int totalShips = _ui.ships.length;

        for (Ship ship : _ui.ships) {
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

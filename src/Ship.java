import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashSet;

public class Ship extends JLabel implements MouseListener, MouseMotionListener {
    public static int placedCounter = 0;


    //    import UI
    private final UI _ui;

    //    ship properties
    public boolean validPosition = false;

    //    cells selected by the ship
    public HashSet<GridItem> selectedCells = new HashSet<>();
    private int defaultX, defaultY, size;
    //    ship states
    private boolean collided = false;
    private boolean positioned = false;

    public Ship(UI ui) {
        this._ui = ui;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    public void setProperties() {
        size = Math.round((float) this.getWidth() / 50);
    }

    public void setDefaultPosition(int x, int y) {
        this.defaultX = x;
        this.defaultY = y;
        this.setLocation(x, y);
    }

    public void resetPosition() {
        this.setLocation(defaultX, defaultY);
        this.positioned = false;
    }

    public int getShipSize() {
        return size;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        setCursor(Cursor.getDefaultCursor());

        if (positioned) {
            placedCounter--;

            for (GridItem gridItem : selectedCells) {
                gridItem.setBorder(null);
                gridItem.setLinkedShip(null);
            }
            selectedCells.clear();
        }

        if (validPosition && !collided) {
            placedCounter++;

            positioned = true;
            for (GridItem gridItem : Grid.selectedCells) {
                gridItem.setBorder(BorderFactory.createLineBorder(Color.RED, 4));
                gridItem.setLinkedShip(this);
                selectedCells.add(gridItem);
            }
        }

        if (collided || !validPosition) {

            this.resetPosition();
        }

        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                Globals.playerGrid.gridItems[row][col].setOpaque(false);
                Globals.playerGrid.gridItems[row][col].repaint();
            }
        }

        if (placedCounter == 6) {
            _ui.addPlayButton();
        } else {
            _ui.remove(_ui.playGame);
            _ui.revalidate();
            _ui.repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int xGrid = Globals.playerGrid.getBounds().x;
        int yGrid = Globals.playerGrid.getBounds().y;
        int xShip = Ship.this.getBounds().x;
        int yShip = Ship.this.getBounds().y;


        Globals.playerGrid.checkIfOverLabel(Ship.this);
        checkCollisions(Ship.this);

        int x = e.getXOnScreen() - getParent().getLocationOnScreen().x - getWidth() / 2;
        int y = e.getYOnScreen() - getParent().getLocationOnScreen().y - getHeight() / 2;
        int maxX = getParent().getWidth() - getWidth();
        int maxY = getParent().getHeight() - getHeight();

        x = Math.max(0, Math.min(x, maxX));
        y = Math.max(0, Math.min(y, maxY));

        if (Globals.playerGrid.checkIfOverLabel(Ship.this) > 0 || xShip > xGrid - 54 && xShip < xGrid + 594 && yShip > yGrid - 54 && yShip < yGrid + 594)
            setLocation((Math.round((float) x / 54) * 54) - 14, (Math.round((float) y / 54) * 54) - 23);
        else {
            setLocation(x, y);
        }
    }

    public void checkCollisions(Ship draggingShip) {
        int counter = 0;
        final int totalShips = Globals.playerShips.length;

        for (Ship ship : Globals.playerShips) {
            if (ship != draggingShip && checkCollision(draggingShip, ship)) {
                this.collided = true;
            } else {
                counter++;
                if (counter == totalShips) {
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

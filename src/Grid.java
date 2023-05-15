import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class Grid extends JLabel {
    static Set<GridItem> selectedCells;

    ImageIcon gridBackground = new ImageIcon("src/assets/grid.png");
    GridItem[][] gridItems = new GridItem[10][10];


    public Grid(int posX, int posY, int width, int height) {
        int gapSize = 4;
        int col_increment = gapSize;

        this.setIcon(gridBackground);
        this.setLayout(null);
        this.setBounds(posX, posY, width, height);

        for (int row = 0; row < 10; row++) {
            int row_increment = gapSize;

            for (int col = 0; col < 10; col++) {

                gridItems[row][col] = new GridItem();
                gridItems[row][col].setOpaque(false);

//                GridItem class properties
                gridItems[row][col].setRelativeX(row);
                gridItems[row][col].setRelativeY(col);


                gridItems[row][col].setBounds(row_increment, col_increment, 50, 50);
//                map[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                this.add(gridItems[row][col]);
                row_increment += 54;
            }
            col_increment += 54;
        }
    }

    public int checkIfOverLabel(Ship ship) {
        selectedCells = new HashSet<>(); // Set to keep track of selected grid cells

        for (Component comp : this.getComponents()) {
            if (comp instanceof GridItem gridItem) {
                Rectangle compBounds = SwingUtilities.convertRectangle(comp.getParent(), comp.getBounds(), this);
                Rectangle shipBounds = SwingUtilities.convertRectangle(ship.getParent(), ship.getBounds(), this);

                if (shipBounds.intersects(compBounds)) {
                    if (ship.getShipSize() > selectedCells.size()) {
                        selectedCells.add(gridItem); // Add the panel to the set of selected cells
                        gridItem.setBackground(new Color(0, 0, 0, 0.5f));
                        gridItem.setOpaque(true);
                    }
                } else if (selectedCells.size() == ship.getShipSize()) {
                    ship.validPosition = true;
                    gridItem.setOpaque(false);
                } else {
                    selectedCells.remove(gridItem); // Remove the panel from the set of selected cells
                    ship.validPosition = false;
                    gridItem.setOpaque(false);
                }
            }
        }

        this.repaint();

        return selectedCells.size();
    }


    public void resetGridItemBorder() {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                gridItems[row][col].setBorder(null);
            }
        }
    }

    public void setGridState(boolean t) {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                gridItems[row][col].setPlaying(t);
            }
        }
    }
}

import java.util.Random;

public class ShipPositioner {
    static final int gridDimension = 10;

    public static void positionShips(Grid grid, Ship[] ships) {
        Random random = new Random();

        for (Ship ship : ships) {
            boolean isPositioned = false;

            while (!isPositioned) {
                int x = random.nextInt(gridDimension);
                int y = random.nextInt(gridDimension);
                int orientation = random.nextInt(2); // 0 = horizontal, 1 = vertical

                if (canPlaceShip(grid, ship, x, y, orientation)) {
                    placeShip(grid, ship, x, y, orientation);
                    isPositioned = true;
                }
            }
        }
    }

    private static boolean canPlaceShip(Grid grid, Ship ship, int x, int y, int orientation) {
        int length = ship.getShipSize();

        if (orientation == 0) {
            // horizontal placement
            if (x + length > gridDimension) {
//                Out of bounds
                return false;
            }

            for (int i = x; i < x + length; i++) {
                if (grid.gridItems[i][y].getLinkedShip() != null) {
//                    Ship already placed
                    return false;
                }
            }
        } else {
            // vertical placement
            if (y + length > gridDimension) {
//                Out of bounds
                return false;
            }

            for (int j = y; j < y + length; j++) {
                if (grid.gridItems[x][j].getLinkedShip() != null) {
//                    Ship already placed
                    return false;
                }
            }
        }

        return true;
    }

    private static void placeShip(Grid grid, Ship ship, int x, int y, int orientation) {
        int length = ship.getShipSize();

        if (orientation == 0) {
            // horizontal placement
            for (int i = x; i < x + length; i++) {
                grid.gridItems[i][y].setLinkedShip(ship);
                ship.selectedCells.add(grid.gridItems[i][y]);
            }
        } else {
            // vertical placement
            for (int j = y; j < y + length; j++) {
                grid.gridItems[x][j].setLinkedShip(ship);
                ship.selectedCells.add(grid.gridItems[x][j]);
            }
        }
    }
}

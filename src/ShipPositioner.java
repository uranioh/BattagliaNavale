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
                    System.out.println("Ship placed");
                    System.out.println("Ship size: " + ship.getShipSize());
                    System.out.println("Ship orientation: " + orientation);
                    System.out.println("Ship coordinates: " + x + ", " + y);

                } else {
                    System.out.println("Can't place ship");
                }
            }
        }
    }

    private static boolean canPlaceShip(Grid grid, Ship ship, int x, int y, int orientation) {
        int length = ship.getShipSize();

        if (orientation == 0) {
            // horizontal placement
            if (x + length > gridDimension) {
                System.out.println("Ship out of bounds");
                return false;
            }

            for (int i = x; i < x + length; i++) {
                if (grid.gridItems[i][y].getLinkedShip() != null) {
                    System.out.println("Ship already placed");
                    return false;
                }
            }
        } else {
            // vertical placement
            if (y + length > gridDimension) {
                System.out.println("Ship out of bounds");
                return false;
            }

            for (int j = y; j < y + length; j++) {
                if (grid.gridItems[x][j].getLinkedShip() != null) {
                    System.out.println("Ship already placed");
                    return false;
                }
            }
        }

        return true;
    }

    private static void placeShip(Grid grid, Ship ship, int x, int y, int orientation) {
        int length = ship.getShipSize();

        if (orientation == 0) {
            System.out.println("Horizontal placement");
            // horizontal placement
            for (int i = x; i < x + length; i++) {
                grid.gridItems[i][y].setLinkedShip(ship);
                System.out.println("Ship placed at " + i + ", " + y);
            }
        } else {
            System.out.println("Vertical placement");
            // vertical placement
            for (int j = y; j < y + length; j++) {
                grid.gridItems[x][j].setLinkedShip(ship);
                System.out.println("Ship placed at " + x + ", " + j);
            }
        }
    }
}

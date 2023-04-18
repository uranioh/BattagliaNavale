import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.HashSet;
import java.util.Set;

public class Multiplayer extends JFrame {
    //    Background image panel
    JLabel mainPanel = new JLabel();
    JLabel gridPanel_player = new JLabel();
    JLabel gridPanel_enemy = new JLabel();

    //    Array of ships and their background
    Ship[] ships = new Ship[8];
    JLabel[] ships_bg = new JLabel[8];

    //    Matrix of the player map's JPanels
    GridItem[][] gridItems_player = new GridItem[10][10];
    GridItem[][] gridItems_enemy = new GridItem[10][10];

    JButton resetShips_Button = new JButton();
    ImageIcon resetShips_Icon = new ImageIcon("icon/icona_reset.png");

    //    Various icons
    ImageIcon bg = new ImageIcon("template.jpg");
    ImageIcon grid_player = new ImageIcon("grid.png");
    ImageIcon grid_enemy = new ImageIcon("grid.png");


    public Multiplayer() {
        Container c = getContentPane();

//        Set background image
        mainPanel.setIcon(bg);

//        Reset button properties
        resetShips_Button.setIcon(resetShips_Icon);
        resetShips_Button.setBorderPainted(false);
        resetShips_Button.setFocusPainted(false);
        resetShips_Button.setContentAreaFilled(false);
        resetShips_Button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        resetShips_Button.setBounds(120, 770, 50, 50);
        resetShips_Button.addActionListener(e -> resetShips());
        mainPanel.add(resetShips_Button);


        createGridItems();
        setShips();

//        Player grid
        gridPanel_player.setIcon(grid_player);
        gridPanel_player.setLayout(null);
        gridPanel_player.setBounds(bg.getIconWidth() / 2 - 600, bg.getIconHeight() / 2 - 594 / 2, 594, 594);
        mainPanel.add(gridPanel_player);

//        Enemy grid
        gridPanel_enemy.setIcon(grid_enemy);
        gridPanel_enemy.setLayout(null);
        gridPanel_enemy.setBounds(bg.getIconWidth() / 2 + 100, bg.getIconHeight() / 2 - 594 / 2, 594, 594);
        mainPanel.add(gridPanel_enemy);


        c.add(mainPanel);

        pack();
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void setShips() {
        for (int i = 0; i < 8; i++) {
            ships[i] = new Ship(this);
            ships[i].setIcon(new ImageIcon(String.format("ships/%d.png", i)));
//            ships[i].setBorder(new LineBorder(Color.RED, 2));

            ships_bg[i] = new JLabel();
            ships_bg[i].setIcon(new ImageIcon(String.format("ships_bg/%d.png", i)));

            final int v = i;

            ships[i].addMouseMotionListener(new MouseMotionAdapter() {
                public void mouseDragged(MouseEvent e) {
                    super.mouseDragged(e);

                    checkIfOverLabel(ships[v]);

                    int x = e.getXOnScreen() - ships[v].getParent().getLocationOnScreen().x - ships[v].getWidth() / 2;
                    int y = e.getYOnScreen() - ships[v].getParent().getLocationOnScreen().y - ships[v].getHeight() / 2;
                    int maxX = ships[v].getParent().getWidth() - ships[v].getWidth();
                    int maxY = ships[v].getParent().getHeight() - ships[v].getHeight();


                    x = Math.max(0, Math.min(x, maxX));
                    y = Math.max(0, Math.min(y, maxY));

                    if (x > 200 && y > 150)
                        ships[v].setLocation((Math.round((float) x / 54) * 54) - 14, (Math.round((float) y / 54) * 54) - 23);
                    else {
                        ships[v].setLocation(x, y);
                    }
                }
            });
        }

        resetShips();


        for (int i = 0; i < 8; i++) {
            mainPanel.add(ships[i]);
            ships[i].setProperties();
        }
        for (int i = 0; i < 8; i++) {
            mainPanel.add(ships_bg[i]);

        }
    }

    public void createGridItems() {
        int gapSize = 4;
        int col_increment = gapSize;

        for (int row = 0; row < 10; row++) {
            int row_increment = gapSize;
            for (int col = 0; col < 10; col++) {
                gridItems_player[row][col] = new GridItem();
                gridItems_player[row][col].setOpaque(false);

//                GridItem class properties
                gridItems_player[row][col].setX(row_increment);
                gridItems_player[row][col].setY(col_increment);


                gridItems_player[row][col].setBounds(row_increment, col_increment, 50, 50);
//                map[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                gridPanel_player.add(gridItems_player[row][col]);
                row_increment += 54;
            }
            col_increment += 54;
        }
    }

    public void resetShips() {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                gridItems_player[row][col].setOpaque(false);
            }
        }

        ships[0].setBounds(100, 68, 266, 50);
        ships[1].setBounds(100, 128, 212, 50);
        ships[2].setBounds(100, 190, 158, 50);
        ships[3].setBounds(100, 250, 104, 50);

        ships[4].setBounds(100, 337, 50, 266);
        ships[6].setBounds(100, 625, 50, 104);
        ships[7].setBounds(170, 517, 50, 212);
        ships[5].setBounds(170, 334, 50, 158);

        ships_bg[0].setBounds(100, 68, 258, 45);
        ships_bg[1].setBounds(100, 128, 206, 45);
        ships_bg[2].setBounds(100, 190, 154, 45);
        ships_bg[3].setBounds(100, 250, 99, 37);

        ships_bg[4].setBounds(100, 337, 45, 258);
        ships_bg[6].setBounds(100, 625, 37, 99);

        ships_bg[7].setBounds(170, 517, 45, 206);
        ships_bg[5].setBounds(170, 334, 45, 154);

        gridPanel_player.repaint();
    }


    public void checkIfOverLabel(Ship ship) {
        Set<Component> selectedCells = new HashSet<>(); // Set to keep track of selected grid cells

        for (Component comp : gridPanel_player.getComponents()) {
            if (comp instanceof GridItem panel) {
                Rectangle compBounds = SwingUtilities.convertRectangle(comp.getParent(), comp.getBounds(), gridPanel_player);
                Rectangle shipBounds = SwingUtilities.convertRectangle(ship.getParent(), ship.getBounds(), gridPanel_player);

                if (shipBounds.intersects(compBounds)) {
                    if (ship.getShipSize() > selectedCells.size()) {
                        selectedCells.add(panel); // Add the panel to the set of selected cells
                        panel.setState(true);
                        panel.setBackground(new Color(0, 0, 0, 0.5f));
                        panel.setOpaque(true);
                    }
                } else {
                    selectedCells.remove(panel); // Remove the panel from the set of selected cells
                    panel.setState(false);
                    panel.setOpaque(false);
                }
            }
        }

        gridPanel_player.repaint();
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.HashSet;
import java.util.Set;

public class SinglePlayer extends JFrame {
    //    Background image panel
    JLabel mainPanel = new JLabel();
    JLabel gridPanel = new JLabel();

    //    Array of boats' images
    Boat[] boats = new Boat[8];
    JLabel[] boats_bg = new JLabel[8];

    //    Matrix of the player map's JPanels
    GridItem[][] gridItems = new GridItem[10][10];

    JButton resetBoats_Button = new JButton();
    ImageIcon resetBoats_Icon = new ImageIcon("icon/icona_reset.png");

    //    Various icons
    ImageIcon bg = new ImageIcon("template.jpg");
    ImageIcon grid = new ImageIcon("grid.png");


    public SinglePlayer() {
        Container c = getContentPane();

//        Set background image
        mainPanel.setIcon(bg);

//        Reset button properties
        resetBoats_Button.setIcon(resetBoats_Icon);
        resetBoats_Button.setBorderPainted(false);
        resetBoats_Button.setFocusPainted(false);
        resetBoats_Button.setContentAreaFilled(false);
        resetBoats_Button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        resetBoats_Button.setBounds(120, 770, 50, 50);
        resetBoats_Button.addActionListener(e -> {
            resetBoats();
            // TODO: reset background mappanel
        });
        mainPanel.add(resetBoats_Button);


        createGridItems();
        setBoats();
        gridPanel.setIcon(grid);
        gridPanel.setLayout(null);
        gridPanel.setBounds(bg.getIconWidth() / 2 - 500, bg.getIconHeight() / 2 - 594 / 2, 594, 594);
        mainPanel.add(gridPanel);

        c.add(mainPanel);
        pack();
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void setBoats() {
        for (int i = 0; i < 8; i++) {
            boats[i] = new Boat();
            boats[i].setIcon(new ImageIcon(String.format("boats/%d.png", i)));

            boats_bg[i] = new JLabel();
            boats_bg[i].setIcon(new ImageIcon(String.format("boats_bg/%d.png", i)));

            final int v = i;

            boats[i].addMouseMotionListener(new MouseMotionAdapter() {
                public void mouseDragged(MouseEvent e) {
                    super.mouseDragged(e);

                    checkIfOverLabel(boats[v]);

                    int x = e.getXOnScreen() - boats[v].getParent().getLocationOnScreen().x - boats[v].getWidth() / 2;
                    int y = e.getYOnScreen() - boats[v].getParent().getLocationOnScreen().y - boats[v].getHeight() / 2;
                    int maxX = boats[v].getParent().getWidth() - boats[v].getWidth();
                    int maxY = boats[v].getParent().getHeight() - boats[v].getHeight();

                    x = Math.max(0, Math.min(x, maxX));
                    y = Math.max(0, Math.min(y, maxY));


//                    boats[v].setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                    if (x > 200 && y > 150)
                        boats[v].setLocation((Math.round((float) x / 54) * 54) - 20, (Math.round((float) y / 54) * 54) - 20);
                    else {
                        boats[v].setLocation(x, y);
                    }
                }
            });
        }

        resetBoats();


        for (int i = 0; i < 8; i++) {
            mainPanel.add(boats[i]);

        }
        for (int i = 0; i < 8; i++) {
            mainPanel.add(boats_bg[i]);

        }
    }

    public void createGridItems() {
        int gapSize = 4;
        int col_increment = gapSize;

        for (int row = 0; row < 10; row++) {
            int row_increment = gapSize;
            for (int col = 0; col < 10; col++) {
                gridItems[row][col] = new GridItem();
                gridItems[row][col].setOpaque(false);

//                GridItem class properties
                gridItems[row][col].setX(row_increment);
                gridItems[row][col].setY(col_increment);


                gridItems[row][col].setBounds(row_increment, col_increment, 50, 50);
//                map[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                gridPanel.add(gridItems[row][col]);
                row_increment += 54;
            }
            col_increment += 54;
        }
    }

    public void resetBoats() {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                gridItems[row][col].setOpaque(false);
            }
        }

        boats[0].setBounds(100, 68, 258, 45);
        boats[1].setBounds(100, 128, 206, 45);
        boats[2].setBounds(100, 190, 154, 45);
        boats[3].setBounds(100, 250, 99, 37);
        boats[4].setBounds(100, 337, 45, 258);
        boats[6].setBounds(100, 625, 37, 99);

        boats[7].setBounds(170, 517, 45, 206);
        boats[5].setBounds(170, 334, 45, 154);

        boats_bg[0].setBounds(100, 68, 258, 45);
        boats_bg[1].setBounds(100, 128, 206, 45);
        boats_bg[2].setBounds(100, 190, 154, 45);
        boats_bg[3].setBounds(100, 250, 99, 37);
        boats_bg[4].setBounds(100, 337, 45, 258);
        boats_bg[6].setBounds(100, 625, 37, 99);

        boats_bg[7].setBounds(170, 517, 45, 206);
        boats_bg[5].setBounds(170, 334, 45, 154);

        gridPanel.repaint();
    }


    public void checkIfOverLabel(Boat boat) {
        Set<Component> selectedCells = new HashSet<>(); // Set to keep track of selected grid cells

        for (Component comp : gridPanel.getComponents()) {
            if (comp instanceof GridItem panel) {
                Rectangle compBounds = SwingUtilities.convertRectangle(comp.getParent(), comp.getBounds(), gridPanel);
                Rectangle boatBounds = SwingUtilities.convertRectangle(boat.getParent(), boat.getBounds(), gridPanel);

                if (boatBounds.intersects(compBounds)) {
                    if (boat.getBoatSize() > selectedCells.size()) {
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

        gridPanel.repaint();
    }
}

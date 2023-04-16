import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

//RIFARE POSIZIONAMENTO DENTRO GRIDPANEL
//CONTROLLO FUNZIONAMENTO JLAYERPANE
public class SinglePlayer extends JFrame {
    //    Background image panel
    JLabel mainPanel = new JLabel();
    JLabel gridPanel = new JLabel();

    //    Array of boats' images
    JLabel[] boats = new JLabel[8];

    //    Matrix of the player map's JPanels
    Square[][] map = new Square[10][10];

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
        resetBoats_Button.setBounds(1680, 15, 50, 50);
        resetBoats_Button.addActionListener(e -> {
            resetBoats();
            // TODO: reset background mappanel
        });
        mainPanel.add(resetBoats_Button);

        createMap();
        setBoats();
        gridPanel.setIcon(grid);
        gridPanel.setLayout(null);
        gridPanel.setBounds(bg.getIconWidth() / 2 - 500, bg.getIconHeight() / 2 - 594 / 2, 594, 594);
        mainPanel.add(gridPanel);

        c.add(mainPanel);
        pack();
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void setBoats() {
        for (int i = 0; i < 8; i++) {
            boats[i] = new JLabel();
            boats[i].setIcon(new ImageIcon(String.format("barche/barca%d.png", i)));
            final int v = i;

//            JLayeredPane layeredPane = new JLayeredPane();
//            panel.setComponentZOrder(label, 0);


            boats[i].addMouseMotionListener(new MouseMotionAdapter() {
                public void mouseDragged(MouseEvent e) {
                    checkIfOverLabel(boats[v]);
                    super.mouseDragged(e);

                    int x = e.getXOnScreen() - boats[v].getParent().getLocationOnScreen().x - boats[v].getWidth() / 2;
                    int y = e.getYOnScreen() - boats[v].getParent().getLocationOnScreen().y - boats[v].getHeight() / 2;
                    int maxX = boats[v].getParent().getWidth() - boats[v].getWidth();
                    int maxY = boats[v].getParent().getHeight() - boats[v].getHeight();

                    x = Math.max(0, Math.min(x, maxX));
                    y = Math.max(0, Math.min(y, maxY));
                    boats[v].setLocation(x, y);
                }
            });


            boats[v].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    super.mousePressed(e);
                    boats[v].setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    super.mouseReleased(e);
                    boats[v].setCursor(Cursor.getDefaultCursor());

                    int size = getBoatSize(boats[v]);
                    System.out.println(size);

                    System.out.println(boats[v].getX());
                    System.out.println(boats[v].getY());
                }
            });
        }

        resetBoats();


        for (int i = 0; i < 8; i++) {
            mainPanel.add(boats[i]);
        }
    }

    public void createMap() {
        int gapSize = 4;
        int col_increment = gapSize;

        for (int row = 0; row < 10; row++) {
            int row_increment = gapSize;
            for (int col = 0; col < 10; col++) {
                map[row][col] = new Square();
                map[row][col].setOpaque(false);
                map[row][col].setX(row_increment);
                map[row][col].setY(col_increment);
                map[row][col].setBounds(row_increment, col_increment, 50, 50);
//                map[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                gridPanel.add(map[row][col]);
                row_increment += 54;
            }
            col_increment += 54;
        }
    }

    public void resetBoats() {
        boats[0].setBounds(100, 68, 258, 45);
        boats[1].setBounds(100, 128, 206, 45);
        boats[2].setBounds(100, 190, 154, 45);
        boats[3].setBounds(100, 250, 99, 37);
        boats[4].setBounds(100, 337, 45, 258);
        boats[6].setBounds(100, 625, 37, 99);

        boats[7].setBounds(170, 517, 45, 206);
        boats[5].setBounds(170, 334, 45, 154);
    }


    public void checkIfOverLabel(JLabel boat) {
        int selected = 0;
        for (Component comp : gridPanel.getComponents()) {
            if (comp instanceof Square panel) {
                Rectangle compBounds = SwingUtilities.convertRectangle(comp.getParent(), comp.getBounds(), gridPanel);
                Rectangle boatBounds = SwingUtilities.convertRectangle(boat.getParent(), boat.getBounds(), gridPanel);
                if (boatBounds.intersects(compBounds)) {
                    if (getBoatSize(boat) > selected) {
                        panel.setState(true);
                        panel.setBackground(new Color(0, 0, 0, 0.5f));
                        panel.setOpaque(true);
                        selected++;
                    }
                    else {
                        panel.setState(false);
                        panel.setOpaque(false);
                    }

                } else {
                    ((JComponent) comp).setOpaque(false);
                    panel.setState(false);
                    if (selected > 0) {
                        selected--;
                    }
                }
            }
        }
        gridPanel.repaint();
    }


//    public void checkIfOverLabel(JLabel label) {
//        for (Component comp : gridPanel.getComponents()) {
//            if (comp instanceof Square panel && comp.getBounds().intersects(label.getBounds())) {
//                System.out.println("Boat: " + label.getBounds());
//                System.out.println("Component: " + comp.getBounds());
//                panel.setState(true);
//                panel.setBackground(new Color(0, 0, 0, 0.5f));
//                panel.setOpaque(true);
//            } else if (comp instanceof JComponent) {
//                ((JComponent) comp).setOpaque(false);
//            }
//        }
//        gridPanel.repaint();
//    }

    private int getBoatSize(JLabel boat) {
        int size;
        if (boat.getWidth() > boat.getHeight()) {
            size = Math.round((float) boat.getWidth() / 50);
        } else {
            size = Math.round((float) boat.getHeight() / 50);
        }
        return size;
    }
}

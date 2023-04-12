import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SinglePlayer extends JFrame {
//    Background image panel
    JLabel main_panel = new JLabel();

//    Array of boats' images
    JLabel[] boatsImages = new JLabel[8];

//    Matrix of the player map's JPanels
    Square[][] map = new Square[10][10];
    JPanel[][] map_enemy = new JPanel[10][10];

    JButton resetBoatPosition = new JButton();

//    Various icons
    ImageIcon sfondo = new ImageIcon("template.jpg");
    ImageIcon icona_reset = new ImageIcon("icon/icona_reset.png");
    ImageIcon[] barca_img = new ImageIcon[8];


    public SinglePlayer() {
        Container c = getContentPane();

//        Set background image
        main_panel.setIcon(sfondo);

//        Reset button properties
        resetBoatPosition.setIcon(icona_reset);
        resetBoatPosition.setBorderPainted(false);
        resetBoatPosition.setFocusPainted(false);
        resetBoatPosition.setContentAreaFilled(false);
        resetBoatPosition.setCursor(new Cursor(Cursor.HAND_CURSOR));
        resetBoatPosition.setBounds(1680, 15, 50, 50);
        resetBoatPosition.addActionListener(e -> {
            reset_boats_position();
            // TODO: reset background mappanel
        });
        main_panel.add(resetBoatPosition);

        set_boats();
        set_map_panel();


        c.add(main_panel);
        pack();
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void set_boats() {
        for (int i = 0; i < 8; i++) {
            barca_img[i] = new ImageIcon(String.format("barche/barca%d.png", i));
            boatsImages[i] = new JLabel();
            boatsImages[i].setIcon(barca_img[i]);
            final int v = i;
            boatsImages[i].addMouseMotionListener(new MouseMotionAdapter() {
                public void mouseDragged(MouseEvent e) {
                    countPanelsUnderLabel(boatsImages[v]);
//                    System.out.println("Numero totale di JPanel sotto la JLabel: " + numPanels);
                    super.mouseDragged(e);
                    int x = e.getX() + boatsImages[v].getX();
                    int y = e.getY() + boatsImages[v].getY();
                    int maxX = boatsImages[v].getParent().getWidth() - boatsImages[v].getWidth();
                    int maxY = boatsImages[v].getParent().getHeight() - boatsImages[v].getHeight();
                    x = Math.max(0, Math.min(x, maxX));
                    y = Math.max(0, Math.min(y, maxY));
                    boatsImages[v].setLocation(x, y);
                }
            });


            boatsImages[v].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    super.mousePressed(e);
                    boatsImages[v].setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    super.mouseReleased(e);
                    boatsImages[v].setCursor(Cursor.getDefaultCursor());
                }
            });
        }

        reset_boats_position();


        for (int i = 0; i < 8; i++) {
            main_panel.add(boatsImages[i]);
        }
    }

    public void set_map_panel() {
        int col_increment = 275;
        for (int row = 0; row < 10; row++) {
            int row_increment = 367;
            for (int col = 0; col < 5; col++) {
                map[row][col] = new Square();
                map[row][col].setX(row);
                map[row][col].setY(col);
                //map[row][col].setBackground(Color.black);
                map[row][col].setOpaque(false);
                map[row][col].setBounds(row_increment, col_increment, 48, 48);
                main_panel.add(map[row][col]);
                row_increment += 53;
            }
            for (int col = 5; col < 10; col++) {
                map[row][col] = new Square();
                map[row][col].setX(row);
                map[row][col].setY(col);
                //map[row][col].setBackground(Color.black);
                map[row][col].setBorder(BorderFactory.createLineBorder(Color.black));
                map[row][col].setOpaque(false);
                map[row][col].setBounds(row_increment, col_increment, 48, 48);
                main_panel.add(map[row][col]);
                row_increment += 52;

            }
            if (row == 3) {
                col_increment += 53;
            } else if (row == 9) {
                col_increment += 54;
            } else {
                if (row % 2 == 0) {
                    col_increment += 53;
                }
                if (row % 2 == 1) {
                    col_increment += 52;
                }
            }

        }
    }

    public void reset_boats_position() {
        boatsImages[0].setBounds(99, 68, 258, 45);
        boatsImages[1].setBounds(99, 128, 206, 45);
        boatsImages[2].setBounds(99, 190, 154, 45);
        boatsImages[3].setBounds(99, 250, 99, 37);
        boatsImages[4].setBounds(102, 337, 45, 258);
        boatsImages[5].setBounds(170, 334, 45, 154);
        boatsImages[6].setBounds(105, 625, 37, 99);
        boatsImages[7].setBounds(172, 517, 45, 206);
    }


    public void countPanelsUnderLabel(JLabel label) {
        for (Component comp : main_panel.getComponents()) {
            if (comp instanceof Square panel && comp.getBounds().intersects(label.getBounds())) {
                panel.setState(true);
                panel.setBackground(new Color(0, 0, 0, 0.5f));
                panel.setOpaque(true);
            } else if (comp instanceof JComponent) {
                ((JComponent) comp).setOpaque(false);
            }
        }
        main_panel.repaint();
    }

}

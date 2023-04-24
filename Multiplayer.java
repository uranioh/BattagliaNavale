import javax.swing.*;
import java.awt.*;

public class Multiplayer extends JFrame {
    //    Background image panel
    JLabel mainPanel = new JLabel();

    Grid playerGrid, enemyGrid;

    //    Array of ships and their background
    Ship[] ships = new Ship[8];
    JLabel[] ships_bg = new JLabel[8];

    JButton resetShips_Button = new JButton();
    ImageIcon resetShips_Icon = new ImageIcon("assets/icona_reset.png");

    //    Various icons
    ImageIcon bg = new ImageIcon("assets/background.jpg");


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


        setShips();

//        Player grid
        playerGrid = new Grid(bg.getIconWidth() / 2 - 600, bg.getIconHeight() / 2 - 594 / 2, 594, 594);
        mainPanel.add(playerGrid);

//        Enemy grid
        enemyGrid = new Grid(bg.getIconWidth() / 2 + 100, bg.getIconHeight() / 2 - 594 / 2, 594, 594);
        mainPanel.add(enemyGrid);


        c.add(mainPanel);

        pack();
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    public void setShips() {
        for (int i = 0; i < 8; i++) {
            ships[i] = new Ship(this);
            ships[i].setIcon(new ImageIcon(String.format("ships/%d.png", i)));

            ships_bg[i] = new JLabel();
            ships_bg[i].setIcon(new ImageIcon(String.format("ships_bg/%d.png", i)));
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

    public void resetShips() {
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
    }
}

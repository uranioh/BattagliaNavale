import javax.swing.*;
import java.awt.*;

public class UI extends JLabel {
    Grid playerGrid, enemyGrid;

    //    Array of ships and their background
    Ship[] ships = new Ship[6];
    JLabel[] ships_bg = new JLabel[6];

    JButton resetShips_Button = new JButton();
    ImageIcon resetShips_Icon = new ImageIcon("assets/icona_reset.png");

    //    Various icons
    ImageIcon bg = new ImageIcon("assets/background.jpg");

    public UI() {
        setIcon(bg);

//        Reset button properties
        resetShips_Button.setIcon(resetShips_Icon);
        resetShips_Button.setBorderPainted(false);
        resetShips_Button.setFocusPainted(false);
        resetShips_Button.setContentAreaFilled(false);
        resetShips_Button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        resetShips_Button.setBounds(120, 770, 50, 50);
        resetShips_Button.addActionListener(e -> resetShips());
        add(resetShips_Button);


        setShips();

//        Player grid
        playerGrid = new Grid(bg.getIconWidth() / 2 - 600, bg.getIconHeight() / 2 - 594 / 2, 594, 594);
        add(playerGrid);

//        Enemy grid
        enemyGrid = new Grid(bg.getIconWidth() / 2 + 100, bg.getIconHeight() / 2 - 594 / 2, 594, 594);
        add(enemyGrid);


    }

    public void setShips() {
        for (int i = 0; i < 6; i++) {
            ships[i] = new Ship(this);
            ships[i].setIcon(new ImageIcon(String.format("ships/%d.png", i)));
            ships[i].setSize(ships[i].getIcon().getIconWidth(), ships[i].getIcon().getIconHeight());

            ships_bg[i] = new JLabel();
            ships_bg[i].setIcon(new ImageIcon(String.format("ships_bg/%d.png", i)));
            ships_bg[i].setSize(ships_bg[i].getIcon().getIconWidth(), ships_bg[i].getIcon().getIconHeight());
        }

//        Horizontal ships
        for (int i = 0; i < 3; i++) {
            ships[i].setDefaultPosition(100, 150 + i * 50);
            ships_bg[i].setBounds(100, 150 + i * 50, ships_bg[i].getIcon().getIconWidth(), ships_bg[i].getIcon().getIconHeight());
        }

//        Vertical ships
        for (int i = 3; i < 6; i++) {
            ships[i].setDefaultPosition(100 + (i - 3) * 50, 350);
            ships_bg[i].setBounds(100 + (i - 3) * 50, 350, ships_bg[i].getIcon().getIconWidth(), ships_bg[i].getIcon().getIconHeight());
        }

        for (int i = 0; i < 6; i++) {
            add(ships[i]);
            ships[i].setProperties();
        }
        for (int i = 0; i < 6; i++) {
            add(ships_bg[i]);
        }
    }

    public void resetShips() {
        for (Ship s : ships) {
            for (GridItem gridItem : s.selectedCells) {
                gridItem.setBorder(null);
            }
            s.selectedCells.clear();
            s.resetPosition();
        }
    }
}

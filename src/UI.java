import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI extends backgroundPainter {
    JPanel gridPanel = new JPanel();
    JLabel playGameTitle = new JLabel("In attesa dell'avversario...", JLabel.CENTER);

    JLabel playGameLabelIcon = new JLabel("");
    JLabel playGameText = new JLabel("Gioca");
    Grid playerGrid, enemyGrid;

    //    Array of ships and their background
    Ship[] ships = new Ship[6];
    JLabel[] ships_bg = new JLabel[6];

    JButton resetShips_Button = new JButton();
    JButton playGame = new JButton();
    ImageIcon resetShips_Icon = new ImageIcon("src/assets/icona_reset.png");

    //    Various icons
    ImageIcon bg = new ImageIcon("src/assets/background.jpg");
    ImageIcon playButtonImage = new ImageIcon("src/assets/play-button.png");

    public UI() {


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
        AddListenerPlayGameButton();

    }

    public void AddListenerPlayGameButton() {
        playGame.addActionListener(e -> {
            for (int c = 0; c < 6; c++) {
                int x = 0;
                int y = 0;
                x = (ships[c].getLocation().x - playerGrid.getLocation().x);
                y = (ships[c].getLocation().y - playerGrid.getLocation().y);


                remove(ships[c]);
                ships[c].setBounds(x, y, ships[c].getIcon().getIconWidth(), ships[c].getIcon().getIconHeight());
                playerGrid.add(ships[c]);

            }
            playerGrid.resetGridItemBorder();


            for (int i = 0; i < 6; i++) {
                remove(ships_bg[i]);
            }
            remove(resetShips_Button);
            remove(playGame);
            remove(playerGrid);
            remove(enemyGrid);

            setLayout(new BorderLayout());

            gridPanel.setOpaque(false);
            add(playGameTitle, BorderLayout.NORTH);
            add(gridPanel, BorderLayout.SOUTH);


            playGameTitle.setForeground(Color.WHITE);
            playGameTitle.setFont(new Font("Arial", Font.BOLD, 40));
            gridPanel.add(playerGrid);
            gridPanel.add(enemyGrid);
            playerGrid.setPreferredSize(new Dimension(playerGrid.getIcon().getIconWidth(), playerGrid.getIcon().getIconHeight()));
            revalidate();
            repaint();
        });
    }

    public void addPlayGameButton() {
        //playGame.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        playGame.setLayout(null);
        playGame.setBorder(new roundedBorder(200));
        playGame.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5, true));
        playGame.setContentAreaFilled(false);
        playGame.setFocusPainted(false);
        playGame.setCursor(new Cursor(Cursor.HAND_CURSOR));
        playGame.setBounds(1455, 900, 150, 50);
        playGame.setForeground(Color.white);
        playGameText.setForeground(Color.white);
        playGameLabelIcon.setIcon(playButtonImage);
        playGameText.setFont(new Font("Arial", Font.BOLD, 23));
        playGameText.setBounds(30, 15, 80, 20);
        playGameLabelIcon.setBounds(100, 13, playGameLabelIcon.getIcon().getIconWidth(), playGameLabelIcon.getIcon().getIconHeight());
        playGame.add(playGameText);
        playGame.add(playGameLabelIcon);
        add(playGame);

        this.revalidate();
        this.repaint();

    }

    public void setShips() {
        for (int i = 0; i < 6; i++) {
            ships[i] = new Ship(this);
            ships[i].setIcon(new ImageIcon(String.format("src/ships/%d.png", i)));
            ships[i].setSize(ships[i].getIcon().getIconWidth(), ships[i].getIcon().getIconHeight());

            ships_bg[i] = new JLabel();
            ships_bg[i].setIcon(new ImageIcon(String.format("src/ships_bg/%d.png", i)));
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
        Ship.placedCounter = 0;

        this.remove(playGame);
        this.revalidate();
        this.repaint();
    }
}

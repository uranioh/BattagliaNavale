import javax.swing.*;
import java.awt.*;

public class UI extends BackgroundPainter {
    JPanel gridPanel = new JPanel();
    JLabel playGameTitle = new JLabel("In attesa dell'avversario...", JLabel.CENTER);

    JLabel turnLabel = new JLabel("È il tuo turno", JLabel.CENTER);

    JLabel playGameLabelIcon = new JLabel("");
    JLabel playGameText = new JLabel("Gioca");


    //    Array of ships and their background

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
        Globals.playerGrid = new Grid(bg.getIconWidth() / 2 - 600, bg.getIconHeight() / 2 - 594 / 2, 594, 594);
        Globals.playerGrid.setOwner("player");
        add(Globals.playerGrid);

//        Enemy grid
        Globals.enemyGrid = new Grid(bg.getIconWidth() / 2 + 100, bg.getIconHeight() / 2 - 594 / 2, 594, 594);
        Globals.enemyGrid.setOwner("enemy");
        add(Globals.enemyGrid);

        enablePlayButton();
    }

    public void enablePlayButton() {
        playGame.addActionListener(e -> playGame());
    }

    public void playGame() {
        for (int c = 0; c < 6; c++) {
            int x = (Globals.playerShips[c].getLocation().x - Globals.playerGrid.getLocation().x);
            int y = (Globals.playerShips[c].getLocation().y - Globals.playerGrid.getLocation().y);


            remove(Globals.playerShips[c]);
            Globals.playerShips[c].setBounds(x, y, Globals.playerShips[c].getIcon().getIconWidth(), Globals.playerShips[c].getIcon().getIconHeight());
            Globals.playerGrid.add(Globals.playerShips[c]);

        }
        Globals.playerGrid.resetGridItemBorder();

        for (int c = 0; c < 6; c++) {
            int x = (Globals.enemyShips[c].getLocation().x - Globals.enemyGrid.getLocation().x);
            int y = (Globals.enemyShips[c].getLocation().y - Globals.enemyGrid.getLocation().y);


            remove(Globals.enemyShips[c]);
            Globals.enemyShips[c].setBounds(x, y, Globals.enemyShips[c].getIcon().getIconWidth(), Globals.enemyShips[c].getIcon().getIconHeight());
            Globals.enemyGrid.add(Globals.enemyShips[c]);

        }
        Globals.enemyGrid.resetGridItemBorder();



        for (int i = 0; i < 6; i++) {
            remove(ships_bg[i]);
        }

        remove(resetShips_Button);
        remove(playGame);
        remove(Globals.playerGrid);
        remove(Globals.enemyGrid);

        setLayout(new BorderLayout());

        gridPanel.setOpaque(false);
        add(playGameTitle, BorderLayout.NORTH);
        add(turnLabel, BorderLayout.CENTER);
        add(gridPanel, BorderLayout.SOUTH);


        playGameTitle.setForeground(Color.WHITE);
        playGameTitle.setFont(new Font("Arial", Font.BOLD, 40));

        turnLabel.setForeground(Color.WHITE);
        turnLabel.setFont(new Font("Arial", Font.BOLD, 40));

        gridPanel.add(Globals.playerGrid);
        gridPanel.add(Globals.enemyGrid);

        Globals.playerGrid.setPreferredSize(new Dimension(
                Globals.playerGrid.getIcon().getIconWidth(),
                Globals.playerGrid.getIcon().getIconHeight()
        ));

        revalidate();
        repaint();
    }

    public void addPlayButton() {
        //playGame.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        playGame.setLayout(null);
        playGame.setBorder(new RoundedBorder(200));
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

        this.add(playGame);
        this.revalidate();
        this.repaint();
    }

    public void setShips() {
//        Create ships and their background
        for (int i = 0; i < 6; i++) {
//            Player ships
            Globals.playerShips[i] = new Ship(this);
            Globals.playerShips[i].setIcon(new ImageIcon(String.format("src/ships/%d.png", i)));
            Globals.playerShips[i].setSize(Globals.playerShips[i].getIcon().getIconWidth(), Globals.playerShips[i].getIcon().getIconHeight());

//            Enemy ships
            Globals.enemyShips[i] = new Ship(this);
            Globals.enemyShips[i].setIcon(new ImageIcon(String.format("src/ships/%d.png", i)));
            Globals.enemyShips[i].setSize(Globals.enemyShips[i].getIcon().getIconWidth(), Globals.enemyShips[i].getIcon().getIconHeight());

//            Ships background
            ships_bg[i] = new JLabel();
            ships_bg[i].setIcon(new ImageIcon(String.format("src/ships_bg/%d.png", i)));
            ships_bg[i].setSize(ships_bg[i].getIcon().getIconWidth(), ships_bg[i].getIcon().getIconHeight());
        }

//        Horizontal ships bounds
        for (int i = 0; i < 3; i++) {
            Globals.playerShips[i].setDefaultPosition(100, 150 + i * 50);
            ships_bg[i].setBounds(100, 150 + i * 50, ships_bg[i].getIcon().getIconWidth(), ships_bg[i].getIcon().getIconHeight());
        }

//        Vertical ships bounds
        for (int i = 3; i < 6; i++) {
            Globals.playerShips[i].setDefaultPosition(100 + (i - 3) * 50, 350);
            ships_bg[i].setBounds(100 + (i - 3) * 50, 350, ships_bg[i].getIcon().getIconWidth(), ships_bg[i].getIcon().getIconHeight());
        }

        for (int i = 0; i < 6; i++) {
            add(Globals.playerShips[i]);
            Globals.playerShips[i].setProperties();
            Globals.enemyShips[i].setProperties();
        }
        for (int i = 0; i < 6; i++) {
            add(ships_bg[i]);
        }
    }

    public void resetShips() {
        for (Ship s : Globals.playerShips) {
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

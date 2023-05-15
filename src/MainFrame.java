import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    JLabel gameTitle = new JLabel("BATTAGLIA NAVALE");

    //    JPanels
    JPanel bottomPanel = new JPanel();
    JLabel mainPanel = new JLabel();
    JPanel localePanel = new JPanel();
    JPanel onlinePanel = new JPanel();
    JPanel accountPanel = new JPanel();
    JPanel settingsPanel = new JPanel();

    //    JButtons
    JButton localeButton = new JButton("LOCALE");
    JButton onlineButton = new JButton("ONLINE");
    JButton accountButton = new JButton("ACCOUNT");
    JButton settingsButton = new JButton("IMPOSTAZIONI");

    //    Background and game modes
    ImageIcon bg = new ImageIcon("src/assets/background.jpg");
    Multiplayer multiplayerGame = new Multiplayer();
    Singleplayer singleplayerGame = new Singleplayer();


    public MainFrame() {
        Container c = getContentPane();
        mainPanel.setIcon(bg);
        mainPanel.add(gameTitle);
        gameTitle.setBounds(600, 100, 1000, 100);
        gameTitle.setForeground(Color.WHITE);
        gameTitle.setFont(new Font("Arial", Font.BOLD, 70));

        addLowerUIElements();

        setMinimumSize(new Dimension(1700, 1000));
        c.add(mainPanel);
        c.setLayout(new FlowLayout());
        setResizable(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void addLowerUIElements() {
        localePanel.add(localeButton);
        onlinePanel.add(onlineButton);
        accountPanel.add(accountButton);
        settingsPanel.add(settingsButton);

        bottomPanel.add(localePanel);
        bottomPanel.add(onlinePanel);
        bottomPanel.add(accountPanel);
        bottomPanel.add(settingsPanel);

        // Creazione dei bottoni con bordi arrotondati

        localeButton.setBorder(new RoundedBorder(30)); // Imposta un raggio di 20 pixel per l'arrotondamento del bordo del bottone "locale"
        onlineButton.setBorder(new RoundedBorder(30)); // Imposta un raggio di 20 pixel per l'arrotondamento del bordo del bottone "online"
        accountButton.setBorder(new RoundedBorder(30)); // Imposta un raggio di 20 pixel per l'arrotondamento del bordo del bottone "account"
        settingsButton.setBorder(new RoundedBorder(30)); // Imposta un raggio di 20 pixel per l'arrotondamento del bordo del bottone "impostazioni"


        localePanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
        localePanel.setOpaque(false);

        onlinePanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
        onlinePanel.setOpaque(false);

        accountPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
        accountPanel.setOpaque(false);

        settingsPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
        settingsPanel.setOpaque(false);

        localeButton.setPreferredSize(new Dimension(210, 80));
        onlineButton.setPreferredSize(new Dimension(210, 80));
        accountButton.setPreferredSize(new Dimension(210, 50));
        settingsButton.setPreferredSize(new Dimension(210, 50));

        //locale.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5,true));
        localeButton.setContentAreaFilled(false);
        localeButton.setForeground(Color.white);
        localeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //online.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
        onlineButton.setContentAreaFilled(false);
        onlineButton.setForeground(Color.white);
        onlineButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //account.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
        accountButton.setContentAreaFilled(false);
        accountButton.setForeground(Color.white);
        accountButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //impostazioni.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
        settingsButton.setContentAreaFilled(false);
        settingsButton.setForeground(Color.white);
        settingsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        localeButton.setFont(new Font("Arial", Font.BOLD, 35));
        onlineButton.setFont(new Font("Arial", Font.BOLD, 35));
        accountButton.setFont(new Font("Arial", Font.BOLD, 20));
        settingsButton.setFont(new Font("Arial", Font.BOLD, 20));

        localeButton.setFocusPainted(false);
        onlineButton.setFocusPainted(false);
        accountButton.setFocusPainted(false);
        settingsButton.setFocusPainted(false);

        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.setBounds(655, 500, 600, 250);
        bottomPanel.setOpaque(false);
        mainPanel.add(bottomPanel);

        localeButton.addActionListener(e -> newGame(singleplayerGame));
        onlineButton.addActionListener(e -> newGame(multiplayerGame));
    }

    //    aggiunge la modalità di gioco selezionata al frame
    private void newGame(UI game) {
        Container c = getContentPane();
        c.remove(mainPanel);
        c.setLayout(new BorderLayout());
        c.add(game, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
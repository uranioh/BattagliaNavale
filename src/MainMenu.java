import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    JLabel gameTitle = new JLabel("BATTAGLIA NAVALE");
    JPanel bottom = new JPanel();
    JLabel mainPanel = new JLabel();
    JPanel localePanel = new JPanel();
    JPanel onlinePanel = new JPanel();
    JPanel accountPanel = new JPanel();
    JPanel impostazioniPanel = new JPanel();
    JButton locale = new JButton("LOCALE");
    JButton online = new JButton("ONLINE");
    JButton account = new JButton("ACCOUNT");
    JButton impostazioni = new JButton("IMPOSTAZIONI");
    ImageIcon bg = new ImageIcon("assets/background.jpg");


    public MainMenu() {
        Container c = getContentPane();
        mainPanel.setIcon(bg);
        mainPanel.add(gameTitle);
        gameTitle.setBounds(600, 100, 1000, 100);
        gameTitle.setForeground(Color.WHITE);
        gameTitle.setFont(new Font("Arial", Font.BOLD, 70));

        createBottom();


        c.add(mainPanel);
        c.setLayout(new FlowLayout());
        pack();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void createBottom() {
        localePanel.add(locale);
        onlinePanel.add(online);
        accountPanel.add(account);
        impostazioniPanel.add(impostazioni);

        bottom.add(localePanel);
        bottom.add(onlinePanel);
        bottom.add(accountPanel);
        bottom.add(impostazioniPanel);

        // Creazione dei bottoni con bordi arrotondati

        locale.setBorder(new roundedBorder(30)); // Imposta un raggio di 20 pixel per l'arrotondamento del bordo del bottone "locale"
        online.setBorder(new roundedBorder(30)); // Imposta un raggio di 20 pixel per l'arrotondamento del bordo del bottone "online"
        account.setBorder(new roundedBorder(30)); // Imposta un raggio di 20 pixel per l'arrotondamento del bordo del bottone "account"
        impostazioni.setBorder(new roundedBorder(30)); // Imposta un raggio di 20 pixel per l'arrotondamento del bordo del bottone "impostazioni"


        localePanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
        localePanel.setOpaque(false);

        onlinePanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
        onlinePanel.setOpaque(false);

        accountPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
        accountPanel.setOpaque(false);

        impostazioniPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
        impostazioniPanel.setOpaque(false);

        locale.setPreferredSize(new Dimension(210, 80));
        online.setPreferredSize(new Dimension(210, 80));
        account.setPreferredSize(new Dimension(210, 50));
        impostazioni.setPreferredSize(new Dimension(210, 50));

        //locale.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5,true));
        locale.setContentAreaFilled(false);
        locale.setForeground(Color.white);
        locale.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //online.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
        online.setContentAreaFilled(false);
        online.setForeground(Color.white);
        online.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //account.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
        account.setContentAreaFilled(false);
        account.setForeground(Color.white);
        account.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //impostazioni.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
        impostazioni.setContentAreaFilled(false);
        impostazioni.setForeground(Color.white);
        impostazioni.setCursor(new Cursor(Cursor.HAND_CURSOR));

        locale.setFont(new Font("Arial", Font.BOLD, 35));
        online.setFont(new Font("Arial", Font.BOLD, 35));
        account.setFont(new Font("Arial", Font.BOLD, 20));
        impostazioni.setFont(new Font("Arial", Font.BOLD, 20));

        locale.setFocusPainted(false);
        online.setFocusPainted(false);
        account.setFocusPainted(false);
        impostazioni.setFocusPainted(false);

        bottom.setLayout(new FlowLayout());
        bottom.setBounds(655, 620, 600, 250);
        bottom.setOpaque(false);
        mainPanel.add(bottom);

    }


}



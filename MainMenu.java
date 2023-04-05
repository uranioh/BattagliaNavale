import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame implements ActionListener {
    JPanel top = new JPanel();
    JPanel bottom = new JPanel();

    JLabel main = new JLabel("""
            <html>
                <center>
                    <h1>Benvenuto nel gioco Battaglia Navale!</h1>
                    <p>Seleziona una delle opzioni qui sotto per iniziare a giocare.</p>
                </center>
            </html>
            """);

    JButton newGame_Singleplayer = new JButton("Single player");
    JButton newGame_Multiplayer = new JButton("Multiplayer");
    public MainMenu() {
        super("Battaglia Navale - Menù Principale");
        Container c =  this.getContentPane();
        c.setLayout(new GridLayout(2, 1, 10, 10));

        top.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        top.add(main);

        bottom.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bottom.setLayout(new GridLayout(1, 2, 10, 10));

        newGame_Singleplayer.addActionListener(this);
        newGame_Singleplayer.setFocusPainted(false);
        bottom.add(newGame_Singleplayer);

        newGame_Multiplayer.addActionListener(this);
        newGame_Multiplayer.setFocusPainted(false);
        bottom.add(newGame_Multiplayer);

        c.add(top);
        c.add(bottom);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

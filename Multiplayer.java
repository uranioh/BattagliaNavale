import javax.swing.*;
import java.awt.*;

public class Multiplayer extends JFrame {
    //    Background image panel
    UI mainPanel = new UI();


    public Multiplayer() {
        Container c = getContentPane();

//        Set background image


        c.add(mainPanel);

        pack();
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }
}

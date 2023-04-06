import javax.swing.*;
import java.awt.*;

public class SinglePlayer extends JFrame {
    JLabel main_panel= new JLabel();
    JPanel map_panel= new JPanel();
    JPanel map_enemy_panel= new JPanel();
    JPanel[][] map = new JPanel[10][10];
    JPanel[][] map_enemy = new JPanel[10][10];
    ImageIcon logo= new ImageIcon("template.JPG");

    public SinglePlayer(){
        Container c= getContentPane();
        main_panel.setIcon(logo);
        c.add(main_panel);
        setSize(1773,898);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}

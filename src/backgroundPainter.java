import javax.swing.*;
import java.awt.*;

public class backgroundPainter extends JPanel {
    private final Image backgroundImage;

    public backgroundPainter() {
        // Carica l'immagine di sfondo
        backgroundImage = new ImageIcon("src/assets/background.jpg").getImage();
        setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Disegna l'immagine di sfondo
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }


}




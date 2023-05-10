import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;

public class roundedBorder extends AbstractBorder {
    private final int cornerRadius; // Raggio dell'arrotondamento

    public roundedBorder(int cornerRadius) {
        this.cornerRadius = cornerRadius;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Creazione di un'area con un rettangolo arrotondato
        RoundRectangle2D roundRectangle = new RoundRectangle2D.Double(x, y, width - 1, height - 1, cornerRadius, cornerRadius);
        Area area = new Area(roundRectangle);

        // Disegno del bordo arrotondato
        g2d.setColor(c.getForeground());
        g2d.setStroke(new BasicStroke(3)); // Spessore del bordo
        g2d.draw(area);

        g2d.dispose();
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(cornerRadius, cornerRadius, cornerRadius, cornerRadius);
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        insets.top = insets.left = insets.bottom = insets.right = cornerRadius;
        return insets;
    }
}

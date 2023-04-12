import javax.swing.*;

public class Square extends JPanel {
    public boolean state;
    public boolean state_back;
    int x;
    int y;

    public Square() {
        state = false;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setState_back(boolean state_back) {
        this.state_back = state_back;
    }

    public boolean getStateback() {
        return this.state_back;
    }
}

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class windowListenerSinglePlayer implements WindowListener {
    SinglePlayer s;
    public windowListenerSinglePlayer(SinglePlayer s){
        this.s=s;
    }
    @Override
    public void windowOpened(WindowEvent e) {
        System.out.println(s.getWidth());
        if(s.getWidth()<1919){
            System.out.println(s.getWidth());
            s.setBoatsResize();
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}

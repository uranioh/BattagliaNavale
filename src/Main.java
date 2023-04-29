import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | UnsupportedLookAndFeelException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        //MainFrame window = new MainFrame();




        generateCpuMatrix t=new generateCpuMatrix();
    }

}

package Main;

import com.sun.tools.javac.Main;

import javax.swing.*;

public class MainClass {
    public static void main(String[] args) {
        JFrame window = new JFrame("Grafik");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setUndecorated(true);

        MainPanel gp = new MainPanel();
        window.add(gp);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gp.startGameThread();
    }
}

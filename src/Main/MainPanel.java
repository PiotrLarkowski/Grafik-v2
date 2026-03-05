package Main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    public int FPS = 60;
    public GamePanel() {
        this.setPreferredSize(new Dimension((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
                (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()));
        this.setBackground(Color.CYAN);
        this.setLayout(null);
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {

    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        paintBasicWindowLook(graphics);
        int rowHeight = 30, rowWidth = 25;
        for (int i = 1; i < 11; i++) {
            graphics.drawLine(100,100+(rowHeight*i),1100,100+(rowHeight*i));
        }
        for (int i = 0; i < 41; i++) {
            graphics.drawLine(100+(rowWidth*i),130,100+(rowWidth*i),400);
        }
    }
}

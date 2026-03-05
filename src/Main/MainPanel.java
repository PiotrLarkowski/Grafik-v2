package Main;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel implements Runnable {
    public static int appPhase = 0;
    static boolean findNumberOfDays = false;
    public int FPS = 60;
    static int WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    static int HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    KeyHandler keyH = new KeyHandler();
    MyMouseListener mouseL = new MyMouseListener();
    Thread gameThread;

    public MainPanel() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(new Color(34, 139, 80));
        this.setDoubleBuffered(true);
        this.addMouseListener(mouseL);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.setLayout(null);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        long drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000) {
//                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.BLACK);

        g2.setFont(new Font("Tahoma", Font.BOLD, 30));
        g2.drawString("ESC",(int) (WIDTH * 0.93),(int)(WIDTH * 0.05));
        g2.setFont(new Font("Tahoma", Font.BOLD, 20));
        g2.drawString("Wyjście",(int) (WIDTH * 0.925),(int)(WIDTH * 0.07));
        g2.setStroke(new BasicStroke(5));
        drawRectPercent(g2,0.92,0.05,0.05,0.1);
        g2.setStroke(new BasicStroke(1));

        printHints(g2);

        drawButtons(g2);

        String month = "Styczen";
        drawMonthGrid(g2, month);
    }

    private static void drawButtons(Graphics2D g2) {
        g2.setFont(new Font("Tahoma", Font.BOLD, 20));
        g2.drawString("Nowy grafik",(int) (WIDTH * 0.03),(int) (HEIGHT * 0.05));
        drawRectPercent(g2,0.01,0.02,0.1,0.05);
        g2.drawString("Przycik 2",(int) (WIDTH * 0.03),(int) (HEIGHT * 0.11));
        drawRectPercent(g2,0.01,0.08,0.1,0.05);
        g2.drawString("Przycik 3",(int) (WIDTH * 0.03),(int) (HEIGHT * 0.17));
        drawRectPercent(g2,0.01,0.14,0.1,0.05);
    }

    private void printHints(Graphics2D g2) {
        g2.setFont(new Font("Tahoma", Font.BOLD, 24));

        g2.drawString("PODPOWIEDŹ",(int) (WIDTH * 0.72),(int)(WIDTH * 0.05));
        g2.drawRoundRect((int) (WIDTH * 0.6), (int)(HEIGHT * 0.05), (int)(WIDTH * 0.3), (int)(HEIGHT * 0.2), 25, 25);
        g2.setFont(new Font("Tahoma", Font.BOLD, 14));

        String textHint = "Kliknij na kwadrat przedstawiający dany dzień dla danej osoby by ustawić \n\n " +
                "że ma to być dzień W - Wolny, D/W - Dniówka lub Wolny, \n\n" +
                " N/W - Nocka lub Wolny, D - Dniówka, N - Nocka, U - Urlop";
        drawStringMultiline(g2, textHint,(int) (WIDTH * 0.62),(int)(WIDTH * 0.07));
    }

    void drawStringMultiline(Graphics2D g2, String text, int x, int y) {
        FontMetrics fm = g2.getFontMetrics();
        int lineHeight = fm.getHeight();

        for(String line : text.split("\n")) {
            g2.drawString(line, x, y);
            y += lineHeight;
        }
    }

    private static void drawMonthGrid(Graphics2D g2, String month) {
        g2.setFont(new Font("Tahoma", Font.BOLD, 14));
        int numberOfDaysInMonth = setNumberOfDays(month);

        for (int i = 0; i < numberOfDaysInMonth; i++) {
            g2.drawString(String.valueOf(i+1),(int)((WIDTH * 0.1)+15)+(int)((WIDTH * 0.02)*i),(int) (HEIGHT * 0.5)-5);
            drawRectPercent(g2, 0.1+(0.02*i), 0.50, 0.02, 0.04);
            for (int j = 0; j < 6; j++) {
                drawRectPercent(g2, 0.1+(0.02*i), 0.50+(0.04*j), 0.02, 0.04);
            }
        }
    }

    static void drawRectPercent(Graphics2D g2, double x, double y, double wigth, double height) {
        int px = (int) (WIDTH * x);
        int py = (int) (HEIGHT * y);
        int pw = (int) (WIDTH * wigth);
        int ph = (int) (HEIGHT * height);

        g2.drawRect(px, py, pw, ph);
    }

    private static int setNumberOfDays(String month) {
        int numberOfDays = 0;
        findNumberOfDays = false;
        String[] monthWith31Days = {"Styczen", "Marzec", "Maj", "Lipiec", "Sierpien", "Październik", "Grudzien"};
        String[] monthWith30Days = {"Kwiecien", "Czerwiec", "Wrzesien", "Listopad"};
        for (int i = 0; i < monthWith31Days.length; i++) {
            if (month.equals(monthWith31Days[i])) {
                numberOfDays = 31;
                findNumberOfDays = true;
                break;
            }
        }
        for (int i = 0; i < monthWith30Days.length; i++) {
            if (findNumberOfDays) {
                break;
            }
            if (month.equals(monthWith30Days[i])) {
                numberOfDays = 30;
                findNumberOfDays = true;
                break;
            }
        }
        if (findNumberOfDays == false) {
            numberOfDays = 28;
        }
        return numberOfDays;
    }
}

package Main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyMouseListener implements MouseListener {
    public int xValue;
    public int yValue;
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println();
        if(e.getButton() == 1){
            System.out.println(xValue = e.getX());
            System.out.println(yValue = e.getY());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

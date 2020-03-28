import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyDrawing extends JPanel {

    private int X, Y, radius;

    public MyDrawing() {

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                X = e.getX();
                Y = e.getY();
                radius = (int) (100 * Math.random());

                repaint();
            }
        });

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.fillOval(X - radius / 2, Y - radius / 2, radius, radius);
    }

}

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

public class DrawingPanel extends JPanel {

    final MainFrame frame;
    final static int W = 800, H = 600;

    BufferedImage image;
    Graphics2D graphics;

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        createOffScreenImage();
        init();
    }

    private void createOffScreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0,0, W, H);
    }

    private void init() {
        setPreferredSize(new Dimension(W, H));
        setBorder(BorderFactory.createEtchedBorder());
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                drawShape(e.getX(), e.getY());
                repaint();
            }
        });
    }

    private void drawShape(int x, int y) {
        Random random = new Random();
        int radius = Math.abs(random.nextInt()) % W/2;
        int sides = frame.getConfigPanel().getSides();
        Color color = new Color(random.nextFloat(),
                                random.nextFloat(),
                                random.nextFloat(),
                                random.nextFloat());
        graphics.setColor(color);
        graphics.fill(new RegularPolygon(x, y, radius, sides));
    }

    @Override
    public void update(Graphics g) {

    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }

    public int getW() {
        return W;
    }

    public int getH() {
        return H;
    }
}

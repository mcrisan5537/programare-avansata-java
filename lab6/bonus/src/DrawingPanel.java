import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class DrawingPanel extends JPanel {

    private final MainFrame frame;
    final static int W = 800, H = 600;

    private boolean currentShapeIsLine = false;
    private Point lineP1, lineP2;

    // for retained mode, keep previous shapes in memory
    static class PairShapeColor {
        Shape shape;
        Color color;
        PairShapeColor(Shape shape, Color color) {
            this.shape = shape;
            this.color = color;
        }
    }
    private List<PairShapeColor> shapes;

    BufferedImage image;
    Graphics2D graphics;

    DrawingPanel(MainFrame frame) {
        this.frame = frame;
        shapes = new ArrayList<>();
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

            @Override
            public void mouseReleased(MouseEvent e) { // used for drawing lines
                if(currentShapeIsLine) {
                    drawShape(e.getX(), e.getY());
                    repaint();
                }
            }
        });
    }

    private void drawShape(int x, int y) {
        Random random = new Random();

        int radius = frame.getConfigPanel().getComponentSize();
        int sides = frame.getConfigPanel().getSides();
        Color color = frame.getConfigPanel().getColor();
        Shape shape = frame.getShapesPanel().getShape(x, y, radius, sides); // depending on chosen shape not all arguments matter
        if(shape instanceof Line2D)
            if(lineP1 == null) {
                lineP1 = new Point(x, y);
                return; // wait for mouseReleased, to get line end's coordinates
            } else if(lineP2 == null) {
                lineP2 = new Point(x, y);
                shape = new Line2D.Double(lineP1, lineP2);

                // draw line
                graphics.setColor(Color.BLACK); // all lines are black
                graphics.draw(shape);

                // draw all previous shapes, so the drawn line is not on top of the shape
                for(PairShapeColor pairShapeColor : shapes) {
                    graphics.setColor(pairShapeColor.color);
                    graphics.fill(pairShapeColor.shape);
                }

                shapes.add(new PairShapeColor(shape, Color.BLACK));
                lineP1 = null; lineP2 = null; // prepare for next line with two uninitialized points
                return;
            }

        shapes.add(new PairShapeColor(shape, color));
        graphics.setColor(color);
        graphics.fill(shape);
    }

    @Override
    public void update(Graphics g) {

    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }

    int getW() {
        return W;
    }

    int getH() {
        return H;
    }

    List<PairShapeColor> getShapes() {
        return shapes;
    }

    public void setCurrentShapeIsLine(Boolean currentShapeIsLine) {
        this.currentShapeIsLine = currentShapeIsLine;
    }
}

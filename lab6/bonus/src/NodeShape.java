import java.awt.geom.Ellipse2D;

class NodeShape extends Ellipse2D.Double {

    NodeShape(double x0, double y0, double radius) {
        super(x0 - radius / 2, y0 - radius / 2, radius, radius);
    }

}
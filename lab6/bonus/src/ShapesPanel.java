import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.Line2D;

class ShapesPanel extends JPanel {

    private final MainFrame frame;
    private JButton squareButton, nodeButton, regularPolygonButton, lineButton; // line button for drawing edges
    private ShapeEnum shapeEnum; // enum ShapeEnum { OTHER, SQUARE, NODE, REGULAR_POLYGON, EDGE; }

    ShapesPanel(MainFrame frame) {
        this.frame = frame;
        this.shapeEnum = ShapeEnum.OTHER; // default value
        setLayout(new GridLayout(1,3));
        init();
    }

    private void init() {

        setLayout(new BorderLayout());

        // one panel for Square, Node, Regular Polygon buttons
        JPanel shapes = new JPanel();
        squareButton = new JButton("Square");
        regularPolygonButton = new JButton("Regular Polygon");

        squareButton.addActionListener(this::buttonPress);
        regularPolygonButton.addActionListener(this::buttonPress);

        shapes.add(squareButton);
        shapes.add(regularPolygonButton);

        // one panel for Graph node, Graph edge buttons
        JPanel graph =  new JPanel();
        nodeButton = new JButton("Node");
        nodeButton.addActionListener(this::buttonPress);
        lineButton = new JButton("Edge");
        lineButton.addActionListener(this::buttonPress);

        graph.add(nodeButton);
        graph.add(lineButton);

        add(shapes, BorderLayout.NORTH);
        add(graph, BorderLayout.SOUTH);

    }

    private void buttonPress(ActionEvent e) {
        JButton source = (JButton) e.getSource();

        JSpinner sidesField = frame.getConfigPanel().getSidesField();
        frame.getDrawingPanel().setCurrentShapeIsLine(false);

        if (source.equals(squareButton)) { // clicked on Square
            sidesField.setValue((Integer)4); // set Sides value to 4
            shapeEnum = ShapeEnum.SQUARE;
        } else if (source.equals(nodeButton)) {
            sidesField.setValue((Integer)99999999); // set Sides value to 9999999
            shapeEnum = ShapeEnum.NODE;
        } else if (source.equals(regularPolygonButton)) {
            sidesField.setValue((Integer)6); // set Sides value to 6
            shapeEnum = ShapeEnum.REGULAR_POLYGON;
        } else if (source.equals(lineButton)) {
            frame.getDrawingPanel().setCurrentShapeIsLine(true);
            sidesField.setValue((Integer)2);
            shapeEnum = ShapeEnum.EDGE;
        }
    }

    Shape getShape(int x, int y, int radius, int sides) {
        Shape shape = null;
        switch(shapeEnum) {
            case OTHER:

            case REGULAR_POLYGON:
                shape = new RegularPolygon(x, y, radius, sides);
                break;

            case SQUARE:
                shape = new Rectangle(x - radius / 2, y - radius / 2, radius, radius);
                break;

            case NODE:
                shape = new NodeShape(x, y, radius);
                break;

            case EDGE:
                shape = new Line2D.Double();
                break;
        };
        return shape;
    }

    void setShapeEnum(ShapeEnum shapeEnum) {
        this.shapeEnum = shapeEnum;
    }
}

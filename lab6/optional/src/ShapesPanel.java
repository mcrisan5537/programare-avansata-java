import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

class ShapesPanel extends JPanel {

    private final MainFrame frame;
    private JButton squareButton, nodeButton, regularPolygonButton;
    private ShapeEnum shapeEnum;

    ShapesPanel(MainFrame frame) {
        this.frame = frame;
        this.shapeEnum = ShapeEnum.OTHER; // default value
        setLayout(new GridLayout(1,3));
        init();
    }

    private void init() {

        squareButton = new JButton("Square");
        nodeButton = new JButton("Node");
        regularPolygonButton = new JButton("Regular Polygon");

        squareButton.addActionListener(this::buttonPress);
        nodeButton.addActionListener(this::buttonPress);
        regularPolygonButton.addActionListener(this::buttonPress);

        add(squareButton);
        add(nodeButton);
        add(regularPolygonButton);
    }

    private void buttonPress(ActionEvent e) {
        JButton source = (JButton) e.getSource();

        JSpinner sidesField = frame.getConfigPanel().getSidesField();

        if (source.equals(squareButton)) { // clicked on Square
            sidesField.setValue((Integer)4); // set Sides value to 4
            shapeEnum = ShapeEnum.SQUARE;
        } else if (source.equals(nodeButton)) {
            sidesField.setValue((Integer)99999999); // set Sides value to 0
            shapeEnum = ShapeEnum.NODE;
        } else if (source.equals(regularPolygonButton)) {
            sidesField.setValue((Integer)6); // set Sides value to 6
            shapeEnum = ShapeEnum.REGULAR_POLYGON;
        }
    }

    Shape getShape(int x, int y, int radius, int sides) {
        Shape shape = null;
        switch(shapeEnum) {
            case OTHER:

            case REGULAR_POLYGON:
                return new RegularPolygon(x, y, radius, sides);

            case SQUARE:
                return new Rectangle(x - radius / 2, y - radius / 2, radius, radius);

            case NODE:
                return new NodeShape(x, y, radius);
        };
        return shape;
    }

    void setShapeEnum(ShapeEnum shapeEnum) {
        this.shapeEnum = shapeEnum;
    }
}

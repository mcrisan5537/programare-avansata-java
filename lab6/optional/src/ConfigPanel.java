import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class ConfigPanel extends JPanel {

    private final MainFrame frame;
    private JSpinner sidesField;
    private JComboBox<ColorEnum> colorCombo; // enum ColorEnum { RANDOM, RED, GREEN, BLUE; }
    private JComboBox<SizeEnum> sizeCombo; // enum SizeEnum { RANDOM, SMALL, MEDIUM, LARGE; }

    ConfigPanel(MainFrame frame) {
        this.frame = frame;
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        init();
    }

    private void init() {

        JLabel sidesLabel = new JLabel("Number of sides:");
        sidesField = new JSpinner(new SpinnerNumberModel(0, 0, 100 ,1));
        sidesField.setValue(6);
        sidesField.addChangeListener(e -> frame.getShapesPanel().setShapeEnum(ShapeEnum.OTHER));

        JLabel colorLabel = new JLabel("Color:");
        colorCombo = new JComboBox<>();
        colorCombo.addItem(ColorEnum.RANDOM);
        colorCombo.addItem(ColorEnum.RED);
        colorCombo.addItem(ColorEnum.GREEN);
        colorCombo.addItem(ColorEnum.BLUE);
        colorCombo.setSelectedIndex(0);

        JLabel sizeLabel = new JLabel("Size:");
        sizeCombo = new JComboBox<>();
        sizeCombo.addItem(SizeEnum.RANDOM);
        sizeCombo.addItem(SizeEnum.SMALL);
        sizeCombo.addItem(SizeEnum.MEDIUM);
        sizeCombo.addItem(SizeEnum.LARGE);
        sizeCombo.setSelectedIndex(0);

        add(Box.createHorizontalGlue());
        add(sidesLabel);
        add(sidesField);
        add(Box.createHorizontalGlue());
        add(colorLabel);
        add(colorCombo);
        add(Box.createHorizontalGlue());
        add(sizeLabel);
        add(sizeCombo);
        add(Box.createHorizontalGlue());

    }

    int getSides() {
        return (Integer)sidesField.getValue();
    }

    Color getColor() {
        Color color = new Color((float)Math.random(),
                                (float)Math.random(),
                                (float)Math.random(),
                                (float)Math.random());
        ColorEnum colorEnum = (ColorEnum)colorCombo.getSelectedItem();
        switch(colorEnum) {
            case RANDOM:
                break;

            case RED:
                color = Color.RED;
                break;

            case GREEN:
                color = Color.GREEN;
                break;

            case BLUE:
                color = Color.BLUE;
                break;
        }
        return color;
    }

    int getComponentSize() {
        SizeEnum sizeEnum = (SizeEnum)sizeCombo.getSelectedItem();
        int radius = Math.abs(new Random().nextInt(DrawingPanel.H / 4));
        switch(sizeEnum) {
            case RANDOM:
                break;

            case SMALL:
                radius = 50;
                break;

            case MEDIUM:
                radius = 100;
                break;

            case LARGE:
                radius = 150;
                break;
        }
        return radius;
    }

    JSpinner getSidesField() {
        return sidesField;
    }

    public JComboBox<ColorEnum> getColorCombo() {
        return colorCombo;
    }

    public JComboBox<SizeEnum> getSizeCombo() {
        return sizeCombo;
    }
}

import javax.swing.*;

public class ConfigPanel extends JPanel {

    final MainFrame frame;
    JLabel sidesLabel;
    JSpinner sidesField;
    JComboBox<String> colorCombo;

    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        sidesLabel = new JLabel("Number of sides:");
        sidesField = new JSpinner(new SpinnerNumberModel(0, 0, 100 ,1));
        sidesField.setValue(6);

        colorCombo = new JComboBox<>();
        colorCombo.addItem("Random");
        colorCombo.addItem("Black");
        colorCombo.setSelectedIndex(0);

        add(sidesLabel);
        add(sidesField);
        add(colorCombo);

    }

    public int getSides() {
        return (Integer)sidesField.getValue();
    }

}

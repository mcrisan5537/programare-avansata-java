import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.Method;

public class ControlPanel extends JPanel {

    private JLabel classLabel;
    private JTextField classField;

    private JLabel textLabel;
    private JTextField textField;

    private JButton addButton;

    public ControlPanel() {
        init();
    }

    private void init() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        classLabel = new JLabel("Class canonical name");
        add(classLabel);
        classField = new JTextField();
        classField.setColumns(20);
        add(classField);

        textLabel = new JLabel("Class label (if applicable)");
        add(textLabel);
        textField = new JTextField();
        textField.setColumns(20);
        add(textField);

        addButton = new JButton("Add component");
        addButton.addActionListener(this::addComponent);
        add(addButton);
//        setVisible(true);
    }

    private void addComponent(ActionEvent ae) {
        try {
            Class c = Class.forName(classField.getText());
            JComponent component = (JComponent) c.getConstructor().newInstance();
            try {
                Method setText = c.getMethod("setText", String.class);
                setText.invoke(component, textField.getText());
            } catch(Exception e) {}
            Main.mainframe.getDesignPanel().removeAll();
            Main.mainframe.getDesignPanel().add(component);

            Insets insets = Main.mainframe.getDesignPanel().getInsets();
            Dimension dimension = component.getPreferredSize();
            component.setBounds(insets.left, insets.top, dimension.width, dimension.height);

            Main.mainframe.getDesignPanel().revalidate();
            Main.mainframe.getDesignPanel().repaint();
        } catch(Exception e) {
            e.printStackTrace();
            System.err.println("failed loading component");
        }

    }

}

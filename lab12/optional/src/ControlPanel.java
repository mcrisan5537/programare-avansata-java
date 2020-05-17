import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ControlPanel extends JPanel {

    private String filename = "design_panel.xml";

    private JPanel topPanel;
    private JLabel classLabel;
    private JTextField classField;

    private JLabel textLabel;
    private JTextField textField;

    private JButton addButton;
    private JButton clearButton;


    private JPanel bottomPanel;
    private JButton saveButton;
    private JButton loadButton;

    private JLabel setTextLabel;
    private JTextField setTextField;

    private JLabel setWidthLabel;
    private JTextField setWidthField;

    private JLabel setHeightLabel;
    private JTextField setHeightField;

    private JButton setPropertiesButton;

    public ControlPanel() {
        init();
    }

    private void init() {
        setLayout(new BorderLayout());

        initTopPanel();
        add(topPanel, BorderLayout.NORTH);
        initBottomPanel();
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void initTopPanel() {
        topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 1));

        classLabel = new JLabel("Class canonical name");
        topPanel.add(classLabel);
        classField = new JTextField();
        classField.setColumns(20);
        topPanel.add(classField);

        textLabel = new JLabel("Class label (if applicable)");
        topPanel.add(textLabel);
        textField = new JTextField();
        textField.setColumns(20);
        topPanel.add(textField);

        addButton = new JButton("Add component");
        addButton.addActionListener(this::addComponent);
        topPanel.add(addButton);

        clearButton = new JButton("Clear design panel");
        clearButton.addActionListener(this::clearComponents);
        topPanel.add(clearButton);
    }

    private void initBottomPanel() {
        bottomPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 1));

        saveButton = new JButton("Save design panel");
        saveButton.addActionListener(this::save);
        bottomPanel.add(saveButton);

        loadButton = new JButton("Load design panel");
        loadButton.addActionListener(this::load);
        bottomPanel.add(loadButton);

        setTextLabel = new JLabel("Component text");
        bottomPanel.add(setTextLabel);
        setTextField = new JTextField();
        setTextField.setColumns(10);
        bottomPanel.add(setTextField);

        setWidthLabel = new JLabel("Component width");
        bottomPanel.add(setWidthLabel);
        setWidthField = new JTextField();
        setWidthField.setColumns(4);
        bottomPanel.add(setWidthField);

        setHeightLabel = new JLabel("Component height");
        bottomPanel.add(setHeightLabel);
        setHeightField = new JTextField();
        setHeightField.setColumns(4);
        bottomPanel.add(setHeightField);

        setPropertiesButton = new JButton("Set properties");
        setPropertiesButton.addActionListener(this::setProperties);
        bottomPanel.add(setPropertiesButton);
    }

    private void addComponent(ActionEvent ae) {
        try {
            Class c = Class.forName(classField.getText());
            JComponent component = (JComponent) c.getConstructor().newInstance();
            try {
                Method setText = c.getMethod("setText", String.class);
                setText.invoke(component, textField.getText());
            } catch(Exception e) {}
            Main.mainframe.getDesignPanel().addComponent(component);
        } catch(Exception e) {
            e.printStackTrace();
            System.err.println("failed loading component");
        }

    }

    private void clearComponents(ActionEvent ae) {
        Main.mainframe.getDesignPanel().clear();
    }

    private void save(ActionEvent ae) {
        try {
            XMLEncoder xmlEncoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(filename)));
            xmlEncoder.writeObject(Main.mainframe.getDesignPanel());
            xmlEncoder.flush();
            xmlEncoder.close();
        } catch(FileNotFoundException e) {
            System.err.println("error creating XMLEncoder");
            e.printStackTrace();
        }
    }

    private void load(ActionEvent ae) {
        try {
            XMLDecoder xmlDecoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(filename)));
            DesignPanel designPanel = (DesignPanel) xmlDecoder.readObject();
            java.util.List<JComponent> components = designPanel.getComponentList();
            Main.mainframe.getDesignPanel().setComponentList(components);
            xmlDecoder.close();
        } catch(FileNotFoundException e) {
            System.err.println("error creating xmlDecoder");
            e.printStackTrace();
        }
    }

    private void setProperties(ActionEvent ae) {
        JComponent component = (JComponent) Main.mainframe.getDesignPanel().getLastFocusedComponent();

        String newText = setTextField.getText();
        int newWidth = Integer.parseInt(setWidthField.getText());
        int newHeight = Integer.parseInt(setHeightField.getText());

        try {
            Method method = component.getClass().getMethod("setText", String.class);
            method.invoke(component, newText);
        } catch(NoSuchMethodException | IllegalAccessException | InvocationTargetException ignored) {}

        int indexInComponentsList = Main.mainframe.getDesignPanel().getComponentList().indexOf(component);
        Main.mainframe.getDesignPanel().getxOffsetList().set(indexInComponentsList, newWidth);
        component.setSize(newWidth, newHeight);
    }

}

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private ControlPanel controlPanel;
    private DesignPanel designPanel;
    private PropertiesPanel propertiesPanel;

    public MainFrame() {
        init();
    }

    public void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        controlPanel = new ControlPanel();
        add(controlPanel, BorderLayout.NORTH);
        designPanel = new DesignPanel();
        add(designPanel, BorderLayout.CENTER);
        propertiesPanel = new PropertiesPanel();
        add(propertiesPanel, BorderLayout.SOUTH);

        pack();
    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }

    public void setControlPanel(ControlPanel controlPanel) {
        this.controlPanel = controlPanel;
    }

    public DesignPanel getDesignPanel() {
        return designPanel;
    }

    public void setDesignPanel(DesignPanel designPanel) {
        this.designPanel = designPanel;
    }

    public PropertiesPanel getPropertiesPanel() {
        return propertiesPanel;
    }

    public void setPropertiesPanel(PropertiesPanel propertiesPanel) {
        this.propertiesPanel = propertiesPanel;
    }
}

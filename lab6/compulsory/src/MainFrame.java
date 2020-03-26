import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    ConfigPanel configPanel;
    DrawingPanel drawingPanel;
    ControlPanel controlPanel;

    public MainFrame() {
        super("My drawing app.");
        init();
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        configPanel = new ConfigPanel(this);
        drawingPanel = new DrawingPanel(this);
        controlPanel = new ControlPanel(this);

        add(configPanel, BorderLayout.NORTH);
        add(drawingPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        pack();
    }

    public ConfigPanel getConfigPanel() {
        return this.configPanel;
    }

    public DrawingPanel getDrawingPanel() {
        return drawingPanel;
    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }
}

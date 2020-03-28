import javax.swing.*;
import java.awt.*;

class MainFrame extends JFrame {

    ConfigPanel configPanel;
    ShapesPanel shapesPanel;
    DrawingPanel drawingPanel;
    ControlPanel controlPanel;

    MainFrame() {
        super("My drawing app.");
        init();
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        configPanel = new ConfigPanel(this);
        shapesPanel = new ShapesPanel(this);
        drawingPanel = new DrawingPanel(this);
        controlPanel = new ControlPanel(this);

        // new JPanel that consists of ConfigPanel, ShapesPanel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(configPanel, BorderLayout.NORTH);
        topPanel.add(shapesPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);
        add(drawingPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        pack();
        setResizable(false);
    }

    ConfigPanel getConfigPanel() {
        return this.configPanel;
    }

    ShapesPanel getShapesPanel() {
        return shapesPanel;
    }

    DrawingPanel getDrawingPanel() {
        return drawingPanel;
    }

}

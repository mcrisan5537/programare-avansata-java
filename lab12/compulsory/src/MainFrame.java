import javax.swing.*;
import java.awt.*;
import java.util.zip.DeflaterInputStream;

public class MainFrame extends JFrame {

    private ControlPanel controlPanel;
    private DesignPanel designPanel;

    public MainFrame() {
        init();
    }

//    public MainFrame(ControlPanel controlPanel, DesignPanel designPanel) {
//        this.controlPanel = controlPanel;
//        this.designPanel = designPanel;
//    }

    public void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        controlPanel = new ControlPanel();
        add(controlPanel, BorderLayout.NORTH);
        designPanel = new DesignPanel();
        add(designPanel, BorderLayout.CENTER);

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
}

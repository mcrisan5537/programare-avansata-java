import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileOutputStream;
import java.io.IOException;

public class ControlPanel extends JPanel {

    final MainFrame frame;
    JButton loadButton, saveButton, resetButton, exitButton;

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        loadButton = new JButton("Load");
        saveButton = new JButton("Save");
        resetButton = new JButton("Reset");
        exitButton = new JButton("Exit");
        init();
    }

    private void init() {
        setLayout(new GridLayout(1,4));

        add(loadButton);
        add(saveButton);
        add(resetButton);
        add(exitButton);

        loadButton.addActionListener(this::load);
        saveButton.addActionListener(this::save);
        resetButton.addActionListener(this::reset);
        exitButton.addActionListener(this::exit);

    }

    private void load(ActionEvent actionEvent) {
        // todo
    }

    private void save(ActionEvent actionEvent) {
        try {
            ImageIO.write(frame.drawingPanel.image, "PNG", new FileOutputStream("test.png"));
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void reset(ActionEvent actionEvent) {
        DrawingPanel drawingPanel = frame.getDrawingPanel();
        drawingPanel.graphics.setColor(new Color(255, 255, 255));
        drawingPanel.graphics.fill(new Rectangle(drawingPanel.getW(), drawingPanel.getH()));
        drawingPanel.repaint();
    }

    private void exit(ActionEvent actionEvent) {
        System.exit(1);
    }

}

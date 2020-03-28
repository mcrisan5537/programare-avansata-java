import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

class ControlPanel extends JPanel {

    private final MainFrame frame;
    private JButton loadButton, saveButton, resetButton, exitButton, undoButton;
    private JFileChooser fileChooser;

    ControlPanel(MainFrame frame) {
        this.frame = frame;
        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        loadButton = new JButton("Load");
        saveButton = new JButton("Save");
        resetButton = new JButton("Reset");
        exitButton = new JButton("Exit");
        undoButton = new JButton("Undo");
        init();
    }

    private void init() {
        setLayout(new GridLayout(1,5));

        loadButton.addActionListener(this::load);
        saveButton.addActionListener(this::save);
        resetButton.addActionListener(this::reset);
        exitButton.addActionListener(this::exit);
        undoButton.addActionListener(this::undo);

        add(loadButton);
        add(saveButton);
        add(resetButton);
        add(exitButton);
        add(undoButton);
    }

    private void load(ActionEvent actionEvent) {
        int returnVal = fileChooser.showOpenDialog(this);

        if(returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                BufferedImage image = ImageIO.read(file);
                frame.getDrawingPanel().image = image;
                frame.getDrawingPanel().graphics = image.createGraphics();
                frame.getDrawingPanel().repaint();
            } catch (IOException e) {
                System.out.println(e);
                e.printStackTrace();
            }
        }

    }

    private void save(ActionEvent actionEvent) {
        try {
            ImageIO.write(frame.drawingPanel.image, "PNG", new FileOutputStream("test.png"));
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    private void reset(ActionEvent actionEvent) {
        DrawingPanel drawingPanel = frame.getDrawingPanel();
        drawingPanel.graphics.setColor(Color.WHITE);
        drawingPanel.graphics.fill(new Rectangle(drawingPanel.getW(), drawingPanel.getH()));
        drawingPanel.repaint();
    }

    private void exit(ActionEvent actionEvent) {
        System.exit(1);
    }

    private void undo(ActionEvent actionEvent) {
        DrawingPanel drawingPanel = frame.getDrawingPanel();

        if(!drawingPanel.getShapes().isEmpty()) { // if there are more than 0 shapes drawn
            // then create new image & graphics
            // and redraw every shape except the most recent one
            drawingPanel.getShapes().remove(frame.getDrawingPanel().getShapes().size() - 1);
            drawingPanel.image = new BufferedImage(drawingPanel.getW(), drawingPanel.getH(), BufferedImage.TYPE_INT_ARGB);
            drawingPanel.graphics = drawingPanel.image.createGraphics();
            drawingPanel.graphics.setColor(Color.WHITE);
            drawingPanel.graphics.fillRect(0, 0, drawingPanel.getW(), drawingPanel.getH());
            for (DrawingPanel.PairShapeColor pair : drawingPanel.getShapes()) {
                drawingPanel.graphics.setColor(pair.color);
                drawingPanel.graphics.fill(pair.shape);
            }
            drawingPanel.repaint();
        }
    }

}

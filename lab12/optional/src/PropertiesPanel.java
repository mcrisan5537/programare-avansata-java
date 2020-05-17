import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;

public class PropertiesPanel extends JPanel {

    private JTable propertiesTable;

    public PropertiesPanel() {
        init();
    }

    private void init() {

        setLayout(new FlowLayout());
        propertiesTable = new JTable(2, 3);
        TableColumn column = null;
        for(int i = 0; i < 3; i++) {
            column = propertiesTable.getColumnModel().getColumn(i);
            column.setPreferredWidth(200);
        }

        // component name
        propertiesTable.setValueAt("Component name", 0, 0);
        // has text
        propertiesTable.setValueAt("Has text", 0, 1);
        // has action listener
        propertiesTable.setValueAt("Has action listener", 0, 2);

        add(propertiesTable);
    }

    public void setName(String string) {
        propertiesTable.setValueAt(string, 1, 0);
    }

    public void setHasText(String string) {
        propertiesTable.setValueAt(string, 1, 1);
    }

    public void setHasActionListener(String string) {
        propertiesTable.setValueAt(string, 1, 2);
    }

}

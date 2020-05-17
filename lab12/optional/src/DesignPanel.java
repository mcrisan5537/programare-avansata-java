import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DesignPanel extends JPanel implements Serializable, MouseListener {

    private java.util.List<JComponent> componentList = new ArrayList<>();
    private java.util.List<Integer> xOffsetList = new ArrayList<>();
    private Component lastFocusedComponent;

    public DesignPanel() {
        init();
    }

    public void init() {
        setLayout(null);
        setPreferredSize(new Dimension(480, 640));
    }

    public synchronized void addComponent(JComponent component) {
        component.addMouseListener(this);

        int xOffset = 0;
        for(Integer xoffset : xOffsetList)
            xOffset += xoffset;

        boolean hasSizeChanged = component.getSize().getWidth() != 0;
        if(hasSizeChanged)
            component.setBounds(xOffset + 1, 0, component.getSize().width, component.getSize().height);
        else
            component.setBounds(xOffset + 1, 0, component.getPreferredSize().width, component.getPreferredSize().height);

        this.add(component);
        this.revalidate();
        this.repaint();

        this.componentList.add(component);
        this.xOffsetList.add(component.getSize().width);
    }

    public synchronized void clear() {
        this.componentList.clear();
        this.xOffsetList.clear();
        this.removeAll();
        this.revalidate();
        this.repaint();
    }

    public synchronized List<JComponent> getComponentList() {
        return componentList;
    }

    public synchronized void setComponentList(List<JComponent> componentList) {
        this.clear();
        for(JComponent component : componentList)
            this.addComponent(component);
    }

    public List<Integer> getxOffsetList() {
        return xOffsetList;
    }

    public void setxOffsetList(List<Integer> xOffsetList) {
        this.xOffsetList = xOffsetList;
    }

    public Component getLastFocusedComponent() {
        return lastFocusedComponent;
    }

    public void setLastFocusedComponent(Component lastFocusedComponent) {
        this.lastFocusedComponent = lastFocusedComponent;
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        this.lastFocusedComponent = me.getComponent();

        String className = "";
        String hasText = "false";
        String hasActionListener = "false";

        try {
            BeanInfo info = Introspector.getBeanInfo(me.getComponent().getClass());
            className = info.getBeanDescriptor().getBeanClass().getSimpleName();
            for(MethodDescriptor methodDescriptor : info.getMethodDescriptors()) {
                if("setText".equals(methodDescriptor.getName()))
                    hasText = "true";
                if("addActionListener".equals(methodDescriptor.getName()))
                    hasActionListener = "true";
            }
        } catch(IntrospectionException e) {}

        Main.mainframe.getPropertiesPanel().setName(className);
        Main.mainframe.getPropertiesPanel().setHasText(hasText);
        Main.mainframe.getPropertiesPanel().setHasActionListener(hasActionListener);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Main.mainframe.getPropertiesPanel().setName("");
        Main.mainframe.getPropertiesPanel().setHasText("");
        Main.mainframe.getPropertiesPanel().setHasActionListener("");
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }
}

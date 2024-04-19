package Frontend;

import javax.swing.*;
import java.awt.*;

public class InterfaceManager {
    private static final String TITLE = "JavaCool";
    private final JFrame mainFrame;

    public InterfaceManager() {
        mainFrame = new JFrame(TITLE);
        initializeMainFrame();
    }

    private void initializeMainFrame() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        mainFrame.setSize(screenSize);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());
    }

    public void display() {
        SwingUtilities.invokeLater(() -> {
            mainFrame.setVisible(true);
        });
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }

    public static void main(String[] args) {
        InterfaceManager interfaceManager = new InterfaceManager();
        interfaceManager.display();
        interfaceManager.setupComponents();
    }

    private void setupComponents() {
        ToolbarManager.setupToolbar(mainFrame);
        PanelManager.setupPanels(mainFrame);
    }
}
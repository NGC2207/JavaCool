package Frontend;

import javax.swing.*;
import java.awt.*;

public class ToolbarManager {
    private static final String[] TOOL_BUTTONS = {"Refresh", "Clear", "Save"};
    private static final String[] CODE_BUTTONS = {"Compile", "Run","Submit"};

    public static void setupToolbar(JFrame mainFrame) {
        JToolBar toolBar = new JToolBar();
        Font font = new Font("Arial", Font.PLAIN, 32);
        for (String button : TOOL_BUTTONS) {
            JButton jButton = new JButton(button);
            jButton.setFont(font);
            jButton.setForeground(Color.BLUE);
            toolBar.add(jButton);
        }
        toolBar.add(Box.createHorizontalGlue());

        for (String button : CODE_BUTTONS) {
            JButton jButton = new JButton(button);
            jButton.setFont(font);
            jButton.setForeground(Color.BLUE);
            toolBar.add(jButton);
        }
        mainFrame.add(toolBar, BorderLayout.NORTH);
    }
}
package Frontend;

import javax.swing.*;
import java.awt.*;

public class ToolbarManager {
    private static final String[] TOOL_BUTTONS = {"refresh", "clear", "save"};
    private static final String[] CODE_BUTTONS = {"compile", "run"};

    public static void setupToolbar(JFrame mainFrame) {
        JToolBar toolBar = new JToolBar();
        Font font = new Font("Arial", Font.PLAIN, 32);
        for (String button : TOOL_BUTTONS) {
            JButton jButton = new JButton(button);
            jButton.setFont(font);
            jButton.setForeground(Color.BLUE);
            toolBar.add(jButton);
        }
        /// 添加占位符，使得新按钮位于最右侧
        toolBar.add(Box.createHorizontalGlue());

        // 添加新按钮
        for (String button : CODE_BUTTONS) {
            JButton jButton = new JButton(button);
            jButton.setFont(font);
            jButton.setForeground(Color.BLUE);
            toolBar.add(jButton);
        }
        mainFrame.add(toolBar, BorderLayout.NORTH);
    }
}
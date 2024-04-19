package Frontend;

import javax.swing.*;
import java.awt.*;

public class PanelManager {
    public static void setupPanels(JFrame mainFrame) {
        JPanel detailsPanel = createDetailsPanel();
        JSplitPane editorPanel = createEditorPanel(); // 修改此处，将 editorPanel 设置为 JSplitPane

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, detailsPanel, editorPanel);
        splitPane.setDividerLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 8 * 3);
        mainFrame.add(splitPane, BorderLayout.CENTER);
    }

    private static JPanel createDetailsPanel() {
        JPanel panel = new JPanel();
        JTabbedPane detailsTabbedPane = new JTabbedPane();
        detailsTabbedPane.addTab("question", new JPanel());
        detailsTabbedPane.addTab("details", new JPanel());
        panel.setLayout(new BorderLayout());
        panel.add(detailsTabbedPane, BorderLayout.CENTER);
        return panel;
    }

    private static JSplitPane createEditorPanel() {
        JPanel panel = new JPanel();
        JTextArea editorTextArea = new JTextArea();
        editorTextArea.setLineWrap(true);
        editorTextArea.setWrapStyleWord(true);
        editorTextArea.setFont(new Font("Consolas", Font.PLAIN, 32));
        JTabbedPane editorTabbedPane = new JTabbedPane();
        editorTabbedPane.addTab("compile", new JPanel()); // 添加一个空面板示例
        editorTabbedPane.addTab("run", new JPanel()); // 添加一个空面板示例

        // 创建一个新的 JSplitPane，垂直分割
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JScrollPane(editorTextArea), editorTabbedPane);

        // 设置分隔条位置为屏幕宽度的 2/3 处
        int dividerLocation = Toolkit.getDefaultToolkit().getScreenSize().width / 3 ;
        splitPane.setDividerLocation(dividerLocation);

        return splitPane; // 返回嵌套的 JSplitPane
    }
}
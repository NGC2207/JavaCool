package Frontend;

import util.backend.BEHelper;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class PanelManager {
    private static final int SCREEN_WIDTH_DIVISOR = 8;
    private static final String[] DETAILS_TABS = {"Question", "Details"};
    private static final String[] EDITOR_TABS = {"Output"};
    private static JList<String> problemList;
    private static JScrollPane problemListScrollPane;
    private static JTextArea editorTextArea;
    private static JTextArea compileTextArea;
    private static int indexOfQuestion;

    public static int getIndexOfQuestion(){
        return indexOfQuestion;
    }

    public static JTextArea getCompileTextArea(){
        return compileTextArea;
    }

    public static JTextArea getEditorTextArea(){
        return editorTextArea;
    }

    public static void setupPanels(JFrame mainFrame) {
        JPanel detailsPanel = createDetailsPanel();
        JSplitPane editorPanel = createEditorPanel();

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, detailsPanel, editorPanel);
        splitPane.setDividerLocation(Toolkit.getDefaultToolkit().getScreenSize().width / SCREEN_WIDTH_DIVISOR+50);
        mainFrame.add(splitPane, BorderLayout.CENTER);

        try {
            resetProblemList();
            JTabbedPane detailsTabbedPane = (JTabbedPane) detailsPanel.getComponent(0);
            detailsTabbedPane.setComponentAt(0, problemListScrollPane);
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    private static JPanel createDetailsPanel() {
        JPanel panel = new JPanel();
        JTabbedPane detailsTabbedPane = new JTabbedPane();
        for (String tab : DETAILS_TABS) {
            detailsTabbedPane.addTab(tab, new JPanel());
        }
        panel.setLayout(new BorderLayout());
        panel.add(detailsTabbedPane, BorderLayout.CENTER);

        detailsTabbedPane.addChangeListener(e -> {
            JTabbedPane sourceTabbedPane = (JTabbedPane) e.getSource();
            int selectedIndex = sourceTabbedPane.getSelectedIndex();
            if (selectedIndex == 0) {
                try {
                    resetProblemList();
                    detailsTabbedPane.setComponentAt(0, problemListScrollPane);
                } catch (IOException ex) {
                    handleIOException(ex);
                }
            }
        });

        return panel;
    }

    private static JSplitPane createEditorPanel() {
        JPanel panel = new JPanel();
        editorTextArea = new JTextArea();
        editorTextArea.setLineWrap(true);
        editorTextArea.setWrapStyleWord(true);
        editorTextArea.setFont(new Font("Consolas", Font.PLAIN, 32));
        JTabbedPane editorTabbedPane = new JTabbedPane();
        for (String tab : EDITOR_TABS) {

            compileTextArea = new JTextArea();
            compileTextArea.setEditable(false); // 设置为不可编辑
            compileTextArea.setFont(new Font("Consolas", Font.PLAIN, 32)); // 设置字体大小为32
            JScrollPane compileScrollPane = new JScrollPane(compileTextArea);
            editorTabbedPane.addTab(tab, compileScrollPane);
        }

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JScrollPane(editorTextArea), editorTabbedPane);
        splitPane.setDividerLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 3);

        return splitPane;
    }

    private static void resetProblemList() throws IOException {
        problemList = new JList<>(BEHelper.getNameOfAllProblem());
        problemListScrollPane = new JScrollPane(problemList);

        problemList.setCellRenderer(new ProblemListCellRenderer());

        problemList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTabbedPane detailsTabbedPane = (JTabbedPane) SwingUtilities.getAncestorOfClass(JTabbedPane.class, problemListScrollPane);
                    if (detailsTabbedPane != null) {
                        detailsTabbedPane.setSelectedIndex(1);
                    }
                    indexOfQuestion = problemList.locationToIndex(e.getPoint());
                    try {
                        // 创建一个不可编辑的 JTextArea，自动换行
                        JTextArea textArea = new JTextArea(BEHelper.getInnerProblem(indexOfQuestion + 1).toString());
                        textArea.setFont(new Font("Consolas", Font.PLAIN, 32));
                        textArea.setEditable(false);
                        textArea.setLineWrap(true); // 启用自动换行
                        textArea.setWrapStyleWord(true); // 根据单词换行
                        JScrollPane scrollPane = new JScrollPane(textArea); // 使用 JTextArea 创建 JScrollPane
                        detailsTabbedPane.setComponentAt(1, scrollPane);
                    } catch (IOException ex) {
                        handleIOException(ex);
                    }
                }
            }
        });
    }

    private static class ProblemListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            label.setFont(new Font("Consolas", Font.PLAIN, 32));
            label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            label.setPreferredSize(new Dimension(0, label.getPreferredSize().height)); // 设置单元格宽度自适应
            label.setHorizontalAlignment(SwingConstants.LEFT); // 左对齐
            label.setVerticalAlignment(SwingConstants.TOP); // 顶部对齐
            label.setOpaque(true);
            label.setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
            label.setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());
            label.setText("<html><body style='width: 300px'>" + value + "</body></html>"); // 设置HTML以支持自动换行
            return label;
        }
    }

    private static void handleIOException(IOException ex) {
        ex.printStackTrace();
    }
}
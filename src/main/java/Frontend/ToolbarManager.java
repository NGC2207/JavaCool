package Frontend;

import util.backend.BEHelper;
import util.database.DBHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ToolbarManager {
    private static final String[] TOOL_BUTTONS = {"Clear"};
    private static final String[] CODE_BUTTONS = {"Compile", "Run", "Submit"};

    public static void setupToolbar(JFrame mainFrame) {
        JToolBar toolBar = new JToolBar();
        Font font = new Font("Arial", Font.PLAIN, 32);
        for (String button : TOOL_BUTTONS) {
            JButton jButton = new JButton(button);
            jButton.setFont(font);
            jButton.setForeground(Color.BLUE);
            toolBar.add(jButton);
            // 添加点击事件监听器
            jButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (button.equals("Clear")) {
                        // 清空editorTextArea的内容
                        PanelManager.getEditorTextArea().setText("");
                    }
                }
            });
        }
        toolBar.add(Box.createHorizontalGlue());

        for (String button : CODE_BUTTONS) {
            JButton jButton = new JButton(button);
            jButton.setFont(font);
            jButton.setForeground(Color.BLUE);
            toolBar.add(jButton);
            jButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    if (button.equals("Compile")) {
                        String code = PanelManager.getEditorTextArea().getText();
                        PanelManager.getCompileTextArea().setText("Compiling...\n");
                        int indexOfNumber = PanelManager.getIndexOfQuestion() + 1;
                        String compileResult = BEHelper.compile(indexOfNumber, code);
                        if (compileResult.isEmpty()) {
                            PanelManager.getCompileTextArea().setText("Compile successfully!\n");
                        } else {
                            PanelManager.getCompileTextArea().setText(compileResult);
                        }
                    } else if (button.equals("Run")) {
                        String code = PanelManager.getEditorTextArea().getText();
                        PanelManager.getCompileTextArea().setText("Compiling...\n");
                        int indexOfNumber = PanelManager.getIndexOfQuestion() + 1;
                        String compileResult = BEHelper.compile(indexOfNumber, code);
                        if (compileResult.isEmpty()) {
                            PanelManager.getCompileTextArea().setText("Running...\n");
                            String runResult = BEHelper.run(indexOfNumber);
                            PanelManager.getCompileTextArea().setText(runResult);
                        } else {
                            PanelManager.getCompileTextArea().setText("Compile failed\n");
                        }
                    } else if (button.equals("Submit")) {
                        String code = PanelManager.getEditorTextArea().getText();
                        String runResult="";
                        PanelManager.getCompileTextArea().setText("Compiling...\n");
                        int indexOfNumber = PanelManager.getIndexOfQuestion() + 1;
                        String compileResult = BEHelper.compile(indexOfNumber, code);
                        if (compileResult.isEmpty()) {
                            PanelManager.getCompileTextArea().setText("Running...\n");
                            runResult = BEHelper.run(indexOfNumber);
                            PanelManager.getCompileTextArea().setText(runResult);
                        } else {
                            PanelManager.getCompileTextArea().setText("Compile failed\n");
                        }
                        String answer = null;
                        boolean flag=false;
                        int numOfExpectedAnswer= 0;
                        try {
                            numOfExpectedAnswer = BEHelper.getProblem(indexOfNumber).getExpectedAnswer().length;
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        for(int i=0;i<numOfExpectedAnswer;i++){
                            try {
                                answer=BEHelper.getProblem(indexOfNumber).getExpectedAnswer()[i];
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            if(runResult.equals(answer)){
                                PanelManager.getCompileTextArea().setText("Accepted\n");
                                flag=true;
                            }
                        }
                        if(flag==false){
                            PanelManager.getCompileTextArea().setText("Wrong Answer\n"+"Your answer is: "+runResult+"\n"+"Expected answer is: "+answer);
                        }
                    }
                }
            });
        }
        mainFrame.add(toolBar, BorderLayout.NORTH);
    }
}
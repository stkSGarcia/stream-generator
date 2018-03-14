package io.transwarp.streamgui;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.Enumeration;

/**
 * Author: stk
 * Date: 2018/3/13
 * <p>
 * Entrance frame.
 */
public class MainFrame extends JFrame {
    private JSplitPane splitPane;
    private JPanel panel;

    public MainFrame() {
        double scale_x = 0.5;
        double scale_y = 0.8;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screen.width * scale_x);
        int height = (int) (screen.height * scale_y);
        setPreferredSize(new Dimension(width, height));
        setLocation((int) (screen.width * (1 - scale_x) / 2), (int) (screen.height * (1 - scale_y) / 2));
        setTitle("Stream Generator v0.1.0");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initMenu();
        initPanel();
        initSplitPane();

        pack();
        setVisible(true);
        splitPane.setDividerLocation(0.6);
    }

    public static void main(String[] args) {
        /*
        Set global font.
         */
        FontUIResource fontUIResource = new FontUIResource(new Font("宋体", Font.PLAIN, 14));
        for (Enumeration keys = UIManager.getDefaults().keys(); keys.hasMoreElements(); ) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, fontUIResource);
            }
        }
        new MainFrame();
    }

    private void initMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu1 = new JMenu("文件");
        JMenu menu2 = new JMenu("编辑");

        JMenuItem item1_1 = new JMenuItem("退出");

        JMenuItem item2_1 = new JMenuItem("连接");
        JMenuItem item2_2 = new JMenuItem("自定义模板");

        menu1.add(item1_1);
        menu2.add(item2_1);
        menu2.add(item2_2);

        menuBar.add(menu1);
        menuBar.add(menu2);

        setJMenuBar(menuBar);

        item1_1.addActionListener(e -> System.exit(0));
    }

    private void initPanel() {
        panel = new SenderPanel();
    }

    /**
     * Initiate SplitPane and TextArea (used for output).
     */
    private void initSplitPane() {
        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true);
        splitPane.setOneTouchExpandable(true);
        splitPane.setLeftComponent(panel);
        ConsolePane consolePane = ConsolePane.getInstance();
        splitPane.setRightComponent(consolePane);
        setContentPane(splitPane);
    }
}

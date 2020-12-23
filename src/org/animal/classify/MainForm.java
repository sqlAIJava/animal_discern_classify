package org.animal.classify;

import org.animal.classify.service.impl.DiscernServiceImpl;

import javax.swing.*;
import java.awt.*;

public class MainForm {
    private static final int windowWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    private static final int windowHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    private static final int width = 900;
    private static final int height = 600;

    private static DiscernServiceImpl discernService = new DiscernServiceImpl();

    private JPanel root;

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainForm");
        frame.setContentPane(new MainForm().root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("动物分类识别系统");
        frame.setBounds((windowWidth - width)/2, (windowHeight - height)/2, width, height);
        frame.setVisible(true);
        discernService = new DiscernServiceImpl();
    }
}

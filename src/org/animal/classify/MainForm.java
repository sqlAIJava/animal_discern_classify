package org.animal.classify;

import org.animal.classify.entity.ForwardResult;
import org.animal.classify.entity.ReverseResult;
import org.animal.classify.entity.RulesEnum;
import org.animal.classify.service.impl.DiscernServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class MainForm{
    private static final int windowWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    private static final int windowHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    private static final int width = 900;
    private static final int height = 600;

    private static DiscernServiceImpl discernService = new DiscernServiceImpl();


    private JPanel root;
    private JButton button1;
    private JTextPane textPane1;
    private JTextPane textPane2;

    public MainForm() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textPane2.setText("");
                discernService = new DiscernServiceImpl();
                String requestText = textPane1.getText();
                if(requestText.equals("")){
                    JOptionPane.showMessageDialog(null, "请输入");
                    return;
                }
                List<String> request = Arrays.asList(requestText.split("\r\n"));
                request = request.stream().filter(i->!i.equals("")).collect(Collectors.toList());
                // 判断是 正向 还是 逆向
                if(request.size() > 1){
                    // TODO 正向
                    System.out.println("正向");
                    forward(request);
                }else if(request.size() == 1) {
                    // 判断是 正向 还是 逆向
                    if(RulesEnum.checkForWardOrReverse(request.get(0))){
                        // TODO 正向
                        System.out.println("正向");
                        forward(request);
                    }else {
                        // TODO 逆向
                        System.out.println("逆向");
                        reverse(request.get(0));
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "请输入");
                }
            }
        });
        textPane1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                textPane2.setText("");
            }
        });
    }

    // 正向输出
    public void forward(List<String> request){
        ForwardResult forwardResult = discernService.discernByForward(request, i -> {
            System.out.println(i.toString());
            String ret = "正向依次识别中：" + "规则="+ i.getRules() + "   结果="+i.getResult()+"   匹配率="+i.getProbability();
            if(textPane2.getText().equals(""))
                textPane2.setText(ret);
            else
                textPane2.setText(textPane2.getText()+"\n"+ret);
            return i;
        });
        System.out.println("===最合适的答案===" + forwardResult.toString());
        textPane2.setText(textPane2.getText()+"\n"+"===最合适的答案===");
        textPane2.setText(textPane2.getText()+"\n"+ "结果="+forwardResult.getResult()+"   匹配率="+forwardResult.getProbability());
    }

    // 逆向输出
    public void reverse(String request){
        ReverseResult reverseResult = discernService.discernByReverse(request, i -> {
            System.out.println("【" + i.get("vertex") + "】递归" + "找到规则" + i.get("rule"));

            System.out.println(i.toString());
            if(textPane2.getText().equals(""))
                textPane2.setText("【" + i.get("vertex") + "】递归" + "找到规则" + i.get("rule"));
            else
                textPane2.setText(textPane2.getText()+"\n"+"【" + i.get("vertex") + "】递归" + "找到规则" + i.get("rule"));

            return (String) i.get("vertex");
        });
        System.out.println("===最终的答案===" + reverseResult.getRules());
        textPane2.setText(textPane2.getText()+"\n"+"===最合适的答案===");
        textPane2.setText(textPane2.getText()+"\n"+reverseResult.getRules());
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainForm");
        frame.setContentPane(new MainForm().root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("动物分类识别系统");
        frame.setBounds((windowWidth - width)/2, (windowHeight - height)/2, width, height);
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}

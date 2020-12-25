package org.animal.classify;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import org.animal.classify.entity.ForwardResult;
import org.animal.classify.entity.ReverseResult;
import org.animal.classify.entity.RulesEnum;
import org.animal.classify.service.impl.DiscernServiceImpl;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainFormEclipse {
	
    private static final int windowWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    private static final int windowHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    private static final int width = 900;
    private static final int height = 600;

    private static DiscernServiceImpl discernService = new DiscernServiceImpl();

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFormEclipse window = new MainFormEclipse();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFormEclipse() {
		initialize();
	}
	JTextPane textPane = new JTextPane();
	JTextPane textPane_1 = new JTextPane();
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("动物分类识别系统");
		frame.setBounds((windowWidth - width)/2, (windowHeight - height)/2, width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		
		JButton btnNewButton = new JButton("识别");
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 794, GroupLayout.PREFERRED_SIZE)
							.addGap(10))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 794, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNewButton)
					.addGap(26))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(256)
					.addComponent(btnNewButton)
					.addContainerGap(282, Short.MAX_VALUE))
		);
		
		JLabel lblNewLabel_1 = new JLabel("以下为识别结果：");
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
						.addComponent(textPane_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textPane_1, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblNewLabel = new JLabel("请输入规则或结论（单个换行）：");
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(textPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		textPane.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				textPane_1.setText("");
			}
		});
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textPane, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE))
		);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				textPane_1.setText("");
                discernService = new DiscernServiceImpl();
                String requestText = textPane.getText();
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
		
		panel.setLayout(gl_panel);
		frame.getContentPane().setLayout(groupLayout);
	}


	// 正向输出
    public void forward(List<String> request){
        ForwardResult forwardResult = discernService.discernByForward(request, i -> {
            System.out.println(i.toString());
            String ret = "正向依次识别中：" + "规则="+ i.getRules() + "   结果="+i.getResult()+"   匹配率="+i.getProbability();
            if(textPane_1.getText().equals(""))
            	textPane_1.setText(ret);
            else
            	textPane_1.setText(textPane_1.getText()+"\n"+ret);
            return i;
        });
        System.out.println("===最合适的答案===" + forwardResult.toString());
        textPane_1.setText(textPane_1.getText()+"\n"+"===最合适的答案===");
        textPane_1.setText(textPane_1.getText()+"\n"+ "结果="+forwardResult.getResult()+"   匹配率="+forwardResult.getProbability());
    }

    // 逆向输出
    public void reverse(String request){
        ReverseResult reverseResult = discernService.discernByReverse(request, i -> {
            System.out.println("【" + i.get("vertex") + "】递归" + "找到规则" + i.get("rule"));

            System.out.println(i.toString());
            if(textPane_1.getText().equals(""))
            	textPane_1.setText("【" + i.get("vertex") + "】递归" + "找到规则" + i.get("rule"));
            else
            	textPane_1.setText(textPane_1.getText()+"\r\n"+"【" + i.get("vertex") + "】递归" + "找到规则" + i.get("rule"));

            return (String) i.get("vertex");
        });
        System.out.println("===最终的答案===" + reverseResult.getRules());
        textPane_1.setText(textPane_1.getText()+"\r\n"+"===最合适的答案===");
        textPane_1.setText(textPane_1.getText()+"\r\n"+reverseResult.getRules());
    }
	
	
}

package com.niuer.ljj.计算器;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonListener;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculateUI extends JFrame {

	// 窗口面板
	private JPanel contentPane;

	// 声明计算对象
	CallBack callback;
	
	// 声明显示结果的标签
	JLabel lblNewLabel;

	// 指向当前对象
	public void setCallBack(CallBack callback) {
		this.callback = callback;
	}

	/**
	 * Create the frame.
	 */
	public CalculateUI() {

		// 设置关闭方式
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 设置标题
		setTitle("计算器");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		// 初始化界面
		initUI();

		setVisible(true);

	}

	public void initUI() {

		// 设置窗口头部的标签用来显示结果
		lblNewLabel = new JLabel("0");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 21));
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblNewLabel, BorderLayout.NORTH);

		// 放置按钮的面板
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		contentPane.add(panel, BorderLayout.CENTER);
		// 设置表格形式4*5，间隔为4
		panel.setLayout(new GridLayout(5, 5, 4, 4));

		// 将要显示的字加入字符串中
		String[] s = { "1", "2", "3","4", "5", "6", "7", "8", "9","0",
				"AC", "+", "-","*","/","=",".","(",")"};

		// 初始化按钮
		JButton[] buttons = new JButton[s.length];

		// 新建监听器对象
		BtnListener listener = new BtnListener();

		// 将按钮加入面板
		for (int i = 0; i < s.length; i++) {

				// 将字放在按钮上
				buttons[i]= new JButton(s[i]);

				// 给按钮加监听器
				buttons[i].addActionListener(listener);

				// 将按钮添加到面板中
				panel.add(buttons[i]);

		}

	}

	// 创建监听器类
	class BtnListener implements ActionListener {

		// 实例化一个字符串缓冲区
		StringBuffer str = new StringBuffer();

		// 新建字符串存表达式
		String news = "";

		@Override
		public void actionPerformed(ActionEvent e) {

			// 得到用户按下按钮上的文本
			String s = ((JButton) e.getSource()).getText();

			// 算出结果
			if (s != "=") {

				// 将表达式写入news中
				news = String.valueOf(str.append(s));

				lblNewLabel.setText(news);
			} else {
				System.out.println(news);
				callback.setExpression(news);

				news = String.valueOf(str.append("=" + callback.getResult()));

				lblNewLabel.setText(news);
			}
			
			// 判断是否按下清空键，若按下则清空后取“0”
			if (s == "AC") {
				str.delete(0, news.length());
				lblNewLabel.setText("0");
			}
		}
	}

	// 接口回掉
	public interface CallBack {

		// 设置输入的值,传表达式
		void setExpression(String expression);

		// 获得结果
		String getResult();
	}

}

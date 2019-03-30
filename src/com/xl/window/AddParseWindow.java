package com.xl.window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddParseWindow extends JFrame{
	
	JButton btn_ok,btn_cancel;
	JTextField text_name,text_value;
	public AddParseWindow(){
		setTitle("添加一条参数");
		setSize(320, 200);
		JPanel layout_mainJPanel= new JPanel();
		setContentPane(layout_mainJPanel);
		Box box_v= Box.createVerticalBox();
		layout_mainJPanel.add(box_v);
		text_name= new JTextField();
		text_value= new JTextField();
		JLabel label_nameJLabel= new JLabel("name");
		JLabel label_valueJLabel= new JLabel("value");
		Box box_label_name= Box.createHorizontalBox();
		box_label_name.add(label_nameJLabel);
		box_label_name.add(Box.createHorizontalGlue());
		Box box_label_valueBox= Box.createHorizontalBox();
		box_label_valueBox.add(label_valueJLabel);
		box_label_valueBox.add(Box.createHorizontalGlue());
		box_v.add(box_label_name);
		box_v.add(text_name);
		box_v.add(box_label_valueBox);
		box_v.add(text_value);
		btn_ok= new JButton("确定");
		btn_cancel= new JButton("取消");
		Box box_btnBox= Box.createHorizontalBox();
		box_btnBox.add(btn_ok);
		box_btnBox.add(btn_cancel);
		box_v.add(box_btnBox);
		btn_ok.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
			}
		});
		btn_cancel.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
			}
		});
	}
	
	
	//设置点击事件
	public void addActionListener(ActionListener listener) {
		btn_ok.addActionListener(listener);
	}
	
	//获取参数
	public String getValue() {
		if(text_name.getText().length()!=0&& text_value.getText().length()!=0)
		return text_name.getText()+":"+ text_value.getText();
		else{
			return null;
		}
	}
	
}

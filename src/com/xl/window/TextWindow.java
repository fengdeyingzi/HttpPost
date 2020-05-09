package com.xl.window;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.xl.util.JsonFormat;

public class TextWindow extends JFrame{
	
	JTextArea textArea;
	public TextWindow(){
		setTitle("返回信息");
		setSize(640, 480);
		JPanel layout_mainJPanel= new JPanel();
		setContentPane(layout_mainJPanel);
		setLayout(new BoxLayout(layout_mainJPanel, BoxLayout.Y_AXIS));
		this.textArea= new JTextArea();
		textArea.setLineWrap(true);        //激活自动换行功能 
		textArea.setWrapStyleWord(true);            // 激活断行不断字功能
		//JTextArea.setSize(600,0);
		//label.setMaximumSize(new Dimension(600, 0));
		JScrollPane scrollPane= new JScrollPane(textArea);
		scrollPane.setMinimumSize(new Dimension(620, 400));
//		scrollPane.setMaximumSize(layout_mainJPanel.getMaximumSize());
//		scrollPane.setPreferredSize(layout_mainJPanel.getPreferredSize());
//		scrollPane.setMaximumSize(new Dimension(1280, 960));
		Box box_v= Box.createVerticalBox();
		box_v.setPreferredSize(new Dimension(620, 30));
		layout_mainJPanel.add(box_v);
		box_v.add(scrollPane);
		Box box_btn = Box.createHorizontalBox();
		box_btn.setAlignmentX(0.5f);
		
		JButton btn_format = new JButton("JSON格式化");
//		btn_format.setPreferredSize(new Dimension(620, 30));
		btn_format.setMaximumSize(new Dimension(620, 30));
		btn_format.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jsonFormat();
				
			}
		});
		box_v.add(box_btn);
		box_btn.add(btn_format);
		
//		setContentPane(scrollPane);
	}
	
	//格式化json
	public void jsonFormat() {
		String retext = JsonFormat.formatJson(textArea.getText());
		textArea.setText(retext);
	}
	
	public void setText(String text) {
		this.textArea.setText(text);
		if(text.startsWith("{")){
			jsonFormat();
		}
	}

}

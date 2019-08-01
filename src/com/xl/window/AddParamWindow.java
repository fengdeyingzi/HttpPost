package com.xl.window;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import org.json.JSONObject;

/**
 * 导入数据
 * {
 *   url:"",
 *   head:[{
 *   name:name,
 *   value:value
 *   }],
 *   get:[{
 *   name:name,
 *   value:value
 *   }
 *   ],
 *   post:[
 *   {
 *   name:name,
 *   value:value
 *   }]
 *   
 * }
 * @author Administrator
 *
 */

public class AddParamWindow extends JFrame {
JTextArea textArea;
JButton btn_add;
	/**
	 * Create the panel.
	 */
	public AddParamWindow() {
		setTitle("添加一条参数");
		setSize(640, 320);
		JPanel layout_mainJPanel= new JPanel();
		setContentPane(layout_mainJPanel);
//		layout_mainJPanel.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		Box verticalBox = Box.createVerticalBox();
		
		layout_mainJPanel.add(verticalBox);
		
		 textArea = new JTextArea();
		JScrollPane scrollPane= new JScrollPane(textArea);
		verticalBox.add(scrollPane);
		scrollPane.setPreferredSize(new Dimension(600, 200));
		 btn_add = new JButton("导入");
		verticalBox.add(btn_add);
		
		btn_add.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String text = textArea.getText();
				setVisible(false);
				
			}
		});

	}
	
	public void addActionListener(ActionListener listener){
		btn_add.addActionListener(listener);
	}
	
	public java.lang.String getValue(){
		return textArea.getText();
	}

}

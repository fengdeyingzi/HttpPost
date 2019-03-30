package com.xl.window;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.Border;

import com.xl.swing.util.XConnect;

public class PostWindow extends JFrame{

	Vector<String> data_head;
	Vector<String> data_get;
	Vector<String> data_post;
	private JList list_head;
	private JList list_get;
	private JList list_post;
	private JButton btn_addhead;
	private JButton btn_getButton;
	private JButton btn_postButton;
	private JTextField edit_urlField;
	private JButton btn_go; 
	
	public PostWindow(){
		data_head = new Vector<String>();
		data_get= new Vector<String>();
		data_post= new Vector<String>();
		data_head.add("name:fengdeyingzi");
		Box box_v= Box.createVerticalBox();
		JPanel layout_mainJPanel= new JPanel();
		setTitle("post测试");
		setSize(640, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(layout_mainJPanel);
		layout_mainJPanel.add(box_v);
		 list_head= new JList();
		list_head.setName("head");
		
		list_head.setListData(data_head);
		list_head.setPreferredSize(new Dimension(600, 120));
		list_head.setMaximumSize(new Dimension(600,120));
		
		list_head.setBorder(BorderFactory.createTitledBorder("head"));
		 list_get= new JList();
		list_get.setPreferredSize(new Dimension(600, 120));
		list_get.setMaximumSize(new Dimension(600,120));
		list_get.setBorder(BorderFactory.createTitledBorder("get"));
		
		 list_post= new JList();
		list_post.setPreferredSize(new Dimension(600, 120));
		list_post.setMaximumSize(new Dimension(600,120));
		list_post.setBorder(BorderFactory.createTitledBorder("post"));
		JLabel textPane= new JLabel("输入url：");
		 //让加载字体
		 try {
			Font font = Font.createFont(Font.TRUETYPE_FONT,getClass().getResource("DroidSansMono.ttf"));
		} catch (FontFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		 
		Component box_aa= Box.createHorizontalGlue();
		Box box_textBox=Box.createHorizontalBox();
		box_textBox.add(textPane);
		box_textBox.add(box_aa);
		textPane.setAlignmentX(0);
		textPane.setHorizontalAlignment(0);
		 edit_urlField= new JTextField();
		 btn_go= new JButton();
		 
		btn_go.setText("send");
		Box box_edit= Box.createHorizontalBox();
		box_edit.add(edit_urlField);
		box_edit.add(btn_go);
		
		 btn_addhead= new JButton("添加head");
		 btn_getButton= new JButton("添加get参数");
		 btn_postButton= new JButton("添加post参数");
		
		JButton btn_clear= new JButton("清空");
		box_v.add(box_textBox); //
		
		box_v.add(box_edit);
		
		box_v.add(list_head);
		box_v.add(list_get);
		box_v.add(list_post);
		Box box_buttonBox= Box.createHorizontalBox();
		box_v.add(box_buttonBox);
		box_buttonBox.add(btn_addhead);
		box_buttonBox.add(btn_getButton);
		box_buttonBox.add(btn_postButton);
		box_buttonBox.add(btn_clear);
		btn_addhead.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				final AddParseWindow dialogAddParseWindow= new AddParseWindow();
				dialogAddParseWindow.setVisible(true);
				dialogAddParseWindow.setLocation(getX()+160, getY()+40);
				dialogAddParseWindow.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String textString= dialogAddParseWindow.getValue();
						data_head.add(textString);
						list_head.setListData(data_head);
					}
				});
			}
		});
		
		btn_getButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// TODO Auto-generated method stub
				final AddParseWindow dialogAddParseWindow= new AddParseWindow();
				dialogAddParseWindow.setVisible(true);
				dialogAddParseWindow.setLocation(getX()+160, getY()+40);
				dialogAddParseWindow.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String textString= dialogAddParseWindow.getValue();
						data_get.add(textString);
						refreshList();
					}
				});
				
			}
		});
		btn_postButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// TODO Auto-generated method stub
				final AddParseWindow dialogAddParseWindow= new AddParseWindow();
				dialogAddParseWindow.setVisible(true);
				dialogAddParseWindow.setLocation(getX()+160, getY()+40);
				dialogAddParseWindow.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String textString= dialogAddParseWindow.getValue();
						data_post.add(textString);
						refreshList();
					}
				});
			}
		});
		btn_clear.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				data_get.clear();
				data_post.clear();
				data_head.clear();
				refreshList();
				
			}
		});
		btn_go.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				XConnect connect= new XConnect(edit_urlField.getText(), new XConnect.PostGetInfoListener() {
					
					public void onPostGetText(String text) {
						// TODO Auto-generated method stub
						TextWindow window= new TextWindow();
						window.setLocation(getX(), getY());
						window.setVisible(true);
						window.setText(text);
					}
				});
				for(int i=0;i<data_head.size();i++){
					String items[]= data_head.get(i).split(":");
					connect.addHead(items[0], items[1]);
				}
				for(int i=0;i<data_get.size();i++){
					String items[]= data_get.get(i).split(":");
					connect.addGetParmeter(items[0], items[1]);
				}
				for(int i=0;i<data_post.size();i++){
					String items[]= data_post.get(i).split(":");
					connect.addPostParmeter(items[0], items[1]);
				}
				connect.execute();
			}
		});
		
	}
	
	
	private void refreshList(){
		list_get.setListData(data_get);
				list_head.setListData(data_head);
				list_post.setListData(data_post);
	}
	
	
	
	
	
}

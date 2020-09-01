package com.xl.window;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.Border;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.xl.tool.OConnect;
import com.xl.tool.XConnect;
import com.xl.util.ClipBoard;
import com.xl.util.JsonFormat;

//import com.xl.swing.util.XConnect;

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
	private JButton btn_addText;
	private JButton btn_output;
	private JTextField edit_urlField;
	private JButton btn_go; 
	private JCheckBox checkBox_isJson;
	private JCheckBox checkBox_isData;
	
	public PostWindow(){
		data_head = new Vector<String>();
		data_get= new Vector<String>();
		data_post= new Vector<String>();
		data_head.add("name:fengdeyingzi");
		Box box_v= Box.createVerticalBox();
		JPanel layout_mainJPanel= new JPanel();
		setTitle("HttpPost测试-v1.2 - 风的影子 制作");
		setSize(640, 520);
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
//		 try {
//			Font font = Font.createFont(Font.TRUETYPE_FONT,getClass().getResource("DroidSansMono.ttf"));
//		} catch (FontFormatException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} 
		 
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
		
		btn_addText = new JButton();
		btn_addText.setText("导入数据");
		
		btn_output = new JButton();
		btn_output.setText("导出数据");
		
		checkBox_isJson = new JCheckBox("json类型数据");
		
		checkBox_isData = new JCheckBox("Data封装");
		
		
		 btn_addhead= new JButton("添加head");
		 btn_getButton= new JButton("添加get参数");
		 btn_postButton= new JButton("添加post参数");
		
		JButton btn_clear= new JButton("清空");
		box_v.add(box_textBox); //
		
		box_v.add(box_edit);
		
		box_v.add(list_head);
		box_v.add(list_get);
		box_v.add(list_post);
		Box box_check = Box.createHorizontalBox();
		box_check.add(checkBox_isJson);
		box_check.add(checkBox_isData);
		
		Box box_buttonBox= Box.createHorizontalBox();
		box_v.add(box_check);
		box_v.add(box_buttonBox);
		
		box_buttonBox.add(btn_addText);
		box_buttonBox.add(btn_output);
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
//						data_head.add(textString);
						addItem(data_head, textString);
						list_head.removeAll();
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
//						data_get.add(textString);
						addItem(data_get, textString);
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
//						data_post.add(textString);
						addItem(data_post, textString);
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
				String url = edit_urlField.getText();
				if(url.indexOf("://") <0){
					url = "http://"+url;
				}
				OConnect connect= new OConnect(url, new OConnect.PostGetInfoListener() {
					
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
					connect.addHeader(items[0], items[1]);
				}
				for(int i=0;i<data_get.size();i++){
					String items[]= data_get.get(i).split(":");
					connect.addGetParmeter(items[0], items[1]);
				}
				for(int i=0;i<data_post.size();i++){
					String items[]= data_post.get(i).split(":");
					connect.addPostParmeter(items[0], items[1]);
				}
				connect.setJSONPost(checkBox_isJson.isSelected(),checkBox_isData.isSelected());
				connect.start();
			}
		});
		
		btn_addText.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				final AddParamWindow window = new AddParamWindow();
				window.setLocation(getX(), getY());
				window.setVisible(true);
				window.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String text = window.getValue();
						try{
						JSONObject jsonObject = new JSONObject(text);
						String url = jsonObject.getString("url");
						data_get.clear();
						data_post.clear();
						data_head.clear();
						edit_urlField.setText(url);
						checkBox_isJson.setSelected(jsonObject.optBoolean("is_json"));
						checkBox_isData.setSelected(jsonObject.optBoolean("is_data"));
						JSONObject array_head = jsonObject.getJSONObject("head");
						JSONObject array_get = jsonObject.getJSONObject("get");
						JSONObject array_post = jsonObject.getJSONObject("post");
						Iterator<String> iter_head = array_head.keys();
						while (iter_head.hasNext()){
                            String key = iter_head.next();
                            data_head.add(key+":"+array_head.getString(key));
                        }
//						for(int i=0;i<array_head.length();i++){
//							JSONObject obj = array_head.getJSONObject(i);
//							String key = obj.getString("key");
//							String value = obj.getString("value");
//							data_head.add(key+":"+value);
//						}
						Iterator<String> iter_get = array_get.keys();
						while (iter_get.hasNext()){
                            String key = iter_get.next();
                            data_get.add(key+":"+array_get.getString(key));
                        }
//						for(int i=0;i<array_get.length();i++){
////							JSONObject obj = array_get.getJSONObject(i);
//							String key = obj.getString("key");
//							String value = obj.getString("value");
//							data_get.add(key+":"+value);
//						}
						Iterator<String> iter_post = array_post.keys();
						while (iter_post.hasNext()){
                            String key = iter_post.next();
                            data_post.add(key+":"+array_post.getString(key));
                        }
//						for(int i=0;i<array_post.length();i++){
////							JSONObject obj = array_post.getJSONObject(i);
//							String key = obj.getString("key");
//							String value = obj.getString("value");
//							data_post.add(key+":"+value);
//						}
						refreshList();
						window.dispose();
						}
						catch(JSONException e1){
							e1.printStackTrace();
							window.dispose();
						}
						
						
						
					}
				});
			}
		});
		
		btn_output.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				JSONObject jsonObject = new JSONObject();
				
				String url = edit_urlField.getText();
				jsonObject.put("url", url);
				jsonObject.put("is_json", checkBox_isJson.isSelected());
				jsonObject.put("is_data", checkBox_isData.isSelected());
				JSONObject array_head = new JSONObject();
				JSONObject array_get = new JSONObject();
				JSONObject array_post = new JSONObject();
				
				for(int i=0;i<data_head.size();i++){
					String items[]= data_head.get(i).split(":");
//					JSONObject obj = new JSONObject();
					array_head.put(items[0],items[1]);
//					array_head.put(obj);
				}
				for(int i=0;i<data_get.size();i++){
					String items[]= data_get.get(i).split(":");
//					JSONObject obj = new JSONObject();
					array_get.put( items[0],items[1]);
//					array_get.put(obj);
				}
				for(int i=0;i<data_post.size();i++){
					String items[]= data_post.get(i).split(":");
//					JSONObject obj = new JSONObject();
					array_post.put(items[0],items[1]);

//					array_post.put(obj);
				}
				jsonObject.put("head", array_head);
				jsonObject.put("get", array_get);
				
				jsonObject.put("post", array_post);
				String outString = jsonObject.toString();
				String jsonFormatText= JsonFormat.formatJson(outString);
				ClipBoard.setText(jsonFormatText);
				TextWindow window= new TextWindow();
				window.setLocation(getX(), getY());
				window.setVisible(true);
				window.setText(jsonFormatText);
			}
		});
		
	}
	
	
	private void refreshList(){
		list_get.removeAll();
		list_head.removeAll();
		list_post.removeAll();
		list_get.setListData(data_get);
				list_head.setListData(data_head);
				list_post.setListData(data_post);
	}
	
	private void addItem(Vector<String> list_item, String item) {
		
		for(int i=0;i<list_item.size();i++){
			String temp = list_item.get(i);
			if(temp.startsWith(item+":")){
				list_item.remove(i);
			}
		}
		list_item.add(item);
	}
	
	
	
	
	
}

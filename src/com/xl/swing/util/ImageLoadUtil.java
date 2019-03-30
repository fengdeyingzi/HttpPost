package com.xl.swing.util;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.ImageIcon;

public class ImageLoadUtil {
	
	//从内存卡加载图片
	public static Image getImageFromCard(String path) {
		return Toolkit.getDefaultToolkit().getImage(path);
		
	}
	
	
	//从网络加载图片
	public static Image getImageFromNet(URL url){
		return Toolkit.getDefaultToolkit().getImage(url);
	}
	
	//从jar包内加载图片
	public static Image getImageFromClass(String path){
		
		ImageIcon image= new ImageIcon(new ImageLoadUtil().getClass().getResource(path));
		return image.getImage();
	}
	

}

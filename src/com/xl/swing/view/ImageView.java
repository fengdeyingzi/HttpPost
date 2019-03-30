package com.xl.swing.view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JComponent;
import javax.swing.JLabel;

public class ImageView extends JComponent {
	Image image;
	int draw_w;
	int draw_h;
	public ImageView(){
		super();
		
	}
	
	public ImageView(Image image){
		this.image= image;
	}
	
	public void setImage(Image image) {
		this.image= image;
	
		repaint();
	}
	

	
	@Override
	public void paintComponent(Graphics g) {
		
		// TODO Auto-generated method stub
		if(image!=null){
			//如果图片宽高比大于控件宽高比 那么以控件宽度显示图片
			if(((float)image.getWidth(this))/image.getHeight(this) > ((float)getWidth())/getHeight()){
				
				draw_w= getWidth();
				draw_h= getWidth()* image.getHeight(this)/image.getWidth(this);
			}else{
			draw_h= getHeight();
				draw_w= getHeight() * image.getWidth(this)/image.getHeight(this);
				
			}
			g.drawImage(image,
					(getWidth()-draw_w)/2, (getHeight()-draw_h)/2,(getWidth()-draw_w)/2+draw_w,(getHeight()-draw_h)/2+draw_h,
					0,0,image.getWidth(this),image.getHeight(this), 
					 this);
			//g.drawImage(image,  0	, 0, getWidth(), getHeight(),0, 0, image.getWidth(this), image.getHeight(this)	, this);
		}
		
		//super.paint(g);
		System.out.println("paint"+draw_w +" "+ draw_h);
	}

}

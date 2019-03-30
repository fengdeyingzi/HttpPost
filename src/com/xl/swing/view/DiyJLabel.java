package com.xl.swing.view;
import java.awt.Graphics;  
import java.awt.Image;  
import java.io.File;  
import java.io.IOException;  
  
import javax.imageio.ImageIO;  
import javax.swing.JLabel;  
  
/** 
 * 重写JLabel，用于自定义显示图片 
 * @author RuiLin.Xie - xKF24276 
 * 
 */  
public class DiyJLabel extends JLabel  
{  
    //图片路径  
    private String imgPath;  
      
    //显示样式  
    private ShowStyle style;  
      
    //图片  
    private Image img;  
  
    /** 
     * 设置图片路径及显示样式 
     * @param path 
     */  
    public void setImage(String path, ShowStyle style)  
    {  
        imgPath = path;  
        this.style = style;  
          
        try  
        {  
            img = ImageIO.read(new File(imgPath));  
        }  
        catch (IOException e)  
        {  
            e.printStackTrace();  
        }  
    }  
    
    public void setImage(Image image) {
		this.img= image;
		repaint();
	}
      
    /** 
     * 重写label绘画方法 
     */  
    @Override  
    protected void paintComponent(Graphics g)  
    {  
        Image img = getImage();  
          
        if(ShowStyle.ORIGINALLY == style && img != null)  
        {  
            //图片原始像素  
            g.drawImage(img, 0, 0, img.getWidth(this), img.getHeight(this), this);  
        }  
        else if(ShowStyle.ZOOM == style && img != null)  
        {  
            //缩放到与label同大小  
            g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);  
        }  
        else if(style == null && img != null)  
        {  
            //图片原始像素  
            g.drawImage(img, 0, 0, img.getWidth(this), img.getHeight(this), this);  
        }  
  
        super.paintComponent(g);  
        g = null;  
    }  
      
    /** 
     * 得到设置图片 
     * @return 
     */  
    public Image getImage()  
    {  
        return img;  
    }  
}  
  
/** 
 * 显示样式 
 * @author RuiLin.Xie - xKF24276 
 */  
enum ShowStyle  
{  
    /** 原始像素 **/  
    ORIGINALLY,  
      
    /** 缩放到与Label同大小 **/  
    ZOOM  
}  
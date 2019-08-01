import com.xl.swing.util.UIUtil;
import com.xl.window.PostWindow;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;


public class Main {

	public static void main(String[] args) {
		// 启动looper	
        Looper.prepare(true);
		UIUtil.setWindowsStyle();
		PostWindow window= new PostWindow();
		window.setVisible(true);
		
		Handler handler = new Handler() {
			
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				System.out.println("message....");
			}
		};
		
		handler.sendMessage(new Message());
		
		Looper.loop();
		
		
	}
}

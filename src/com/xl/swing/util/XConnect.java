package com.xl.swing.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Handler;

import javax.swing.SwingWorker;

public class XConnect extends SwingWorker<String, Void> {
    private String url;
    //private String param;
    PostGetInfoListener listener;
    private final static int CONNENT_TIMEOUT = 5000;
    private final static int READ_TIMEOUT = 15000;
    private static String ua= "Dalvik/1.6.0 (Linux; U; Android 4.4.4; MI 4LTE MIUI/V6.6.2.0.KXDCNCF)";
	private HashMap<String,String> fileMap;
	private HashMap<String,String> postMap;
	private HashMap<String,String> getMap;
	private HashMap<String, String> headMap;
    Handler handler;
    static String TAG = "XConnect";
  
    //创建post请求
    public XConnect(String url,String param,final PostGetInfoListener listener)
    {
        super();
		this.postMap = new HashMap<String,String>();
		this.fileMap = new HashMap<String,String>();
		this.getMap = new HashMap<String,String>();
		this.headMap= new HashMap<String, String>();
        this.url=url;
        //this.param=param;
        this.listener = listener;
        if(param!=null) {
            String list[] = param.split("&");
            for (String item : list) {
                String ii[] = item.split("=");
                if (ii.length == 2)
                    postMap.put(ii[0], ii[1]);
            }
        }
      
    }

    //创建get请求
    public XConnect(String url, PostGetInfoListener listener)
    {
        this(url,null,listener);
    }
	
	//添加post文件
	public void addPostFile(String name,String fileName){
		fileMap.put(name, fileName);
	}
	
	//添加post参数
	public void addPostParmeter(String name,String value){
		postMap.put(name,value);
	}
	
	public void addHead(String name,String value){
		this.headMap.put(name, value);
	}
	
	//
    public  String getUrl() {
		if(url.indexOf('?')>0){
			return url + "&"+getInfo();
		}
		else
			return url + "?" + getInfo();
    }
	
	
	//
	public void addGetParmeter(String name,String value){
		getMap.put(name,value);
	}
	
	//获取get内容
    public String getInfo() {
        StringBuilder builder = new StringBuilder();
        Iterator iter = getMap.entrySet().iterator();
        boolean isStart = true;
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String) entry.getKey();
            String val = (String) entry.getValue();
            try {
                builder.append(key + "=" + URLEncoder.encode(val, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            ;
            isStart = false;
            if (!isStart) {
                builder.append("&");
            }


        }
        String re = builder.toString();
        if(re.length()!=0)
			re = re.substring(0, re.length() - 1);
        return re;
    }
	
	//获取post
    public String postInfo() {
        StringBuilder builder = new StringBuilder();
        Iterator iter = postMap.entrySet().iterator();
        boolean isStart = true;
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String) entry.getKey();
            String val = (String) entry.getValue();
            /*
            try {
                builder.append(key + "=" + URLEncoder.encode(val, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            */
            //xldebug 后台不能解码。。故修改

            builder.append(key+"="+val);
            isStart = false;
            if (!isStart) {
                builder.append("&");
            }


        }
        String re = builder.toString();
        re = re.substring(0, re.length() - 1);
        return re;
    }
	

    public static String get(String url)
    {
        String result = "";
        BufferedReader in = null;
        try
        {
            String urlName = url ;
            URL realUrl = new URL(urlName);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            //conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            conn.setRequestProperty("user-agent", "Dalvik/1.6.0 (Linux; U; Android 4.4.4; MI 4LTE MIUI/V6.6.2.0.KXDCNCF)");
            
            //if(BaseConfig.token!=null)
            //conn.setRequestProperty("token", BaseConfig.token);
            conn.setConnectTimeout(CONNENT_TIMEOUT);
            conn.setReadTimeout(READ_TIMEOUT);

            //conn.setRequestProperty("user-agent","MQQBrowser\nQ-UA2: QV=3&PL=ADR&PR=QB&PP=com.tencent.mtt&PPVN=6.5.0.2170&TBSVC=26001&CO=BK&COVC=036504&PB=GE&VE=GA&DE=PHONE&CHID=0&LCID=9678&MO= MI4LTE &RL=1080*1920&OS=4.4.4&API=19");
            // 建立实际的连接
            conn.connect();
            // 获取所有响应头字段
			/*
			Map<String, List<String>> map = conn.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet())
			{
				System.out.println(key + "--->" + map.get(key));
			}
			*/
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null)
            {
                result += "\n" + line;
            }
        }
        catch (Exception e)
        {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally
        {
            try
            {
                if (in != null)
                {
                    in.close();
                }
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定URL发送POST方法的请求
     *
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是name1=value1&name2=value2的形式。
     * @return URL所代表远程资源的响应
     */
    public String post(String url, String param)
    {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try
        {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性




            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            //conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            conn.setRequestProperty("user-agent", "Dalvik/1.6.0 (Linux; U; Android 4.4.4; MI 4LTE MIUI/V6.6.2.0.KXDCNCF)");
            if(!headMap.isEmpty()){
            	
            }
            
           
			//if(BaseConfig.token!=null){
           //     conn.setRequestProperty("token",BaseConfig.token);
           // }
            conn.setConnectTimeout(CONNENT_TIMEOUT);
            conn.setReadTimeout(READ_TIMEOUT);

            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null)
            {
                result += "\n" + line;
            }
        }
        catch (Exception e)
        {
            System.out.println("发送POST请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally
        {
            try
            {
                if (out != null)
                {
                    out.close();
                }
                if (in != null)
                {
                    in.close();
                }
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public String doConnect()
    {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try
        {
            URL realUrl = new URL(getUrl());
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性




            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            //conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            conn.setRequestProperty("user-agent", "Dalvik/1.6.0 (Linux; U; Android 4.4.4; MI 4LTE MIUI/V6.6.2.0.KXDCNCF)");
            if(!headMap.isEmpty()){
            	Iterator iter = headMap.entrySet().iterator();  
                while (iter.hasNext()) {  
                    Map.Entry entry = (Map.Entry) iter.next();  
                    String name = (String) entry.getKey();  
                    String value = (String) entry.getValue();  
                    conn.setRequestProperty(name, value);
                }
            }
          //  if(BaseConfig.token!=null)
         //   conn.setRequestProperty("token",BaseConfig.token);
            conn.setConnectTimeout(CONNENT_TIMEOUT);
            conn.setReadTimeout(READ_TIMEOUT);

            /*
            if (cookie != null)
            {
                conn.setRequestProperty("Cookie", cookie);
            }
            */
            // 发送POST请求必须设置如下两行
            conn.setDoInput(true);
            if(!postMap.isEmpty()){
            	conn.setDoOutput(true);
            	// 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            String param= postInfo();
             // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            }
            
            
            
           
            
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null)
            {
                result += "\n" + line;
            }
        }
        catch (Exception e)
        {
            System.out.println("发送POST请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally
        {
            try
            {
                if (out != null)
                {
                    out.close();
                }
                if (in != null)
                {
                    in.close();
                }
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
        return result;
    }


	
	/**
     * 
     * @param urlStr  url地址
     * @param textMap 附带信息
     * @param fileMap 文件列表   
     * @return 返回json的报文 如果失败，则为空
     */

	public String postFileByForm(String urlStr, Map<String, String> textMap,  
										Map<String, String> fileMap){
        String res = "";  
        HttpURLConnection conn = null;  
        String BOUNDARY = "---------------------------WebKitFormBoundaryvQdJRYhxZtA2ZkYN"; //boundary就是request头和上传文件内容的分隔符  
        try {  
            URL url = new URL(urlStr);  
            conn = (HttpURLConnection) url.openConnection();  
            conn.setConnectTimeout(CONNENT_TIMEOUT);  
            conn.setReadTimeout(READ_TIMEOUT);  
            conn.setDoOutput(true);  
            conn.setDoInput(true);  
            conn.setUseCaches(false);  
            conn.setRequestMethod("POST");  
            conn.setRequestProperty("Connection", "Keep-Alive");  
            conn.setRequestProperty("User-Agent",  
									"Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");  
            conn.setRequestProperty("Content-Type",  
									"multipart/form-data; boundary=" + BOUNDARY);  
		//	if(BaseConfig.token!=null)
		//		conn.setRequestProperty("token", BaseConfig.token);
				   
            OutputStream out = new DataOutputStream(conn.getOutputStream());  
            // text  
            if (textMap != null) {  
                StringBuffer strBuf = new StringBuffer();  
                Iterator iter = textMap.entrySet().iterator();  
                while (iter.hasNext()) {  
                    Map.Entry entry = (Map.Entry) iter.next();  
                    String inputName = (String) entry.getKey();  
                    String inputValue = (String) entry.getValue();  
                    if (inputValue == null) {  
                        continue;  
                    }  
                    strBuf.append("\r\n").append("--").append(BOUNDARY).append(  
						"\r\n");  
                    strBuf.append("Content-Disposition: form-data; name=\""  
								  + inputName + "\"\r\n\r\n");  
                    strBuf.append(inputValue);  
                }  
                out.write(strBuf.toString().getBytes());                
            }  

            // file  
            if (fileMap != null) {  
                Iterator iter = fileMap.entrySet().iterator();  
                while (iter.hasNext()) {  
                    Map.Entry entry = (Map.Entry) iter.next();  
                    String inputName = (String) entry.getKey();  
                    String inputValue = (String) entry.getValue();  
                    if (inputValue == null) {  
                        continue;  
                    }  
                    File file = new File(inputValue);  
                    String filename = file.getName();  
                    String contentType = null;                    
                    if (contentType == null || contentType.equals("")) {  
                        contentType = "application/octet-stream";  
                    }  

                    StringBuffer strBuf = new StringBuffer();  
                    strBuf.append("\r\n").append("--").append(BOUNDARY).append(  
						"\r\n");  
                    strBuf.append("Content-Disposition: form-data; name=\""  
								  + inputName + "\"; filename=\"" + filename  
								  + "\"\r\n");  
                    strBuf.append("Content-Type:" + contentType + "\r\n\r\n");  

                    out.write(strBuf.toString().getBytes());  

                    DataInputStream in = new DataInputStream(  
						new FileInputStream(file));  
                    int bytes = 0;  
                    byte[] bufferOut = new byte[1024];  
                    while ((bytes = in.read(bufferOut)) != -1) {  
                        out.write(bufferOut, 0, bytes);  
                    }  
                    in.close();  
                }  
            }  

            byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();  
            out.write(endData);  
            out.flush();  
            out.close();  

            // 读取返回数据  
            StringBuffer strBuf = new StringBuffer();  
            BufferedReader reader = new BufferedReader(new InputStreamReader(  
														   conn.getInputStream()));  
            String line = null;  
            while ((line = reader.readLine()) != null) {  
                strBuf.append(line).append("\n");  
            }  
            res = strBuf.toString();  
            reader.close();  
            reader = null;  
        } catch (Exception e) {             
			//日志处理
            res = "";
        } finally {  
            if (conn != null) {  
                conn.disconnect();  
                conn = null;  
            }  
        }  

        return res;  
    }  

    /*
    监听器
    */
    public interface PostGetInfoListener
    {
        public void onPostGetText(String text);
    }

//    线程运行
	@Override
	protected String doInBackground() throws Exception {
		// TODO Auto-generated method stub
		String text = null;
        if(url.startsWith("https://"))
        {
            if(!postMap.isEmpty())
                text = HttpUtil.HttpsPost(getUrl(),postInfo(),null);
            else
                text = HttpUtil.HttpsPost(getUrl(),null,null);
        }
        else
        {
            if(!postMap.isEmpty() || !fileMap.isEmpty()){
				if(fileMap.isEmpty())
                text = doConnect();
				else{
					text = postFileByForm(getUrl(), postMap, fileMap);
				}
				}
            else
                text = doConnect();
        }
		return text;
	}


//	连接完成
	@Override
	protected void done() {
		// TODO Auto-generated method stub
		super.done();
		if(listener!=null){
			try {
//				get获取doBackground的数据
				listener.onPostGetText(get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


}

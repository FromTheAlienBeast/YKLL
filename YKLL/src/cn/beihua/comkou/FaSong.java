package cn.beihua.comkou;

import java.io.*;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.beiahu.utils.DataSourceUtils;
import cn.beihua.action.Action;
import cn.beihua.vo.*;
import gnu.io.*;

public class FaSong /*extends Thread extends ActionSupport*/ implements SerialPortEventListener{
	static CommPortIdentifier portId; 
    static Enumeration<?> portList; 
    InputStream inputStream; 
    static OutputStream outputStream;
    static SerialPort serialPort; 
    char []ch = new char[8];  
    public  static String s;
    public static String [] mystr;
    public Com com=new Com();
    public List<Pro> list;
    public String username;
    public String state;
    public String site;
    public String code;
    public static FaSong cRead = new FaSong ();
    private BlockingQueue<String> msgQueue = new LinkedBlockingQueue<String>();
   // @Override
    public void serialEvent(SerialPortEvent event) {//
    	switch (event.getEventType()) {
        	case SerialPortEvent.BI:
        	case SerialPortEvent.OE:
        	case SerialPortEvent.FE:
        	case SerialPortEvent.PE:
        	case SerialPortEvent.CD:
        	case SerialPortEvent.CTS:
        	case SerialPortEvent.DSR:
        	case SerialPortEvent.RI:
        	case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
        		break;
        	case SerialPortEvent.DATA_AVAILABLE:
        		break;
        	}
    	}
    public int startComPort() {
        portList = CommPortIdentifier.getPortIdentifiers();

        while (portList.hasMoreElements()) {

            portId = (CommPortIdentifier) portList.nextElement();

            System.out.println("设备类型--->" + portId.getPortType());
            System.out.println("设备名称---->" + portId.getName());
        }
 
        if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
      
        	//接受
            if (portId.getName().equals("COM3")) {
                try {
                 
                    serialPort = (SerialPort) portId.open("COM_3", 2000);

                } catch (PortInUseException e) {
                    e.printStackTrace();
                    return 0;
                }
          
                try {
                    inputStream = serialPort.getInputStream();
                    outputStream = serialPort.getOutputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                    return 0;
                }
             
                try {
                    serialPort.addEventListener(this);
                } catch (TooManyListenersException e) {
                    e.printStackTrace();
                    return 0;
                }
             
                serialPort.notifyOnDataAvailable(true);
                try {
                    serialPort.setSerialPortParams(115200,
                            SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
                            SerialPort.PARITY_NONE);
                } catch (UnsupportedCommOperationException e) {
                    e.printStackTrace();
                    return 0;
                }

                return 1;
            }
        }
    return 0;
}
    //@Override
    public void run() { 
    	 int i = cRead.startComPort();
         System.out.println(i);
         if (i == 1) {
    // TODO Auto-generated method stub
    	try {
    		System.out.println("--------------任务处理线程运行了--------------");
       
      		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
      			//查询购买的的商品
      		String sql1="select * from pro";
      		System.out.println("test  前");
			list = (List<Pro>) qr.query(sql1, new BeanListHandler(Pro.class));
				
			for (Pro bean : list) {
			
      			username=bean.getUsername();
      			site=bean.getSite();
      			state=bean.getState();
      			code=bean.getCode();
    
      			try {
			        outputStream.write(username.getBytes("utf-8"));
			        outputStream.write(state.getBytes("utf-8"));
					outputStream.write(site.getBytes("utf-8"));
					outputStream.write(code.getBytes("utf-8"));
					username.getBytes();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}   
      			System.out.println(username+state+site+code);
      			try {
					Thread.sleep(20*1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
      		}
			
        } catch (SQLException e) {
		e.printStackTrace();
	}
         }
     	
}

   //调用前台页面的 
    public String execute(){
    	System.out.println("test1");
    	//cRead.start();
    	//cRead.run();   
    	System.out.println("test2");
		return "record";   //在前端页面上通过迭代显示出来
	}
public static void main(String[] args) {
	//FaSong cRead = new FaSong ();
	//FaSong cRead = new FaSong ();
    //int i = cRead.startComPort();
    //if (i == 1) {
    	//cRead.start();
		//cRead.run();
    	cRead.execute();
   // } else {
        return;
   // }
	//cRead.execute();
}
    
}


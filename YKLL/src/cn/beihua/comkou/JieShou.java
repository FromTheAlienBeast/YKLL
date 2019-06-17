package cn.beihua.comkou;

import java.io.*;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import cn.beiahu.utils.DataSourceUtils;
import cn.beihua.action.Action;
import cn.beihua.vo.*;
import gnu.io.*;

public class JieShou extends Thread/*HttpServlet*/  implements SerialPortEventListener{
	static CommPortIdentifier portId; 
    static Enumeration<?> portList; 
    InputStream inputStream; 
    static OutputStream outputStream;
    static SerialPort serialPort; 
    char []ch = new char[8];  
    public  static String s;
    public static String [] mystr;
    public Com com=new Com();
    public Action action=new Action();
    public Product product=new Product();
    public User user=new User();
    public int n=0;
    public int id;
    public String name;
    public double shop_price;
    public String  image;
    public int uid;
    public static Pro pro=new Pro();
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
        	byte[] readBuffer = new byte[20];
            try {
                int numBytes = -1;
                //获取信息的长度
                while (inputStream.available() > 0) {
                    numBytes = inputStream.read(readBuffer);

                    if (numBytes > 0) {
                        msgQueue.add(new String(readBuffer));
                        readBuffer = new byte[20];
                    } else {
                        msgQueue.add("e------500");
                    }	
                }
            } catch (IOException e) {
            }
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
                
                    serialPort.setSerialPortParams(9600,
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
@Override
public void run() { 
	int ii=1;
	 String username;
	 String state;
	 String site;
	 String code;
    // TODO Auto-generated method stub
	try {
		/*JieShou cRead = new JieShou();
	    int i = cRead.startComPort();
	    if (i == 1) {*/
        System.out.println("--------------任务处理线程运行了--------------");
        while (true) {
            // 如果堵塞队列中存在数据就将其输出
            if (msgQueue.size() > 0) {
            
      		    s = msgQueue.take();
      		    //分割接受的字符
      		   mystr=s.split(",");
      		   username=mystr[0];
      		   state=mystr[1];
      		   site=mystr[2];
      		   code=mystr[3];
      		   String nul=mystr[4];
      			com.setUserName(username);
      			com.setState(state);
      			com.setSite(site);
      			com.setCode(code);
      			QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
      		//2.编写sql
      			String sql="insert into com values(?,?,?,?)";
      		//3.执行sql
      			username=com.getUserName();
      			state=com.getState();
      			site=com.getSite();
      			code=com.getCode();
      			//加入com数据库
      			//qr.update(sql,username,state,site,code);
      			//通过code在上商品中查询到  
      			String sql1="select * from product where CODE=?";
      			product = qr.query(sql1, new BeanHandler(Product.class),code);
      			//通过username查询uid
      			String sqlu="select * from user where username=?";
      			user=qr.query(sqlu, new BeanHandler(User.class),username);
      			System.out.println(user);
      			//将product的  pid  pname    shop_price  image存入到pro数据库中
      			id=product.getPid();
      			name=product.getPname();
      			shop_price=product.getShop_price();
      			image=product.getImage();
      			code=product.getCode();
      			uid=user.getUid();
      		//Pro的数据库中加入了购买的东西；
      			String sqlp="insert into pro values(?,?,?,?,?,?,?,?,?,?)";
      			qr.update(sqlp,id,name,shop_price,image,username,site,state,code,uid,ii);   
      			ii++;
      			System.out.println(product.getPid()+product.getPname());
            }
        }
	   /* }else {
	        return;
	    }*/
    } catch (InterruptedException e) {
        e.printStackTrace();
    } catch (SQLException e) {
		e.printStackTrace();
	}
}

public static void main(String[] args) {
	JieShou cRead = new JieShou();
   int i = cRead.startComPort();
    if (i == 1) {
        cRead.start();
    } else {
        return;
    }
}
}

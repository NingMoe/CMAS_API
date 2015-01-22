package Reader;

import java.io.IOException;
import java.sql.Time;
import java.util.Properties;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;
import jssc.SerialPortTimeoutException;

import org.apache.log4j.Logger;


import Utilities.DataFormat;

import exception.RespCode;
import org.apache.commons.lang.ArrayUtils;

public class RecvSender {

	private SerialPort mPort = null;
	private String portName = null;
	private boolean initOk = false;
	static Logger logger = Logger.getLogger(RecvSender.class);
	
	public RecvSender(){
		//todo...
		
	}
	
	private int init()
	{
		// TODO Auto-generated method stub		
		logger.info("Start");
		int result = RespCode.SUCCESS.getId();
		try{
			
			Properties  apiConfig = new Properties();
			apiConfig.load(RecvSender.class.getClassLoader().getResourceAsStream("EasycardAPI.properties")); 
							
			if((portName = apiConfig.getProperty("ReaderPort"))==null)
				result = RespCode.ERROR.getId();
			logger.debug("Reader ComportName: " + portName);
			logger.info("End");
			
		}
		catch(IOException e)
		{
			result = RespCode.ERROR.getId();
			logger.error("IOException: "+e.getMessage());
		}
		return result;
	}
	
	
	private static String[] sGetPortList() {
		return SerialPortList.getPortNames();
	}
	
	private boolean openPort(String portName) {
		
		logger.info("Start");
		String[] portNames = SerialPortList.getPortNames();
		if (portNames == null) {
			logger.error("no any comport existed");
			return false;
		}
		int i = 0;
		for (; i < portNames.length; i ++) {
			if (portNames[i].equals(portName)) {
				break;
			}
		}
		if (i >= portNames.length) {
			logger.error("portName("+portName+") not exist");
			return false;
		}
		if(mPort != null)
		{
			//duplicated Open the same portName
			if(mPort.getPortName().equalsIgnoreCase(portName) && 
			   mPort.isOpened())
				return true;//already opend ,return true
			else
				closePort();
		}
		
		
		mPort = new SerialPort(portName);
		if (mPort == null) {
			logger.error("new SerialPort obj fail");
			return false;
		}
		
		try {
			if (!mPort.openPort()) {
				logger.error("openport fail");
				return false;
			}
			if (!mPort.setParams(SerialPort.BAUDRATE_115200, 
			        		SerialPort.DATABITS_8,
			        		SerialPort.STOPBITS_1,
			        		SerialPort.PARITY_NONE)) {
				closePort();
				logger.error("setParams fail");
				return false;
			}
		} catch (SerialPortException e) {
			closePort();
			e.printStackTrace();
			logger.error("openPort SerialPortException: "+e.getMessage());
			return false;
		}
		
		logger.info("End");
		return true;
	}
	
	private boolean closePort() {
		logger.info("Start");
		if (mPort == null || !mPort.isOpened()) {
			mPort = null;
			logger.error("mPort obj null or opened fail");
			return false;
		}
		
		try {
			mPort.closePort();
		} catch (SerialPortException e) {
			e.printStackTrace();
			logger.error("closet Port SerialPortException:"+e.getMessage());
		}
		mPort = null;
		
		logger.info("End");
		return true;
	}
	
	private boolean write(byte[] bytes) {
		
		logger.info("Start");
		if (mPort == null || !mPort.isOpened() || 
			bytes == null || bytes.length <= 0) {
			logger.error("mPort obj something wrong");
			return false;
		}
		
		try {
			return mPort.writeBytes(bytes);
		} catch (SerialPortException e) {
			e.printStackTrace();
			logger.error("write SerialPortException:"+e.getMessage());
		}
		
		logger.info("End");
		return false;
	}
	
	private byte[] read(int byteCount, int timeOut) {
		
		logger.info("Start");
		if (mPort == null || !mPort.isOpened()) {
			logger.error("mPort obj something wrong");
			return null;
		}
	
		try {
			return mPort.readBytes(byteCount, timeOut);
		} catch (SerialPortException | SerialPortTimeoutException e) {
			logger.error("read SerialPortException:"+e.getMessage());
			e.printStackTrace();
			
		}
		
		logger.info("End");
		return null;
	}
	
	
	
	public byte[] apduSendRecv(byte[] sendBuffer) {
		// TODO Auto-generated method stub
		
		logger.info("Start");
				
    	int totalRecvLen;
    	
		
    	byte[] recvDataBody;
    	byte[] recvLen;
    	byte[] recvBuffer;
    	
    	
    	try {    		
    		if(initOk == false)
    			if(init()!=RespCode.SUCCESS.getId()) 
    			{
    				logger.error("init() fail");
    				return null;
    			}
    		if(!openPort(portName))
		    {
		    	logger.error("openPort:("+portName+") fail");
		    	return null;
		    	//return RespCode.COMPORT_OPEN_FAIL.getId();
		    }
    		
    		logger.debug("pack APDU command:"+DataFormat.hex2StringLog(sendBuffer));
    		boolean ret= write(sendBuffer);//Write data to port
    		if(ret == false){
    			logger.error("portName:"+portName+", Write apdu data fail");
    			return null;
    		}
	
    		recvLen = read(3,2000);//2sec to read data len
    		
    		logger.debug("read finish:"+DataFormat.hex2StringLog(recvLen));
    		if(recvLen==null) {
    			logger.error("null len");
    			return null;
    		}
    		    		
    		totalRecvLen = (recvLen[2]&0xFF) + ((recvLen[1] << 8)&0xFF00) + ((recvLen[0] << 16)&0xFF0000);
    		logger.info("read body len:"+totalRecvLen);
    		
    		//expectedRecvLen = DataFormat.byteArrayToInt(recvLen);
    		recvDataBody = read(totalRecvLen+1, 5000);//5sec to read dataBody and LRC
    		
    		recvBuffer = ArrayUtils.addAll(recvLen, recvDataBody);
    		
    	
    		logger.debug("recv from Comport total Byte: "+recvBuffer.length);
			
			
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error(e.getMessage());
			return null;
		}
    	
    	logger.info("End");
    	return recvBuffer;
    }

}

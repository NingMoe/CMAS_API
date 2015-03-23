package CMAS;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ConfigManager {

	static Logger logger = Logger.getLogger(ConfigManager.class);
	private ArrayList<Properties> cfgList = null;
	
	public static final String ROOT_DIR="config/";
	public static final String EASYCARD_API_FILE = "EasycardAPI.properties"; 
	public static final String TXN_INFO_FILE = "TxnInfo.properties";
	public static final String HOST_DEVE_INFO_FILE = "HostDeve.properties";
	public static final String HOST_TEST_INFO_FILE = "HostTest.properties";
	public static final String HOST_PROD_INFO_FILE = "HostProduction.properties";
	public static final String USER_DEFINITION_FILE = "UserDefinition.properties";
	public static final String CARD_NUMBER_BLACKLIST = "CardNumber.black";
	public static final String CA_CERT = "CA.cer";
	public static final String API_JAR = "EasyCardApi.jar";
	
	public enum ConfigOrder
	{
		EASYCARD_API,
		TXN_INFO,
		HOST_INFO,
		USER_DEF,
	}
	
	public ConfigManager(){
		logger.info("Start");
		
		logger.info("End");
	}
	
	public ArrayList<Properties> prepareConfig()
	{
		logger.info("Start");
		cfgList = new ArrayList<Properties>();
		Properties easyCardApip= new Properties();
		Properties txnInfo = new Properties();
		Properties hostInfo = new Properties();
		Properties userDef = new Properties();
		
		String filename=null;
		try {
			//txnInfo.load(ConfigManager.class.getResourceAsStream("../../config/TxnInfo.properties"));	
		
			int len = ConfigOrder.values().length;
			for(int i=0; i<len; i++)
			{
				ConfigOrder c = ConfigOrder.values()[i];
				switch(c)
				{
					case EASYCARD_API:
						easyCardApip.load(ConfigManager.class.getClassLoader().getResourceAsStream(ConfigManager.EASYCARD_API_FILE));
						cfgList.add(easyCardApip);
						break;
					case TXN_INFO:
						txnInfo.load(ConfigManager.class.getClassLoader().getResourceAsStream(ConfigManager.TXN_INFO_FILE));
						cfgList.add(txnInfo);
						break;				
					case HOST_INFO:	
						if(easyCardApip.getProperty("Environment").equalsIgnoreCase("0")) {
							logger.info("Develop Environment");
							filename = ConfigManager.HOST_DEVE_INFO_FILE;
						}
						else if(easyCardApip.getProperty("Environment").equalsIgnoreCase("1")) {
							logger.info("Test Environment");
							filename = ConfigManager.HOST_TEST_INFO_FILE;
						}
						else{
							logger.info("Production Environment");
							filename = ConfigManager.HOST_PROD_INFO_FILE;
						}
						hostInfo.load(ConfigManager.class.getClassLoader().getResourceAsStream(filename));
						cfgList.add(hostInfo);
						break;	
					
					case USER_DEF:
						userDef.load(ConfigManager.class.getClassLoader().getResourceAsStream(ConfigManager.USER_DEFINITION_FILE));
						cfgList.add(userDef);
						break;
					default:
						logger.error("oh! maybe some Properties forgot to add2Arraylist");
						break;
				}
				
			}
			
		} 
		catch(IllegalArgumentException e)
		{			
			logger.error("IllegalArgumentException:"+e.getMessage());
			e.printStackTrace();
		}
		catch (FileNotFoundException e)
		{
			logger.error("FileNotFoundException:"+e.getMessage());
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block		
			logger.error("IOException:"+e.getMessage());
			e.printStackTrace();
		}
		catch (NullPointerException e)
		{	
			logger.error("NullPointerException:"+e.getMessage());
			e.printStackTrace();
		}
		catch (Exception e)
		{		
			logger.error("Exception:"+e.getMessage());
			e.printStackTrace();			
		}
		
		logger.info("End");
		return cfgList;
	}
	
	public void saveConfig(){
		
		Properties p = cfgList.get(ConfigOrder.TXN_INFO.ordinal());
		//save TxnInfo pro.
		try{
			File f = new File(ConfigManager.ROOT_DIR+ConfigManager.TXN_INFO_FILE);
			FileOutputStream os;			
			os = new FileOutputStream(f);			 		
			p.store(os, null);			
			os.close();
		}
		catch(NullPointerException ne)
		{
			logger.error(ne.getMessage());
		}
		catch (FileNotFoundException fe) {
			// TODO Auto-generated catch block
			logger.error(fe.getMessage());
		}
		catch (IOException ioe )
		{
			logger.error(ioe.getMessage());
		}		
	}
	
}

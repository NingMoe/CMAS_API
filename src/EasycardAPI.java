import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import CMAS.Process;
import ErrorMessage.IRespCode;




public class EasycardAPI {
	static Logger logger = Logger.getLogger(EasycardAPI.class);
	
	
	
	
	public EasycardAPI() //throws FileNotFoundException, IOException
	{
		logger.info("Start");
		apiInit();
		logger.info("End");
	}
	
	private static boolean apiInit(){
		
		try{
			//setting log config properties
			Properties logp = new Properties();	
			//logp.load(EasycardAPI.class.getResourceAsStream("../config/log4j.properties"));
			logp.load(EasycardAPI.class.getClassLoader().getResourceAsStream("log4j.properties"));

			
			
			//logp.load(new FileInputStream(EasycardAPI.class.getResource("/").getPath() +  "Config/log4.properties"));
			//String path= this.getClass().getResource("/").getPath()+ Config.PATH.Config+ "log4j.properties";
			
			//logp.load(new FileInputStream(path));
			//PropertyConfigurator.configure(logp);
			
			
		}catch(Exception e){
			
			logger.debug("init fail:"+e.getMessage());
			return false;
		}
		
		return true;
	}
	

	public static void cmasSignOn()
	{	
		try{
		
			logger.info("Start");
			
			IRespCode result;
			Process process = new Process();
			result = process.doSignon();
			
			logger.info("Do_Signon() result:"+result.getId()+":"+result.getMsg()); 
			
			logger.info("End");
		}catch(Exception e){
			e.getStackTrace();		
			logger.error(e.getMessage());	
		}
	}
	

	public static void readCardNumber(){	
		try{
		
			logger.info("Start");		
			//Process process = new Process(rs);
			logger.info("End");
		
	
		}catch(Exception e){
			logger.error(e.getMessage());
		}
	}

	public  static void main(String args[]) {	
	     
		logger.info("********** App Start **********");
			
		
		
		apiInit();
	    cmasSignOn();
  
	     logger.info("End");
	}
	
}

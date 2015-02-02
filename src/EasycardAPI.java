import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;








import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import Process.Process;
import Process.Process_Signon;
import Reader.RecvSender;
import TransData.BatchInfo;
import TransData.TMINFO;
import CMAS.CMAS;
import CMAS.CMASTAG;
import CMAS.SSLClient;
import CMAS.TransFormat;




public class EasycardAPI {
	static Logger logger = Logger.getLogger(EasycardAPI.class);
	
	
	
	RecvSender rs = null;
	public EasycardAPI() //throws FileNotFoundException, IOException
	{
		apiInit();
		if(rs==null)
			rs = new RecvSender();		
	}
	
	public EasycardAPI(RecvSender posRs)
	{
		apiInit();
		rs = posRs;
	}
	
	private boolean apiInit(){
		
		try{
			//setting log config properties
			Properties logp = new Properties();	
		//	logp.load(EasycardAPI.class.getResourceAsStream("log4j.properties"));
			//logp.load(new FileInputStream(EasycardAPI.class.getResource("/").getPath() +  "Config/log4.properties"));
			String path= this.getClass().getResource("/").getPath()+ Config.PATH.Config+ "log4j.properties";
			
			logp.load(new FileInputStream(path));
			PropertyConfigurator.configure(logp);
			logger.info("********** App Start **********");
			
		}catch(Exception e){
			
			logger.debug("init fail:"+e.getMessage());
			return false;
		}
		
		return true;
	}
	

	public static  void Do_Signon()
	{	
		try{
		
			//reset
			Process_Signon  p = new Process_Signon();
			TMINFO tm=new TMINFO();
			p.Start(tm);
						
	
		}catch(IOException e){
		logger.error(e.getMessage());	
		}
	}
	

	public static void readCardNumber(){	
		try{
		
			logger.info("Start");		
			Process process = new Process(rs);
			logger.info("End");
		
	
		}catch(Exception e){
			logger.error(e.getMessage());
		}
	}

	public  static void main(String args[]) {	
	     
		logger.info("Start");
		System.out.print("Start"); 
	 	Properties logp = new Properties();	
		//	logp.load(EasycardAPI.class.getResourceAsStream("log4j.properties"));
			//logp.load(new FileInputStream(EasycardAPI.class.getResource("/").getPath() +  "Config/log4.properties"));
			String path= EasycardAPI.class.getResource("/").getPath()+ Config.PATH.Config+ "log4j.properties";
			
			logp.load(new FileInputStream(path));
			PropertyConfigurator.configure(logp);
			logger.info("********** App Start **********");
			
	     Do_Signon();
  
	     logger.info("End");
	}
	
}


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import CMAS.CMAS;
import Reader.EZReader;


import org.apache.log4j.*;

public class EasycardAPI {

	static Logger logger = Logger.getLogger(EasycardAPI.class);
	

	
	public static void main(String Args[]) throws FileNotFoundException, IOException
	{
		try{
			//setting log config properties
			Properties logp = new Properties();			
			logp.load(CMAS.class.getClassLoader().getResourceAsStream("log4j.properties"));
			PropertyConfigurator.configure(logp);
			logger.info("********** App Start **********");
			
			//reset
			EZReader reader = EZReader.getInstance();
			reader.setPortName("COM6");
			reader.setMerchantId("10001");
			reader.setMerchantSTCode("100011");
			reader.exeReset();
			
			
			
						
		}catch(IOException e)
		{
			logger.error(e.getMessage());			
		}

		/*
		Properties  properties = new Properties();
		 String path=EasycardAPI.class.getResource("/").getPath() +  "EasycardAPI.properties";
         properties.load(new FileInputStream(path));
	  
         CMAS host=new CMAS();
         host.LoadTransFormatTable("SG_Q");
		
*/


	}

}


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import CMAS.CMAS;


public class EasycardAPI {


	
	public static void main(String Args[]) throws FileNotFoundException, IOException
	{
		
		
	
		 Properties  properties = new Properties();
		 String path=EasycardAPI.class.getResource("/").getPath() +  "EasycardAPI.properties";
         properties.load(new FileInputStream(path));
	  
         CMAS host=new CMAS();
         host.LoadTransFormatTable("SG_Q");
		//			XMLReader xmlreader		=new XMLReader("f:\\test.xml");


         ////////////////////////////////////////////////////////
		 //  READER 
		 /////////////////////////////////////////////////////
	/*
         String ReaderPort=    properties.getProperty("ReaderPort");
		 READER dongle =new READER(ReaderPort );
		 String Merchant_STCODE= properties.getProperty("Merchant_STCODE");
		 String Merchant_LocationID= properties.getProperty("Merchant_LocationID");
		 String Merchant_ID =properties.getProperty("Merchant_ID");
		 String TM_ID= properties.getProperty("TM_ID");
		 String TM_AgentNumber= properties.getProperty("TM_AgentNumber");
		 String TM_SerialNo= properties.getProperty("TM_SerialNo");
		 
		 SetReaderparameter( Merchant_STCODE,Merchant_LocationID,Merchant_ID,TM_ID,TM_AgentNumber,TM_SerialNo);
		 dongle.Reset();
		 
		 */
		 
	//	if( dongle.APDU.SW==0x9000);
	//	SSLClient sslclient;
//		try {
//			sslclient = new SSLClient();	
//			sslclient.connect();
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}

	}

}

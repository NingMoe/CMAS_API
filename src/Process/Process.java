package Process;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import CMAS.SSLClient;
import Reader.EZReader;

public class Process {

	
	 Properties  Process_properties;
	 Properties Config_properties;
	 Date TransDateTime ;
	 
	 
	 
	
	public Process() throws FileNotFoundException, IOException
	{
		 Config_properties= new Properties();
		 Process_properties= new Properties();
		 Process_properties.load(new FileInputStream(SSLClient.class.getResource("/").getPath() +  "process.properties"));
	}
	public void GetTransDataTime()
	{
		TransDateTime = new Date( );
		
	
	}
	public void SignOn()
	{
		EZReader reader = EZReader.getInstance();
		reader.exeReset();
		
		 String ReaderPort=    Process_properties.getProperty("ReaderPort");
		 //Dongle =new V2Command(ReaderPort );
		 
		 String Merchant_STCODE= Process_properties.getProperty("Merchant_STCODE");
		 String Merchant_LocationID= Process_properties.getProperty("Merchant_LocationID");
		 String Merchant_ID =Process_properties.getProperty("Merchant_ID");
		 String TM_ID= Process_properties.getProperty("TM_ID");
		 String TM_AgentNumber= Process_properties.getProperty("TM_AgentNumber");
		 String TM_SerialNo= Process_properties.getProperty("TM_SerialNo");
		 
		 TransDateTime = new Date( );
		
	    
		// Dongle.ResetCmd.gotTag5566();
	     //Dongle.SetReaderparameter( Merchant_STCODE,Merchant_LocationID,Merchant_ID,TM_ID,TM_AgentNumber,TM_SerialNo,TransDateTime);
		// Dongle.Reset();
		 
	}
}


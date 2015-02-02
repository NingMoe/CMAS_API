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

import Reader.RecvSender;
import TransData.TransactionData;

public class Process {

	 
	 Properties  Process_properties;
	 Properties Config_properties;
	 TransactionData TransData;
	 Date TransDateTime ;
	
	 RecvSender recvSender=null; 
		public Process() throws FileNotFoundException, IOException
		{
	
			 Config_properties= new Properties();
			 String Path_EasycardAPI_properties=this.getClass().getResource("/").getPath() +  "./EasycardAPI.properties";
			 Config_properties.load(new FileInputStream(Path_EasycardAPI_properties));
			 Process_properties= new Properties();
			 Process_properties.load(new FileInputStream(SSLClient.class.getResource("/").getPath() + Config.PATH.Process_Properties));
		

			 if(recvSender==null)		
				 recvSender = new RecvSender();

		}
	
		public Process(RecvSender rs)
		{
			recvSender = rs;
		}

	public void Init()
	{
		String newDeviceid=Process_properties.getProperty("NewDeviceID");
		if( newDeviceid.isEmpty() ){
				
		}
	}

}


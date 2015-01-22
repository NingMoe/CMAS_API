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

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import Reader.PPR_Reset.OneDayQuotaFlagForMicroPayment;
import Reader.PPR_Reset.OneDayQuotaWriteForMicroPayment;

import CMAS.SSLClient;

import Reader.EZReader;
import Reader.PPR_Reset;
import Reader.RecvSender;
import Utilities.DataFormat;

public class Process {

	
	 Properties  Process_properties;
	 Properties Config_properties;
	 
	 static Logger logger = Logger.getLogger(Process.class); 
	 
	 private RecvSender recvSender=null; 
	public Process() throws FileNotFoundException, IOException
	{
		//if null, used default RecvSender, control PC port by easyCard developer
		 if(recvSender==null)		
			 recvSender = new RecvSender();

	}
	/*
	 * send and receive data was controlled by POS vendor 
	 */
	public Process(RecvSender rs)
	{
		recvSender = rs;
	}
	
	
	public void signOn()
	{
		int sUnixTimeStamp = 0;
		int sSerialNumber = 9302;
		String scTimeZone = "Asia/Taipei";
		
		EZReader reader = new EZReader(recvSender);
		
		PPR_Reset req = new PPR_Reset();
		req.SetOffline(false);

		req.SetReq_NewLocationID((short) 1);

		req.SetReq_TMLocationID("0000100011");

		req.SetReq_TMID("0");

		sUnixTimeStamp = (int) (System.currentTimeMillis() / 1000L);

		req.SetReq_TMTXNDateTime(sUnixTimeStamp);

		req.SetReq_TMSerialNumber(sSerialNumber);

		req.SetReq_TMAgentNumber("0");

		req.SetReq_TXNDateTime(sUnixTimeStamp, scTimeZone);

		req.SetReq_SAMSlotControlFlag(true, 1);

		req.SetReq_OneDayQuotaWriteForMicroPayment(OneDayQuotaWriteForMicroPayment.WNWC);

		req.SetReq_CheckEVFlagForMifareOnly(true);

		req.SetReq_MerchantLimitUse(true);

		req.SetReq_OneDayQuotaFlagForMicroPayment(OneDayQuotaFlagForMicroPayment.YCYA);

		req.SetReq_OnceQuotaFlagForMicroPayment(true);

		req.SetReq_PayOnBehalfFlag(true);

		req.SetReq_OneDayQuotaForMicroPayment((short) 2000);

		req.SetReq_OnceQuotaForMicroPayment((short) 500);
		
		reader.exeCommand(req);
		
		
		
		logger.info("exe "+req.getClass().getName()+" , result:"+String.format("%04X", req.GetRespCode()));
		
		logger.info("recv RAW data:"+DataFormat.hex2StringLog(req.GetRespond()));
		logger.info("getter:"+req.GetResp_OneDayQuotaWriteForMicroPayment());
		
		//CMAS SSL process
		//todo...
		
		//pprSignOn Command
		//todo...
		
		//finish signon
		//todo...
		
		/*
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
		 */
	}
}


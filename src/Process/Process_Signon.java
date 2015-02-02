package Process;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;








import java.util.Properties;

import CMAS.CMAS;
import CMAS.TransStatus;
import Reader.EZReader;
import Reader.PPR_Reset;

import Reader.RecvSender;
import Reader.PPR_Reset.OneDayQuotaFlagForMicroPayment;
import Reader.PPR_Reset.OneDayQuotaWriteForMicroPayment;
import TransData.TMINFO;
import TransData.TransactionData;

public class Process_Signon extends Process{
	// private RecvSender recvSender=null; 

	public Process_Signon() throws FileNotFoundException, IOException {
		super();
		
	}


	public void Start(TMINFO tm) throws FileNotFoundException, IOException
	{
		
		TransData=new TransactionData(Process_properties, Config_properties,TXTYPE.SIGNON ,tm);
	
		String scTimeZone = "Asia/Taipei";
		
		EZReader reader = new EZReader(recvSender);
		
		PPR_Reset req = new PPR_Reset();
		req.SetOffline(false);


		
		
		
		req.SetReq_TMID(TransData.TM.GetPOSID());
		//int iUnixTimeStamp=(int) (System.currentTimeMillis() / 1000L);
		//long lUnixTimeStamp =TransData.DateTxnDateTime.getTime(); 
	//	iUnixTimeStamp=(int)lUnixTimeStamp/1000;
	
		req.SetReq_TMTXNDateTime(TransData.TM.GetTxTime());
		req.SetReq_TMSerialNumber(Integer.parseInt(TransData.TM.GetTMSN()));
		req.SetReq_TMAgentNumber(TransData.TM.GetAgentID());
		
		///////////////////////////////////////////////////////////////////////////
		req.SetReq_TMLocationID(String.valueOf(TransData.iSTCode) );
		req.SetReq_NewLocationID((short) TransData.iSubMerchantID);
		req.SetReq_TXNDateTime((int)TransData.DateTxnDateTime.getTime(), scTimeZone);
		req.SetReq_SAMSlotControlFlag(true, 1);
	
		
		reader.exeCommand(req);
		 TransData.PutResetData2TransData(req);
		 CMAS cmas=new CMAS(TransData);
		 cmas.SetRequestFilePath(Process_properties.getProperty("SendFilePath"));
		 cmas.SetResponseFilePath(Process_properties.getProperty("ReceiveFilePath"));
		 cmas.Build_CMASRequest();
		 
		 cmas.SSLSend_Receive();
		 
	}



}

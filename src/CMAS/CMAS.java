package CMAS;



import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;
import java.util.TimeZone;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Node;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;











import CMAS.*;
import CMAS.CMASTAG.DataXferControl;
import CMAS.CMASTAG.VersionInfo;
import CMAS.CMASTAG.VersionInfo_type;
import Process.TXTYPE;
import TransData.TransactionData;
import Utilities.DataFormat;
import Utilities.XMLReader;



/*
 *
 *
 * 
 *  
 *
 *
 *
 */
public class CMAS {
	
		TransactionData TransData;
		
		private String RequestFilePath=null;
		private String ResponseFilePath=null;
		private ReqDataCreator mReqData = null;
		private CMASResponse mRespObj = null;
		List<CMASTAG> list;
		public CMAS (TransactionData txdata )
		{
			  System.out.println("CMAS START"); 
			  SetTransData(txdata);
			  TransFormat FieldTable=new TransFormat();
			  list= FieldTable.Load(TransData.iTxType,TransData.iTxStatus);
			  
		}
		public void SetRequestFilePath(String path)
		{
			RequestFilePath=path;			
		}
		public void SetResponseFilePath(String path){
			ResponseFilePath=path;
		}
		void SetTransData(TransactionData txdata)
		{
			  TransData=txdata;
		}	
		
		public void  main()  {

		}
	
		private void addElement(String s) throws IllegalArgumentException {
			if (s == null || s.length() == 0) {
				throw new IllegalArgumentException("Add element failed");
			}
			mReqData.AddDataElement(s, 0);
		}
		
		public void DumpElementsToFile() {
			mReqData.DumpElementsToFile("f:\\temp\\", "CmasReq");
		}
  public void SSLSend_Receive() throws FileNotFoundException, IOException
  {
	  SSLClient ssl=new SSLClient();
	  Properties properties = new Properties();
	  properties.load(new FileInputStream(this.getClass().getResource("/").getPath() + Config.PATH.EasycardAPI_Properties));
      String serverIP =properties.getProperty("ServerIP");
      String serverPort =properties.getProperty("ServerPort");
	  
      ssl.SendReceive(mReqData.GetReqData(),serverIP,serverPort);
  }
public void Build_CMASRequest()
	{

		mReqData = ReqDataCreator.sGetInstance();
		if (mReqData == null) {
			throw new IllegalArgumentException("Request Data = null");
		}
		mReqData.RemoveAllTransUnit();
		mReqData.AddTransUnit();

		String s="";
		for (CMASTAG tag : list) {   
			if(tag.TagName.equals("T5588")){
				 s=  tag.Build_Tag5588(TransData.SSLVER,TransData.BLCVER,TransData.APVER,TransData.ParaVER);
				 addElement(s);	
			}else{
				System.out.format("Tag name :%s",	tag.Name)   ;
				SetTagValue(tag);
				s = "<" + tag.GetTagName() + ">" + tag.GetValue()+ "</" + tag.GetTagName() + ">";
				addElement(s);				
			}			
		}
		DumpElementsToFile();
}
	
	
	public void UnFormat_CMASResponse()
	{
			
		
	}
	public void SetTagValue(CMASTAG tag){
			
		 String s = null ;
	    int itagnum =Integer.parseInt( tag.TagName.substring(1));
        switch(itagnum){
        case 100://message type
             s =  tag.BuildTag0100_MessageType(TransData.iTxType, TransData.iTxStatus);
          
             break;
        case 200: //Card ID
             s =  tag.BuildTag0200(TransData.bytesCardID);
          
             break;
        case 211: 
             
            	s=tag.BuildTag0211(TransData.bytesPID);
             break;
        case 212: 
        	s=tag.BuildTag0212(TransData.bBasicData_Autoloadflag);
             break;
        case 213:
           	s=tag.BuildTag0213(TransData.bBasicData_CardType);
             break;
        case 214:
           	s=tag.BuildTag0214(TransData.bBasicData_CardProfile);
             break;
        case 300:
           	s=tag.BuildTag0300_ProcessCode(TransData.iTxType,TransData.bMsgType, TransData.bSubType, TransData.bBasicData_CardProfile, TransData.iTxStatus);
             break;
        case 400: 
          	s=tag.BuildTag0400(TransData.iTxnAmt);
          
             break;
        case 403: 
          	s=tag.BuildTag0403(TransData.iDeductAMT);
             break;
        case 407: 
        	s=tag.BuildTag0407(TransData.iADDAMT);
             break;
        case 408: 
        	s=tag.BuildTag0408(TransData.iEVafterTxn);
             break;
        case 409: 
        	s=tag.BuildTag0409(TransData.iAutoloadAMT);
             break;
        case 410:
        	s=tag.BuildTag0410(TransData.iEVBeforeTxn);
             break;
        case 1100:
        	s=tag.BuildTag1100(TransData.iTerminalInvoiceNum);
             break;
        case 1101:
        	s=tag.BuildTag1101(Integer.parseInt(TransData.TM.GetTMSN()));
             break;
        case 1200: 
        	s=tag.BuildTag1200(TransData.DateTxnDateTime);
             break;
        case 1201: 
        	s=tag.BuildTag1201(TransData.TM.GetTxTimestr());
             break;
        case 1300: 
        	s=tag.BuildTag1300(TransData.DateTxnDateTime);
             break;
        case 1301:
        	s=tag.BuildTag1301(TransData.TM.GetTxDatestr());
             break;
        case 1400: 
          	s=tag.BuildTag1400(TransData.bytesCardExpiryDate);
             break;
        case 1402: 
           	s=tag.BuildTag1402(TransData.bytesCardVaildDate);
             break;
        case 1403: 
        	s=tag.BuildTag1403(TransData.bytesCardVaildDateAfterSet);
             break;
        case 3700: 
         	s=tag.BuildTag3700(TransData.DateTxnDateTime,TransData.iTerminalInvoiceNum);
             break;
        case 3701:
        	s=tag.BuildTag3701(TransData.TM.getTnxInvoiceNo());
        	break;    
        case 3800: 
        	break;
        case 3900: 
        	break;
        case 4100: 
        	s=tag.BuildTag4100(TransData.bytesCPUDeviceID);
             break;
        case 4101: 
        	s=tag.BuildTag4101(TransData.bytesDeviceID);
             break;
        case 4102: 
        	s=tag.BuildTag4102(TransData.sTerminalIP);
             break;
        case 4103: 
        	s=tag.BuildTag4103(TransData.sMechineID);
             break;
        case 4104:
        	s=tag.BuildTag4104(TransData.bytesReaderSN);
             break;
        case 4200:
        	s=tag.BuildTag4200(TransData.sCPUSPID);
             break;
        case 4210: 
        	s=tag.BuildTag4210(TransData.iSubMerchantID);
             break;
        case 4800: 
        	s=tag.BuildTag4800(TransData.iCPUPurseVersionNUM);
             break;
        case 4801:
        	s=tag.BuildTag4801(TransData.bytesCardAVRDATA);
             break;
        case 4802://ucCardAVRDATA
        	s=tag.BuildTag4802(TransData.iIssuerCode);
             break;
        case 4803: 
        	s=tag.BuildTag4803(TransData.iBankCode);
             break;
        case 4804: 
         	s=tag.BuildTag4804(TransData.iAreaCode);
             break;
        case 4805: 
          	s=tag.BuildTag4805(TransData.bytesCPUSubAreaCode);
             break;
        case 4806: 
          	s=tag.BuildTag4806(TransData.bytesProfileExpiryDate);
             break;
        case 4807:
        //  	s=tag.BuildTag4807(TransData.ucNEWProfileExpiryDate);
             break;
        case 4808:
          	s=tag.BuildTag4808(TransData.iCardTxnSN);
             break;
        case 4809: 
          	s=tag.BuildTag4809(TransData.iCPUTxnMode);
             break;
        case 4810: 
          	s=tag.BuildTag4810(TransData.iCPUTQ);
             break;
        case 4811://ulSNBeforeTxn
        	s=tag.BuildTag4811(TransData.iSNBeforeTxn);
             break;
        case 4812: 
        	s=tag.BuildTag4812(TransData.bytesCTC);
             break;
        case 4813: 
        	s=tag.BuildTag4813(TransData.iLoyaltyCounter);
             break;
        case 4814: 
        	s=tag.BuildTag4814(TransData.iDepositValue);
             break;
        case 4815: 
        
             break;
        case 4816: 
             
             break;
        case 4817:
             
             break;
        case 4818: 
        	s=tag.BuildTag4818(TransData.bytesANOTHEREV);
             break;
        case 4819:
        	s=tag.BuildTag4819(TransData.iLockReason);
             break;
        case 4820: 
        	s=tag.BuildTag4820(TransData.iHostSpecVersionNo);
             break;
        case 4821: 
             
        	s=tag.BuildTag4821(TransData.CardParameter.toString());
             break;
        case 4823: 
        	s=tag.BuildTag4823(TransData.iOneDayQuotaWrite);
             break;
        case 4824: 
        	s=tag.BuildTag4824(TransData.iCPDReadFlag);
             break;
 ///
             case 4825: 
        	s=tag.BuildTag4825(TransData.iCPUCreditBalanceChangeFlag);
             break;
        case 4826: 
           	s=tag.BuildTag4826(TransData.iChipIDLength);
             break;     
        case 4827: 
           	s=tag.BuildTag4827(TransData.CPUCardParameter.toString());
             break;
        case 4828: 
           	s=tag.BuildTag4828(TransData.MifareSettingData.toString());
             break;     
        case 4829: 
           	s=tag.BuildTag4829(TransData.CPUCardSettingPara.toString());
             break;
        case 5301: 
           	s=tag.BuildTag5301(TransData.bSAMKeyVersion);
             break;
        case 5302:
           	s=tag.BuildTag5302(TransData.CPUCardKeyInfo.toString());
             break;
        case 5303: 
           	s=tag.BuildTag5303(TransData.iCPUHashTYPE);
             break;
        case 5304: 
           	s=tag.BuildTag5304(TransData.iCPUHostadminKVN);
             break;
        case 5305: 
           	s=tag.BuildTag5305(TransData.iSigntureKeyKVN);
             break;
        case 5306: 
           	s=tag.BuildTag5306(TransData.bytesCPUEDC);
             break;
        case 5307: 
           	s=tag.BuildTag5307(TransData.bytesRSAM);
             break;
        case 5308:
           	s=tag.BuildTag5308(TransData.bytesRHOST);
             break;
        case 5361:
           	s=tag.BuildTag5361(TransData.bytesSAMID);
             break;
        case 5362:
           	s=tag.BuildTag5362(TransData.bytesSAMSN);
             break;
   ///
             case 5363:
            	  	s=tag.BuildTag5362(TransData.bytesSAMCRN);
            
             break;
        case 5364:
          	s=tag.BuildTag5364(TransData.CPUSAMINFOData.toString());
             
             break;
        case 5365:
        	s=tag.BuildTag5365(TransData.SAMtransInfoData.toString());	
        case 5366: 
          	s=tag.BuildTag5366(TransData.bytesSingleCreditTxnAmtLimit);
           
             break;
        case 5367:
          	s=tag.BuildTag5367(TransData.CSAMPARA.toString());
           
             break;
        case 5368: 
          	s=tag.BuildTag5368(TransData.bytesSTC);
           
             break;
        case 5369: 
          	s=tag.BuildTag5369(TransData.bSAMSignOnControlFlag);
     
             break;
        case 5370:
          	s=tag.BuildTag5370(TransData.CPULastSignonInfoData.toString());
             
             break;
        case 5371:
          	s=tag.BuildTag5371(TransData.bytesCPUSAMID);
         
             break;
        case 5501: 
          	s=tag.BuildTag5501(TransData.Batch.GetBatchSNStr());
        
             break;
        case 5503: 
          	s=tag.BuildTag5503(TransData.iSTCode);
       
             break;
        case 5504: 
          	s=tag.BuildTag5504(TransData.TM.GetPOSID());
          
             break;
        case 5509: 
          	s=tag.BuildTag5509(TransData.iEDCTYPE);
        
             break;
        case 5510: 
          	s=tag.BuildTag5510(TransData.TM.GetAgentID());
          
             break;
        case 5548: 
        	break;
        case 5550: 
        
             break;
        case 5581:
          	s=tag.BuildTag5581(TransData.bytesOrgTxnTerminalID);
            
             break;
        case 5582: 
          	s=tag.BuildTag5582(TransData.bytesOrgTxnRRN);
       
             break;
        case 5583: 
          	s=tag.BuildTag5583(TransData.bytesOrgTxnDate);
   
             break;
       
        case 5589: 
        // 未建functioon
             break;
        case 5590: 
             
             break;
        case 5591://Batch Settle
     
             break;
        case 5592: 
          	s=tag.Build_Tag5592(TransData.iSettle_TotleCnt);
      
             break;
        case 5595: 
        
             break;
        case 5596: 
          	
    		s = tag.Build_Tag5596(TransData.xferControl);
        	break;
        case 6000: 
          	s=tag.BuildTag6000(TransData.bytesReaderFWVersion);
        
             break;
        case 6001: 
          	s=tag.BuildTag6001(TransData.bytesReaderAVRDATA);
       
             break;
        case 6002:
          	s=tag.BuildTag6002(TransData.stTermHostInfo_t.toString());
        
             break;
        case 6003:
          	s=tag.BuildTag6003(TransData.stTermParaInfo_t.toString());
           
             break;
        case 6004: 
          	s=tag.BuildTag6004(TransData.iBLVersion);
          
             break;
        case 6400: 
          	s=tag.BuildTag6400(TransData.bytesSTAC);
         
             break;
        case 6401: 
          	s=tag.BuildTag6401(TransData.bytesHTAC);
    
             break;
        case 6402: 
          	s=tag.BuildTag6402(TransData.bytesCTAC);
       
             break;
        case 6403: 
          	s=tag.BuildTag5362(TransData.bytesMAC);
  
             break;
        case 6404: 
          	s=tag.BuildTag6404(TransData.bytesCPUMAC_HCrypto);
       
             break;
        case 6405: 
          	s=tag.BuildTag6405(TransData.bytesCPUSignature);
          
             break;
        case 6406:
          	s=tag.BuildTag6406(TransData.bytesCPUTermCrypto);
       
             break;
        case 6407: 
          	s=tag.BuildTag6407(TransData.bytesHostCrypto);
        
             break;
        case 6408:
          	s=tag.BuildTag6408(TransData.bytesSATOKEN);
           
             break;
        case 6409: 
          	s=tag.BuildTag6409(TransData.bytesHostToken);
             
             break;
   
        }
        tag.SetValue(s);
	}
  /*
     public void Build_CMASRequest(String TransStatus)
     {
    	 
			try{
				
				LoadTransFormatTable(TransStatus);
				
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		
			 // root elements
                Document doc = docBuilder.newDocument();
				doc.getDocumentElement().normalize();
		    	Element rootElement =  (Element) doc.createElement("TransXML");
		    	 doc.appendChild(rootElement);
		    	
		    	Element TransElement = doc.createElement("Trans");
		 		rootElement.appendChild(TransElement);
		   
		       {
				 		Element T0100 = doc.createElement("T0100");
				 		T0100.appendChild(doc.createTextNode("0800"));
				 		TransElement.appendChild(T0100);
			   }	
		       
		 		Element T0300 = doc.createElement("T0300");
		 		T0100.appendChild(doc.createTextNode("881999"));
		 		TransElement.appendChild(T0300);
		    	 
		 		Element T1100 = doc.createElement("T1100");
		 		String tx_sn=Process_properties.getProperty("CMAS_SerialNo").toString();
		 		T1100.appendChild(doc.createTextNode(tx_sn ));
		 		TransElement.appendChild(T1100);
		 		
		 		Element T1101 = doc.createElement("T1101");
		 		T1101.appendChild(doc.createTextNode( Process_properties.getProperty("TM_SerialNo").toString()));
		 		TransElement.appendChild(T1101);
		 		//Trans Time hhmmss
				Element T1200 = doc.createElement("T1200");
				SimpleDateFormat ftime =   new SimpleDateFormat ("hhmmss");
			    String  strTxTime=ftime.format(TransDateTime);
			   //t1201與t1200同值
			    Element T1201 = doc.createElement("T1201");
		 		T1201.appendChild(doc.createTextNode( Process_properties.getProperty(strTxTime).toString()));
		 		TransElement.appendChild(T1201);
		 	
		 		///Trans Date yyyyMMdd
		 		Element T1300 = doc.createElement("T1300");
				 ftime =   new SimpleDateFormat ("yyyyMMdd");
			    String  strTxDate=ftime.format(TransDateTime);
			   //t1301與t1300同值
			    Element T1301 = doc.createElement("T1301");
		 		T1301.appendChild(doc.createTextNode( strTxDate));
		 		TransElement.appendChild(T1301);
		 	
		 		
		 		Element T3700 = doc.createElement("T3700");
		 		String RRN=String.format("%s%s", strTxDate,tx_sn);
		 		T3700.appendChild(doc.createTextNode( RRN));
		 		TransElement.appendChild(T3700);
		 		
			
		 		Element T3701 = doc.createElement("T3701");
		 		String TM_INVOICENO=Process_properties.getProperty("TM_INVOICENO").toString();
		 		T3701.appendChild(doc.createTextNode( TM_INVOICENO));
		 		TransElement.appendChild(T3701);
		 		
		 		Element T4100 = doc.createElement("T4100");
		 		String CPUDEVICEID=DataFormat.convertToHexString(Dongle.Reset.outucCPUDeviceID);
		 		T4100.appendChild(doc.createTextNode( CPUDEVICEID));
		 		TransElement.appendChild(T4100);
		 		
				Element T4101 = doc.createElement("T4101");
		 		String DEVICEID=DataFormat.convertToHexString(Dongle.Reset.outucDeviceID);
		 		T4101.appendChild(doc.createTextNode( DEVICEID));
		 		TransElement.appendChild(T4101);
		 		
		 		///////////////////////////////////////////////////////
		 		// DEVICE IP &NAME
		 		/////////////////////////////////////////////////////
		 
		 		java.net.InetAddress i;
		 		String IP;
		 		String HostName;
				try { 	
					//////////// DEVICE IP ADDRESS
					Element T4102 = doc.createElement("T4102");
					i = java.net.InetAddress.getLocalHost();
					IP=i.getHostAddress();
				    T4102.appendChild(doc.createTextNode( IP));	
					TransElement.appendChild(T4102);
					//////////// DEVICE IP HOST NAME
					Element T4103 = doc.createElement("T4103");
					i = java.net.InetAddress.getLocalHost();
					HostName=i.getHostName();
					T4103.appendChild(doc.createTextNode( HostName));
					TransElement.appendChild(T4103);
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		 		///////////////////////////////////////////////////////
		 		//Reader ID
		 		/////////////////////////////////////////////////////
				Element T4104 = doc.createElement("T4104");
		 		String READERID=DataFormat.convertToHexString(Dongle.Reset.outucReaderID);
		 		T4104.appendChild(doc.createTextNode( READERID));
		 		TransElement.appendChild(T4104);
		 		
				///////////////////////////////////////////////////////
				//Merchant_ID
				/////////////////////////////////////////////////////
				Element T4200= doc.createElement("T4200");
				String Merchant_ID= Process_properties.getProperty("Merchant_ID");
				T4104.appendChild(doc.createTextNode( Merchant_ID));
				TransElement.appendChild(T4200);
				
				///////////////////////////////////////////////////////
				//T4210 New Location ID
				/////////////////////////////////////////////////////
				Element T4210= doc.createElement("T4210");
				String Merchant_LocationID= Process_properties.getProperty("Merchant_LocationID");
				T4104.appendChild(doc.createTextNode( Merchant_LocationID));
				TransElement.appendChild(T4210);
				
				///////////////////////////////////////////////////////
				//T4820 ucHostSpecVersionNo 
				/////////////////////////////////////////////////////
				Element T4820= doc.createElement("T4820");
				String ucHostSpecVersionNo= Integer.toHexString((int)Dongle.Reset.outucHostSpecVersionNo);
				T4104.appendChild(doc.createTextNode( ucHostSpecVersionNo));
				TransElement.appendChild(T4820);
				
				///////////////////////////////////////////////////////
				//T4823 CPU One Day quota write flag
				/////////////////////////////////////////////////////
				Element T4823= doc.createElement("T4823");
				String  OneDayquotawriteflag= Integer.toHexString((int)Dongle.Reset.outstSAMParameterInfo_t.bOneDayQuotaWrite2);
				T4104.appendChild(doc.createTextNode( OneDayquotawriteflag));
				TransElement.appendChild(T4823);
				
				///////////////////////////////////////////////////////
				//T4824 CPUCPD Read Flag
				/////////////////////////////////////////////////////
				Element T4824= doc.createElement("T4824");
				int OneDayQuotaFlag=Dongle.Reset.outstSAMParameterInfo_t.bOneDayQuotaFlag0|Dongle.Reset.outstSAMParameterInfo_t.bOneDayQuotaFlag1<<4;
				String  strOneDayQuotaFlag= Integer.toHexString(OneDayQuotaFlag);
				T4104.appendChild(doc.createTextNode( strOneDayQuotaFlag));
				TransElement.appendChild(T4824);
				
				
				///////////////////////////////////////////////////////
				//T5301  SAM KEY VERSION
				/////////////////////////////////////////////////////
				Element T5301= doc.createElement("T5301");
				String  strSAMKeyVersion=Integer.toHexString((int)Dongle.Reset.outucSAMKeyVersion);
				T4104.appendChild(doc.createTextNode( strSAMKeyVersion));
				TransElement.appendChild(T5301);
				
				
				///////////////////////////////////////////////////////
				//T5307  RSAM 
				/////////////////////////////////////////////////////
				Element T5307= doc.createElement("T5307");
				String  RSAM=DataFormat.convertToHexString(Dongle.Reset.outucRSAM);
				T5307.appendChild(doc.createTextNode( RSAM));
				TransElement.appendChild(T5307);
				
				///////////////////////////////////////////////////////
				//T5308  RHOST
				/////////////////////////////////////////////////////
				Element T5308= doc.createElement("T5308");
				String RHOST=DataFormat.convertToHexString(Dongle.Reset.outucRHOST);
				T5308.appendChild(doc.createTextNode( RHOST));
				TransElement.appendChild(T5308);
				
				///////////////////////////////////////////////////////
				//T5361 SAM ID
				/////////////////////////////////////////////////////
				Element T5361= doc.createElement("T5361");
				String SAMID=DataFormat.convertToHexString(Dongle.Reset.outucSAMID);
				T5361.appendChild(doc.createTextNode( SAMID));
				TransElement.appendChild(T5361);
				///////////////////////////////////////////////////////
				//T5362 SAM SN
				/////////////////////////////////////////////////////
				Element T5362= doc.createElement("T5362");
				String SAMSN=DataFormat.convertToHexString(Dongle.Reset.outucSAMSN);
				T5362.appendChild(doc.createTextNode( SAMSN));
				TransElement.appendChild(T5362);
				///////////////////////////////////////////////////////
				//T5363 SAM CRN
				/////////////////////////////////////////////////////
				Element T5363= doc.createElement("T5363");
				String SAMCRN=DataFormat.convertToHexString(Dongle.Reset.outucSAMCRN);
				T5363.appendChild(doc.createTextNode( SAMCRN));
				TransElement.appendChild(T5363);
				
				///////////////////////////////////////////////////////
				//T5364 CPUSAMINFO
				/////////////////////////////////////////////////////
				Element T5364= doc.createElement("T5364");
				String CPUSAMInfo =Integer.toHexString((int)Dongle.Reset.outucSAMVersion)
														+DataFormat.convertToHexString(Dongle.Reset.outucSAMUsageControl)
														+Integer.toHexString(Dongle.Reset.outucSAMAdminKVN)
														+Integer.toHexString(Dongle.Reset.outucSAMIssuerKVN)
														+DataFormat.convertToHexString(Dongle.Reset.outucTagListTable);
		
				T5364.appendChild(doc.createTextNode( CPUSAMInfo));
				TransElement.appendChild(T5364);
				///////////////////////////////////////////////////////
				//T5365 SAM transaction info
				/////////////////////////////////////////////////////
				Element T5365= doc.createElement("T5365");
				String  SAMtransactioninfo=DataFormat.convertToHexString(Dongle.Reset.outucAuthCreditLimit)+
																	 DataFormat.convertToHexString(Dongle.Reset.outucAuthCreditBalance)+	
																	 DataFormat.convertToHexString(Dongle.Reset.outucAuthCreditCumulative)+
																	 DataFormat.convertToHexString(Dongle.Reset.outucAuthCancelCreditCumulative) ;
				T5365.appendChild(doc.createTextNode( SAMtransactioninfo));
				TransElement.appendChild(T5365);
				
				///////////////////////////////////////////////////////
				//T5366 SAM single credit transaction amount limit
				/////////////////////////////////////////////////////
				Element T5366= doc.createElement("T5366");
				String SingleCreditTxnAmtLimit=DataFormat.convertToHexString(Dongle.Reset.outucSingleCreditTxnAmtLimit);
				T5366.appendChild(doc.createTextNode( SingleCreditTxnAmtLimit));
				TransElement.appendChild(T5366);
				
				///////////////////////////////////////////////////////
				//T5368 STC
				/////////////////////////////////////////////////////
				Element T5368= doc.createElement("T5368");
				String STC=DataFormat.convertToHexString(Dongle.Reset.outucSTC);
				T5368.appendChild(doc.createTextNode( SingleCreditTxnAmtLimit));
				TransElement.appendChild(T5368);
				
				///////////////////////////////////////////////////////
				//T5369 SAM SIGNON CONTROL FLAG
				/////////////////////////////////////////////////////
				Element T5369= doc.createElement("T5369");
				int SAMSignOnControlFlag=Dongle.Reset.outstSAMParameterInfo_t.bSAMSignOnControlFlag4|Dongle.Reset.outstSAMParameterInfo_t.bSAMSignOnControlFlag5<<4;
				String  strSAMSignOnControlFlag= Integer.toHexString(SAMSignOnControlFlag);
				T5369.appendChild(doc.createTextNode( strSAMSignOnControlFlag));
				TransElement.appendChild(T5369);
				
				///////////////////////////////////////////////////////
				//T5370 CPULastSignonInfoData
				/////////////////////////////////////////////////////
				Element T5370= doc.createElement("T5370");
				String LastSignOnInfo= Dongle.Reset.outstLastSignOnInfo_t.toString();
				T5370.appendChild(doc.createTextNode(LastSignOnInfo));
				TransElement.appendChild(T5370);
								
				///////////////////////////////////////////////////////
				//T5371 CPU SAM ID
				/////////////////////////////////////////////////////
				Element T5371= doc.createElement("T5371");
				String CPUSAMID=DataFormat.convertToHexString( Dongle.Reset.outucCPUSAMID);
				T5371.appendChild(doc.createTextNode(CPUSAMID));
				TransElement.appendChild(T5371);
				
				///////////////////////////////////////////////////////
				//T5501 BATCHNO
				/////////////////////////////////////////////////////
				Element T5501= doc.createElement("T5501");
				String BATCHNO= 
				T5371.appendChild(doc.createTextNode(CPUSAMID));
				TransElement.appendChild(T5371);
				
		 		// write the content into xml file
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File("C:\\Senddata.xml"));
		 
				// Output to console for testing
				// StreamResult result = new StreamResult(System.out);
		 
				transformer.transform(source, result);
		 
				System.out.println("File saved!");
		 
			  } catch (ParserConfigurationException pce) {
				pce.printStackTrace();
			  } catch (TransformerException tfe) {
				tfe.printStackTrace();
			  }
		
		}
*/
}

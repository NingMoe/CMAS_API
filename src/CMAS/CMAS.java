package CMAS;



import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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

import Utilities.XMLReader;


public class CMAS {
	
	
	 ArrayList <String> TagList; 
	public CMAS (){
		TagList=new ArrayList<String>();
		
	}
	public void  main()  {

//	     Element tag0100= (Element)TransFormat.doc.getElementsByTagName("T0100" ).item(0);
//	      String attributeValule=tag0100.getAttribute("SG_Q");
           	
	}
  public void  LoadTransFormatTable(String TransStatus)
  {
	  XMLReader TransFormat;
	   String path=CMAS.class.getResource("/").getPath() +  "resource/transformat.xml";
	  TransFormat =new XMLReader(path);
	  
	  Element TagDef=(Element) TransFormat.doc.getElementsByTagName("TAGDEF").item(0);
       NodeList tags=   (NodeList) TagDef.getChildNodes();
 //      int nodenum=tags.getLength();
      
       
   /*    
       for( Node node =(Node) TagDef.getFirstChild();node!=null;node=(Node) node.getNextSibling()){
    	   if(node.getNodeType()==Node.ELEMENT_NODE){
    		 
    		  System.out.println("Tag Name : " + node.getNodeName());
    		  System.out.println("Tag attribute : " +node.getAttributes().getNamedItem(TransStatus).getNodeValue());	  
    	   }
    
 	       
       }
       */
       
       

	   for(int i=0;i<tags.getLength();i++){
		   if(tags.item(i).getNodeType()==Node.ELEMENT_NODE){
			   	System.out.println("Tag Name : " + tags.item(i).getNodeName());
			   	System.out.println("Tag attribute : " +tags.item(i).getAttributes().getNamedItem(TransStatus).getNodeValue());	
				  if(    tags.item(i).getAttributes().getNamedItem(TransStatus).getNodeValue().equals("M")){
 			      		 TagList.add(tags.item(i).getNodeName());			  
				  }		  
			   	
		   }

		//  if(   tag.getAttribute(TransStatus)=="M" ||tag.getAttribute(TransStatus)=="C"){
		//	 TagList.add(Tags.getNodeName());			  
		//  }		  
		  
	   }
	   
		 int tagcount=	TagList.size();
		System.out.format("Tag size :%d",	TagList.size())  ;
		for(int i=0;i<tagcount;i++){
			
			System.out.format("Tag data :%s",	TagList.get(i))   ;
		}
		
  }
	/*
     public void Build_CMASRequest(String TransStatus)
     {
    	 
			try{
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		
			 // root elements
                Document doc = docBuilder.newDocument();
               
         
			 	
				doc.getDocumentElement().normalize();
		    	Element rootElement =  (Element) doc.createElement("TransXML");
		    	 doc.appendChild(rootElement);
		    	
		    	Element TransElement = doc.createElement("Trans");
		 		rootElement.appendChild(TransElement);
		       if( ((Element) TransFormat.doc.getElementsByTagName("T0100" )).getAttribute(TransStatus)=="M")
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

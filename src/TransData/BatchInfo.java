package TransData;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
 









import java.util.LinkedHashMap;
import java.util.Properties;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import CMAS.CMAS;
//import CMAS.SSLClient;
 

public class BatchInfo {
	    String filePath; 
	    int iBatchSN;
	    String  sBatchSN;
	    String  sDeviceID;
	    int iTotleCnt;
	    int iTotleAMT;
	    int iADDVALUENETCnt;
	    int iADDVALUENETAMT;
	    int iDEDUCTNETCnt;
	    int iDEDUCTNETAMT;
	    int iADDCnt;
	    int iADDAMT;
	    int iDEDUCTCnt;
	    int iDEDUCTAMT;
	    int iADDVOIDCnt;
	    int iADDVOIDAMT;	
	    int iDEDUCTVOIDCnt;
	    int iDEDUCTVOIDAMT;	
	    int iREFUNDCnt;
	    int iREFUNDAMT;
	    int iBLACKCARDCNT;  
	    int iAUTOLOADCnt;
	    int iAUTOLOADAMT;
	    int iAUTOLOADENABLECNT;
	    int iSALECARDCnt;
	    int iSALECARDAMT;
	    int iRETURNCARDCnt;
	    int iRETURNCARDAMT;    
	    int iSETVALUECNT;   
	    int[] iCntByProfile;
	    int[] iAMTByProfile;	
	    
	    Properties BatchInfo_properties;
	   
	    public BatchInfo(){
		//    filePath="Batch"+DeviceID+".Dat"; 	
	    	filePath="Batch.Dat"; 	
			GetBatchInfo();
		    
	    }
	    
	    
 public static boolean WriteFile(String str, String path, boolean append)
 { // �g��

	   try {

	   File file = new File(path);// �إ��ɮסA�ǳƼg��

	   BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, append), "utf8"));// �]�w��BIG5�榡

	   // �Ѽ�append�N��n���n�~��\�J���ɮפ�

	   writer.write(str); // �g�J�Ӧr��

	   writer.newLine(); // �g�J����

	   writer.close(); 

	   } catch (IOException e){

	   e.printStackTrace();

	   System.out.println(path + "�g�ɿ��~!!");return false;

	   }
	return true;
}
	  public void main(){
		  
	   }
//
	   void init()
	   {	
		   		sBatchSN="";
			    UpdateBatchSN();
			    sBatchSN=GetBatchSNStr();
			    iTotleCnt=0;
			    iTotleAMT=0;
			    iADDVALUENETCnt=0;
			    iADDVALUENETAMT=0;
			    iDEDUCTNETCnt=0;
			    iDEDUCTNETAMT=0;
			    iADDCnt=0;
			    iADDAMT=0;
			    iDEDUCTCnt=0;
			    iDEDUCTAMT=0;
			    SaveBatchInfo();
	   }
	   //�]�w��e�ϥγ]�ƽs��
	   //�妸�������O����۳]�ơA����ɻݰO���]�ƽs��
	   //�ðO���b�ɦW���A�ɦW�s�X Batch+Deviceid+.dat

	   void SetDeviceID(String devid)
	   {
		   sDeviceID=devid;
		   
	   }
		   //���o��e�妸��
	public   int GetBatchSN(int BatchSn)
	   {
		  
		   if(BatchSn<=0){
		    	iBatchSN=1;
		    }
		    
		    if(iBatchSN>=100){
		    	iBatchSN=1;
		    } 
		    sBatchSN=GetBatchSNStr();
		    return iBatchSN;
	   }
	   //���o�妸�Ǹ�   YYMMDD+00()��X�Ǹ�
	public   String GetBatchSNStr(){	   	
	   		Date now = new Date( );
		    SimpleDateFormat fDate =   new SimpleDateFormat ("YYMMDD");
		    String nowDate=fDate.format(now);
		    
		    	
		    sBatchSN=String.format("%s%02d", nowDate,iBatchSN);	
		    System.out.format("BatchSNSTR=%s", sBatchSN);
		    
		    return sBatchSN;
	   	}
	   	
	public	int UpdateBatchSN(){	   	
			
		    if(iBatchSN==0){
		    	iBatchSN++;
		    }
		    
		    if(iBatchSN>=100){
		    	iBatchSN=1;
		    } 
		    return iBatchSN;
		}
	    public  void GetBatchInfo() {
	    	
	    	try{
		           JSONParser parser = new JSONParser();
		    	    Object obj = parser.parse(new FileReader(filePath));
		            JSONObject jsonObject = (JSONObject) obj;
		          
		            String name = (String) jsonObject.get("name");
		       		System.out.println(name);
		        
		    	    iBatchSN =Integer.valueOf(((Long) jsonObject.get("iBatchSN")).intValue()) ;
		    	    sBatchSN = (String) jsonObject.get("sBatchSN");
		    	
		    	    sDeviceID=(String) jsonObject.get("sDeviceID");
		    	    iTotleCnt=Integer.valueOf(((Long) jsonObject.get("iTotleCnt")).intValue()) ;
		    	    iTotleAMT=Integer.valueOf(((Long) jsonObject.get("iTotleAMT")).intValue()) ;
		    	    iADDVALUENETCnt=Integer.valueOf(((Long) jsonObject.get("iADDVALUENETCnt")).intValue()) ;
		    	    iADDVALUENETAMT=Integer.valueOf(((Long) jsonObject.get("iADDVALUENETAMT")).intValue()) ;
		    	    iDEDUCTNETCnt=Integer.valueOf(((Long) jsonObject.get("iDEDUCTNETCnt")).intValue()) ;
		    	    iDEDUCTNETAMT=Integer.valueOf(((Long) jsonObject.get("iDEDUCTNETAMT")).intValue()) ;
		    	    iADDCnt=Integer.valueOf(((Long) jsonObject.get("iADDCnt")).intValue()) ;
		    	    iADDAMT=Integer.valueOf(((Long) jsonObject.get("iADDAMT")).intValue()) ;
		    	    iDEDUCTCnt=Integer.valueOf(((Long) jsonObject.get("iDEDUCTCnt")).intValue()) ;
		    	    iDEDUCTAMT=Integer.valueOf(((Long) jsonObject.get("iDEDUCTAMT")).intValue()) ;
		         
		            System.out.println("sDEVICEID: " + sDeviceID);
		            System.out.println("iTotleCnt: " + iTotleCnt);
		            System.out.println("iTotleAMT: " + iTotleAMT);
		            System.out.println("iADDVALUENETCnt: " + iADDVALUENETCnt);
		            System.out.println("iADDVALUENETAMT: " + iADDVALUENETAMT);
		            System.out.println("iDEDUCTNETCnt: " + iDEDUCTNETCnt);
		            System.out.println("iDEDUCTNETAMT: " + iDEDUCTNETAMT);
		            System.out.println("iADDCnt: " + iADDCnt);
		            System.out.println("iADDAMT: " + iADDAMT);
		            System.out.println("iDEDUCTCnt: " + iDEDUCTCnt);
		            System.out.println("iDEDUCTAMT: " + iDEDUCTAMT);
	           }catch (IOException e) {
	        	    init();//Ū�����ɮ�
	        	   
	           }catch (Exception e) {
	               e.printStackTrace();
	           }
	       }
	   public void SaveBatchInfo()
	   {
		 
		    File file = new File(filePath);
		    try {
		     	   if(!file.exists())
		       	   {
		    	       	 file.createNewFile();
		    	       	 JSONObject obj=new JSONObject();
		        	     obj.put("iBatchSN",iBatchSN);
		        	     obj.put("sBatchSN",new String(sBatchSN));
		        	  //   obj.put("sDEVICEID",new String(sDEVICEID));
		        	     obj.put("iTotleCnt",iTotleCnt);
		        	     obj.put("iTotleAMT",iTotleAMT);
		        	     obj.put("iADDVALUENETCnt",iADDVALUENETCnt);
		        	     obj.put("iADDVALUENETAMT",iADDVALUENETAMT);
		        	     obj.put("iDEDUCTNETCnt",iDEDUCTNETCnt);
		        	     obj.put("iDEDUCTNETAMT",iDEDUCTNETAMT);
		        	     obj.put("iADDCnt",iADDCnt);
		        	     obj.put("iADDAMT",iADDAMT);
		        	     obj.put("iDEDUCTCnt",iDEDUCTCnt);
		        	     obj.put("iDEDUCTAMT",iDEDUCTAMT);
		        	     String jsonText =  obj.toJSONString();  
		        	         
		        	     WriteFile(jsonText, filePath, false); 
		        	          
		        	      System.out.print(jsonText);
		        	   }     
		        	 } catch (IOException e) {
		        	           // TODO Auto-generated catch block
		        	           e.printStackTrace();
		        	 }
	   }
}

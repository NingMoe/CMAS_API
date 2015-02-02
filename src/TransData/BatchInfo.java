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
import CMAS.SSLClient;
 

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
 { // 寫檔

	   try {

	   File file = new File(path);// 建立檔案，準備寫檔

	   BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, append), "utf8"));// 設定為BIG5格式

	   // 參數append代表要不要繼續許入該檔案中

	   writer.write(str); // 寫入該字串

	   writer.newLine(); // 寫入換行

	   writer.close(); 

	   } catch (IOException e){

	   e.printStackTrace();

	   System.out.println(path + "寫檔錯誤!!");return false;

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
	   //設定當前使用設備編號
	   //批次交易交易記錄跟著設備，交易時需記錄設備編號
	   //並記錄在檔名中，檔名編碼 Batch+Deviceid+.dat

	   void SetDeviceID(String devid)
	   {
		   sDeviceID=devid;
		   
	   }
		   //取得當前批次號
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
	   //取得批次序號   YYMMDD+00()兩碼序號
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
	        	    init();//讀不到檔案
	        	   
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

package Reader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

import javax.xml.bind.DatatypeConverter;

import Utilities.DataFormat;




class APDUCmd{
	byte CLA;
	byte INS;
	byte P1;
	byte P2;
	byte Lc;
	byte Le;
	byte []CMDDATA;
	byte []RespDATA;
	
	int    SW;
	byte EDC;
	
	public APDUCmd(){}
	public APDUCmd(byte cla,byte ins,byte p1,byte p2,byte lc,byte le){
			CLA=cla;
			INS=ins;
			P1=p1;
			P2=p2;
			Lc=lc;
			CMDDATA=new byte[Lc];
		
			Le=le;
	}
	public APDUCmd(byte cla,byte ins,byte p1,byte p2,byte lc,byte[]data,byte le){
		CLA=cla;
		INS=ins;
		P1=p1;
		P2=p2;
		Lc=lc;
		CMDDATA=new byte[Lc];
		CMDDATA=data;
		Le=le;
}
public APDUCmd(byte cla,byte ins,byte p1,byte p2,byte le){
			CLA=cla;
			INS=ins;
			P1=p1;
			P2=p2;
			Le=le;
		    Lc=0;
	}
 
public byte checksum(int length,byte  data[])
{
byte chk= 0x00;
int i= 0x00;
 
for(i=0;i<length;i++)
chk^=data[i];
return chk;
}

public byte [] getCmdBytes(){
		byte []buf=new byte[256];
		int inCnt=0;
		buf[0]=0x00;buf[1]=0x00;
		inCnt=3;
		buf[inCnt++]=CLA;buf[inCnt++]=INS;
		
		buf[inCnt++]=P1;buf[inCnt++]=P2;
	
		if(Lc!=0)
		{	
			buf[inCnt++]=Lc;
		
		}
		
		if(Lc!=0){
			System.arraycopy(CMDDATA,0,buf,inCnt,Lc);
			inCnt+=Lc;
		}
		if(Le!=0){
		
			buf[inCnt++]=Le;
		
		}
		buf[2]=(byte) (inCnt-3);
		buf[inCnt++] = checksum(inCnt,buf);//EDC
		System.out.print( DatatypeConverter.printHexBinary(buf));
		return buf;
}

//////////////////////////////////////////////////////////////
	public boolean SetRespData(byte [] respdata){
		int len;
		if(respdata[0]==0x00&&respdata[1]==0)
			{
				//len=GetBytetoInt(respdata[2]);
		  	len=(char)(respdata[2] & 0xff);
				RespDATA=new byte[len];
				System.arraycopy(respdata,3, RespDATA, 0, len-2);
				System.out.println( DatatypeConverter.printHexBinary(RespDATA));
				SW=((respdata[2+len-2+1])&0xff)*256+(respdata[2+len-2+2]&0xff);
				System.out.println(SW);
			}
		return true;
			
		
	}
}

 class CMD_Reset extends APDUCmd
{
	  Date Transtime;
	  class  SAMParameter{
	        byte bCPDReadFlag0 ;
	        byte bCPDReadFlag1 ;
	        byte bOneDayQuotaWrite2 ;
	        byte bOneDayQuotaWrite3;
	        byte bSAMSignOnControlFlag4;
	        byte bSAMSignOnControlFlag5;
	        byte bCheckEVFlag6;
			byte bDeductLimitFlag7;
			byte bOneDayQuotaFlag0;
			byte bOneDayQuotaFlag1;
			byte bOnceQuotaFlag2;
			byte bCheckDeductFlag3;
			byte bRFU24 ;
			byte bRFU25 ;
			byte bRFU26 ;
			byte bRFU27 ;
			byte []ucOneDayQuota;
			byte []ucOnceQuota;
			byte []ucCheckDeductValue;
			byte ucAddQuotaFlag;
			byte []ucAddQuota;
			int datelen;
			public SAMParameter(byte[] data,	   int offset)
			{
			    int startpoint=offset;
				byte b=data[offset++];
				
				bCPDReadFlag0=							(byte) ((b >> 0) & 0x1);
				System.out.println(String.format("%x", bCPDReadFlag0));
				bCPDReadFlag1 =							(byte) ((b >> 1) & 0x1);
				System.out.println(String.format("%x", bCPDReadFlag1));
		        bOneDayQuotaWrite2=				(byte) ((b >> 2) & 0x1);
				System.out.println(String.format("%x", bOneDayQuotaWrite2));
		        bOneDayQuotaWrite3=				(byte) ((b >> 3) & 0x1);
				System.out.println(String.format("%x", bOneDayQuotaWrite3));
		        bSAMSignOnControlFlag4=		(byte) ((b >> 4) & 0x1);
				System.out.println(String.format("%x", bSAMSignOnControlFlag4));
		        bSAMSignOnControlFlag5=		(byte) ((b >> 5) & 0x1);
				System.out.println(String.format("%x", bSAMSignOnControlFlag5));
		        bCheckEVFlag6=							(byte) ((b >> 6) & 0x1);
				System.out.println(String.format("%x", bCheckEVFlag6));
			    bDeductLimitFlag7=						(byte) ((b >> 7) & 0x1);
				System.out.println(String.format("%x", bDeductLimitFlag7));
				
				
				 b=data[offset++];
				 bOneDayQuotaFlag0				=(byte) ((b >> 0) & 0x1);
					System.out.println(String.format("%x", bOneDayQuotaFlag0));
				 bOneDayQuotaFlag1				=(byte) ((b >> 1) & 0x1);
				 System.out.println(String.format("%x", bOneDayQuotaFlag1));
				 bOnceQuotaFlag2						=(byte) ((b >> 2) & 0x1);
				 System.out.println(String.format("%x", bOnceQuotaFlag2));
				 bCheckDeductFlag3					=(byte) ((b >> 3) & 0x1);
				 System.out.println(String.format("%x", bCheckDeductFlag3));
				 bRFU24											=(byte) ((b >> 4) & 0x1);
				 System.out.println(String.format("%x", bRFU24));
				 bRFU25										 	=(byte) ((b >> 5) & 0x1);
				 System.out.println(String.format("%x", bRFU25));
				 bRFU26 										=(byte) ((b >> 6) & 0x1);
				 System.out.println(String.format("%x", bRFU26));
				 bRFU27											=(byte) ((b >> 7) & 0x1);
				 System.out.println(String.format("%x", bRFU27));
				ucOneDayQuota=new byte[2];
				
				System.arraycopy(data, offset, ucOneDayQuota, 0,2);
		    	offset+=2;	
			
		    	ucOnceQuota=new byte[2];
				System.arraycopy(data, offset, ucOnceQuota, 0,2);
			    offset+=2;	
				
			    ucCheckDeductValue=new byte[2];
				System.arraycopy(data, offset, ucCheckDeductValue, 0,2);
			    offset+=2;	
			    
				 ucAddQuotaFlag=data[offset++];
				
				ucAddQuota=new byte[3];
				System.arraycopy(data, offset, ucAddQuota, 0,3);
			    offset+=3;	
			    datelen=offset-startpoint;
			}
			public int getDatalen()
			{
				return datelen;				
			}
	    } 
	  
	 class  LastSingOn_DATA{
		byte []ucPreCPUDeviceID;//[6];
		byte [] ucPreSTC;//[4];
		byte [] ucPreTxnDateTime;//[4];
		byte  ucPreCreditBalanceChangeFlag;
		byte [] ucPreConfirmCode;//[2];
		byte [] ucPreCACrypto;//[16];
		int datalen;
		public  LastSingOn_DATA(byte[] data,int offset)
		{
			int startpoint=offset;
			ucPreCPUDeviceID=new byte[6];//[6];
			System.arraycopy(data, offset, ucPreCPUDeviceID, 0,6);
	    	offset+=6;	
			byte [] ucPreSTC=new byte[4];//[4];
			System.arraycopy(data, offset, ucPreSTC, 0,4);
	    	offset+=4;	
			byte [] ucPreTxnDateTime=new byte[4];//[4];
			System.arraycopy(data, offset, ucPreTxnDateTime, 0,4);
	    	offset+=4;	
			ucPreCreditBalanceChangeFlag=data[offset++];
			ucPreConfirmCode=new byte[2];//[2];
			System.arraycopy(data, offset, ucPreConfirmCode, 0,2);
	    	offset+=2;	
			ucPreCACrypto=new byte[16] ;//[16];
			System.arraycopy(data, offset, ucPreCACrypto, 0,16);
		    offset+=16;	
		    datalen=offset-startpoint;
		}
		public int getDatalen()
		{
			return datalen;
		}
		public String toString(){
			String  string =DataFormat.convertToHexString(ucPreCPUDeviceID)+
					 DataFormat.convertToHexString(ucPreSTC)+	
					 DataFormat.convertToHexString(ucPreTxnDateTime)+
					 Integer.toHexString(ucPreCreditBalanceChangeFlag)+
					 DataFormat.convertToHexString(ucPreConfirmCode)+
					 DataFormat.convertToHexString(ucPreCACrypto) ;
			return string;
		}
	}
	 
	    String inTMLocationID;
	    String inTMID;
	    String inTMTxnDateTime;
	    String inTMSerialNumber;//[6];
	    String inTMAgentNumber;//[4];
	    String inTxnDateTime;//[4];
	    String inLocationID;//1;
	    String inCPULocationID;//[2];
	    String inSPID;//1
	    String inCPUSPID;//[3];
	    String inRFU1;//[5];
	    byte[] inSAMSlot;//[1];
	    String inRFU2;//[11]
	    
	
	        byte outucHostSpecVersionNo;//4820
	    	byte[] outucReaderID;//4104
	    	byte[] outucReaderFWVersion;//[6];//config   t6000
	    	byte[] outucSAMID;//[8];//5361
	    	byte[] outucSAMSN;//[4];//53662
	    	byte[] outucSAMCRN;//[8];//5663
	    	byte[] outucDeviceID;//[4];//4101
	    	byte outucSAMKeyVersion;//5301
	    	byte[] outucSTAC;//[8];//6400
	    	byte outucSAMVersion;//5364-1
	    	byte[] outucCPUSAMID;//[8];//5361 
	    	byte[] outucSAMUsageControl;//[3];//5364-2
	    	byte outucSAMAdminKVN;//5364-3
	    	byte outucSAMIssuerKVN;//5364-4
	    	byte[] outucAuthCreditLimit;//[3];//5365-1
	    	byte[] outucSingleCreditTxnAmtLimit;//[3];//5366
	    	byte[] outucAuthCreditBalance;//[3];//5365-2
	    	byte[] outucAuthCreditCumulative;//[3];//5365-3
	    	byte[] outucAuthCancelCreditCumulative;//[3];//5365-4
	    	byte[] outucCPUDeviceID;//[6];//4100
	    	byte[] outucTagListTable;//[40];//5364-5
	    	byte[] outucSAMIssuerSpecData;//[32];//5364-6
	    	byte[] outucSTC;//[4];//5368
	    	byte[] outucRSAM;//[8];//5307
	    	byte[] outucRHOST;//[8];//5308
	    	byte[] outucSATOKEN;//[16];//6408
	    	SAMParameter outstSAMParameterInfo_t;//12   6002 6003
	    	byte[] outucRemainderAddQuota;//[3];
	    	byte[] outucCancelCreditQuota;//[3];//6//03-3
	    	byte[] outucDeMAC;//[8];//6003-2
	    	byte[] outucLastTxnDateTime;//[4];//				?????
	        LastSingOn_DATA outstLastSignOnInfo_t;//5370
	        byte[] outucStatusCode;//[2];
	        
	        
	    public void UnpackResponseData(byte[] data)
	    {
	    	int offset=0;
	    	outucHostSpecVersionNo=data[offset];
	    	offset+=1;
	    	 outucReaderID=new byte[4];//4104
	    	System.arraycopy(data, offset, outucReaderID, 0, 4);
	    	offset+=4;
	    	 outucReaderFWVersion=new byte[6];//[6];//config   t6000
	       	System.arraycopy(data, offset, outucReaderFWVersion, 0, 6);
	    	offset+=6;
	    	 outucSAMID=new byte[8];;//[8];//5361
	      	System.arraycopy(data, offset, outucSAMID, 0,8);
	    	offset+=8;
	    	
	    	 outucSAMSN=new byte[4];;//[4];//53662
	    	System.arraycopy(data, offset, outucSAMSN, 0,4);
	    	offset+=4;
	    	
	    	 outucSAMCRN=new byte[8];;//[8];//5663
	    	System.arraycopy(data, offset, outucSAMCRN, 0,8);
	    	offset+=8;
	    	
	    	 outucDeviceID=new byte[4];;//[4];//4101
	    	System.arraycopy(data, offset, outucDeviceID, 0,4);
	    	offset+=4;
	    	
	    	outucSAMKeyVersion=data[offset++];
	    	
	    	 outucSTAC=new byte[8];//[8];//6400
	    	System.arraycopy(data, offset, outucSTAC, 0,8);
	    	offset+=8;
	    	
///////////////////////////////////////////////////////////
	    	outucSAMVersion=data[offset++];//5364-1
	    	
	    	 outucCPUSAMID=new byte[8];//[8];///5361
	    	System.arraycopy(data, offset, outucCPUSAMID, 0,8);
	    	offset+=8;
	    	
	    	 outucSAMUsageControl=new byte[3];;//[3];//5364-2
	    	System.arraycopy(data, offset, outucSAMUsageControl, 0,3);
	    	offset+=3;
	    	
	    	outucSAMAdminKVN=data[offset++];//5364-3
	    	
	    	outucSAMIssuerKVN=data[offset++];//5364-4
	    	
	    	 outucAuthCreditLimit=new byte[3];//[3];//5364-3
	    	System.arraycopy(data, offset, outucAuthCreditLimit, 0,3);
	    	offset+=3;
	    	
	    	 outucSingleCreditTxnAmtLimit=new byte[3];//[3];//5366
	    	System.arraycopy(data, offset, outucSingleCreditTxnAmtLimit, 0,3);
	    	offset+=3;
	    	
	    	 outucAuthCreditBalance=new byte[3];//[3];//5365-2
	    	System.arraycopy(data, offset, outucAuthCreditBalance, 0,3);
	    	offset+=3;
	    	 outucAuthCreditCumulative=new byte[3];//[3];//5365-3
	    	System.arraycopy(data, offset, outucAuthCreditCumulative, 0,3);
	    	offset+=3;
	    	 outucAuthCancelCreditCumulative=new byte[3];//[3];//5365-4
	    	System.arraycopy(data, offset, outucAuthCancelCreditCumulative, 0,3);
	    	offset+=3;
	    	 outucCPUDeviceID=new byte[6];//[6];//4100
	    	System.arraycopy(data, offset, outucCPUDeviceID, 0,6);
	    	offset+=6;
	    	 outucTagListTable=new byte[40];;//[40];//5364-5
	    	System.arraycopy(data, offset, outucTagListTable, 0,40);
	    	offset+=40;
	    	 outucSAMIssuerSpecData=new byte[32];//[32];//5364-6
	    	System.arraycopy(data, offset, outucSAMIssuerSpecData, 0,32);
	    	offset+=32;
	    	 outucSTC=new byte[4];;//[4];//5368
	    	System.arraycopy(data, offset, outucSTC, 0,4);
	    	offset+=4;
	    	
	    	 outucRSAM=new byte[8];//[8];//5307
	     	System.arraycopy(data, offset, outucRSAM, 0,8);
	    	offset+=8;
	    	 outucRHOST=new byte[8];//[8];//5308
	      	System.arraycopy(data, offset, outucRHOST, 0,8);
	    	offset+=8;
	    	
	    	 outucSATOKEN=new byte[16];//[16];//6408
	     	System.arraycopy(data, offset, outucSATOKEN, 0,16);
	    	offset+=16;
	    	
	    		
	    	SAMParameter outstSAMParameterInfo=new SAMParameter(data,offset);//12   6002 6003
	    	offset+=outstSAMParameterInfo.getDatalen();
	    	 outucRemainderAddQuota=new byte[3];//[3];
	    	System.arraycopy(data, offset, outucRemainderAddQuota, 0,3);
	    	offset+=3;
	    	 outucCancelCreditQuota=new byte[3];//[3];//6//03-3
	    	System.arraycopy(data, offset, outucCancelCreditQuota, 0,3);
	    	offset+=3;
	    	 outucDeMAC=new byte[8];//[8];//6003-2
	    	System.arraycopy(data, offset, outucDeMAC, 0,8);
	    	offset+=8;
	    	outucLastTxnDateTime=new byte[4];//[4];//				?????
	    	System.arraycopy(data, offset, outucDeMAC, 0,4);
	    	offset+=4;
	        LastSingOn_DATA outstLastSignOnInfo=new LastSingOn_DATA(data,offset);//5370
	        offset+=outstLastSignOnInfo.getDatalen();
	//        byte[] outucStatusCode=new byte[2];//[2];
	    	
	    	
	    }
	    
	    public int inBuildResetCommand( )
	    {
	   int ret;
	     
       byte LOCATION_ID;
       int NEW_LOCATION_ID;
       byte [] Data=new byte[128];
       int offset=0;
  	 
      
  
       String bTMLocationID=String.format("%010d",Integer.parseInt(inTMLocationID));
       System.arraycopy(bTMLocationID.getBytes(),0,CMDDATA,offset,10);
       offset+=10;
       String bTMID=String.format("%02d",Integer.parseInt( inTMID)); 
       System.arraycopy(bTMID.getBytes(),0,CMDDATA,offset,2);
       offset+=2;
      
      
       
       SimpleDateFormat ftime =   new SimpleDateFormat ("yyyyMMddhhmmss");
       inTMTxnDateTime=ftime.format(Transtime);
       System.arraycopy(inTMTxnDateTime.getBytes(),0,CMDDATA,offset,14);
       offset+=14;
       
      String bTMSerialNumber =String.format("%06d",Integer.parseInt(inTMSerialNumber )); //終端機(TM)交易序號  
  
       System.arraycopy(  bTMSerialNumber.getBytes(),0,CMDDATA,offset,6);
       offset+=6;
       
        inTMAgentNumber =String.format("%04d",Integer.parseInt(inTMAgentNumber)); //收銀員代號
       System.arraycopy(inTMAgentNumber.getBytes(),0,CMDDATA,offset,4);
       offset+=4;
       
       SimpleDateFormat fdate =   new SimpleDateFormat ("");//終端機交易時間
       int UnixTime = (int) (Transtime.getTime()/1000);
       inTxnDateTime=Integer.toHexString(UnixTime);
       System.arraycopy(inTxnDateTime.getBytes(),0,CMDDATA,offset,4);
       offset+=4;
       
       
      
       
      
       byte[] bCPULocationIDbyte =String.format("%02d",Integer.parseInt(  inCPULocationID)).getBytes();//二代分公司代號 
       
     //  byte[] chars =  sys.GetNodeValue("MERCHANT","NewLocationID").getBytes();
       inLocationID =Integer.toHexString((int) bCPULocationIDbyte[0]);//一代分公司代號
       CMDDATA[offset++]=(byte) inLocationID.charAt(0);
     
       System.arraycopy(bCPULocationIDbyte,0,CMDDATA,offset,2);
       offset+=2;//new locationonID
       
  
       
       CMDDATA[offset++]=0x00;// old spid
      

    //   inCPUSPID = sys.GetNodeValue("MERCHANT","MERCHANTID"); //特店代號
       
     
     // =String.format("%03x",Integer.parseInt(inCPUSPID)).getBytes();
       byte[] bCPUSPID =DataFormat.intToByteArray ( Integer.parseInt(inCPUSPID));

       System.arraycopy(bCPUSPID,0,CMDDATA,offset,3);
       offset+=3;
       offset+=1+2+2;//未使用之欄位 
   //    int i = Integer.parseInt(sys.GetNodeValue("DEVICE","SAMSLOT"));
       CMDDATA[offset]=0x11;//inSAMSlot[0]; //SAM SLOT
        offset+=11;
       return 0;
}
	
	    public CMD_Reset(String Merchant_STCODE,String Merchant_LocationID,String Merchant_ID,String TM_ID,String TM_AgentNumber,String TM_SerialNo,Date txtime) {
	    	  	
	    	
			super((byte)0x80,(byte)0x01, (byte)0x00,(byte)0x00,(byte)0x40,(byte)0xFA);
		// TODO Auto-generated constructor stub/////////////////////////////////////
			
			inTMLocationID=Merchant_STCODE;
	    	inCPULocationID=Merchant_LocationID;
	    	inCPUSPID=Merchant_ID;
	    	inTMID=TM_ID;
	    	inTMAgentNumber=TM_AgentNumber;
	    	inTMSerialNumber=TM_SerialNo;
	    	Transtime=txtime;
			inBuildResetCommand();
	    }
	
}


public class READER {
	public test value;
	
	CMD_Reset  Reset;
    static String  PortName;
    static SerialPort serialPort; 
  	static String       Merchant_ID;//特店代號
 	static String		Merchant_STCODE;//分店代號
 	static  String		Merchant_LocationID;//分公司代碼
 	static   String		TM_ID;//店號
 	static  String  		TM_SerialNo;//
 	static   String		TM_AgentNumber;//店員代號
 	Date Transtime;
     String GetPortName(){
    	 return PortName;
     }
     void SetPortName(String portname ){
    	  PortName=portname;
     }
     public void SetReaderparameter(String stcode,String marchant_location,String merchant_id,String tmid,String tm_agentnumber,String tm_serialno,Date DateTime)
     {
    	Merchant_ID=merchant_id;//特店代號
        Merchant_STCODE=new String(stcode);//分店代號
		Merchant_LocationID=new String(marchant_location);//分公司代碼
        TM_ID=new String(tmid);//店號
  		TM_SerialNo=new String(tm_serialno);//
        TM_AgentNumber=new String(tm_agentnumber);//店員代號
        Transtime=DateTime;
        return ;
     }
     public  READER(String PortName) {
    	 serialPort =new SerialPort(PortName);
    	
	 }
     
    private SerialPort SerialPort(String portName2) {
		// TODO Auto-generated method stub
		return null;
	}
    

    
	private int SendandReceiveAPDU()
    {
    	int incomedatacount;
    	byte []buffer=new byte[256];
    	try {
    		
		    	serialPort.openPort();	
		    	serialPort.setParams(SerialPort.BAUDRATE_115200, 
				SerialPort.DATABITS_8,
				SerialPort.STOPBITS_1,
				SerialPort.PARITY_NONE);
		    	boolean ret=serialPort.writeBytes(Reset.getCmdBytes());//Write data to port
		  
	/*	int mask = SerialPort.MASK_RXCHAR ;//Prepare mask
            serialPort.setEventsMask(mask);//Set mask
            serialPort.addEventListener(new SerialPortReader());//Add SerialPortEventListener
            */
		
			do{
			        incomedatacount=serialPort.getInputBufferBytesCount();
			 }while(incomedatacount<=0);

			
			 buffer =  serialPort.readBytes(incomedatacount);//Read 10 bytes from serial port
			 Reset.SetRespData(buffer);
	//		ResponseAPDU   RespCMD=new ResponseAPDU(buffer2);
	//		System.out.println(RespCMD.toString());   
	//System.out.format("Response SW: %x %x",RespCMD.getSW1(),RespCMD.getSW2());
		
		//	System.out.format("Response hash: %x ",RespCMD.hashCode());
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}//Open serial port
    	return 0;
    }
	
	
    public void Reset(){
    
    	Reset=new CMD_Reset( Merchant_STCODE, Merchant_LocationID, Merchant_ID, TM_ID, TM_AgentNumber,TM_SerialNo,Transtime);
    
    
    	if(SendandReceiveAPDU()==0){
				if(Reset.SW==0x9000)
					Reset.UnpackResponseData(Reset.RespDATA);
		}
	
		
    }

}

class test
{    	
	public int a;
	private int b;
}

package TransData;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

import CMAS.SSLClient;
import CMAS.TransStatus;
import CMAS.CMASTAG.DataXferControl;
import Process.TXTYPE;

import Reader.PPR_Reset;
import Reader.PPR_Reset.SAMSignOnControlFlag;
import Utilities.DataFormat;
import Utilities.Util;



public class TransactionData {//6002



	
public class TERMHOST{//6002
	byte ucOneDayQuotaFlag;//[2];
	byte[] ucOneDayQuota;//[4];
	byte ucOnceQuotaFlag;//[2];
	byte[] ucOnceQuota;//[4];
	byte ucCheckEVFlag;//[2];
	byte ucAddQuotaFlag;//[2];
	byte[] ucAddQuota;//[6];//
	byte ucCheckDeductFlag;//[2];
	byte[] ucCheckDeductValue;//[4];
	byte ucDeductLimitFlag;//[2];
	byte[] ucAPIVersion;//[8];
	byte[] ucRFU;//[10];
	TERMHOST()
	{
		ucOneDayQuota=new byte[2];
		ucOnceQuota=new byte[2];
		ucAddQuota=new byte[3];
		ucCheckDeductValue=new byte[2];
		ucAPIVersion=new byte[4];
		ucRFU=new byte[5];
		
	}
	  public void  SetucOneDayQuotaFlag(byte Data){
		  ucOneDayQuotaFlag=Data;
	  }
	  public void  SetucOneDayQuota(byte[] Data,int len){
		  ucOneDayQuota=Arrays.copyOf(Data, len);
	  }
	  public void  SetucOnceQuotaFlag(byte Data){
		  ucOnceQuotaFlag=Data;
	  }
	  public void  SetucOnceQuota(byte[] Data,int len){
		  ucOnceQuota=Arrays.copyOf(Data, len);
	  }
	
	  public void  SetucCheckEVFlag(byte Data){
		  ucCheckEVFlag=Data;
	  }
	  public void  SetucAddQuotaFlag(byte Data){
		  ucAddQuotaFlag=Data;
	  }
	  public void  SetucAddQuota(byte[] Data,int len){
		  ucAddQuota=Arrays.copyOf(Data, len);
	  }
	  public void  SetucCheckDeductFlag(byte Data){
		  ucCheckDeductFlag=Data;
	  }
	  public void  SetucCheckDeductValue(byte[] Data,int len){
		  ucCheckDeductValue=Arrays.copyOf(Data, len);
	  }
	  public void  SetucDeductLimitFlag(byte Data){
		  ucDeductLimitFlag=Data;
	  }
	  public void  SetucAPIVersion(byte[] Data,int len){
		  ucAPIVersion=Arrays.copyOf(Data, len);
	  }
	  public void  SetucRFU(byte[] Data,int len){
		  ucRFU=Arrays.copyOf(Data, len);
	  }
	
	public String toString(){
		String str=new String();
		str=String.format("%02x",ucOneDayQuotaFlag)+
				DataFormat.convertToHexString(ucOneDayQuota)+
				String.format("%02x",ucOnceQuotaFlag)+
				DataFormat.convertToHexString(ucOnceQuota)+
				String.format("%02x",ucCheckEVFlag)+
				String.format("%02x",ucAddQuotaFlag)+
				DataFormat.convertToHexString(ucAddQuota)+
				String.format("%02x",ucCheckDeductFlag)+
				DataFormat.convertToHexString(ucCheckDeductValue)+
				String.format("%02x",ucDeductLimitFlag)+
				DataFormat.convertToHexString(ucAPIVersion)+
				DataFormat.convertToHexString(ucRFU);
		
		return 	 str;
	}
 
}

public class CPUCARDKEY_INFO{ //5302
	byte ucAdminKeyKVN;//[1];
	byte ucCreditKeyKVN;//[1];
	byte ucDebitKeyKVN;//[1];
	CPUCARDKEY_INFO(){
		
	}
	  public void  SetucAdminKeyKVN(byte Data){
		  ucAdminKeyKVN=Data;
	  }
	  public void  SetucCreditKeyKVN(byte Data){
		  ucCreditKeyKVN=Data;
	  }
		
	  public void  SetucDebitKeyKVN(byte Data){
		  ucDebitKeyKVN=Data;
	  }
		  
	 public String toString()
	 {
		String str=new String();
		str=String.format("%02x",ucAdminKeyKVN)+String.format("%02x",ucCreditKeyKVN)+String.format("%02x",ucDebitKeyKVN);
					
		return 	 str;
	 }
	
}

public class TERMPARA{//6003
	byte[] ucRemainderAddQuota;//[3];
	byte[] ucDeMAC;//[8];
    byte[] ucCancelCreditQuota;//[3];
    byte[] ucRFU;//[18];
    TERMPARA(){
    	ucRemainderAddQuota=new byte[3];
    	ucDeMAC=new byte[8];
    	ucCancelCreditQuota=new byte[3];
    	ucRFU=new byte[18];
    }
    public void  SetucRemainderAddQuota(byte[] Data,int len){
    	ucRemainderAddQuota=Arrays.copyOf(Data, len);
    }
    
    public void  SetucDeMAC(byte[] Data,int len){
    	ucDeMAC=Arrays.copyOf(Data, len);
    }
    
    public void  SetucCancelCreditQuota(byte[] Data,int len){
    	ucCancelCreditQuota=Arrays.copyOf(Data, len);
    }
    
    public void  SetRFU(byte[] Data,int len){
    	ucRFU=Arrays.copyOf(Data, len);
    }
	public String toString(){
		String str=new String();
		str=DataFormat.convertToHexString(ucRemainderAddQuota)+
				DataFormat.convertToHexString(ucDeMAC)+
				DataFormat.convertToHexString(ucCancelCreditQuota)+
				DataFormat.convertToHexString(ucRFU);
		
		return 	 str;
	}
}

public class CPUSAMINFO{//5364
     byte ucSAMVersion;//[1];            
     byte[] SAMUsageControl;//l[3];
     byte ucSAMAdminKVN;//[1];
     byte SAMIssuerKVN;//[1];
     byte[] TagListtable;//[40];
     byte[] ucSAMIssuerSpecData;//[32];    
     CPUSAMINFO(){
    	   ucSAMVersion=0;//[2];    
    	   SAMUsageControl=new byte[3];//l[6];
    	   ucSAMAdminKVN=0;//[2];
    	   SAMIssuerKVN=0;//[2];
    	   TagListtable=new byte[40];//[80];
    	   ucSAMIssuerSpecData=new byte[32] ;//[64];    
     }
     public void  SetSAMVersion(byte Data){
    	 
    	 ucSAMVersion=Data;
     }
     public void  SetSAMUsageControl(byte[] Data,int len){
    	 SAMUsageControl=Arrays.copyOf(Data, len);
     }
     public void  SetSAMAdminKVN(byte Data){
    	 ucSAMAdminKVN=Data;
     }
     public void  SetSAMIssuerKVN(byte Data){
    	 SAMIssuerKVN=Data;
     }
     public void  SetTagListtable(byte[] Data,int len){
    	 TagListtable=Arrays.copyOf(Data, len);
     }
     public void  SetSAMIssuerSpecData(byte[] Data,int len){
    	 ucSAMIssuerSpecData=Arrays.copyOf(Data, len);
     }
     
     
     public String toString(){
 		String str=new String();
 		str=String.format("%02x", ucSAMVersion)  +DataFormat.convertToHexString(SAMUsageControl)+String.format("%02x", SAMIssuerKVN)+
 				DataFormat.convertToHexString(TagListtable) +DataFormat.convertToHexString(ucSAMIssuerSpecData) ;
 		return 	 str;
 	}
}

public class SAMTRANSINFO{//5365
        byte[] ucAuthCreditLimit;//[3];
        byte[] ucAuthCreditBalance;//[3];
        byte[] ucAuthCreditCumulative;//[3];
        byte[] ucAuthCancelCreditCumulative;//[3];
        SAMTRANSINFO(){
           ucAuthCreditLimit=new byte[3];//[3];
           ucAuthCreditBalance=new byte[3];//[3];
           ucAuthCreditCumulative=new byte[3];//[3];
           ucAuthCancelCreditCumulative=new byte[3];//[3];
        }
        public void  SetucAuthCreditLimit(byte[] Data,int len){
        	ucAuthCreditLimit=Arrays.copyOf(Data, len);
        }
        public void  ucAuthCreditCumulative(byte[] Data,int len){
        	ucAuthCreditBalance=Arrays.copyOf(Data, len);
        }
        public void  SetucAuthCreditBalance(byte[] Data,int len){
        	ucAuthCreditBalance=Arrays.copyOf(Data, len);
        }
        public void  SetucAuthCancelCreditCumulative(byte[] Data,int len){
        	ucAuthCancelCreditCumulative=Arrays.copyOf(Data, len);
        }
        public String toString(){
     		String str=new String();
     		str=DataFormat.convertToHexString(ucAuthCreditLimit)+
     				DataFormat.convertToHexString(ucAuthCreditBalance)+
     				DataFormat.convertToHexString(ucAuthCreditCumulative)+
     				DataFormat.convertToHexString(ucAuthCancelCreditCumulative);
     		return 	 str;
     	}
}

public class CPUSAMPARAMETERSETTINGDATA{//5367
        byte[] SAMUPDATEOPTION;//[1];
        byte[] NEWSAMVALUE;//[40];
        byte[] UpdateSAMVALUEMAC;//[16];
        CPUSAMPARAMETERSETTINGDATA(){
        	SAMUPDATEOPTION=new byte[1];//[1];
            NEWSAMVALUE=new byte[40];//[40];
            UpdateSAMVALUEMAC=new byte[16] ;//[16];
        }
        public void  SetNEWSAMVALUE(byte[] Data,int len){
        	NEWSAMVALUE=Arrays.copyOf(Data, len);
        }
        public void  SetSAMUPDATEOPTION(byte[] Data,int len){
        	SAMUPDATEOPTION=Arrays.copyOf(Data, len);
        }
        public void  SetUpdateSAMVALUEMAC(byte[] Data,int len){
        	UpdateSAMVALUEMAC=Arrays.copyOf(Data, len);
        }
        public String toString(){
     		String str=new String();
     		str=DataFormat.convertToHexString(SAMUPDATEOPTION)+
     				DataFormat.convertToHexString(NEWSAMVALUE)+
     				DataFormat.convertToHexString(UpdateSAMVALUEMAC);
     		return 	 str;
     	}
    }
 

  public class CPULASTSIGNONINFO{//5370
        byte[] ucPreCPUDeviceID;//[6 ];
        byte[] ucPreSTC;//[4];
        byte[] ucPreTxnDateTime;//[4];
        byte	 ucPreCreditBalanceChangeFlag;//;
        byte[] ucPreConfirmCode;//[2];
        byte[]  ucPreCACrypto;//[16];
        
        CPULASTSIGNONINFO(){
        	ucPreCPUDeviceID=new byte[6];//[6 ];
            ucPreSTC=new byte[4];//[4];
            ucPreTxnDateTime=new byte[4] ;//[4];
            ucPreCreditBalanceChangeFlag=0 ;//;
            ucPreConfirmCode=new byte[2] ;//[2];
            ucPreCACrypto=new byte[16];//[16];
        	
            
        }
        
        public void SetData(CPULASTSIGNONINFO data){
        	SetucPreCPUDeviceID(data.ucPreCPUDeviceID,data.ucPreCPUDeviceID.length);
            SetPreSTC(data.ucPreSTC,data.ucPreSTC.length);//[4];
            SetPreTxnDateTime(data.ucPreTxnDateTime,data.ucPreTxnDateTime.length) ;//[4];
            SetPreCreditBalanceChangeFlag(data.ucPreCreditBalanceChangeFlag) ;//;
            SetPreConfirmCode(data.ucPreConfirmCode,data.ucPreConfirmCode.length);//[2];
            SetPreCACrypto(data.ucPreCACrypto,data.ucPreCACrypto.length);//[16];
        }
        public void  SetucPreCPUDeviceID(byte[] Data,int len){
        	ucPreCPUDeviceID=Arrays.copyOf(Data, len);
        }
        
        public void  SetPreSTC(byte[] Data,int len){
        	ucPreSTC=Arrays.copyOf(Data, len);
        }
        public void  SetPreTxnDateTime(byte[] Data,int len){
        	ucPreTxnDateTime=Arrays.copyOf(Data, len);
        }
        public void  SetPreCreditBalanceChangeFlag(byte data){
        	ucPreCreditBalanceChangeFlag=data;
        }
        public void  SetPreConfirmCode(byte[] Data,int len){
        	ucPreConfirmCode=Arrays.copyOf(Data, len);
        }
        
        public void  SetPreCACrypto(byte[] Data,int len){
        	ucPreCACrypto=Arrays.copyOf(Data, len);
        }
        public String toString(){
     		String str=new String();
     		str=DataFormat.convertToHexString(ucPreCPUDeviceID)+
     				DataFormat.convertToHexString(ucPreSTC)+
     				DataFormat.convertToHexString(ucPreTxnDateTime)+
     					DataFormat.convertToHexString(ucPreConfirmCode)+
     				DataFormat.convertToHexString(ucPreCACrypto);
     		return 	 str;
     	}
    };

  public class CARDPARAMETER{//4821
    
    byte[] ucCardOneDayQuota;//[3];	//4821
    byte[] ucCardOneDayQuotaDate;//[2]; //4821
    CARDPARAMETER(){
    	  ucCardOneDayQuota=new byte[3];//[3];	//4821
    	  ucCardOneDayQuotaDate=new byte[2] ;//[2]; //4821
    }
    public void  SetCardOneDayQuota(byte[] Data,int len)
	{
		ucCardOneDayQuota=Arrays.copyOf(Data, len);
		
	}
	public void  SetCardOneDayQuotaDate_BeforeTX(byte[] Data,int len)
	{
		ucCardOneDayQuotaDate=Arrays.copyOf(Data, len);
	}
    public String toString(){
 		String str=new String();
 		str=DataFormat.convertToHexString(ucCardOneDayQuota)+
 				DataFormat.convertToHexString(ucCardOneDayQuotaDate);
 		return 	 str;
 	}
}
public class CPUCARDPARAMETER{//交易前 4827
	byte[] CardOneDayQuota_BeforeTX;//[3];//4827
	byte[] CardOneDayQuotaDate_BeforeTX;//[2];//4827
	CPUCARDPARAMETER(){
		 CardOneDayQuota_BeforeTX=new byte[3];//[3];//4827
		 CardOneDayQuotaDate_BeforeTX=new byte[2];//[2];//4827
	}
	public void  SetCardOneDayQuota_BeforeTX(byte[] Data,int len)
	{
		CardOneDayQuota_BeforeTX=Arrays.copyOf(Data, len);
		
	}
	public void  SetCardOneDayQuotaDate_BeforeTX(byte[] Data,int len)
	{
		CardOneDayQuotaDate_BeforeTX=Arrays.copyOf(Data, len);
	}
	public String toString(){
	 		String str=new String();
	 		str=DataFormat.convertToHexString(CardOneDayQuota_BeforeTX)+
	 				DataFormat.convertToHexString(CardOneDayQuotaDate_BeforeTX);
	 		return 	 str;
	 	}
}
public class MIFARESETTINGDATA{//4828
	byte []personalProfileAuthorization;//1
	byte [] RFU;//9
	MIFARESETTINGDATA(){
		 personalProfileAuthorization=new byte[1];
		 RFU=new byte[9];
	}
	public String toString(){
 		String str=new String();
 		str=DataFormat.convertToHexString(personalProfileAuthorization)+
 				DataFormat.convertToHexString(RFU);
 		return 	 str;
 	}
}

public class CPUCARDSETTINGPARAMETER{//4829
	byte[] MicroPaymentSetting;//1
	byte[] RFU;//15
	CPUCARDSETTINGPARAMETER(){
		MicroPaymentSetting=new byte[1];//1
		RFU=new byte[15] ;//15
	}
	public String toString(){
 		String str=new String();
 		str=DataFormat.convertToHexString(MicroPaymentSetting)+
 				DataFormat.convertToHexString(RFU);
 		return 	 str;
 	}
}








	
	public static final int TXTYPE_TMS           			=      0;        
	public static final int TXTYPE_SIGNON   			=      1;
	public static final int TXTYPE_ADD                 	=		 2;
	public static final int TXTYPE_DEDUCT             = 	 3;                
	public static final int TXTYPE_VOID               	= 	 4;
	public static final int TXTYPE_SETVALUE         =	 	 5;
	public static final int TXTYPE_SETTLEMENT    			=	6;
	public static final int TXTYPE_AUTOLOADENABLE 	=7;
	public static final int TXTYPE_REFUND             			=8;
	public static final int TXTYPE_LOCKCARD           		=9;         
	public static final int TXTYPE_REPORT              			=10;          
	public static final int TXTYPE_DEBUG		   					=11;
	public static final int TXTYPE_DEDUCTVOID          		=12;
	public static final int TXTYPE_ADDVOID           			=13;   
	public static final int TXTYPE_AUTOLOAD          		=14;           
    public BatchInfo Batch;
    public Properties Process_properties;
    public Properties Config_properties;
	
	public int iBATCHNO; //批次號
	public  int iTXSN; //交易序號
	public int iTxType;//交易類別
	public	    int iTxStatus; //TxStatus交易狀態
	public	    byte bAdviceFLAG;	//是否完成上傳advice
	public byte bMsgType;
	public byte bSubType;
	public byte bReadPurseFlag;
	public        byte bLCDControlFlag;
	public        Date  DateTxnDateTime;
	public 		Date  DateTMTxnDateTime;
	public        String SSLVER;
	public        String BLCVER;
	public       String APVER;
	public        String ParaVER;
	public        DataXferControl xferControl ;
	public 		TMINFO 	TM;       //1101 TMSN 1201 TMTXDATE 1301 TMTXTIME  3701 TMINVOICENUM  5504 TMID 5510 AGENTID
	public        byte bMessageType;//;//[4+1];                    //0100
	public        byte[] bytesCardID;//;//[7];                                    //0200
	public        byte[] bytesPID;//;//[8+1];                                       //0211
	public        byte bBasicData_Autoloadflag;                     //0212        
	public        byte bBasicData_CardType;                         //0213
	public        byte bBasicData_CardProfile;                      //0214
	public        byte bProcessCode;//;//[6+1];       			 //0300
	       // int uiDongleVersion;      //0301
	public        int iTxnAmt;           //0400
	public       int iDeductAMT;         //0403
	public        int iADDAMT;            //0407
	public        int iEVafterTxn;          //0408
	public        int iAutoloadAMT;         //0409        
	public        int iEVBeforeTxn;         //0410
	    //   Date  ucTxnDateTime;//;//[14+1];       //0700
	public       int iTerminalInvoiceNum;      //1100
//	public        int iTMTxnSN;            //1101
	     //byte ucTxnTime;//;//[6+1];       //1200
//	public        String sTMTxnTime;//;//[6+1];       //1201
	    //byte ucTxnData;//[8+1];       //1300 
//	public     	String sTMTxnData;//[8+1];       //1301 
	public        byte[] bytesCardExpiryDate;//[4];     //1401 n4 yymm
	public        byte[] bytesCardVaildDate;//[4];      //1402 
	public        byte[] bytesCardVaildDateAfterSet;//[4]; //1403 yyyymmdd
	public        String sRRN;//[14+1];               //3700 T1300+T1100
	//public        String sTMINVOICENO;//[20+1];              //3701        
	public        String sTxnAuthNum;//[6+1];         //3800
	public        String sResponseCode;//[2+1];       //3900
	public        String sSVCSResponseCode;//[2+1];   //3903
	public        byte[] bytesCPUDeviceID;//[6];         //4100 NEW DEVICE ID ucTerminalID
	public        byte[] bytesDeviceID;//[4];         //4100 NEW DEVICE ID ucTerminalID
	public        String sTerminalIP;//[15+1];         //4102                                  //4101
	public        String sMechineID;//[20];           //4103
	public        byte[] bytesReaderSN;//[4];            //4104
	public        String sCPUSPID;//[3];                                                //4200 ucMerchantID
	public        int 	 iSPID;
	public        int  	 iSubMerchantID;//[2];        //4210 Newlocation ID 
	public        byte 	 bLocationID;
	public        int 	 iCPUPurseVersionNUM; //4800
	public        byte[] bytesCardAVRDATA;//[45];           //4801
	public        int 	 iIssuerCode;         //4802
	public        int 	 iBankCode;           //4803
	public        int 	 iAreaCode;           //4804
	public        byte[] bytesCPUSubAreaCode;//[2];     //4805
	public        byte[] bytesProfileExpiryDate;//[4];  //4806
	public        byte[] bytescNEWProfileExpiryDate;//[4];//4807
	public        int  	 iCardTxnSN;          //4808
	public        int		 iCPUTxnMode;         //4809
	public        int 	 iCPUTQ;              //4810
	public        int    iSNBeforeTxn;        //4811
	public        byte[] bytesCTC;//[3];                //4812
	public        int 	 iLoyaltyCounter;    //4813
	public        int 	 iDepositValue;      //4814
	public        int    iCustomerFee;    //4817
	public        byte[] bytesANOTHEREV;//[3];     //4818
	public        int 	 iLockReason;		//4819
	public        int    iHostSpecVersionNo;  //4820	     
	public        CARDPARAMETER		CardParameter;//4821
	
		  //  int ucCardAutoloadParameters_AutoloadFlag;//4822
		  //  int ucCardAutoloadParameters_AutoloadValue;//4822
	public        int iOneDayQuotaWrite;//4823
	public        int iCPDReadFlag;        //4824     
	public       int iCPUCreditBalanceChangeFlag;//4825
	public        int iChipIDLength;       //4826
	      //  byte ucCPUCardParameters;//[10+1];//4827
	public       CPUCARDPARAMETER  CPUCardParameter;//4827
	public        MIFARESETTINGDATA	 MifareSettingData;//4828	        
	        //byte ucPersonalProfileAuth;//4828
	public       CPUCARDSETTINGPARAMETER CPUCardSettingPara;//4829
	        //byte ucMicroPaymentSetting;//4829
	public       byte bSAMKeyVersion;         //5301
	public       CPUCARDKEY_INFO CPUCardKeyInfo;//5302
	      //  byte ucAdminKeyKVN;	//5302
	     //   byte ucCreditKeyKVN;	//5302
	     //   byte ucDebitKeyKVN;    //5302
	public       int	iCPUHashTYPE;        //5303
	public        int iCPUHostadminKVN;    //5304
	public       int iSigntureKeyKVN;     //5305
	public        byte[] bytesCPUEDC;//[3];             //5306
	public        byte[] bytesRSAM;//[8];              //5307
	public        byte[] bytesRHOST;//[8];            //5308
	public        byte[] bytesSAMID;//[8];             //5361
	public       byte[] bytesSAMSN;//[4];            //5363
	public       byte[] bytesSAMCRN;//[8];            //5363
	public        CPUSAMINFO CPUSAMINFOData;        //5364             
	public       SAMTRANSINFO  SAMtransInfoData;   //5365      
	public       byte[] bytesSingleCreditTxnAmtLimit;//[3]; //5366
	public        CPUSAMPARAMETERSETTINGDATA CSAMPARA;    //5367
	public      byte[] bytesSTC;//[4];      //5368
	public        byte 	 bSAMSignOnControlFlag;             //5369
	public        CPULASTSIGNONINFO CPULastSignonInfoData;//5370
	public        byte[]	bytesCPUSAMID;//[8];                   //未加入       
	public			String sBatchNo;//[8+1];                    //5501
	public        int iSTCode;                    //5503 STCODE
	//public       String sTMPOSID;//[6+1];                         //5504 �T�w 000000
	public        int		 iEDCTYPE;                      //5509
	//public        String  sTMAgentNumber;//[4+1];                                       //5510        
	public       String sTMTxnDateTime;//[8+1];                                        //5514
	      
	public        byte[] bytesOrgTxnTerminalID;//[6];           //5581
	public        byte[] bytesOrgTxnRRN;//[14+1];                 //5582
	public      byte[] bytesOrgTxnDate;//[8+1];                 //5583
	     
	public        byte 	 bDeductStatus;               //558901
	public      int 	iSettle_TotleCnt;              //5591
	public        int   iSettle_TotleAmt;            //5592
	      
	  
	public      byte[] bytesReaderFWVersion;//[6];           //6000
	public     byte[] bytesReaderAVRDATA;//[33];              //6001 len= 83
	public       TERMHOST stTermHostInfo_t;		//6002
	public       TERMPARA stTermParaInfo_t;		//6003  
	public       int iBLVersion;                                        //6004
	public     byte[] bytesSTAC;//[8];                        //6400
	public      byte[] bytesHTAC;//[8];                        //6401
	public     byte[] bytesCTAC;//[8];                        //6402
	public       byte[] bytesMAC;//[4];                         //6403        
	public      byte[] bytesCPUMAC_HCrypto;//[16];              //6404
	public    byte[] bytesCPUSignature;//[16];                //6405
	public      byte[] bytesCPUTermCrypto;//[16];               //6406  ucCACrypto
	public      byte[] bytesHostCrypto;//[16];                  //6407
	public      byte[] bytesSATOKEN;//[16];                   //6408
	public     byte[] bytesHostToken;//[16];                   //6409  
	        /////////////////////////////////////////////////
	
	        
		public void init(){
//			  iBATCHNO=0;; //批次號
			  
			  iTXSN=0; //交易序號
			  iTxType=0;//交易類別
			  iTxStatus=0; //TxStatus交易狀態
			  bAdviceFLAG=0;;	//是否完成上傳advice
			  bMsgType=0;
			  bSubType=0;
			  bReadPurseFlag=0;
			  bLCDControlFlag=0;
			  DateTxnDateTime=new Date();
			  SSLVER="";
			  BLCVER="";
			  APVER="";
			  ParaVER="";
			 //  xferControl=new DataXferControl() ;
		  	   TM=new TMINFO();     
			   String sMessageType="";//;//[4+1];                    //0100
			   bytesCardID=new byte[7] ;//;//[7];                                    //0200
			   bytesPID=new byte[8+1] ;//;//[8+1];                                       //0211
			   bBasicData_Autoloadflag=0;                     //0212        
			   bBasicData_CardType=0;                         //0213
			   bBasicData_CardProfile=0;                      //0214
			   String sProcessCode;//;//[6+1];       			 //0300
			       // int uiDongleVersion;      //0301
			    iTxnAmt=0;           //0400
			    iDeductAMT=0;         //0403
			    iADDAMT=0;            //0407
			    iEVafterTxn=0;          //0408
			    iAutoloadAMT=0;         //0409        
			    iEVBeforeTxn=0;         //0410
			    //   Date  ucTxnDateTime;//;//[14+1];       //0700
			    iTerminalInvoiceNum=0;      //1100
			  //  iTMTxnSN=0;            //1101
			     //byte ucTxnTime;//;//[6+1];       //1200
			//     sTMTxnTime="";//;//[6+1];       //1201
			    //byte ucTxnData;//[8+1];       //1300 
			//     sTMTxnData="";//[8+1];       //1301 
			     bytesCardExpiryDate=new byte[4];//[4];     //1401 n4 yymm
			     bytesCardVaildDate=new byte[4];//[4];      //1402 
			     bytesCardVaildDateAfterSet=new byte[4] ;//[4]; //1403 yyyymmdd
			     sRRN="";//[14+1];               //3700 T1300+T1100
			  //   sTMINVOICENO="";//[20+1];              //3701        
			     sTxnAuthNum="";//[6+1];         //3800
			     sResponseCode="";//[2+1];       //3900
			     sSVCSResponseCode="";//[2+1];   //3903
			     bytesCPUDeviceID=new byte[6] ;//[6];         //4100 NEW DEVICE ID ucTerminalID
			     bytesDeviceID=new byte[4] ;//[4];         //4100 NEW DEVICE ID ucTerminalID
			     sTerminalIP="";//[15+1];         //4102                                  //4101
			     sMechineID="";//[20];           //4103
			     bytesReaderSN=new byte[4] ;//[4];            //4104
			     sCPUSPID=new String();//[3];                                                //4200 ucMerchantID
			     iSPID=0;;
			     iSubMerchantID=0;//[2];        //4210 Newlocation ID 
			     bLocationID=0;;
			     iCPUPurseVersionNUM=0; //4800
			     bytesCardAVRDATA=new byte[45];//[45];           //4801
			      iIssuerCode=0;         //4802
			      iBankCode=0;           //4803
			      iAreaCode=0;           //4804
			       bytesCPUSubAreaCode=new byte[2] ;//[2];     //4805
			       bytesProfileExpiryDate=new byte[4];//[4];  //4806
			       bytescNEWProfileExpiryDate=new byte[4];//[4];//4807
			       iCardTxnSN=0;          //4808
			       iCPUTxnMode=0;         //4809
			       iCPUTQ=0;              //4810
			       iSNBeforeTxn=0;        //4811
			       bytesCTC=new byte[3];//[3];                //4812
			       iLoyaltyCounter=0;    //4813
			       iDepositValue=0;      //4814
			       iCustomerFee=0;    //4817
			       bytesANOTHEREV=new byte[3];//[3];     //4818
			        iLockReason=0;		//4819
			        iHostSpecVersionNo=0;  //4820	     
			        CardParameter=new CARDPARAMETER();//4821
			
				  //   ucCardAutoloadParameters_AutoloadFlag;//4822
				  //   ucCardAutoloadParameters_AutoloadValue;//4822
			          iOneDayQuotaWrite=0;//4823
			          iCPDReadFlag=0;        //4824     
			          iCPUCreditBalanceChangeFlag=0;//4825
			          iChipIDLength=0;       //4826
			      //   ucCPUCardParameters;//[10+1];//4827
			          CPUCardParameter=new CPUCARDPARAMETER();//4827
			          MifareSettingData=new MIFARESETTINGDATA();//4828	        
			        // ucPersonalProfileAuth;//4828
			          CPUCardSettingPara=new CPUCARDSETTINGPARAMETER();//4829
			        // ucMicroPaymentSetting;//4829
			         bSAMKeyVersion=0;         //5301
			         CPUCardKeyInfo=new CPUCARDKEY_INFO() ;//5302
			      //   ucAdminKeyKVN;	//5302
			     //    ucCreditKeyKVN;	//5302
			     //    ucDebitKeyKVN;    //5302
			        iCPUHashTYPE=0;        //5303
			        iCPUHostadminKVN=0;    //5304
			        iSigntureKeyKVN=0;     //5305
			        bytesCPUEDC=new  byte[3];//[3];             //5306
			        bytesRSAM=new  byte[8];//[8];              //5307
			        bytesRHOST=new  byte[8];//[8];            //5308
			        bytesSAMID=new  byte[8];//[8];             //5361
			        bytesSAMSN=new  byte[4];//[4];            //5363
			        bytesSAMCRN=new  byte[8];//[8];            //5363
			        CPUSAMINFOData=new CPUSAMINFO();        //5364             
			        SAMtransInfoData=new SAMTRANSINFO();   //5365      
			        bytesSingleCreditTxnAmtLimit=new byte[3];//[3]; //5366
			        CSAMPARA=new CPUSAMPARAMETERSETTINGDATA();    //5367
			        bytesSTC=new byte[4];//[4];      //5368
			        bSAMSignOnControlFlag=0;             //5369
			        CPULastSignonInfoData=new CPULASTSIGNONINFO();//5370
			        bytesCPUSAMID=new byte[8];//[8];                   //未加入       
			        sBatchNo=new String();//[8+1];                    //5501
			        iSTCode=0;                    //5503 STCODE
			   //     sTMPOSID=new String();//[6+1];                         //5504 �T�w 000000
			        iEDCTYPE=0;                      //5509
			    //    sTMAgentNumber=new String();//[4+1];                                       //5510        
			        sTMTxnDateTime=new String();//[8+1];                                        //5514
			        bytesOrgTxnTerminalID=new  byte[6] ;//[6];           //5581
			        bytesOrgTxnRRN=new  byte[14];//[14+1];                 //5582
			        bytesOrgTxnDate=new  byte[8];//[8+1];                 //5583
			     
			      	bDeductStatus=0;               //558901
			       	iSettle_TotleCnt=0;              //5591
			        iSettle_TotleAmt=0;            //5592
			      
			  
			        bytesReaderFWVersion=new byte[6] ;//[6];           //6000
			        bytesReaderAVRDATA=new byte[83];//[83];              //6001 len= 83
			        stTermHostInfo_t=new TERMHOST();		//6002
			        stTermParaInfo_t=new TERMPARA();		//6003  
			        iBLVersion=0;                                        //6004
			        bytesSTAC=new byte[8];//[8];                        //6400
			        bytesHTAC=new byte[8];//[8];                        //6401
			        bytesCTAC=new byte[8];//[8];                        //6402
			        bytesMAC=new byte[4];//[4];                         //6403        
			        bytesCPUMAC_HCrypto=new byte[16];//[16];              //6404
			        bytesCPUSignature=new byte[16];//[16];                //6405
			        bytesCPUTermCrypto=new byte[16];//[16];               //6406  ucCACrypto
			        bytesHostCrypto=new byte[16];//[16];                  //6407
			        bytesSATOKEN=new byte[16];//[16];                   //6408
			        bytesHostToken=new byte[16];//[16];                   //6409  
			        /////////////////////////////////////////////////
			        SetBatchInfo();
			    
		}
		
		public void PutResetData2TransData(PPR_Reset  DongleOut)
		{
			this.iHostSpecVersionNo=(int)DongleOut.GetResp_SpecVersionNumber();//. outucHostSpecVersionNo;
	    
	    	
	    	System.arraycopy( DongleOut.GetResp_ReaderID(), 0,this.bytesReaderSN, 0,4);//outucReaderID
	      
	    	System.arraycopy(DongleOut.GetResp_ReaderFWVersion(),0,this.bytesReaderFWVersion,0,DongleOut.GetResp_ReaderFWVersion().length);//outucReaderFWVersion
	    	System.arraycopy(DongleOut.GetResp_SAMID(),0,this.bytesSAMID,0,DongleOut.GetResp_SAMID().length);
	       	System.arraycopy(DongleOut.GetResp_SAMSN(),0,this.bytesSAMSN,0,DongleOut.GetResp_SAMSN().length);  //outucSAMSN
	      	System.arraycopy(DongleOut.GetResp_SAMCRN(),0,this.bytesSAMCRN,0,DongleOut.GetResp_SAMCRN().length);  
	      	
	     	System.arraycopy(DongleOut.GetResp_DeviceID(),0,this.bytesDeviceID,0,DongleOut.GetResp_DeviceID().length);  
	      	this.bSAMKeyVersion=DongleOut.GetResp_SAMKeyVersion();//outucSAMKeyVersion;
	      	System.arraycopy(DongleOut.GetResp_S_TAC(),0,this.bytesSTAC,0,DongleOut.GetResp_S_TAC().length);  
	    	System.arraycopy(DongleOut.GetResp_SAMIDNew(),0,this.bytesCPUSAMID,0,DongleOut.GetResp_SAMIDNew().length);  
	    	
	    	  //5364
	    	this.CPUSAMINFOData.ucSAMVersion=DongleOut.GetResp_SAMVersionNumber();//.outucSAMVersion;
	    
	     	System.arraycopy(DongleOut.GetResp_SAMUsageControl(),0,this.CPUSAMINFOData.SAMUsageControl,0,DongleOut.GetResp_SAMUsageControl().length);  
	     	this.CPUSAMINFOData.ucSAMAdminKVN=DongleOut.GetResp_SAMAdminKVN();//.outucSAMAdminKVN;
	     	this.CPUSAMINFOData.SAMIssuerKVN=DongleOut.GetResp_SAMIssuerKVN();//.outucSAMIssuerKVN;
	
	    	System.arraycopy(DongleOut.GetResp_TagListTable(),0,this.CPUSAMINFOData.TagListtable,0,DongleOut.GetResp_TagListTable().length);  
	    	
	    	System.arraycopy(DongleOut.GetResp_SAMIssuerSpecificData(),0,this.CPUSAMINFOData.ucSAMIssuerSpecData,0,DongleOut.GetResp_SAMIssuerSpecificData().length);  
		    //5365   	
	    	System.arraycopy(DongleOut.GetResp_ACL(),0,this.SAMtransInfoData.ucAuthCreditLimit,0,DongleOut.GetResp_ACL().length);  
	    	
	    	System.arraycopy(DongleOut.GetResp_ACB(),0,this.SAMtransInfoData.ucAuthCreditLimit,0,DongleOut.GetResp_ACB().length);  
	    	
	    	System.arraycopy(DongleOut.GetResp_ACC(),0,this.SAMtransInfoData.ucAuthCreditCumulative,0,DongleOut.GetResp_ACC().length);  
		    //5366	
	    	System.arraycopy(DongleOut.GetResp_ACCC(),0,this.SAMtransInfoData.ucAuthCancelCreditCumulative,0,DongleOut.GetResp_ACCC().length);  
	  
	    	System.arraycopy(DongleOut.GetResp_NewDeviceID(),0,this.bytesCPUDeviceID,0,DongleOut.GetResp_NewDeviceID().length);  
	    	System.arraycopy(DongleOut.GetResp_STC(),0,this.bytesSTC,0,DongleOut.GetResp_STC().length);  
	    	
	    	System.arraycopy(DongleOut.GetResp_RSAM(),0,this.bytesRSAM,0,DongleOut.GetResp_RSAM().length);  
	    	System.arraycopy(DongleOut.GetResp_RHOST(),0,this.bytesRHOST,0,DongleOut.GetResp_RHOST().length);  
	    	System.arraycopy(DongleOut.GetResp_SATOKEN(),0,this.bytesSATOKEN,0,DongleOut.GetResp_SATOKEN().length);  

	    	   //5369
	     	this.bSAMSignOnControlFlag =DongleOut.GetResp_SAMSignOnControlFlag();//((byte)DongleOut.GetResp_SAMSignOnControlFlag()?1:0);
	
	     	
	    	   //  TAG 6002 
	    	//System.arraycopy(DongleOut.outstSAMParameterInfo_t.ucOneDayQuota,0,this.stTermHostInfo_t.ucOneDayQuota,0,DongleOut.outstSAMParameterInfo_t.ucOneDayQuota.length);  
	    	this.stTermHostInfo_t.SetucOneDayQuota(DongleOut.GetResp_OneDayQuotaForMicroPayment(), DongleOut.GetResp_OneDayQuotaForMicroPayment().length);
	    	this.stTermHostInfo_t.ucOneDayQuotaFlag=DongleOut.GetResp_OneDayQuotaFlagForMicroPayment();//One Day Quota Flag
	    	
      
	    	//this.stTermHostInfo_t.ucOnceQuotaFlag =(byte)(DongleOut.GetResp_OnceQuotaFlagForMicroPayment()?1:0);//Once Quota Flag
	    	
	    	//System.arraycopy(DongleOut.outstSAMParameterInfo_t.ucOnceQuota,0,this.stTermHostInfo_t.ucOnceQuota,0,DongleOut.outstSAMParameterInfo_t.ucOnceQuota.length);  
	    	this.stTermHostInfo_t.SetucOnceQuota(DongleOut.GetResp_OnceQuotaForMicroPayment(), DongleOut.GetResp_OnceQuotaForMicroPayment().length);
	    
	    	//stTermHostInfo_t.ucCheckEVFlag = DongleOut.bGetResp_CheckEVFlagForMifareOnly();//Check EV Flag
	    	//stTermHostInfo_t.ucAddQuotaFlag = DongleOut.bGetResp_AddQuotaFlag();//.outstSAMParameterInfo_t.ucAddQuotaFlag;//Add Quota Flag
	    	//System.arraycopy(DongleOut.outstSAMParameterInfo_t.ucAddQuota,0,this.stTermHostInfo_t.ucAddQuota,0,DongleOut.outstSAMParameterInfo_t.ucAddQuota.length);  
	    	this.stTermHostInfo_t.SetucAddQuota(DongleOut.GetResp_AddQuota(), DongleOut.GetResp_AddQuota().length);
	    	//stTermHostInfo_t.ucCheckDeductFlag = DongleOut.bGetResp_CheckDebitFlag();//Check Debit Flag
	    	//System.arraycopy(DongleOut.outstSAMParameterInfo_t.ucCheckDeductValue,0,this.stTermHostInfo_t.ucCheckDeductValue,0,DongleOut.outstSAMParameterInfo_t.ucCheckDeductValue.length);  
	    	this.stTermHostInfo_t.SetucCheckDeductValue(DongleOut.GetResp_CheckDebitValue(), DongleOut.GetResp_CheckDebitValue().length);
	    	
	    	
	    	//stTermHostInfo_t.ucDeductLimitFlag = DongleOut.bGetResp_MerchantLimitUseForMicroPayment();//;.bDeductLimitFlag7;//Deduct Limit Flag
	     //	System.arraycopy(API_VERSION,0,this.stTermHostInfo_t.ucAPIVersion,0,3);  
	    	byte[] API_VERSION=new byte[]{0x00,0x00,0x00};
	     	this.stTermHostInfo_t.SetucAPIVersion(API_VERSION, 3);
	  
	
		      //TAG TermHost 6003       
			    	//memcpy(stTermParaInfo_t.ucRemainderAddQuota,DongleOut->ucRemainderAddQuota,sizeof(stTermParaInfo_t.ucRemainderAddQuota));//Remainder Add Quota
			  
	      	//System.arraycopy(DongleOut.outucDeMAC,0,this.stTermParaInfo_t.ucDeMAC,0,DongleOut.outucDeMAC.length);  
	      	this.stTermParaInfo_t.SetucDeMAC(DongleOut.GetResp_deMACParameter(), DongleOut.GetResp_deMACParameter().length);//outucDeMAC, DongleOut.outucDeMAC.length);
	    
	    	//System.arraycopy(DongleOut.outucCancelCreditQuota,0,this.stTermParaInfo_t.ucCancelCreditQuota,0,DongleOut.outucCancelCreditQuota.length);  
	    	this.stTermParaInfo_t.SetucCancelCreditQuota(DongleOut.GetResp_CancelCreditQuota(), DongleOut.GetResp_CancelCreditQuota().length);

	     	//System.arraycopy(DongleOut.outucCancelCreditQuota,0,this.stTermParaInfo_t.ucCancelCreditQuota,0,DongleOut.outucCancelCreditQuota.length);  
	    	this.stTermParaInfo_t.SetucRemainderAddQuota(DongleOut.GetResp_RemainderofAddQuota(), DongleOut.GetResp_RemainderofAddQuota().length);
	        //4823
	    	this.iOneDayQuotaWrite= DongleOut.GetResp_OneDayQuotaWriteForMicroPayment();//bOneDayQuotaWrite2 |    (DongleOut.outstSAMParameterInfo_t.bOneDayQuotaWrite3<<4);//CPU One Day Quota Write Flag 4823
	    	
	    //4824
	    	  this.iCPDReadFlag=DongleOut.GetResp_CPDReadFlag();//bCPDReadFlag0| DongleOut.outstSAMParameterInfo_t.bCPDReadFlag1<<4;
	            
	    //5370
	    	  
	    
	    		//System.arraycopy(DongleOut.outstLastSignOnInfo_t,0,this.CPULastSignonInfoData,0,DongleOut.outstLastSignOnInfo_t.getDatalen());  
	    	  this.CPULastSignonInfoData.SetucPreCPUDeviceID(DongleOut.GetResp_PreviousNewDeviceID(),DongleOut.GetResp_PreviousNewDeviceID().length);
	    	  this.CPULastSignonInfoData.SetPreSTC(DongleOut.GetResp_PreviousSTC(),DongleOut.GetResp_PreviousSTC().length);//[4];
	    	  this.CPULastSignonInfoData.SetPreTxnDateTime(DongleOut.bGetResp_LastTXNDateTime(),DongleOut.bGetResp_LastTXNDateTime().length) ;//[4];
	    	  this.CPULastSignonInfoData.SetPreCreditBalanceChangeFlag(DongleOut.bGetResp_PreviousCreditBalanceChangeFlag());//.ucPreCreditBalanceChangeFlag) ;//;
	    	  this.CPULastSignonInfoData.SetPreConfirmCode(DongleOut.GetResp_PreviousConfirmCode(),DongleOut.GetResp_PreviousConfirmCode().length);//[2];
	    	  this.CPULastSignonInfoData.SetPreCACrypto(DongleOut.GetResp_PreviousCACrypto(),DongleOut.GetResp_PreviousCACrypto().length);//[16];

	          
			
		}

/*
		public void SetResetData2TransData(CMD_Reset DongleOut, byte[] API_VERSION)
	    {
	    	this.iHostSpecVersionNo=(int)DongleOut. outucHostSpecVersionNo;
	    
	    	
	    	System.arraycopy( DongleOut.outucReaderID, 0,this.bytesReaderSN, 0,4);
	      
	    	System.arraycopy(DongleOut.outucReaderFWVersion,0,this.bytesReaderFWVersion,0,DongleOut.outucReaderFWVersion.length);
	    	System.arraycopy(DongleOut.outucSAMID,0,this.bytesSAMID,0,DongleOut.outucSAMID.length);
	       	System.arraycopy(DongleOut.outucSAMSN,0,this.bytesSAMSN,0,DongleOut.outucSAMSN.length);  
	      	System.arraycopy(DongleOut.outucSAMCRN,0,this.bytesSAMCRN,0,DongleOut.outucSAMCRN.length);  
	      	
	     	System.arraycopy(DongleOut.outucDeviceID,0,this.bytesDeviceID,0,DongleOut.outucDeviceID.length);  
	      	this.bSAMKeyVersion=DongleOut.outucSAMKeyVersion;
	      	System.arraycopy(DongleOut.outucSTAC,0,this.bytesSTAC,0,DongleOut.outucSTAC.length);  
	    	System.arraycopy(DongleOut.outucCPUSAMID,0,this.bytesCPUSAMID,0,DongleOut.outucCPUSAMID.length);  
	    	
	    	  //5364
	    	this.CPUSAMINFOData.ucSAMVersion=DongleOut.outucSAMVersion;
	    
	     	System.arraycopy(DongleOut.outucSAMUsageControl,0,this.CPUSAMINFOData.SAMUsageControl,0,DongleOut.outucSAMUsageControl.length);  
	     	this.CPUSAMINFOData.ucSAMAdminKVN=DongleOut.outucSAMAdminKVN;
	     	this.CPUSAMINFOData.SAMIssuerKVN=DongleOut.outucSAMIssuerKVN;
	
	    	System.arraycopy(DongleOut.outucTagListTable,0,this.CPUSAMINFOData.TagListtable,0,DongleOut.outucTagListTable.length);  
	    	
	    	System.arraycopy(DongleOut.outucSAMIssuerSpecData,0,this.CPUSAMINFOData.ucSAMIssuerSpecData,0,DongleOut.outucSAMIssuerSpecData.length);  
		    //5365   	
	    	System.arraycopy(DongleOut.outucAuthCreditLimit,0,this.SAMtransInfoData.ucAuthCreditLimit,0,DongleOut.outucAuthCreditLimit.length);  
	    	
	    	System.arraycopy(DongleOut.outucAuthCreditBalance,0,this.SAMtransInfoData.ucAuthCreditLimit,0,DongleOut.outucAuthCreditBalance.length);  
	    	
	    	System.arraycopy(DongleOut.outucAuthCreditCumulative,0,this.SAMtransInfoData.ucAuthCreditCumulative,0,DongleOut.outucAuthCreditCumulative.length);  
		    //5366	
	    	System.arraycopy(DongleOut.outucAuthCancelCreditCumulative,0,this.SAMtransInfoData.ucAuthCancelCreditCumulative,0,DongleOut.outucAuthCancelCreditCumulative.length);  
	  
	    	System.arraycopy(DongleOut.outucCPUDeviceID,0,this.bytesCPUDeviceID,0,DongleOut.outucCPUDeviceID.length);  
	    	System.arraycopy(DongleOut.outucSTC,0,this.bytesSTC,0,DongleOut.outucSTC.length);  
	    	
	    	System.arraycopy(DongleOut.outucRSAM,0,this.bytesRSAM,0,DongleOut.outucRSAM.length);  
	    	System.arraycopy(DongleOut.outucRHOST,0,this.bytesRHOST,0,DongleOut.outucRHOST.length);  
	    	System.arraycopy(DongleOut.outucSATOKEN,0,this.bytesSATOKEN,0,DongleOut.outucSATOKEN.length);  

	    	   //5369
	     	this.bSAMSignOnControlFlag = (byte) (DongleOut.outstSAMParameterInfo_t.bSAMSignOnControlFlag4 |
	    					(DongleOut.outstSAMParameterInfo_t.bSAMSignOnControlFlag5<<4));
	     	
	
	     	
	    	   //  TAG 6002 
	    	//System.arraycopy(DongleOut.outstSAMParameterInfo_t.ucOneDayQuota,0,this.stTermHostInfo_t.ucOneDayQuota,0,DongleOut.outstSAMParameterInfo_t.ucOneDayQuota.length);  
	    	this.stTermHostInfo_t.SetucOneDayQuota(DongleOut.outstSAMParameterInfo_t.ucOneDayQuota, DongleOut.outstSAMParameterInfo_t.ucOneDayQuota.length);
	    	this.stTermHostInfo_t.ucOneDayQuotaFlag=(byte) (DongleOut.outstSAMParameterInfo_t.bOneDayQuotaFlag0 |
				    (DongleOut.outstSAMParameterInfo_t.bOneDayQuotaFlag1 << 4));//One Day Quota Flag
	    	
      
	    	this.stTermHostInfo_t.ucOnceQuotaFlag = DongleOut.outstSAMParameterInfo_t.bOnceQuotaFlag2;//Once Quota Flag
	    	
	    	//System.arraycopy(DongleOut.outstSAMParameterInfo_t.ucOnceQuota,0,this.stTermHostInfo_t.ucOnceQuota,0,DongleOut.outstSAMParameterInfo_t.ucOnceQuota.length);  
	    	this.stTermHostInfo_t.SetucOnceQuota(DongleOut.outstSAMParameterInfo_t.ucOnceQuota, DongleOut.outstSAMParameterInfo_t.ucOnceQuota.length);
	    
	    	stTermHostInfo_t.ucCheckEVFlag = DongleOut.outstSAMParameterInfo_t.bCheckEVFlag6;//Check EV Flag
	    	stTermHostInfo_t.ucAddQuotaFlag = DongleOut.outstSAMParameterInfo_t.ucAddQuotaFlag;//Add Quota Flag
	    	//System.arraycopy(DongleOut.outstSAMParameterInfo_t.ucAddQuota,0,this.stTermHostInfo_t.ucAddQuota,0,DongleOut.outstSAMParameterInfo_t.ucAddQuota.length);  
	    	this.stTermHostInfo_t.SetucAddQuota(DongleOut.outstSAMParameterInfo_t.ucAddQuota, DongleOut.outstSAMParameterInfo_t.ucAddQuota.length);
	    	stTermHostInfo_t.ucCheckDeductFlag = DongleOut.outstSAMParameterInfo_t.bCheckDeductFlag3;//Check Debit Flag
	    	//System.arraycopy(DongleOut.outstSAMParameterInfo_t.ucCheckDeductValue,0,this.stTermHostInfo_t.ucCheckDeductValue,0,DongleOut.outstSAMParameterInfo_t.ucCheckDeductValue.length);  
	    	this.stTermHostInfo_t.SetucCheckDeductValue(DongleOut.outstSAMParameterInfo_t.ucCheckDeductValue, DongleOut.outstSAMParameterInfo_t.ucCheckDeductValue.length);
	    	stTermHostInfo_t.ucDeductLimitFlag = DongleOut.outstSAMParameterInfo_t.bDeductLimitFlag7;//Deduct Limit Flag
	     //	System.arraycopy(API_VERSION,0,this.stTermHostInfo_t.ucAPIVersion,0,3);  
	     	//this.stTermHostInfo_t.SetucAPIVersion(API_VERSION, 3);
	  
	
		      //TAG TermHost 6003       
			    	//memcpy(stTermParaInfo_t.ucRemainderAddQuota,DongleOut->ucRemainderAddQuota,sizeof(stTermParaInfo_t.ucRemainderAddQuota));//Remainder Add Quota
			  
	      	//System.arraycopy(DongleOut.outucDeMAC,0,this.stTermParaInfo_t.ucDeMAC,0,DongleOut.outucDeMAC.length);  
	      	this.stTermParaInfo_t.SetucDeMAC(DongleOut.outucDeMAC, DongleOut.outucDeMAC.length);
	    
	    	//System.arraycopy(DongleOut.outucCancelCreditQuota,0,this.stTermParaInfo_t.ucCancelCreditQuota,0,DongleOut.outucCancelCreditQuota.length);  
	    	this.stTermParaInfo_t.SetucCancelCreditQuota(DongleOut.outucCancelCreditQuota, DongleOut.outucCancelCreditQuota.length);

	     	//System.arraycopy(DongleOut.outucCancelCreditQuota,0,this.stTermParaInfo_t.ucCancelCreditQuota,0,DongleOut.outucCancelCreditQuota.length);  
	    	this.stTermParaInfo_t.SetucRemainderAddQuota(DongleOut.outucRemainderAddQuota, DongleOut.outucRemainderAddQuota.length);
	        //4823
	    	this.iOneDayQuotaWrite= DongleOut.outstSAMParameterInfo_t.bOneDayQuotaWrite2 |
	    				     (DongleOut.outstSAMParameterInfo_t.bOneDayQuotaWrite3<<4);//CPU One Day Quota Write Flag 4823
	    	
	    //4824
	    	  this.iCPDReadFlag=DongleOut.outstSAMParameterInfo_t.bCPDReadFlag0| DongleOut.outstSAMParameterInfo_t.bCPDReadFlag1<<4;
	            
	    //5370
	    	  
	    
	    		//System.arraycopy(DongleOut.outstLastSignOnInfo_t,0,this.CPULastSignonInfoData,0,DongleOut.outstLastSignOnInfo_t.getDatalen());  
	    	  this.CPULastSignonInfoData.SetucPreCPUDeviceID(DongleOut.outstLastSignOnInfo_t.ucPreCPUDeviceID,DongleOut.outstLastSignOnInfo_t.ucPreCPUDeviceID.length);
	    	  this.CPULastSignonInfoData.SetPreSTC(DongleOut.outstLastSignOnInfo_t.ucPreSTC,DongleOut.outstLastSignOnInfo_t.ucPreSTC.length);//[4];
	    	  this.CPULastSignonInfoData.SetPreTxnDateTime(DongleOut.outstLastSignOnInfo_t.ucPreTxnDateTime,DongleOut.outstLastSignOnInfo_t.ucPreTxnDateTime.length) ;//[4];
	    	  this.CPULastSignonInfoData.SetPreCreditBalanceChangeFlag(DongleOut.outstLastSignOnInfo_t.ucPreCreditBalanceChangeFlag) ;//;
	    	  this.CPULastSignonInfoData.SetPreConfirmCode(DongleOut.outstLastSignOnInfo_t.ucPreConfirmCode,DongleOut.outstLastSignOnInfo_t.ucPreConfirmCode.length);//[2];
	    	  this.CPULastSignonInfoData.SetPreCACrypto(DongleOut.outstLastSignOnInfo_t.ucPreCACrypto,DongleOut.outstLastSignOnInfo_t.ucPreCACrypto.length);//[16];

	          
	    	

 
	    }
	 */   
			void SetConfig_properties(Properties prop )
			{
				Config_properties=prop;
			}
	    	void SetProcess_Properist(Properties prop ){
	    	    Process_properties= prop;
			
	    	}
	    
	    	void SetBatchInfo(){
	    		Batch=new BatchInfo();
	        
	    	}
	    	void SetTXSN(int sn){
	    		this.iTXSN=sn;
	    		
	    	}
	        public void SetTransDataTime()
	        { 
	   
	        
	        	this.DateTxnDateTime= new Date();	        	  
	       //     SimpleDateFormat fDate =   new SimpleDateFormat ("yyyyMMdd");
	       //     ucTxnData=fDate.format(TxnDateTime);
	       //     SimpleDateFormat ftime =   new SimpleDateFormat ("hhmmss");
	        //    ucTxnTime=ftime.format(TxnDateTime);
	            
	      //      System.arraycopy(inTMTxnDateTime.getBytes(),0,CMDDATA,offset,14);
	      //      offset+=14;
	        	
	        }
	
	      
			public  void  SetSTCODE(String STCODE)
			{
					this.iSTCode=Integer.parseInt(STCODE);
			}
			public void SetiSubMerchantID(String NewLocationID)
			{
					this.iSubMerchantID=Integer.parseInt(NewLocationID);
			}
			public void SetCPUSPID(String CPUSPID)
			{
				this.sCPUSPID=CPUSPID;
			}
			public void SetMerchineID(String MerchineID)
			{
				this.sMechineID=MerchineID;
			}
		/*	public void SetTMINVOICENO(String tm_invoiceno)
			{
				this.sTMINVOICENO=tm_invoiceno;
			}*/
		/*	public void SetTM_AgentNumber(String agentNo)
			{
				this.sTMAgentNumber=agentNo;
			}*/
			private void SetTxType(int txtype)
			{
			
				iTxType=txtype;
			}
			public     TransactionData(Properties process_prop,Properties config_prop,int txtype,TMINFO tm)
	        {
				init();
	        	SetTxType(txtype);
	        	SetProcess_Properist( process_prop );
	        	SetConfig_properties(config_prop );
	        	this.iTxType=TXTYPE.SIGNON;
	        	this.iTxStatus=TransStatus.REQ;
	        	this.SetCPUSPID ( Config_properties.getProperty("Merchant_ID"));
	        	this.SetSTCODE(  Config_properties.getProperty("Merchant_STCODE"));
	        	this.SetiSubMerchantID( Config_properties.getProperty("Merchant_LocationID"));
	        	this.SetMerchineID(Config_properties.getProperty("MerchineID"));
	        //	this.SetTM_ID(Process_properties.getProperty("TM_ID"));
	        //	this.SetTM_AgentNumber( Process_properties.getProperty("TM_AgentNumber"));
	      //  	this.SetTMINVOICENO( Process_properties.getProperty("TM_SerialNo"));
	        	this.SetTransDataTime();
	        	SetTMInfo(tm);
	        }
	        
	        public void SetTMInfo(TMINFO tm)
	        {
	        		TM=tm;
	        		if(TM.TxDatetime==null)
	        			TM.SetTxDatetime(this.DateTxnDateTime);
	         /*
	          		this.sTMTxnData=tm.GetTxDatestr();
	        		this.sTMTxnTime=tm.GetTxTimestr();
	        		this.sTMAgentNumber=tm.GetAgentID();
	        		this.sTMINVOICENO=tm.getTnxInvoiceNo();
	        		this.sTMPOSID=tm.GetPOSID();
	              */
	        } 
}

package CMAS;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.print.DocFlavor.CHAR_ARRAY;

import CMAS.CMASTAG.DataXferControl;
import CMAS.CMASTAG.VersionInfo;
import CMAS.CMASTAG.VersionInfo_type;
import Process.TXTYPE;
import Utilities.DataFormat;

public class CMASTAG {
	
	public static final int 	AUTH_REQ=0;
	public static final int	AUTH_RESP=1;
	public static final int	VERIFY_REQ=2;
	public static final int	VERIFY_RESP=3;
	public static final int	ADV_REQ=4;
	public static final int	ADV_RESP=5;
	public static final int	SG_REQ=6;
	public static final int	SG_RESP=7;
	public static final int	SGADV_REQ=8;
	public static final int	SGADV_RESP=9;
	public static final int	RE_REQ=10;
	public static final int	RE_RESP=11;
	public static final int	LC_REQ=12;
	public static final int	LC_RESP=13;
	public static final int	DEBUG=14;
	public static final int	SETTLE_REQ=15;
	public static final int	SETTLE_RESP=16;

	
	String Name;
	String Value;
	String Type;
	char[] TransFlags;
	String TagName;
	int Len;
	 void CMASTAG(){
		 TransFlags=new char[17];
	}
	public void SetName(String name){
			Name=name;
	}
	public void SetValue(String value){
			Value=value;
	}
	public void SetLen(String len){
		Len=Integer.parseInt(len);
	}
	public void SetType(String type){
			Type=type;
	}
	public void SetTagName(String tagname){
		TagName=tagname;
	}
	
	public void SetTransFlag(char[] Flags )
	{
	
			System.arraycopy(Flags, 0, TransFlags, 0,Flags.length); 
		
	}
	public String GetName()
	{
		return Name;
	}
	public String GetValue()
	{
		return Value;
	}
	public String GetTagName()
	{
		return TagName;
	}
	public String GetType()
	{
		return Type;
	}
	public int GetLength()
	{
		return Len;
	}
	public char[] GetTransFlag(){
		return TransFlags;
	}
	public char GetTransFlag(int TxStatus){
		return TransFlags[TxStatus];
	}

public	String BuildTag0100_MessageType(int inTxnType,int TxnStatus)
{
	
	
	String sMessageType = "";
	switch(inTxnType)
	{
	
		case TXTYPE.SIGNON:
			if(TxnStatus==TransStatus.REQ)
			{
				sMessageType=MessageType.scDevControlReq;//"0800";
			}else if(TxnStatus==TransStatus.ADVREQ){
				sMessageType=MessageType.scDevControlAdv;//"0820";
			}
			break;
        case TXTYPE.ADD:
        case TXTYPE.AUTOLOAD:    
        case TXTYPE.DEDUCT:
	    case TXTYPE.SETVALUE:
        case TXTYPE.AUTOLOADENABLE:	 
	    case TXTYPE.REFUND:	
	    case TXTYPE.VOID:
			if(TxnStatus==TransStatus.REQ)
			{
				sMessageType=MessageType.scAuthorReq;//"0100";
			
			}else if(TxnStatus==TransStatus.ADVREQ){
				sMessageType=MessageType.scFinTranscAdv;//"0220";
            }
			break;               
   	    case  TXTYPE.LOCKCARD:
        case  TXTYPE.REPORT:	
            	sMessageType=MessageType.scFileUPXferAdv;  //0320
            	break;
        case  TXTYPE.SETTLEMENT:	
			if(TxnStatus==TransStatus.REQ)
			{	
				sMessageType=MessageType.scCardAcceptReq;//"0500";
			}		
			break;
		
	}	
	
	return sMessageType;
}

	public String BuildTag0200(byte[] CardID)
{
	String s;
	s=Long.toString(  DataFormat.getLong7Byte(CardID, 0));
	System.out.println(s);
		return s;
}		
public String BuildTag0211(byte[] PID)
{
	String Tag0211_ucPID=new String(PID);
    System.out.format("T0211 PID=%s", Tag0211_ucPID);
	return Tag0211_ucPID;
		
}

public String BuildTag0212(byte autoloadflag)
{
	String Tag0212_ucBasicData_Autoloadflag=Byte.toString(autoloadflag);
    System.out.format("T0211 Autoloadflag=%s", Tag0212_ucBasicData_Autoloadflag);
	return Tag0212_ucBasicData_Autoloadflag;
		
}
public String BuildTag0213(byte  Cardtype)
{
	String Tag0213_ucBasicData_CardType=Byte.toString(Cardtype);
    System.out.format("T0213 CardType=%s",  Tag0213_ucBasicData_CardType);
	return Tag0213_ucBasicData_CardType;
}


public String BuildTag0214(byte  CardProfile)
{
	String Tag0214ucBasicData_CardProfile=Byte.toString(CardProfile);
    System.out.format("T0213 CardProfile=%s",  Tag0214ucBasicData_CardProfile);
	return Tag0214ucBasicData_CardProfile;
}


/*
 *  TxnStatus 	嚙踝蕭嚙踝蕭嚙踝蕭A
 *  TxnType		嚙踝蕭嚙踝蕭嚙踝蕭O
 *  MsgType		0x00嚙踝蕭嚙踝蕭嚙踝蕭嚙� 0x02嚙稼嚙踝蕭/嚙諛動加嚙踝蕭/嚙篁嚙篆/嚙締嚙踝蕭[嚙踝蕭 0x05嚙篁嚙範/嚙褓退/贖嚙稷/嚙諸卡 0x07嚙罷嚙範 0x23 嚙諛動加嚙褓旗嚙請啟伐蕭 					
 *  SubType		MsgType=0x02嚙踝蕭(0x30嚙緹嚙踝蕭嚙稼嚙踝蕭0x31嚙踝蕭嚙衝卡嚙稼嚙踝蕭 0x32嚙瘡嚙諄卡嚙稼嚙踝蕭 0x40嚙諛動加嚙踝蕭 0x0B嚙篁嚙篆 0X0C嚙締嚙踝蕭[嚙踝蕭 )
 *  						MsgType=0x02嚙踝蕭(  0X00嚙篁嚙範 0X1F嚙褓退嚙諸卡 0X20嚙褓退 0X21贖嚙稷  )
 *  						嚙踝蕭L  0x00
 * 							
 *  CardProfile	嚙範嚙踝蕭嚙踝蕭嚙踝蕭嚙瞌
 * 
 */
	String BuildTag0300_ProcessCode(int TxnType,byte MsgType,byte SubType,byte CardProfile, int TxStatus){
		String ProcessCode=new String ("");
		switch(TxnType)
		{
			//se Status_RESET:
	      	case TXTYPE.REPORT:
	      				ProcessCode=ProcessingCode.scStatusReport;//"950007";
	                          break;
	      	case TXTYPE.SIGNON:
	      		ProcessCode=ProcessingCode.scEDCSignOn;//"881999";
				break;
	      	case TXTYPE.ADD:
			case TXTYPE.DEDUCT:
			case TXTYPE.SETVALUE:
	        case TXTYPE.REFUND:	
			case TXTYPE.VOID:
			case TXTYPE.AUTOLOAD:   	
				if(MsgType== 0x02)
				{
					if(SubType == 0x0A)
						ProcessCode=ProcessingCode.scCPUCancelMerchandise;//"823899";
					else if(SubType == 0x0B)
						ProcessCode=ProcessingCode.scCPUReturnMerchandise;//"851999";
					else if(SubType == 0x0C)
						ProcessCode=ProcessingCode.scCPUXformRefundAddCredit;//"839799";
					else if(SubType == 0x30)
					{
						if(CardProfile >= 0x01 && CardProfile <= 0x03)
							ProcessCode=ProcessingCode.scCPUCoBranderCardAutoCredit;//"841799";
						else
							ProcessCode=ProcessingCode.scCPUCashAddCredit;//"811799";
					}else if(SubType == 0x40)
						ProcessCode=ProcessingCode.scCPUAutoAddCredit;//"825799";
					}else if(MsgType == 0x07)
						ProcessCode=ProcessingCode.scCPUSellCard;//"851799";
					else if(MsgType == 0x09)
						ProcessCode=ProcessingCode.scCPUCancelSellCard;//"851899";
					else if(MsgType == 0x0B)
						ProcessCode=ProcessingCode.scCPUCancelAddCredit;//"811899";
					else if(MsgType == 0x01){//
	                             if(TransStatus.REQ==TxStatus)
	                            	   ProcessCode=ProcessingCode.scCPUTXNAuth;//"816399";
	                             else
	                            	   ProcessCode=ProcessingCode.scCPUMerchandise;//"811599";  
	                 }
				break;
	             case TXTYPE.LOCKCARD: 
	            	 ProcessCode=ProcessingCode.scCardLock;//"596100";
	                 break;            
	            case TXTYPE.SETTLEMENT:
	            	ProcessCode=ProcessingCode.scSettlement;//"900000";
	              break;
	            case TXTYPE.AUTOLOADENABLE:	  //AUTOLOADENABLE
	            	ProcessCode=ProcessingCode.scCPUCoBranderCardAutoCredit;//"814799";		
	              break;
	           default:
	        	   ProcessCode="";		
	        	   break;
		}
		    return ProcessCode;
	}
	


public String BuildTag0400(int   TxnAmt)
{
	String Tag0400_lTxnAmt=Integer.toString(TxnAmt);
    System.out.format("T0213 lTxnAmt=%s",  Tag0400_lTxnAmt);
	return Tag0400_lTxnAmt;
}


public String BuildTag0403(int   DeductAmt)
{
	String Tag0403_lDeductAMT=Integer.toString(DeductAmt);
    System.out.format("Tag0403_lDeductAMT=%s",  Tag0403_lDeductAMT);
	return Tag0403_lDeductAMT;
}
public String BuildTag0408(int   EVafterTxn)
{
	String Tag0408_lEVafterTxn=Integer.toString(EVafterTxn);
    System.out.format("Tag0408_lEVafterTxn=%s",  Tag0408_lEVafterTxn);
	return Tag0408_lEVafterTxn;
}

public String BuildTag0407(int   ADDAMT)
{
	String Tag0407_lADDAMT=Integer.toString(ADDAMT);
    System.out.format("Tag0407_lADDAMT=%s",  Tag0407_lADDAMT);
	return Tag0407_lADDAMT;
}


public String BuildTag0409(int AutoloadAMT  )
{
	String Tag0409_lAutoloadAMT=Integer.toString(AutoloadAMT);
    System.out.format("Tag0409_lAutoloadAMT=%s",  Tag0409_lAutoloadAMT);
	return Tag0409_lAutoloadAMT;
}

public String BuildTag0410(int EVBeforeTxn  )
{
	String Tag0410_lEVBeforeTxn=Integer.toString(EVBeforeTxn);
    System.out.format("Tag0410_lEVBeforeTxn=%s",  Tag0410_lEVBeforeTxn);
	return Tag0410_lEVBeforeTxn;
}

public String BuildTag1100(int TerminalInvoiceNum)
{
	String Tag1100_ulTerminalInvoiceNum=Integer.toString(TerminalInvoiceNum);
    System.out.format("Tag1100_ulTerminalInvoiceNum=%s",  Tag1100_ulTerminalInvoiceNum);
	return  Tag1100_ulTerminalInvoiceNum;
	
}

public String BuildTag1101(int ulTMTxnSN)
{
	String Tag1101_ulTMTxnSN=Integer.toString(ulTMTxnSN);
    System.out.format("ulTMTxnSN=%s",  Tag1101_ulTMTxnSN);
	return  Tag1101_ulTMTxnSN;	
}

public String BuildTag1200(Date txnDateTime )
{
   
    SimpleDateFormat ftime =   new SimpleDateFormat ("hhmmss");
    String Tag1200_ucTxnTime=ftime.format(txnDateTime);

    System.out.format("Tag1200_ucTxnTime=%s",  Tag1200_ucTxnTime);
	return Tag1200_ucTxnTime;
}


public String BuildTag1201(String TMTxTime)
{
   
	
    String Tag1201_ucTMTxnTime=TMTxTime;

    System.out.format("Tag1201_ucTMTxnTime=%s",  Tag1201_ucTMTxnTime);
	return Tag1201_ucTMTxnTime;
}

public String BuildTag1300(Date TxnDateTime )
{
    SimpleDateFormat fDate =   new SimpleDateFormat ("yyyyMMdd");
    String Tag1300_ucTxnData=fDate.format(TxnDateTime);
    System.out.format("Tag1300_ucTxnData=%s", Tag1300_ucTxnData);
	return Tag1300_ucTxnData;
}

public String BuildTag1301(String TMTxDate)
{
	String Tag1201_ucTMTxnTime=TMTxDate;
     System.out.format("Tag1201_ucTMTxnTime=%s",  Tag1201_ucTMTxnTime);
	return Tag1201_ucTMTxnTime;
}


public String BuildTag1400(byte[] CardExpiryDate )
{
	int  unix_CardExpDate=DataFormat.getInt(CardExpiryDate,0);
	
	TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	java.util.Date time= new java.util.Date((unix_CardExpDate*1000));
	

    SimpleDateFormat fDate =   new SimpleDateFormat ("YYMM");
    String Tag1400_ucCardExpiryDate=fDate.format(time);
    System.out.format("Tag1400_ucCardExpiryDate=%s", Tag1400_ucCardExpiryDate);
	return Tag1400_ucCardExpiryDate;
}


public String BuildTag1402(byte[] CardExpiryDate )
{
	int  unix_ucCardExpiryDate=DataFormat.getInt(CardExpiryDate,0);
	
	TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	java.util.Date time= new java.util.Date((unix_ucCardExpiryDate*1000));
	

    SimpleDateFormat fDate =   new SimpleDateFormat ("YYYYMMDD");
    String Tag1402_ucCardVaildDate=fDate.format(time);
    System.out.format("Tag1402_ucCardVaildDate=%s", Tag1402_ucCardVaildDate);
	return Tag1402_ucCardVaildDate;
}



public String BuildTag1403(byte[] ucCardVaildDate )
{
	int  unix_ucCardVaildDate=DataFormat.getInt(ucCardVaildDate,0);
	
	TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	java.util.Date time= new java.util.Date((unix_ucCardVaildDate*1000));
	

    SimpleDateFormat fDate =   new SimpleDateFormat ("YYYYMMDD");
    String Tag1403_ucCardVaildDateAfterSet=fDate.format(time);
    System.out.format("Tag1403_ucCardVaildDateAfterSet=%s", Tag1403_ucCardVaildDateAfterSet);
	return Tag1403_ucCardVaildDateAfterSet;
}

public String BuildTag3700(Date TxnDateTime,int TerminalInvoiceNum)
{
	  SimpleDateFormat fDate =   new SimpleDateFormat ("yyyyMMdd");
	  String Tag1300_ucTxnData=fDate.format(TxnDateTime);
	 
	 String Tag1100_ulTerminalInvoiceNum=String.format("%06d",TerminalInvoiceNum);
	
	String Tag3700_ucRRN=Tag1300_ucTxnData+Tag1100_ulTerminalInvoiceNum;
	System.out.format("RRN=%s",Tag3700_ucRRN);
	return Tag3700_ucRRN; 
}

public String BuildTag3701(String TMINVOICENO)
{
	String Tag3701_ucTMINVOICENO=TMINVOICENO;
	System.out.format("Tag3701_ucTMINVOICENO=%s",Tag3701_ucTMINVOICENO);
	return Tag3701_ucTMINVOICENO; 
}

public String BuildTag4100(byte[] NewDeviceID)
{
	String Tag4100_ucCPUDeviceID=DataFormat.bytesToHex(NewDeviceID);
	return Tag4100_ucCPUDeviceID;
}

public String BuildTag4101(byte[] DeviceID)
{
	String Tag4101ucDeviceID=DataFormat.bytesToHex(DeviceID);
	return Tag4101ucDeviceID;
}

public String BuildTag4102(String LocalIP){
	String Tag4102_ucTerminalIP=LocalIP;
	return Tag4102_ucTerminalIP;
}
public String BuildTag4103(String MechineID)
{
	String Tag4103_ucMechineID=MechineID;
	return Tag4103_ucMechineID;
}


public String BuildTag4104(byte[] ReaderSN)
{
	String Tag4104_ucReaderSN=DataFormat.bytesToHex(ReaderSN);
	return Tag4104_ucReaderSN;
}


public String BuildTag4200(String CPUSPID)
{
	String Tag4200_ucCPUSPID=CPUSPID;
    System.out.format("ulTMTxnSN=%s",  Tag4200_ucCPUSPID);
	return  CPUSPID;	
}

public String BuildTag4201(int NewLocationID)
{
	String Tag4201_NewLocationID=Integer.toString(NewLocationID);
    System.out.format("ulTMTxnSN=%s",  Tag4201_NewLocationID);
	return  Tag4201_NewLocationID;	
}

public String BuildTag4210(int SubMerchantID)
{
	String Tag4210_ucSubMerchantID=Integer.toString(SubMerchantID);
    System.out.format("Tag4210_ucSubMerchantID=%s",  Tag4210_ucSubMerchantID);
	return  Tag4210_ucSubMerchantID;	
}

public String BuildTag4800(int CPUPurseVersionNUM)
{
	String Tag4800_ucCPUPurseVersionNUM=Integer.toString(CPUPurseVersionNUM);
    System.out.format("Tag4800_ucCPUPurseVersionNUM=%s",  Tag4800_ucCPUPurseVersionNUM);
	return  Tag4800_ucCPUPurseVersionNUM;	
}


public String BuildTag4801(byte[] AVRDATA)
{
	String Tag4801_ucCardAVRDATA=DataFormat.bytesToHex(AVRDATA);
	return Tag4801_ucCardAVRDATA;
}


public String BuildTag4802(int issuecode)
{
	String Tag4802_ucIssuerCode=Integer.toString(issuecode);
	return Tag4802_ucIssuerCode;
}


public String BuildTag4803(int BankCode)
{
	String Tag4803_ucBankCode=Integer.toString(BankCode);
	return Tag4803_ucBankCode;
}

public String BuildTag4804(int areacode)
{
	String Tag4804_ucAreaCode=Integer.toString(areacode);
	return Tag4804_ucAreaCode;
}


public String BuildTag4805(byte[] SubAreaCode)
{
	String Tag4805_ucCPUSubAreaCode=DataFormat.bytesToHex(SubAreaCode);
	return Tag4805_ucCPUSubAreaCode;
}


public String BuildTag4806(byte[] ProfileExpiryDate)
{
	String Tag4806_ulProfileExpiryDate=DataFormat.bytesToHex(ProfileExpiryDate);
	return Tag4806_ulProfileExpiryDate;
}


public String BuildTag4808(int lCardTxnSN)
{
	String Tag4808_ulCardTxnSN=Integer.toString(lCardTxnSN);
	return Tag4808_ulCardTxnSN;
}


public String BuildTag4809(int TM)
{
	String Tag4809_ucCPUTxnMode=Integer.toString(TM);
	return Tag4809_ucCPUTxnMode;
}


public String BuildTag4810(int TQ)
{
	String Tag4810_ucCPUTQ=Integer.toString(TQ);
	return Tag4810_ucCPUTQ;
}


public String BuildTag4811(int SNBeforeTxn)
{
	String Tag4811_ulSNBeforeTxn=Integer.toString(SNBeforeTxn);
	return Tag4811_ulSNBeforeTxn;
}


public String BuildTag4812(byte[] CTC)
{
	String Tag4812_ucCTC=DataFormat.bytesToHex(CTC);
	return Tag4812_ucCTC;
}


public String BuildTag4813(int LoyalityCounter)
{
	String Tag4813_usLoyaltyCounter=Integer.toString(LoyalityCounter);
	return Tag4813_usLoyaltyCounter;
}


public String BuildTag4814(int Deposit)
{
	String Tag4814_ulDepositValue=Integer.toString(Deposit); 
	return Tag4814_ulDepositValue;
}


public String BuildTag4818(byte[] ANOTHEREV)
{
	String Tag4818_ucANOTHEREV=DataFormat.bytesToHex(ANOTHEREV);
	return Tag4818_ucANOTHEREV;
}

public String BuildTag4819(int LockReason)
{
	String Tag4819_ucLockReason=Integer.toString(LockReason);
	return Tag4819_ucLockReason;
}


public String BuildTag4820(int HostSpecVersionNo)
{
	String Tag4820_ucHostSpecVersionNo=Integer.toString(HostSpecVersionNo);
	return Tag4820_ucHostSpecVersionNo;
}



public String BuildTag4821(String CardParameter)
{
	String Tag4821_CardParameter=CardParameter;
	return Tag4821_CardParameter;
}


public String BuildTag4823(int OneDayQuotaWrite)
{
	String Tag4823_ucOneDayQuotaWrite=Integer.toString(OneDayQuotaWrite);
	return Tag4823_ucOneDayQuotaWrite;
}


public String BuildTag4824(int CPDReadFlag)
{
	String Tag4824_ucCPDReadFlag=Integer.toString(CPDReadFlag);
	return Tag4824_ucCPDReadFlag;
}


public String BuildTag4825(int CreditBalanceChangeFlag)
{
	String Tag4825_ucCPUCreditBalanceChangeFlag=Integer.toString(CreditBalanceChangeFlag);
	return Tag4825_ucCPUCreditBalanceChangeFlag;
}
public String BuildTag4826(int ChipIDLength)
{
	String Tag4825_ucCPUCreditBalanceChangeFlag=Integer.toString(ChipIDLength);
	return Tag4825_ucCPUCreditBalanceChangeFlag;
}

public String BuildTag4827(String CPUCardParameter)
{
	String Tag4827_CPUCardParameter=CPUCardParameter;
	return Tag4827_CPUCardParameter;
}


public String BuildTag4828(String CPUCardParameter)
{
	String Tag4827_CPUCardParameter=CPUCardParameter;
	return Tag4827_CPUCardParameter;
}

public String BuildTag4829(String CPUCardSettingPara )
{
	String Tag4829_ucMicroPaymentSetting=CPUCardSettingPara;
	return Tag4829_ucMicroPaymentSetting;
}

public String BuildTag5301(int SamKVN){
	String Tag5301_KeyVersionSamKVN=Integer.toString(SamKVN);
	return  Tag5301_KeyVersionSamKVN;
}

public String BuildTag5302(String CPUCardKeyInfo )
{
	String Tag5302_CPUCardKeyInfo=CPUCardKeyInfo;
	return Tag5302_CPUCardKeyInfo;
}


public String BuildTag5303(int CPUHashTYPE )
{
	String Tag5303_ucCPUHashTYPE=Integer.toHexString(CPUHashTYPE);
	return Tag5303_ucCPUHashTYPE;
}



public String BuildTag5304(int HostadminKVN )
{
	String Tag5304_ucCPUHostadminKVN=Integer.toString(HostadminKVN);
	return Tag5304_ucCPUHostadminKVN;
}

public String BuildTag5305(int SigntureKeyKVN )
{
	String Tag5305_ucSigntureKeyKVN=Integer.toString(SigntureKeyKVN);
	return Tag5305_ucSigntureKeyKVN;
}

public String BuildTag5306(byte[] CPUEDC )
{
	String Tag5306_ucCPUEDC=DataFormat.bytesToHex(CPUEDC);
	return Tag5306_ucCPUEDC;
}


public String BuildTag5307(byte[] RSAM )
{
	String Tag5307_ucRSAM=DataFormat.bytesToHex(RSAM);
	return Tag5307_ucRSAM;
}


public String BuildTag5308(byte[] RHOST )
{
	String Tag5308_ucRHOST=DataFormat.bytesToHex(RHOST);
	return Tag5308_ucRHOST;
}


public String BuildTag5361(byte[] SAMID )
{
	String Tag5361_ucSAMID=DataFormat.bytesToHex(SAMID);
	return Tag5361_ucSAMID;
}

public String BuildTag5362(byte[] SAMSN )
{
	String Tag5362_ucSAMSN=DataFormat.bytesToHex(SAMSN);
	return Tag5362_ucSAMSN;
}

public String BuildTag5363(byte[] SAMCRN )
{
	String Tag5363_ucSAMCRN=DataFormat.bytesToHex(SAMCRN);
	return Tag5363_ucSAMCRN;
}

public String BuildTag5364(String SAMINFO )
{
	String Tag5364_CPUSAMINFOData=SAMINFO;
	return Tag5364_CPUSAMINFOData;
}


public String BuildTag5365(String SAMtransInfoData )
{
	String Tag5365_SAMtransInfoData=SAMtransInfoData;
	return Tag5365_SAMtransInfoData;
}


public String BuildTag5367(String CSAMPARA )
{
	String Tag5367_CSAMPARA=CSAMPARA;
	return Tag5367_CSAMPARA;
}


public String BuildTag5366(byte[] SingleCreditTxnAmtLimit )
{
	String Tag5366_ucSingleCreditTxnAmtLimit=DataFormat.bytesToHex(SingleCreditTxnAmtLimit);
	return Tag5366_ucSingleCreditTxnAmtLimit;
}


public String BuildTag5368(byte[] STC )
{
	String Tag5368_ucSTC=DataFormat.bytesToHex(STC);
	return Tag5368_ucSTC;
}


public String BuildTag5369(int SAMSignOnControlFlag )
{
	String Tag5369_ucSAMSignOnControlFlag=Integer.toString(SAMSignOnControlFlag);
	return Tag5369_ucSAMSignOnControlFlag;
}
public String BuildTag5370(String CPULastSignonInfoData )
{
	String Tag5370_CPULastSignonInfoData=CPULastSignonInfoData;
	return Tag5370_CPULastSignonInfoData;
}

public String BuildTag5371(byte[] CPUSAMID )
{
	String Tag5371_ucCPUSAMID=DataFormat.bytesToHex(CPUSAMID);
	return Tag5371_ucCPUSAMID;
}

public String BuildTag5501(String BatchNo )
{
	String Tag5501_ucBatchNo=BatchNo;
	return Tag5501_ucBatchNo;
}

public String BuildTag5503(int  STCODE )
{
	String Tag5503_ulSTCode=Integer.toString(STCODE);
	return Tag5503_ulSTCode;
}

public String BuildTag5504(String  TMID )
{
	String Tag5504_ucTMPOSID=TMID;
	return Tag5504_ucTMPOSID;
}

public String BuildTag5509(int  EDCTYPE )
{
	String Tag5509_EDCTYPE=Integer.toString(EDCTYPE);
	return Tag5509_EDCTYPE;
}

public String BuildTag5510(String  TMAgentNo )
{
	String Tag5510_ucTMPOSID=TMAgentNo;
	return Tag5510_ucTMPOSID;
}



public String BuildTag5581(byte[]  OrgTxnTerminalID )
{
	String Tag5581_ucOrgTxnTerminalID=DataFormat.bytesToHex(OrgTxnTerminalID);
	return Tag5581_ucOrgTxnTerminalID;
}

public String BuildTag5582(byte[]  OrgTxnRRN )
{
	String Tag5582_ucOrgTxnRRN=DataFormat.bytesToHex(OrgTxnRRN);
	return Tag5582_ucOrgTxnRRN;
}

public String BuildTag5583(byte[]  OrgTxnDate )
{
	String Tag5583_ucOrgTxnDate=DataFormat.bytesToHex(OrgTxnDate);
	return Tag5583_ucOrgTxnDate;
}


public String BuildTag5591(int   TotleCnt )
{
	String Tag5591_ulSettle_TotleCnt=Integer.toString(TotleCnt);
	return Tag5591_ulSettle_TotleCnt;
}


/* var, 嚙踝蕭嚙踝蕭嚙踝蕭T, M/M/, 嚙踝蕭嚙緝嚙豎伐蕭嚙稽嚙複迎蕭嚙璀嚙踝蕭T55880x */
public static final String scVersionInfo = "T5588";
public enum VersionInfo_type {
	ssl,			// SSL嚙踝蕭嚙踝蕭
	blackList,		// 嚙蝓名嚙賣版嚙踝蕭
	devProgVer,		// 嚙豎伐蕭嚙稽嚙複程嚙踝蕭嚙踝蕭嚙踝蕭
	devParamVer,	// 嚙豎伐蕭嚙稽嚙複參數迎蕭嚙踝蕭
	dongleProg		// Dongle嚙緹嚙踝蕭嚙踝蕭嚙踝蕭
};
	/* an 2, 嚙踝蕭嚙璀嚙踝蕭T嚙踝蕭嚙踝蕭, 01: SSL嚙踝蕭嚙踝蕭, 02: 嚙蝓名嚙賣版嚙踝蕭, 03: 嚙豎伐蕭嚙稽嚙複程嚙踝蕭嚙踝蕭嚙踝蕭, 04: 嚙豎伐蕭嚙稽嚙複參數迎蕭嚙踝蕭, 05: Dongle嚙緹嚙踝蕭嚙踝蕭嚙踝蕭 */
	public static final String scVersionInfo_type = "T558801";
	public static final String scVersionInfo_type_ssl = "01";
	public static final String scVersionInfo_type_backList = "02";
	public static final String scVersionInfo_type_devProgVer = "03";
	public static final String scVersionInfo_type_devParamVer = "04";
	public static final String scVersionInfo_type_dongleProg = "05";
	/* 0~30bytes, 嚙踝蕭嚙璀嚙踝蕭T嚙締嚙踝蕭嚙踝蕭, Key嚙踝蕭嚙踝蕭嚙踝蕭嚙踝蕭嚙踝蕭J, 嚙蝓名嚙踝蕭嚙褕殷蕭嚙踝蕭嚙踝蕭, 嚙豎伐蕭嚙稽嚙複程嚙踝蕭嚙瞇嚙踝蕭program ID, 嚙豎伐蕭嚙稽嚙複參數歹蕭嚙踝蕭J*/
	public static final String scVersionInfo_subtype = "T558802";
	/* an 0~30, 嚙踝蕭嚙璀嚙踝蕭T嚙踝蕭嚙踝蕭嚙踝蕭嚙箴, SSL嚙踝蕭嚙踝蕭嚙編嚙踝蕭, 嚙蝓名嚙賣版嚙踝蕭嚙編嚙踝蕭, 嚙豎伐蕭嚙稽嚙複程嚙踝蕭嚙踝蕭嚙踝蕭嚙編嚙踝蕭, Dongle嚙緹嚙踝蕭嚙踝蕭嚙踝蕭嚙編嚙踝蕭, 嚙踝蕭L嚙踝蕭嚙踝蕭嚙編嚙踝蕭 */
	public static final String scVersionInfo_content = "T558803";

	
public class VersionInfo {
	VersionInfo_type type;
	String VersionInfo_type;
	String VersionInfo_subtype;
	String VersionInfo_content;
};


public String  Build_Tag5588(String SSLVER,String BLCVER,String APVER,String ParaVER) {

	String s="";
	/* T5588, 版本資訊, M/M/ , 內崁端末設備狀態資訊55880x */
	VersionInfo infoSSL =new VersionInfo();
	infoSSL.type = VersionInfo_type.ssl;
	infoSSL.VersionInfo_content =SSLVER;
	String SSLstr=sGetVersionInfo(infoSSL);
	s += SSLstr;

	
	VersionInfo infoBlackList = new VersionInfo();
	infoBlackList.type = VersionInfo_type.blackList;
	infoBlackList.VersionInfo_subtype = "";
	infoBlackList.VersionInfo_content =BLCVER;	// ???
	String Blcstr=sGetVersionInfo(infoBlackList);
	s =s+Blcstr;
	
	
	VersionInfo infoProgVer =new VersionInfo();
	infoProgVer.type = VersionInfo_type.devProgVer;
	infoProgVer.VersionInfo_subtype = "ECCAPP";
	infoProgVer.VersionInfo_content =APVER;	// ???

	String APstr=sGetVersionInfo(infoProgVer);
	s=s+APVER;
	
	VersionInfo infoParamVer =new VersionInfo();
	infoParamVer.type = VersionInfo_type.devParamVer;
	infoParamVer.VersionInfo_content = ParaVER;	// ???
	String ParaVer=sGetVersionInfo(infoParamVer);
	s += ParaVer;

	return s;	
}
public String sGetVersionInfo(VersionInfo info) {
	String s = "";
	if (info == null) {
		return s;
	}
	
	switch (info.type) {
	case ssl:			// SSL嚙踝蕭嚙踝蕭
		s += s = "<" + scVersionInfo_type + ">" + "01" + "</" + scVersionInfo_type + ">";
		if (info.VersionInfo_content == null) {
			s += s = "<" + scVersionInfo_content + ">" + "</" + scVersionInfo_content + ">";
		} else {
			s += s = "<" + scVersionInfo_content + ">" + info.VersionInfo_content + "</" + scVersionInfo_content + ">";
		}
		break;
	case blackList:	// 嚙蝓名嚙賣版嚙踝蕭
		s += s = "<" + scVersionInfo_type + ">" + "02" + "</" + scVersionInfo_type + ">";
		if (info.VersionInfo_subtype == null) {
			s += s = "<" + scVersionInfo_subtype + ">" + "</" + scVersionInfo_subtype + ">";
		} else {
			s += s = "<" + scVersionInfo_subtype + ">" + info.VersionInfo_subtype + "</" + scVersionInfo_subtype + ">";
		}
		if (info.VersionInfo_content == null) {
			s += s = "<" + scVersionInfo_content + ">" + "</" + scVersionInfo_content + ">";
		} else {
			s += s = "<" + scVersionInfo_content + ">" + info.VersionInfo_content + "</" + scVersionInfo_content + ">";
		}
		break;
	case devProgVer:	// 嚙豎伐蕭嚙稽嚙複程嚙踝蕭嚙踝蕭嚙踝蕭
		s += s = "<" + scVersionInfo_type + ">" + "03" + "</" + scVersionInfo_type + ">";
		if (info.VersionInfo_subtype == null) {
			s += s = "<" + scVersionInfo_subtype + ">" + "</" + scVersionInfo_subtype + ">";
		} else {
			s += s = "<" + scVersionInfo_subtype + ">" + info.VersionInfo_subtype + "</" + scVersionInfo_subtype + ">";
		}
		if (info.VersionInfo_content == null) {
			s += s = "<" + scVersionInfo_content + ">" + "</" + scVersionInfo_content + ">";
		} else {
			s += s = "<" + scVersionInfo_content + ">" + info.VersionInfo_content + "</" + scVersionInfo_content + ">";
		}
		break;
	case devParamVer:	// 嚙豎伐蕭嚙稽嚙複參數迎蕭嚙踝蕭
		s += s = "<" + scVersionInfo_type + ">" + "04" + "</" + scVersionInfo_type + ">";
		if (info.VersionInfo_content == null) {
			s += s = "<" + scVersionInfo_content + ">" + "</" + scVersionInfo_content + ">";
		} else {
			s += s = "<" + scVersionInfo_content + ">" + info.VersionInfo_content + "</" + scVersionInfo_content + ">";
		}
		break;
	case dongleProg:
		s += s = "<" + scVersionInfo_type + ">" + "05" + "</" + scVersionInfo_type + ">";
		s += s = "<" + scVersionInfo_content + ">" + "???" + "</" + scVersionInfo_content + ">";
		break;
	}
	s = "<" + scVersionInfo + ">" + s + "</" + scVersionInfo + ">";
	return s;
}

/* n 6, 嚙踝蕭嚙踝蕭`嚙踝蕭嚙� 嚙課佗蕭message type=0200嚙踝蕭嚙稼嚙窯, 嚙瘡batchno嚙踝蕭嚙緘嚙踝蕭嚙蝓�*/
public static final String scSettleCounts = "T5592";
/*嚙踝蕭嚙踝蕭`嚙踝蕭嚙踝蕭*/
public static final String scSettleCounts_totalAMT= "T559201";
/* n 8, 嚙踝蕭嚙踝蕭[嚙瘢嚙窯嚙踝蕭嚙踝蕭 */
public static final String scSettleCounts_totalPointAdd= "T559202";
/* n 8, 嚙踝蕭嚙踝蕭嚙踝蕭I嚙窯嚙踝蕭嚙踝蕭*/
public static final String scSettleCounts_totalPointDeduct = "T559203";

String Build_Tag5592( int BatchCnt)
{
	String s="";   	
	s += s = "<" + scSettleCounts + ">" + "02" + "</" + scSettleCounts + ">";
	
	s += s = "<" + scSettleCounts_totalAMT + ">" +String.valueOf(BatchCnt) + "</" + scSettleCounts_totalAMT + ">";


	s += s = "<" + scSettleCounts_totalPointAdd + ">"  +"</" + scSettleCounts_totalPointAdd + ">";

	s += s = "<" + scSettleCounts_totalPointDeduct + ">"+  "</" + scSettleCounts_totalPointDeduct + ">";
	
//	s = "<" + scSettleCounts + ">" + s + "</" + scSettleCounts + ">";
	
	return s;
}

/* var, 嚙踝蕭e嚙踝蕭嚙踝蕭,  */
public static final String scDataXferControl = "T5596";
	/* n 8, 嚙窯嚙踝蕭嚙踝蕭 */
	public static final String scDataXferControl_total = "T559601";
	/* n 8, 嚙緩嚙褒送嚙踝蕭嚙踝蕭 */
	public static final String scDataXferControl_xferred = "T559602";
	/* n 8, 嚙緩嚙踝蕭嚙踝蕭嚙踝蕭嚙踝蕭 */
	public static final String scDataXferControl_received = "T559603";
	/* n 8, 嚙褒送嚙褒賂蕭 */
	public static final String scDataXferControl_sn = "T559604";
	
public class DataXferControl{
	int total; 			/* n 8, 嚙窯嚙踝蕭嚙踝蕭 */
	int xferred;		/* n 8, 嚙緩嚙褒送嚙踝蕭嚙踝蕭 */
	int received;		/* n 8, 嚙緩嚙踝蕭嚙踝蕭嚙踝蕭嚙踝蕭 */		
	int sn;				/* n 8, 嚙褒送嚙褒賂蕭 */
};



public String  Build_Tag5596(DataXferControl xferControl) {
	String s = "";
	if (xferControl == null) {
		return "";
	}
	String total = String.format("%08d", xferControl.total);
	s = "<" + scDataXferControl_total + ">" + total + "</" + scDataXferControl_total + ">";
	String xferred = String.format("%08d", xferControl.xferred);
	s += "<" + scDataXferControl_xferred + ">" + xferred + "</" + scDataXferControl_xferred + ">";
	String received = String.format("%08d", xferControl.received);
	s += "<" + scDataXferControl_received + ">" + received + "</" + scDataXferControl_received + ">";
	String sn = String.format("%08d", xferControl.sn);
	s += "<" + scDataXferControl_sn + ">" + sn + "</" + scDataXferControl_sn + ">";
	//s = "<" + scDataXferControl + ">" + s + "</" + scDataXferControl + ">";
	return s;
}
public String BuildTag6000(byte[] ReaderFWVersion )
{
	String Tag6000_ucReaderFWVersion=DataFormat.bytesToHex(ReaderFWVersion);
	return Tag6000_ucReaderFWVersion;
}


public String BuildTag6001(byte[] AVR )
{
	String Tag6001_ReaderAVRDATA=DataFormat.bytesToHex(AVR);
	return Tag6001_ReaderAVRDATA;
}


public String BuildTag6002(String TermHost )
{
	String Tag6002_stTermHostInfo_t=TermHost;
	return Tag6002_stTermHostInfo_t;
}

public String BuildTag6003(String TermPara )
{
	String Tag6003_stTermParaInfo_t=TermPara;
	return Tag6003_stTermParaInfo_t;
}

public String BuildTag6004(int BLVersion )
{
	String Tag6004_ulBLVersion=Integer.toString(BLVersion);
	return Tag6004_ulBLVersion;
}

public String BuildTag6400(byte[] STAC )
{
	String Tag6400_ucSTAC=DataFormat.bytesToHex(STAC);
	return Tag6400_ucSTAC;
}

public String BuildTag6401(byte[] HTAC )
{
	String Tag6401_HTAC=DataFormat.bytesToHex(HTAC);
	return Tag6401_HTAC;
}

public String BuildTag6402(byte[] CTAC )
{
	String Tag6402_CTAC=DataFormat.bytesToHex(CTAC);
	return Tag6402_CTAC;
}

public String BuildTag6403(byte[] MAC )
{
	String Tag6403_MAC=DataFormat.bytesToHex(MAC);
	return Tag6403_MAC;
}

public String BuildTag6404(byte[] CPUMAC )
{
	String Tag6404_CPUMAC_HCrypto=DataFormat.bytesToHex(CPUMAC);
	return Tag6404_CPUMAC_HCrypto;
}


public String BuildTag6405(byte[] CPUSignature )
{
	String Tag6405_CPUSignature=DataFormat.bytesToHex(CPUSignature);
	return Tag6405_CPUSignature;
}


public String BuildTag6406(byte[] TermCrypto )
{
	String Tag6406_CPUTermCrypto=DataFormat.bytesToHex(TermCrypto);
	return Tag6406_CPUTermCrypto;
}

public String BuildTag6407(byte[] HostCrypto )
{
	String Tag6407_HostCrypto=DataFormat.bytesToHex(HostCrypto);
	return Tag6407_HostCrypto;
}

public String BuildTag6408(byte[] SATOKEN )
{
	String Tag6408_ucSATOKEN=DataFormat.bytesToHex(SATOKEN);
	return Tag6408_ucSATOKEN;
}

public String BuildTag6409(byte[] HostToken )
{
	String Tag6409_HostToken=DataFormat.bytesToHex(HostToken);
	return Tag6409_HostToken;
}

}


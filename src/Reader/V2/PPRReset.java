package Reader.V2;

import org.apache.log4j.Logger;

import exception.RespCode;
import Reader.EZReader;
import Utilities.DataFormat;
import Utilities.EZLib;



public class PPRReset {

	
	static Logger logger = Logger.getLogger(EZReader.class);
	//in field	
	private byte tmLocationId[] = new byte[10];//10
	private byte tmId[] = {0x30,0x30};//2. default:"00"
	private byte tmTxnDateTime[] = new byte[14];//14
	private byte tmSerialNumber[] = new byte[6];//6
	private byte tmAgentNumber[] = new byte[4];//4
	private byte txnUnixDateTime[] = new byte[4];//4
	private byte locationId;
	private byte newLocationId[] = new byte[2];//2
	private byte serviceProviderId;
	private byte newServiceProviderId[] = new byte[3];//3
	private byte oneDayQuotaWriteFlagSets;
	private byte oneDayQuota[] = new byte[2];//2
	private byte onceDayQuota[] = new byte[2];//2
	private byte samSlotControlFlag=0x11;//default:0x11
	private byte RFU[]=new byte[11];//11
	
	private String respCode;
	
	//out field
	private byte specVersionNumber;
	private byte readerID[]  = new byte[4]; //4
	private byte readerFWVersion[] = new byte[6];//6
	
	//---old sam field
	private byte samID[] = new byte[8]; //8
	private byte samSN[] = new byte[4];//4
	private byte samCRN[] = new byte[8];//8
	private byte deviceID[] = new byte[4];//4
	private byte samKeyVersion;
	private byte STAC[] = new byte[8];
	
	//---new sam 17 field
	private byte samVersionNumber;
	private byte SID[] = new byte[8];
	private byte samUsageControl[] = new byte[3];
	private byte samAdminKVN;
	private byte samIssuerKVN;
	private byte authorizedCreditLimit[] = new byte[3];
	private byte singleCreditTxnAmtLimit[] = new byte[3];
	private byte authorizedCreditBalance[] = new byte[3];
	private byte authorizedCreditCumulative[] = new byte[3];
	private byte authorizedCancelCreditCumulative[] = new byte[3];
	private byte newDeviceID[] = new byte[6];
	private byte tabListTable[] = new byte[40];
	private byte samIssuerSecificData[] = new byte[32];
	private byte STC[] = new byte[4];
	private byte RSAM[] = new byte[8];
	private byte RHOST[] = new byte[8];
	private byte SATOKEN[] = new byte[16];
	//---
	private byte CPDReadFlag;
	private byte oneDayQuotaWritForMicroPayment;	
	private byte samSignOnControlFlag;
	private boolean checkEVFlagForMifareOnly;
	private boolean merchantLimitUseForMicroPayment;
	//---
	private byte oneDayQuotaFlagForMicroPayment;	
	private boolean onceQuotaFlagForMicroPayment;
	private boolean checkDebitFlag;
	private boolean mifareCheckEnableFlag;
	private boolean payOnBehalfFlag;
	private byte RFU_oneDayQuotaFlagForMicroPaymentGroup;
	//
	
	private byte oneDayQuotaForMicroPayment[] = new byte[2];
	private byte onceQuotaForMicroPayment[] = new byte[2];
	private byte checkDebitValue[] = new byte[2];
	
	//---old Quota control
	private boolean addQuotaFlag;
	private byte addQuota[] = new byte[3];
	private byte theReminderOfAddQuota[] = new byte[3];
	private byte cancelCreditQuota[] = new byte[3];
	//---
	private byte deMACParameter[] = new byte[8];
	private byte lastTxnDateTime[] = new byte[4];
	//new sam confirm
	private byte previousNewDeviceId[] = new byte[6];
	private byte previousSTC[] = new byte[4];
	private byte previousTxnDateTime[] = new byte[4];
	private byte previousCreditBalanceChangeFlag;
	private byte previousConfirmCode[] = new byte[2];
	private byte previousCACrypto[] = new byte[16];

	//constructor
	public PPRReset()
	{
		//...todo
	}
	
	public byte[] packApdu()
	{
		
			int p;
			byte tempArray[] = new byte[64];
			byte data[] = new byte[74];//3+5+64+1+1
			data[0] = 0x00;data[1] = 0x00;data[2] = 0x46;
			data[3] = (byte) 0x80;data[4] = 0x01;data[5] = 0x00;data[6] = (byte) 0x80;data[7] = 0x40;
			p=8;
			try
			{
				logger.info("Start");
				//TM Location ID(10bytes)
				System.arraycopy(tmLocationId, 0, data, p, tmLocationId.length);
				p+=tmLocationId.length;
				logger.debug("tmLocationId:"+tmLocationId.length+", p="+p);
				
				//TM ID(2)
				System.arraycopy(tmId, 0, data, p, tmId.length);
				p+=tmId.length;
				logger.debug("tmId:"+tmId.length+", p="+p);
				
				//TM Txn Date Time(14)
				System.arraycopy(tmTxnDateTime, 0, data, p, tmTxnDateTime.length);
				p+=tmTxnDateTime.length;
				logger.debug("tmTxnDateTime:"+tmTxnDateTime.length+", p="+p);
				
				//TM Serial Number(6)
				System.arraycopy(tmSerialNumber, 0, data, p, tmSerialNumber.length);
				p+=tmSerialNumber.length;
				logger.debug("tmSerialNumber:"+tmSerialNumber.length+", p="+p);
		
				//TM Agent Number(4)
				System.arraycopy(tmAgentNumber, 0, data, p, tmAgentNumber.length);
				p+=tmAgentNumber.length;
				logger.debug("tmAgentNumber:"+tmAgentNumber.length+", p="+p);
				
		
				//TXN Date Time(4) UnixTime, LSB
				System.arraycopy(this.txnUnixDateTime, 0, data, p, txnUnixDateTime.length);
				p+=txnUnixDateTime.length;
				logger.debug("txnUnixDateTime:"+txnUnixDateTime.length+", p="+p);
				
				
				//Location Id(1)
				data[p] = locationId;
				p+=1;
				logger.debug("locationId, p="+p);
				
				//new Location ID(2)
				System.arraycopy(this.newLocationId, 0, data, p, this.newLocationId.length);
				p+=this.newLocationId.length;
				logger.debug("newLocationId:"+newLocationId.length+", p="+p);
				
				//service Provider ID(1)
				data[p] = this.serviceProviderId;		
				p+=1;
				logger.debug("locationId, p="+p);
				
				//new service Provider ID(3)			
				System.arraycopy(this.newServiceProviderId, 0, data, p, this.newServiceProviderId.length);
				p += this.newServiceProviderId.length;
				logger.debug("newServiceProviderId:"+newServiceProviderId.length+", p="+p);
				
				//skip 5 bytes
				p+=1+2+2;
				logger.debug("skip, p="+p);
				
				// SAM Slot Control flag(1)
				data[p] = this.samSlotControlFlag;
				p+=1;
				logger.debug("samSlotControlFlag, p="+p);
				
				//RFU
				p+=11;
				logger.debug("RFU, p="+p);
				
				logger.debug("ready pack Le, now p:"+p);
				//Le
				data[p] = (byte)0xfa;
				p+=1;
				
				//LRC		
				data[p] = EZLib.calCheckSum(p, data);
				
				logger.debug("pack APDU command:"+EZLib.hex2StringLog(data));
				logger.info("End");
			
			}catch(Exception e)
			{
				logger.error("exception: "+e.getMessage());
			}
			
		return data;
	}
	
	
	public int unPackApdu(byte data[])
	{
		//total:256 bytes
		int result = RespCode.SUCCESS.getId();
		int len = data.length;
		

		
		
		
		
		return result;
	}

	public byte[] getTmLocationId() {
		return tmLocationId;
	}

	public void setTmLocationId(byte[] tmLocationId) {
		this.tmLocationId = tmLocationId;
	}

	public byte[] getTmId() {
		return tmId;
	}

	public void setTmId(byte[] tmId) {
		this.tmId = tmId;
	}

	public byte[] getTmTxnDateTime() {
		return tmTxnDateTime;
	}

	public void setTmTxnDateTime(byte[] tmTxnDateTime) {
		this.tmTxnDateTime = tmTxnDateTime;
	}

	public byte[] getTmSerialNumber() {
		return tmSerialNumber;
	}

	public void setTmSerialNumber(byte[] tmSerialNumber) {
		this.tmSerialNumber = tmSerialNumber;
	}

	public byte[] getTmAgentNumber() {
		return tmAgentNumber;
	}

	public void setTmAgentNumber(byte[] tmAgentNumber) {
		this.tmAgentNumber = tmAgentNumber;
	}

	public byte[] getTxnUnixDateTime() {
		return txnUnixDateTime;
	}

	public void setTxnUnixDateTime(byte[] txnUnixDateTime) {
		this.txnUnixDateTime = txnUnixDateTime;
	}

	public byte getLocationId() {
		return locationId;
	}

	public void setLocationId(byte locationId) {
		this.locationId = locationId;
	}

	public byte[] getNewLocationId() {
		return newLocationId;
	}

	public void setNewLocationId(byte[] newLocationId) {
		this.newLocationId = newLocationId;
	}

	public byte getServiceProviderId() {
		return serviceProviderId;
	}

	public void setServiceProviderId(byte serviceProviderId) {
		this.serviceProviderId = serviceProviderId;
	}

	public byte[] getNewServiceProviderId() {
		return newServiceProviderId;
	}

	public void setNewServiceProviderId(byte[] newServiceProviderId) {
		this.newServiceProviderId = newServiceProviderId;
	}

	public byte getOneDayQuotaWriteFlagSets() {
		return oneDayQuotaWriteFlagSets;
	}

	public void setOneDayQuotaWriteFlagSets(byte oneDayQuotaWriteFlagSets) {
		this.oneDayQuotaWriteFlagSets = oneDayQuotaWriteFlagSets;
	}

	public byte[] getOneDayQuota() {
		return oneDayQuota;
	}

	public void setOneDayQuota(byte[] oneDayQuota) {
		this.oneDayQuota = oneDayQuota;
	}

	public byte[] getOnceDayQuota() {
		return onceDayQuota;
	}

	public void setOnceDayQuota(byte[] onceDayQuota) {
		this.onceDayQuota = onceDayQuota;
	}

	public byte getSamSlotControlFlag() {
		return samSlotControlFlag;
	}

	public void setSamSlotControlFlag(byte samSlotControlFlag) {
		this.samSlotControlFlag = samSlotControlFlag;
	}

	public byte[] getRFU() {
		return RFU;
	}

	public void setRFU(byte[] rFU) {
		RFU = rFU;
	}

	public String getRespCode() {
		return respCode;
	}

	public byte getSpecVersionNumber() {
		return specVersionNumber;
	}

	public byte[] getReaderID() {
		return readerID;
	}

	public byte[] getReaderFWVersion() {
		return readerFWVersion;
	}

	public byte[] getSamID() {
		return samID;
	}

	public byte[] getSamSN() {
		return samSN;
	}

	public byte[] getSamCRN() {
		return samCRN;
	}

	public byte[] getDeviceID() {
		return deviceID;
	}

	public byte getSamKeyVersion() {
		return samKeyVersion;
	}

	public byte[] getSTAC() {
		return STAC;
	}

	public byte getSamVersionNumber() {
		return samVersionNumber;
	}

	public byte[] getSID() {
		return SID;
	}

	public byte[] getSamUsageControl() {
		return samUsageControl;
	}

	public byte getSamAdminKVN() {
		return samAdminKVN;
	}

	public byte getSamIssuerKVN() {
		return samIssuerKVN;
	}

	public byte[] getAuthorizedCreditLimit() {
		return authorizedCreditLimit;
	}

	public byte[] getSingleCreditTxnAmtLimit() {
		return singleCreditTxnAmtLimit;
	}

	public byte[] getAuthorizedCreditBalance() {
		return authorizedCreditBalance;
	}

	public byte[] getAuthorizedCreditCumulative() {
		return authorizedCreditCumulative;
	}

	public byte[] getAuthorizedCancelCreditCumulative() {
		return authorizedCancelCreditCumulative;
	}

	public byte[] getNewDeviceID() {
		return newDeviceID;
	}

	public byte[] getTabListTable() {
		return tabListTable;
	}

	public byte[] getSamIssuerSecificData() {
		return samIssuerSecificData;
	}

	public byte[] getSTC() {
		return STC;
	}

	public byte[] getRSAM() {
		return RSAM;
	}

	public byte[] getRHOST() {
		return RHOST;
	}

	public byte[] getSATOKEN() {
		return SATOKEN;
	}

	public byte getCPDReadFlag() {
		return CPDReadFlag;
	}

	public byte getOneDayQuotaWritForMicroPayment() {
		return oneDayQuotaWritForMicroPayment;
	}

	public byte getSamSignOnControlFlag() {
		return samSignOnControlFlag;
	}

	public boolean isCheckEVFlagForMifareOnly() {
		return checkEVFlagForMifareOnly;
	}

	public boolean isMerchantLimitUseForMicroPayment() {
		return merchantLimitUseForMicroPayment;
	}

	public byte getOneDayQuotaFlagForMicroPayment() {
		return oneDayQuotaFlagForMicroPayment;
	}

	public boolean isOnceQuotaFlagForMicroPayment() {
		return onceQuotaFlagForMicroPayment;
	}

	public boolean isCheckDebitFlag() {
		return checkDebitFlag;
	}

	public boolean isMifareCheckEnableFlag() {
		return mifareCheckEnableFlag;
	}

	public boolean isPayOnBehalfFlag() {
		return payOnBehalfFlag;
	}

	public byte getRFU_oneDayQuotaFlagForMicroPaymentGroup() {
		return RFU_oneDayQuotaFlagForMicroPaymentGroup;
	}

	public byte[] getOneDayQuotaForMicroPayment() {
		return oneDayQuotaForMicroPayment;
	}

	public byte[] getOnceQuotaForMicroPayment() {
		return onceQuotaForMicroPayment;
	}

	public byte[] getCheckDebitValue() {
		return checkDebitValue;
	}

	public boolean isAddQuotaFlag() {
		return addQuotaFlag;
	}

	public byte[] getAddQuota() {
		return addQuota;
	}

	public byte[] getTheReminderOfAddQuota() {
		return theReminderOfAddQuota;
	}

	public byte[] getCancelCreditQuota() {
		return cancelCreditQuota;
	}

	public byte[] getDeMACParameter() {
		return deMACParameter;
	}

	public byte[] getLastTxnDateTime() {
		return lastTxnDateTime;
	}

	public byte[] getPreviousNewDeviceId() {
		return previousNewDeviceId;
	}

	public byte[] getPreviousSTC() {
		return previousSTC;
	}

	public byte[] getPreviousTxnDateTime() {
		return previousTxnDateTime;
	}

	public byte getPreviousCreditBalanceChangeFlag() {
		return previousCreditBalanceChangeFlag;
	}

	public byte[] getPreviousConfirmCode() {
		return previousConfirmCode;
	}

	public byte[] getPreviousCACrypto() {
		return previousCACrypto;
	}
}

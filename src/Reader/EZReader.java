package Reader;
//import EasycardAPI;



import org.apache.log4j.Logger;



import Reader.PPR_Reset;

import exception.*;
import Utilities.*;


public class EZReader{
	
	static Logger logger = Logger.getLogger(EZReader.class);
	
	
    private String merchantId;//10001
    private String merchantSTCode;//100001
    private RecvSender recvSender=null;
    
    //public PPRReset pprReset = new PPRReset();
    public PPR_Reset pprReset = new PPR_Reset();
 	
 	
    public EZReader(RecvSender rs){
    	logger.info("Start");
    	
    	recvSender = rs;
    	logger.info("End");
    }
    
    public int exeCommand(APDU command)
    {
    	logger.info("Start");
    	int successCode = RespCode.SUCCESS.getId();
    	
    	byte[] sendBuffer;
    	byte[] recvBuffer = null;
    	
    	try{    	
    		sendBuffer = command.GetRequest();    	
    		
    		
    		recvBuffer = recvSender.apduSendRecv(sendBuffer);
    		if(recvBuffer==null) return RespCode.ERROR.getId();
    		
    		if(command.SetRespond(recvBuffer)==false)
    		{
    			logger.error("command: "+command.getClass().getName()+",setRespond fail");
    			return RespCode.ERROR.getId();
    		}
    	}
    	catch(Exception e)
    	{
    		logger.error("Exception: "+ e.getMessage());
    		return RespCode.ERROR.getId();
    	}
    	
		return successCode;
    	
    	
    }
    
    
	/**
	 * @return the merchantId
	 */
	public String getMerchantId() {
		return merchantId;
	}


	/**
	 * @param merchantId the merchantId to set
	 */
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}


	/**
	 * @return the merchantSTCode
	 */
	public String getMerchantSTCode() {
		return merchantSTCode;
	}


	/**
	 * @param merchantSTCode the merchantSTCode to set
	 */
	public void setMerchantSTCode(String merchantSTCode) {
		this.merchantSTCode = merchantSTCode;
	}
	
}













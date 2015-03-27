package Reader;
//import EasycardAPI;



import org.apache.log4j.Logger;

import ErrorMessage.ApiRespCode;
import ErrorMessage.IRespCode;
import ErrorMessage.ReaderRespCode;


public class EZReader{
	
	static Logger logger = Logger.getLogger(EZReader.class);

    private IRecvSender recvSender=null;
    
    
 	
    public EZReader(IRecvSender rs){
    	logger.info("Start");
    	
    	recvSender = rs;
    	logger.info("End");
    }
    
    public IRespCode exeCommand(APDU command)
    {
    	logger.info("Start");
    	
    	IRespCode result;
    	int statusCode;
    	
    	byte[] sendBuffer;
    	byte[] recvBuffer = null;
    	
    	try{    	
    		sendBuffer = command.GetRequest();    	
    		
    		
    		recvBuffer = recvSender.sendRecv(sendBuffer);
    		if(recvBuffer==null) {
    			logger.error("recvBuffer is NULL");
    			return ApiRespCode.READER_NO_RESPONSE;
    		}
    		
    		if(command.SetRespond(recvBuffer)==false)
    		{
    			logger.error("command: "+command.getClass().getName()+",setRespond fail");
    			return ApiRespCode.ERROR;
    		}
    		
    		if((statusCode = command.GetRespCode()) != 0x9000)
    		{
    			String hexCode = String.format("%04X", statusCode);
    			logger.error("APDU respCode:"+hexCode);
    			result = ApiRespCode.fromCode(hexCode, ReaderRespCode.values());
    		}    		
    		else
    			result = ReaderRespCode._9000;
    		
    		//for debug
    		command.debugResponseData();
    		
    	}
    	catch(Exception e)
    	{
    		logger.error("Exception: "+ e.getMessage());
    		return ReaderRespCode.EXCEPTION_ERROR;
    	}
    	
		return result;
    	
    	
    }
}













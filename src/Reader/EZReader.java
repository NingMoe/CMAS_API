package Reader;
//import EasycardAPI;



import org.apache.log4j.Logger;



import Reader.PPR_Reset;


import Utilities.*;


public class EZReader{
	
	static Logger logger = Logger.getLogger(EZReader.class);

    private RecvSender recvSender=null;
    
    
 	
    public EZReader(RecvSender rs){
    	logger.info("Start");
    	
    	recvSender = rs;
    	logger.info("End");
    }
    
    public int exeCommand(APDU command)
    {
    	logger.info("Start");
    	
    	int result = RespCode.SUCCESS.getId();
    	
    	byte[] sendBuffer;
    	byte[] recvBuffer = null;
    	
    	try{    	
    		sendBuffer = command.GetRequest();    	
    		
    		
    		recvBuffer = recvSender.apduSendRecv(sendBuffer);
    		if(recvBuffer==null) {
    			logger.error("recvBuffer is NULL");
    			return RespCode.ERROR.getId();
    		}
    		
    		if(command.SetRespond(recvBuffer)==false)
    		{
    			logger.error("command: "+command.getClass().getName()+",setRespond fail");
    			return RespCode.ERROR.getId();
    		}
    		
    		if((result = command.GetRespCode()) != 0x9000)    		
    			logger.error("APDU respCode:"+String.format("%04X", result));
    			
    		
    		
    	}
    	catch(Exception e)
    	{
    		logger.error("Exception: "+ e.getMessage());
    		return RespCode.ERROR.getId();
    	}
    	
		return result;
    	
    	
    }

}













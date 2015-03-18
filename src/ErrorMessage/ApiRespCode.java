package ErrorMessage;

import java.util.HashMap;
import java.util.Map;



public enum ApiRespCode  implements IRespCode{
	
	/**/
	SUCCESS("0", "Success", 0),
	ERROR("-1", "Something Error", 1),
	ESCAPE("-2", "Escape", 1),	
	LRC_NOT_MATCH("-3", "Reader'LRC maybe does not match with API", 1),
	COMPORT_OPEN_FAIL("-4", "Reader Open Comport fail", 1),
	SSL_CONNECT_FAIL("-5", "SSL Connection Fail", 1),
	READER_NO_RESPONSE("-6", "Reader no response", 1),
	UNKNOWN_ERROR_CODE("XXXX","Unknowen ErrCode", 1);
	

	  
	private final String id; 
	private final String msg;
	private final int actionId;
	private static Map<String, IRespCode> codeToEnumMap;

	  
	  
	ApiRespCode(String id, String msg, int actionId) {
	    this.id = id;
	    this.msg = msg;
	    this.actionId = actionId;
	  }

	  public String getId() {
	    return this.id;
	  }

	  public String getMsg() {
	    return this.msg;
	  }
	  
	  public int getActionId() {
		    return this.actionId; 
	  }
	  
	  public static IRespCode fromCode(String code, IRespCode[] rc) {
            // Keep a hashmap of mapping between code and corresponding enum as a cache.  We need to initialize it only once
            if (codeToEnumMap == null) {
                codeToEnumMap = new HashMap<String, IRespCode>();
                for (IRespCode aEnum : rc) {
                    codeToEnumMap.put(aEnum.getId(), aEnum);
                }
            }

            IRespCode enumForGivenCode = codeToEnumMap.get(code);
            if (enumForGivenCode == null) {
                enumForGivenCode = UNKNOWN_ERROR_CODE;
            }

            return enumForGivenCode;      
	  }
	
}

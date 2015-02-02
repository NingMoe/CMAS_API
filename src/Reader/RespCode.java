package Reader;

import java.util.HashMap;
import java.util.Map;



public enum RespCode{
	SUCCESS(0, "Success"),
	ERROR(-1, "Something Error"),
	ESCAPE(-2, "Escape"),	
	LRC_NOT_MATCH(-3, "Reader'LRC maybe does not match with API"),
	COMPORT_OPEN_FAIL(-4, "Reader Open Comport fail"),
	UNKNOWN_ERROR_CODE(-9999,"Unknowen ErrCode");
	

	  private final int id;
	  private final String msg;
	  private static Map<Integer, RespCode> codeToEnumMap;

	  
	  RespCode(int id, String msg) {
	    this.id = id;
	    this.msg = msg;
	  }

	  public int getId() {
	    return this.id;
	  }

	  public String getMsg() {
	    return this.msg;
	  }
	  
	  public static RespCode fromCode(int code) {
            // Keep a hashmap of mapping between code and corresponding enum as a cache.  We need to initialize it only once
            if (codeToEnumMap == null) {
                codeToEnumMap = new HashMap<Integer, RespCode>();
                for (RespCode aEnum : RespCode.values()) {
                    codeToEnumMap.put(aEnum.getId(), aEnum);
                }
            }

            RespCode enumForGivenCode = codeToEnumMap.get(code);
            if (enumForGivenCode == null) {
                enumForGivenCode = UNKNOWN_ERROR_CODE;
            }

            return enumForGivenCode;      
	  }
	
}

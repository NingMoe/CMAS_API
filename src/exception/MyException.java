package exception;

public class MyException extends Exception {

	  private int errorCode;
	  private String errorMsg;

	  public MyException(RespCode code) {
	    this.errorMsg = code.getMsg();
	    this.errorCode = code.getId();
	  }

	  public int getErrorCode() {
	    return errorCode;
	  }

	  public String getErrorMsg() {
	    return errorMsg;
	  }
	
}


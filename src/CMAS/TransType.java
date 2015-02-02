package CMAS;

public class TransType {
	public enum code {
		AUTH, 
	   VERIFY,
	  TXADVICE,
	   SIGNON,
	   STATUSREPORT,
	   TMS,
	   LOCKCARDADVICE,
	  DEBUG,
	  SETTLEMENT
	}
	public static final int 	 AUTH=0; 
	public static final int   VERIFY=1;
	public static final int  TXADVICE=2;
	public static final int   SIGNON=3;
	public static final int   STATUSREPORT=4;
	public static final int   TMS =5;
	public static final int   LOCKCARDADVICE =6;
	public static final int  DEBUG=7;
	public static final int  SETTLEMENT=8;
	
}

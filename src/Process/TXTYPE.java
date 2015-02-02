package Process;

public class TXTYPE {
	public enum code {
		 START,
		 TMS,
		 SIGNON ,
		 ADD,
		 DEDUCT ,
	 	 VOID,
		SETVALUE,
		 SETTLEMENT,
		AUTOLOADENABLE,
		REFUND,
		LOCKCARD,
		 REPORT,
		DEBUG,
		DEDUCTVOID ,
		 ADDVOID,
		AUTOLOAD,
	}
		public static final int 	 TMS =1;
		public static final int 	 SIGNON =2;
		public static final int 	 ADD =3;
		public static final int 	 DEDUCT =4;  
		public static final int 	 VOID=5;
		public static final int 	 SETVALUE=6;
		public static final int 	 SETTLEMENT=7;
		public static final int 	 AUTOLOADENABLE=8;
		public static final int 	 REFUND=9;
		public static final int 	LOCKCARD=10;
		public static final int 	 REPORT=11;
		public static final int 	 DEBUG=12;
		public static final int 	 DEDUCTVOID =13;
		public static final int 	 ADDVOID=14;
		public static final int 	 AUTOLOAD=15;

}

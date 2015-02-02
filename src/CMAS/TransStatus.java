package CMAS;

import Process.TXTYPE;

public class TransStatus {

	public enum code {
	 	 INIT,
		 REQ,
		RSP,
		ADVREQ,
		ADVRSP,
		OK,
		VOIDED,
	}
	public static final int 	 INIT=0;
	public static final int 	 REQ=1;
	public static final int 	RSP=2;
	public static final int 	ADVREQ=3 ;
	public static final int 	ADVRSP=4;
	public static final int 	OK=5;  
	public static final int 	VOIDED=6;
	
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
	
	static int iGetTransStatus( int inTxType , int Status)
	{
		int    iTransStatus = 0;
		int inTransType=0;
		switch(inTxType)
		{     
		       //TXTYPE.SIGNON:
			case TXTYPE.SIGNON :    
					inTransType=TransType.SIGNON;
					break;  
		    case  TXTYPE.AUTOLOADENABLE:     
		    case  TXTYPE.DEDUCT:    
		             inTransType=TransType.VERIFY;
		             break;  
		    case  TXTYPE.ADD:
		    case  TXTYPE.SETVALUE: 
		    case  TXTYPE.VOID: 
		    case  TXTYPE.REFUND :		    	
		    case  TXTYPE.AUTOLOAD://2014.06.26, kobe added it
		    			inTransType=TransType.AUTH;
		                break;
			case  TXTYPE.LOCKCARD:
						inTransType=TransType.LOCKCARDADVICE;
						break;
		    case  TXTYPE.REPORT:
		                inTransType=TransType.STATUSREPORT;
		                break;
			case  TXTYPE.DEBUG:
				  		inTransType=TransType.DEBUG;	
		                  break;
		    case  TXTYPE.SETTLEMENT:   
		                 inTransType=TransType.SETTLEMENT;
		                 break;
			}
		  
	    switch(inTransType)
	    {
	        case TransType.AUTH:
	            if(Status==REQ)
	            	iTransStatus=AUTH_REQ;
	            else if(Status==RSP)
	            	iTransStatus=AUTH_RESP;
	            else if(Status==ADVREQ)
	            	iTransStatus=ADV_REQ;
	            else if(Status==ADVRSP)
	            	iTransStatus=ADV_RESP;
	            break;
	        case TransType.VERIFY:
	             if(Status==REQ)
	            		iTransStatus=VERIFY_REQ;
	            else if(Status==RSP)
	            		iTransStatus=VERIFY_RESP;
	            else if(Status==ADVREQ)
	            	iTransStatus=ADV_REQ;
	            else if(Status==ADVRSP)
	            	iTransStatus=ADV_RESP;
	            break;
	       
	        case  TransType.SIGNON:
	            if(Status==REQ)
	            	iTransStatus=SG_REQ;
	            else if(Status==RSP)
	            	iTransStatus=SG_RESP;
		     else if(Status==ADVREQ)
		    		iTransStatus=ADV_REQ;
	            else if(Status==ADVRSP)
	            	iTransStatus=ADV_RESP;
	            break;       
	        case  TransType.STATUSREPORT :
	              if(Status==REQ)
	            		iTransStatus=RE_REQ;
	            else if(Status==RSP)
	            	iTransStatus=RE_RESP;
				break;       
			case  TransType.LOCKCARDADVICE :
	             if(Status==ADVREQ)
	            		iTransStatus=LC_REQ;
	             else if(Status==ADVRSP)
	            		iTransStatus=LC_RESP;
	             break;    
	        case   TransType.DEBUG:    
	        	iTransStatus=DEBUG;
	              break;
	        case TransType.SETTLEMENT:   
	             if(Status==REQ)
	            		iTransStatus=SETTLE_REQ;
	            else if(Status==RSP)
	            	iTransStatus=SETTLE_RESP;
	            break;       
	        default:
	            return 0;
	    } 
	    
	    return iTransStatus;
	}
	

	static String usGetTransStatusSTR( int inTxType , int inTransStatus)
	{
		String     TransStatusSTR = null;
		int inTransType=0;
		switch(inTxType)
		{     
		       //TXTYPE.SIGNON:
			case TXTYPE.SIGNON :    
					inTransType=TransType.SIGNON;
					break;  
		    case  TXTYPE.AUTOLOADENABLE:     
		    case  TXTYPE.DEDUCT:    
		             inTransType=TransType.VERIFY;
		             break;  
		    case  TXTYPE.ADD:
		    case  TXTYPE.SETVALUE: 
		    case  TXTYPE.VOID: 
		    case  TXTYPE.REFUND :		    	
		    case  TXTYPE.AUTOLOAD://2014.06.26, kobe added it
		    			inTransType=TransType.AUTH;
		                break;
			case  TXTYPE.LOCKCARD:
						inTransType=TransType.LOCKCARDADVICE;
						break;
		    case  TXTYPE.REPORT:
		                inTransType=TransType.STATUSREPORT;
		                break;
			case  TXTYPE.DEBUG:
				  		inTransType=TransType.DEBUG;	
		                  break;
		    case  TXTYPE.SETTLEMENT:   
		                 inTransType=TransType.SETTLEMENT;
		                 break;
			}
		  
	    switch(inTransType)
	    {
	        case TransType.AUTH:
	            if(inTransStatus==REQ)
	                TransStatusSTR=new String("AU_Q");
	            else if(inTransStatus==RSP)
	                TransStatusSTR=new String("AU_P");
	            else if(inTransStatus==ADVREQ)
	                TransStatusSTR=new String("ADV_Q");
	            else if(inTransStatus==ADVRSP)
	                TransStatusSTR=new String("ADV_P");
	            break;
	        case TransType.VERIFY:
	             if(inTransStatus==REQ)
	            	    TransStatusSTR=new String("VERIFY_Q");
	            else if(inTransStatus==RSP)
	                TransStatusSTR=new String("VERIFY_P");
	            else if(inTransStatus==ADVREQ)
	                TransStatusSTR=new String("ADV_Q");
	            else if(inTransStatus==ADVRSP)
	                TransStatusSTR=new String("ADV_P");
	            break;
	       
	        case  TransType.SIGNON:
	            if(inTransStatus==REQ)
	                TransStatusSTR=new String("SG_Q");
	            else if(inTransStatus==RSP)
	                TransStatusSTR=new String("SG_P");
		     else if(inTransStatus==ADVREQ)
		    	    TransStatusSTR=new String("SGADV_Q");
	            else if(inTransStatus==ADVRSP)
	                TransStatusSTR=new String("SGADV_P");
	            break;       
	        case  TransType.STATUSREPORT :
	              if(inTransStatus==REQ)
	            	    TransStatusSTR=new String("RE_Q");
	            else if(inTransStatus==RSP)
	                	TransStatusSTR=new String("RE_P");
				break;       
			case  TransType.LOCKCARDADVICE :
	             if(inTransStatus==ADVREQ)
	            	   TransStatusSTR=new String("LC_Q");
	             else if(inTransStatus==ADVRSP)
	            	 TransStatusSTR=new String("LC_P");
	             break;    
	        case   TransType.DEBUG:    
	        	 TransStatusSTR=new String("DEBUG");
	              break;
	        case TransType.SETTLEMENT:   
	             if(inTransStatus==REQ)
	            	 TransStatusSTR=new String("SETTLE_Q");
	            else if(inTransStatus==RSP)
	            	 TransStatusSTR=new String("SETTLE_P");
	            break;       
	        default:
	            return null;
	    } 
	    
	    return TransStatusSTR;
	}
}

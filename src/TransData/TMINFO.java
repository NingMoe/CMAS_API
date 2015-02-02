package TransData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



public class TMINFO
{
	String 	POSID;
	String 	TMSN;//6byte
	String 	TnxInvoiceNo; //20byte  1101 TMSN 以此欄位取末六碼 
	Date     TxDatetime;
	String     AgentID;
	public TMINFO(){
		 	POSID=new String("00");
		 	TnxInvoiceNo=new String("000000");
		    TxDatetime=null;
		    AgentID=new String("0000");
	}
	public void SetTmInfo(String Posid,String sn,String yyyyMMddhhmmss,String agentid)
	{
		this.AgentID=agentid;
		this.POSID=Posid;
		this.TnxInvoiceNo=sn;
		this.SetTxDatetime(yyyyMMddhhmmss);
	}
	
	public void SetTxDatetime(String YYMMDDhhmmss){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddhhmmss");
		TxDatetime= null;
		try{
			TxDatetime = formatter.parse(YYMMDDhhmmss);
		}catch(ParseException e){		
			 System.out.println("unparseable using" + formatter);
		}
	}
	public void SetTxDatetime(Date txdate)
	{
		TxDatetime=txdate;
	}
	public String GetTxDatestr()
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		return  formatter.format(TxDatetime);
	}
	public String GetTxTimestr()
	{
		SimpleDateFormat formatter = new SimpleDateFormat("hhmmss");
		return  formatter.format(TxDatetime);
	}
	public int GetTxTime()
	{
		return (int)TxDatetime.getTime()/1000; 		
	} 
	
	public String GetPOSID()
	{
		
			return POSID;
	
	}
	
	public String GetAgentID(){
		
			return  AgentID;
	}
	
	public String getTnxInvoiceNo()
	{
		return TnxInvoiceNo;
	}
	public String GetTMSN()
	{//取末六碼
		return TnxInvoiceNo.substring(TnxInvoiceNo.length()-6);  
	}
}
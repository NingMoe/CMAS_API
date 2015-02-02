package Utilities;

public class EZLib {
	public static byte calCheckSum(int length,byte  data[])
 	{
	 	byte chk= 0x00;
	 	int i= 0x00;
	 	 
	 	for(i=0;i<length-1;i++)	 		
	 		chk^=data[i];
	 	return chk;
 	}
	
	public static String hex2StringLog(byte[] data)
	{
		StringBuilder sb = new StringBuilder(data.length * 2);
		   for(byte b: data)
		      sb.append("("+String.format("%02x", b & 0xff)+")");
		   return sb.toString();		
	}
	
	
	
}

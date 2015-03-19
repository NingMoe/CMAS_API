package CMAS;

import java.util.ArrayList;

import org.apache.log4j.Logger;



import Comm.FTP.Ftp4j;
import Process.ConfigManager;

public class CmasFTPList {
	
	static Logger logger = Logger.getLogger(CmasFTPList.class);
	ArrayList<CmasDataSpec.SubTag5595> t5595s = null;
	public CmasFTPList(ArrayList<CmasDataSpec.SubTag5595> t5595s){
		this.t5595s = t5595s;
	}
	
	public void startDownload(String ftpIP, int ftpPort, String ftpID, String ftpPwd){
		Ftp4j ftps = new Ftp4j(null);
		try{
			
			if(ftps.connect2Server(ftpIP,ftpPort,ftpID,ftpPwd,true) == false){
				logger.error("FTP Connect Fail");
				return;
			}
			
			logger.info("FTP Connect OK");
			for(CmasDataSpec.SubTag5595 t5595:t5595s)
			{		
				String filename=null;
				String t559501=t5595.getT559502();
				if(t559501.equalsIgnoreCase("TM10"))
					filename = ConfigManager.CA_CERT;
				else if(t559501.equalsIgnoreCase("TM12"))
					filename = ConfigManager.API_JAR;
				if(filename != null)
				{					
					ftps.download(t5595.getT559503(), ConfigManager.ROOT_DIR+filename); // download to
				}
				
			}
		} catch(Exception e) {
			
			logger.error(e.getMessage());
		}
		
		finally{
			ftps.disconnect();			
		}
		
		
		return;
	}
	
}

package CMAS;

import java.util.ArrayList;

import org.apache.log4j.Logger;



import Comm.FTP.Ftp4j;
import CMAS.ConfigManager;

public class CmasFTPList extends Thread{
	
	static Logger logger = Logger.getLogger(CmasFTPList.class);
	
	private Ftp4j ftps = new Ftp4j(null);
	private boolean isFtpsOK = false;
	
	ArrayList<CmasDataSpec.SubTag5595> t5595s = null;
	private String url = null;
	private String bkIP=null;
	private int port = 0;
	private String id = null;
	private String pwd = null;
	
	public CmasFTPList(String url,
			String bkIP,
			int port, 
			String id,
			String pwd, 
			ArrayList<CmasDataSpec.SubTag5595> t5595s){
		
		this.url = url;
		this.bkIP = bkIP;
		this.port = port;
		this.id = id;
		this.pwd = pwd;
		this.t5595s = t5595s;
	}
	
	/**
	 * @return the isFtpsOK
	 */
	public boolean isFtpsOK() {
		return isFtpsOK;
	}

	/**
	 * @param isFtpsOK the isFtpsOK to set
	 */
	public void setFtpsOK(boolean isFtpsOK) {
		this.isFtpsOK = isFtpsOK;
	}

	private void startDownload(){
		
		try{			
			
			String rootDir = ftps.getCurrentDIR();
			logger.info("FTP Connect OK");
			for(CmasDataSpec.SubTag5595 t5595:t5595s)
			{		
				String filename=null;
				String t559501=t5595.getT559502();
				if(t559501.equalsIgnoreCase("TM10")) //ssl C Cert
					filename = ConfigManager.CA_CERT;
				if(t559501.equalsIgnoreCase("TM11"))
					filename = ConfigManager.CARD_NUMBER_BLACKLIST;//black list
				else if(t559501.equalsIgnoreCase("TM12"))
					filename = ConfigManager.API_JAR;//api file
				if(filename != null) {					
					ftps.download(rootDir + t5595.getT559503(), ConfigManager.ROOT_DIR+filename); // download to
				}
				
			}
		} catch(Exception e) {
			
			logger.error(e.getMessage());
		}
		return;
	}
	
	public void disconnect(){		
		ftps.disconnect();
	}
	
	public void run()
	{
		int retry = 4;//twice try url, twice try ip
		int cnt = 0;
		while(cnt < retry)
		{
			if(ftps.connect2Server(url,port,id,pwd,true) == false){
				logger.error("FTP Connect Fail no."+cnt);
				cnt++;
				continue;
			}
			if(cnt == 2) {
				logger.error("url connect fail, change backup IP:"+this.bkIP);
				url = bkIP;
			}
			
			if(cnt == retry) {
				logger.error("ftps connect fail");
				this.setFtpsOK(false);
			}
			else {
				logger.info("ftps connect OK");
				this.setFtpsOK(true);
				break;
			}
		}
		
		if(this.isFtpsOK){
			startDownload();
		}
	}
	
	
}

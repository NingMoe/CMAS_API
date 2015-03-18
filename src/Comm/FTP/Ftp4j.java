package Comm.FTP;

import java.io.IOException;



import it.sauronsoftware.ftp4j.FTPAbortedException;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferException;
import it.sauronsoftware.ftp4j.FTPDataTransferListener;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.log4j.Logger;



//reference @ http://www.sauronsoftware.it/projects/ftp4j/index.php by kobe
public class Ftp4j {
	 
	private TrustManager[] trustManager=null;
	private FTPDataTransferListener ftpListener = null;
	private FTPClient client = null;
	private X509TrustManager x509=null;
	static Logger logger = Logger.getLogger(Ftp4j.class);
	
	public Ftp4j(TrustManager[] trustManager, FTPDataTransferListener ftpListener){
		
		if(trustManager==null){
			// An alternative SSLSocketFactory, 
			// for example, can be used to trust every certificate given by the remote host (use it carefully): 
			
			/*
			//X509TrustManager x509;
			trustManager = new TrustManager[] { x509 = new X509TrustManager() {
				public X509Certificate[] getAcceptedIssuers() {
					logger.debug("X509Certificate");
					return null;
				}
				public void checkClientTrusted(X509Certificate[] certs, String authType) {
					logger.debug("checkClientTrusted");
				}
				public void checkServerTrusted(X509Certificate[] certs, String authType) {
					logger.debug("checkServerTrusted");
				}
			} };*/		
		} else this.trustManager = trustManager;
		
		
		if(ftpListener == null) {
			this.ftpListener = new Ftp4jListener();
		} else this.ftpListener = ftpListener;
	}
	
	public boolean connect2Server(String host, 
							int port, 
							String id, 
							String pwd,
							boolean eanbleSSL)
	{
		
		boolean result = false;
		this.client = new FTPClient();
		client.setPassive(true); // Passive mode
		if(eanbleSSL)
		{
			logger.info("Enable FTPS");
			SSLContext sslContext = null;
			try {
				
				/*
				TrustManager[] trustManager = new TrustManager[] { new X509TrustManager() {
					public X509Certificate[] getAcceptedIssuers() {
						logger.debug("X509Certificate");
						return null;
					}
					public void checkClientTrusted(X509Certificate[] certs, String authType) {
						logger.debug("checkClientTrusted");
					}
					public void checkServerTrusted(X509Certificate[] certs, String authType) {
						logger.debug("checkServerTrusted");
					}
				} };*/	
				
				
				
				sslContext = SSLContext.getInstance("SSL");
				sslContext.init(null, trustManager, new SecureRandom());
				
			} catch (NoSuchAlgorithmException e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			} catch (KeyManagementException e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
			client.setSSLSocketFactory(sslSocketFactory);
			client.setSecurity(FTPClient.SECURITY_FTPS);
		}
		
		try {
			//client.connect(host, port);
			//client.login(id, pwd);
			client.connect("211.78.134.167", 990);
			client.login("ftp_edc", "123@abc");
			
			result = true;
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (FTPIllegalReplyException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (FTPException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		
		return result;
	}
	
	public boolean download(String serverPath, String localPath) {
		
		boolean result = false;
		
		try {
			
			if(client==null) {
				logger.error("ftpClient object was NULL");
				return false;
			}
			
			try {
				client.download(serverPath, new java.io.File(localPath), ftpListener);
				result = true;
			} catch (FTPDataTransferException e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage());
				e.printStackTrace();
			} catch (FTPAbortedException e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage());
				e.printStackTrace();
			} 
			
			
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (FTPIllegalReplyException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (FTPException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	public boolean ftpDisconnect()
	{
		boolean result = false;
		try {
			this.client.disconnect(true);
			result = true;
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage());
		} catch (FTPIllegalReplyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage());
		} catch (FTPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		
		return result;
	}
}

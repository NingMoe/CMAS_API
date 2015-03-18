package Comm.Socket;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Arrays;

import javax.net.SocketFactory;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManagerFactory;

import org.apache.log4j.Logger;

import Process.Process;

//public class SSL extends Thread {
public class SSL {
	
	static Logger logger = Logger.getLogger(SSL.class);
	
	//private String scCertFilePath = "../config/CMAS-FTP51T.jks";
	private String scCertFilePath=null ;//= "../config/CMAS-FTP51T.jks";
	
	private final String scPassWord = "Cmas@999";
	
	private final int scBufferLen = 65535;
	
	private SSLSocket mSocket = null;
	private String mUrl = null;
	private int mPort = 0; 
	
	private String mReq = "";
    //private ICMASResponse mClient;
    
    private byte[] mBuffer = new byte[scBufferLen];
    
	public SSL(String url, int port, String certFilePath) {
		mUrl = url;
		mPort = port;
		scCertFilePath = certFilePath;
	}
	
	//protected boolean connect() {
	public boolean connect() {
		if (mUrl == null) {
			return false;
		}
	
		KeyStore keystore = null;
		try {
			keystore = getKeyStore();
		} catch (KeyStoreException e) {
			logger.debug(e.getMessage());
			//mClient.onError(e.getMessage());
			return false;
		} catch (NoSuchAlgorithmException e) {
			logger.debug(e.getMessage());
			//mClient.onError(e.getMessage());
			return false;
		} catch (CertificateException e) {
			logger.debug(e.getMessage());
			//mClient.onError(e.getMessage());
			return false;
		} catch (IOException e) {
			logger.debug(e.getMessage());
			//mClient.onError(e.getMessage());
			return false;
		}
		
		mSocket = null;
		try {
			mSocket = getSSLSocket(keystore);
		} catch (KeyManagementException e) {
			logger.debug(e.getMessage());
			//mClient.onError(e.getMessage());
			return false;
		} catch (UnrecoverableKeyException e) {
			logger.debug(e.getMessage());
			//mClient.onError(e.getMessage());
			return false;
		} catch (KeyStoreException e) {
			logger.debug(e.getMessage());
			//mClient.onError(e.getMessage());
			return false;
		} catch (NoSuchAlgorithmException e) {
			logger.debug(e.getMessage());
			//mClient.onError(e.getMessage());
			return false;
		} catch (UnknownHostException e) {
			logger.debug(e.getMessage());
			//mClient.onError(e.getMessage());
			return false;
		} catch (IOException e) {
			logger.debug(e.getMessage());
			//mClient.onError(e.getMessage());
			return false;
		}
		
		return true;
	}
	
	//protected boolean disconnect() {
	public boolean disconnect() {
		
		logger.info("Start");
		if (mSocket == null) {
			logger.error("sslSocket Obj 'NULL'");
			return false;
		}
		
		try {
			logger.info("SSL Close");
			mSocket.close();
		} catch (IOException e) {
			
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		
		return true;
	}
	
	private KeyStore getKeyStore() throws KeyStoreException,
	  									  NoSuchAlgorithmException,
	  									  CertificateException,
	  									  IOException {
		
		logger.debug("SSL CertFileName:"+scCertFilePath);
		//FileInputStream fKeyStore = new FileInputStream("C:\\eclipse\\Workspace\\EasycardAPI\\config\\CMAS-FTP51T.jks");

		//InputStream fKeyStore = SSL.class.getClass().getResourceAsStream(scCertFilePath);
		
		
		InputStream fKeyStore = SSL.class.getClassLoader().getResourceAsStream(scCertFilePath);
		logger.debug("load SSL Cert OK");
		
		KeyStore keystore = KeyStore.getInstance("JKS");
		keystore.load(fKeyStore, scPassWord.toCharArray()); 

		fKeyStore.close();

		return keystore;
	}
	
	private SSLSocket getSSLSocket(KeyStore keystore) throws KeyStoreException,
	   														 NoSuchAlgorithmException,
	   														 IOException,
	   														 KeyManagementException,
	   														 UnknownHostException,
	   														 UnrecoverableKeyException,
	   														 IOException {
		TrustManagerFactory tmf = TrustManagerFactory.getInstance("SUNX509");
		tmf.init(keystore);

		KeyManagerFactory kmf = KeyManagerFactory.getInstance("SUNX509");
		kmf.init(keystore, scPassWord.toCharArray()); 

		SSLContext context = SSLContext.getInstance("SSL");
		context.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

		SocketFactory sf = context.getSocketFactory();
		return (SSLSocket) sf.createSocket(mUrl, mPort);
	}
	
	//protected void sendRequest() throws IOException {
	//protected String sendRequest(String req){
	public String sendRequest(String req){
		
		try {
			
		
			if (mSocket == null) {
				//throw new IOException(ICMASResponse.scErr_NoConnection);
				logger.error("SSL Socket NoConnection");
				return null;
				//throw new IOException("SSL Socket NoConnection");
			}
			/*
			if (mReq == null) {
				logger.error("SSL send data was NULL");
				throw new IOException("SSL send data was NULL");
				//throw new IOException(ICMASResponse.scErr_InvalidParam);
			}*/
			OutputStream out;
			out = mSocket.getOutputStream();
			
	   	 	InputStream in;
	   	 	in = mSocket.getInputStream();
			
	   	 	
	   	 	out.write(req.getBytes("UTF-8"));
	   	 	out.flush();
	
	   	 	String resp = null;
	   	 	int len = in.read(mBuffer);
	   	 	if (len > 0) {
	   	 		resp = new String(Arrays.copyOf(mBuffer, len), "UTF-8");
	   	 	}
	
	   	 	//out.close(); //!!Note: close() means closeSocket
	   	 	//in.close(); //!!Note: close() means closeSocket
	   	 		   	 
	   	 	return resp;
		} catch(IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	/*
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			try {
				synchronized (this) {
					this.wait();
				}
				if (!connect()) {
					continue;
				}
				sendRequest(); 
				disconnect();
			} catch (InterruptedException e) {
				if (mClient != null) {
					mClient.onError(e.getMessage());
				}
        		break;
			} catch (IOException e) {
				if (mClient != null) {
					mClient.onError(e.getMessage());
				}
			}
		}
	}*/
}

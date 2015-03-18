package Comm.FTP;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import org.apache.log4j.Logger;

import Comm.Socket.SSL;

public class MyX509TrustManager implements TrustManager{
	/*
	  * The default X509TrustManager returned by IbmX509. We'll delegate
	  * decisions to it, and fall back to the logic in this class if the
	  * default X509TrustManager doesn't trust it.
	  */
	static Logger logger = Logger.getLogger(MyX509TrustManager.class);
	private final String scPassWord = "Cmas@999";
	 X509TrustManager pkixTrustManager;
	 MyX509TrustManager() throws Exception {
	  // create a "default" JSSE X509TrustManager.
	  KeyStore ks = KeyStore.getInstance("JKS");
	  
	  InputStream fKeyStore = MyX509TrustManager.class.getClassLoader().getResourceAsStream("CMAS-FTP51T.jks");
	  ks.load(fKeyStore, scPassWord.toCharArray());

	  TrustManagerFactory tmf =
	  TrustManagerFactory.getInstance("SUNX509");
			  //TrustManagerFactory.getInstance("IbmX509", "IBMJSSE2");
	   tmf.init(ks);

	   TrustManager tms [] = tmf.getTrustManagers();

	   /*
	    * Iterate over the returned trustmanagers, look
	    * for an instance of X509TrustManager. If found,
	    * use that as our "default" trust manager.
	    */
	   for (int i = 0; i < tms.length; i++) {
	    if (tms[i] instanceof X509TrustManager) {
	     pkixTrustManager = (X509TrustManager) tms[i];
	     return;
	    }
	   }

	   /*
	    * Find some other way to initialize, or else we have to fail the
	    * constructor.
	    */
	   throw new Exception("Couldn't initialize");
	  }

	  /*
	   * Delegate to the default trust manager.
	   */
	  public void checkClientTrusted(X509Certificate[] chain, String authType)
	   throws CertificateException {
	    try {
	    	logger.debug("checkClientTrusted, authType:"+authType);
	     pkixTrustManager.checkClientTrusted(chain, authType);
	    } catch (CertificateException e) {
	    	e.printStackTrace();
	     // do any special handling here, or rethrow exception.
	    }
	   }

	   /*
	    * Delegate to the default trust manager.
	    */
	   public void checkServerTrusted(X509Certificate[] chain, String authType)
	    throws CertificateException {
	     try {
	    	 System.out.println();
	      pkixTrustManager.checkServerTrusted(chain, authType);
	     } catch (CertificateException e) {
	    	 e.printStackTrace();
	    	 logger.debug(e.getMessage());
	      /*
	       * Possibly pop up a dialog box asking whether to trust the
	       * cert chain.
	       */
	     }
	    }

	    /*
	     * Merely pass this through.
	     */
	    public X509Certificate[] getAcceptedIssuers() {
	    	logger.debug("");
	     return pkixTrustManager.getAcceptedIssuers();
	    }
}

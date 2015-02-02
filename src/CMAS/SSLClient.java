package CMAS;



import java.security.KeyStore;
import java.io.FileInputStream;
import java.util.Properties;

import javax.net.ssl.*;

import java.io.OutputStream;
import java.io.InputStream;
import java.io.FileReader;
import java.io.BufferedReader;


public class SSLClient {

   private static String fileName = "SSLClient.java";
   private static String process = "SSLClient";
   private static String serverIP;
   private static int serverPort = 0;
  
   private static String keyPath;
   private static String keyPass="Cmas@999";
   public void SetServerIP(String ip,String port)
   {
	   	serverIP=ip;

	   	int iport=Integer.parseInt(port);
	   	if(iport<=65535&& iport >0)
	   		serverPort=iport;
	   	else
	   		serverPort=7000;
   }
   public  byte[] SendReceive(String Reqdata,String IP,String Port) {
       try {
    	   SetServerIP(IP,Port);
    	   byte[]     Receivebuffer= new byte[65536];
           
         //  properties.load(new FileInputStream(SSLClient.class.getResource("/").getPath() + process + ".properties"));
         
 
   //        SendfilePath = properties.getProperty("SendFilePath");
  //         ReceivefilePath = properties.getProperty("ReceiveFilePath");
           	   keyPath =this.getClass().getResource("/").getPath() + Config.PATH.Config+"CMAS-FTP51T.jks";
  //         keyPass = properties.getProperty("KeyPass");

      /*     String content = "";

           FileReader fr = new FileReader(filePath);
           BufferedReader br = new BufferedReader( (fr);
           String line = "";
           while ((line = br.readLine()) != null)
           {
               content += line;
           }*/

           KeyStore keyStore = KeyStore.getInstance("JKS");
           keyStore.load(new FileInputStream(keyPath), keyPass.toCharArray());
           TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
           tmf.init(keyStore);
           KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
           kmf.init(keyStore, keyPass.toCharArray());
           SSLContext sslContext = SSLContext.getInstance("SSL");
           sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
           SSLSocketFactory ssls = sslContext.getSocketFactory();
           SSLSocket socket =  (SSLSocket)ssls.createSocket(serverIP, serverPort);
           OutputStream os = socket.getOutputStream();
           os.write(Reqdata.getBytes("UTF-8"));
           os.flush();

           InputStream is = socket.getInputStream();
      
           is.read(Receivebuffer);
           System.out.println(new String(Receivebuffer, "UTF-8"));
           return Receivebuffer;
      } catch(Exception e) {
          e.printStackTrace();
          return null;
      }
   }
}
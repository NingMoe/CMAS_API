package CMAS;

/**
*
* @author hyt
*/
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
   private static String filePath;
   private static String keyPath;
   private static String keyPass;
   
   public static void main(String args[]) {
       try {
           Properties properties = new Properties();
           properties.load(new FileInputStream(SSLClient.class.getResource("/").getPath() + process + ".properties"));
           serverIP =properties.getProperty("ServerIP");
           serverPort =Integer.parseInt(properties.getProperty("ServerPort"));
           filePath = properties.getProperty("FilePath");
           keyPath = properties.getProperty("KeyPath");
           keyPass = properties.getProperty("KeyPass");

           String content = "";

           FileReader fr = new FileReader(filePath);
           BufferedReader br = new BufferedReader(fr);
           String line = "";
           while ((line = br.readLine()) != null)
           {
               content += line;
           }

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
           os.write(content.getBytes("UTF-8"));
           os.flush();

           InputStream is = socket.getInputStream();
           byte[] b = new byte[65536];
           is.read(b);
           System.out.println(new String(b, "UTF-8"));

      } catch(Exception e) {
          e.printStackTrace();
      }
   }
}
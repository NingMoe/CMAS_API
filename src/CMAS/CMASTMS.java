package CMAS;

import java.io.File;
import java.sql.Date;
import java.text.ParseException;   
import java.text.SimpleDateFormat;   
    









import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;   
import org.xml.sax.SAXException;   
import org.xml.sax.helpers.DefaultHandler;   

import java.io.File;   
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;   
import java.util.ArrayList;   
import java.util.List;   
    
import java.util.Properties;
import java.util.TimeZone;

import javax.xml.parsers.DocumentBuilder;   
import javax.xml.parsers.DocumentBuilderFactory;   
import javax.xml.parsers.ParserConfigurationException;   
    





import org.w3c.dom.Document;   
import org.w3c.dom.Element;   
import org.w3c.dom.Node;   
import org.w3c.dom.NodeList;   
import org.xml.sax.SAXException;   

import CMAS.ReqTagCreator.DataXferControl;
import CMAS.ReqTagCreator.VersionInfo;
import CMAS.ReqTagCreator.VersionInfo_type;
import Process.TXTYPE;
import Utilities.DataFormat;
    

    
 class DomUtil {   
    
    private DocumentBuilderFactory factory;   
    private DocumentBuilder builder;   
    private Document document;   
        
    private List<TAG> list;   
        
    /**  
     * 
     * @return DocumentBuilderFactory  
     */   
    private DocumentBuilderFactory getDocumentBuilderFactory(){   
        return DocumentBuilderFactory.newInstance();   
    }   
        
    /**  
     * 
     * @param DocumentBuilderFactory fac   
     * @return DocumentBuilder  
     * @throws ParserConfigurationException  
     */   
    private DocumentBuilder getDocumentBuilder(DocumentBuilderFactory fac) throws ParserConfigurationException{   
        return fac.newDocumentBuilder();   
    }   
        
    /**  
     * 功能：解析XML文件  
     * @param file xml文件  
     * @return List<User>  
     * @throws ParserConfigurationException  
     * @throws SAXException  
     * @throws IOException  
     */   
    public List<TAG> parseXml(File file) throws ParserConfigurationException, SAXException, IOException{   
        factory = getDocumentBuilderFactory();   
        builder = getDocumentBuilder(factory);   
        document = builder.parse(file);   
        List<TAG> list = parseXmlProcess(document);   
        return list;   
    }   
        
    /**  
     * 功能：xml文件具体解析程序 
     * @param document  
     * @return List<User>  
     */   
    private List<TAG> parseXmlProcess(Document document){   
        list = new ArrayList<TAG>();   
            
          
        Element root = document.getDocumentElement();   
        
        NodeList childNodes = root.getChildNodes();   
            
        for(int i=0; i<childNodes.getLength(); i++){//取得節點集合長度
            Node node = childNodes.item(i);
                
            if("Trans".equals(node.getNodeName())){
                NodeList pChildNodes = node.getChildNodes();   
                int plen=pChildNodes.getLength();
                for(int p=0; p<pChildNodes.getLength(); p++){   
                		TAG tag = new TAG();   
                        Node uNode = pChildNodes.item(p);   
                        NodeList cChildNodes =uNode.getChildNodes();   
                        int alen= cChildNodes.getLength();
                        if(cChildNodes.getLength()>1)
                            {
                        		  tag.list = new ArrayList<TAG>();   
                        		  tag.SetName(uNode.getNodeName());
                            	  for(int c=0;c<cChildNodes.getLength();c++){     
                            		  TAG childTag  = new TAG();   
                            		  Node cNode = cChildNodes.item(c);   
                            		  childTag.SetName(cNode.getNodeName());
                            		  childTag.SetValue(cNode.getTextContent());
                            		  tag.list.add(childTag);   
                            	  }
                            }else{
                            	    tag.SetName(uNode.getNodeName());
                            	    tag.SetValue(uNode.getTextContent());
                            }
                            list.add(tag);   
                        }   
                                
                }   
               
        }   
            
        return list;   
    }   
 }
 
/**  
 * 定義xml解析時的監聽  
 *   
 * 可實現接口：ContentHandler，DTDHandler， EntityResolver 和 ErrorHandler   
 * 但常用的繼承：DefaultHandler   
 */   
 class SaxUtil extends DefaultHandler {   
    
    private TAG tag;   
     private String content;   

        
    @Override   
    public void characters(char[] ch, int start, int length)   
            throws SAXException {   
        content = new String(ch, start, length);   
    }   
        
    //當解析到文件開始時觸發   
    @Override   
    public void startDocument() throws SAXException {   
        super.startDocument();   
    }   
        
   //當解析到文件結束時觸發 
    @Override   
    public void endDocument() throws SAXException {   
        super.endDocument();   
    }   
        
    //當解析到元素開始時觸發
    @Override   
    public void startElement(String uri, String localName, String name,   
            Attributes attributes) throws SAXException    
    {   
        if("Trans".equals(name))   
        {   
        	tag = new TAG();   
        }   
       
    }   
        
    //當解析到元素結束時觸發   
    @Override   
    public void endElement(String uri, String localName, String name)   
            throws SAXException    
    {   
        tag.SetName(name);
    	tag.SetValue(content);
               
    }   
    
    public TAG gettag(){   
        return tag;   
    }   
}
 

class TAG {
	String Name;
	String Value;
	List <TAG> list;
	void SetName(String name){
			Name=name;
	}
	void SetValue(String value){
			Value=value;
	}
	
}


class TMSTAG extends TAG{
	String Version;
	String Name;
	String Value;
	String ValidityDateStart;
	String ValidityDateEND;
	void SetVersion(String ver){
		Version=ver;
	}
	void SetValidityDateStart(String validityDateStart){
		ValidityDateStart=validityDateStart;
	}
	void SetValidityDateEnd(String validityDatend){
		ValidityDateEND=validityDatend;
	}
}

public class CMASTMS {   
	
	 static List<TAG> list;
    /*public void main(String[] args) {   
            
        try {   
            //1.获取factory   
            SAXParserFactory factory = SAXParserFactory.newInstance();   
            //2.获取parser   
            SAXParser parser = factory.newSAXParser();   
            //3.获取解析时的监听器对象   
            SaxUtil su = new SaxUtil();   
            //4.开始解析   
            parser.parse(new File("f:ftpserver/user/log/RecvFile133237.xml"), su);   
                
            System.out.println(su.gettag());   
            
        } catch (ParserConfigurationException e) {   
            e.printStackTrace();   
        } catch (SAXException e) {   
            e.printStackTrace();   
        } catch (IOException e) {   
            e.printStackTrace();   
        }   
            
    }   */
	 private DomUtil domUtil = new DomUtil();   
     
	    public static void main(String[] args) {   
	        try {   
	            File f = new File("RecvFile090247.xml");   
	            list = new CMASTMS().domUtil.parseXml(f);   
	            for (TAG tag : list) {   
	                System.out.format("%s:%s\n",tag.Name,tag.Value);   
	            }   
	        } catch (ParserConfigurationException e) {   
	            e.printStackTrace();   
	        } catch (SAXException e) {   
	            e.printStackTrace();   
	        } catch (IOException e) {   
	            e.printStackTrace();   
	        }   
	            
	    }   
	

public void SetTagData(String TagName,String TagValue)
{
	
}

	    
	    
	    
	    
	    
	    
}   


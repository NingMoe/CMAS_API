package CMAS;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.Node;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import Utilities.XMLReader;
import Process.*;
public class TransFormat {
	
	public static final int 	 TMS =1;
	public static final int 	 SIGNON =2;
	public static final int 	ADD =3;
	public static final int 	 DEDUCT =4;  
	public static final int 	 VOID=5;
	public static final int 	 SETVALUE=6;
	public static final int 	 SETTLEMENT=7;
	public static final int 	 AUTOLOADENABLE=8;
	public static final int 	 REFUND=9;
	public static final int 	LOCKCARD=10;
	public static final int 	 REPORT=11;
	public static final int 	 DEBUG=12;
	public static final int 	 DEDUCTVOID =13;
	public static final int 	 ADDVOID=14;
	public static final int 	 AUTOLOAD=15;
	
	
	String Path;
	DocumentBuilderFactory docFactory;
	DocumentBuilder docBuilder ;
	Document doc ;
	
public TransFormat()
{
//	  Path=CMAS.class.getResource("/").getPath() +  Config.PATH.Config+"transformat.xml";

}

	/*LoadTransFormatTable
	 * �̥�����OŪ��  TransFormat Table ���o�ӥ�����W�����
	 * input :
	 * 		TransType ������� �[�ȡB�ʳf�B����....
	 * 		TxStatus	TxStatus_REQ  ' TxStatus_RSP....
	 * output
	 * 		�ʥ]Tag�ƶq
	 * 	 */
  public  List<CMASTAG>   Load(int inTxnType,int inTransStatus)
  {
	  XMLReader FormatTable;
	  List <CMASTAG> TransTagList = new ArrayList<CMASTAG>();   ;
	 
	  FormatTable =new XMLReader(Path);
	  
	   String TransStatusStr=TransStatus.usGetTransStatusSTR(inTxnType,inTransStatus);
	  
	  Element TagDef=(Element) FormatTable.doc.getElementsByTagName("TAGDEF").item(0);
       NodeList tags=   (NodeList) TagDef.getChildNodes();
       
	   for(int i=0;i<tags.getLength();i++){
		   if(tags.item(i).getNodeType()==Node.ELEMENT_NODE){
			   	System.out.println("Tag Name : " + tags.item(i).getNodeName());
			   	System.out.println("Tag attribute : " +tags.item(i).getAttributes().getNamedItem(TransStatusStr).getNodeValue());	
				 if(    tags.item(i).getAttributes().getNamedItem(TransStatusStr).getNodeValue().equals("M")||
						 tags.item(i).getAttributes().getNamedItem(TransStatusStr).getNodeValue().equals("C")){
					  
					 CMASTAG  Fields =new CMASTAG();
					  Fields.SetTagName(  tags.item(i).getNodeName());
					  Fields.SetName( tags.item(i).getAttributes().getNamedItem("name").getNodeValue());
					  Fields.SetType(  tags.item(i).getAttributes().getNamedItem("type").getNodeValue());
					  Fields.SetLen(  tags.item(i).getAttributes().getNamedItem("length").getNodeValue());
					  TransTagList.add(Fields);			  
				  }		  
		   }

	   }
		return 	TransTagList;
  }
  
  /*Ū���Ҧ�tag ���
   * 
   */
  public  List<CMASTAG>   Load()
  {
	  XMLReader FormatTable;
	  List <CMASTAG> TransTagList = new ArrayList<CMASTAG>();   ;
	 
	  FormatTable =new XMLReader(Path);
	  
	
	  
	  Element TagDef=(Element) FormatTable.doc.getElementsByTagName("TAGDEF").item(0);
       NodeList tags=   (NodeList) TagDef.getChildNodes();
       
	   for(int i=0;i<tags.getLength();i++){
		   if(tags.item(i).getNodeType()==Node.ELEMENT_NODE){
			
					  
			   CMASTAG  Fields =new CMASTAG();
					  Fields.SetTagName(  tags.item(i).getNodeName());
					  Fields.SetName( tags.item(i).getAttributes().getNamedItem("name").getNodeValue());
					  Fields.SetType(  tags.item(i).getAttributes().getNamedItem("type").getNodeValue());
					  Fields.SetLen(  tags.item(i).getAttributes().getNamedItem("length").getNodeValue());
					  
					  char[] Transflags =new char[17];
					  if(tags.item(i).getAttributes().getNamedItem("AU_Q").getNodeValue().toCharArray().length>0)
						  Transflags[TransStatus.AUTH_REQ]=tags.item(i).getAttributes().getNamedItem("AU_Q").getNodeValue().toCharArray()[0];
					  if(tags.item(i).getAttributes().getNamedItem("AU_P").getNodeValue().toCharArray().length>0)
						  Transflags[TransStatus.AUTH_RESP]=tags.item(i).getAttributes().getNamedItem("AU_P").getNodeValue().toCharArray()[0];
					  if(tags.item(i).getAttributes().getNamedItem("VERIFY_Q").getNodeValue().toCharArray().length>0)
						 Transflags[TransStatus.VERIFY_REQ]=tags.item(i).getAttributes().getNamedItem("VERIFY_Q").getNodeValue().toCharArray()[0];
					  if(tags.item(i).getAttributes().getNamedItem("VERIFY_Q").getNodeValue().toCharArray().length>0)
						  Transflags[TransStatus.VERIFY_RESP]=tags.item(i).getAttributes().getNamedItem("VERIFY_Q").getNodeValue().toCharArray()[0];
					  if(tags.item(i).getAttributes().getNamedItem("LC_Q").getNodeValue().toCharArray().length>0)
						  Transflags[TransStatus.LC_REQ]=tags.item(i).getAttributes().getNamedItem("LC_Q").getNodeValue().toCharArray()[0];
					  if(tags.item(i).getAttributes().getNamedItem("LC_P").getNodeValue().toCharArray().length>0)
						  Transflags[TransStatus.LC_RESP]=tags.item(i).getAttributes().getNamedItem("LC_P").getNodeValue().toCharArray()[0];
					  if(tags.item(i).getAttributes().getNamedItem("ADV_Q").getNodeValue().toCharArray().length>0)
						  Transflags[TransStatus.ADV_REQ]=tags.item(i).getAttributes().getNamedItem("ADV_Q").getNodeValue().toCharArray()[0];
					  if(tags.item(i).getAttributes().getNamedItem("ADV_P").getNodeValue().toCharArray().length>0)
						  Transflags[TransStatus.ADV_RESP]=tags.item(i).getAttributes().getNamedItem("ADV_P").getNodeValue().toCharArray()[0];
					  if(tags.item(i).getAttributes().getNamedItem("SG_Q").getNodeValue().toCharArray().length>0)
						  Transflags[TransStatus.SG_REQ]=tags.item(i).getAttributes().getNamedItem("SG_Q").getNodeValue().toCharArray()[0];
					  if(tags.item(i).getAttributes().getNamedItem("SG_P").getNodeValue().toCharArray().length>0)
						  Transflags[TransStatus.SG_RESP]=tags.item(i).getAttributes().getNamedItem("SG_P").getNodeValue().toCharArray()[0];
					  if(tags.item(i).getAttributes().getNamedItem("SETTLE_P").getNodeValue().toCharArray().length>0)
						  Transflags[TransStatus.SETTLE_REQ]=tags.item(i).getAttributes().getNamedItem("SETTLE_P").getNodeValue().toCharArray()[0];
					  if(tags.item(i).getAttributes().getNamedItem("SETTLE_Q").getNodeValue().toCharArray().length>0)
						  Transflags[TransStatus.SETTLE_RESP]=tags.item(i).getAttributes().getNamedItem("SETTLE_Q").getNodeValue().toCharArray()[0];
					  if(tags.item(i).getAttributes().getNamedItem("RE_Q").getNodeValue().toCharArray().length>0)
						  Transflags[TransStatus.RE_REQ]=tags.item(i).getAttributes().getNamedItem("RE_Q").getNodeValue().toCharArray()[0];
					  if(tags.item(i).getAttributes().getNamedItem("RE_P").getNodeValue().toCharArray().length>0)
						  Transflags[TransStatus.RE_RESP]=tags.item(i).getAttributes().getNamedItem("RE_P").getNodeValue().toCharArray()[0];
					  if(tags.item(i).getAttributes().getNamedItem("SGADV_Q").getNodeValue().toCharArray().length>0)
						  Transflags[TransStatus.SGADV_REQ]=tags.item(i).getAttributes().getNamedItem("SGADV_Q").getNodeValue().toCharArray()[0];
					  if(tags.item(i).getAttributes().getNamedItem("SGADV_P").getNodeValue().toCharArray().length>0)
						  Transflags[TransStatus.SGADV_RESP]=tags.item(i).getAttributes().getNamedItem("SGADV_P").getNodeValue().toCharArray()[0];
					  
					  Fields.SetTransFlag(Transflags);
					  
					  TransTagList.add(Fields);			  
				
		   }

	   }
		return 	TransTagList;
  }



 public  void main(){
	  		   List<CMASTAG> list= this.Load();
	   for (CMASTAG tag : list) {   
           System.out.format("%s:%s:%s\n",tag.Name,tag.Value,tag.TagName);   
           for (int i=0;i<17;i++) {  
        	   System.out.format("%c:",tag.TransFlags[i]); 
           }  
           System.out.format("\n"); 
       }   
  }
  
}

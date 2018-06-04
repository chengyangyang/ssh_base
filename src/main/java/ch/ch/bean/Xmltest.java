/*
package ch.ch.bean;

import java.io.File;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

public class Xmltest {

	int aa = 0;
	
	 public void testParseXml(){
		 SAXReader sa = new SAXReader();
		 try {
			 String path2 = Xmltest.class.getClassLoader().getResource("").getPath();
			 String path = Xmltest.class.getResource("/").getPath();
			 String relativelyPath=System.getProperty("user.dir"); 
			 String url = relativelyPath+"/target/classes/spring-hibernate.xml";
			 Document document = sa.read(new File(url));
			 Element rootElement = document.getRootElement();
			// getElement(rootElement);
			 getStyleElement(rootElement);
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 
	 @Test
	 public void asdf(){
		 String relativelyPath=System.getProperty("user.dir"); 
		 String url = relativelyPath+"/target/classes/spring-hibernate.xml";
		 XmlUtil.getXmlContext(url);
		 
	 }
	 
	//遍历出所有的节点传入根节点这个是从上往下遍历
	private void getElement(Element next) {
		List elements = next.elements();
		if(!elements.isEmpty()){
			for(int i = 0 ; i < elements.size(); i++){
				Element object = (Element) elements.get(i);
				getElement(object);
			}
		}else{
			System.out.println("节点名称："+next.getName());
			//System.out.println("qqqqqqqqqqqqq"+kg+"qqqqqqqqqqq");
			List attributes = next.attributes(); 
			for(int i = 0;i < attributes.size();i++){
				Attribute object = (Attribute)attributes.get(i);
				System.out.println("属性名称为："+object.getName()+"属性值:"+object.getValue());
				System.out.println("当前节点的文本值："+next.getText());
			}
		}
		
	}
	
	
	
	//遍历出所有的节点传入根节点这个是从上往下遍历
		private void getStyleElement(Element next) {
			List elements = next.elements();
			if(!elements.isEmpty()){
				aa++;
				for(int i = 0 ; i < elements.size(); i++){
					Element object = (Element) elements.get(i);
					String cc = "";
					//拼接节点的空格
					for(int j = 0 ; j < aa-1;j++){
						cc += "	";
					}
					String attr = "";
					//获得节点的属性
					List attributes = object.attributes();
					for(int j = 0; j < attributes.size();j++){
						Attribute object2 = (Attribute)attributes.get(j);
						object2.getName();
						object2.getValue();
						String attr01 = "  "+object2.getName()+"="+object2.getValue();
						attr += attr01;
					}
					
					System.out.println(cc+"<"+object.getName()+attr+">");//获得节点的名称
					//System.out.println("afasdf"+object.getNamespacePrefix());//得到节点的前缀
					//System.out.println("chan"+object.getStringValue().replaceAll("\\s*", ""));//将大片空格全部替换为""
					if(object.elements().size() == 0 && !object.getStringValue().replaceAll("\\s*", "").equals("")){
						System.out.println(cc+object.getStringValue());
						System.out.println(cc+"<"+object.getName()+" />");
					}
					getStyleElement(object);
					Element parent = object.getParent();
					if(parent.getName().equals("beans")){
						aa = 1;
						System.out.println();
					}
					
				}
				
				
			}
			
		}
	
}
*/

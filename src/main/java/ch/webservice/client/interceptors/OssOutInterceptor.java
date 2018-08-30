package ch.webservice.client.interceptors;

import ch.constants.SystemConstants;
import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.DOMUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.namespace.QName;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//import org.apache.cxf.helpers.XMLUtils;

/**
 * @description the business logic before file uploaded 
 * @author Vani
 * @Time 2016-8-2
 */
public class OssOutInterceptor extends AbstractSoapInterceptor{
	
	//date format for this interceptor 
	private static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
	//source application name which call oss.
	private String source; 
	

	public OssOutInterceptor() {
		super(Phase.WRITE); //在写之前
	}

	/* 
	 * 过滤器消息处理
	 * @param message 需要处理的消息
	 * @exception Fault 处理消息过程中抛出的soap异常
	 */
	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		
		String timeStamp = dateTimeFormat.format(new Date());
		QName qname = new QName("RequestSOAPHeader");

		Document doc = (Document) DOMUtils.createDocument();
		Element root = doc.createElement(SystemConstants.XML_HEADER);
		
		//get source related info
		Element source = doc.createElement(SystemConstants.XML_SOURCE);
		/*source.setTextContent(EncryptAndDecryptUtil.getSHAEncryptResultBaseOnAdditionalInfo(BusinessConstants.PROJECT_TRADING));
		Element username = doc.createElement(SystemConstants.XML_USERNAME);
		username.setTextContent(SessionUtil.getUserNameFromSession());
		
		//get authentication related info
		Element eTimestamp = doc.createElement(SystemConstants.XML_TIMESTAMP);
		eTimestamp.setTextContent(timeStamp);
		Element eLicense = doc.createElement(SystemConstants.XML_LICENSE);
		eLicense.setTextContent(EncryptAndDecryptUtil.getSHAEncryptResultBaseOnAdditionalInfo(timeStamp));
		*/
		Element child = doc.createElementNS(null,
				SystemConstants.XML_AUTHENTICATION);
	/*
		child.appendChild(eTimestamp);
		child.appendChild(eLicense);
		child.appendChild(source);
		child.appendChild(username);*/
		root.appendChild(child);
		// 只是打印xml内容到控制台,可删除
//		XMLUtils.printDOM(root);
		
		SoapHeader head = new SoapHeader(qname, root);
		List<Header> headers = message.getHeaders();
		headers.add(head);
		
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	

}

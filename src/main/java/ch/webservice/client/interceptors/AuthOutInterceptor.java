package ch.webservice.client.interceptors;

import ch.constants.SystemConstants;
import ch.util.EncryptAndDecryptUtil;
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

public class AuthOutInterceptor extends AbstractSoapInterceptor{
	
	protected static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	

	public AuthOutInterceptor() {
		super(Phase.WRITE); //在写之前
	}

	@Override
	public void handleMessage(SoapMessage message) throws Fault {

		String timeStamp = dateTimeFormat.format(new Date());
		QName qname = new QName("RequestSOAPHeader");

		Document doc = (Document) DOMUtils.createDocument();
		Element root = doc.createElement(SystemConstants.XML_HEADER);
		//构造用户信息的结点
		/*Element eUser = doc.createElement(SystemConstants.XML_USERNAME);
		eUser.setTextContent(SessionUtil.getUserNameFromSession());
		Element eUserId = doc.createElement(SystemConstants.XML_USERID);
		eUserId.setTextContent(String.valueOf(SessionUtil.getUserIdFromSession()));
		Element ePhone = doc.createElement(SystemConstants.XML_PHONE);
		ePhone.setTextContent(String.valueOf(SessionUtil.getUserPhoneFromSession()));
		Element eRealName = doc.createElement(SystemConstants.XML_REALNAME);
		eRealName.setTextContent(String.valueOf(SessionUtil.getRealNameFromSession()));
		Element eEnterCode = doc.createElement(SystemConstants.XML_ENTER_CODE);
		String enterCode = SessionUtil.getEnterCodeFromSession();
		eEnterCode.setTextContent(enterCode != null ? String.valueOf(SessionUtil.getEnterCodeFromSession()) : null);
		Element eEnterId = doc.createElement(SystemConstants.XML_ENTER_ID);
		String enterId = SessionUtil.getEnterIdFromSession();
		eEnterId.setTextContent(enterId != null ? String.valueOf(SessionUtil.getEnterIdFromSession()) : null);
		Element emailName = doc.createElement(SystemConstants.XML_EMAIL);
		emailName.setTextContent(String.valueOf(SessionUtil.getEmailFromSession()));*/
		
		Element eTimestamp = doc.createElement(SystemConstants.XML_TIMESTAMP);
		eTimestamp.setTextContent(timeStamp);
		Element eLicense = doc.createElement(SystemConstants.XML_LICENSE);
		eLicense.setTextContent(EncryptAndDecryptUtil.getSHAEncryptResultBaseOnAdditionalInfo(timeStamp));
		
		Element child = doc.createElementNS(null,
				SystemConstants.XML_AUTHENTICATION);
		Element sessionChild = doc.createElementNS(null,
				SystemConstants.XML_SESSIONBEAN);
		
		//把用户信息通过soap传给后台服务
		/*sessionChild.appendChild(eUser);
		sessionChild.appendChild(eUserId);
		sessionChild.appendChild(ePhone);
		sessionChild.appendChild(eRealName);
		sessionChild.appendChild(eEnterCode);
		sessionChild.appendChild(eEnterId);
		sessionChild.appendChild(emailName);*/
		child.appendChild(eTimestamp);
		child.appendChild(eLicense);
		root.appendChild(child);
		root.appendChild(sessionChild);
		
//		XMLUtils.printDOM(root);// 只是打印xml内容到控制台,可删除
		
		SoapHeader head = new SoapHeader(qname, root);
		List<Header> headers = message.getHeaders();
		headers.add(head);
		
	}
	
}

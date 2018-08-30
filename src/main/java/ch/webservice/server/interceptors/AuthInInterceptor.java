package ch.webservice.server.interceptors;

import ch.constants.SystemConstants;
import ch.util.EncryptAndDecryptUtil;
import ch.util.TRMXMLUtil;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Element;

import javax.xml.soap.SOAPException;
import java.util.List;

//import org.apache.cxf.helpers.XMLUtils;

public class AuthInInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

	public AuthInInterceptor() {
		// 指定该拦截器在哪个阶段被激发
		super(Phase.PRE_INVOKE);
	}

	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		// 获取SOAP消息的全部头
		List<Header> headers = message.getHeaders();

		if (null == headers || headers.size() < 1) {
			throw new Fault(new SOAPException("SOAP消息头格式不对哦！"));
		}

		for (Header header : headers) {
			if (SystemConstants.XML_HEADER.equals(header.getName().getLocalPart())) {
				//获取cxf拦截器转换得到的头信息对象
				Object obj = header.getObject();
				Element element = (Element) obj;
//	                    XMLUtils.printDOM(element);

				String timeStamp = TRMXMLUtil.getNodeValue(element.getChildNodes(), SystemConstants.XML_TIMESTAMP);
				String license = TRMXMLUtil.getNodeValue(element.getChildNodes(), SystemConstants.XML_LICENSE);
				String username = TRMXMLUtil.getNodeValue(element.getChildNodes(), SystemConstants.XML_USERNAME);
				String userId = TRMXMLUtil.getNodeValue(element.getChildNodes(), SystemConstants.XML_USERID);
				String phone = TRMXMLUtil.getNodeValue(element.getChildNodes(), SystemConstants.XML_PHONE);
				String realName = TRMXMLUtil.getNodeValue(element.getChildNodes(), SystemConstants.XML_REALNAME);
				// 军企id
	                    /*String enterCode = TRMXMLUtil.getNodeValue(element.getChildNodes(), SystemConstants.XML_ENTER_CODE);
	                    String enterId = TRMXMLUtil.getNodeValue(element.getChildNodes(), SystemConstants.XML_ENTER_ID);*/
				String email = TRMXMLUtil.getNodeValue(element.getChildNodes(), SystemConstants.XML_EMAIL);
				if (!license.equals(EncryptAndDecryptUtil.getSHAEncryptResultBaseOnAdditionalInfo(timeStamp))) {
					throw new Fault(new SOAPException("Authentication Error!"));
				}

				//把username对象放到theadlocal种，以便不同的thread拥有独立的SessionBean对象
	                   /* this.setSessionBean(username, userId,phone, realName,
	                    		enterCode, enterId, email);
					}*/

			}
		}
	
	/*private void setSessionBean(String username,String userId,String phone, String realName, String enterCode, String enterId, String email) {
		SessionBean sessionBean = new SessionBean();
		sessionBean.setUserName(username);
		sessionBean.setUserId(userId);
		sessionBean.setPhone(phone);
		sessionBean.setRealName(realName);
		sessionBean.setEnterCode(enterCode);
		sessionBean.setEnterId(StringHelper.isNotEmpty(enterId) ? Integer.parseInt(enterId) : null);
		sessionBean.setEmail(email);
		ServerSession.setSessionBean(sessionBean);
	}*/

	}
}
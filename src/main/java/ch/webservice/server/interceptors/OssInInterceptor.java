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

public class OssInInterceptor extends AbstractPhaseInterceptor<SoapMessage>{

	public OssInInterceptor() {
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
					if(SystemConstants.XML_HEADER.equals(header.getName().getLocalPart())){
						//获取cxf拦截器转换得到的头信息对象  
	                    Object obj = header.getObject();
	                    Element element = (Element)obj;
//	                    XMLUtils.printDOM(element);
	                    
	                    String source = TRMXMLUtil.getNodeValue(element.getChildNodes(), SystemConstants.XML_SOURCE);
	                    String username = TRMXMLUtil.getNodeValue(element.getChildNodes(), SystemConstants.XML_USERNAME);
	                    String timeStamp = TRMXMLUtil.getNodeValue(element.getChildNodes(), SystemConstants.XML_TIMESTAMP);
	                    String license = TRMXMLUtil.getNodeValue(element.getChildNodes(), SystemConstants.XML_LICENSE);
	                    
	                    SOAPException exception = null;
	                    
	                    if(!license.equals(EncryptAndDecryptUtil.getSHAEncryptResultBaseOnAdditionalInfo(timeStamp))){
	                    	exception = new SOAPException("Authentication Error!");
	                    	exception.printStackTrace();
	                    	throw new Fault(exception);
	                    }
	                    
	                   /* if( !Arrays.asList(BusinessConstants.PROJECT_ARRAY).contains(getSHAEncryptedProject(source)) ){
	                    	exception = new SOAPException("unknown source!");
	                    	exception.printStackTrace();
		                    throw new Fault(exception);
	                    }*/
	                    
	                   // OSSSession.setSessionBean(new OSSInfoBean(getSHAEncryptedProject(source), username));
	                    
					}
					
				}
	}
	
	/*private String getSHAEncryptedProject(String encrypted){
		
		if( null != encrypted ){
			for(String project : Arrays.asList(BusinessConstants.PROJECT_ARRAY)){
				if(encrypted.equals(EncryptAndDecryptUtil.getSHAEncryptResultBaseOnAdditionalInfo(project))){
					return project;
				}
			}
		}
		
		return null;
		
	}*/
	
}

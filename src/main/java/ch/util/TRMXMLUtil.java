package ch.util;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TRMXMLUtil {
	
	private TRMXMLUtil() {
		super();
	}

	public static Node getNodeByName(NodeList nodeList, String nodeName){
		
		if(null == nodeName){
			return null;
		}
		
		for(int i=0; i<nodeList.getLength(); i++){
			Node currentNode = nodeList.item(i);
			if(null != currentNode && nodeName.equals(currentNode.getNodeName())){
				return currentNode;
			}
			if(currentNode.hasChildNodes()){
				Node tempNode = getNodeByName(nodeList.item(i).getChildNodes(), nodeName);
				if(null != tempNode && nodeName.equals(tempNode.getNodeName())){
					return tempNode;
				}
			}
			
		}
		
		return null;
	}
	
	public static String getNodeValue(NodeList nodeList, String nodeName){
		Node node = getNodeByName(nodeList, nodeName);
		String value = "";
		if(null != node && node.hasChildNodes() && null != node.getChildNodes().item(0)){
			value = node.getChildNodes().item(0).getNodeValue();
		}
		return value;
	}

}

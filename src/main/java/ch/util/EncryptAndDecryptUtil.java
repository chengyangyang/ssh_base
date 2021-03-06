package ch.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptAndDecryptUtil {
	
	public static String OFFSET_INFO = "TRM";
	public static final String KEY_SHA = "SHA";
	public static final String DEFAULT_ENCRYPTED_RESULT = "-3el6ucrlvr0npve1oqjn96v6evedpt5m";
	
	public static String getSHAEncryptResultBaseOnAdditionalInfo(String toEncryptStr){
		
		return getSHAEncryptResult(toEncryptStr + OFFSET_INFO);
		
	}
	
	public static String getSHAEncryptResult(String toEncryptStr){
		BigInteger sha =null;
        byte[] inputData = toEncryptStr.getBytes();   
        try {
             MessageDigest messageDigest = MessageDigest.getInstance(KEY_SHA);  
             messageDigest.update(inputData);
             sha = new BigInteger(messageDigest.digest());   
        } catch (Exception e) {
        	return DEFAULT_ENCRYPTED_RESULT;
        }
        
        return sha.toString(32);
	};
	
	/**
	  * MD5 获取加密后的字符串
	  * @param input
	  * @return
	  */
	 public static String stringMD5(String pw) {
		 try {  
	        // 拿到一个MD5转换器（如果想要SHA1参数换成”SHA1”）  
	        MessageDigest messageDigest =MessageDigest.getInstance("md5");  
	        // 输入的字符串转换成字节数组  
	        byte[] inputByteArray = pw.getBytes();  
	        // inputByteArray是输入字符串转换得到的字节数组  
	        messageDigest.update(inputByteArray);  
	        // 转换并返回结果，也是字节数组，包含16个元素  
	        byte[] resultByteArray = messageDigest.digest();  
	        // 字符数组转换成字符串返回  
	        return byteArrayToHex(resultByteArray);  
	     } catch (NoSuchAlgorithmException e) {  
	        return null;  
	     }  
	 }
	 
	 public static String byteArrayToHex(byte[] byteArray) {  
        // 首先初始化一个字符数组，用来存放每个16进制字符  
        char[] hexDigits = {'0','1','2','3','4','5','6','7','8','9', 'a','b','c','d','e','f' };  
        // new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））  
        char[] resultCharArray =new char[byteArray.length * 2];  
        // 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去  
        int index = 0; 
        for (byte b : byteArray) {  
           resultCharArray[index++] = hexDigits[b>>> 4 & 0xf];  
           resultCharArray[index++] = hexDigits[b& 0xf];  
        }
        // 字符数组组合成字符串返回  
        return new String(resultCharArray);  
    }


	/**
	 * 将字符串进行sha1加密
	 *
	 * @param str 需要加密的字符串
	 * @return 加密后的内容
	 */
	public static String sha1(String str) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.update(str.getBytes());
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static void main(String[] args) {
		System.out.println(EncryptAndDecryptUtil.getSHAEncryptResult("TRM"));
		
	}

}

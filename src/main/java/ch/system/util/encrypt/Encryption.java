package ch.system.util.encrypt;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

/**
 * <p>加密/解密
 * <p>2017-4-11 13:27:36
 * @author zhaosimiao
 *
 */
public class Encryption {
	
	/**
	 * DES密钥
	 * 注意：DES加密和解密过程中，密钥长度都必须是8的倍数,最长为56位
	 */
	private static final String key = "01s71avd22afd2344ere22su34esa123ia01d23a0fd0f2d02cadf231";
	
	/**
	 * DES加密
	 * @param str
	 * @return
	 */
	@SuppressWarnings("restriction")
	public static String encrypt(String str) {
		String encryptStr = "";
		try{
	        SecureRandom random = new SecureRandom();
	        DESKeySpec desKey = new DESKeySpec(key.getBytes());
	        //创建一个密匙工厂，然后用它把DESKeySpec转换成
	        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
	        SecretKey securekey = keyFactory.generateSecret(desKey);
	        //Cipher对象实际完成加密操作
	        Cipher cipher = Cipher.getInstance("DES");
	        //用密匙初始化Cipher对象
	        cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
	        //现在，获取数据并加密
	        //正式执行加密操作
	        encryptStr = new sun.misc.BASE64Encoder().encode(cipher.doFinal(str.getBytes()));
        }catch(Throwable e){
                e.printStackTrace();
        }
		return encryptStr;
	}
	
	/**
	 * DES解密
	 * @param str
	 * @return
	 */
	@SuppressWarnings("restriction")
	public static String decrypt(String str) {
		String decryptStr = "";
		try {
			// DES算法要求有一个可信任的随机数源
			SecureRandom random = new SecureRandom();
			// 创建一个DESKeySpec对象
			DESKeySpec desKey = new DESKeySpec(key.getBytes());
			// 创建一个密匙工厂
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			// 将DESKeySpec对象转换成SecretKey对象
			SecretKey securekey = keyFactory.generateSecret(desKey);
			// Cipher对象实际完成解密操作
			Cipher cipher = Cipher.getInstance("DES");
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.DECRYPT_MODE, securekey, random);
			// 真正开始解密操作
			decryptStr = new String(cipher.doFinal(new sun.misc.BASE64Decoder().decodeBuffer(str)));
		} catch (Exception e) {
			throw new RuntimeException("解密错误，错误信息：", e); 
		}
		return decryptStr;
	}
	
	public static void main(String[] args) {
		String jdbcPassword = "admin";
		String password = decrypt("Wv6Y+AdZ/Wc=");
		System.out.println(encrypt(jdbcPassword)+password);
	}
	
}

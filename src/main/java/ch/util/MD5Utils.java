package ch.util;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import java.security.MessageDigest;

/**
 * @author <a href="mailto:humorbeau@163.com">胡渊博</a>
 * @date 2016年12月17日
 * @version 1.0
 */
public class MD5Utils {

	private static Md5PasswordEncoder instance;

	private MD5Utils (){}

	/**
	 * 通过单例获取Md5PasswordEncoder
	 * @return
	 */
	public static synchronized Md5PasswordEncoder getInstance() {
		if (instance == null) {
			instance = new Md5PasswordEncoder();
		}
		return instance;
	}

	private static byte[] md5(String s) {
		MessageDigest algorithm;
		try {
			algorithm = MessageDigest.getInstance("MD5");
			algorithm.reset();
			algorithm.update(s.getBytes("UTF-8"));
			byte[] messageDigest = algorithm.digest();
			return messageDigest;
		} catch (Exception e) {
		}
		return null;
	}

	private static final String toHex(byte hash[]) {
		if (hash == null) {
			return null;
		}
		StringBuffer buf = new StringBuffer(hash.length * 2);
		int i;

		for (i = 0; i < hash.length; i++) {
			if ((hash[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString(hash[i] & 0xff, 16));
		}
		return buf.toString();
	}

	public static String hash(String s) {
		try {
			return new String(toHex(md5(s)).getBytes("UTF-8"), "UTF-8");
		} catch (Exception e) {
			return s;
		}
	}

}

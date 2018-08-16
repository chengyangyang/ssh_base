package ch.system.util.encrypt;

import org.hibernate.internal.util.StringHelper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.Properties;

public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
			throws BeansException {
		// 获取配置文件中密码的密文
		String jdbcPassword = props.getProperty("jdbc.password");
		if(StringHelper.isNotEmpty(jdbcPassword)) {
			// 解密密文
			String password = Encryption.decrypt(jdbcPassword);
			// 解密后的密码放入props中
			props.setProperty("jdbc.password", password);
		}
		super.processProperties(beanFactoryToProcess, props);
	}
	
	/*public static void main(String[] args) {
		String password = Encryption.encrypt("trmtxj");
		System.out.println(password);
	}*/
	
}

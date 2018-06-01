package ch.util.converter;

import com.trm.util.TrmDateUtil;
import org.dozer.DozerConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;

public class TimestampToStringConverter extends DozerConverter<Timestamp, String> {

	private static Logger logger = LoggerFactory.getLogger(TimestampToStringConverter.class);
	
	public TimestampToStringConverter() {
		super(Timestamp.class, String.class);
	}
	
	/**
	 * <p>Title: convertTo</p>
	 * <p>Description: 从XMLGregorianCalendar转化为String</p>
	 * @param source
	 * @param destination
	 * @return
	 */
	@Override
	public String convertTo(Timestamp source, String destination) {
		if(null != source) {
			return TrmDateUtil.convertDate(source);
		} else {
			try {
				throw new Exception("日期类转换String异常");
			} catch (Exception e) {
				logger.error("日期类为null,转换String异常 :" + source);
			}
		}
		return null;
	}

	/**
	 * <p>Title: convertFrom</p>
	 * <p>Description: </p>
	 * @param source
	 * @param destination
	 * @return
	 */
	@Override
	public Timestamp convertFrom(String source, Timestamp destination) {
		return null;
	}

}

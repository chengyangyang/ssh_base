package ch.util.converter;

import org.dozer.DozerConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class StringConverterToTimestamp extends DozerConverter<String, Timestamp> {

	private static Logger logger = LoggerFactory.getLogger(StringConverterToTimestamp.class);
	private static final String TIMESTAMP_PATTERN= "yyyy-MM-dd HH:mm:ss";
	private static final String DATE_PATTERN= "yyyy-MM-dd";
	public StringConverterToTimestamp() {
		super(String.class, Timestamp.class);
	}

	@Override
	public Timestamp convertTo(String time, Timestamp destination) {
		Date date = new Date(); 
		DateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
		try {
			if(null != time && !"".equals(time)) {
				date = sdf.parse(time);
				Timestamp ts = new Timestamp(date.getTime());
				return ts;
			}
			
		} catch (ParseException e) {
			logger.error("时间转换异常:" + e.getMessage());
//			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String convertFrom(Timestamp source, String destination) {
		// TODO Auto-generated method stub
		return null;
	}

}

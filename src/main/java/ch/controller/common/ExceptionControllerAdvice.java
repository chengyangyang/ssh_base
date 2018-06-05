package ch.controller.common;

import ch.constants.ExceptionConstants;
import ch.exception.BusinessException;
import ch.exception.ExceptionResponse;
import ch.exception.Reason;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 异常处理类
 * @author zhaosimiao 2017-5-9 15:42:36
 */
@ControllerAdvice
public class ExceptionControllerAdvice {
	
	public static Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

	/**
	 * 处理自定义的业务异常
	 * @param e 业务异常
	 * @return
	 */
	@ExceptionHandler(BusinessException.class)
	public @ResponseBody
	ExceptionResponse businessExceptionHandle(BusinessException e) {
		
		ExceptionResponse response = new ExceptionResponse();
		response.setResponsecode(ExceptionConstants.BUSI_ERR_9999);
		response.setErrorType(ExceptionConstants.ERR_TYPE_0);
		
		Reason reason = new Reason();
		reason.setDesc(e.getExceptionMsg());
		reason.setReasoncode(e.getExceptionCode());
		response.setReason(reason);
		
		response.setExplain(ExceptionConstants.EXPLAIN_ERR_1);
		
		// 将具体报错信息写入日志
		e.printStackTrace();
		return response;
	}
}

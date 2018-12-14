package ch.common.exceptions;

import ch.common.exceptions.bean.BindingExcepObj;
import ch.constants.ExceptionConstants;
import ch.common.exceptions.bean.BusinessException;
import ch.common.exceptions.bean.ExceptionResponse;
import ch.common.exceptions.bean.Reason;

import ch.constants.SystemConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * 异常处理类(就是将错误的信息返回到页面中)
 * @author  
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

	/**
	 * 表单校验
	 * @param e
	 * @return
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public @ResponseBody ExceptionResponse JsonBindExceptionHandle(MethodArgumentNotValidException e) {

		BindingResult br = e.getBindingResult();
		List<FieldError> errorList = br.getFieldErrors();
		ExceptionResponse resp = new ExceptionResponse();
		BindingExcepObj excepObj = null;

		for (FieldError error : errorList) {
			excepObj = new BindingExcepObj();
			excepObj.setFieldName(error.getField());
			excepObj.setMessage(error.getDefaultMessage());
			resp.getFiledlist().add(excepObj);
		}
		resp.setErrorType(SystemConstants.ERROR_TYPE_1);
		resp.setResponsecode(SystemConstants.RESPONSECOE_008);
		Reason reason = new Reason();
		reason.setDesc("表单验证有误！");
		reason.setReasoncode("");
		resp.setReason(reason);
		return resp;
	}


}

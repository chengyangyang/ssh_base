package ch.exception;

public class BusinessException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String exceptionName;
	private String exceptionMsg;
	private String exceptionCode;
	public String getExceptionName() {
		return exceptionName;
	}
	public void setExceptionName(String exceptionName) {
		this.exceptionName = exceptionName;
	}
	public String getExceptionMsg() {
		return exceptionMsg;
	}
	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}
	public String getExceptionCode() {
		return exceptionCode;
	}
	public void setExceptionCode(String exceptionCode) {
		this.exceptionCode = exceptionCode;
	}
	
}

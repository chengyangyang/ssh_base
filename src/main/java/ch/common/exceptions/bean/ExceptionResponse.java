package ch.common.exceptions.bean;

import java.util.ArrayList;
import java.util.List;

public class ExceptionResponse {

	private String explain;
	private String responsecode;
	private String errorType;
	private Reason reason;
	private List<BindingExcepObj> filedErrorList;

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}
	
	public String getResponsecode() {
		return responsecode;
	}

	public void setResponsecode(String responsecode) {
		this.responsecode = responsecode;
	}

	public String getErrorType() {
		return errorType;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}
	
	public Reason getReason() {
		return reason;
	}

	public void setReason(Reason reason) {
		this.reason = reason;
	}

	public List<BindingExcepObj> getFiledlist() {
		if (filedErrorList == null) {
			filedErrorList = new ArrayList<BindingExcepObj>();
		}
		return filedErrorList;
	}

	public void setFiledErrorList(List<BindingExcepObj> filedErrorList) {
		this.filedErrorList = filedErrorList;
	}

	@Override
	public String toString() {
		return "ExceptionResponse [explain=" + explain + ", responsecode=" + responsecode + ", errorType=" + errorType
				+ ", reason=" + reason + ", filedErrorList=" + filedErrorList + "]";
	}

}

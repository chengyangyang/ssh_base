package ch.extend.entity;

import java.sql.Timestamp;

public class CompanyParent {
	
	private String companyName;
	private String companyAddress;
	private Timestamp createDate;
	
	public CompanyParent() {
		super();
	}

	public CompanyParent(String companyName, String companyAddress, Timestamp createDate) {
		super();
		this.companyName = companyName;
		this.companyAddress = companyAddress;
		this.createDate = createDate;
	}
	
	public void haveThing() {
		System.out.println("父类公司有十栋楼房");
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	
}

package ch.extend.entity;

import java.sql.Timestamp;

public class CompanyChildren extends CompanyParent{

	private String companyChildrenAddress;
	
	public CompanyChildren() {
		super();
	}
	
	public String getCompanyChildrenAddress() {
		return companyChildrenAddress;
	}

	public void setCompanyChildrenAddress(String companyChildrenAddress) {
		this.companyChildrenAddress = companyChildrenAddress;
	}

	@Override
	public void haveThing() {
		super.haveThing();
		System.out.println("子类公司有五栋房子");
	}
	
	
	
	public static void main(String[] args) {
		//第一种情况这种情况只能设置父类
		CompanyParent company = new CompanyChildren();
		company.setCompanyName("daad");
		company.setCompanyAddress("ad");
		//第二种情况,就可以设置子类
		CompanyParent company1 = new CompanyChildren();
		if (company1 instanceof CompanyChildren) {
			CompanyChildren newCompany = (CompanyChildren) company1;
			newCompany.setCompanyChildrenAddress("dfafa");
		}
		
	}

	
	
	
}

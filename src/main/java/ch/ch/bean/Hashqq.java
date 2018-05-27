package ch.ch.bean;

public class Hashqq {

	private int cc;
	private int aa;
	
	
	public Hashqq() {
		super();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + aa;
		result = prime * result + cc;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hashqq other = (Hashqq) obj;
		if (aa != other.aa)
			return false;
		if (cc != other.cc)
			return false;
		return true;
	}
	public Hashqq(int cc, int aa) {
		super();
		this.cc = cc;
		this.aa = aa;
	}
	public int getCc() {
		return cc;
	}
	public void setCc(int cc) {
		this.cc = cc;
	}
	public int getAa() {
		return aa;
	}
	public void setAa(int aa) {
		this.aa = aa;
	}
	
	
	
}

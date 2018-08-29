package ch.tool.sqltobean;

public class StringFormatConfig {

	private Boolean toLowerCase = false; //是否转化为小写
	private Boolean capital = false; //是否首字母大写
	private Boolean hump = true; //是否驼峰命名
	private Boolean remove_ = true;//是否移除_
	private String db = "none"; //默认不进行转化

	public StringFormatConfig() {
	}
	
	
	public StringFormatConfig(Boolean toLowerCase, Boolean capital, Boolean hump, Boolean remove_) {
		this.toLowerCase = toLowerCase;
		this.capital = capital;
		this.hump = hump;
		this.remove_ = remove_;
	}
	
	public StringFormatConfig(String db,Boolean toLowerCase, Boolean capital, Boolean hump, Boolean remove_) {
		this.db = db;
		this.toLowerCase = toLowerCase;
		this.capital = capital;
		this.hump = hump;
		this.remove_ = remove_;
	}
	
	
	public static StringFormatConfig setConfig(Boolean toLowerCase, Boolean capital, Boolean hump, Boolean remove_) {
		return new StringFormatConfig(toLowerCase,capital,hump,remove_);
	}

	public Boolean getToLowerCase() {
		return toLowerCase;
	}
	public void setToLowerCase(Boolean toLowerCase) {
		this.toLowerCase = toLowerCase;
	}
	public Boolean getCapital() {
		return capital;
	}
	public void setCapital(Boolean capital) {
		this.capital = capital;
	}
	public Boolean getHump() {
		return hump;
	}
	public void setHump(Boolean hump) {
		this.hump = hump;
	}
	public Boolean getRemove_() {
		return remove_;
	}
	public void setRemove_(Boolean remove_) {
		this.remove_ = remove_;
	}


	public String getDb() {
		return db;
	}


	public void setDb(String db) {
		this.db = db;
	}

}

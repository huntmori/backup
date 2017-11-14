package com.sundol.VO;

import 	java.sql.Date;
//	sql을 사용하면 날짜, 시간 따로 나오고 util을 사용하면 날짜 + 시간이 나온다.

public class ShopManagerVO {
	private	String		code;
	private	String		name;
	private	Date		wday;
	private	String		mname;
	private	String		sname;
	private	String		lcode;
	private	String		mcode;
	
	
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getMcode() {
		return mcode;
	}
	public void setMcode(String mcode) {
		this.mcode = mcode;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getLcode() {
		return lcode;
	}
	public void setLcode(String lcode) {
		this.lcode = lcode;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getWday() {
		return wday;
	}
	public void setWday(Date wday) {
		this.wday = wday;
	}
}

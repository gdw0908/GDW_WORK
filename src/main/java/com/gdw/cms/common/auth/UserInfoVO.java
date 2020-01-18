package com.gdw.cms.common.auth;

public class UserInfoVO implements UserInfo {
	private static final long serialVersionUID = 2629032401634524434L;
	
	String username;
	String password;	
	String adminId;				
	String adminPass;			
	String adminName;			
	String adminGradeFlag;
	String regUserid;			
	String regDttime;			
	String modUserid;			
	String modDttime;			
	String adminUseYn;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getAdminPass() {
		return adminPass;
	}
	public void setAdminPass(String adminPass) {
		this.adminPass = adminPass;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAdminGradeFlag() {
		return adminGradeFlag;
	}
	public void setAdminGradeFlag(String adminGradeFlag) {
		this.adminGradeFlag = adminGradeFlag;
	}
	public String getRegUserid() {
		return regUserid;
	}
	public void setRegUserid(String regUserid) {
		this.regUserid = regUserid;
	}
	public String getRegDttime() {
		return regDttime;
	}
	public void setRegDttime(String regDttime) {
		this.regDttime = regDttime;
	}
	public String getModUserid() {
		return modUserid;
	}
	public void setModUserid(String modUserid) {
		this.modUserid = modUserid;
	}
	public String getModDttime() {
		return modDttime;
	}
	public void setModDttime(String modDttime) {
		this.modDttime = modDttime;
	}
	public String getAdminUseYn() {
		return adminUseYn;
	}
	public void setAdminUseYn(String adminUseYn) {
		this.adminUseYn = adminUseYn;
	}
	
}

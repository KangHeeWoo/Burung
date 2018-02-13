package admin.vo;

public class MemberVo {
	//
	private int memNum;
	private String memId;
	private String memAddr;
	private String memPhone;
	private String memEmail;
	private String memBirth;
	private String memName;
	public MemberVo(int memNum, String memId, String memAddr, String memPhone, String memEmail, String memBirth,
			String memName) {
		this.memNum = memNum;
		this.memId = memId;
		this.memAddr = memAddr;
		this.memPhone = memPhone;
		this.memEmail = memEmail;
		this.memBirth = memBirth;
		this.memName = memName;
	}
	public MemberVo() {
	}
	public int getMemNum() {
		return memNum;
	}
	public void setMemNum(int memNum) {
		this.memNum = memNum;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemAddr() {
		return memAddr;
	}
	public void setMemAddr(String memAddr) {
		this.memAddr = memAddr;
	}
	public String getMemPhone() {
		return memPhone;
	}
	public void setMemPhone(String memPhone) {
		this.memPhone = memPhone;
	}
	public String getMemEmail() {
		return memEmail;
	}
	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}
	public String getMemBirth() {
		return memBirth;
	}
	public void setMemBirth(String memBirth) {
		this.memBirth = memBirth;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}

}

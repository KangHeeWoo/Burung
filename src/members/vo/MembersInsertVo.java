package members.vo;

public class MembersInsertVo {
	private int memNum;
	private String memId;
	private String memPwd;
	private String memAddr;
	private String memPhone;
	private String memEmail;
	private String memBirth;
	private String memName;
	private String memRegDate;
	public MembersInsertVo(int memNum, String memId, String memPwd, String memAddr, String memPhone, String memEmail,
			String memBirth, String memName, String memRegDate) {
		super();
		this.memNum = memNum;
		this.memId = memId;
		this.memPwd = memPwd;
		this.memAddr = memAddr;
		this.memPhone = memPhone;
		this.memEmail = memEmail;
		this.memBirth = memBirth;
		this.memName = memName;
		this.memRegDate = memRegDate;
	}
	@Override
	public String toString() {
		return "MembersInsertVo [memNum=" + memNum + ", memId=" + memId + ", memPwd=" + memPwd + ", memAddr=" + memAddr
				+ ", memPhone=" + memPhone + ", memEmail=" + memEmail + ", memBirth=" + memBirth + ", memName="
				+ memName + ", memRegDate=" + memRegDate + "]";
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
	public String getMemPwd() {
		return memPwd;
	}
	public void setMemPwd(String memPwd) {
		this.memPwd = memPwd;
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
	public String getMemRegDate() {
		return memRegDate;
	}
	public void setMemRegDate(String memRegDate) {
		this.memRegDate = memRegDate;
	}
}

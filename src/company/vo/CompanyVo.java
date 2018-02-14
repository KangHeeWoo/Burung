package company.vo;

public class CompanyVo {
	private int offNum;
	private String offName;
	private String offAddr;
	private String offTel;
	private String offEmail;
	private String offInfo;
	
	public CompanyVo() {}

	public CompanyVo(int offNum, String offName, String offAddr, String offTel, String offEmail, String offInfo) {
		super();
		this.offNum = offNum;
		this.offName = offName;
		this.offAddr = offAddr;
		this.offTel = offTel;
		this.offEmail = offEmail;
		this.offInfo = offInfo;
	}

	public int getOffNum() {
		return offNum;
	}

	public void setOffNum(int offNum) {
		this.offNum = offNum;
	}

	public String getOffName() {
		return offName;
	}

	public void setOffName(String offName) {
		this.offName = offName;
	}

	public String getOffAddr() {
		return offAddr;
	}

	public void setOffAddr(String offAddr) {
		this.offAddr = offAddr;
	}

	public String getOffTel() {
		return offTel;
	}

	public void setOffTel(String offTel) {
		this.offTel = offTel;
	}

	public String getOffEmail() {
		return offEmail;
	}

	public void setOffEmail(String offEmail) {
		this.offEmail = offEmail;
	}

	public String getOffInfo() {
		return offInfo;
	}

	public void setOffInfo(String offInfo) {
		this.offInfo = offInfo;
	}
	
}

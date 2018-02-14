package admin.vo;

public class SelectOptionVo {
	private int sListNum;
	private int optNum;
	private int sOptNum;
	private String optType;
	private String optInfo;
	private int optPrice;
	public SelectOptionVo(int sListNum, int optNum, int sOptNum, String optType, String optInfo, int optPrice) {
		super();
		this.sListNum = sListNum;
		this.optNum = optNum;
		this.sOptNum = sOptNum;
		this.optType = optType;
		this.optInfo = optInfo;
		this.optPrice = optPrice;
	}
	public SelectOptionVo() {
		super();
	}
	public int getsListNum() {
		return sListNum;
	}
	public void setsListNum(int sListNum) {
		this.sListNum = sListNum;
	}
	public int getOptNum() {
		return optNum;
	}
	public void setOptNum(int optNum) {
		this.optNum = optNum;
	}
	public int getsOptNum() {
		return sOptNum;
	}
	public void setsOptNum(int sOptNum) {
		this.sOptNum = sOptNum;
	}
	public String getOptType() {
		return optType;
	}
	public void setOptType(String optType) {
		this.optType = optType;
	}
	public String getOptInfo() {
		return optInfo;
	}
	public void setOptInfo(String optInfo) {
		this.optInfo = optInfo;
	}
	public int getOptPrice() {
		return optPrice;
	}
	public void setOptPrice(int optPrice) {
		this.optPrice = optPrice;
	}
	
}

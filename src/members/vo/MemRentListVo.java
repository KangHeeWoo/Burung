package members.vo;

public class MemRentListVo {
	private int rListNum;
	private String rStartDate;
	private String rEndDate;
	private int rTotPrice;
	private String renState;
	private int renNum;
	private String rCarName;
	private int memNum;
	@Override
	public String toString() {
		return "MemRentListVo [rListNum=" + rListNum + ", rStartDate=" + rStartDate + ", rEndDate=" + rEndDate
				+ ", rTotPrice=" + rTotPrice + ", renState=" + renState + ", renNum=" + renNum + ", rCarName="
				+ rCarName + ", memNum=" + memNum + "]";
	}
	public int getrListNum() {
		return rListNum;
	}
	public void setrListNum(int rListNum) {
		this.rListNum = rListNum;
	}
	public String getrStartDate() {
		return rStartDate;
	}
	public void setrStartDate(String rStartDate) {
		this.rStartDate = rStartDate;
	}
	public String getrEndDate() {
		return rEndDate;
	}
	public void setrEndDate(String rEndDate) {
		this.rEndDate = rEndDate;
	}
	public int getrTotPrice() {
		return rTotPrice;
	}
	public void setrTotPrice(int rTotPrice) {
		this.rTotPrice = rTotPrice;
	}
	public String getRenState() {
		return renState;
	}
	public void setRenState(String renState) {
		this.renState = renState;
	}
	public int getRenNum() {
		return renNum;
	}
	public void setRenNum(int renNum) {
		this.renNum = renNum;
	}
	public String getrCarName() {
		return rCarName;
	}
	public void setrCarName(String rCarName) {
		this.rCarName = rCarName;
	}
	public int getMemNum() {
		return memNum;
	}
	public void setMemNum(int memNum) {
		this.memNum = memNum;
	}
	public MemRentListVo(int rListNum, String rStartDate, String rEndDate, int rTotPrice, String renState, int renNum,
			String rCarName, int memNum) {
		super();
		this.rListNum = rListNum;
		this.rStartDate = rStartDate;
		this.rEndDate = rEndDate;
		this.rTotPrice = rTotPrice;
		this.renState = renState;
		this.renNum = renNum;
		this.rCarName = rCarName;
		this.memNum = memNum;
	}
}

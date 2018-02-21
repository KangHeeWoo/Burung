package rent.vo;

import java.sql.Date;

public class RentListVo {
	private int rListNum;
	private Date rStartDate;
	private Date rEndDate;
	private int rTotPrice;
	private String renState;
	private int memNum;
	private int renNum;
	@Override
	public String toString() {
		return "RentListVo [rListNum=" + rListNum + ", rStartDate=" + rStartDate + ", rEndDate=" + rEndDate
				+ ", rTotPrice=" + rTotPrice + ", renState=" + renState + ", memNum=" + memNum + ", renNum=" + renNum
				+ "]";
	}
	public RentListVo(int rListNum, Date rStartDate, Date rEndDate, int rTotPrice, String renState, int memNum,
			int renNum) {
		super();
		this.rListNum = rListNum;
		this.rStartDate = rStartDate;
		this.rEndDate = rEndDate;
		this.rTotPrice = rTotPrice;
		this.renState = renState;
		this.memNum = memNum;
		this.renNum = renNum;
	}
	public int getrListNum() {
		return rListNum;
	}
	public void setrListNum(int rListNum) {
		this.rListNum = rListNum;
	}
	public Date getrStartDate() {
		return rStartDate;
	}
	public void setrStartDate(Date rStartDate) {
		this.rStartDate = rStartDate;
	}
	public Date getrEndDate() {
		return rEndDate;
	}
	public void setrEndDate(Date rEndDate) {
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
	public int getMemNum() {
		return memNum;
	}
	public void setMemNum(int memNum) {
		this.memNum = memNum;
	}
	public int getRenNum() {
		return renNum;
	}
	public void setRenNum(int renNum) {
		this.renNum = renNum;
	}
}
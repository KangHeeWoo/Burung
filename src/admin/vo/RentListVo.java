package admin.vo;

import java.sql.Date;

public class RentListVo {
	//
	private int rListNum;
	private Date rStartDate;
	private Date rEndDate;
	private int rTotPrice;
	private String renState;
	private int renNum;
	private String rCarName;
	private int memNum;


	public RentListVo(int rListNum, Date rStartDate, Date rEndDate, int rTotPrice, String renState, int renNum,
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

	public RentListVo(int rListNum, Date rStartDate, Date rEndDate, int rTotPrice, String renState,int renNum) {
		super();
		this.rListNum = rListNum;
		this.rStartDate = rStartDate;
		this.rEndDate = rEndDate;
		this.rTotPrice = rTotPrice;
		this.renState = renState;
		this.renNum = renNum;
	}
	
	public RentListVo(int rListNum, Date rStartDate, Date rEndDate, int rTotPrice, String renState, int renNum,
			String rCarName) {
		super();
		this.rListNum = rListNum;
		this.rStartDate = rStartDate;
		this.rEndDate = rEndDate;
		this.rTotPrice = rTotPrice;
		this.renState = renState;
		this.renNum = renNum;
		this.rCarName = rCarName;
	}

	public RentListVo() {
		super();
	}
	
	public int getMemNum() {
		return memNum;
	}
	
	public void setMemNum(int memNum) {
		this.memNum = memNum;
	}
	
	public String getrCarName() {
		return rCarName;
	}
	
	public void setrCarName(String rCarName) {
		this.rCarName = rCarName;
	}
	public int getRenNum() {
		return renNum;
	}

	public void setRenNum(int renNum) {
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
	
}

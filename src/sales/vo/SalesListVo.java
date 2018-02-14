package sales.vo;

import java.sql.Date;

public class SalesListVo {
	private int sListNum;
	private int salPrice;
	private String salState;
	private int memNum;
	private int salNum;
	private Date salDate;
	public int getsListNum() {
		return sListNum;
	}
	public void setsListNum(int sListNum) {
		this.sListNum = sListNum;
	}
	public int getSalPrice() {
		return salPrice;
	}
	public void setSalPrice(int salPrice) {
		this.salPrice = salPrice;
	}
	public String getSalState() {
		return salState;
	}
	public void setSalState(String salState) {
		this.salState = salState;
	}
	public int getMemNum() {
		return memNum;
	}
	public void setMemNum(int memNum) {
		this.memNum = memNum;
	}
	public int getSalNum() {
		return salNum;
	}
	public void setSalNum(int salNum) {
		this.salNum = salNum;
	}
	public Date getSalDate() {
		return salDate;
	}
	public void setSalDate(Date salDate) {
		this.salDate = salDate;
	}
	public SalesListVo(int sListNum, int salPrice, String salState, int memNum, int salNum, Date salDate) {
		super();
		this.sListNum = sListNum;
		this.salPrice = salPrice;
		this.salState = salState;
		this.memNum = memNum;
		this.salNum = salNum;
		this.salDate = salDate;
	}
	@Override
	public String toString() {
		return "SalesListVo [sListNum=" + sListNum + ", salPrice=" + salPrice + ", salState=" + salState + ", memNum="
				+ memNum + ", salNum=" + salNum + ", salDate=" + salDate + "]";
	}
}

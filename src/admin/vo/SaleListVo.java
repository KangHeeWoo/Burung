package admin.vo;

import java.sql.Date;

public class SaleListVo {
	//
	private int sListNum;
	private int salPrice;
	private String salState;
	private int salNum;
	private Date salDate;
	private String sCarModel;
	
	public SaleListVo(int sListNum, int salPrice, String salState, int salNum, Date salDate, String sCarModel) {
		super();
		this.sListNum = sListNum;
		this.salPrice = salPrice;
		this.salState = salState;
		this.salNum = salNum;
		this.salDate = salDate;
		this.sCarModel = sCarModel;
	}
	public SaleListVo(int sListNum, int salPrice, String salState, int salNum) {
		this.sListNum = sListNum;
		this.salPrice = salPrice;
		this.salState = salState;
		this.salNum = salNum;
	}
	public SaleListVo() {
	}
	
	public Date getSalDate() {
		return salDate;
	}
	public void setSalDate(Date salDate) {
		this.salDate = salDate;
	}
	public String getsCarModel() {
		return sCarModel;
	}
	public void setsCarModel(String sCarModel) {
		this.sCarModel = sCarModel;
	}
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
	public int getSalNum() {
		return salNum;
	}
	public void setSalNum(int salNum) {
		this.salNum = salNum;
	}
	
}

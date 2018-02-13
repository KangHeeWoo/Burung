package admin.vo;

public class SaleListVo {
	private int sListNum;
	private int salPrice;
	private String salState;
	private int salNum;
	public SaleListVo(int sListNum, int salPrice, String salState, int salNum) {
		this.sListNum = sListNum;
		this.salPrice = salPrice;
		this.salState = salState;
		this.salNum = salNum;
	}
	public SaleListVo() {
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

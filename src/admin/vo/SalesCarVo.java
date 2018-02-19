package admin.vo;

public class SalesCarVo {
	private int salNum;
	private String scarName;
	private String scarModel;
	private int salCnt;
	private int scarPrice;
	public SalesCarVo(int salNum, String scarName, String scarModel, int salCnt, int scarPrice) {
		super();
		this.salNum = salNum;
		this.scarName = scarName;
		this.scarModel = scarModel;
		this.salCnt = salCnt;
		this.scarPrice = scarPrice;
	}
	public SalesCarVo() {
		super();
	}
	public int getSalNum() {
		return salNum;
	}
	public void setSalNum(int salNum) {
		this.salNum = salNum;
	}
	public String getScarName() {
		return scarName;
	}
	public void setScarName(String scarName) {
		this.scarName = scarName;
	}
	public String getScarModel() {
		return scarModel;
	}
	public void setScarModel(String scarModel) {
		this.scarModel = scarModel;
	}
	public int getSalCnt() {
		return salCnt;
	}
	public void setSalCnt(int salCnt) {
		this.salCnt = salCnt;
	}
	public int getScarPrice() {
		return scarPrice;
	}
	public void setScarPrice(int scarPrice) {
		this.scarPrice = scarPrice;
	}
}

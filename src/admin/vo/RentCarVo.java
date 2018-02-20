package admin.vo;

public class RentCarVo {
	private int renNum;
	private String rcarName;
	private String rcarModel;
	private int timePay;
	private String state;
	
	public RentCarVo(int renNum, String rcarName, String rcarModel, int timePay, String state) {
		super();
		this.renNum = renNum;
		this.rcarName = rcarName;
		this.rcarModel = rcarModel;
		this.timePay = timePay;
		this.state = state;
	}
	public RentCarVo() {
		super();
	}
	public int getRenNum() {
		return renNum;
	}
	public void setRenNum(int renNum) {
		this.renNum = renNum;
	}
	public String getRcarName() {
		return rcarName;
	}
	public void setRcarName(String rcarName) {
		this.rcarName = rcarName;
	}
	public String getRcarModel() {
		return rcarModel;
	}
	public void setRcarModel(String rcarModel) {
		this.rcarModel = rcarModel;
	}
	public int getTimePay() {
		return timePay;
	}
	public void setTimePay(int timePay) {
		this.timePay = timePay;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}

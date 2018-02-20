package rent.vo;

public class RentVo {
	private int renNum;
	private String rCarName;
	private String rCarModel;
	private int timePay;
	private String state;
	@Override
	public String toString() {
		return "RentVo [renNum=" + renNum + ", rCarName=" + rCarName + ", rCarModel=" + rCarModel + ", timePay="
				+ timePay + ", state=" + state + "]";
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
	public String getrCarModel() {
		return rCarModel;
	}
	public void setrCarModel(String rCarModel) {
		this.rCarModel = rCarModel;
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
	public RentVo(int renNum, String rCarName, String rCarModel, int timePay, String state) {
		super();
		this.renNum = renNum;
		this.rCarName = rCarName;
		this.rCarModel = rCarModel;
		this.timePay = timePay;
		this.state = state;
	}
}
package sales.vo;

public class SalesLogVo {
	private String memId;
	private String sCarName;
	private int salPrice;
	private String salDate;
	public SalesLogVo(String memId, String sCarName, int salPrice, String salDate) {
		super();
		this.memId = memId;
		this.sCarName = sCarName;
		this.salPrice = salPrice;
		this.salDate = salDate;
	}
	@Override
	public String toString() {
		return "SalesLogVo [memId=" + memId + ", sCarName=" + sCarName + ", salPrice=" + salPrice + ", salDate="
				+ salDate + "]";
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getsCarName() {
		return sCarName;
	}
	public void setsCarName(String sCarName) {
		this.sCarName = sCarName;
	}
	public int getSalPrice() {
		return salPrice;
	}
	public void setSalPrice(int salPrice) {
		this.salPrice = salPrice;
	}
	public String getSalDate() {
		return salDate;
	}
	public void setSalDate(String salDate) {
		this.salDate = salDate;
	}
}

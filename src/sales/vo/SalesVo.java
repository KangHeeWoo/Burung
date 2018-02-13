package sales.vo;

public class SalesVo {
	private int salNum;
	private String sCarName;
	private String sCarModel;
	private int salCnt;
	private String sMainImg;
	private String sSubImg;
	
	public int getSalNum() {
		return salNum;
	}
	public void setSalNum(int salNum) {
		this.salNum = salNum;
	}
	public String getsCarName() {
		return sCarName;
	}
	public void setsCarName(String sCarName) {
		this.sCarName = sCarName;
	}
	public String getsCarModel() {
		return sCarModel;
	}
	public void setsCarModel(String sCarModel) {
		this.sCarModel = sCarModel;
	}
	public int getSalCnt() {
		return salCnt;
	}
	public void setSalCnt(int salCnt) {
		this.salCnt = salCnt;
	}
	public String getsMainImg() {
		return sMainImg;
	}
	public void setsMainImg(String sMainImg) {
		this.sMainImg = sMainImg;
	}
	public String getsSubImg() {
		return sSubImg;
	}
	public void setsSubImg(String sSubImg) {
		this.sSubImg = sSubImg;
	}
	public SalesVo(int salNum, String sCarName, String sCarModel, int salCnt, String sMainImg, String sSubImg) {
		super();
		this.salNum = salNum;
		this.sCarName = sCarName;
		this.sCarModel = sCarModel;
		this.salCnt = salCnt;
		this.sMainImg = sMainImg;
		this.sSubImg = sSubImg;
	}
	@Override
	public String toString() {
		return "SalesVo [salNum=" + salNum + ", sCarName=" + sCarName + ", sCarModel=" + sCarModel + ", salCnt="
				+ salCnt + ", sMainImg=" + sMainImg + ", sSubImg=" + sSubImg + "]";
	}
}

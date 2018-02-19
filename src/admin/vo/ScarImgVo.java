package admin.vo;

public class ScarImgVo {
	private int scarimgNum;
	private String scarimgName;
	private int salNum;
	public ScarImgVo(int scarimgNum, String scarimgName, int salNum) {
		super();
		this.scarimgNum = scarimgNum;
		this.scarimgName = scarimgName;
		this.salNum = salNum;
	}
	public ScarImgVo() {
		super();
	}
	public int getScarimgNum() {
		return scarimgNum;
	}
	public void setScarimgNum(int scarimgNum) {
		this.scarimgNum = scarimgNum;
	}
	public String getScarimgName() {
		return scarimgName;
	}
	public void setScarimgName(String scarimgName) {
		this.scarimgName = scarimgName;
	}
	public int getSalNum() {
		return salNum;
	}
	public void setSalNum(int salNum) {
		this.salNum = salNum;
	}
	
	
}

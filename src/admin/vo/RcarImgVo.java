package admin.vo;

public class RcarImgVo {
	private int rcarImgNum;
	private String rcarImgName;
	public RcarImgVo(int rcarImgNum, String rcarImgName) {
		super();
		this.rcarImgNum = rcarImgNum;
		this.rcarImgName = rcarImgName;
	}
	public RcarImgVo() {
		super();
	}
	public int getRcarImgNum() {
		return rcarImgNum;
	}
	public void setRcarImgNum(int rcarImgNum) {
		this.rcarImgNum = rcarImgNum;
	}
	public String getRcarImgName() {
		return rcarImgName;
	}
	public void setRcarImgName(String rcarImgName) {
		this.rcarImgName = rcarImgName;
	}
	
}

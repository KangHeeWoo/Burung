package board.vo;

public class RevImgVo {
	private int reCarImgNum;
	private String reCarImgName;
	private int reNum;
	public RevImgVo(int reCarImgNum, String reCarImgName, int reNum) {
		super();
		this.reCarImgNum = reCarImgNum;
		this.reCarImgName = reCarImgName;
		this.reNum = reNum;
	}
	
	public RevImgVo() {}

	public int getReCarImgNum() {
		return reCarImgNum;
	}

	public void setReCarImgNum(int reCarImgNum) {
		this.reCarImgNum = reCarImgNum;
	}

	public String getReCarImgName() {
		return reCarImgName;
	}

	public void setReCarImgName(String reCarImgName) {
		this.reCarImgName = reCarImgName;
	}

	public int getReNum() {
		return reNum;
	}

	public void setReNum(int reNum) {
		this.reNum = reNum;
	}
	
}

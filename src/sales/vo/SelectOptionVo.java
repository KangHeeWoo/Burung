package sales.vo;

public class SelectOptionVo {
	private int sOptNum;
	private int sListNum;
	private int optNum;
	public int getsOptNum() {
		return sOptNum;
	}
	public void setsOptNum(int sOptNum) {
		this.sOptNum = sOptNum;
	}
	public int getsListNum() {
		return sListNum;
	}
	public void setsListNum(int sListNum) {
		this.sListNum = sListNum;
	}
	public int getOptNum() {
		return optNum;
	}
	public void setOptNum(int optNum) {
		this.optNum = optNum;
	}
	public SelectOptionVo(int sOptNum, int sListNum, int optNum) {
		super();
		this.sOptNum = sOptNum;
		this.sListNum = sListNum;
		this.optNum = optNum;
	}
	@Override
	public String toString() {
		return "SelectOptionVo [sOptNum=" + sOptNum + ", sListNum=" + sListNum + ", optNum=" + optNum + "]";
	}
}

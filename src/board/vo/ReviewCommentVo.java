package board.vo;

import java.sql.Date;

public class ReviewCommentVo {
	private int rComNum;
	private String rComCon;
	private Date rComRegd;
	private int memNum;
	public ReviewCommentVo(int rComNum, String rComCon, Date rComRegd, int memNum) {
		super();
		this.rComNum = rComNum;
		this.rComCon = rComCon;
		this.rComRegd = rComRegd;
		this.memNum = memNum;
	}
	
	public ReviewCommentVo() {}

	public int getrComNum() {
		return rComNum;
	}

	public void setrComNum(int rComNum) {
		this.rComNum = rComNum;
	}

	public String getrComCon() {
		return rComCon;
	}

	public void setrComCon(String rComCon) {
		this.rComCon = rComCon;
	}

	public Date getrComRegd() {
		return rComRegd;
	}

	public void setrComRegd(Date rComRegd) {
		this.rComRegd = rComRegd;
	}

	public int getMemNum() {
		return memNum;
	}

	public void setMemNum(int memNum) {
		this.memNum = memNum;
	}
	
}

package board.vo;

import java.sql.Date;

public class ReviewCommentVo {
	private int rComNum;
	private String rComCon;
	private Date rComRegd;
	private int memNum;
	private int revNum;
	public ReviewCommentVo(int rComNum, String rComCon, Date rComRegd, int memNum,int revNum) {
		super();
		this.rComNum = rComNum;
		this.rComCon = rComCon;
		this.rComRegd = rComRegd;
		this.memNum = memNum;
		this.revNum=revNum;
	}
	
	public ReviewCommentVo() {}
	
	public int getrRevNum() {
		return revNum;
	}

	public void setrRevNum(int revNum) {
		this.revNum = revNum;
	}
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

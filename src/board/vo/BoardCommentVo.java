package board.vo;

import java.sql.Date;

public class BoardCommentVo {
	private int bComNum;
	private String bComCon;
	private Date bComRegd;
	private int memNum;
	private int boaNum;
	public int getbComNum() {
		return bComNum;
	}
	public void setbComNum(int bComNum) {
		this.bComNum = bComNum;
	}
	public String getbComCon() {
		return bComCon;
	}
	public void setbComCon(String bComCon) {
		this.bComCon = bComCon;
	}
	public Date getbComRegd() {
		return bComRegd;
	}
	public void setbComRegd(Date bComRegd) {
		this.bComRegd = bComRegd;
	}
	public int getMemNum() {
		return memNum;
	}
	public void setMemNum(int memNum) {
		this.memNum = memNum;
	}
	public int getBoaNum() {
		return boaNum;
	}
	public void setBoaNum(int boaNum) {
		this.boaNum = boaNum;
	}
	public BoardCommentVo(int bComNum, String bComCon, Date bComRegd, int memNum, int boaNum) {
		super();
		this.bComNum = bComNum;
		this.bComCon = bComCon;
		this.bComRegd = bComRegd;
		this.memNum = memNum;
		this.boaNum = boaNum;
	}
	public BoardCommentVo() {}

}

package board.vo;

import java.sql.Date;

public class BoardVo {
	private int boaNum;
	private String boaTitle;
	private String boaContent;
	private int boaHit;
	private Date boaRegd;
	private int memNum;
	public BoardVo(int boaNum, String boaTitle, String boaContent, int boaHit, Date boaRegd,
			int memNum) {
		super();
		this.boaNum = boaNum;
		this.boaTitle = boaTitle;
		this.boaContent = boaContent;

		this.boaHit = boaHit;
		this.boaRegd = boaRegd;
		this.memNum = memNum;
	}
	public BoardVo() {}
	public int getBoaNum() {
		return boaNum;
	}
	public void setBoaNum(int boaNum) {
		this.boaNum = boaNum;
	}
	public String getBoaTitle() {
		return boaTitle;
	}
	public void setBoaTitle(String boaTitle) {
		this.boaTitle = boaTitle;
	}
	public String getBoaContent() {
		return boaContent;
	}
	public void setBoaContent(String boaContent) {
		this.boaContent = boaContent;
	}

	public int getBoaHit() {
		return boaHit;
	}
	public void setBoaHit(int boaHit) {
		this.boaHit = boaHit;
	}
	public Date getBoaRegd() {
		return boaRegd;
	}
	public void setBoaRegd(Date boaRegd) {
		this.boaRegd = boaRegd;
	}
	public int getMemNum() {
		return memNum;
	}
	public void setMemNum(int memNum) {
		this.memNum = memNum;
	}
	
}

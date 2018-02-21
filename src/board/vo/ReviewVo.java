package board.vo;

import java.sql.Date;

public class ReviewVo {
	private int revNum;
	private String revTitle;
	private String revContent;
	private int revScore;
	private int revHit;
	private Date revRegd;
	private int memNum;
	private String memId;
	private String carname;
	
	public ReviewVo(int revNum, String revTitle, String revContent, int revScore, int revHit, Date revRegd,
			int memNum,String carname) {
		super();
		this.revNum = revNum;
		this.revTitle = revTitle;
		this.revContent = revContent;
		this.revScore = revScore;
		this.revHit = revHit;
		this.revRegd = revRegd;
		this.memNum = memNum;
		this.carname=carname;
	}
	public ReviewVo(int revNum, String revTitle, String revContent, int revScore, int revHit, Date revRegd,
			int memNum,String memId,String carname) {
		super();
		this.revNum = revNum;
		this.revTitle = revTitle;
		this.revContent = revContent;
		this.revScore = revScore;
		this.revHit = revHit;
		this.revRegd = revRegd;
		this.memNum = memNum;
		this.memId=memId;
		this.carname=carname;
	}
	
	@Override
	public String toString() {
		return "ReviewVo [revNum=" + revNum + ", revTitle=" + revTitle + ", revContent=" + revContent + ", revScore="
				+ revScore + ", revHit=" + revHit + ", revRegd=" + revRegd + ", memNum=" + memNum + ", memId=" + memId
				+ ", carname=" + carname + "]";
	}
	public ReviewVo() {}
	
	public String getMemId() {
		return memId;
	}
	public String getCarname() {
		return carname;
	}
	public void setCarname(String carname) {
		this.carname = carname;
	}
	public int getRevNum() {
		return revNum;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}

	public void setRevNum(int revNum) {
		this.revNum = revNum;
	}

	public String getRevTitle() {
		return revTitle;
	}

	public void setRevTitle(String revTitle) {
		this.revTitle = revTitle;
	}

	public String getRevContent() {
		return revContent;
	}

	public void setRevContent(String revContent) {
		this.revContent = revContent;
	}

	public int getRevScore() {
		return revScore;
	}

	public void setRevScore(int revScore) {
		this.revScore = revScore;
	}

	public int getRevHit() {
		return revHit;
	}

	public void setRevHit(int revHit) {
		this.revHit = revHit;
	}

	public Date getRevRegd() {
		return revRegd;
	}

	public void setRevRegd(Date revRegd) {
		this.revRegd = revRegd;
	}

	public int getMemNum() {
		return memNum;
	}

	public void setMemNum(int memNum) {
		this.memNum = memNum;
	}
	
	
}

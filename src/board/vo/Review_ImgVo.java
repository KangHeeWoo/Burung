package board.vo;

import java.sql.Date;

public class Review_ImgVo {
	private int revNum;
	private String revContent;
	private String revTitle;
	private int revScore;
	private Date revRegd;
	private String memId;
	private String carName;
	private int revHit;
	private int memNum;
	
	public Review_ImgVo() {}
	
	public Review_ImgVo(int revNum, String revContent, String revTitle, int revScore, Date revRegd, String memId,
			String carName, int revHit, int memNum) {
		super();
		this.revNum = revNum;
		this.revContent = revContent;
		this.revTitle = revTitle;
		this.revScore = revScore;
		this.revRegd = revRegd;
		this.memId = memId;
		this.carName = carName;
		this.revHit = revHit;
		this.memNum = memNum;
	}
	public int getRevNum() {
		return revNum;
	}
	public void setRevNum(int revNum) {
		this.revNum = revNum;
	}
	public String getRevContent() {
		return revContent;
	}
	public void setRevContent(String revContent) {
		this.revContent = revContent;
	}
	public String getRevTitle() {
		return revTitle;
	}
	public void setRevTitle(String revTitle) {
		this.revTitle = revTitle;
	}
	public int getRevScore() {
		return revScore;
	}
	public void setRevScore(int revScore) {
		this.revScore = revScore;
	}
	public Date getRevRegd() {
		return revRegd;
	}
	public void setRevRegd(Date revRegd) {
		this.revRegd = revRegd;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	public int getRevHit() {
		return revHit;
	}
	public void setRevHit(int revHit) {
		this.revHit = revHit;
	}
	public int getMemNum() {
		return memNum;
	}
	public void setMemNum(int memNum) {
		this.memNum = memNum;
	}
	@Override
	public String toString() {
		return "Review_ImgVo [revNum=" + revNum + ", revContent=" + revContent + ", revTitle=" + revTitle
				+ ", revScore=" + revScore + ", revRegd=" + revRegd + ", memId=" + memId + ", carName=" + carName
				+ ", revHit=" + revHit + ", memNum=" + memNum + "]";
	}
	
	

}

package admin.vo;

import java.sql.Date;

public class NoticeVo {
	private int notNum;
	private String notTitle;
	private String notContent;
	private int notHit;
	private Date notRegd;
	private int memNum;
	private String memName;
	
	public NoticeVo(int notNum, String notTitle, String notContent, int notHit, Date notRegd, int memNum,
			String memName) {
		super();
		this.notNum = notNum;
		this.notTitle = notTitle;
		this.notContent = notContent;
		this.notHit = notHit;
		this.notRegd = notRegd;
		this.memNum = memNum;
		this.memName = memName;
	}
	public NoticeVo(int notNum, String notTitle, String notContent, int notHit, Date notRegd, int memNum) {
		super();
		this.notNum = notNum;
		this.notTitle = notTitle;
		this.notContent = notContent;
		this.notHit = notHit;
		this.notRegd = notRegd;
		this.memNum = memNum;
	}
	public NoticeVo() {
		super();
	}
	public int getNotNum() {
		return notNum;
	}
	public void setNotNum(int notNum) {
		this.notNum = notNum;
	}
	public String getNotTitle() {
		return notTitle;
	}
	public void setNotTile(String notTitle) {
		this.notTitle = notTitle;
	}
	public String getNotContent() {
		return notContent;
	}
	public void setNotContent(String notContent) {
		this.notContent = notContent;
	}
	public int getNotHit() {
		return notHit;
	}
	public void setNotHit(int notHit) {
		this.notHit = notHit;
	}
	public Date getNotRegd() {
		return notRegd;
	}
	public void setNotRegd(Date notRegd) {
		this.notRegd = notRegd;
	}
	public int getMemNum() {
		return memNum;
	}
	public void setMemNum(int memNum) {
		this.memNum = memNum;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	
}

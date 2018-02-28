package board.vo;

import java.sql.Date;

public class reviewBatch {
	private int revnum;
	private String revtitle;
	private String revcontent;
	private int revscore;
	private Date revregd;
	private String carname;
	private String memid;
	public int getRevnum() {
		return revnum;
	}
	public void setRevnum(int revnum) {
		this.revnum = revnum;
	}
	public String getRevtitle() {
		return revtitle;
	}
	public void setRevtitle(String revtitle) {
		this.revtitle = revtitle;
	}
	public String getRevcontent() {
		return revcontent;
	}
	public void setRevcontent(String revcontent) {
		this.revcontent = revcontent;
	}
	public int getRevscore() {
		return revscore;
	}
	public void setRevscore(int revscore) {
		this.revscore = revscore;
	}
	public Date getRevregd() {
		return revregd;
	}
	public void setRevregd(Date revregd) {
		this.revregd = revregd;
	}
	public String getCarname() {
		return carname;
	}
	public void setCarname(String carname) {
		this.carname = carname;
	}
	public String getMemid() {
		return memid;
	}
	public void setMemid(String memid) {
		this.memid = memid;
	}
	public reviewBatch(int revnum, String revtitle, String revcontent, int revscore, Date revregd, String carname,
			String memid) {
		super();
		this.revnum = revnum;
		this.revtitle = revtitle;
		this.revcontent = revcontent;
		this.revscore = revscore;
		this.revregd = revregd;
		this.carname = carname;
		this.memid = memid;
	}
	public reviewBatch() {
		super();
	}
	@Override
	public String toString() {
		return "등록된 글 [ 글번호:" + revnum + ", 제목:" + revtitle + ", 내용:" + revcontent + ", 평점:"
				+ revscore + ", 등록일:" + revregd + ", 차종:" + carname + ", 회원아이디:" + memid + "]";
	}
	
	
}

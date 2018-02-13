package board.vo;

import java.sql.Date;

public class BoardMemVo {
	private int boanum;
	private String boatitle;
	private int boahit;
	private Date boaRegd;
	private String memId;
	
	public int getBoanum() {
		return boanum;
	}
	public void setBoanum(int boanum) {
		this.boanum=boanum;
	}
	public String getBoatitle() {
		return boatitle;
	}
	public void setBoatitle(String boatitle) {
		this.boatitle = boatitle;
	}
	public int getBoahit() {
		return boahit;
	}
	public void setBoahit(int boahit) {
		this.boahit = boahit;
	}
	public Date getBoaRegd() {
		return boaRegd;
	}
	public void setBoaRegd(Date boaRegd) {
		this.boaRegd = boaRegd;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public BoardMemVo(int bonum,String boatitle, int boahit, Date boaRegd, String memId) {
		super();
		this.boanum=boanum;
		this.boatitle = boatitle;
		this.boahit = boahit;
		this.boaRegd = boaRegd;
		this.memId = memId;
	}
	public BoardMemVo() {
		super();
	}
	
}

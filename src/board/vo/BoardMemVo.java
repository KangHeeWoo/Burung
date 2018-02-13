package board.vo;

import java.sql.Date;

public class BoardMemVo {
	private String boatitle;
	private int boahit;
	private Date boaRegd;
	private String memId;
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
	public BoardMemVo(String boatitle, int boahit, Date boaRegd, String memId) {
		super();
		this.boatitle = boatitle;
		this.boahit = boahit;
		this.boaRegd = boaRegd;
		this.memId = memId;
	}
	public BoardMemVo() {
		super();
	}
	
}

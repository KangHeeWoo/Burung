package board.vo;

import java.sql.Date;

public class BoardMemVo {
	private int boanum;
	private String boatitle;
	private int boahit;
	private Date boaRegd;
	private String memid;
	
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
	public String getMemid() {
		return memid;
	}
	public void setMemid(String memid) {
		this.memid = memid;
	}
	
	public BoardMemVo(int boanum, String boatitle, int boahit, Date boaRegd, String memid) {
		super();
		this.boanum = boanum;
		this.boatitle = boatitle;
		this.boahit = boahit;
		this.boaRegd = boaRegd;
		this.memid = memid;
	}
	public BoardMemVo() {
		super();
	}
	@Override
	public String toString() {
		return "BoardMemVo [boanum=" + boanum + ", boatitle=" + boatitle + ", boahit=" + boahit + ", boaRegd=" + boaRegd
				+ ", memid=" + memid + "]";
	}
	
}

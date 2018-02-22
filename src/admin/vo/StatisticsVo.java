package admin.vo;

import java.sql.Date;

public class StatisticsVo {
	private String rcarModel;
	private String rcarName;
	private int rlistNum;
	private Date rendDate;
	private int cnt;
	public StatisticsVo(String rcarModel, String rcarName, int rlistNum, Date rendDate) {
		super();
		this.rcarModel = rcarModel;
		this.rcarName = rcarName;
		this.rlistNum = rlistNum;
		this.rendDate = rendDate;
	}
	
	public StatisticsVo(String rcarModel, String rcarName, int rlistNum, Date rendDate, int cnt) {
		super();
		this.rcarModel = rcarModel;
		this.rcarName = rcarName;
		this.rlistNum = rlistNum;
		this.rendDate = rendDate;
		this.cnt = cnt;
	}

	public StatisticsVo() {
		super();
	}
	
	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public String getRcarModel() {
		return rcarModel;
	}
	public void setRcarModel(String rcarModel) {
		this.rcarModel = rcarModel;
	}
	public String getRcarName() {
		return rcarName;
	}
	public void setRcarName(String rcarName) {
		this.rcarName = rcarName;
	}
	public int getRlistNum() {
		return rlistNum;
	}
	public void setRlistNum(int rlistNum) {
		this.rlistNum = rlistNum;
	}
	public Date getRendDate() {
		return rendDate;
	}
	public void setRendDate(Date rendDate) {
		this.rendDate = rendDate;
	}
	
	
}

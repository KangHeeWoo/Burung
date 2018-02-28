package rent.batch;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import admin.dao.RentListDao;
import admin.vo.RentListVo;

public class RentBatch {	
	String path = RentBatch.class.getResource("/").getPath();
	String path1 = path.replaceAll("/WEB-INF/classes/", "");
	Calendar cal = Calendar.getInstance();
	String year = String.valueOf(cal.get(Calendar.YEAR));
	String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
	String day = String.valueOf(cal.get(Calendar.DATE));
	String sysdate = year + "/" + month + "/" + day;

	public RentBatch() {
		System.out.println("rent batch 호출");
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				System.out.println("rent batch");
				try {// 파일생성
					PrintWriter pw = new PrintWriter(path1 + "/log/rent_log_" + year + month + day + ".txt");
					RentListDao dao = new RentListDao();
					ArrayList<RentListVo> list = dao.rentlist(sysdate);
					for (int i = 0; i < list.size(); i++) {
						RentListVo vo = list.get(i);
						int rlistnum = vo.getrListNum();
						int memnum = vo.getRenNum();
						Date rstartdate = vo.getrStartDate();
						Date renddate = vo.getrEndDate();
						int rtotprice = vo.getrTotPrice();
						String rcarname = vo.getrCarName();
						pw.println(rlistnum + " " + memnum + " " + rstartdate + " " + renddate + " " + rtotprice + " "
								+ rcarname);
					}
					pw.close();
				} catch (IOException ie) {
					System.out.println(ie.getMessage());
				}
			}
		};
		Timer timer = new Timer(true);
		cal.set(2018, 1, 25, 23, 59, 0);
		timer.scheduleAtFixedRate(task, new Date(cal.getTimeInMillis()), 1000 * 60 * 60 * 24);
	}

}

package members.batch;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import members.dao.MembersDao;
import members.vo.MembersInsertVo;
import sales.batch.SalesBatch;

public class MembersBatch {
	public MembersBatch() {
		System.out.println("members batch 호출");
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				// 배치에서 실행될 메소드 호출
				System.out.println("members batch");
				membersInsertLog();
			}
		};

		Timer timer = new Timer(true);
		Calendar cal = Calendar.getInstance();
		cal.set(2018, 1, 25, 23, 59, 0);
		timer.scheduleAtFixedRate(task, new Date(cal.getTimeInMillis()), 1000 * 60 * 60 * 24);
	}

	public void membersInsertLog() {
		String path = SalesBatch.class.getResource("/").getPath();
		path = path.replaceAll("/WEB-INF/classes/", "");

		Calendar cal = Calendar.getInstance();
		int y = cal.get(Calendar.YEAR);
		int m = cal.get(Calendar.MONTH) + 1;
		int d = cal.get(Calendar.DATE);

		String date = "";

		date += y;
		if (m < 10) {
			date += "0";
		}
		date += m;
		date += d;

		PrintWriter pw = null;

		try {
			pw = new PrintWriter(path + "/log/members_log_" + date + ".txt");

			MembersDao dao = MembersDao.getInstance();
			ArrayList<MembersInsertVo> list = dao.memberList(date);
			
			for(MembersInsertVo vo : list) {
				pw.println(vo);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			pw.close();
		}
	}
}

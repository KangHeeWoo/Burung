package sales.batch;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import sales.dao.SalesDao;
import sales.vo.SalesLogVo;

public class SalesBatch {
	public SalesBatch() {
		System.out.println("sales batch 호출");
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				// 배치에서 실행될 메소드 호출
				createSalesLog();
				System.out.println("sales batch");
			}
		};

		Timer timer = new Timer(true);
		Calendar cal = Calendar.getInstance();
		cal.set(2018, 1, 27, 23, 59, 0);
		timer.scheduleAtFixedRate(task, new Date(cal.getTimeInMillis()), 1000 * 60 * 60 * 24);
	}

	// 배치에서 실행될 메소드 정의
	public void createSalesLog() {
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
			pw = new PrintWriter(path + "/log/sales_log" + date + ".txt");

			SalesDao dao = SalesDao.getInstance();
			ArrayList<SalesLogVo> list = dao.logList(date);

			for (SalesLogVo vo : list) {
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

package review.batch;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import board.dao.ReviewDao;
import board.vo.reviewBatch;

public class ReviewBatch {
	public ReviewBatch() {
		System.out.println("review batch ȣ��");
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				System.out.println("review batch");
				String path = ReviewBatch.class.getResource("/").getPath();
				path = path.replaceAll("/WEB-INF/classes/", "");

				// ��ġ���� ����� �޼ҵ� ȣ��
				Calendar cal = Calendar.getInstance();

				int year = cal.get(Calendar.YEAR);
				int month = cal.get(Calendar.MONTH) + 1;
				String month1 = "";
				if (month < 10) {
					month1 = "0" + String.valueOf(month);
				}
				int date = cal.get(Calendar.DATE);
				String date1 = String.valueOf(year) + month1 + String.valueOf(date);

				try {
					// ���ϸ�
					BufferedWriter bw = new BufferedWriter(new FileWriter(path + "/log/review_log_" + date1 + ".txt"));
					// dao
					ReviewDao dao = ReviewDao.getInstance();
					ArrayList<reviewBatch> reviewLog = dao.reviewLog(date1);

					// String���� �ٲٱ�
					for (reviewBatch vo : reviewLog) {
						bw.write(vo.toString());
						bw.newLine();
					}
					bw.close();

				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		};

		Timer timer = new Timer(true);
		Calendar cal = Calendar.getInstance();
		cal.set(2018, 1, 25, 23, 59, 0);
		timer.scheduleAtFixedRate(task, new Date(cal.getTimeInMillis()), 1000 * 60 * 60 * 24);
	}
}
package board.batch;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import board.dao.BoardDao;
import board.vo.BoardMemVo;

public class BoardBatch {
	public BoardBatch() {
		System.out.println("board batch 호출");
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				System.out.println("board batch");
				String path = BoardBatch.class.getResource("/").getPath();
				path = path.replaceAll("/WEB-INF/classes/", "");
				// 배치에서 실행될 메소드 호출
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
					BufferedWriter bw = new BufferedWriter(new FileWriter(path + "/log/board_log_" + date1 + ".txt"));

					BoardDao dao = BoardDao.getInstance();
					ArrayList<BoardMemVo> list = dao.boardBatch(date1);

					for (int i = 0; i < list.size(); i++) {
						bw.write(list.toString());
						bw.newLine();
					}
					bw.close();

				} catch (IOException ie) {
					System.out.println(ie.getMessage());
				}
			}
		};

		Timer timer = new Timer(true);
		Calendar cal = Calendar.getInstance();
		cal.set(2018, 1, 27, 23, 59, 0);
		timer.scheduleAtFixedRate(task, new Date(cal.getTimeInMillis()), 1000 * 60 * 60 * 24);
	}
	// 배치에서 실행될 메소드 정의
}

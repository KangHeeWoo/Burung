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
	      TimerTask task = new TimerTask() {   
	          @Override
	          public void run() {
	        	  String path = BoardBatch.class.getResource("/").getPath();
	              path = path.replaceAll("/WEB-INF/classes/", "");
	             // 배치에서 실행될 메소드 호출
	              Calendar cal = Calendar.getInstance();
	               
	               int year = cal.get ( cal.YEAR );
	               int month = cal.get ( cal.MONTH ) + 1 ;
	               String month1="";
	               if(month<10) {
	                  month1="0"+String.valueOf(month);
	               }
	               int date = cal.get ( cal.DATE ) ;
	               String date1=String.valueOf(year)+month1+String.valueOf(date);
	               
	        	  try {
	        		  BufferedWriter bw=new BufferedWriter(new FileWriter(path+"/board_log_"+date1+".txt"));
	    
	        		  BoardDao dao = BoardDao.getInstance();
	        		  ArrayList<BoardMemVo> list = dao.boardBatch(date1);
	        		  
	        		  for(int i=0;i<list.size();i++) { 
	        			  bw.write(list.toString());
	        		  }
	        		  bw.close();
	        		  
	        	  }catch(IOException ie) {
	        		  System.out.println(ie.getMessage());
	        	  }
	          }
	       };
	       
	       Timer timer = new Timer(true);
	       Calendar cal = Calendar.getInstance();
	       cal.set(2018,2,27,14,0,0);
	       //timer.scheduleAtFixedRate(task, new Date(cal.getTimeInMillis()), 1000 * 60 * 60 * 24);
	       timer.scheduleAtFixedRate(task, new Date(cal.getTimeInMillis()), 1000 * 60);
	    }
	    //배치에서 실행될 메소드 정의
	}

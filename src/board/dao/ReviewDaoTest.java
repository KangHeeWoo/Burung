package board.dao;
import  static org.junit.Assert.*;  //Ŭ���� �̸����� ��밡��

import java.util.ArrayList;

import org.junit.Test;

import board.vo.ReviewVo;

public class ReviewDaoTest {
	public ReviewDaoTest() {}
	
	@Test
	public void list() {

		Review_Dao dao=new Review_Dao();
		ArrayList<String> vo=dao.imglist(1);
		assertNotNull(vo);
		
	}
}

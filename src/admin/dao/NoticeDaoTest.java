package admin.dao;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Test;

import admin.vo.MemberVo;
import admin.vo.NoticeVo;


public class NoticeDaoTest {
	public NoticeDaoTest() {}
	
	@Test
	public void list() {
		NoticeDaoJUnit dao=new NoticeDaoJUnit();
		ArrayList<NoticeVo> list= dao.list(1, 5);
		assertNotNull(list);
	}
}

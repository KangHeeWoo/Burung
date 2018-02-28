package admin.dao;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Test;

import admin.vo.MemberVo;


public class NoticeDaoTest {
	public NoticeDaoTest() {}
	
	@Test
	public void list() {
		MemberDao dao=new MemberDao();
		ArrayList<MemberVo> list=dao.memDetail(1);
		assertNotNull(list);
	}
}

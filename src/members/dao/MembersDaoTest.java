package members.dao;

import static org.junit.Assert.*;
import org.junit.Test;

import members.vo.MembersVo;

public class MembersDaoTest {
	public MembersDaoTest() {}
	
	@Test
	public void list() {
		MembersDaoJUnit dao = new MembersDaoJUnit();
		MembersVo vo = dao.list("test1");
		assertNotNull(vo);
		
	}
}

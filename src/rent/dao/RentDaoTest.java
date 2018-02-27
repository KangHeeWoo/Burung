package rent.dao;

import static org.junit.Assert.*;
import org.junit.Test;

import rent.vo.RentListVo;

public class RentDaoTest {

	public RentDaoTest() {}
	
	@Test
	public void insert() {
		RentDao dao = RentDao.getInstance();
		int n = dao.addRentList(new RentListVo(0, null, null, 0, null, 1, 1));
		assertEquals(n, 1);
	}
	
	@Test
	public void num() {}
	
	@Test
	public void car() {
		
	}
	
	@Test
	public void list() {}
}

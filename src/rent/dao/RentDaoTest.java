package rent.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class RentDaoTest {

	public RentDaoTest() {}
	
	@Test
	public void list() {
		RentDaoJUnit dao = new RentDaoJUnit();
		ArrayList<String> list = dao.rentList("2018-02-28 09:00", "2018-03-01 09:00", "Panamera");
		assertNotNull(list);
	}
}

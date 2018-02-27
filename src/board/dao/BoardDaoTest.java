package board.dao;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
import board.vo.BoardMemVo;

public class BoardDaoTest {
	public BoardDaoTest() {}
	
	@Test
	public void listAll() {
		Board_Dao dao = new Board_Dao();
		ArrayList<BoardMemVo> vo = dao.listAll(1, 1, "1", null);
		
		assertNotNull(vo);
	}
}

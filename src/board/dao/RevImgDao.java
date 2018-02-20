package board.dao;

public class RevImgDao {
	private static RevImgDao instance = new RevImgDao();

	private RevImgDao() {
	}

	public static RevImgDao getInstance() {
		return instance;
	}
}

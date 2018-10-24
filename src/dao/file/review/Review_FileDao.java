package dao.file.review;

import java.util.List;

import dto.board.Review_Board;
import dto.file.Review_Filetb;

public interface Review_FileDao {
	public void insertFiletb(Review_Filetb notice_filetb);

	// 삭제
	public int deleteFiletbByfileno(int fileno,Review_Board board);
	public void deleteFiletbByboardno(Review_Board board);

	public int selectFileno();

	// 리스트
	public List<Review_Filetb> selectFiletbAll();

	public List<Review_Filetb> selectFiletb(Review_Board board);

	public Review_Filetb selectByFileno(int fileno);
}

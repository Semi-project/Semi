package dao.file.notice;

import java.util.List;

import dto.board.Notice_Board;
import dto.file.Notice_Filetb;

public interface Notice_FileDao {

	// 삽입z
	public void insertFiletb(Notice_Filetb notice_filetb);

	// 업데이트
//	public void updateFiletbByfileno(Notice_filetb notice_filetb);

	// 삭제
	public int deleteFiletbByfileno(int fileno,Notice_Board board);
  
	public void deleteFiletbByboardno(Notice_Board board);

	public int selectFileno();

	// 리스트
	public List<Notice_Filetb> selectFiletbAll();

	public List<Notice_Filetb> selectFiletb(Notice_Board board);

	public Notice_Filetb selectByFileno(int fileno);
}
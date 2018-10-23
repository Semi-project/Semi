package dao.file;

import java.util.List;

import dto.file.Notice_Filetb;

public interface Notice_FileDao {

	// 삽입z
	public void insertFiletb(Notice_Filetb notice_filetb);

	// 업데이트
//	public void updateFiletbByfileno(Notice_filetb notice_filetb);

	// 삭제
	public void deleteFiletbByfileno(Notice_Filetb notice_filetb);

	public int selectFileno();

	// 리스트
	public List<Notice_Filetb> selectFiletbAll();
}
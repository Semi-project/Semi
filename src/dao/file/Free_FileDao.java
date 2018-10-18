package dao.file;

import java.util.List;

import dto.file.Free_Filetb;

public interface Free_FileDao {

	//삽입
	public void insertFiletb(Free_Filetb free_filetb);
		
	//업데이트
	public void updateFiletbByfileno(Free_Filetb free_filetb);

	//삭제
	public void deleteFiletbByfileno(Free_Filetb free_filetb);
		
	//리스트
	public List<Free_Filetb> selectFiletbAll();
}

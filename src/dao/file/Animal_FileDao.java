package dao.file;

import java.util.List;

import dto.file.Animal_Filetb;

public interface Animal_FileDao {

	//삽입
	public void insertFiletb(Animal_Filetb animal_filetb);
	
	//삭제
	public void deleteFiletbByAnimalCode(Animal_Filetb animal_filetb);
	
	//리스트
	public List<Animal_Filetb> selectFiletbByBoardno(Animal_Filetb animal_filetb);
}


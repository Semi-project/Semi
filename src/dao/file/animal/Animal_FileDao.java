package dao.file.animal;

import java.util.List;

import dto.file.Animal_Filetb;

public interface Animal_FileDao {

	//삽입
	public void insertFiletb(Animal_Filetb animal_filetb);
	
	//삭제
	public void deleteFiletbByAnimalCode(Animal_Filetb animal_filetb);
	
	// 해당 동물 사진 가져오기
	public List<Animal_Filetb> selectFiletbByAnimalCode(Animal_Filetb animal_filetb);
	
	// file sequence 미리 가져오기
	public int selectFileno();
	
	// 리스트에서 삭제
	public void deleteAnimalListFile(String codes);
}


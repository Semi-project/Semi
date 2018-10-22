package service.animal;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.animal.Animal;
import dto.file.Animal_Filetb;

public interface AnimalService {

	// 입양보내기 승인된 동물들
	public List<Animal> selectAnimal();
		
	// 입양보내기 승인되지 않은 동물들
	public List<Animal> selectAnimalnotAutho();
	
	// 동물 정보 전체 조회
	public Animal selectAnimalByanimal_Code(Animal animal);
		
	// 동물 정보 삽입
	public void insertAnimal(Animal animal, Animal_Filetb animal_file);
		
	// 동물 정보 수정
	public void updateAnimalByAnimal_Code(Animal animal);
		
	// 동물 정보 삭제
	public void deleteAnimalByAnimal_Code(Animal animal, Animal_Filetb animal_filetb);
	
	// 파라미터 가져오기
	public Animal getParam(HttpServletRequest req, HttpServletResponse resp);
}

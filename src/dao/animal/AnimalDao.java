package dao.animal;

import java.util.List;

import dto.animal.Animal;

public interface AnimalDao {
	
	//동물 정보 전체 조회
	public List<Animal> selectAnimal();
	
	//동물 정보 전체 조회
	public Animal selectAnimalByanimal_Code(Animal animal);
	
	//동물 정보 삽입
	public void insertAnimal(Animal animal);
	
	//동물 정보 수정
	public void updateAnimalByAnimal_Code(Animal animal);
	
	//동물 정보 삭제
	public void deleteAnimalByAnimal_Code(Animal animal);
	
	// 입양 보내기 승인
	public void updateStatus(Animal animal);
}

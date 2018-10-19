package dao.animal;

import java.util.List;

import dto.animal.Animal;

public interface AnimalDao {

	// 새로운 게시글 넘버 
	public int selectSeqNextval();
	
	// 입양보내기 승인된 동물들
	public List<Animal> selectAnimal();

	// 입양보내기 승인되지 않은 동물들
	public List<Animal> selectAnimalnotAutho();
	
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

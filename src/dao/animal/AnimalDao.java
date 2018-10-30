package dao.animal;

import java.util.List;

import dto.animal.Animal;
import util.Paging;

public interface AnimalDao {

	// 새로운 동물 넘버 
	public int selectSeqNextval();
	
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
	
	// 모든 동물 수 조회
	public int selectCntAll();
	
	// 관리자용 동물 리스트
	public List selectPagingListAdmin(Paging paging);
	
	// 입양 보내기 수락된 동물 수 조회
	public int selectCntAcpt();
	
	// 회원용 동물 리스트
	public List selectPagingListUser(Paging paging);
	
	// 주희센세 코드
	public int animal_code(Animal animal);
	
	// 리스트에서 삭제
	public void deleteAnimalList(String codes);
	
	// 리스트에서 수락
	public void acceptAnimalList(String codes);

}

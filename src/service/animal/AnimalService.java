package service.animal;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.animal.Animal;
import dto.animal.Species;
import dto.file.Animal_Filetb;
import util.Paging;

public interface AnimalService {
	
	// view에 쓸 getParam
	public Animal getParam(HttpServletRequest req, HttpServletResponse resp);
	
	// 동물 정보 전체 조회
	public Animal selectAnimalByanimal_Code(Animal animal);
		
	// 동물 정보 삽입
	public void insertAnimal(Animal animal, Animal_Filetb animal_file);
		
	// 동물 정보 수정
	public void updateAnimalByAnimal_Code(Animal animal);
		
	// 동물 정보 삭제
	public void deleteAnimalByAnimal_Code(Animal animal, Animal_Filetb animal_filetb);
	
	// 입양보내기 입력
	public void write(HttpServletRequest req, HttpServletResponse resp);
	
	// 품종 가져오기
	public List getSpecies();
	
	// 페이징 필요 메소드
	
	// 동물 전체 수 조회
	public int getTotalCount();
	
	//요청파라미터에서 curPage 반환
	public int getCurPage(HttpServletRequest req);
	
	// 관리자 페이징
	public List getPagingListAdmin(Paging paging);
	
	// 수락된 동물 수 조회
	public int getCountAcpt();
	
	// 일반 유저 페이징
	public List selectPagingListUser(Paging paging);
	
	
	// 리스트에서 삭제
	public void animalListDelete(String codes);
	
	// 리스트에서 수락
	public void animalListAccept(String codes);
	
}

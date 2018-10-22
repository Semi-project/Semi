package service.animal;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.animal.Animal;
import dto.file.Animal_Filetb;
import util.Paging;

public interface AnimalService {
	
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
	
	// 페이징 필요 메소드
	
	// 동물 전체 수 조회
	public int getTotalCount();
	
	//요청파라미터에서 curPage 반환
	public int getCurPage(HttpServletRequest req);
	
	// 허가된 동물 페이징 조회
	public List getPagingListAuth(Paging paging);
	
	// 허가되지 않은 동물 페이징 조회
	public List getPagingListUnauth(Paging paging);
	
	
}

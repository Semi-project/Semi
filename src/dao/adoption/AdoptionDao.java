package dao.adoption;

import java.util.List;

import dto.adoption.Adoption;
import util.Paging;

public interface AdoptionDao {
	
	//일반 전체 리스트 출력
	public List<Adoption> selectAll();

	//일반 전체 리스트 페이징 처리 후 출력
	public List<Adoption> selectPagingList(Paging paging);

	//전체 리스트 갯수 
	public int selectAdoptionCntAll();

	//동물 코드로 검색 
	public Adoption selectAdoptionByAnimal_Code(Adoption adoption);

	//유저 아이디로 검색 
	public Adoption selectAdoptionByUserId(Adoption adoption);

	//유저 아이디로 삭제 
	public void deleteAdoptionByUserId(Adoption adoption);

	//동물 코드로 삭제 
	public void deleteAdoptionByAnimal_Code(Adoption adoption);

	// 유저아이디로 수정
	public void updateAdoptionByUserId(Adoption adoption);
	
	// 입양 신청하기
	public void insertAdoption(Adoption adoption);
	
	//시퀀스 넘버 
	public int selectSeqNextval();
}

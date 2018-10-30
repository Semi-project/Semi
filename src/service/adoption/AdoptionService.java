package service.adoption;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.adoption.Adoption;
import dto.file.Animal_Filetb;
import util.Paging;

public interface AdoptionService {


	// 입양 신청서에 파라미터 전달
	public Adoption getParam(HttpServletRequest req, HttpServletResponse resp);

	// 요청파라미터에서 curPage 반환
	public int getCurPage(HttpServletRequest req);
	public String getSearch(HttpServletRequest req);

	public int getTotalCount(String search) ;
	// 전체 게시글 수 조회
	public int getTotalCount();

	// 게시글 전체 조회
	public List<Adoption> getList();

	

	// 입양신청서 리스트 출력 (페이징)
	public List<Adoption> getPagingList(Paging paging);
	
	
	public Adoption view(Adoption adoptionView);
	
	// 글 작성자 닉네임 얻기
	public String getNick(Adoption adoption);

	// 첨부파일 얻기
	public Animal_Filetb viewFile(Adoption adoption);

	// 게시글 수정
	public void update(HttpServletRequest req);
	public void updateList(String names);
	// 삭제
	public void delete(Adoption adoption);
	public void deleteAdoptionList(String names);
	public void write(HttpServletRequest req, HttpServletResponse resp);

	public int getStatus(Adoption adoption);
}

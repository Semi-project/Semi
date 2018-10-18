package service.adoption;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.adoption.Adoption;
import util.Paging;

public interface AdoptionService {
	// 입양신청서 리스트 출력 (페이징)
	public List<Adoption> getPagingList(Paging paging);

	// 입양 신청서에 파라미터 전달
	public Adoption getParam(HttpServletRequest req, HttpServletResponse resp);

	public void write(Adoption adoption);

	public void delete(Adoption adoption);

	public void update(HttpServletRequest req);
	
}

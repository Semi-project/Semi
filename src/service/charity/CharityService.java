package service.charity;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.animal.Animal;
import dto.charity.Charity;

public interface CharityService {

	// 해당 유저 전체 후원 조회
	public List<Charity> selectAllByUserId(Charity charity);
	
	// 해당 후원 내역 보기
	public void selectByCharityCode(Charity charity);
	
	// 후원하기
	public void insertCharity(Charity charity);
	
	// 후원 취소 하기
	public void deleteCharityByCharityCode(Charity charity);
	
	// Param 가져오기
	public Charity getParam(HttpServletRequest req, HttpServletResponse resp);
	
}

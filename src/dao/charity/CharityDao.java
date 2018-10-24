package dao.charity;

import java.util.List;

import dto.charity.Charity;
import dto.member.Member;

public interface CharityDao {
	
	// 후원 정보 삽입
	public void insert(Charity charity);
	
	// 후원정보 userId 로 조회
	public List<Charity> selectAll(Charity charity);
	
	public Charity selectCharityByCharityCode(Charity charity);
	
	// charity_Code로 후원정보 삭제
	public void deleteByCharityCode(Charity charity);
	
	// 후원자 이름으로 후원정보 조회
	public List<Charity> selectAllByUserId(Member member);
	
	// 후원자별 후원 횟수
	public List<Member> selectCountCharityGroupByUserId(Member member);
	
}

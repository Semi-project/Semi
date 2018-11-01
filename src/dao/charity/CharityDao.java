package dao.charity;

import java.util.List;

import dto.charity.Charity;
import dto.member.Member;

public interface CharityDao {

	// 후원 정보 삽입
	public void insert(Charity charity);

	// 후원정보 userId 로 조회
	public List<Charity> selectAll(Charity charity);

}

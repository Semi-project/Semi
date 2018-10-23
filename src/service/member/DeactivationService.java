package service.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.animal.Animal;
import dto.member.Member;

public interface DeactivationService {
	//////////////////탈퇴////////////////////////
	public void deleteMember(Member member);

	// Param 가져오기
	public Member getParam(HttpServletRequest req, HttpServletResponse resp);

}

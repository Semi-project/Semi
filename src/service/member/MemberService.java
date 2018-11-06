package service.member;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.member.Member;
import util.Paging;

public interface MemberService {

	///////////////// 유저//////////////////

	// 유저정보조회
	public Member selectMemberByUserId(Member member);

	// 회원정보수정
	public void updateMember(HttpServletRequest req, Member member) throws Exception;

	// 비밀번호 변경
	public int updateMemberPassword(Member member);
	public int updateMember(Member member ) throws Exception ;
	// 회원삭제
	public void deleteMemberByUserId(Member member)  throws Exception;

	// 아이디 찾기
	public List<Member> searchUserId(Member member);

	// 비밀번호 찾기
	public Member searchUserPw(Member member);

	// 2018-10-09
	// 파라미터 받기
	public Member getParam(HttpServletRequest req, HttpServletResponse resp);

	public int join(Member param);

	public int checkUserId(Member member);

	public int checkUser(Member member);

	public int checkUserEmail(Member member);

	// 2018-10-31

	public List<Member> getAllList(Paging paging);

	public String getSearch(HttpServletRequest req);

	public int getCurPage(HttpServletRequest req);

	public int getTotalCount(String search);

	public boolean selectUserPwCheck(Member member);

}

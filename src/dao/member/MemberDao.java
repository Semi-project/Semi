package dao.member;

import java.sql.SQLException;
import java.util.List;

import dto.member.Member;
import util.Paging;

public interface MemberDao {
	// 유저 아이디로 멤버 정보 조회
	public Member selectMemberByUserId(Member member);

	public List<Member> selectMemberAll(Paging paging);

	// 회원가입
	public int insertMember(Member member);

	// 회원정보수정
	public int updateMember(Member member);
	// 비밀번호 변경
	public int updateMemberPassword(Member member) throws Exception;

	// 회원삭제
	public void deleteMemberByUserId(Member member) throws SQLException;

	// 유저 id check
	public int selectCntMemberByUserId(Member member);


	// 유저 id+pw check
	public int selectCntMemberByUser(Member member);
	
	
	// 유저 email check 
	public int selectCntMemberByUserEmail(Member member);
	
	public Member selectUseridByUserInfo(Member member);
	
	//id 찾기 
	public int selectCntMemberByUserEmailAndName(Member member);
	public List<Member> selectMemberByUserEmailAndName(Member member);

	public int selectCntMemberPwByuserInfo(Member member);
	public Member selectMemberPwByuserInfo(Member member);
	
	public int selectCntAll(String search);

	public boolean selectUserPwCheck(Member member);
	
}

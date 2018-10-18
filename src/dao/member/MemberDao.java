package dao.member;

import java.util.List;

import dto.member.Member;

public interface MemberDao {
	
	// 유저 아이디로 멤버 정보 조회
	public Member selectMemberByUserId(Member member);
	
	// 유저 전체조회
	public List<Member> selectMemberAll();

	// 회원가입
	public void insertMember(Member member);

	// 회원정보수정
	public void updateMember(Member member);

	// 비밀번호 변경
	public void updateMemberPassword(Member member);

	// 회원삭제
	public void deleteMemberByUserId(Member member);

	// 유저 확인
	public int selectCntMemberByUserId(Member member);
	
	// 유저 정보로 아이디 찾기
	public Member selectUseridByUserInfo(Member member);

}

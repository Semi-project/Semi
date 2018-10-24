package dao.member;

import dto.member.Member;
import dto.member.Role;

public interface RoleDao {

	// 유저 아이디로 멤버 정보 조회 사용자인지 권한자인지
		public Role selectByUserId(Member member);
}

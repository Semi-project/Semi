package dao.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import dto.member.Member;
import util.DBConn;

public class MemberDaoImpl implements MemberDao {
	private Member member = new Member();
	private SubscriptionDao subScription = new SubscriptionDaoImpl();
	private RoleDao roleDao = new RoleDaoImpl();
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private Connection conn = DBConn.getConnection();

	@Override
	public Member selectMemberByUserId(Member member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Member> selectMemberAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertMember(Member member) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateMember(Member member) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateMemberPassword(Member member) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteMemberByUserId(Member member) {
		// TODO Auto-generated method stub

	}

	@Override
	public int selectCntMemberByUserId(Member member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Member selectUseridByUserInfo(Member member) {
		// TODO Auto-generated method stub
		return null;
	}

}

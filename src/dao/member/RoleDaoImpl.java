package dao.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dto.member.Member;
import dto.member.Role;
import util.DBConn;

public class RoleDaoImpl implements RoleDao {
	private Member member = new Member();
	private Role role = new Role();
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private Connection conn = DBConn.getConnection();

	@Override
	public Role selectByUserId(Member member) {

		return null;
	}

}

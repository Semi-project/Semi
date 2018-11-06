package dao.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.member.Member;
import util.DBConn;
import util.Paging;

public class MemberDaoImpl implements MemberDao {

	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private Connection conn = DBConn.getConnection();

	@Override
	public Member selectMemberByUserId(Member member) {
		Member m = new Member();
		String sql = "";
		sql = "SELECT *FROM member WHERE userid = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, member.getUserid());
			rs = ps.executeQuery();
			while (rs.next()) {
				m.setUserid(rs.getString("userid"));
				m.setUserpw(rs.getString("userpw"));
				m.setName(rs.getString("name"));
				m.setGender(rs.getString("gender"));
				m.setUserbirth(rs.getDate("userbirth"));
				m.setPhone(rs.getString("phone"));
				m.setAddress(rs.getString("address"));
				m.setEmail(rs.getString("email"));
				m.setSubscription_no(rs.getInt("subscription_no"));
				m.setRole_id(rs.getInt("role_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return m;
	}

	@Override
	public int selectCntMemberByUserId(Member member) {
		int cnt = -1;
		String sql = "";
		rs = null;
		ps = null;
		sql += "SELECT COUNT(*) FROM member where userid=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, member.getUserid());
			rs = ps.executeQuery();
			if (rs.next()) {
				cnt = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return cnt;
	}

	@Override
	public int selectCntMemberByUser(Member member) {
		int cnt = -1;
		String sql = "";
		rs = null;
		ps = null;
		sql += "SELECT COUNT(*) FROM member where userid=? AND userpw=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, member.getUserid());
			// System.out.println(member.getUserpw());
			ps.setString(2, member.getUserpw());
			rs = ps.executeQuery();
			if (rs.next()) {
				cnt = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return cnt;
	}

	@Override
	public int selectCntMemberByUserEmail(Member member) {
		int cnt = -1;
		String sql = "";
		sql += "SELECT COUNT(*) FROM member where email=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, member.getEmail());
			rs = ps.executeQuery();
			rs.next();
			cnt = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return cnt;
	}

	@Override
	public int insertMember(Member member) {
		int cnt = -1;
		String sql = "";
		sql += "INSERT INTO member";
		sql += " (userid, userpw, name, gender,userbirth,phone,";
		sql += " address, email, subscription_no, role_id)";
		sql += " VALUES (?,?,?,?,?,?,?,?,subscription_seq.currval,1)";
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.setString(1, member.getUserid());
			ps.setString(2, member.getUserpw());
			ps.setString(3, member.getName());
			ps.setString(4, member.getGender());
			ps.setDate(5, new java.sql.Date((member.getUserbirth()).getTime()));
			ps.setString(6, member.getPhone());
			ps.setString(7, member.getAddress());
			ps.setString(8, member.getEmail());

			cnt = ps.executeUpdate();
			conn.commit();

		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return cnt;
	}

	@Override
	public int updateMember(Member member) {
		String sql = "";
		sql += "UPDATE member";
		sql += " SET phone = ? ,";
		sql += "	email = ? ,	";
		sql += "    address = ?";
		sql += " WHERE userid = ? ";

		try {

			conn.setAutoCommit(false);

			ps = conn.prepareStatement(sql);

			ps.setString(1, member.getPhone());
			ps.setString(2, member.getEmail());
			ps.setString(3, member.getAddress());
			ps.setString(4, member.getUserid());

			ps.executeQuery();

			conn.commit();

		} catch (SQLException e) {

			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();

		} finally {

			try {
				if (ps != null)
					ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return 1;

	}

	@Override
	public int updateMemberPassword(Member member) {
		String sql = "";
		sql += " UPDATE member";
		sql += " SET userpw = ? ";
		sql += "  WHERE userid =?";

		try {
			conn.setAutoCommit(false);

			ps = conn.prepareStatement(sql);

			ps.setString(1, member.getUserpw());
			ps.setString(2, member.getUserid());

			ps.executeQuery();

			conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 1;
	}

	@Override
	   public void deleteMemberByUserId(Member member) throws SQLException {

	      String sql = "";
	      sql += "UPDATE member SET";
	      sql += " userpw= null";
	      sql += " , name = null";
	      sql += " , gender = null";
	      sql += " , userbirth= null";
	      sql += " , phone= null";
	      sql += " , address= null";
	      sql += " , email= null";
	      sql += " , subscription_no= null";
	      sql += " , role_id= null";
	      sql += " WHERE userid=?";

	      try {
	         conn.setAutoCommit(false);

	         ps = conn.prepareStatement(sql);
	         
	         ps.setString(1, member.getUserid());
	         

	         ps.executeQuery();

	         conn.commit();

	      } catch (SQLException e) {
	         e.printStackTrace();
	         conn.rollback();
	      } finally {
	         try {
	            if (ps != null)
	               ps.close();

	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	      }
	}
	@Override
	public Member selectUseridByUserInfo(Member member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectCntMemberByUserEmailAndName(Member member) {
		int cnt = -1;
		String sql = "";
		sql += "SELECT COUNT(*) FROM member where email=? AND name=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, member.getEmail());
			ps.setString(2, member.getName());

			rs = ps.executeQuery();
			rs.next();
			cnt = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return cnt;
	}

	@Override
	public List<Member> selectMemberAll(Paging paging) {
		String sql = "";
		sql += "SELECT * FROM (";
		sql += " SELECT rownum rnum, B.* FROM (";
		sql += " 	SELECT ";
		sql += " 	* FROM Member ";
		if (paging.getSearch() != null && !"".equals(paging.getSearch())) {
			sql += " WHERE userid LIKE '%" + paging.getSearch() + "%'";
		}
		sql += " 	ORDER BY userid";
		sql += " ) B";
		sql += " ORDER BY rnum";
		sql += ")";
		sql += "WHERE rnum between ? AND ?";
		List<Member> list = new ArrayList<Member>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());

			rs = ps.executeQuery();
			while (rs.next()) {
				Member m = new Member();
				m.setUserid(rs.getString("userid"));
				m.setUserpw(rs.getString("userpw"));
				m.setName(rs.getString("name"));
				m.setGender(rs.getString("gender"));
				m.setUserbirth(rs.getDate("userbirth"));
				m.setPhone(rs.getString("phone"));
				m.setAddress(rs.getString("address"));
				m.setEmail(rs.getString("email"));
				m.setSubscription_no(rs.getInt("subscription_no"));
				m.setRole_id(rs.getInt("role_id"));
				list.add(m);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return list;
	}

	@Override
	public List<Member> selectMemberByUserEmailAndName(Member member) {
		String sql = "";
		sql += "SELECT * FROM member where email=? AND name=?";
		List<Member> list = new ArrayList<Member>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, member.getEmail());
			ps.setString(2, member.getName());

			rs = ps.executeQuery();

			while (rs.next()) {
				Member m = new Member();
				m.setUserid(rs.getString("userid"));
				m.setUserpw(rs.getString("userpw"));
				m.setName(rs.getString("name"));
				m.setGender(rs.getString("gender"));
				m.setUserbirth(rs.getDate("userbirth"));
				m.setPhone(rs.getString("phone"));
				m.setAddress(rs.getString("address"));
				m.setEmail(rs.getString("email"));
				m.setSubscription_no(rs.getInt("subscription_no"));
				m.setRole_id(rs.getInt("role_id"));
				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public int selectCntMemberPwByuserInfo(Member member) {
		String sql = "SELECT count(*) FROM member WHERE name=? AND userid=? AND email=?";
		int cnt = -1;
		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, member.getName());
			ps.setString(2, member.getUserid());
			ps.setString(3, member.getEmail());
			rs = ps.executeQuery();
			rs.next();
			cnt = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return cnt;
	}

	@Override
	public Member selectMemberPwByuserInfo(Member member) {
		Member m = new Member();
		String sql = "SELECT * FROM member WHERE name=? AND userid=? AND email=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, member.getName());
			ps.setString(2, member.getUserid());
			ps.setString(3, member.getEmail());

			rs = ps.executeQuery();

			while (rs.next()) {

				m.setUserid(rs.getString("userid"));
				m.setUserpw(rs.getString("userpw"));
				m.setName(rs.getString("name"));
				m.setGender(rs.getString("gender"));
				m.setUserbirth(rs.getDate("userbirth"));
				m.setPhone(rs.getString("phone"));
				m.setAddress(rs.getString("address"));
				m.setEmail(rs.getString("email"));
				m.setSubscription_no(rs.getInt("subscription_no"));
				m.setRole_id(rs.getInt("role_id"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return m;
	}

	@Override
	public int selectCntAll(String search) {
		String sql = "";
		sql += "SELECT COUNT(*) FROM member";
		if (search != null && !"".equals(search)) {
			sql += " WHERE userid LIKE '%" + search + "%'";
		}

		// DB 객체
		PreparedStatement ps = null;
		ResultSet rs = null;

		int cnt = 0;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			rs.next();

			cnt = rs.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return cnt;
	}

	@Override
	public boolean selectUserPwCheck(Member member) {
		Member m = new Member();
		boolean flag = false;

		String sql = "SELECT userid , userpw FROM MEMBER WHERE userid = ? AND userpw = ?";

		try {

			ps = conn.prepareStatement(sql);

			ps.setString(1, member.getUserid());
			ps.setString(2, member.getUserpw());

			rs = ps.executeQuery();

			while (rs.next()) {
				m.setUserid(rs.getString("userid"));
				m.setUserpw(rs.getString("userpw"));

				if (m != null && !"".equals(m)) {
					flag = true;
				}
			}

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
}

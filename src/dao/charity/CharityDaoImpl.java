package dao.charity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.adoption.Adoption;
import dto.charity.Charity;
import dto.member.Member;
import util.DBConn;
import util.Paging;

public class CharityDaoImpl implements CharityDao {

	private Connection conn = DBConn.getConnection();

	private PreparedStatement ps = null;
	private ResultSet rs = null;

	@Override
	public void insert(Charity charity) {
		String sql = "INSERT INTO charity  (imp_uid,merchant_uid,pay_method,name,paid_amount, buyer_name,buyer_email,userid,charity_date)VALUES(?,?,?,?,?,?,?,?,?)";

		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.setString(1, charity.getImp_uid());
			ps.setString(2, charity.getMerchant_uid());
			ps.setString(3, charity.getPay_method());
			ps.setString(4, charity.getName());
			ps.setInt(5, charity.getPaid_amount());
			ps.setString(6, charity.getBuyer_name());
			ps.setString(7, charity.getBuyer_email());
			ps.setString(8, charity.getUserid());
			ps.setDate(9, new java.sql.Date(charity.getCharity_date().getTime()));

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
				// DB객체 닫기
				if (ps != null)
					ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public List<Charity> selectAll(Charity charity) {

		String sql = "SELECT * FROM charity WHERE userid=? ORDER BY charity_date desc";

		List<Charity> list = new ArrayList<Charity>();

		try {
			ps = conn.prepareStatement(sql);
			// 해당 userid 자료만 가져오기
			ps.setString(1, charity.getUserid());

			rs = ps.executeQuery();

			while (rs.next()) {
				Charity c = new Charity();
				c.setImp_uid(rs.getString("imp_uid"));
				c.setMerchant_uid(rs.getString("merchant_uid"));
				c.setPay_method(rs.getString("pay_method"));
				c.setName(rs.getString("name"));
				c.setPaid_amount(rs.getInt("paid_amount"));
				c.setBuyer_name(rs.getString("buyer_name"));
				c.setBuyer_email(rs.getString("buyer_email"));
				c.setUserid(rs.getString("userid"));
				c.setCharity_date(rs.getDate("charity_date"));

				list.add(c);
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

		return list;
	}

	@Override
	public int selectCharityCntAll(String search) {
		// 전체 게시글 수 조회 쿼리
		String sql = "";
		sql += "SELECT COUNT(*) FROM charity";
		if (search != null && !"".equals(search)) {
			sql += " WHERE name LIKE '%" + search + "%'";
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
	public List<Charity> selectPagingList(Paging paging) {
		String sql = "";
		sql += "SELECT *FROM (" + "SELECT rownum rnum, B.*FROM (" + "SELECT IMP_UID, " + "MERCHANT_UID, "
				+ "PAY_METHOD, " + "NAME, " + "PAID_AMOUNT, " + "BUYER_NAME, " + "BUYER_EMAIL, " + "USERID, "
				+ "CHARITY_DATE " + "FROM charity WHERE userid = ?";
		sql += " ORDER BY CHARITY_DATE desc) B ORDER BY rnum ) WHERE rnum between ? AND ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Charity> list = new ArrayList<>();
		try {
			// DB작업
			ps = conn.prepareStatement(sql);

			ps.setString(1, paging.getUserid());
			ps.setInt(2, paging.getStartNo());
			ps.setInt(3, paging.getEndNo());

			rs = ps.executeQuery();

			// 조회 결과 담기
			while (rs.next()) {
				Charity c = new Charity();

				// ResultSet의 결과 행 하나씩 DTO에 저장
				c.setImp_uid(rs.getString("imp_uid"));
				c.setMerchant_uid(rs.getString("merchant_uid"));
				c.setPay_method(rs.getString("pay_method"));
				c.setName(rs.getString("name"));
				c.setPaid_amount(rs.getInt("paid_amount"));
				c.setBuyer_name(rs.getString("buyer_name"));
				c.setBuyer_email(rs.getString("buyer_email"));
				c.setUserid(rs.getString("userid"));
				c.setCharity_date(rs.getDate("charity_date"));
				// 조회결과를 List로 생

				list.add(c);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// DB객체 닫기
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

}

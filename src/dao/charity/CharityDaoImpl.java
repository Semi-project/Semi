package dao.charity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.charity.Charity;
import dto.member.Member;
import util.DBConn;

public class CharityDaoImpl implements CharityDao {

	private Connection conn = DBConn.getConnection();
	
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	private String sql;
	
	@Override
	public void insert(Charity charity) {

		

	}

	@Override
	public List<Charity> selectAll(Charity charity) {
		
		sql = "SELECT * FROM charity WHERE userid=?";
		
		List<Charity> list = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			// 해당 userid 자료만 가져오기
			ps.setString(1, charity.getUserId());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				charity.setCharity_Code( rs.getInt("charity_code"));
				charity.setCharity_Cate_Code( rs.getInt("charity_cate_code"));
				charity.setCharity_Amount( rs.getInt("charity_amount"));
				charity.setCharity_Date(rs.getDate("charity_date"));
				charity.setCharity_Payment( rs.getInt("charity_payment_code"));
				
				list.add(charity);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)	rs.close();
				if(ps!=null)	ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}

	@Override
	public Charity selectCharityByCharityCode(Charity charity) {

		sql = "SELECT * FROM charity WHERE charity_code=?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, charity.getCharity_Code());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				charity.setCharity_Cate_Code( rs.getInt("charity_cate_code"));
				charity.setCharity_Amount( rs.getInt("charity_amount"));
				charity.setUserId( rs.getString("userid"));
				charity.setCharity_Date(rs.getDate("charity_date"));
				charity.setCharity_Payment( rs.getInt("charity_payment_code"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)	rs.close();
				if(ps!=null)	ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return charity;
	}

	@Override
	public void deleteByCharityCode(Charity charity) {

		sql = "DELETE FROM charity WHERE charity_code=?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, charity.getCharity_Code());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps!=null)	ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public List<Charity> selectAllByUserId(Member member) {

		// sql = "SELECT * FROM charity WHERE userid=?";
		// 이 method의 userid는 userid인가 username인가?
		// 이 method의 존재 이유는?
						
				
		return null;
	}

	@Override
	public List<Member> selectCountCharityGroupByUserId(Member member) {

		// 무슨 용도로 만든 method인가?
		
	
		sql = "SELECT Counts, name FROM (";
		sql += "SELECT COUNT(*) AS Counts, charity.userid";
		sql += " FROM charity, member";
		sql += " WHERE member.userid = charity.userid";
		sql += " GROUP BY charity.userid) c,";
		sql += " member m";
		sql += " WHERE c.userid = m.userid;";
		
		try {
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)	rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if(rs!=null)	rs.close();
					if(ps!=null)	ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
						
		return null;
	}

}

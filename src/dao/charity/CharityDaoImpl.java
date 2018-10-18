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
		
		sql = "SELECT * FROM charity";
		
		List<Charity> list = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				charity.setCharity_Code( rs.getInt("charity_code"));
				charity.setCharity_Cate_Code( rs.getInt("charity_cate_code"));
				charity.setCharity_Amount( rs.getInt("charity_amount"));
				charity.setUserId( rs.getString("userid"));
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteByCharityCode(Charity charity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Charity> selectAllByUserId(Member member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Member> selectCountCharityGroupByUserId(Member member) {
		// TODO Auto-generated method stub
		return null;
	}

}

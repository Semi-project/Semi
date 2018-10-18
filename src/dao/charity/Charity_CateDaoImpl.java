package dao.charity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.charity.Charity_Cate;
import util.DBConn;

public class Charity_CateDaoImpl implements Charity_CateDao{

	private Connection conn = DBConn.getConnection();
	
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	private String sql;
	
	@Override
	public Charity_Cate selectCharityNameByCharityCateCode(Charity_Cate charity_Cate) {

		sql = "SELECT * FROM charity_Cate WHERE charity_Cate_Code=?";
		
		// charity_cate_code 받아오기
		int charity_Cate_Code = charity_Cate.getCharity_Cate_Code();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, charity_Cate_Code);
			
			rs = ps.executeQuery();
			
			// 쿼리 조회후 결과 있을 때 값 저장
			while(rs.next())
				charity_Cate.setCharity_Name( rs.getString("charity_Name"));
			
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
		
		return charity_Cate;
	}

}

package dao.board.recommend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.board.Free_Board;
import util.DBConn;

public class RecommendDaoImpl implements RecommendDao{
	// DB 연결 객체
		private Connection conn = DBConn.getConnection();
		
		// JDBC 객체
		private PreparedStatement ps;
		private ResultSet rs;
	@Override
	public int selectCountRecommend(Free_Board freeboard) {

//		System.out.println(board);
		
		String sql = "SELECT COUNT(*) FROM recommend"
				+ " WHERE userid=?"
				+ " AND boardno=?";
		
		int cnt = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, freeboard.getUserid());
			ps.setInt(2, freeboard.getBoardno());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				cnt = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return cnt;
	}

	

	@Override
	public void insertRecommend(Free_Board freeboard) {
		String sql = "INSERT INTO recommend (userid, boardno)"
				+ " VALUES( ?, ? )";
		
//		System.out.println(board);
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, freeboard.getUserid());
			ps.setInt(2, freeboard.getBoardno());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
		
	

	@Override
	public void deleteRecommend(Free_Board freeboard) {
		String sql = "DELETE recommend"
				+ " WHERE userid=?"
				+ "	AND boardno=?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, freeboard.getUserid());
			ps.setInt(2, freeboard.getBoardno());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	

	@Override
	public int selectTotalRecommend(Free_Board freeboard) {
		String sql = "SELECT COUNT(*) FROM recommend"
				+ " WHERE boardno=?";
		
		int cnt = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, freeboard.getBoardno());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				cnt = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return cnt;
	}


}

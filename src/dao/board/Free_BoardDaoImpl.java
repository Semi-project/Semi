package dao.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.board.Free_Board;
import util.DBConn;
import util.Paging;

public class Free_BoardDaoImpl implements Free_BoardDao {

	private Connection conn=DBConn.getConnection();
	private PreparedStatement ps =null;
	private ResultSet rs=null;

	@Override
	public List<Free_Board> selectFreeBoard() {

		String sql="SELECT * FROM free_board ORDER BY boardno DESC";


		List <Free_Board>list= new ArrayList<Free_Board>();

		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();

			while(rs.next()) {
				Free_Board b = new Free_Board();
				b.setBoardno(rs.getInt("boardno"));
				b.setCateno(rs.getInt("cateno"));
				b.setContent(rs.getString("content"));
				b.setHit(rs.getInt("hit"));
				b.setInsert_Dat(rs.getDate("insert_Dat"));
				b.setRecommend(rs.getInt("recomend"));
				b.setTitle(rs.getString("title"));
				b.setUpdate_Dat(rs.getDate("update_Dat"));
				b.setUserid(rs.getString("userid"));

				list.add(b);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)    rs.close();
				if(ps!=null)	ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	

		return list;
	}

	@Override
	public void insertFreeBoard(Free_Board freeBoard) {
		// TODO Auto-generated method stub

	}

	@Override
	public Free_Board viewFreeBoard(Free_Board freeBoard) {
		String sql="SELECT * FROM free_board WHERE boardno=?";
		Free_Board fb= new Free_Board();
		
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, fb.getBoardno());
			ps.executeQuery();
			
			while(rs.next()) {
				fb.setBoardno(rs.getInt("boardno"));
				fb.setContent(rs.getString("content"));
				fb.setHit(rs.getInt("hit"));
				fb.setTitle(rs.getString("title"));
				fb.setUserid(rs.getString("userid"));
				fb.setInsert_Dat(rs.getDate("insert_Dat"));
			}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					//DB객체 닫기
					if(rs!=null)	rs.close();
					if(ps!=null)	ps.close();
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		return fb;
	}

	@Override
	public void updateFreeBoard(Free_Board freeBoard) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteFreeBoard(Free_Board freeBoard) {
		// TODO Auto-generated method stub

	}

	@Override
	public String selectFreeBoardByTitle(Free_Board freeBoard) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String selectFreeBoardByContent(Free_Board freeBoard) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String selectFreeBoardByuserId(Free_Board freeBoard) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selecntFreeBoardCntAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Free_Board> selectFreeBoardPagingList(Paging paging) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateHit(Free_Board freeBoard) {
		String sql="UPDATE free_board"
				+ " SET hit = hit+1"
				+ " WHERE boardno=?";
		
		
		try {
			conn.setAutoCommit(false);
			
			ps=conn.prepareStatement(sql);
			ps.setInt(1, freeBoard.getBoardno());
			ps.executeUpdate();
			
			
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				//DB객체 닫기
				if(ps!=null)	ps.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void updateRecommend(Free_Board freeBoard) {
		// TODO Auto-generated method stub

	}

	@Override
	public Free_Board selectByComment_No(Free_Board freeBoard) {
		// TODO Auto-generated method stub
		return null;
	}

}

package dao.comment.free;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import dto.board.Free_Board;
import dto.comment.Free_Comments;
import util.DBConn;
import util.Paging;

public class Free_CommentDaoImpl implements Free_CommentDao {
	private Connection conn=DBConn.getConnection();
	private PreparedStatement ps =null;
	private ResultSet rs=null;

	@Override
	public void insert(Free_Comments comment) {
		String sql="insert into free_comments(comment_no,userid,content,boardno)values(FREE_COMMENTS_SEQ.nextval,?,?,?)";
	
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, comment.getUserid());
			ps.setString(2, comment.getContent());
			ps.setInt(3, comment.getBoardno());
			
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
	public List<Free_Comments> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Free_Comments selectFree_CommentByContent(Free_Comments comment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Free_Comments selectFree_CommentByUserId(Free_Comments comment) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void updateFree_CommentByUserId(Free_Comments comment) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Free_Comments> selectFree_CommentPagingList(Paging paging) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override   
	public List selectComment(Free_Board freeboard) {
		String sql
		= "SELECT * FROM ("
				+ "SELECT rownum rnum, B.* FROM ("
				+ "	SELECT"
				+ "		comment_no,"
				+ "		boardno,"
				+ "		userid,"
				+ "		content,"
				+ "		to_char(insert_dat, 'yyyy-mm-dd hh24:mi:ss') insert_dat"
				+ "	FROM free_comments"
				+ "	WHERE boardno = ?"
				+ "	ORDER BY insert_dat"
				+ "	) B"
				+ ") ORDER BY rnum DESC";
		
//		String sql ="SELECT * FROM (SELECT rownum rnum, B.* FROM (SELECT comment_no,boardno,userid,content,insert_dat FROM free_comments WHERE boardno =? ORDER BY insert_dat) B) ORDER BY rnum DESC";

		List commentList = new ArrayList();
		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, freeboard.getBoardno() );

			// ResultSet 반환
			rs = ps.executeQuery();

			while( rs.next() ) {
				Free_Comments comment = new Free_Comments();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

				comment.setRnum(rs.getInt("rnum"));
				comment.setComment_no(rs.getInt("comment_no"));
				comment.setBoardno(rs.getInt("boardno"));
				comment.setUserid(rs.getString("userid"));
				comment.setContent(rs.getString("content"));
				 comment.setInsert_dat(format.parse(rs.getString("insert_dat")));

				commentList.add(comment);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)	rs.close();
				if(ps!=null)	ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return commentList;
	}




	@Override
	public int countComment(Free_Comments comment) {
String sql = "SELECT COUNT(*) FROM Free_comments WHERE comment_no=?";
		
		int cnt = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, comment.getComment_no());
			rs = ps.executeQuery();
			rs.next();
			cnt = rs.getInt(1);
			
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
	public void deleteComment(Free_Comments comment) {
		String sql
		= "DELETE Free_Comments	WHERE comment_no = ?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, comment.getComment_no());
			
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
		
	


}

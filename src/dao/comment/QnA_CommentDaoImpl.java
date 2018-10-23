package dao.comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.board.QnA;
import dto.comment.QnA_Comments;
import util.DBConn;
import util.Paging;

public class QnA_CommentDaoImpl implements QnA_CommentDao {

	private Connection conn = DBConn.getConnection();
	
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public void insert(QnA_Comments comment) throws Exception {
		String sql =" INSERT INTO QnA_COMMENTS ("
					+ " 	commmentno,"
					+ " 	userid,"
					+ " 	content,"
					+ " 	boardno )"
					+ " VALUES ("
					+ "		qna_comments_seq.nextval,"
					+ "		?,"
					+ "		?,"
					+ "		? )";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, comment.getCommentNo());
			ps.setString(2, comment.getUserid());
			ps.setString(3, comment.getContent());
			ps.setInt(4, comment.getBoardno());
			
			ps.executeQuery();
			
			conn.commit();
		
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		}finally {
			try {
				if(ps!=null) ps.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		

	}

	@Override
	public List selectQnA_Comments(QnA qna) {
		String sql
		= "SELECT * FROM ("
				+ "SELECT rownum rnum, B.* FROM ("
				+ "	SELECT"
				+ "		commentno,"
				+ "		userid,"
				+ "		content,"
				+ "		insert_dat"
				+ "		boardno"
				+ "	FROM qna_comments"
				+ "	WHERE boardno = ?"
				+ "	ORDER BY insert_dat"
				+ "	) B"
				+ ") ORDER BY rnum DESC";
		
			List commentList = new ArrayList<>();
			
			try {
				ps = conn.prepareStatement(sql);
				
				ps.setInt(1,  qna.getBoardno());
				
				rs = ps.executeQuery();
				
				while(rs.next()) {
					
					QnA_Comments comment = new QnA_Comments();
					
					comment.setRnum(rs.getInt("rnum"));
					comment.setCommentNo(rs.getInt("commentno"));
					comment.setUserid(rs.getString("userid"));
					comment.setContent(rs.getString("content"));
					comment.setInsertDat(rs.getDate("insert_dat"));
					comment.setBoardno(rs.getInt("boardno"));
				
					commentList.add(comment);
				}
			
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					if(rs != null) rs.close();
					if(ps != null) ps.close();
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		return commentList;
	}

	
	@Override
	public int selectQnA_CommentCntAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteQnA_Comment(QnA_Comments comment) throws Exception {
		
		String sql ="DELETE qna_comments"
				+ "	WHERE commentno = ?";
		
		try {
			ps = conn.prepareStatement(sql);
		
			ps.setInt(1,  comment.getCommentNo());
			
			ps.executeQuery();
			
			conn.commit();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps != null) ps.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		

	}

	@Override
	public void updateQnA_CommentByUserId(QnA_Comments comment) {
		// TODO Auto-generated method stub

	}

	

}

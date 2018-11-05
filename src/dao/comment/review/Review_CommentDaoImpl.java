package dao.comment.review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.board.Review_Board;
import dto.comment.Notice_Comments;
import dto.comment.Review_Comments;
import util.DBConn;

public class Review_CommentDaoImpl implements Review_CommentDao {
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	// DB연결 객체
	private Connection conn = DBConn.getConnection();

	@Override
	public List<Review_Comments> select(Review_Board board) {
		String sql = "SELECT * FROM (" + "SELECT rownum rnum, B.* FROM (" + "	SELECT" + "		comment_no,"
				+ "		boardno," + "		userid," + "		content," + "		insert_dat"
				+ "	FROM review_comments" + "	WHERE boardno = ?" + "	ORDER BY insert_dat" + "	) B"
				+ ") ORDER BY rnum DESC";
		List<Review_Comments> commentList = new ArrayList<Review_Comments>();
		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, board.getBoardno());

			// ResultSet 반환
			rs = ps.executeQuery();

			while (rs.next()) {
				Review_Comments comment = new Review_Comments();

				comment.setRnum(rs.getInt("rnum"));
				comment.setBoardno(rs.getInt("boardno"));
				comment.setCommentNo(rs.getInt("comment_no"));
				comment.setUserid(rs.getString("userid"));
				comment.setContent(rs.getString("content"));
				comment.setInsertDat(rs.getDate("insert_dat"));

				commentList.add(comment);
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

		return commentList;

	}

	@Override
	public void insert(Review_Comments comment) {
	
		// TODO Auto-generated method stub
		String sql = "";
		sql += "INSERT INTO review_comments(comment_no,userid,content,boardno)";
		sql += " VALUES(review_COMMENTS_SEQ.nextval,?,?,?)";
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);

			ps.setString(1, comment.getUserid());
			ps.setString(2, comment.getContent());
			ps.setInt(3, comment.getBoardno());
			ps.executeUpdate();
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
	public void deleteComment(Review_Comments comment) {
		String sql = "";
		sql += "DELETE review_comments WHERE comment_no=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, comment.getCommentNo());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
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
	public int countComment(Review_Comments comment) {
		String sql = "SELECT COUNT(*) FROM review_comments WHERE comment_no=?";
		int cnt = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, comment.getCommentNo());
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

}

package dao.comment.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dto.board.Notice_Board;
import dto.comment.Notice_Comments;
import util.DBConn;

public class Notice_CommentDaoImpl implements Notice_CommentDao {

	private PreparedStatement ps = null;
	private ResultSet rs = null;
	// DB연결 객체
	private Connection conn = DBConn.getConnection();

	@Override
	public List<Notice_Comments> select(Notice_Board board) {

		return null;
	}

	@Override
	public void insert(Notice_Comments comment) {
		// TODO Auto-generated method stub
		String sql = "";
		sql += "INSERT INTO notice_comments(comment_no,userid,content,boardno)";
		sql += " VALUES(NOTICE_COMMENTS_SEQ.nextval,?,?,?)";
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);

			ps.setInt(1, comment.getBoardno());
			ps.setString(2, comment.getUserid());
			ps.setString(3, comment.getContent());
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
	public void deleteComment(Notice_Comments comment) {
		String sql = "";
		sql += "DELETE notice_comments WHERE comment_no=?";
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
	public int countComment(Notice_Comments comment) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Notice_Comments selectNotice_CommentByUserId(Notice_Comments comment) {
		// TODO Auto-generated method stub
		return null;
	}

}

package dao.comment.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

		String sql = "SELECT * FROM (" + "SELECT rownum rnum, B.* FROM (" + "	SELECT" + "		comment_no,"
				+ "		boardno," + "		userid," + "		content," + "		insert_dat"
				+ "	FROM notice_comments" + "	WHERE boardno = ?" + "	ORDER BY insert_dat" + "	) B"
				+ ") ORDER BY rnum DESC";
		List<Notice_Comments> commentList = new ArrayList<Notice_Comments>();
		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, board.getBoardno());

			// ResultSet 반환
			rs = ps.executeQuery();

			while (rs.next()) {
				Notice_Comments comment = new Notice_Comments();

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
		String sql = "SELECT COUNT(*) FROM notice_comments WHERE comment_no=?";
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

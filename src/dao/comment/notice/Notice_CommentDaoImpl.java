package dao.comment.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
//댓글 입력------------------------------------------------------------------------
	@Override
	public void insert(Notice_Comments comment) {
		// TODO Auto-generated method stub
		String sql = "";
		sql += "INSERT INTO notice_comments(comment_no,userid,content,insert_dat,boardno)";
		sql += " VALUES(NOTICE_COMMENTS_SEQ.nextval,?,?,sysdate,?)";
		try {
			conn.setAutoCommit(false);
			Date dat = new Date();
			ps = conn.prepareStatement(sql);
			ps.setString(1, comment.getUserid());
			ps.setString(2, comment.getContent());
//			ps.setDate(3, new java.sql.Date(dat.getTime()));
			ps.setInt(3, comment.getBoardno());
			ps.executeUpdate();
//			conn.commit();
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
				if (ps != null)		ps.close();

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
			ps.setInt(1, comment.getComment_no());

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
	
// 코멘트 조회 - 코멘트 번호를 rnum을 통해 같이 조회한다----------------
	@Override
	public List selectComment(Notice_Board noticeboard) {
		String sql
		= "SELECT * FROM ("
				+ "SELECT rownum rnum, B.* FROM ("
				+ "	SELECT"
				+ "		comment_no,"
				+ "		boardno,"
				+ "		userid,"
				+ "		content,"
				+ "		to_char(insert_dat, 'yyyy-mm-dd hh24:mi:ss') insert_dat"  
				+ "	FROM notice_comments"
				+ "	WHERE boardno = ?"
				+ "	ORDER BY insert_dat"
				+ "	) B"
				+ ") ORDER BY rnum DESC";
		
//		String sql ="SELECT * FROM (SELECT rownum rnum, B.* FROM (SELECT comment_no,boardno,userid,content,insert_dat FROM free_comments WHERE boardno =? ORDER BY insert_dat) B) ORDER BY rnum DESC";

		List commentList = new ArrayList();
		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, noticeboard.getBoardno() );

			// ResultSet 반환
			rs = ps.executeQuery();

			while( rs.next() ) {
				Notice_Comments comment = new Notice_Comments();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

				comment.setRnum(rs.getInt("rnum"));
				comment.setComment_no(rs.getInt("comment_no"));
				comment.setBoardno(rs.getInt("boardno"));
				comment.setUserid(rs.getString("userid"));
				comment.setContent(rs.getString("content"));
				comment.setInsert_dat(format.parse(rs.getString("insert_dat")));

				commentList.add(comment);
			}

		} catch (SQLException | ParseException e) {
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

}

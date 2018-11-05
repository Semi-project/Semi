package dao.board.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.board.Notice_Board;
import dto.file.Notice_Filetb;
import util.DBConn;
import util.Paging;

public class Notice_BoardDaoImpl implements Notice_BoardDao {
	
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	// DB연결 객체
	private Connection conn = DBConn.getConnection();

//공지사항 리스트----------------------------------------------------------------------
	@Override
	public List<Notice_Board> selectNoticeBoardAll() {
		String sql = "";
		sql += "SELECT * FROM notice_board";
		sql += " ORDER BY boardno DESC";
		ps = null;
		rs = null;
		List<Notice_Board> list = new ArrayList<Notice_Board>();
		try {
			// DB작업
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			// 전체조회 결과 담기
			while (rs.next()) {
				Notice_Board b = new Notice_Board();
				// ResultSet의 결과 행 하나씩 DTO에 저장
				b.setBoardno(rs.getInt("boardno"));
				b.setCateno(rs.getInt("cateno"));
				b.setTitle(rs.getString("title"));
				b.setContent(rs.getString("content"));
				b.setInsert_dat(rs.getDate("insert_dat"));
				b.setInsert_dat(rs.getDate("update_dat"));
				b.setHit(rs.getInt("hit"));
				b.setUserid(rs.getString("userid"));
				b.setRecomend(rs.getInt("recomend"));
				// 조회결과를 List로 생성
				list.add(b);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// DB객체 닫기
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// 전체조회 결과 반환
		return list;
	}
//----------------------------------------------------------------------
	@Override
	public void insertNoticeBoard(Notice_Board noticeBoard) {
		
		String sql = "";
		sql += "INSERT INTO notice_board(BOARDNO,CATENO,TITLE,CONTENT,HIT,USERID,RECOMEND)";
		sql += " VALUES(notice_board_seq.currval, 1001, ?, ?, 0,?,0)";
		ps = null;
		try {
			conn.setAutoCommit(false);
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, noticeBoard.getTitle());
			ps.setString(2, noticeBoard.getContent());
			ps.setString(3, noticeBoard.getUserid());
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
				if (ps != null)		ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
//글 수정----------------------------------------------------------------------
	@Override
	public void updateNoticeBoard(Notice_Board noticeBoard) {

		// 다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += " UPDATE notice_board";
		sql += " SET title = ?,";
		sql += " content = ?";
		sql += " WHERE boardno = ?";

		try {
			conn.setAutoCommit(false);

			ps = conn.prepareStatement(sql);
			ps.setString(1, noticeBoard.getTitle());
			ps.setString(2, noticeBoard.getContent());
			ps.setInt(3, noticeBoard.getBoardno());

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
				if (ps != null)		ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
@Override
	public void insertFile(Notice_Filetb notice_Filetb) {
		// TODO Auto-generated method stub
		
	}
	//----------------------------------------------------------------------
	@Override
	public String selectNoticeBoardByTitle(Notice_Board noticeBoard) {
		// TODO Auto-generated method stub
		return null;
	}
//----------------------------------------------------------------------
	@Override
	public String selectNoticeBoardByContent(Notice_Board noticeBoard) {
		// TODO Auto-generated method stub
		return null;
	}
//----------------------------------------------------------------------
	@Override
	public String selectNoticeBoardByuserId(Notice_Board noticeBoard) {
		// TODO Auto-generated method stub
		return null;
	}
//----------------------------------------------------------------------
	@Override
	public int selectNoticeBoardCntAll() {
		// 전체 게시글 수 조회 쿼리
		ps = null;
		rs = null;
		String sql = "";
		sql += "SELECT COUNT(*) FROM notice_board";
		int cnt = -1;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			rs.next();

			cnt = rs.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}
//----------------------------------------------------------------------
	@Override
	public List<Notice_Board> selectNoticeBoardPagingList(Paging paging) {
		String sql = "";
		sql += "SELECT * FROM (";
		sql += " SELECT rownum rnum, B.* FROM (";
		sql += " 	SELECT * FROM notice_board";
		sql += " 	ORDER BY boardno DESC";
		sql += " ) B";
		sql += " ORDER BY rnum";
		sql += ")";
		sql += "WHERE rnum between ? AND ?";
		ps = null;
		rs = null;
		List<Notice_Board> list = new ArrayList<Notice_Board>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());
			rs = ps.executeQuery();
			while (rs.next()) {
				Notice_Board notice_board = new Notice_Board();

				// ResultSet의 결과 행 하나씩 DTO에 저장
				notice_board.setBoardno(rs.getInt("boardno"));
				notice_board.setCateno(rs.getInt("cateno"));
				notice_board.setTitle(rs.getString("title"));
				notice_board.setContent(rs.getString("content"));
				notice_board.setHit(rs.getInt("hit"));
				notice_board.setInsert_dat(rs.getDate("insert_dat"));
				notice_board.setUserid(rs.getString("userid"));
				notice_board.setRecomend(rs.getInt("recomend"));

				// 조회결과를 List로 생성
				list.add(notice_board);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// DB객체 닫기
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
//----------------------------------------------------------------------
	@Override
	public void updateHit(Notice_Board noticeBoard) {
		// 전체 조회 쿼리
		String sql = "";
		sql += "UPDATE notice_board";
		sql += " SET hit = hit+1";
		sql += " WHERE boardno=?";

		ps = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.setInt(1, noticeBoard.getBoardno());
			ps.executeUpdate();
			// 정상적으로 진행될 경우 commit
			conn.commit();
		} catch (SQLException e) {
			// 예외발생 시 rollback
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
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
//공지사항 글 삭제----------------------------------------------------------------------
	@Override
	public void deleteNoticeBoard(Notice_Board noticeBoard) {
		String sql = "DELETE notice_board WHERE boardno=?";
		String name = null;

		try {
			conn.setAutoCommit(false);

			// DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, noticeBoard.getBoardno());

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
				if (ps != null)		ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
//----------------------------------------------------------------------
	@Override
	public Notice_Board selectByComment_No(Notice_Board noticeBoard) {
		// TODO Auto-generated method stub
		return null;
	}
//----------------------------------------------------------------------
	@Override
	public int selectNotice_Boardno() {
		String sql = "";
		sql += "SELECT notice_board_seq.nextval";
		sql += " FROM dual";
		ps = null;
		rs = null;

		// 게시글번호
		int boardno = 0;

		try {
			// DB작업
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			// 결과 담기
			while (rs.next()) {
				boardno = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// DB객체 닫기
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// 게시글 번호 반환
		return boardno;

	}
//----------------------------------------------------------------------
	@Override
	public Notice_Board selectNoticeBoardByBoardNo(Notice_Board noticeBoard) {
		// 전체 조회 쿼리
		String sql = "";
		sql += "SELECT * FROM notice_board";
		sql += " WHERE boardno = ?";
		ps = null;
		rs = null;

		Notice_Board notice_Board = new Notice_Board();

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, noticeBoard.getBoardno());
			rs = ps.executeQuery();
			while (rs.next()) {
				notice_Board.setBoardno(rs.getInt("boardno"));
				notice_Board.setCateno(rs.getInt("cateno"));
				notice_Board.setTitle(rs.getString("title"));
				notice_Board.setContent(rs.getString("content"));
				notice_Board.setHit(rs.getInt("hit"));
				notice_Board.setInsert_dat(rs.getDate("insert_dat"));
				notice_Board.setUserid(rs.getString("userid"));
				notice_Board.setRecomend(rs.getInt("recomend"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// DB객체 닫기
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return notice_Board;
	}
//----------------------------------------------------------------------
	@Override
	public String selectNickByBoardno(Notice_Board notice_Board) {
		String sql = "";
		sql += "SELECT M.userid FROM member M, notice_board B";
		sql += " WHERE B.userid=M.userid AND B.boardno=?";
		ps = null;
		rs = null;
		String userid = null;
		// DB작업
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, notice_Board.getBoardno());
			rs = ps.executeQuery();

			while (rs.next()) {

				userid = rs.getString(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// DB객체 닫기
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return userid;
	}

}

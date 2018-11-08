package dao.board.review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.board.Review_Board;
import util.DBConn;
import util.Paging;

public class Review_BoardDaoImpl implements Review_BoardDao {
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	// DB연결 객체
	private Connection conn = DBConn.getConnection();

	@Override
	public List<Review_Board> selectReviewBoardAll() {
		String sql = "";
		sql += "SELECT * FROM review_board";
		sql += " order by boardno desc, insert_dat desc";
		ps = null;
		rs = null;
		List<Review_Board> list = new ArrayList<Review_Board>();
		try {
			// DB작업
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			// 전체조회 결과 담기
			while (rs.next()) {
				Review_Board b = new Review_Board();
				// ResultSet의 결과 행 하나씩 DTO에 저장
				b.setBoardno(rs.getInt("boardno"));
				b.setCateno(rs.getInt("cateno"));
				b.setTitle(rs.getString("title"));
				b.setContent(rs.getString("content"));
				b.setInsert_dat(rs.getDate("insert_dat"));
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

	@Override
	public void insertReviewBoard(Review_Board reviewBoard) {
		String sql = "";
		sql += "INSERT INTO review_board(BOARDNO,CATENO,TITLE,CONTENT,HIT,USERID,RECOMEND)";
		sql += " VALUES(?, ?, ?, ?, 0,?,0)";
		//System.out.println("찍히나요");
		ps = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.setInt(1, reviewBoard.getBoardno());
			ps.setInt(2, reviewBoard.getCateno());
			ps.setString(3, reviewBoard.getTitle());
			ps.setString(4, reviewBoard.getContent());
			ps.setString(5, reviewBoard.getUserid());
			ps.executeQuery();

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
	public void updateReviewBoard(Review_Board reviewBoard) {
		// 다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "UPDATE review_board";
		sql += " SET title = ?,";
		sql += " 	content = ?";
		sql += " WHERE boardno = ?";
		ps = null;

		try {
			conn.setAutoCommit(false);

			// DB작업
			ps = conn.prepareStatement(sql);
			ps.setString(1, reviewBoard.getTitle());
			ps.setString(2, reviewBoard.getContent());
			ps.setInt(3, reviewBoard.getBoardno());

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
	public void deleteReviewBoard(Review_Board reviewBoard) {
		String sql = "DELETE review_board WHERE boardno=?";

		// DB 객체
		ps = null;

		try {
			conn.setAutoCommit(false);

			// DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, reviewBoard.getBoardno());

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
	public Review_Board selectReviewBoardByBoardNo(Review_Board reviewBoard) {
		// 전체 조회 쿼리
		String sql = "";
		sql += "SELECT * FROM review_board";
		sql += " WHERE boardno = ?";
		ps = null;
		rs = null;

		Review_Board review_Board = new Review_Board();

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, reviewBoard.getBoardno());
			rs = ps.executeQuery();
			while (rs.next()) {
				review_Board.setBoardno(rs.getInt("boardno"));
				review_Board.setCateno(rs.getInt("cateno"));
				review_Board.setTitle(rs.getString("title"));
				review_Board.setContent(rs.getString("content"));
				review_Board.setHit(rs.getInt("hit"));
				review_Board.setInsert_dat(rs.getDate("insert_dat"));
				review_Board.setUserid(rs.getString("userid"));
				review_Board.setRecomend(rs.getInt("recomend"));

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

		return review_Board;
	}

	@Override
	public String selectReviewBoardByTitle(Review_Board noticeBoard) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String selectReviewByContent(Review_Board noticeBoard) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String selectReviewByuserId(Review_Board noticeBoard) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectReviewCntAll() {
		// 전체 게시글 수 조회 쿼리
		ps = null;
		rs = null;
		String sql = "";
		sql += "SELECT COUNT(*) FROM review_board";
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

	@Override
	public List<Review_Board> selectReviewPagingList(Paging paging) {
		String sql = "";
		sql += "SELECT * FROM (";
		sql += " SELECT rownum rnum, B.* FROM (";
		sql += " 	SELECT * FROM review_board";
		sql += " 	ORDER BY boardno DESC";
		sql += " ) B";
		sql += " ORDER BY rnum";
		sql += ")";
		sql += "WHERE rnum between ? AND ?";
		ps = null;
		rs = null;
		List<Review_Board> list = new ArrayList<Review_Board>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());
			rs = ps.executeQuery();
			while (rs.next()) {
				Review_Board review_board = new Review_Board();

				// ResultSet의 결과 행 하나씩 DTO에 저장
				review_board.setBoardno(rs.getInt("boardno"));
				review_board.setCateno(rs.getInt("cateno"));
				review_board.setTitle(rs.getString("title"));
				review_board.setContent(rs.getString("content"));
				review_board.setHit(rs.getInt("hit"));
				review_board.setInsert_dat(rs.getDate("insert_dat"));
				review_board.setUserid(rs.getString("userid"));
				review_board.setRecomend(rs.getInt("recomend"));

				// 조회결과를 List로 생성
				list.add(review_board);
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

	@Override
	public int selectReview_Boardno() {
		String sql = "";
		sql += "SELECT review_board_seq.nextval";
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

	@Override
	public void updateHit(Review_Board reviewBoard) {
		// 전체 조회 쿼리
		String sql = "";
		sql += "UPDATE review_board";
		sql += " SET hit = hit+1";
		sql += " WHERE boardno=?";

		ps = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.setInt(1, reviewBoard.getBoardno());
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

	@Override
	public Review_Board selectByComment_No(Review_Board noticeBoard) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String selectNickByBoardno(Review_Board review_Board) {
		String sql = "";
		sql += "SELECT M.userid FROM member M, review_board B";
		sql += " WHERE B.userid=M.userid AND B.boardno=?";
		ps = null;
		rs = null;
		String userid = null;
		// DB작업
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, review_Board.getBoardno());
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

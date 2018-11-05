package dao.file.review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.board.Review_Board;
import dto.file.Review_Filetb;
import util.DBConn;

public class Review_FileDaoImpl implements Review_FileDao {
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	// DB연결 객체
	private Connection conn = DBConn.getConnection();

	@Override
	public void insertFiletb(Review_Filetb review_filetb) {
		String sql = "";
		sql += "INSERT INTO review_filetb(fileno,boardno,file_originname,file_savename)";
		sql += " VALUES (?, ?, ?, ?)";

		ps = null;

		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.setInt(1, review_filetb.getFileno());
			ps.setInt(2, review_filetb.getBoardno());
			ps.setString(3, review_filetb.getFile_OriginName());
			ps.setString(4, review_filetb.getFile_SaveName());

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
	public int deleteFiletbByfileno(int fileno, Review_Board board) {
		String sql = "";
		sql += "DELETE review_filetb WHERE fileno=? AND boardno=?";
		ps = null;
		int cnt = -1;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.setInt(1, fileno);
			ps.setInt(2, board.getBoardno());

			cnt = ps.executeUpdate();

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
		return cnt;
	}

	@Override
	public void deleteFiletbByboardno(Review_Board board) {
		String sql = "";
		sql += "DELETE review_filetb";
		sql += " WHERE boardno=?";

		ps = null;

		try {
			conn.setAutoCommit(false);
			// DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, board.getBoardno());
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
	public int selectFileno() {
		String sql = "";
		sql += "SELECT review_filetb_seq.nextval";
		sql += " FROM dual";

		// DB 객체
		PreparedStatement ps = null;
		ResultSet rs = null;

		// 게시글번호
		int fileno = 0;

		try {
			// DB작업
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			// 결과 담기
			while (rs.next()) {
				fileno = rs.getInt(1);
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
		return fileno;
	}

	@Override
	public List<Review_Filetb> selectFiletbAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Review_Filetb> selectFiletb(Review_Board board) {
		String sql = "";
		sql += "SELECT *FROM review_filetb WHERE boardno=? ORDER by fileno";

		ps = null;
		rs = null;
		List<Review_Filetb> list = new ArrayList<Review_Filetb>();
		// DB작업
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, board.getBoardno());

			rs = ps.executeQuery();

			while (rs.next()) {
				Review_Filetb boardFile = new Review_Filetb();
				boardFile.setFileno(rs.getInt("fileno"));
				boardFile.setBoardno(rs.getInt("boardno"));
				boardFile.setFile_OriginName(rs.getString("FILE_ORIGINNAME"));
				boardFile.setFile_SaveName(rs.getString("file_SaveName"));

				list.add(boardFile);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
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

		return list;
	}

	@Override
	public Review_Filetb selectByFileno(int fileno) {
		String sql = "";
		sql += "SELECT *FROM review_filetb WHERE fileno=? ORDER by fileno";
		ps = null;
		rs = null;
		Review_Filetb boardFile = new Review_Filetb();

		try {
			// DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, fileno);

			rs = ps.executeQuery();

			while (rs.next()) {

				boardFile.setFileno(rs.getInt("fileno"));
				boardFile.setBoardno(rs.getInt("boardno"));
				boardFile.setFile_OriginName(rs.getString("FILE_ORIGINNAME"));
				boardFile.setFile_SaveName(rs.getString("FILE_SAVENAME"));
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

		return boardFile;
	}

	@Override
	public List<Review_Filetb> selectByBoardnolimit() {
		String sql = "";
		sql += "SELECT fileno, boardno,FILE_ORIGINNAME,FILE_SAVENAME FROM(";
		sql += " SELECT fileno, boardno, FILE_ORIGINNAME,FILE_SAVENAME, ROW_NUMBER() OVER(PARTITION BY boardno ORDER BY fileno ASC) as rn";
		sql += " FROM review_filetb) WHERE rn=1 ORDER BY boardno desc";

		ps = null;
		rs = null;
		List<Review_Filetb> list = new ArrayList<Review_Filetb>();

		try {
			// DB작업
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				Review_Filetb boardFile = new Review_Filetb();

				boardFile.setFileno(rs.getInt("fileno"));
				boardFile.setBoardno(rs.getInt("boardno"));
				boardFile.setFile_OriginName(rs.getString("FILE_ORIGINNAME"));
				boardFile.setFile_SaveName(rs.getString("FILE_SAVENAME"));
				list.add(boardFile);
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

		return list;
	}

}

package dao.file.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.board.Notice_Board;
import dto.file.Notice_Filetb;
import util.DBConn;

public class Notice_FileDaoImpl implements Notice_FileDao {

	private PreparedStatement ps = null;
	private ResultSet rs = null;
	// DB연결 객체
	private Connection conn = DBConn.getConnection();
//------------------------------------------------------------------------
	@Override
	public void insertFiletb(Notice_Filetb notice_filetb) {

		String sql = "";
		sql += "INSERT INTO notice_filetb(fileno,boardno,file_originname,file_savename,filesize)";
		sql += " VALUES (notice_filetb_seq.nextval, ?, ?, ?, ?)";
//		System.out.println("진행중"+notice_filetb);

		try {
			conn.setAutoCommit(false);
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, notice_filetb.getBoardno());
			ps.setString(2, notice_filetb.getFile_OriginName());
			ps.setString(3, notice_filetb.getFile_SaveName());
			ps.setLong(4, notice_filetb.getFilesize());

			ps.executeUpdate();

			conn.commit();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// DB객체 닫기
				if (ps != null)	ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
//-----------------------------------------------------------------------
	@Override
	public int deleteFiletbByfileno(int fileno, Notice_Board board) {
		String sql = "";
		sql += "DELETE notice_filetb WHERE fileno=? AND boardno=?";
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
	public List<Notice_Filetb> selectFiletbAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectFileno() {
		// 다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "SELECT notice_filetb_seq.nextval";
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
//----------------------------------------------------------------------------
	@Override
	public Notice_Filetb selectFiletb(Notice_Board board) {
		String sql = "";
		sql += "SELECT * FROM notice_filetb WHERE boardno=? ORDER by fileno";

		Notice_Filetb notice_filetb = new Notice_Filetb();
		// DB작업
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, board.getBoardno());

			rs = ps.executeQuery();

			while (rs.next()) {
//				Notice_Filetb boardFile = new Notice_Filetb();
				notice_filetb.setFileno(rs.getInt("fileno"));
				notice_filetb.setBoardno(rs.getInt("boardno"));
				notice_filetb.setFile_OriginName(rs.getString("FILE_ORIGINNAME"));
				notice_filetb.setFile_SaveName(rs.getString("file_SaveName"));

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

		return notice_filetb;
	}
//------------------------------------------------------------------------------------------
	@Override
	public Notice_Filetb selectByFileno(int fileno) {
		String sql = "";
		sql += "SELECT * FROM notice_filetb WHERE fileno = ? ORDER by fileno";
		
		Notice_Filetb notice_filetb = new Notice_Filetb();

		try {
			// DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, fileno);

			rs = ps.executeQuery();

			while (rs.next()) {

				notice_filetb.setFileno(rs.getInt("fileno"));
				notice_filetb.setBoardno(rs.getInt("boardno"));
				notice_filetb.setFile_OriginName(rs.getString("FILE_ORIGINNAME"));
				notice_filetb.setFile_SaveName(rs.getString("FILE_SAVENAME"));
				notice_filetb.setFilesize(rs.getLong("filesize"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// DB객체 닫기
				if (ps != null)		ps.close();
				if (rs != null)		rs.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return notice_filetb;
	}
//--------------------------------------------------------------------
	@Override
	public void deleteFiletbByboardno(Notice_Board board) {

		String sql = "";
		sql += "DELETE notice_filetb";
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

}

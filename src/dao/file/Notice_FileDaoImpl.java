package dao.file;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dto.file.Notice_Filetb;
import util.DBConn;

public class Notice_FileDaoImpl implements Notice_FileDao {

	private PreparedStatement ps = null;
	private ResultSet rs = null;
	// DB연결 객체
	private Connection conn = DBConn.getConnection();

	@Override
	public void insertFiletb(Notice_Filetb notice_filetb) {

		String sql = "";
		sql += "INSERT INTO notice_filetb(fileno,boardno,file_originname,file_savename)";
		sql += " VALUES (?, ?, ?, ?)";

		ps = null;

		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.setInt(1, notice_filetb.getFileno());
			ps.setInt(2, notice_filetb.getBoardno());
			ps.setString(3, notice_filetb.getFile_OriginName());
			ps.setString(4, notice_filetb.getFile_SaveName());

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
	public void deleteFiletbByfileno(Notice_Filetb notice_filetb) {
		// TODO Auto-generated method stub

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

}

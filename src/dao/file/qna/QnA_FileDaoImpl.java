package dao.file.qna;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.board.QnA;
import dto.file.QnA_Filetb;
import util.DBConn;

public class QnA_FileDaoImpl implements QnA_FileDao {

	private QnA_Filetb QnA_file = new QnA_Filetb();
	private Connection conn = DBConn.getConnection(); 
	
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	@Override
	public void insertFile(QnA_Filetb qnafile) throws Exception{
		
		String sql = "";
		sql += "INSERT INTO qna_filetb(fileno,boardno,originname,storedname,filesize) ";
		sql += " VALUES (boardfile_seq.nextval, ?, ?, ?, ?)";
		
		try {
			conn.setAutoCommit(false);
			
			ps = conn.prepareStatement(sql);
			
			
		
			ps.setInt(1, qnafile.getBoardno());
			ps.setString(2, qnafile.getOriginName());
			ps.setString(3, qnafile.getStoredName());
			ps.setLong(4, qnafile.getFilesize());
			
			ps.executeUpdate();
			
			conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		}finally {
			
			try {
				if(ps != null) ps.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public QnA_Filetb selectFile(QnA qna) throws Exception{
	
		String sql = "";
		sql += "SELECT * FROM qna_filetb";
		sql += " WHERE boardno = ?";
		sql += " ORDER BY fileno";
		
		QnA_Filetb qna_File = new QnA_Filetb();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, qna.getBoardno());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				qna_File.setFileno(rs.getInt("fileno"));
				qna_File.setBoardno(rs.getInt("boardno"));
				qna_File.setOriginName(rs.getString("orignname"));
				qna_File.setStoredName(rs.getString("storedname"));
				qna_File.setFilesize(rs.getLong("filesize"));
				qna_File.setInsert_dat(rs.getDate("insert_dat"));
			}
			
			
		
		} catch (SQLException e) {
			e.printStackTrace();
		
		}finally {
			try {
				if(ps != null) ps.close();
							
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return qna_File;
	}

	@Override
	public QnA_Filetb selectByFileno(int fileno) {
		
		String sql = "";
		sql += "SELECT * FROM qna_filetb";
		sql += " WHERE fileno = ?";
		sql += " ORDER BY fileno";
		
		QnA_Filetb qna_File = new QnA_Filetb();
		
		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, fileno);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				qna_File.setFileno(rs.getInt("fileno"));
				qna_File.setBoardno(rs.getInt("boardno"));
				qna_File.setOriginName(rs.getString("orignname"));
				qna_File.setStoredName(rs.getString("storedname"));
				qna_File.setFilesize(rs.getLong("filesize"));
				qna_File.setInsert_dat(rs.getDate("insert_dat"));
			}
		
		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			try {
				if( rs != null) rs.close();
				if( ps != null) ps.close();
				
				
			} catch (SQLException e) {
			e.printStackTrace();
			}
		}
		
		
		return qna_File;
	}

	@Override
	public int delete(QnA qna) throws Exception {
		
		String sql = "";
		sql += "DELETE qna_filetb";
		sql += " WHERE boardno = ?";
		
		
		try {
			conn.setAutoCommit(false);
	
			ps=conn.prepareStatement(sql);
			ps.setInt(1,  qna.getBoardno());
			ps.executeUpdate();
			
			conn.commit();
		
		
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		}finally {
			try {
				
				if (ps != null) ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 1;
		
	}

	@Override
	public void deletedeleteBoardListFile(String names) {
		String sql = "DELETE FROM qna_filetb WHERE boardno IN ( "+names+" )";
		
		try {
			ps= conn.prepareStatement(sql);
			
			ps.executeUpdate();
		
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

	
	
}

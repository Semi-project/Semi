package dao.file;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dto.file.Free_Filetb;
import util.DBConn;

public class Free_FileDaoImpl implements Free_FileDao {
	private Connection conn=DBConn.getConnection();
	private PreparedStatement ps =null;
	private ResultSet rs=null;
	private Free_Filetb free_file = new Free_Filetb();

	
	@Override
	public void insertFiletb(Free_Filetb free_filetb) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateFiletbByfileno(Free_Filetb free_filetb) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteFiletbByfileno(Free_Filetb free_filetb) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Free_Filetb> selectFiletbAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Free_Filetb selectFile(int freefileno) {
		//다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "SELECT * FROM boardfile";
		sql += " WHERE fileno = ?";
		sql += " ORDER BY fileno";
		
		Free_Filetb freeboardfile = new Free_Filetb();
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, freefileno);

			rs = ps.executeQuery();
			
			while(rs.next()) {
			
				freeboardfile.setFileno( rs.getInt("fileno") );
				freeboardfile.setBoardno( rs.getInt("boardno") );
				freeboardfile.setFile_OriginName( rs.getString("file_OriginName") );
				freeboardfile.setFile_SaveName( rs.getString("file_SaveName;") );
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				//DB객체 닫기
				if(ps!=null)	ps.close();
				if(rs!=null)	rs.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return freeboardfile;
	}
	
	}
	



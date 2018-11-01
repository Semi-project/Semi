package dao.file.free;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dto.board.Free_Board;
import dto.file.Free_Filetb;
import util.DBConn;

public class Free_FileDaoImpl implements Free_FileDao {
	private Connection conn=DBConn.getConnection();
	private PreparedStatement ps =null;
	private ResultSet rs=null;
	

	


	@Override
	public Free_Filetb selectByfileno(int freefileno) {
		//다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "SELECT * FROM free_filetb";
		sql += " WHERE fileno = ?";
		sql += " ORDER BY fileno";
		System.out.println("1"+freefileno);
		
		Free_Filetb freeboardfile = new Free_Filetb();
//		System.out.println("2"+freeboardfile);
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, freefileno);
//			System.out.println("3"+freefileno);
			rs = ps.executeQuery();
//			System.out.println("4");
			while(rs.next()) {
			
				freeboardfile.setFileno( rs.getInt("fileno") );
				freeboardfile.setBoardno( rs.getInt("boardno") );
				freeboardfile.setFile_OriginName( rs.getString("file_OriginName") );
				freeboardfile.setFile_SaveName( rs.getString("file_SaveName") );
				freeboardfile.setFilesize(rs.getLong("filesize"));
//				 System.out.println("5"+freeboardfile);
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
		
//		System.out.println("6"+freeboardfile);
		return freeboardfile;
	}




	@Override
	public void insertFile(Free_Filetb freefiletb) {
		String sql = "INSERT INTO FREE_FILETB(FILENO,BOARDNO,FILE_ORIGINNAME,FILE_SAVENAME,FileSize)VALUES(FREE_FILETB_SEQ.nextval,?,?,?,?)";
		System.out.println(freefiletb);
		
			try {
				conn.setAutoCommit(false);
				
				ps=conn.prepareStatement(sql);
				ps.setInt(1, freefiletb.getBoardno());
				ps.setString(2, freefiletb.getFile_OriginName());
				ps.setString(3, freefiletb.getFile_SaveName());
				ps.setLong(4, freefiletb.getFilesize());
			
			
				ps.executeUpdate();
				conn.commit();
				
			} catch (SQLException e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
				e.printStackTrace();
			}finally {
				try {
					//DB객체 닫기
					if(ps!=null)	ps.close();
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
	}

	}


	@Override
	public Free_Filetb selectFile(Free_Board freeboard) {
		String sql="";
		sql += "SELECT * FROM Free_Filetb";
		sql += " WHERE boardno = ?";
		sql += " ORDER BY fileno";
		Free_Filetb free_file = new Free_Filetb();
		try {
			ps= conn.prepareStatement(sql);
			ps.setInt(1, freeboard.getBoardno());
//			System.out.println("f1"+free_file);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				
				free_file.setFileno(rs.getInt("fileno"));
				free_file.setBoardno(rs.getInt("boardno"));
				free_file.setFile_OriginName(rs.getString("file_OriginName"));
				free_file.setFile_SaveName(rs.getString("file_savename"));
				free_file.setFilesize(rs.getLong("filesize"));
				
//				System.out.println("f2"+free_file);
			}
		
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			try {
				//DB객체 닫기
				if(ps!=null)	ps.close();
				if(rs!=null)	rs.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
//		System.out.println("f3"+free_file);
		return free_file;
	}




	@Override
	public void delete(Free_Board freeboard) {
	
			//다음 게시글 번호 조회 쿼리
			String sql ="DELETE free_filetb WHERE boardno=?";
			
			//DB 객체
			PreparedStatement ps = null; 
			
			try {
				conn.setAutoCommit(false);
				//DB작업
				ps = conn.prepareStatement(sql);
				ps.setInt(1, freeboard.getBoardno());
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
					//DB객체 닫기
					if(ps!=null)	ps.close();
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	

		
	}
	
	
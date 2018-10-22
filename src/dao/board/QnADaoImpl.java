package dao.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dto.board.QnA;
import util.DBConn;
import util.Paging;

public class QnADaoImpl implements QnADao{
	
	//DB���� ��ü
		private Connection conn = DBConn.getConnection(); 
		private PreparedStatement ps = null;
		private ResultSet rs = null;
		
	// qna ��ü ��ȸ
	@Override
	public List<QnA> selectQnA() {
		
		
		String sql = "";
		sql += "SELECT * FROM QnA";
		sql += " ORDER BY boardno DESC";
		
		List<QnA> list = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			
			while(rs.next()) {
				QnA qna = new QnA();
				
				qna.setCateno(rs.getInt("cateno"));
				qna.setUserid(rs.getString("userid"));
				qna.setBoardno(rs.getInt("boardno"));
				qna.setTitle(rs.getString("title"));
				qna.setInsert_Dat(rs.getDate("insert_dat"));
				qna.setContent(rs.getString("content"));
			
				
				list.add(qna);
				
			}
		
		} catch (SQLException e) {
	
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
		return list;
	}
	// �۾���
	@Override
	public void insertQnA(QnA qna) throws Exception {
		System.out.println(qna);
		String sql ="";
		sql +="INSERT INTO QNA (CATENO , USERID , BOARDNO "
				+ ", TITLE ,CONTENT )";
		sql +=" VALUES(?,?,?,?,?)";
		
		try {
			ps= conn.prepareStatement(sql);
		
			ps.setInt(1, 1002);
			ps.setString(2, qna.getUserid());
			ps.setInt(3, qna.getBoardno());
			ps.setString(4, qna.getTitle());
			/*ps.setDate(5, (java.sql.Date) qna.getInsert_Dat());*/
			ps.setString(5, qna.getContent());
		
			
			ps.executeQuery();
			conn.commit();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		}	finally {
				try {
					if(ps!=null) ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	

	@Override
	// QnA �о����
	public QnA selectQnAByBoardno(int boardNo) {
		String sql ="";
		sql += "SELECT * FROM QnA";
		sql += " WHERE boardno=?";
		
		
		
		QnA q = new QnA();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, boardNo);
			rs= ps.executeQuery();
			
			while(rs.next()) {
				
				q.setCateno(rs.getInt("cateno"));
				q.setUserid(rs.getString("userid"));
				q.setBoardno(rs.getInt("boardno"));
				q.setTitle(rs.getString("title"));
				q.setInsert_Dat(rs.getDate("insert_dat"));
				q.setContent(rs.getString("content"));
			
				
				
			}
		
		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
				
				
		return q;
	}
	// QnA �ۼ���
	@Override
	public void updateQnA(QnA qna) throws SQLException {
		
		String sql = "";
		sql += "UPDATE QnA";
		sql += " SET title=?,";
		sql += "   content = ?";
		sql += " WHERE boardno= ?";
		sql += "   AND userid = ?";
		
		
		
		try {
			conn.setAutoCommit(false);
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, qna.getTitle());
			ps.setString(2, qna.getContent());
			ps.setInt(3, qna.getBoardno());
			ps.setString(4, qna.getUserid());
			ps.executeQuery();
			
			conn.commit();
			
		} catch (SQLException e) {
	
			e.printStackTrace();
			conn.rollback();
		}
		finally {
			try {
				if(ps!=null) ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
	}
	// QnA �ۻ���
	@Override
	public int deleteQnA(QnA qna) throws SQLException {
		
		String sql = "";
		sql += "DELETE QnA";
		sql += " WHERE boardno = ?";
		sql += "   AND userid = ?";
		
		try {
			conn.setAutoCommit(false);
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, qna.getBoardno());
			ps.setString(2, qna.getUserid());
			
			ps.executeQuery();
			
			conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		
		}finally {	
			try {
				if(ps!=null) ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 1;
		
	}


	// QnA ����¡ ó��
	@Override
	public List<QnA> selectQnAPagingList(Paging paging) {
		
		//����¡ ����Ʈ ��ȸ ����
		String sql = "";
		sql += "SELECT * FROM (";
		sql += " SELECT rownum rnum, B.* FROM (";
		sql += " 	SELECT * FROM qna";
		sql += " 	ORDER BY boardno DESC";
		sql += " ) B";
		sql += " ORDER BY rnum";
		sql += ")";
		sql += "WHERE rnum between ? AND ?";
		
		List<QnA> list = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1,  paging.getStartNo());
			ps.setInt(2, paging.getEndNo());
			
			rs = ps.executeQuery();
			
	
			while(rs.next()) {
				
				QnA q = new QnA();
				
				q.setCateno(rs.getInt("cateno"));
				q.setUserid(rs.getString("userid"));
				q.setBoardno(rs.getInt("boardno"));
				q.setTitle(rs.getString("title"));
				q.setInsert_Dat(rs.getDate("insert_dat"));
				q.setContent(rs.getString("content"));
				
				
				list.add(q);
				
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return list;
	}
	//
	@Override
	public int selectQnACntAll() {
		String sql = "";
		sql += "SELECT COUNT(*) FROM QnA";
		
		int cnt = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			rs.next();
			
			cnt = rs.getInt(1);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return cnt;
	}
	
	// �������� ��ã��
	
	@Override
	public QnA selectQnABytitle(QnA qna) {
		
		String sql= "";
		sql +="SELECT * FROM QnA";
		sql += " WHERE title=?";
		
		QnA q = new QnA();
		
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(4, qna.getTitle());
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				
				q.setCateno(rs.getInt("cateno"));
				q.setUserid(rs.getString("userid"));
				q.setBoardno(rs.getInt("boardno"));
				q.setTitle(rs.getString("title"));
				q.setInsert_Dat(rs.getDate("insert_dat"));
				q.setContent(rs.getString("content"));
				
			}
			
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return q;
	}
	// �������� ��ã��
	@Override
	public QnA selectQnABycontent(QnA qna) {
		
		String sql ="";
		sql += "SELECT * FROM QnA";
		sql += " WHERE content=?";
		
		QnA q = new QnA();
		
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(6, qna.getContent());
			rs=ps.executeQuery();
			
		while(rs.next()) {
				
				
				q.setCateno(rs.getInt("cateno"));
				q.setUserid(rs.getString("userid"));
				q.setBoardno(rs.getInt("boardno"));
				q.setTitle(rs.getString("title"));
				q.setInsert_Dat(rs.getDate("insert_dat"));
				q.setContent(rs.getString("content"));
				
			}
			
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return q;
	}

	@Override
	public QnA selectByuserid(QnA qna) {
		String sql ="";
		sql += "SELECT * FROM QnA";
		sql += " WHERE userid=?";
		
		QnA q = new QnA();
		
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(2, qna.getUserid());
			rs=ps.executeQuery();
			
		while(rs.next()) {
				
				
				q.setCateno(rs.getInt("cateno"));
				q.setUserid(rs.getString("userid"));
				q.setBoardno(rs.getInt("boardno"));
				q.setTitle(rs.getString("title"));
				q.setInsert_Dat(rs.getDate("insert_dat"));
				q.setContent(rs.getString("content"));
				
			}
			
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return q;
	}

	@Override
	public QnA selectByComment_no(QnA qna) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int selectBoardno() {
		
		//���� �Խñ� ��ȣ ��ȸ ����
				String sql = "";
				sql += "SELECT QnA_seq.nextval";
				sql += " FROM dual";
				
				int boardno = 0;
				
				try {
					ps = conn.prepareStatement(sql);
					rs = ps.executeQuery();
					
					while(rs.next()) {
						boardno = rs.getInt(1);
						
					}
				
				} catch (SQLException e) {
				
					e.printStackTrace();
				}finally {
					try {
						if(rs != null) rs.close();
						if(ps != null) ps.close();
						
					} catch (SQLException e) {
					e.printStackTrace();
					}
				}
				
				
		return boardno;
	}

}

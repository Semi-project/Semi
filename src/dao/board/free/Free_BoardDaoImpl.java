package dao.board.free;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.board.Free_Board;
import dto.board.Free_Board_param;
import util.DBConn;
import util.Paging;

public class Free_BoardDaoImpl implements Free_BoardDao {

	private Connection conn=DBConn.getConnection();
	private PreparedStatement ps =null;
	private ResultSet rs=null;

	@Override
	public List<Free_Board> selectFreeBoard(Free_Board_param fbp) {
		
		
		//String sql="SELECT * FROM free_board ORDER BY boardno DESC";
		String sql="";
		sql += "SELECT boardno, cateno,TITLE,CONTENT,UPDATE_DAT,HIT,USERID,RECOMEND,INSERT_DAT";
		sql +=	"   FROM";
		sql +=	"   (SELECT ROW_NUMBER() OVER(ORDER BY boardno desc)AS RNUM,";
		sql +=	"      boardno, cateno,TITLE,CONTENT,UPDATE_DAT,HIT,USERID,RECOMEND,INSERT_DAT";  
		sql +=	"      FROM free_board"; 
		if( fbp.getNamesearch()!=null && !"".equals(fbp.getNamesearch()) ) {
			sql+= " WHERE title LIKE '%"+fbp.getNamesearch()+"%'";
		}
		sql +=	"      "; 
		sql +=	"   ) A"; 
		sql +=		"   WHERE rnum BETWEEN "+(1+(fbp.getPageNumber()*10))+" AND "+(10+(fbp.getPageNumber()*10));
		
//		if( fbp.getNamesearch()!=null && !"".equals(fbp.getNamesearch()) ) {
//				sql+= " WHERE title LIKE '%"+fbp.getNamesearch()+"%'";
//			}
		
			List <Free_Board>list= new ArrayList<Free_Board>();

		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();

			while(rs.next()) {
				Free_Board b = new Free_Board();
				b.setBoardno(rs.getInt("boardno"));
				b.setCateno(rs.getInt("cateno"));
				b.setContent(rs.getString("content"));
				b.setHit(rs.getInt("hit"));
				b.setInsert_Dat(rs.getDate("insert_Dat"));
				b.setRecommend(rs.getInt("recomend"));
				b.setTitle(rs.getString("title"));
				b.setUpdate_Dat(rs.getDate("update_Dat"));
				b.setUserid(rs.getString("userid"));

				list.add(b);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)    rs.close();
				if(ps!=null)	ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		
		return list;
		
	}

	@Override
	public void insertFreeBoard(Free_Board freeBoard) {
		String sql = "";
		sql += "INSERT INTO free_board(BOARDNO,TITLE,userid,CONTENT,CATENO,RECOMEND,HIT) ";
		sql += " VALUES (free_board_seq.currval, ?, ?, ?,1000,1,0)";
		
		try {
			conn.setAutoCommit(false);
			
			//DB작업
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, freeBoard.getTitle());
			ps.setString(2, freeBoard.getUserid());
			ps.setString(3, freeBoard.getContent());

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

	

	@Override
	public Free_Board viewFreeBoard(Free_Board freeBoard) {
		//System.out.println(freeBoard.toString());
		String sql="SELECT * FROM free_board WHERE boardno=?";
		
		Free_Board fb= null;
		
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, freeBoard.getBoardno());
			rs=ps.executeQuery();
			
			while(rs.next()) {
				
				fb = new Free_Board(
						rs.getInt("boardno"),
						rs.getInt("cateno"), // 게시판코드
						rs.getString("title"),  // 글제목
						rs.getString("content"), // 글내용
						rs.getDate("insert_Dat"), //작성일
						rs.getDate("update_Dat"), // 수정일
						rs.getInt("hit"), //조회수
						rs.getString("userid"), //작성자
						rs.getInt("recomend")
				);
				
		
				
			}
			
		} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					//DB객체 닫기
					if(rs!=null)	rs.close();
					if(ps!=null)	ps.close();
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
//		System.out.println("최종"+fb);
		return fb;
	}

	@Override
	public void updateFreeBoard(Free_Board freeBoard) {
	System.out.println("업데이트"+freeBoard);

	String sql="Update free_board SET title =?,content =? WHERE boardno=?";

	try {
		conn.setAutoCommit(false);
		ps=conn.prepareStatement(sql);
		ps.setString(1, freeBoard.getTitle());
		ps.setString(2, freeBoard.getContent());
		ps.setInt(3, freeBoard.getBoardno());
		
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
	@Override
	public void deleteFreeBoard(Free_Board freeBoard) {
		String sql="Delete free_board where boardno=?";
		String name=null;
		
		try {
			conn.setAutoCommit(false);
			
			ps=conn.prepareStatement(sql);
			ps.setInt(1, freeBoard.getBoardno());
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
		};

	}

	@Override
	public String selectFreeBoardByTitle(Free_Board freeBoard) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String selectFreeBoardByContent(Free_Board freeBoard) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String selectFreeBoardByuserId(Free_Board freeboard) {
		String sql="select * from free_board where='?'";
		Free_Board fb = new Free_Board();
	
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, freeboard.getUserid());
			rs =ps.executeQuery();
			
			while(rs.next()) {
				fb.setBoardno(rs.getInt("boardno"));
				fb.setCateno(rs.getInt("cateno"));
				fb.setContent(rs.getString("content"));
				fb.setHit(rs.getInt("hit"));
				fb.setInsert_Dat(rs.getDate("insert_Dat"));
				fb.setRecommend(rs.getInt("recomend"));
				fb.setTitle(rs.getString("title"));
				fb.setUpdate_Dat(rs.getDate("update_Dat"));
				fb.setUserid(rs.getString("userid"));

				
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)    rs.close();
				if(ps!=null)	ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	

		return null;
	}
		
	
	

	@Override
	public int selecntFreeBoardCntAll() {
		String sql="select count(*) from free_board ";
		
		int cnt=0;
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			rs.next();
			
			cnt=rs.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps!=null)	ps.close();
				if(rs!=null)	rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return cnt;
	}

	@Override
	public List<Free_Board> selectFreeBoardPagingList(Paging paging) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateHit(Free_Board freeBoard) {
		String sql="UPDATE free_board"
				+ " SET hit = hit+1"
				+ " WHERE boardno=?";
		
		
		try {
			conn.setAutoCommit(false);
			
			ps=conn.prepareStatement(sql);
			ps.setInt(1, freeBoard.getBoardno());
			ps.executeUpdate();
			
			
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
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
	public void updateRecommend(Free_Board freeBoard) {
		// TODO Auto-generated method stub

	}

	@Override
	public Free_Board selectByComment_No(Free_Board freeBoard) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectFreeboardno() {
		//다음 게시글 번호 조회 쿼리
				String sql = "";
				sql += "SELECT free_board_seq.nextval";
				sql += " FROM dual";

				//게시글번호
				int boardno = 0;
				
				try {
					//DB작업
					ps = conn.prepareStatement(sql);
					rs = ps.executeQuery();
					
					//결과 담기
					while(rs.next()) {
						boardno = rs.getInt(1);
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					try {
						//DB객체 닫기
						if(rs!=null)	rs.close();
						if(ps!=null)	ps.close();
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
				//게시글 번호 반환
				return boardno;
			}

	@Override
	public String selectUseridByBoardno(Free_Board freeboard) {
		System.out.println("유저아이디 프리보드"+freeboard);
		String sql="select Userid from free_board where boardno=?";
		String userid=null;
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, freeboard.getBoardno());
			rs=ps.executeQuery();
			while(rs.next()) {
			userid=rs.getString(1);
			
			System.out.println("유저아이디"+userid);
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
		
		return userid;
	}
	

}

package dao.comment.qna;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dto.board.QnA;
import dto.comment.QnA_Comments;
import util.DBConn;

public class QnA_CommentDaoImpl implements QnA_CommentDao {

   private Connection conn = DBConn.getConnection();
   
   private PreparedStatement ps = null;
   private ResultSet rs = null;
   
   @Override
   public void insert(QnA_Comments qna_Comment) throws Exception {
      String sql =" INSERT INTO QnA_COMMENTS ("
               + "    comment_no,"
               + "    userid,"
               + "    content,"
               + "      insert_dat,"   
               + "    boardno)"
               + " VALUES ("
               + "      qna_comments_seq.nextval,"
               + "      ?,"
               + "      ?,"
               + "     ?,"
               + "      ? )";
      
      try {
         ps = conn.prepareStatement(sql);
         
         ps.setInt(1, qna_Comment.getCommentNo());
         ps.setString(2, qna_Comment.getUserid());
         ps.setString(3, qna_Comment.getContent());
         ps.setDate(4, new java.sql.Date(new Date().getTime()));
         ps.setInt(5, qna_Comment.getBoardno());
      
         
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
      

   }

   @Override
   public List selectQnA_Comments(int boardno) {
      String sql
      = "SELECT * FROM ("
            + "SELECT rownum rnum, B.* FROM ("
            + "   SELECT"
            + "      comment_no,"
            + "      userid,"
            + "      content,"
            + "      insert_dat,"
            + "      boardno"
            + "   FROM qna_comments"
            + "   WHERE boardno = ?"
            + "   ORDER BY insert_dat"
            + "   ) B"
            + ") ORDER BY rnum DESC";
      
         List commentList = new ArrayList<>();
         
         QnA qna = new QnA();
         
         try {
            ps = conn.prepareStatement(sql);
            
            ps.setInt(1,  qna.getBoardno());
            
            rs = ps.executeQuery();
            
            while(rs.next()) {
               
               QnA_Comments qna_Comment = new QnA_Comments();
               
               qna_Comment.setRnum(rs.getInt("rnum"));
               qna_Comment.setCommentNo(rs.getInt("commentno"));
               qna_Comment.setUserid(rs.getString("userid"));
               qna_Comment.setContent(rs.getString("content"));
               qna_Comment.setInsertDat(rs.getDate("insert_dat"));
               qna_Comment.setBoardno(rs.getInt("boardno"));
            
               commentList.add(qna_Comment);
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
         
      return commentList;
   }

   
   @Override
   public int selectQnA_CommentCntAll(QnA_Comments qna_Comment) {
      
      String sql = "SELECT COUNT(*) FROM qna_comments WHERE commentbo=?";
      
      int cnt = 0;
      
      try {
         ps = conn.prepareStatement(sql);
         ps.setInt(1, qna_Comment.getCommentNo());
         rs = ps.executeQuery();
         rs.next();
         cnt=rs.getInt(1);
      
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
      
      
      return cnt;
   }

   @Override
   public void deleteQnA_Comment(QnA_Comments qna_Comment) throws Exception {
      
      String sql ="DELETE qna_comments"
            + "   WHERE commentno = ?";
      
      try {
         ps = conn.prepareStatement(sql);
      
         ps.setInt(1,  qna_Comment.getCommentNo());
         
         ps.executeQuery();
         
         conn.commit();
      
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

   @Override
   public void updateQnA_CommentByUserId(QnA_Comments comment) {
      // TODO Auto-generated method stub

   }

   

}
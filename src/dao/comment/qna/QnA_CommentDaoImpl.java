package dao.comment.qna;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import dto.board.QnA;
import dto.comment.QnA_Comments;
import util.DBConn;

public class QnA_CommentDaoImpl implements QnA_CommentDao {

   private Connection conn = DBConn.getConnection();
   
   private PreparedStatement ps = null;
   private ResultSet rs = null;
   
   @Override
   public void insert(QnA_Comments qna_Comment) throws Exception {
         
   String sql = "INSERT INTO QnA_COMMENTS ("   
            + "   comment_no,"
            + "   userid,"
            + "   content,"   
            + "   boardno)"
            + "   VALUES ("
            + "   qna_comments_seq.nextval,"
            + " ?,"
            + " ?,"
            + "   ?)";
      
      
      
   
      try {
         ps = conn.prepareStatement(sql);
         
      
         ps.setString(1, qna_Comment.getUserid());
         ps.setString(2, qna_Comment.getContent());
         ps.setInt(3, qna_Comment.getBoardno());
      
         
         ps.executeQuery();
         
         conn.commit();
      
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         try {
            if(ps!=null) ps.close();
            
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      

   }

   @Override
   public List selectQnA_Comments(QnA qna) {
      String sql
      = "SELECT * FROM ("
            + "SELECT rownum rnum, B.* FROM ("
            + "   SELECT"
            + "      comment_no,"
            + "      userid,"
            + "      content,"
            + "      to_char(insert_dat, 'yyyy-mm-dd hh24:mi:ss') insertdat,"
            + "      boardno"
            + "   FROM qna_comments"
            + "   WHERE boardno = ?"
            + "   ORDER BY insert_dat"
            + "   ) B"
            + ") ORDER BY rnum DESC";
      
         List commentList = new ArrayList<>();
         
   
         
         try {
            ps = conn.prepareStatement(sql);
            
            ps.setInt(1,  qna.getBoardno());
            
            rs = ps.executeQuery();
            
            while(rs.next()) {
               
               QnA_Comments qna_Comment = new QnA_Comments();
               SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
               qna_Comment.setRnum(rs.getInt("rnum"));
               qna_Comment.setComment_No(rs.getInt("comment_no"));
               qna_Comment.setUserid(rs.getString("userid"));
               qna_Comment.setContent(rs.getString("content"));
               qna_Comment.setInsertDat(format.parse(rs.getString("insertdat")));
               qna_Comment.setBoardno(rs.getInt("boardno"));
            
               commentList.add(qna_Comment);
            }
         
         } catch (SQLException e) {
            e.printStackTrace();
         } catch (ParseException e) {
            // TODO Auto-generated catch block
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
         ps.setInt(1, qna_Comment.getComment_No());
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
   public void updateQnA_CommentByUserId(QnA_Comments qna_Comment) {
      // TODO Auto-generated method stub
      
   }

   
   @Override
   public String deleteQnA_Comment(QnA_Comments qna_Comment) throws Exception {
      String result = "";
      
      String sql ="DELETE qna_comments"
            + "   WHERE comment_no = ?";
      
      try {
         ps = conn.prepareStatement(sql);
      
         ps.setInt(1,  qna_Comment.getComment_No());
         
         ps.executeQuery();
         
         conn.commit();
         result = "success";
      } catch (SQLException e) {
         e.printStackTrace();
         conn.rollback();
         result = "fail";
      }finally {
         try {
            if(ps != null) ps.close();
            
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      return result;
   }
}
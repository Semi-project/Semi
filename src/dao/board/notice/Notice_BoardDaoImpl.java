package dao.board.notice;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.board.Notice_Board;
import util.DBConn;
import util.Paging;

public class Notice_BoardDaoImpl implements Notice_BoardDao {

   private Connection conn = DBConn.getConnection();
   
   PreparedStatement ps = null;
   ResultSet rs = null;
   
   @Override
   public List<Notice_Board> selectNoticeBoard() {

      String sql = "";
      sql += " SELECT * FROM notice_board";
      sql += " ORDER BY boardno DESC";
      
      List list = new ArrayList<>();
      
      try {
         ps = conn.prepareStatement(sql);
         rs = ps.executeQuery();
         
         while(rs.next()) {
            Notice_Board n = new Notice_Board();
            
            n.setBoardno(rs.getInt("boardno"));
            n.setCateno(rs.getInt("cateno"));
            n.setTitle(rs.getString("title"));
            n.setContent(rs.getString("content"));
            n.setInsert_dat(rs.getDate("insert_dat"));
            n.setUpdate_dat(rs.getDate("update_dat"));
            n.setHit(rs.getInt("hit"));
            n.setUserid(rs.getString("userid"));
            n.setRecomend(rs.getInt("recomend"));
            
            list.add(n);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         try {
            if(ps!=null)   ps.close();
            if(rs!=null)   rs.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      return list;
   }
//---------------------------------------------------------------
   @Override
   public void insertNoticeBoard(Notice_Board noticeBoard) {

      String sql = "";
      sql += " INSERT INTO notice_board(BOARDNO,CATENO,TITLE,CONTENT,HIT,USERID,RECOMEND)";
      sql += " VALUES (?, ?, ?, ?, 0, ?, ? )";
      
      try {
         conn.setAutoCommit(false);
         
         ps = conn.prepareStatement(sql);
         ps.setInt(1, noticeBoard.getBoardno());
         ps.setInt(2, noticeBoard.getCateno());
         ps.setString(3, noticeBoard.getTitle());
         ps.setString(4, noticeBoard.getContent());
         ps.setInt(5, noticeBoard.getHit());
         ps.setString(6, noticeBoard.getUserid());
         ps.setInt(7, noticeBoard.getRecomend());
         
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
         if(ps!=null)   ps.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
   }
//---------------------------------------------------------------
   @Override
   public Notice_Board viewNoticeBoard(Notice_Board noticeBoard) {
      
      String sql = "";
      sql += " SELECT * FROM notice_board";
      sql += " WHERE boardno = ?";
      
      Notice_Board n = new Notice_Board();
      
      try {
         ps = conn.prepareStatement(sql);
         ps.setInt(1, noticeBoard.getBoardno());
         rs = ps.executeQuery();
         
         while(rs.next()) {
            
            n.setBoardno(rs.getInt("boardno"));
            n.setCateno(rs.getInt("cateno"));
            n.setTitle(rs.getString("title"));
            n.setContent(rs.getString("content"));
            n.setInsert_dat(rs.getDate("insert_dat"));
            n.setUpdate_dat(rs.getDate("update_dat"));
            n.setHit(rs.getInt("hit"));
            n.setUserid(rs.getString("userid"));
            n.setRecomend(rs.getInt("recomend"));
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         try {
            if(rs!=null)   rs.close();
            if(ps!=null)   ps.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      
      return n;

   }

   @Override
   public void updateNoticeBoard(Notice_Board noticeBoard) {
      // TODO Auto-generated method stub

   }

   @Override
   public void deleteNoticeBoard(Notice_Board noticeBoard) {
      // TODO Auto-generated method stub

   }

   @Override
   public String selectNoticeBoardByTitle(Notice_Board noticeBoard) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public String selectNoticeBoardByContent(Notice_Board noticeBoard) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public String selectNoticeBoardByuserId(Notice_Board noticeBoard) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public int selectNoticeBoardCntAll() {
      // TODO Auto-generated method stub
      return 0;
   }

   @Override
   public List<Notice_Board> selectNoticeBoardPagingList(Paging paging) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public void updateHit(Notice_Board noticeBoard) {
      
      String sql ="";
      sql += " UPDATE notice_board";
      sql += " SET hit = hit+1";
      sql += " WHERE boardno=?";
      
      try {
         conn.setAutoCommit(false);
         
         ps = conn.prepareStatement(sql);
         ps.setInt(1, noticeBoard.getBoardno());
         ps.executeQuery();
         
         conn.commit();
         
      } catch (SQLException e) {
         e.printStackTrace();
      }try {
         conn.rollback();
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         try {
            if(ps!=null)   ps.close();
         } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
   }

   @Override
   public Notice_Board selectByComment_No(Notice_Board noticeBoard) {
      // TODO Auto-generated method stub
      return null;
   }

}
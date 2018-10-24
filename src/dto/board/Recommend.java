package dto.board;

public class Recommend {

   private int boardno;
   private String userid;



   public int getBoardno() {
      return boardno;
   }



   public void setBoardno(int boardno) {
      this.boardno = boardno;
   }



   public String getUserid() {
      return userid;
   }



   public void setUserid(String userid) {
      this.userid = userid;
   }



   @Override
   public String toString() {
      return "Recomend [boardno=" + boardno + ", userid=" + userid + "]";
   }



}
package dao.comment.qna;

import java.util.List;

import dto.board.QnA;
import dto.comment.QnA_Comments;
import util.Paging;

public interface QnA_CommentDao {
//댓글 삽입 
   public void insert(QnA_Comments qna_Comment) throws Exception;

   //모든 댓글 선택 (리스트 출력)
   public List selectQnA_Comments(int boardno);

   // 댓글 수 출력
   public int selectQnA_CommentCntAll(QnA_Comments qna_Comment);

   // 댓글 삭제 (userid 와 comment_no로 대조해서 삭제)
   public void deleteQnA_Comment(QnA_Comments qna_Comment)throws Exception ;

   // 댓글 수정(userid 와 comment_no로 대조해서 수정)
   public void updateQnA_CommentByUserId(QnA_Comments qna_Comment);

   

}
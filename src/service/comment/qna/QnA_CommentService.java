package service.comment.qna;

import java.util.List;
import java.util.Map;

import dto.board.QnA;
import dto.comment.QnA_Comments;


public interface QnA_CommentService {
	
	public void insertComment(QnA_Comments qna_Comment) throws Exception;

	//댓글 목록
	public List getCommentList(QnA qna) ;

	//댓글 삭제
	public String deleteComment(QnA_Comments qna_Comment) throws Exception;
}

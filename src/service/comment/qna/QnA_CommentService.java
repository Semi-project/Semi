package service.comment.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.comment.Notice_Comments;
import dto.comment.QnA_Comments;


public interface QnA_CommentService {
	public QnA_Comments view(QnA_Comments qna_Comments);

	public QnA_Comments getParam(HttpServletRequest req, HttpServletResponse resp);

	// 댓글 작성
	public void writeQnA_Comments(HttpServletRequest req);

	// 댓글 수정
	public void updateQnA_Comments(HttpServletRequest req);

	// 댓글 삭제시 본인 이외엔 삭제 불가 하도록 하는 체크
	public boolean checkWriter(HttpServletRequest req, QnA_Comments qna_Comments);

}

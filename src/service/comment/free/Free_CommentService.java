package service.comment.free;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.Free_Board;
import dto.comment.Free_Comments;

public interface Free_CommentService {
	public Free_Comments view(Free_Comments free_Comments);

	public Free_Comments getParam(HttpServletRequest req, HttpServletResponse resp);

	
	// 댓글 수정
	public void updateFree_Comments(HttpServletRequest req);

	// 댓글 삭제시 본인 이외엔 삭제 불가 하도록 하는 체크
	public boolean checkWriter(HttpServletRequest req, Free_Comments free_Comments);

	//댓글 입력
	public void insertComment(Free_Comments comment);
	
	//댓글 목록
	public List getCommentList(Free_Board freeboard);
	
	//댓글 삭제
	public boolean deleteComment(Free_Comments comment);
}

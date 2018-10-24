package service.comment.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.comment.notice.Notice_CommentDao;
import dao.comment.notice.Notice_CommentDaoImpl;
import dto.comment.Notice_Comments;

public interface Notice_CommentService {
	
	
	
	public Notice_Comments view(Notice_Comments notice_Comments);

	public Notice_Comments getParam(HttpServletRequest req, HttpServletResponse resp);

	// 댓글 작성
	public void writeNotice_Comments(HttpServletRequest req);

	// 댓글 수정
	public void updateNotice_Comments(HttpServletRequest req);

	// 댓글 삭제시 본인 이외엔 삭제 불가 하도록 하는 체크
	public boolean checkWriter(HttpServletRequest req, Notice_Comments notice_Comments);

}

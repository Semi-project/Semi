package service.comment.notice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.comment.notice.Notice_CommentDao;
import dao.comment.notice.Notice_CommentDaoImpl;
import dto.board.Free_Board;
import dto.board.Notice_Board;
import dto.comment.Free_Comments;
import dto.comment.Notice_Comments;

public interface Notice_CommentService {
	
	public Notice_Comments view(Notice_Comments notice_Comments);

	public Notice_Comments getParam(HttpServletRequest req, HttpServletResponse resp);

	// 댓글 수정
	public void updateNotice_Comments(HttpServletRequest req);
	
	// 댓글 삭제시 본인 이외엔 삭제 불가 하도록 하는 체크
	public boolean checkWriter(HttpServletRequest req, Notice_Comments notice_Comments);

	//댓글 입력
	public void insertComment(Notice_Comments comment);
		
	//댓글 목록
	public List getCommentList(Notice_Board noticeboard);
		
	//댓글 삭제
	public boolean deleteComment(Notice_Comments comment);
		
	// 댓글 작성
	public void writeNotice_Comments(HttpServletRequest req);



}

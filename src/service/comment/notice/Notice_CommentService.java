package service.comment.notice;

import java.util.List;

import dto.board.Notice_Board;
import dto.comment.Notice_Comments;

public interface Notice_CommentService {

	public void insertNotice_Comments(Notice_Comments comment);

	public List<Notice_Comments> getCommentList(Notice_Board board);

	public boolean deleteComment(Notice_Comments comment);

//업데이트 기능 넣기 

}

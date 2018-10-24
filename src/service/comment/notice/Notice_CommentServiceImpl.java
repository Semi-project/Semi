package service.comment.notice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.comment.notice.Notice_CommentDao;
import dao.comment.notice.Notice_CommentDaoImpl;
import dto.board.Notice_Board;
import dto.comment.Notice_Comments;

public class Notice_CommentServiceImpl implements Notice_CommentService {
	Notice_CommentDao notice_CommentDao = new Notice_CommentDaoImpl();

	@Override
	public void insertNotice_Comments(Notice_Comments comment) {
		notice_CommentDao.insert(comment);
	}

	@Override
	public List<Notice_Comments> getCommentList(Notice_Board board) {

		return notice_CommentDao.select(board);
	}

	@Override
	public boolean deleteComment(Notice_Comments comment) {
		notice_CommentDao.deleteComment(comment);

		if (notice_CommentDao.countComment(comment) > 0) {
			return false;
		} else {
			return true;
		}
	}

}

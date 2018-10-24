package service.comment.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.comment.notice.Notice_CommentDao;
import dao.comment.notice.Notice_CommentDaoImpl;
import dto.comment.Notice_Comments;

public class Notice_CommentServiceImpl implements Notice_CommentService {
	Notice_CommentDao notice_CommentDao = new Notice_CommentDaoImpl();

	@Override
	public Notice_Comments view(Notice_Comments notice_Comments) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Notice_Comments getParam(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void writeNotice_Comments(HttpServletRequest req) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateNotice_Comments(HttpServletRequest req) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean checkWriter(HttpServletRequest req, Notice_Comments notice_Comments) {
		// TODO Auto-generated method stub
		return false;
	}

}

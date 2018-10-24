package controller.board.notice;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import service.board.notice.Notice_BoardService;
import service.board.notice.Notice_BoardServiceImpl;
import service.comment.notice.Notice_CommentService;
import service.comment.notice.Notice_CommentServiceImpl;

@WebServlet("/noticeboard/delete")
public class NoticeBoardDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Notice_BoardService notice_BoardService = new Notice_BoardServiceImpl();
	private Notice_CommentService notice_CommentService = new Notice_CommentServiceImpl();

}

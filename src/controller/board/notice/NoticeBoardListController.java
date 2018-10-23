package controller.board.notice;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import dao.comment.Notice_CommentDao;
import dao.comment.Notice_CommentDaoImpl;
import dao.file.Notice_FileDao;
import dao.file.Notice_FileDaoImpl123;
import service.board.Notice_BoardService;
import service.board.Notice_BoardServiceImpl;
import service.comment.Notice_CommentService;
import service.comment.Notice_CommentServiceImpl;

@WebServlet("/noticeboard/list")
public class NoticeBoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Notice_BoardService notice_BoardService = new Notice_BoardServiceImpl();
	private Notice_CommentService notice_CommentService = new Notice_CommentServiceImpl();
}

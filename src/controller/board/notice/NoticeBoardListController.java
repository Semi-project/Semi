package controller.board.notice;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.Notice_Board;
import service.board.Notice_BoardService;
import service.board.Notice_BoardServiceImpl;
import service.comment.Notice_CommentService;
import service.comment.Notice_CommentServiceImpl;

@WebServlet("/noticeboard/list")
public class NoticeBoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Notice_BoardService notice_BoardService = new Notice_BoardServiceImpl();
	private Notice_CommentService notice_CommentService = new Notice_CommentServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	List<Notice_Board> notice_boardList = notice_BoardService.selectNotice_Board();
	req.setAttribute("notice_boardList", notice_boardList);
	
	req.getRequestDispatcher("/view/board/notice_board/list.jsp")
		.forward(req, resp);
	
	}
}

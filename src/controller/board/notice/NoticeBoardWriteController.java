package controller.board.notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.board.Notice_BoardService;
import service.board.Notice_BoardServiceImpl;
import service.comment.Notice_CommentService;
import service.comment.Notice_CommentServiceImpl;

@WebServlet("/noticeboard/write")
public class NoticeBoardWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Notice_BoardService notice_BoardService = new Notice_BoardServiceImpl();
	private Notice_CommentService notice_CommentService = new Notice_CommentServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if(req.getSession().getAttribute("login")==null) {
			resp.sendRedirect("/main");
			return;
		}
	
		req.getRequestDispatcher("/view/board/notice_board/write.jsp")
			.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		notice_BoardService.writeNotice_Board(req);
	
	
	}
}

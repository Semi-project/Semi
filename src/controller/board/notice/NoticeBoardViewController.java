package controller.board.notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.Notice_Board;
import service.board.notice.Notice_BoardService;
import service.board.notice.Notice_BoardServiceImpl;
import service.comment.notice.Notice_CommentService;
import service.comment.notice.Notice_CommentServiceImpl;

@WebServlet("/noticeboard/view")
public class NoticeBoardViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Notice_BoardService notice_BoardService = new Notice_BoardServiceImpl();
	private Notice_CommentService notice_CommentService = new Notice_CommentServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Notice_Board notice_board = notice_BoardService.getParam(req, resp);
		
		Notice_Board notice_boardView = notice_BoardService.view(notice_board);
		
		req.setAttribute("notice_boardView", notice_boardView);

		
	
		req.getRequestDispatcher("/view/board/notice/view.jsp")
			.forward(req, resp);
	}
}

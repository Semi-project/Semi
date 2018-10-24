package controller.board.reviewboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.Notice_Board;
import dto.board.Review_Board;
import service.board.review.Review_BoardService;
import service.board.review.Review_BoardServiceImpl;

/**
 * Servlet implementation class NoticeBoardUpdateFile
 */
@WebServlet("/review/file/update")
public class ReviewBoardUpdateFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Review_BoardService boardService = new Review_BoardServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	//	System.out.println(req.getParameter("boardno"));
	//	System.out.println(req.getParameter("fileno"));
		Review_Board board = new Review_Board();
		board.setBoardno(Integer.parseInt(req.getParameter("boardno")));
		int fileno = Integer.parseInt(req.getParameter("fileno"));
		int cnt = boardService.deleteByfileno(fileno, board);
		resp.getWriter().println(cnt);
	}
}

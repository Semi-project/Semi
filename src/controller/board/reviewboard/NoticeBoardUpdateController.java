package controller.board.reviewboard;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.Notice_Board;
import dto.file.Notice_Filetb;
import service.board.notice.Notice_BoardService;
import service.board.notice.Notice_BoardServiceImpl;

/**
 * Servlet implementation class NoticeBoardUpdateController
 */
@WebServlet("/review/update")
public class NoticeBoardUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Notice_BoardService notice_BoardService = new Notice_BoardServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Notice_Board board = notice_BoardService.getParam(req, resp);
		if (!notice_BoardService.checkWriter(req, board)) {
			resp.sendRedirect("/notice/list");
			return;
		}

		// 게시글 조회수행
		Notice_Board boardView = notice_BoardService.view(board);
		// MODEL 전달
		req.setAttribute("boardView", boardView);

		// 글 작성자 닉네임 전달
		req.setAttribute("writernick", notice_BoardService.getNick(board));

		// 첨부파일 전달
		List<Notice_Filetb> boardFile = notice_BoardService.viewFile(board);
		req.setAttribute("boardFile", boardFile);

		// VIEW지정
		req.getRequestDispatcher("/view/board/notice/update.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		notice_BoardService.update(req);

		resp.sendRedirect("/notice/list");

	}

}

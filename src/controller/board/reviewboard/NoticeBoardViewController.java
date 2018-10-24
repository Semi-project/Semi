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

@WebServlet("/review/view")
public class NoticeBoardViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Notice_BoardService notice_BoardService = new Notice_BoardServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Notice_Board notice_Board = new Notice_Board();
		notice_Board = notice_BoardService.getParam(req, resp);
		Notice_Board boardView = notice_BoardService.view(notice_Board);
		req.setAttribute("boardView", boardView);
		// 글 작성자 닉네임 전달 현재 nick=userid
		req.setAttribute("writernick", notice_BoardService.getNick(notice_Board));
		// 첨부파일 전달
		List<Notice_Filetb> boardFile = notice_BoardService.viewFile(notice_Board);
		req.setAttribute("boardFile", boardFile);

		// VIEW지정
		req.getRequestDispatcher("/view/board/notice/view.jsp").forward(req, resp);

	}
}

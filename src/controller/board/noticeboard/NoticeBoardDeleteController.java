package controller.board.noticeboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.Notice_Board;
import service.board.notice.Notice_BoardService;
import service.board.notice.Notice_BoardServiceImpl;

@WebServlet("/noticeboard/delete")
public class NoticeBoardDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Notice_BoardService notice_BoardService = new Notice_BoardServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Notice_Board notice_board = notice_BoardService.getParam(req, resp);
		
		// 로그인한 사람의 글이 아니면 중단하고 리스트로
		if (!notice_BoardService.checkWriter(req, notice_board)) {

		//	System.out.println("2");	
			resp.sendRedirect("/noticeboard/list");
			return;
		}
		//System.out.println("1");
		notice_BoardService.delete(notice_board);
		resp.sendRedirect("/noticeboard/list");
	}
}

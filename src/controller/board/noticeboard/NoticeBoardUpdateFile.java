package controller.board.noticeboard;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dto.board.Notice_Board;
import service.board.notice.Notice_BoardService;
import service.board.notice.Notice_BoardServiceImpl;

/**
 * Servlet implementation class NoticeBoardUpdateFile
 */
@WebServlet("/notice/file/update")
public class NoticeBoardUpdateFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Notice_BoardService boardService = new Notice_BoardServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	//	System.out.println(req.getParameter("boardno"));
	//	System.out.println(req.getParameter("fileno"));
		Notice_Board board = new Notice_Board();
		board.setBoardno(Integer.parseInt(req.getParameter("boardno")));
		int fileno = Integer.parseInt(req.getParameter("fileno"));
		int cnt = boardService.deleteByfileno(fileno, board);
		resp.getWriter().println(cnt);
	}
}

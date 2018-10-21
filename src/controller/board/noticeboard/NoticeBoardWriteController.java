package controller.board.noticeboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.comment.Notice_CommentDao;
import dao.comment.Notice_CommentDaoImpl;
import dao.file.Notice_FileDao;
import service.board.notice.Notice_BoardService;
import service.board.notice.Notice_BoardServiceImpl;
import service.comment.Notice_CommentService;
import service.comment.Notice_CommentServiceImpl;

@WebServlet("/notice/write")
public class NoticeBoardWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Notice_BoardService notice_BoardService = new Notice_BoardServiceImpl();
	private Notice_CommentService notice_CommentService = new Notice_CommentServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("login") == null || req.getSession().getAttribute("role_id").equals("0")) {
			resp.sendRedirect("/main");
			return;
		}
		req.getRequestDispatcher("/view/board/notice/write.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 요청 파라미터 한글 인코딩 설정 : UTF-8
		req.setCharacterEncoding("UTF-8");
		// 응답 객체 MIME타입(Content-Type) 설정
		resp.setContentType("text/html;charset=UTF-8");
		/////////////////////////////////////////////////
		notice_BoardService.write(req);
		// System.out.println(req.getParameter("title"));
		// System.out.println(req.getParameter("content"));
		resp.sendRedirect("/notice/list");
	}
}

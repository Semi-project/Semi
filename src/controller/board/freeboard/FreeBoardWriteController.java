package controller.board.freeboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.board.Free_BoardService;
import service.board.Free_BoardServiceImpl;

@WebServlet("/freeboard/write")
public class FreeBoardWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Free_BoardService freeboardService = new Free_BoardServiceImpl();

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//로그인 처리 
		
		if(req.getSession().getAttribute("login")==null) {
			resp.sendRedirect("/main");
			return;
		}
		req.getRequestDispatcher("/view/board/Free/write.jsp")
		.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//요청 파라미터 인코딩 
		req.setCharacterEncoding("UTF-8");
		
		freeboardService.writeFreeboard(req);
		
		resp.sendRedirect("/freeboard/list");
	}
}

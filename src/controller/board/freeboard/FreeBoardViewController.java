package controller.board.freeboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.Free_Board;
import service.board.Free_BoardService;
import service.board.Free_BoardServiceImpl;

@WebServlet("/freeboard/view")
public class FreeBoardViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Free_BoardService freeboardService = new Free_BoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	Free_Board fb= freeboardService.getParam(req, resp);
	
	
	Free_Board freeboard = freeboardService.view(fb);
	

	req.setAttribute("boardService", freeboard);
	
	req.getRequestDispatcher("/view/board/Free/view.jsp").forward(req, resp);
	}

}

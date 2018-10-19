package controller.board.freeboard;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.Free_Board;
import service.board.Free_BoardService;
import service.board.Free_BoardServiceImpl;


@WebServlet("/freeboard/list")
public class FreeBoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Free_BoardService freeboardService = new Free_BoardServiceImpl();

	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	List<Free_Board> boardlist=freeboardService.selectFreeboard();
	req.setAttribute("boardlist", boardlist);
	

	req.getRequestDispatcher("/view/board/Free/list.jsp").forward(req, resp);
	
	
	}
	
	
	
}

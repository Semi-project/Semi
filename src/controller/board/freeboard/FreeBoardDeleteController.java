
package controller.board.freeboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.Free_Board;
import service.board.free.Free_BoardService;
import service.board.free.Free_BoardServiceImpl;


@WebServlet("/freeboard/delete")
public class FreeBoardDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Free_BoardService freeboardService = new Free_BoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
			Free_Board freeboard= freeboardService.getParam(req, resp);
			
			freeboardService.deleteFreeboard(freeboard);
			
			resp.sendRedirect("/freeboard/list");
		
	}
}

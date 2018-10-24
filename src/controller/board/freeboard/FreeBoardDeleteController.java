
package controller.board.freeboard;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import service.board.free.Free_BoardService;
import service.board.free.Free_BoardServiceImpl;


@WebServlet("/freeboard/delete")
public class FreeBoardDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Free_BoardService freeboardService = new Free_BoardServiceImpl();
}

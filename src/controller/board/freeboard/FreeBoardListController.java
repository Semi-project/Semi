package controller.board.freeboard;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.Free_Board;
import dto.board.Free_Board_param;
import service.board.free.Free_BoardService;
import service.board.free.Free_BoardServiceImpl;

@WebServlet("/freeboard/list")
public class FreeBoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Free_BoardService freeboardService = new Free_BoardServiceImpl();
    
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Free_Board_param fbpp= freeboardService.getParampage(req, resp);	
		
		int curPage=freeboardService.getCurPage(req);
		
		int totalCount=freeboardService.selecntFreeBoardCntAll();
		Free_Board_param fbParam = new Free_Board_param(totalCount,curPage);
		
		
	List<Free_Board> boardlist=freeboardService.selectFreeboard(fbParam);
	req.setAttribute("boardlist", boardlist);
	
	req.setAttribute("fbpp", fbpp);

	req.getRequestDispatcher("/view/board/Free/list.jsp").forward(req, resp);
	
	}
}

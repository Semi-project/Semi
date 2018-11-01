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


@WebServlet("/freeboard/recommend")
public class FreeBoardRecommendController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

private Free_BoardService freeboardService = new Free_BoardServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String param = request.getParameter("boardno");
		
		int boardno = 0;
		if( !"".equals(param) && param != null ) {
			boardno = Integer.parseInt(param);
		}

		Free_Board freeboard = new Free_Board();
		freeboard.setBoardno(boardno);
		freeboard.setUserid((String) request.getSession().getAttribute("userid")); 
		
		boolean result = freeboardService.recommend(freeboard);
		int recommend = freeboardService.getRecommend(freeboard);
		
		response.getWriter().println("{\"recommend\": "+recommend+", \"result\": "+result+"}");
	}

}

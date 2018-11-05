package controller.board.freeboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.Free_Board;
import dto.file.Free_Filetb;
import service.board.free.Free_BoardService;
import service.board.free.Free_BoardServiceImpl;


@WebServlet("/freeboard/update")
public class FreeBoardUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	private Free_BoardService freeboardService = new Free_BoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Free_Board freeboard = freeboardService.getParam(req, resp);
//		System.out.println("프리보드 "+freeboard);
	
		Free_Board view=freeboardService.view(freeboard);
//		System.out.println("뷰값"+view);
		
		req.setAttribute("view", view);
		req.setAttribute("userid",freeboardService.getUserid(freeboard));
		
		
		Free_Filetb freefiletb=freeboardService.viewFile(freeboard);
		
//		System.out.println("뷰파일값"+freefiletb);
		req.setAttribute("freefile", freefiletb);
		
		req.getRequestDispatcher("/view/board/Free/update.jsp")
		.forward(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		freeboardService.updateFreeboard(req);
	
//		System.out.println("리퀘스트"+req);
		resp.sendRedirect("/freeboard/list");
	}
  
}

package controller.board.qnaboard;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.QnA;
import service.board.QnAService;
import service.board.QnAServiceImpl;
import util.Paging;

@WebServlet("/qnaboard/list")
public class QnABoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private QnAService qnaService = new QnAServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		int curPage = qnaService.getCurPage(req);
		
//		System.out.println("curpage :" +curPage);
		
		int totalCount = qnaService.selecntQnACntAll();
		Paging paging = new Paging(totalCount , curPage);
		
//		System.out.println(paging);
	
		List<QnA> qnaList = qnaService.getQnAPagingList(paging);
		
		
		req.setAttribute("qnaList", qnaList);
		
		req.setAttribute("paging", paging);
		
		req.getRequestDispatcher("/view/board/qna/list.jsp").forward(req, resp);
	
	
	}

}

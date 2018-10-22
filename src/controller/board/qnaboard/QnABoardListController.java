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

@WebServlet("/qnaboard/list")
public class QnABoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private QnAService qnaService = new QnAServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		List<QnA> qnaList = qnaService.selectQnA();
		req.setAttribute("qnaList", qnaList);
		
		req.getRequestDispatcher("/view/board/qna/list.jsp").forward(req, resp);
		
		
	}
}

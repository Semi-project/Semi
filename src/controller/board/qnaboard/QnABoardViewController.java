package controller.board.qnaboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.QnA;
import service.board.QnAService;
import service.board.QnAServiceImpl;

@WebServlet("/qnaboard/view")
public class QnABoardViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private QnAService qnaService = new QnAServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		// 요청파라미터 -> MODEL
		QnA qna = qnaService.getParam(req, resp);
		
		QnA qnaView = qnaService.viewQnA(qna);
		
		req.setAttribute("qnaView", qnaView);
		System.out.println(qnaView);
		
		req.getRequestDispatcher("/view/board/qna/view.jsp").forward(req, resp);
		
	}
}

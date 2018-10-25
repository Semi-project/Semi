package controller.board.qnaboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.QnA;
import service.board.qna.QnAService;
import service.board.qna.QnAServiceImpl;


@WebServlet("/qnaboard/delete")
public class QnABoardDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private QnAService qnaService = new QnAServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		QnA qna = qnaService.getParam(req, resp);
		
		try {
			
			int resultCnt = 0;
			
			resultCnt = qnaService.deleteQnA(qna);
			
			
			
			if(resultCnt > 0) {
				String result = "success";
				
				String successMsg = "";
				successMsg = "삭제에 성공했습니다.";
				
				req.setAttribute("result", result);
				req.setAttribute("successMsg", successMsg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			String result = "fail";
			
			String failMsg = "";
			failMsg = "삭제에 실패했습니다";
			req.setAttribute("result", result);
			req.setAttribute("failMsg", failMsg);
			
		}
		
		req.getRequestDispatcher("/qnaboard/list").forward(req, resp);
		
	}
	
}

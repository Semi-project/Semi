package controller.comments.qna;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dto.comment.QnA_Comments;
import service.comment.qna.QnA_CommentService;
import service.comment.qna.QnA_CommentServiceImpl;

@WebServlet("/qnacomment/delete")
public class QnA_CommentDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private QnA_CommentService qna_CommentService = new QnA_CommentServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req , resp);
	
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		/*Comment comment = new Comment();
		
		String commentno = (String) request.getParameter("commentno");
		
		comment.setCommentno( Integer.parseInt(commentno) );
		
		boolean success = boardService.deleteComment(comment);
		
		response.getWriter().append("{\"success\":"+success+"}");*/
		
		QnA_Comments qnaComment = new QnA_Comments();
		
		String commentNo = (String) req.getParameter("comment_No");
		
		qnaComment.setComment_No( Integer.parseInt(commentNo) );
		
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
       
        //DTO 타입의 어레이리스트를 json 형태로 바꿔주는 구문(라이브러리 필수, zip->jar 확장자명 꼭 확인)
        Gson gson = new Gson();
		
		try {
		String result = qna_CommentService.deleteComment(qnaComment);
		Map map = new HashMap<>();
		map.put("result", result);
		String json = gson.toJson(map);
		resp.getWriter().println(json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

package controller.board.freeboard;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.Free_Board;
import dto.comment.Free_Comments;
import dto.file.Free_Filetb;
import service.board.free.Free_BoardService;
import service.board.free.Free_BoardServiceImpl;
import service.comment.free.Free_CommentService;
import service.comment.free.Free_CommentServiceImpl;

@WebServlet("/freeboard/view")
public class FreeBoardViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Free_BoardService freeboardService = new Free_BoardServiceImpl();
	private Free_CommentService fcs= new Free_CommentServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Free_Board fb= freeboardService.getParam(req, resp);
		//System.out.println(req.getAttribute("boardno"));

		/*
		 * 전체글
		 * (1,0,0)
		 * 		(1,1,1)
		 * 			(부모글의 REF ,부모글의step+1,부모글의depth+1) =(1,2,2)
		 * 		(1,2,1) -> (1,최대스텝+1 =3,1) 
		 * 		(1,3,1) -> (1,4,1)
		 * 		(1,4,1) -> (1,5,1)
		 * 
		 * (2,0,0)
		 * 
		 * 댓글을 씀과 동시에 업데이트로 step을 바꿔줘야됨
		 * 
		 * 
		 INSERT INTO ROOM_MAINBBS_REPLY(REP_NUM, REP_ID, REP_NAME, REP_MAINBBS_NUM, REP_REFERENCE, REP_STEP, REP_DEPTH, 
								REP_CONTENT, REP_PARENT, REP_WDATE, REP_DEL, REP_PARENT_ID, REP_SECRET, REP_MAINBBS_WRITER)
	VALUES(ROOM_MAINBBS_REPLY_SEQ.NEXTVAL, #{rep_id}, #{rep_name},#{rep_mainbbs_num},
				<if test="rep_parent !=null and rep_parent !=0 and rep_parent !=''">
				(SELECT REP_REFERENCE FROM ROOM_MAINBBS_REPLY WHERE REP_NUM = #{rep_parent}),
				(SELECT MAX(REP_STEP) FROM ROOM_MAINBBS_REPLY WHERE REP_REFERENCE=(SELECT REP_REFERENCE FROM ROOM_MAINBBS_REPLY WHERE 
					REP_NUM=#{rep_parent}))+1,
				(SELECT REP_DEPTH FROM ROOM_MAINBBS_REPLY WHERE REP_NUM = #{rep_parent})+1,
				#{rep_content},
				(SELECT MIN(REP_NUM) FROM ROOM_MAINBBS_REPLY WHERE REP_REFERENCE = (SELECT REP_REFERENCE FROM ROOM_MAINBBS_REPLY 
																					WHERE REP_NUM=#{rep_parent})),
				</if>
				<if test="rep_parent ==null or rep_parent ==0 or rep_parent ==''">
				(SELECT NVL(MAX(REP_REFERENCE),0)+1 FROM ROOM_MAINBBS_REPLY),0,0,#{rep_content},0,
				</if>								
				SYSDATE,
				0,
				#{rep_parent_id},
				#{rep_secret},
				#{rep_mainbbs_writer}
	)
		 */
		
		Free_Board free_board = freeboardService.view(fb);
		req.setAttribute("freeboardService", free_board);
		
		
		Free_Filetb freefiletb = freeboardService.viewFile(fb);
		req.setAttribute("fileno",freefiletb);
	
//댓글
		Free_Comments comment = new Free_Comments();
		
		List<Free_Comments> commentList = fcs.getCommentList(fb);
		
		req.setAttribute("commentList", commentList);
		
		//추천
		//추천 상태
				Free_Board recommend = new Free_Board();
				recommend.setBoardno(fb.getBoardno());
				recommend.setUserid((String) req.getSession().getAttribute("userid") );
		
				req.setAttribute("recommend", freeboardService.recommendCheck(recommend));

		req.getRequestDispatcher("/view/board/Free/view.jsp").forward(req, resp);
	}


}

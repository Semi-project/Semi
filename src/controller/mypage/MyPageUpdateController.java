package controller.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.member.Member;
import service.board.qna.QnAService;
import service.member.MemberService;
import service.member.MemberServiceImpl;

/**
 * Servlet implementation class MyPageUpdateController
 */
@WebServlet("/mypage/update")
public class MyPageUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MemberService memberService= new MemberServiceImpl();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    
    	Member member =new Member();
    	member.setUserid((String)req.getSession().getAttribute("userid"));
    	
    	Member memberView = memberService.selectMemberByUserId(member);
    	
    	req.setAttribute("memberView", memberView );
    	
    	req.getRequestDispatcher("/view/mypage/update.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    
    	req.setCharacterEncoding("UTF-8");
    	
    	Member member= memberService.getParam(req, resp);
    	
    	try {
			memberService.updateMember(req , member);
			
			
			Member memberView = memberService.selectMemberByUserId(member);
			
			req.setAttribute("memberView", memberView );
			
			if(memberView != null) {
				String result = "success";
				req.setAttribute("result", result);
			}
			String successMsg ="";
			successMsg ="수정에 성공했습니다";
			req.setAttribute("successMsg", successMsg);
			
    	
    	} catch (Exception e) {
			e.printStackTrace();
			
			String result ="fail";
			
			String failMsg="";
			failMsg = "수정에 실패했습니다";
			req.setAttribute("result", result);
			req.setAttribute("failMas", failMsg);
		}
    	
    	req.getRequestDispatcher("/view/mypage/view.jsp").forward(req, resp);
    }
}

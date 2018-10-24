package controller.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.member.Member;
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
    
    	Member member = memberService.getParam(req, resp);
    	
    	Member memberView = memberService.selectMemberByUserId(member);
    	
    	req.setAttribute("memberView", memberView );
    	
    	req.getRequestDispatcher("/view/mypage/view.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    
    	req.setCharacterEncoding("UTF-8");
    	
    	try {
			memberService.updateMember(req);
			
			resp.sendRedirect("/mypage/view");
		} catch (Exception e) {
			e.printStackTrace();
		}
   
			
    } 	
  
}

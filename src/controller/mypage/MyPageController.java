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
 * Servlet implementation class MyPageController
 */
@WebServlet("/mypage/view")
public class MyPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private MemberService memberService= new MemberServiceImpl();
    
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Member member = new Member();
		member.setUserid((String)req.getSession().getAttribute("userid"));
		
		Member memberView  = memberService.selectMemberByUserId(member);
		
		String phone = memberView.getPhone();
		
//		String phone1 = phone.substring(0,3);
//		
//		String phone2 = phone.substring(3,7);
//		
//		String phone3 = phone.substring(7,11);
		
		
		req.setAttribute("memberView", memberView);
//		req.setAttribute("phone1", phone1);
//		req.setAttribute("phone2", phone2);
//		req.setAttribute("phone3", phone3);
		
		
		req.getRequestDispatcher("/view/mypage/view.jsp").forward(req, resp);
		
	
	}
	
}

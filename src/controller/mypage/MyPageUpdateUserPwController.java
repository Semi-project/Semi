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


@WebServlet("/mypage/userpwupdate")
public class MyPageUpdateUserPwController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberService memberService = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	Member member = new Member();
	member.setUserid((String)req.getSession().getAttribute("userid"));
	
	Member memberView = memberService.selectMemberByUserId(member);
	
	req.setAttribute("memberView", memberView);
		
	req.getRequestDispatcher("/view/mypage/pwupdate.jsp").forward(req, resp);

	}
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		req.setCharacterEncoding("UTF-8");
		Member member = new Member();
		
		member.setUserid((String)req.getSession().getAttribute("userid"));
		member.setUserpw(req.getParameter("userpw"));
		
		String result ="";
		
		try {
			int resultCnt = 0;
			
			resultCnt =	memberService.updateMemberPassword(member);
			
			if(resultCnt > 0) {
				
				Member memberView = memberService.selectMemberByUserId(member);
				
				req.setAttribute("memberView", memberView);
				
				result = "success";
				req.setAttribute("result", result);
				
				String successMsg = "";
				successMsg ="수정에 성공했습니다";
				req.setAttribute("successMsg", successMsg);
			}
		
		
		} catch (Exception e) {

			e.printStackTrace();
			
			result ="fail";
			
			String failMsg="";
			failMsg = "수정에 실패했습니다";
			req.setAttribute("result", result);
			req.setAttribute("failMas", failMsg);
		}
			resp.sendRedirect("/mypage/view");
		}
}

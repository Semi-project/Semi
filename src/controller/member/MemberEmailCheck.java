package controller.member;

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
 * Servlet implementation class MemberEmailCheck
 */
@WebServlet("/member/membercheckemail")
public class MemberEmailCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberService memberService = new MemberServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Member member = new Member();
		member.setEmail(req.getParameter("useremail"));
//		int cnt=memberService.checkUserEmail(member);
//		resp.getWriter().println(cnt);
	}
}

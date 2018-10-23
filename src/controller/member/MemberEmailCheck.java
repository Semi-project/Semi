package controller.member;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import service.member.MemberService;
import service.member.MemberServiceImpl;

@WebServlet("/member/membercheckemail")
public class MemberEmailCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberService memberService = new MemberServiceImpl();


}

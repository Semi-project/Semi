package controller.member;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import service.member.MemberService;
import service.member.MemberServiceImpl;


@WebServlet("/member/memberidcheck")
public class MemberIdCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberServiceImpl();
	
}

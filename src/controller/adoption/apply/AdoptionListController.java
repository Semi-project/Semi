package controller.adoption.apply;

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
 * Servlet implementation class AdoptionListController
 */
@WebServlet("/adoption/list")
public class AdoptionListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// System.out.println(req.getSession().getAttribute("userid"));
		Member member = new Member();
		member.setUserid((String) req.getSession().getAttribute("userid"));
		member = memberService.selectMemberByUserId(member);
		req.setAttribute("member", member);
		req.getRequestDispatcher("/view/board/adoption/list.jsp").forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
}

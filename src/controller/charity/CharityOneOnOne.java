package controller.charity;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.adoption.Adoption;
import dto.animal.Animal;
import dto.member.Member;
import service.adoption.AdoptionService;
import service.adoption.AdoptionServiceImpl;
import service.animal.AnimalService;
import service.animal.AnimalServiceImpl;
import service.charity.CharityService;
import service.charity.CharityServiceImpl;
import service.member.MemberService;
import service.member.MemberServiceImpl;
import util.Paging;

/**
 * Servlet implementation class CharityOneOnOne
 */
@WebServlet("/charity/oneonone")
public class CharityOneOnOne extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberServiceImpl();
	private CharityService charityService = new CharityServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		if (req.getSession().getAttribute("login") == null) {
			resp.sendRedirect("/main"); // 2018- 10- 23 일 정리
			return;
		}
//		if (req.getSession().getAttribute("charity") == null) {
//			resp.sendRedirect("/main"); // 2018- 10- 23 일 정리
//			return;
//		}
		req.getSession().setAttribute("charity", true);
		Member member = new Member(); // 멤버 정보를 넣을 멤버 변수 초기화
		member.setUserid((String) req.getSession().getAttribute("userid"));
		Member curM = memberService.selectMemberByUserId(member); // 아이디로 현재 유저 정보를 불러옴

		req.setAttribute("curm", curM);

		
		req.getRequestDispatcher("/view/charity/charityoneonone.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	};

}

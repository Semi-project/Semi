package controller.charity;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dto.charity.Charity;
import dto.member.Member;
import service.charity.CharityService;
import service.charity.CharityServiceImpl;
import service.member.MemberService;
import service.member.MemberServiceImpl;

/**
 * Servlet implementation class CharityForSite
 */
@WebServlet("/charity/animal")
public class CharityForAnimal extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberServiceImpl();
	private CharityService charityService = new CharityServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("login") == null) {
			resp.sendRedirect("/main"); // 2018- 10- 23 일 정리
			return;
		}
		req.getSession().setAttribute("charity", true);
		//System.out.println(req.getSession().getAttribute("charity"));
		Member member = new Member(); // 멤버 정보를 넣을 멤버 변수 초기화
		member.setUserid((String) req.getSession().getAttribute("userid"));
		Member curM = memberService.selectMemberByUserId(member); // 아이디로 현재 유저 정보를 불러옴

		req.setAttribute("curm", curM);

		req.getRequestDispatcher("/view/charity/charityforanimal.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// System.out.println("통신중");
		Charity charity = charityService.getParam(req, resp);
		charityService.insertCharity(charity);
		Gson gson = new Gson();
		String json = gson.toJson("success");
		resp.getWriter().println(json);
		resp.sendRedirect("/main");
	}
}

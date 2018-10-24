package controller.member;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dto.member.Member;
import dto.member.Subscription;
import service.member.MemberService;
import service.member.MemberServiceImpl;
import service.member.SubscriptionService;
import service.member.SubscriptionServiceImpl;

/**
 * Servlet implementation class MemberJoinControlle
 */
@WebServlet("/member/join")
public class MemberJoinController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberServiceImpl();
	private SubscriptionService subscriptionService = new SubscriptionServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/view/member/join.jsp").forward(req, resp);
		// [GET] - 회원가입 폼 보여주기
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("통신중");
//		System.out.println(req.getParameter("userid"));
//		System.out.println(req.getParameter("userbirth"));
//		System.out.println(req.getParameter("userpw"));
//		System.out.println(req.getParameter("gender"));
//		System.out.println(req.getParameter("address"));
//		System.out.println(req.getParameter("phone"));
//		System.out.println(req.getParameter("smartPhone"));
//		System.out.println(req.getParameter("email"));
//		System.out.println(req.getParameter("subscriptionNews"));
//		System.out.println(req.getParameter("subscriptionSms"));

		Member param = memberService.getParam(req, resp);
		Subscription subscription = subscriptionService.getParam(req);
		int subscriptionCnt = subscriptionService.insertSubscription(subscription);
		int memberCnt = memberService.join(param);
		 System.out.println(subscriptionCnt+","+memberCnt);
		// memberService.join(param);
		Gson gson = new Gson();
		Map map = new HashMap<>();
		map.put("subscriptionCnt", subscriptionCnt);
		map.put("memberCnt", memberCnt);
		String json = gson.toJson(map);
		resp.getWriter().println(json);
		// resp.sendRedirect("/main");
	}
}

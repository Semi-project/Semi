package controller.member;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dto.member.Member;
import service.member.MemberService;
import service.member.MemberServiceImpl;

/**
 * Servlet implementation class MemberFindIDController
 */
@WebServlet("/member/findid")
public class MemberFindIdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/view/member/findid.jsp").forward(req, resp);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// System.out.println("통신중");
		// System.out.println();
		// System.out.println();
		Member member = new Member();
		member.setName(req.getParameter("username"));
		member.setEmail(req.getParameter("useremail"));

		List<Member> mList = memberService.searchUserId(member);
		String idList ="";
		for (int i = 0; i < mList.size(); i++) {
			idList += mList.get(i).getUserid()+" ";
		}
		System.out.println(idList);
		Gson gson = new Gson();
		Map map = new HashMap<>();
		map.put("idList", idList); //가입된 아이디를 map으로 저장하여 출력 
		String json = gson.toJson(map);
		resp.getWriter().println(json);
	};

}

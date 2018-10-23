package controller.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.member.Member;
import service.member.MemberService;
import service.member.MemberServiceImpl;

/**
 * Servlet implementation class MemberLoginController
 */
@WebServlet("/member/login/*")
public class MemberLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/view/member/login.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Member member = new Member();
		String url = req.getRequestURI().toString();
		// System.out.println(url);
		member = memberService.getParam(req, resp);
		member = memberService.selectMemberByUserId(member);
		int idCnt = memberService.checkUserId(member);
		int userCnt = memberService.checkUser(member);
		// String message = "";
		int cnt = -1;
		if (url.indexOf("login.do") != -1) {

			if (idCnt == 0 && userCnt == 0) {
				// message = "존재하지 않는 아이디입니다.";
				cnt = 1;
				resp.getWriter().println(cnt);
				// System.out.println(message);
			} else if (idCnt == 1 && userCnt == 1) {
				// message = member.getUserid() + "님 환영합니다.";
				// System.out.println(message);
				req.getSession().setAttribute("login", true);
				req.getSession().setAttribute("userid", member.getUserid());
				req.getSession().setAttribute("nick", member.getName());
				req.getSession().setAttribute("role_id", member.getRole_id());
				//System.out.println(member.getRole_id());
				//System.out.println(member.getName());
				cnt = 0;
				resp.getWriter().println(cnt);

			} else {
				// message = "비밀번호가 틀렸습니다.";
				cnt = 2;
				resp.getWriter().println(cnt);
				// System.out.println(message);
			}

//			req.setAttribute("message", message);

//			String page = "/view/member/login_result.jsp";

//			RequestDispatcher rd = req.getRequestDispatcher(page);
//			rd.forward(req, resp);

// resp.getWriter().println(page);
		}

	}
}

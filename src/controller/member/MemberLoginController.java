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
		String url = req.getRequestURI().toString();//login.do 
		// System.out.println(url);
		member = memberService.getParam(req, resp);
		//System.out.println("111111111111111111111: "+member);
		int idCnt = memberService.checkUserId(member); //아이디 체크 
		int userCnt = memberService.checkUser(member); // 비밀번호 체크 
		System.out.println("usercnt"+userCnt);
		 String message = "";
		int cnt = -1;
		if (url.indexOf("login.do") != -1) {

			if (idCnt == 0 && userCnt == 0) {
				 message = "존재하지 않는 아이디입니다.";
				cnt = 1;
				resp.getWriter().print(cnt);
				 System.out.println(message);
			} else if (idCnt == 1 && userCnt == 1) {
				message = member.getUserid() + "님 환영합니다.";
				 System.out.println(message);
				member = memberService.selectMemberByUserId(member);	
			//	System.out.println("222222222222222 : "+member);
				req.getSession().setAttribute("login", true); // 로그인 세션 유지 
				req.getSession().setAttribute("userid", member.getUserid()); // 아이디
				req.getSession().setAttribute("nick", member.getName()); // 닉=이름 
				req.getSession().setAttribute("role_id", member.getRole_id()); // 권한 
				// System.out.println(member.getRole_id());
				// System.out.println(member.getName());
				cnt = 0;
				resp.getWriter().print(cnt);

			} else {
				 message = "비밀번호가 틀렸습니다.";
				cnt = 2;
				resp.getWriter().print(cnt);
				 System.out.println(message);
			}

//			req.setAttribute("message", message);

//			String page = "/view/member/login_result.jsp";

//			RequestDispatcher rd = req.getRequestDispatcher(page);
//			rd.forward(req, resp);

// resp.getWriter().println(page);
		}

	}
}

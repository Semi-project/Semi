package controller.member;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.member.Member;
import service.member.MemberService;
import service.member.MemberServiceImpl;
import util.Paging;

/**
 * Servlet implementation class MemberListController
 */
@WebServlet("/member/list")
public class MemberListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 현재 페이지 번호 얻기
		int curPage = memberService.getCurPage(req);

		// 검색어 얻기
		String search = memberService.getSearch(req);

		// 페이징 객체
		int totalCount = memberService.getTotalCount(search);
		Paging paging = new Paging(totalCount, curPage);
		paging.setSearch(search);

		List<Member> memberList = memberService.getAllList(paging);
		req.setAttribute("memberList", memberList);

		// 페이징 객체 MODEL로 추가
		req.setAttribute("paging", paging);

		// VIEW지정
		req.getRequestDispatcher("/view/supervisor/memberlist.jsp").forward(req, resp);
	}
}

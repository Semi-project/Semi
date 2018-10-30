package controller.adoption.apply;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.adoption.Adoption;
import service.adoption.AdoptionService;
import service.adoption.AdoptionServiceImpl;
import service.member.MemberService;
import service.member.MemberServiceImpl;
import util.Paging;

/**
 * Servlet implementation class AdoptionListController
 */
@WebServlet("/adoption/application/list")
public class AdoptionListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberServiceImpl();
	private AdoptionService adoptionService = new AdoptionServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// System.out.println(req.getSession().getAttribute("userid"));
//		
//		Member member = new Member();
//		member.setUserid((String) req.getSession().getAttribute("userid"));
//		member = memberService.selectMemberByUserId(member);
//		req.setAttribute("member", member);
//		//현재 페이지 번호 얻기
		int curPage = adoptionService.getCurPage(req);
		
		//검색어 얻기
		String search = adoptionService.getSearch(req);
		
		//페이징 객체
		int totalCount = adoptionService.getTotalCount(search);
		Paging paging = new Paging(totalCount, curPage);
		
		//페이징 객체에 검색어 적용
		paging.setSearch(search);
		
//		System.out.println(paging);
		
		//게시글목록 MODEL로 추가
		List<Adoption> list = adoptionService.getPagingList(paging);
		req.setAttribute("boardList", list);
		
		//페이징 객체 MODEL로 추가
		req.setAttribute("paging", paging);
		
		req.getRequestDispatcher("/view/board/adoption/apply/list.jsp").forward(req, resp);
		
	}

}

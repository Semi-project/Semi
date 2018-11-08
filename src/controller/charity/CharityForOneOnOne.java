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
import service.adoption.AdoptionService;
import service.adoption.AdoptionServiceImpl;
import service.animal.AnimalService;
import service.animal.AnimalServiceImpl;
import util.Paging;

/**
 * Servlet implementation class CharityForOneOnOne
 */
@WebServlet("/charity/foroneonone")
public class CharityForOneOnOne extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AnimalService animalService = new AnimalServiceImpl();
	private AdoptionService adoptionService = new AdoptionServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("login") == null) {
			resp.sendRedirect("/member/login"); // 2018- 10- 23 일 정리
			return;
		}
		// 동물 리스트
		int curPage = animalService.getCurPage(req);
		int statusSet = animalService.getCountAcpt();
		Paging paging = new Paging(statusSet, curPage);

		// 게시글목록 MODEL로 추가
		// 일반유저
		List<Animal> animalList = animalService.selectPagingListUser(paging);
		req.setAttribute("animalList", animalList);

		List<Adoption> list = adoptionService.getList();
		req.setAttribute("adoptList", list);

		// 페이징 객체 MODEL로 추가
		req.setAttribute("paging", paging);

		req.getRequestDispatcher("/view/charity/charityforoneonone.jsp").forward(req, resp);
	}

}

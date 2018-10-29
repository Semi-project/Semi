package controller.adoption.send;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.animal.Animal;
import service.animal.AnimalService;
import service.animal.AnimalServiceImpl;
import util.Paging;

// 입양 보내기 리스트
// 관리자

@WebServlet("/adoption/send/list")
public class AdoptionRe_ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AnimalService animalService = new AnimalServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("AdoptionRe_ListController - role_id : " + req.getSession().getAttribute("role_id"));
		
		// 동물 리스트
		int curPage = animalService.getCurPage(req);
		int totalCount = animalService.getTotalCount();
		Paging paging = new Paging(totalCount, curPage);

		// 게시글목록  MODEL로 추가
		// 관리자
		List<Animal> animalList = animalService.getPagingListAdmin(paging);

		req.setAttribute("animalList", animalList);

		// 페이징 객체 MODEL로 추가
		req.setAttribute("paging", paging);

		req.getRequestDispatcher("/view/board/adoption/send/adoptionSendList.jsp").forward(req, resp);

	}

}

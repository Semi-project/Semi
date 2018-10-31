package controller.adoption.send;

import java.io.IOException;
import java.util.ArrayList;
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

// 입양 보내기 리스트

@WebServlet("/adoption/send/list")
public class AdoptionRe_ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// service
	private AnimalService animalService = new AnimalServiceImpl();
	private AdoptionService adoptionService = new AdoptionServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 현재 페이지 번호 얻기
		int curPage = animalService.getCurPage(req);

		// 페이징 객체
		int totalCount = animalService.getTotalCount();
		Paging paging = new Paging(totalCount, curPage);

		// 게시글목록 MODEL로 추가
		// 관리자 전체 동물
		List<Animal> animalList = animalService.getPagingListAdmin(paging);
		req.setAttribute("animalList", animalList);

		List<Adoption> list = adoptionService.getList();
		req.setAttribute("boardList", list);

		///////////////테스트////////////////////////////////////
		for(int i=0; i<animalList.size(); i++) {
			System.out.println((i+1) +". " + animalList.get(i).getAnimal_Name());
		}

		System.out.println("---------------------");

		for(int i=0; i<animalList.size(); i++) {
			if(animalList.get(i).getStatus()==0) {
				//				System.out.println("승인되지 않은 동물: " + animalList.get(i).getAnimal_Name());
				System.out.println(animalList.get(i).getAnimal_Name());
			} else if(animalList.get(i).getStatus()==1) {

				boolean doneList = false;
				List check = new ArrayList<>();

				if(doneList == false) {
					for(int j=0; j<list.size(); j++) {

						if(animalList.get(i).getAnimal_Code() == list.get(j).getAnimalCode()) {
							doneList = true;
							check.add(true);
						} else if(animalList.get(i).getAnimal_Code() != list.get(j).getAnimalCode()) {
							doneList = true;
							check.add(false);
						}
					}

					boolean doneCheck = false;
					if(doneCheck!=true) {					
						for(int k=0; k<check.size(); k++) {
							if(check.contains(true)) {
								//System.out.println("승인되고 신청된 동물 : " + animalList.get(i).getAnimal_Name());
								System.out.println(animalList.get(i).getAnimal_Name() + " [ 입양 완료 ]");
								doneCheck = true;
							} else if(!check.contains(true)){
								//System.out.println("승인되고 신청되지 않은 동물 : " + animalList.get(i).getAnimal_Name());
								System.out.println(animalList.get(i).getAnimal_Name());
								doneCheck = true;
							}
						}
					}

				}
			}
		}


		// 페이징 객체 MODEL로 추가
		req.setAttribute("paging", paging);

		req.getRequestDispatcher("/view/board/adoption/send/adoptionSendList.jsp").forward(req, resp);

	}

}
package controller.adoption.send;

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

// 입양 보내기 리스트

@WebServlet("/adoption/send/list")
public class AdoptionRe_ListController extends HttpServlet {
   private static final long serialVersionUID = 1L;

   // service
   private AnimalService animalService = new AnimalServiceImpl();
   private AdoptionService adoptionService = new AdoptionServiceImpl();

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

      // 로그인 확인
      if(req.getSession().getAttribute("role_id") != null) {
         int roleId = (int)req.getSession().getAttribute("role_id");
         
         if (roleId != 0) {
            System.out.println(req.getSession().getAttribute("role_id"));
            resp.sendRedirect("/adoption/send/mlist");
            return;
         }
      } else {
         resp.sendRedirect("/adoption/send/mlist");
         return;
      }
      
      
      // 현재 페이지 번호 얻기
      int curPage = animalService.getCurPage(req);

      // 페이징 객체
      int totalCount = animalService.getTotalCount();
      Paging paging = new Paging(totalCount, curPage);

      // 게시글목록 MODEL로 추가
      // 관리자 전체 동물
      List<Animal> animalList = animalService.getPagingListAdmin(paging);
      req.setAttribute("animalList", animalList);

      // 입양신청서 리스트
      List<Adoption> list = adoptionService.getList();
      req.setAttribute("adoptList", list);
      
      // 페이징 객체 MODEL로 추가
      req.setAttribute("paging", paging);

      req.getRequestDispatcher("/view/board/adoption/send/adoptionSendList.jsp").forward(req, resp);

   }

}
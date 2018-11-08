package controller.mypage;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.adoption.Adoption;
import dto.member.Member;
import service.adoption.AdoptionService;
import service.adoption.AdoptionServiceImpl;
import util.Paging;


@WebServlet("/mypage/adoption")
public class MypageAdoptionController extends HttpServlet {
   private static final long serialVersionUID = 1L;
   
   private AdoptionService adoptionService = new AdoptionServiceImpl();
       
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       
       int curPage = adoptionService.getCurPage(req);
       
       String search = "";
       
       int totalCount = adoptionService.getTotalCount(search);
       Paging paging = new Paging(totalCount , curPage);
       
       paging.setUserid((String)req.getSession().getAttribute("userid"));
       
//       member.setUserid((String)req.getSession().getAttribute("userid"));
   
       List<Adoption> adoptionList = adoptionService.getPagingUseridList(paging);
       req.setAttribute("adoptionList", adoptionList);
       req.setAttribute("paging",paging);
       
       
       req.getRequestDispatcher("/view/mypage/adoptionlist.jsp").forward(req, resp);
    
    }

}
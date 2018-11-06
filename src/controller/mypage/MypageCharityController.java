package controller.mypage;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.charity.Charity;
import service.charity.CharityService;
import service.charity.CharityServiceImpl;
import util.Paging;


@WebServlet("/mypage/charity")
public class MypageCharityController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CharityService charityService = new CharityServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	int curPage = charityService.getCurPage(req);
	
	String search = "";
	
	int totalCount = charityService.getTotalCount(search);
	Paging paging = new Paging(totalCount , curPage);
	
	paging.setUserid((String)req.getSession().getAttribute("userid"));
	
	List<Charity> charityList = charityService.getPagingList(paging);
	
	req.setAttribute("charityList", charityList);
	req.setAttribute("paging", paging);
	
	req.getRequestDispatcher("/view/mypage/charity.jsp").forward(req , resp);
	
	}
}

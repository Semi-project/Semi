

package controller.board.qnaboard;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.board.qna.QnADao;
import dao.board.qna.QnADaoImpl;
import dto.board.QnA;
import service.board.qna.QnAService;
import service.board.qna.QnAServiceImpl;
import util.Paging;

@WebServlet("/qnaboard/paginglist")
public class QnABoardPagingListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private QnAService qnaService = new QnAServiceImpl();
	private QnADao qnaDao = new QnADaoImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		//현재 페이지 번호 얻기
				int curPage = qnaService.getCurPage(req);
				System.out.println("현재페이지"+curPage);
				
				//검색어 얻기
				
				String searchVal = (req.getParameter("searchVal") == null) ? "" : req.getParameter("searchVal"); 
				
				/*String searchVal = req.getParameter("searchVal");*/
				
				String searchTxt = req.getParameter("searchTxt");
				String search = "";
				
				if(searchVal.equals("title")) {
					search = searchTxt;
				}
				else if(searchVal.equals("content")){
					search = searchTxt;
				}
				else if(searchVal.equals("userid")) {
					search = searchTxt;
				}
				
				//페이징 객체
				int totalCount = qnaService.getTotalCount(searchVal, search);
				System.out.println("totalCount"+totalCount);
				Paging paging = new Paging(totalCount, curPage);
				System.out.println("paging"+paging);
				//페이징 객체에 검색어 적용
				paging.setSearch(search);
				
//				System.out.println(paging);
				
				//게시글목록 MODEL로 추가
				List<QnA> qnaList1 = qnaService.selectQnA();
				
				
				List<QnA> qnaList = qnaService.getQnAPagingList(paging , search, searchVal);
				req.setAttribute("qnaList", qnaList);
				
				System.out.println("리스트 뿌려지나:"+qnaList);
				//페이징 객체 MODEL로 추가
				req.setAttribute("paging", paging);
				System.out.println("페이징 뿌려지나 "+paging);
				//VIEW지정
				req.getRequestDispatcher("/view/board/qna/list.jsp")
					.forward(req, resp);
	}

}

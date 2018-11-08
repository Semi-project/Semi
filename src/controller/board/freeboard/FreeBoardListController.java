package controller.board.freeboard;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.Free_Board;
import dto.board.Free_Board_param;
import service.board.free.Free_BoardService;
import service.board.free.Free_BoardServiceImpl;

@WebServlet("/freeboard/list")
public class FreeBoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Free_BoardService freeboardService = new Free_BoardServiceImpl();
    
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		//페이지 처리
		
		
		
//		fbpp= new Free_Board_param(totalpage,curpage);

		/*
		 *  데이터 갯수 : 55     ,페이지 갯수 6   , 블록 2개
		 *  [1 2 3 4 5] >> / [6]   
		 *  list 를 가져오기 위해서 검색, 페이지처리, 정렬 jsp -> servelt으로 변수를 전달 
		 *  
		 *  검색을 했음
		 *  데이터 갯수 : 20 , 페이지 갯수 2 , 블록 1개
		 *  [1 2] 
		 *  데이터 갯수를 가져오기 위해서 검색, 페이지처리, 정렬 jsp -> servelt으로 변수를 전달 
		 *  
		 *  
		 */
		
//		System.out.println(fbpp);
		Free_Board_param fbpp= freeboardService.getParampage(req, resp); //현재페이지 정보
		
		//검색
		Free_Board_param fbss=freeboardService.getSearchParam(req, resp);
		
		
	fbpp.setNamesearch(fbss.getNamesearch());
		fbpp.setContentsearch(fbss.getContentsearch());
		
				
		System.out.println(fbss.toString());
		/////////////////////////////
		
	
		//초기페이지
		int start = (fbpp.getPageNumber())*fbpp.getRecordCountPerPage()+1;
		fbpp.setStart(start);
		
		//마지막페이지
		int end = (fbpp.getPageNumber()+1)*fbpp.getRecordCountPerPage();
		fbpp.setEnd(end);
		
		
		int totalpage=freeboardService.selecntFreeBoardCntAll();
		
		
	List<Free_Board> boardlist=freeboardService.selectFreeboard(fbpp);
	req.setAttribute("boardlist", boardlist);
	
	//전달값
	req.setAttribute("fbpp", fbpp);//page, search, sort ..
	req.setAttribute("pageCountPerScreen", 10);
	req.setAttribute("recordCountPerPage", fbpp.getRecordCountPerPage());
	req.setAttribute("totalRecordCount", totalpage);
	

	req.getRequestDispatcher("/view/board/Free/list.jsp").forward(req, resp);
	
	}
}

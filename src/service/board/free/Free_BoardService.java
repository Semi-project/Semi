package service.board.free;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.Free_Board;
import dto.board.Free_Board_param;
import dto.comment.Free_Comments;
import dto.file.Free_Filetb;
import util.Paging;

public interface Free_BoardService {
	
	
	
	// 자유게시판 전체 조회
	
	// 자유게시판 글 쓰기 및 파일 업로드
	public void writeFreeboard(HttpServletRequest req); //, File file);
	
	
	public Free_Board_param getParampage(HttpServletRequest req, HttpServletResponse resp);
	
	// 자유게시판 삭제
	public void deleteFreeboard(Free_Board freeBoard);
	
	// 자유게시판 수정
	public void updateFreeboard(HttpServletRequest req );//, File file);
	
	//게시판 제목으로 찾기
	public String searchFreeBoardBytitle(Free_Board freeBoard);

	//게시판 내용으로 찾기
	public String searchFreeBoardBycontent(Free_Board freeBoard);

	// 게시판 아이디로 찾기
	public String searchFreeBoardByuserid(Free_Board freeBoard);

	// 게시판 총 글수 출력
	public int selecntFreeBoardCntAll();



	// 추천수 올려주기
	public void updateRecommend(Free_Board freeBoard);

	//댓글번호로 조회하기
	public Free_Board searchByComment_no (Free_Board freeBoard);
	
	public List<Free_Board> selectByBoardno (Free_Board freeBoard , Free_Comments freeComments , Free_Filetb freeFileTb);

	public Free_Board getParam(HttpServletRequest req, HttpServletResponse resp);
	
	//조회수올리기
	public Free_Board view(Free_Board freeBoard);
	
	//첨부파일 얻기
		public Free_Filetb viewFile(Free_Board freeboard);
		//페이징 위해 
		public List<Free_Board> selectFreeboard(Free_Board_param fbp);
		//업데이트위해 
		public String getUserid(Free_Board freeboard);
		//페이징위해 
		public int getCurPage(HttpServletRequest req);
		
}

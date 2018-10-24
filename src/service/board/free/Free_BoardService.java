package service.board.free;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.file.free.Free_FileDao;
import dto.board.Free_Board;
import dto.comment.Free_Comments;
import dto.file.Free_Filetb;
import util.Paging;

public interface Free_BoardService {
	
	
	
	// 자유게시판 전체 조회
	public List<Free_Board> selectFreeboard();
	
	// 자유게시판 글 쓰기 및 파일 업로드
	public void writeFreeboard(Free_Board freeBoard , Free_FileDao freeFileTb ); //, File file);
	
	// 자유게시판 삭제
	public void deleteFreeboard(Free_Board freeBoard);
	
	// 자유게시판 수정
	public Free_Board updateFreeboard(Free_Board freeBoard );//, File file);
	
	//게시판 제목으로 찾기
	public String searchFreeBoardBytitle(Free_Board freeBoard);

	//게시판 내용으로 찾기
	public String searchFreeBoardBycontent(Free_Board freeBoard);

	// 게시판 아이디로 찾기
	public String searchFreeBoardByuserid(Free_Board freeBoard);

	// 게시판 총 글수 출력
	public int selecntFreeBoardCntAll();

	// 게시판 리스트 페이징 처리
	public List<Free_Board> selectFreeBoardPagingList(Paging paging);

	// 추천수 올려주기
	public void updateRecommend(Free_Board freeBoard);

	//댓글번호로 조회하기
	public Free_Board searchByComment_no (Free_Board freeBoard);
	
	public List<Free_Board> selectByBoardno (Free_Board freeBoard , Free_Comments freeComments , Free_Filetb freeFileTb);

	public Free_Board getParam(HttpServletRequest req, HttpServletResponse resp);
	
	//조회수올리기
	public Free_Board view(Free_Board freeBoard);
	

}

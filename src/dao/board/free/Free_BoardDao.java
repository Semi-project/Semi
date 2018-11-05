package dao.board.free;

import java.util.List;

import dto.board.Free_Board;
import dto.board.Free_Board_param;
import dto.file.Free_Filetb;
import util.Paging;

public interface Free_BoardDao {

	// 게시판 목록보기
	public List<Free_Board> selectFreeBoard(Free_Board_param fbp);

	//게시판 글쓰기
	public void insertFreeBoard(Free_Board freeBoard);

	// 게시판 글읽기
	public Free_Board viewFreeBoard(Free_Board freeBoard);

	// 게시판 글 수정
	public void updateFreeBoard(Free_Board freeBoard);

	// 게시판 글 삭제
	public void deleteFreeBoard(Free_Board freeBoard);

	//게시판 제목으로 찾기
	public String selectFreeBoardByTitle(Free_Board freeBoard);

	//게시판 내용으로 찾기
	public String selectFreeBoardByContent(Free_Board freeBoard);

	// 게시판 아이디로 찾기
	public String selectFreeBoardByuserId(Free_Board freeboard);

	// 게시판 총 글수 출력
	public int selecntFreeBoardCntAll();

	// 게시판 리스트 페이징 처리
	public List<Free_Board> selectFreeBoardPagingList(Paging paging);

	// 조회수 올려주기
	public void updateHit(Free_Board freeBoard);

	// 추천수 올려주기
	public void updateRecommend(Free_Board freeBoard);

	//댓글번호로 조회하기
	public Free_Board selectByComment_No (Free_Board freeBoard);

	// 번호 조회 
	public int selectFreeboardno();
	
	//글 작성자 닉네임 조회
	public String selectUseridByBoardno(Free_Board freeboard);
	
	
}

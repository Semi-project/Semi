package service.board.notice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.Notice_Board;
import dto.file.Notice_Filetb;
import util.Paging;

public interface Notice_BoardService {

	// 공지사항 전체 조회
	public List<Notice_Board> selectNotice_Board();

	// 공지사항 글 쓰기 및 파일 업로드
	public void writeNotice_Board(HttpServletRequest req); // , File file);

	// 공지사항 삭제
	public void deleteNotice_Board(Notice_Board Notice_Board);

	// 공지사항 수정
	public void updateNotice_Board(Notice_Board Notice_Board);// , File file);

	// 공지사항 제목으로 찾기
	public String searchNotice_BoardBytitle(Notice_Board Notice_Board);

	// 공지사항 내용으로 찾기
	public String searchNotice_BoardBycontent(Notice_Board Notice_Board);

	// 공지사항 아이디로 찾기
	public String searchNotice_BoardByuserid(Notice_Board Notice_Board);

	// 공지사항 총 글수 출력
	public int selecntNotice_BoardCntAll();

	// 공지사항 리스트 페이징 처리
	public List<Notice_Board> selectNotice_BoardPagingList(Paging paging);

	// 공지사항 올려주기
	public void updateRecommend(Notice_Board Notice_Board);

	// 댓글번호로 조회하기
	public Notice_Board searchByComment_no(Notice_Board Notice_Board);

	// Param 가져오기
	public Notice_Board getParam(HttpServletRequest req, HttpServletResponse resp);
	
	public Notice_Board view(Notice_Board notice_boardView);

}

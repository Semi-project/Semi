package service.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.QnA;
import dto.file.QnA_Filetb;
import util.Paging;

public interface QnAService {
	
	// 자유게시판 전체 조회
	public List<QnA> selectQnA();
	
	// QnA 글 상세조회
	public QnA viewQnA(QnA qnaView);
	
	// 자유게시판 글 쓰기 및 파일 업로드
	public void writeQnA(HttpServletRequest req);
	
	// 자유게시판 삭제
	public void deleteQnA(QnA qna);
	
	// 자유게시판 수정
	public void updateQnA(HttpServletRequest req);// , File file);
	
	//게시판 제목으로 찾기
	public QnA  searchQnABytitle(QnA qna);

	//게시판 내용으로 찾기
	public QnA  searchQnABycontent(QnA qna);

	// 게시판 아이디로 찾기
	public QnA searchQnAByuserid(QnA qna);

	// 게시판 총 글수 출력
	public int selecntQnACntAll();

	// 게시판 리스트 페이징 처리
	public List getQnAPagingList(Paging paging);

	// Param 가져오기
	public QnA getParam(HttpServletRequest req, HttpServletResponse resp);
	
	public int getCurPage(HttpServletRequest req);
}

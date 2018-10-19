package dao.board;

import java.util.List;

import util.Paging;
import dto.board.Free_Board;
import dto.board.QnA;

public interface QnADao {

		// QnA 목록보기
		public List<QnA> selectQnA();

		//QnA 글쓰기
		public void insertQnA(QnA qna);

		// QnA 글읽기
		public QnA selectQnAByBoardno(QnA qna);

		// QnA 글 수정
		public void updateQnA(QnA qna);

		// QnA 글 삭제
		public void deleteQnA(QnA qna);

		// QnA 리스트 페이징 처리
		public List<QnA> selectQnAPagingList(Paging paging);

		// QnA 총 글수 출력
		public int selectQnACntAll();
		
		// QnA 다음게시글 반환
		public int selectBoardno();

		//QnA 제목으로 찾기
		public QnA selectQnABytitle(QnA qna);

		//QnA 내용으로 찾기
		public QnA selectQnABycontent(QnA qna);

		// QnA 아이디로 찾기
		public QnA selectByuserid(QnA qna);
		
		// 댓글번호로 조회
		public QnA selectByComment_no(QnA qna);
		
		
}

package dao.board.recommend;

import dto.board.Free_Board;

public interface RecommendDao {

	// 추천 수 구하기
	public int selectCountRecommend(Free_Board board);
	
	// 추천 넣기 
	public void insertRecommend(Free_Board board);
	
	// 추천 삭제
	public void deleteRecommend(Free_Board board);
	
	// 총 추천 수 구하기
	public int selectTotalRecommend(Free_Board board);
	
}



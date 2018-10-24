package dao.board;

import dto.board.Recommend;
import dto.member.Member;

public interface RecommendDao {

	public void insertRecommend(Recommend recommend);

	public int selectCntRecommend(Recommend recommend);

	public void deleteRecommend(Recommend recommend);
}

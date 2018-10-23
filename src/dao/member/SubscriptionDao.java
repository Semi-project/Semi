package dao.member;

import dto.member.Member;
import dto.member.Subscription;

public interface SubscriptionDao {


	//수신동의 여부
	public int insertSubscription(Subscription sub);

	//유저아이디  수신여부 조회
	public Subscription selectSubscriptionByuserId(Member member);

	// 수신동의 여부 수정
	public Subscription updateSubscription(Subscription sub);
}

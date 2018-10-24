package service.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.member.Member;
import dto.member.Subscription;

public interface SubscriptionService {
	/////////////////////수신 동의 /////////////////////
	//수신동의 여부
	public int insertSubscription(Subscription subscription);

	//유저아이디  수신여부 조회
	public Subscription selectSubscriptionByuserId(Member member);

	// 수신동의 여부 수정
	public Subscription updateSubscription(Subscription subscription);

	public Subscription getParam(HttpServletRequest req);
}

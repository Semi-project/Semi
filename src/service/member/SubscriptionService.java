package service.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.member.Member;
import dto.member.Subscription;

public interface SubscriptionService {
	/////////////////////수신 동의 /////////////////////
	//수신동의 여부
	public int insertSubscription(Subscription subscription);

	public Subscription getParam(HttpServletRequest req);
}

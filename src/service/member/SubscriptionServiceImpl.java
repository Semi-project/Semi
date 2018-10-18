package service.member;

import dao.member.SubscriptionDao;
import dao.member.SubscriptionDaoImpl;
import dto.member.Member;
import dto.member.Subscription;

public class SubscriptionServiceImpl implements SubscriptionService {
	private SubscriptionDao subscription = new SubscriptionDaoImpl();
	
	@Override
	public void insertSubscription(Subscription sub) {

	}

	@Override
	public Subscription selectSubscriptionByuserId(Member member) {

		return null;
	}

	@Override
	public Subscription updateSubscription(Subscription sub) {

		return null;
	}

}

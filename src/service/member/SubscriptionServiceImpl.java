package service.member;

import javax.servlet.http.HttpServletRequest;

import dao.member.SubscriptionDao;
import dao.member.SubscriptionDaoImpl;
import dto.member.Member;
import dto.member.Subscription;

public class SubscriptionServiceImpl implements SubscriptionService {
	private SubscriptionDao subscriptionDao = new SubscriptionDaoImpl();

	@Override
	public int insertSubscription(Subscription subscription) {

		return subscriptionDao.insertSubscription(subscription);
	}

	@Override
	public Subscription selectSubscriptionByuserId(Member member) {

		return null;
	}

	@Override
	public Subscription updateSubscription(Subscription sub) {

		return null;
	}

	@Override
	public Subscription getParam(HttpServletRequest req) {
		Subscription subscription = new Subscription();
		subscription.setEmailSubscription(Integer.parseInt(req.getParameter("subscriptionNews")));
		subscription.setSmsSubscription(Integer.parseInt(req.getParameter("subscriptionSms")));
		return subscription;
	}

}

package service.charity;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.charity.CharityDao;
import dao.charity.CharityDaoImpl;
import dao.payment.PaymentDao;
import dao.payment.PaymentDaoImpl;
import dto.charity.Charity;
import util.Paging;

public class CharityServiceImpl implements CharityService {

	private CharityDao charityDao = new CharityDaoImpl();

	@Override
	public Charity getParam(HttpServletRequest req, HttpServletResponse resp) {
		Charity charity = new Charity();
		charity.setImp_uid(req.getParameter("imp_uid"));
//		System.out.println(req.getParameter("imp_uid"));
		charity.setMerchant_uid(req.getParameter("merchant_uid"));
//		System.out.println(req.getParameter("merchant_uid"));
		charity.setPay_method(req.getParameter("pay_method"));
//		System.out.println(req.getParameter("pay_method"));
		charity.setName(req.getParameter("name"));
//		System.out.println(req.getParameter("name"));
		charity.setPaid_amount(Integer.parseInt(req.getParameter("paid_amount")));
//		System.out.println(req.getParameter("paid_amount"));
		charity.setBuyer_name(req.getParameter("buyer_name"));
//		System.out.println(req.getParameter("buyer_name"));
		charity.setBuyer_email(req.getParameter("buyer_email"));
//		System.out.println(req.getParameter("buyer_email"));
		charity.setUserid(req.getParameter("userid"));
//		System.out.println(req.getParameter("userid"));
		long unixTime = Long.parseLong(req.getParameter("paid_at")) * 1000;
		Date date = new Date(unixTime);
		charity.setCharity_date(date);
		// System.out.println(req.getParameter("paid_at"));
		return charity;
	}

	@Override
	public List<Charity> selectAllByUserId(Charity charity) {
		return charityDao.selectAll(charity);
	}

	@Override
	public void insertCharity(Charity charity) {

		charityDao.insert(charity);

	}

	@Override
	public int getCurPage(HttpServletRequest req) {
		// ?붿껌?뚮씪誘명꽣 諛쏄린
		String curPage = req.getParameter("curPage");

		// null?대굹 ""???꾨땲硫?int濡?由ы꽩
		if (curPage != null && !"".equals(curPage)) {
			return Integer.parseInt(curPage);
		}
		return 0;
	}

	@Override
	public int getTotalCount(String search) {
		// TODO Auto-generated method stub
		return charityDao.selectCharityCntAll(search);
	}

	@Override
	public List<Charity> getPagingList(Paging paging) {

		return charityDao.selectPagingList(paging);
	}

}
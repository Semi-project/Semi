package dto.charity;

import java.util.Date;

public class Charity {

	private String imp_uid;
	private String merchant_uid;
	private String pay_method;
	private String name;
	private int paid_amount;
	private String buyer_name;
	private String buyer_email;
	private String userid;
	private Date charity_date;

	@Override
	public String toString() {
		return "Charity [imp_uid=" + imp_uid + ", merchant_uid=" + merchant_uid + ", pay_method=" + pay_method
				+ ", name=" + name + ", paid_amount=" + paid_amount + ", buyer_name=" + buyer_name + ", buyer_email="
				+ buyer_email + ", userid=" + userid + ", charity_date=" + charity_date + "]";
	}

	public String getImp_uid() {
		return imp_uid;
	}

	public void setImp_uid(String imp_uid) {
		this.imp_uid = imp_uid;
	}

	public String getMerchant_uid() {
		return merchant_uid;
	}

	public void setMerchant_uid(String merchant_uid) {
		this.merchant_uid = merchant_uid;
	}

	public String getPay_method() {
		return pay_method;
	}

	public void setPay_method(String pay_method) {
		this.pay_method = pay_method;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPaid_amount() {
		return paid_amount;
	}

	public void setPaid_amount(int paid_amount) {
		this.paid_amount = paid_amount;
	}

	public String getBuyer_name() {
		return buyer_name;
	}

	public void setBuyer_name(String buyer_name) {
		this.buyer_name = buyer_name;
	}

	public String getBuyer_email() {
		return buyer_email;
	}

	public void setBuyer_email(String buyer_email) {
		this.buyer_email = buyer_email;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Date getCharity_date() {
		return charity_date;
	}

	public void setCharity_date(Date charity_date) {
		this.charity_date = charity_date;
	}

	/////////////////////////////////
//	private int charity_Code;
//	private int charity_Cate_Code;
//	private int charity_Amount;
//	private String userId;
//	private Date charity_Date;
//	private int charity_Payment;
//	

}

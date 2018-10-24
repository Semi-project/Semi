package dto.member;

public class Subscription {

	 // 수신동의코드 
    private int subscriptionNo;

    // 메일동의 
    private int emailSubscription;

    // SMS동의 
    private int smsSubscription;

    // 수신동의일 
    private int subscriptionDate;

	public int getSubscriptionNo() {
		return subscriptionNo;
	}

	public void setSubscriptionNo(int subscriptionNo) {
		this.subscriptionNo = subscriptionNo;
	}

	public int getEmailSubscription() {
		return emailSubscription;
	}

	public void setEmailSubscription(int emailSubscription) {
		this.emailSubscription = emailSubscription;
	}

	public int getSmsSubscription() {
		return smsSubscription;
	}

	public void setSmsSubscription(int smsSubscription) {
		this.smsSubscription = smsSubscription;
	}

	public int getSubscriptionDate() {
		return subscriptionDate;
	}

	public void setSubscriptionDate(int subscriptionDate) {
		this.subscriptionDate = subscriptionDate;
	}

	@Override
	public String toString() {
		return "Subscription [subscriptionNo=" + subscriptionNo + ", emailSubscription=" + emailSubscription
				+ ", smsSubscription=" + smsSubscription + ", subscriptionDate=" + subscriptionDate + "]";
	}

}

package dto.member;

import java.util.Date;

public class Member {
	private String userid;
	private String userpw;
	private String name;
	private String gender;
	private Date userbirth;
	private String phone;
	private String address;
	private String email;
	private int subscription_no;
	private int role_id;

	@Override
	public String toString() {
		return "Member [userid=" + userid + ", userpw=" + userpw + ", name=" + name + ", gender=" + gender
				+ ", userbirth=" + userbirth + ", phone=" + phone + ", address=" + address + ", email=" + email
				+ ", subscription_no=" + subscription_no + ", role_id=" + role_id + "]";
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserpw() {
		return userpw;
	}

	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getUserbirth() {
		return userbirth;
	}

	public void setUserbirth(Date userbirth) {
		this.userbirth = userbirth;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getSubscription_no() {
		return subscription_no;
	}

	public void setSubscription_no(int subscription_no) {
		this.subscription_no = subscription_no;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

}

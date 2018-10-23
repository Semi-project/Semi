package dto.member;

import java.sql.Date;

public class Deactivation {


    // 탈퇴아이디 
    private String userid;

    // 탈퇴사유 
    private int deactType;

    // 탈퇴 의견 
    private String deactSugg;

    // 탈퇴일 
    private Date deactDate;

    public String getUserid() {
        return userid;
    }

	public int getDeactType() {
		return deactType;
	}

	public void setDeactType(Integer deactType) {
		this.deactType = deactType;
	}

	public String getDeactSugg() {
		return deactSugg;
	}

	public void setDeactSugg(String deactSugg) {
		this.deactSugg = deactSugg;
	}

	public Date getDeactDate() {
		return deactDate;
	}

	public void setDeactDate(Date deactDate) {
		this.deactDate = deactDate;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Override
	public String toString() {
		return "Deactivation [deactDate=" + deactDate + ", deactSugg=" + deactSugg + ", deactType=" + deactType
				+ ", userid=" + userid + "]";
	}
    
}

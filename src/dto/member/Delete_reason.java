package dto.member;

public class Delete_reason {

    // 탈퇴사유 
    private int deactType;

	// 사유종류 
    private String deact;

    
    @Override
    public String toString() {
    	return "Delete_reason [deact=" + deact + ", deactType=" + deactType + "]";
    }


	public int getDeactType() {
		return deactType;
	}


	public void setDeactType(int deactType) {
		this.deactType = deactType;
	}


	public String getDeact() {
		return deact;
	}


	public void setDeact(String deact) {
		this.deact = deact;
	}

}

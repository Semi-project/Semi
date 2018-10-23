package dto.member;

public class Role {
	   // 권한코드 
    private int roleId;

    // 권한 
    private String role;

	@Override
	public String toString() {
		return "Role [role=" + role + ", roleId=" + roleId + "]";
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}


}

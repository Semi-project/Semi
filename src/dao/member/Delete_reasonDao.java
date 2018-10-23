package dao.member;

import dto.member.Delete_reason;

public interface Delete_reasonDao {
// 탈퇴 사유 넣기 	
	public void insertDeleteReason(Delete_reason delete_Reason);
// 탈퇴 사유 조회 
	public Delete_reason selectDeleteReason(Delete_reason delete_Reason);
	
	
	
}

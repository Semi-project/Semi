package dao.member;

import java.util.List;

import dto.member.Deactivation;

public interface DeactivationDao {

	//탈퇴 인원 업데이트
	public void insertDeactivation(Deactivation deactivation) throws Exception;
	//탈퇴 회원 리스트
	public List<Deactivation> selectAll();
	//	탈퇴 회원 조회
	public DeactivationDao selectDeactivationByUserId(Deactivation deactivation);
	//탈퇴 회원수 출력
	public int selectCntAll();




}
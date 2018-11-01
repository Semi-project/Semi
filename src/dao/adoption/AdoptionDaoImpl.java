package dao.adoption;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.adoption.Adoption;
import util.DBConn;
import util.Paging;

public class AdoptionDaoImpl implements AdoptionDao {

	private Connection conn = DBConn.getConnection();

	@Override
	public int selectAdoptionCntAll(String search) {

		// 전체 게시글 수 조회 쿼리
		String sql = "";
		sql += "SELECT COUNT(*) FROM adoption";
		if (search != null && !"".equals(search)) {
			sql += " WHERE animal_name LIKE '%" + search + "%'";
		}

		// DB 객체
		PreparedStatement ps = null;
		ResultSet rs = null;

		int cnt = 0;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			rs.next();

			cnt = rs.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return cnt;
	}

	@Override
	public List<Adoption> selectAll() {
		String sql = "";
		sql += "SELECT ANIMAL_NAME, " + "ADOPTION_REASON, " + "ADOPTION_EXP, " + "ADOPTION_CURANIMAL, "
				+ "ADOPTION_HOUSING, " + "ADOPTION_CALLTIME, " + "STATUS, " + "adoption_code, animal_code, "
				+ "USERID FROM adoption ORDER BY animal_name";
		// DB 객체
		PreparedStatement ps = null;
		ResultSet rs = null;

		// 최종 조회 결과 담을 List
		List<Adoption> list = new ArrayList<>();

		try {
			// DB작업
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			// 전체조회 결과 담기
			while (rs.next()) {
				Adoption b = new Adoption();

				// ResultSet의 결과 행 하나씩 DTO에 저장
				b.setAdoptionCalltime(rs.getString("adoption_calltime"));
				b.setAdoptionHousing(rs.getString("adoption_housing"));
				b.setAdoptionCuranimal(rs.getString("adoption_curanimal"));
				b.setAdoptionExp(rs.getString("adoption_exp"));
				b.setAdoptionReason(rs.getString("adoption_reason"));
				b.setAnimalName(rs.getString("animal_name"));
				b.setUserid(rs.getString("userid"));
				b.setStatus(rs.getInt("status"));
				b.setAdoptionCode(rs.getInt("adoption_code"));
				b.setAnimalCode(rs.getInt("animal_code"));

				// 조회결과를 List로 생성
				list.add(b);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// DB객체 닫기
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// 전체조회 결과 반환
		return list;

	}

	@Override
	public List<Adoption> selectPagingList(Paging paging) {
		String sql = "";
		sql += "SELECT *FROM (" + "SELECT rownum rnum, B.*FROM (" + "SELECT ANIMAL_NAME, " + "ADOPTION_REASON, "
				+ "ADOPTION_EXP, " + "ADOPTION_CURANIMAL, " + "ADOPTION_HOUSING, " + "ADOPTION_CALLTIME, " + "status, "
				+ "adoption_code, " + "animal_code, " + "USERID FROM adoption";
		if (paging.getSearch() != null && !"".equals(paging.getSearch())) {
			sql += " WHERE animal_name LIKE '%" + paging.getSearch() + "%'";
		}
		sql += " ORDER BY adoption_code desc) B ORDER BY rnum ) WHERE rnum between ? AND ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Adoption> list = new ArrayList<>();
		try {
			// DB작업
			ps = conn.prepareStatement(sql);

			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());

			rs = ps.executeQuery();

			// 조회 결과 담기
			while (rs.next()) {
				Adoption b = new Adoption();

				// ResultSet의 결과 행 하나씩 DTO에 저장
				b.setAdoptionCalltime(rs.getString("adoption_calltime"));
				b.setAdoptionHousing(rs.getString("adoption_housing"));
				b.setAdoptionCuranimal(rs.getString("adoption_curanimal"));
				b.setAdoptionExp(rs.getString("adoption_exp"));
				b.setAdoptionReason(rs.getString("adoption_reason"));
				b.setAnimalName(rs.getString("animal_name"));
				b.setUserid(rs.getString("userid"));
				b.setStatus(rs.getInt("status"));
				b.setAdoptionCode(rs.getInt("ADOPTION_CODE"));
				b.setAnimalCode(rs.getInt("animal_code"));
				// 조회결과를 List로 생성
				list.add(b);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// DB객체 닫기
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// 결과 반환
		return list;
	}

	@Override
	public List<Adoption> selectAdoptionByAnimal_name(Adoption adoption) {
		String sql = "SELECT";
		sql += "ANIMAL_NAME";
		sql += ", ADOPTION_REASON";
		sql += ", ADOPTION_EXP";
		sql += ", ADOPTION_CURANIMAL";
		sql += ", ADOPTION_HOUSING";
		sql += ", ADOPTION_CALLTIME";
		sql += ", status";
		sql += ", adoption_code";
		sql += ", animal_code";
		sql += ",USERID";
		sql += " FROM adoption WHERE animal_name=? order by adoption_calltime";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Adoption> list = new ArrayList<>();
		try {
			// DB작업
			ps = conn.prepareStatement(sql);

			ps.setString(1, adoption.getAnimalName());
			rs = ps.executeQuery();

			// 조회 결과 담기
			while (rs.next()) {
				Adoption b = new Adoption();

				// ResultSet의 결과 행 하나씩 DTO에 저장
				b.setAdoptionCalltime(rs.getString("adotpion_calltime"));
				b.setAdoptionHousing(rs.getString("adoption_housing"));
				b.setAdoptionCuranimal(rs.getString("adoption_curanimal"));
				b.setAdoptionExp(rs.getString("adoption_exp"));
				b.setAdoptionReason(rs.getString("adoption_reason"));
				b.setAnimalName(rs.getString("animal_name"));
				b.setUserid(rs.getString("userid"));
				b.setStatus(rs.getInt("status"));
				b.setAdoptionCode(rs.getInt("adoption_code"));
				b.setAnimalCode(rs.getInt("animal_code"));

				// 조회결과를 List로 생성
				list.add(b);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// DB객체 닫기
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// 결과 반환
		return list;
	}

	@Override
	public Adoption selectAdoptionByAdoption_code(Adoption adoption) {
		String sql = "SELECT ";
		sql += "ANIMAL_NAME";
		sql += ", ADOPTION_REASON";
		sql += ", ADOPTION_EXP";
		sql += ", ADOPTION_CURANIMAL";
		sql += ", ADOPTION_HOUSING";
		sql += ", ADOPTION_CALLTIME";
		sql += ", status";
		sql += ", adoption_code";
		sql += ", animal_code";
		sql += ",USERID";
		sql += " FROM adoption WHERE adoption_code=? order by adoption_calltime";
		PreparedStatement ps = null;
		ResultSet rs = null;
		// List<Adoption> list = new ArrayList<>();
		Adoption b = new Adoption();

		try {
			// DB작업
			ps = conn.prepareStatement(sql);

			ps.setInt(1, adoption.getAdoptionCode());
			rs = ps.executeQuery();

			// 조회 결과 담기
			while (rs.next()) {

				// ResultSet의 결과 행 하나씩 DTO에 저장
				b.setAdoptionCalltime(rs.getString("adoption_calltime"));
				b.setAdoptionHousing(rs.getString("adoption_housing"));
				b.setAdoptionCuranimal(rs.getString("adoption_curanimal"));
				b.setAdoptionExp(rs.getString("adoption_exp"));
				b.setAdoptionReason(rs.getString("adoption_reason"));
				b.setAnimalName(rs.getString("animal_name"));
				b.setUserid(rs.getString("userid"));
				b.setStatus(rs.getInt("status"));
				b.setAdoptionCode(rs.getInt("adoption_code"));
				b.setAnimalCode(rs.getInt("animal_code"));

				// 조회결과를 List로 생성
//				list.add(b);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// DB객체 닫기
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// 결과 반환
		return b;
	}

	@Override
	public void insertAdoption(Adoption adoption) {
		String sql = "";
		sql += "INSERT INTO adoption(" + " ANIMAL_CODE," + " ANIMAL_NAME," + " ADOPTION_CODE," + " ADOPTION_REASON,"
				+ " ADOPTION_EXP," + " ADOPTION_CURANIMAL," + " ADOPTION_HOUSING," + " ADOPTION_CALLTIME," + " USERID,"
				+ " STATUS)";
		sql += " VALUES (?,?,?,?,?,?,?,?,?,0)";

		// DB 객체
		PreparedStatement ps = null;

		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);

//					"ANIMAL_CODE," + 
			if (adoption.getAnimalCode() == -1) {
				ps.setString(1, null);
			} else {
				ps.setInt(1, adoption.getAnimalCode());
			}
			// " ANIMAL_NAME," +
			ps.setString(2, adoption.getAnimalName());
//					" ADOPTION_CODE," + 
			ps.setInt(3, adoption.getAdoptionCode());
//					" ADOPTION_REASON," +
			ps.setString(4, adoption.getAdoptionReason());
//					" ADOPTION_EXP," + 
			ps.setString(5, adoption.getAdoptionExp());

//					" ADOPTION_CURANIMAL," + 
			ps.setString(6, adoption.getAdoptionCuranimal());

//					" ADOPTION_HOUSING," + 
			ps.setString(7, adoption.getAdoptionHousing());

//					" ADOPTION_CALLTIME," + 
			ps.setString(8, adoption.getAdoptionCalltime());

//					" USERID," + 
			ps.setString(9, adoption.getUserid());

			// DB작업

			ps.executeUpdate();

			conn.commit();

		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			e.printStackTrace();
		} finally {
			try {
				// DB객체 닫기
				if (ps != null)
					ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public int selectSeqNextval() {

		// 다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "SELECT adoption_seq.nextval FROM dual";

		// DB 객체
		PreparedStatement ps = null;
		ResultSet rs = null;

		// 게시글번호
		int adoption_code = 0;

		try {
			// DB작업
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			// 결과 담기
			while (rs.next()) {
				adoption_code = rs.getInt(1);
			}
			// System.out.println(adoption_code);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// DB객체 닫기
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// 게시글 번호 반환
		return adoption_code;
	}

	@Override
	public void deleteAdoptionList(String names) {
		String sql = "DELETE FROM adoption WHERE adoption_code IN ( " + names + " )";

		// DB 객체
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);

			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void delete(Adoption adoption) {
		String sql = "";
		sql += "DELETE adoption";
		sql += "WHERE adoption_code=?";
		// DB 객체
		PreparedStatement ps = null;

		try {
			conn.setAutoCommit(false);

			// DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, adoption.getAdoptionCode());

			ps.executeUpdate();

			conn.commit();

		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			e.printStackTrace();
		} finally {
			try {
				// DB객체 닫기
				if (ps != null)
					ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void updateAdoptionList(String names) {
		String sql = "UPDATE adoption set status=1 WHERE adoption_code IN ( " + names + " )";

		// DB 객체
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);

			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void update(Adoption adoption) {
		String sql = "";
		sql += "UPDATE adoption";
		sql += "SET status=1 WHERE adoption_code=?";
		// DB 객체
		PreparedStatement ps = null;

		try {
			conn.setAutoCommit(false);

			// DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, adoption.getAdoptionCode());

			ps.executeUpdate();

			conn.commit();

		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			e.printStackTrace();
		} finally {
			try {
				// DB객체 닫기
				if (ps != null)
					ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public int selectStatusbyanimalName(Adoption adoption) {
		String sql = "";
		sql += "SELECT status FROM adoption WHERE animal_name=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		int cnt = -1;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, adoption.getAnimalName());
			rs = ps.executeQuery();

			while (rs.next()) {
				cnt = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				// DB객체 닫기
				if (ps != null)
					ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return cnt;
	}

	@Override
	public Adoption getByanimalCode(Adoption adoption) {
		String sql = "SELECT * FROM adoption WHERE animal_code=?";

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, adoption.getAnimalCode());

			rs = ps.executeQuery();

			while (rs.next()) {

				adoption.setAdoptionCalltime(rs.getString("adoption_calltime"));
				adoption.setAdoptionHousing(rs.getString("adoption_housing"));
				adoption.setAdoptionCuranimal(rs.getString("adoption_curanimal"));
				adoption.setAdoptionExp(rs.getString("adoption_exp"));
				adoption.setAdoptionReason(rs.getString("adoption_reason"));
				adoption.setAnimalName(rs.getString("animal_name"));
				adoption.setUserid(rs.getString("userid"));
				adoption.setStatus(rs.getInt("status"));
				adoption.setAdoptionCode(rs.getInt("adoption_code"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return adoption;

	}
	@Override
	public List<Adoption> selectAllByUserid(Adoption adoption) {
		
		String sql = "";
	    sql += "SELECT *FROM (" + "SELECT rownum rnum, B.*FROM (" + "SELECT ANIMAL_NAME, " + "ADOPTION_REASON, "
	          + "ADOPTION_EXP, " + "ADOPTION_CURANIMAL, " + "ADOPTION_HOUSING, " + "ADOPTION_CALLTIME, " + "status, "
	          + "adoption_code, " + "animal_code, " + "USERID FROM adoption WHERE userid = ?";
	   
	    sql += " ORDER BY adoption_code desc) B ORDER BY rnum ) WHERE rnum between ? AND ?";
		PreparedStatement ps = null;
		ResultSet rs = null ;
		
		List list = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			
			
			while(rs.next()) {
				Adoption adop = new Adoption();
				
				adop.setAdoptionCalltime(rs.getString("adoption_calltime"));
				adop.setAdoptionHousing(rs.getString("adoption_housing"));
				adop.setAdoptionCuranimal(rs.getString("adoption_curanimal"));
				adop.setAdoptionExp(rs.getString("adoption_exp"));
				adop.setAdoptionReason(rs.getString("adoption_reason"));
				adop.setAnimalName(rs.getString("animal_name"));
				adop.setUserid(rs.getString("userid"));
				adop.setStatus(rs.getInt("status"));
				adop.setAdoptionCode(rs.getInt("adoption_code"));
				adop.setAnimalCode(rs.getInt("animal_code"));
				
				
				list.add(adop);
				
			}
		
		
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}

}

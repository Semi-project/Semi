package dao.animal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.animal.Animal;
import util.DBConn;
import util.Paging;

public class AnimalDaoImpl implements AnimalDao {

	private Animal animal;

	private Connection conn = DBConn.getConnection();

	private PreparedStatement ps = null;
	private ResultSet rs = null;

	private String sql;

	@Override
	public int selectSeqNextval() {

		sql = "SELECT animal_seq.nextval FROM dual";

		int animalSeqNext = 0;

		try {
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			rs.next();

			animalSeqNext = rs.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)	rs.close();
				if(ps!=null)	ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return animalSeqNext;
	}

	@Override
	public Animal selectAnimalByanimal_Code(Animal animal) {

		sql = "SELECT * FROM animal, species";
		sql += " WHERE animal.species_code=species.species_code";
		sql += " AND animal_code=?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, animal.getAnimal_Code());

			rs = ps.executeQuery();

			while(rs.next()) {

				animal.setAnimal_Name( rs.getString("animal_name"));
				animal.setAnimal_Age( rs.getInt("animal_age"));
				animal.setAnimal_Gender_Code( rs.getString("animal_gender_code"));
				animal.setAnimal_Gr( rs.getString("animal_gr"));
				animal.setAnimal_Neuters( rs.getString("animal_neuters"));
				animal.setAnimal_Feature( rs.getString("animal_feature"));
				animal.setStatus( rs.getInt("status"));
				animal.setSpecies_Code( rs.getInt("species_code"));		
				animal.setSpecies_Name( rs.getString("species_name"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)	rs.close();
				if(ps!=null)	ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return animal;
	}

	@Override
	public void insertAnimal(Animal animal) {

		sql = "INSERT INTO animal";
		sql += " (animal_name, animal_code, animal_age, animal_gender_code,";
		sql += " animal_gr, animal_neuters, animal_feature, ";
		sql += " status, species_code)";
		sql += " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			conn.setAutoCommit(false);

			ps = conn.prepareStatement(sql);

			ps.setString(1, animal.getAnimal_Name());
			ps.setInt(2, animal.getAnimal_Code());
			ps.setInt(3, animal.getAnimal_Age());
			ps.setString(4, animal.getAnimal_Gender_Code());
			ps.setString(5, animal.getAnimal_Gr());
			ps.setString(6, animal.getAnimal_Neuters());
			ps.setString(7, animal.getAnimal_Feature());
			ps.setInt(8, 0);
			ps.setInt(9, 2002);

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
				if(ps!=null)	ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void updateAnimalByAnimal_Code(Animal animal) {

		sql = "UPDATE animal";
		sql += " SET animal_name=?";
		sql += ", animal_age=?";
		sql += ", animal_gender_code=?";
		sql += ", animal_gr=?";
		sql += ", animal_neuters=?";
		sql += ", animal_feature=?";
		sql += ", species_code=?";

		try {
			conn.setAutoCommit(false);

			ps = conn.prepareStatement(sql);

			ps.setString(1, animal.getAnimal_Name());
			ps.setInt(2, animal.getAnimal_Age());
			ps.setString(3, animal.getAnimal_Gender_Code());
			ps.setString(4, animal.getAnimal_Gr());
			ps.setString(5, animal.getAnimal_Neuters());
			ps.setString(6, animal.getAnimal_Feature());
			ps.setInt(7, animal.getSpecies_Code());

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
				if(ps!=null)	ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void deleteAnimalByAnimal_Code(Animal animal) {

		sql = "DELETE FROM animal WHERE animal_code=?";

		try {
			conn.setAutoCommit(false);

			ps = conn.prepareStatement(sql);
			ps.setInt(1, animal.getAnimal_Code());

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
				if(ps!=null)	ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void updateStatus(Animal animal) {

		sql = "UPDATE animal";
		sql += " SET status=1";
		sql += " WHERE animal_code=?";

		try {
			conn.setAutoCommit(false);

			ps = conn.prepareStatement(sql);

			ps.setInt(1, animal.getAnimal_Code());

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
				if(ps!=null)	ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List selectPagingListAdmin(Paging paging) {

		sql = "SELECT * FROM (";
		sql += " SELECT rownum rnum, A.* FROM (";
		sql += " SELECT animal_name, animal_code,";
		sql += " animal_age, animal_gender_code,"; 
		sql += " animal_gr, animal_neuters,"; 
		sql += " animal_feature, status,";
		sql += " animal.species_code, species_name";
		sql += " FROM animal, species";
		sql += " WHERE species.species_code=animal.species_code";
		sql += " ORDER BY animal_code DESC";
		sql += " ) A";
		sql += " ORDER BY rnum";
		sql += " )";
		sql += " WHERE rnum between ? AND ?";

		List list = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());

			rs = ps.executeQuery();

			while(rs.next()) {
				animal = new Animal();

				animal.setAnimal_Name( rs.getString("animal_name"));
				animal.setAnimal_Code( rs.getInt("animal_code"));
				animal.setAnimal_Age( rs.getInt("animal_age"));
				animal.setAnimal_Gender_Code( rs.getString("animal_gender_code"));
				animal.setAnimal_Gr( rs.getString("animal_gr"));
				animal.setAnimal_Neuters( rs.getString("animal_neuters"));
				animal.setAnimal_Feature( rs.getString("animal_feature"));
				animal.setStatus( rs.getInt("status"));
				animal.setSpecies_Code( rs.getInt("species_code"));
				animal.setSpecies_Name( rs.getString("species_name"));

				list.add(animal);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)	rs.close();
				if(ps!=null)	ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	@Override
	public List selectPagingListUser(Paging paging) {
		sql = "SELECT * FROM (";
		sql += " SELECT rownum rnum, A.* FROM (";
		sql += " SELECT animal_name, animal_code,";
		sql += " animal_age, animal_gender_code,"; 
		sql += " animal_gr, animal_neuters,"; 
		sql += " animal_feature, status,";
		sql += " animal.species_code, species_name";
		sql += " FROM animal, species";
		sql += " WHERE species.species_code = animal.species_code";
		sql += " AND status=1";
		sql += " ORDER BY animal_code DESC";
		sql += " ) A";
		sql += " ORDER BY rnum";
		sql += " )";
		sql += " WHERE rnum between ? AND ?";

		List list = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());

			rs = ps.executeQuery();

			while(rs.next()) {
				animal = new Animal();

				animal.setAnimal_Name( rs.getString("animal_name"));
				animal.setAnimal_Code( rs.getInt("animal_code"));
				animal.setAnimal_Age( rs.getInt("animal_age"));
				animal.setAnimal_Gender_Code( rs.getString("animal_gender_code"));
				animal.setAnimal_Gr( rs.getString("animal_gr"));
				animal.setAnimal_Neuters( rs.getString("animal_neuters"));
				animal.setAnimal_Feature( rs.getString("animal_feature"));
				animal.setStatus( rs.getInt("status"));
				animal.setSpecies_Code( rs.getInt("species_code"));
				animal.setSpecies_Name( rs.getString("species_name"));

				list.add(animal);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)	rs.close();
				if(ps!=null)	ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	@Override
	public int selectCntAll() {

		sql = "SELECT COUNT (*) FROM animal";

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
				if(rs!=null)	rs.close();
				if(ps!=null)	ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return cnt;
	}

	@Override
	public int animal_code(Animal animal) {

		int code = -1;
		String sql = "";
		sql += " SELECT a.animal_code";
		sql += " FROM animal a , adoption adop";
		sql += " WHERE a.animal_code=adop.animal_code AND";
		sql += " a.animal_name=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, animal.getAnimal_Name());

			rs = ps.executeQuery();
			while (rs.next()) {
				code = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return code;

	}

	@Override
	public void deleteAnimalList(String codes) {

		sql = "DELETE FROM animal WHERE animal_code IN ( " + codes + ")";

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
				if(ps!=null)	ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void acceptAnimalList(String codes) {

		sql = "UPDATE animal SET status=1 WHERE animal_code IN ( " + codes + ")";

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
				if(ps!=null)	ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public int selectCntAcpt() {

		sql = "SELECT COUNT (*) FROM animal WHERE status=1";

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
				if(rs!=null)	rs.close();
				if(ps!=null)	ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return cnt;
	}

	@Override
	public int aniaml_code(Animal animal) {

		int code = -1;
	      String sql = "";
	      sql += "SELECT a.animal_code " + "FROM animal a , adoption adop " + "WHERE a.animal_name=?";
	      try {
	         ps = conn.prepareStatement(sql);
	         ps.setString(1, animal.getAnimal_Name());

	         rs = ps.executeQuery();
	         while (rs.next()) {
	            code = rs.getInt(1);
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	      return code;
		
	}


}

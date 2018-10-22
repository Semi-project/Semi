package dao.animal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.animal.Animal;
import util.DBConn;

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
					
			while(rs.next())
				animalSeqNext = rs.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return animalSeqNext;
	}
	
	@Override
	public List<Animal> selectAnimal() {
		
		// 입양보내기 승인된 동물들
		// join으로 품종명 가져오기
		sql = "select * FROM animal, species";
		sql += " WHERE species.species_code = animal.species_code AND";
		sql += " status=1";
		sql += " ORDER BY animal_code DESC";

		List<Animal> list = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql);

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
	public List<Animal> selectAnimalnotAutho() {
		
		// 입양허가 안 된 동물목록 가져오기
		sql = "SELECT * FROM animal, species";
		sql += " WHERE animal.species_code=species.species_code";
		sql += " AND status=0";
		sql += " ORDER BY animal_code DESC";

		List<Animal> list = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql);

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
	public Animal selectAnimalByanimal_Code(Animal animal) {

		sql = "SELECT * FROM animal, species";
		sql += " WHERE animal.species_code=species.species_code";
		sql += " AND animal_code=?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, animal.getAnimal_Code());

			rs = ps.executeQuery();

			while(rs.next()) {

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
		sql += " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, selectSeqNextval());
			ps.setString(2, animal.getAnimal_Name());
			ps.setInt(3, animal.getAnimal_Age());
			ps.setString(4, animal.getAnimal_Gender_Code());
			ps.setString(5, animal.getAnimal_Gr());
			ps.setString(6, animal.getAnimal_Neuters());
			ps.setString(7, animal.getAnimal_Feature());
			ps.setInt(8, 0);
			ps.setInt(9, animal.getSpecies_Code());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
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
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, animal.getAnimal_Name());
			ps.setInt(2, animal.getAnimal_Age());
			ps.setString(3, animal.getAnimal_Gender_Code());
			ps.setString(4, animal.getAnimal_Gr());
			ps.setString(5, animal.getAnimal_Neuters());
			ps.setString(6, animal.getAnimal_Feature());
			ps.setInt(7, animal.getSpecies_Code());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
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
			ps = conn.prepareStatement(sql);
			ps.setInt(1, animal.getAnimal_Code());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
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
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, animal.getAnimal_Code());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps!=null)	ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


}

package dao.animal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.animal.Species;
import util.DBConn;

public class SpeciesDaoImpl implements SpeciesDao {

	private Connection conn = DBConn.getConnection();

	private PreparedStatement ps = null;
	private ResultSet rs = null;

	private String sql;

	@Override
	public List selectSpeciesAll() {

		List list = new ArrayList<>();
		
		list.add(this.selectDogs());
		list.add(this.selectCats());
		list.add(this.selectEtc());		
		
		return list;
	}

	@Override
	public List<Species> selectDogs() {

		List<Species> list = new ArrayList<>();

		sql = "SELECT * FROM species WHERE species_code LIKE '2%'";
		sql += " ORDER BY species_code DESC";

		try {
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			while(rs.next()) {
				Species species = new Species();

				species.setSpecies_Code( rs.getInt("species_code"));
				species.setSpecies_Name( rs.getString("species_name"));

				list.add(species);
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
	public List<Species> selectCats() {

		List<Species> list = new ArrayList<>();

		sql = "SELECT * FROM species WHERE species_code LIKE '3%'";
		sql += " ORDER BY species_code DESC";

		try {
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			while(rs.next()) {
				Species species = new Species();

				species.setSpecies_Code( rs.getInt("species_code"));
				species.setSpecies_Name( rs.getString("species_name"));

				list.add(species);
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
	public List<Species> selectEtc() {
		
		List<Species> list = new ArrayList<>();
		
		sql = "SELECT * FROM species WHERE species_code LIKE '4%'";
		sql += " ORDER BY species_code DESC";

		try {
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			while(rs.next()) {
				Species species = new Species();

				species.setSpecies_Code( rs.getInt("species_code"));
				species.setSpecies_Name( rs.getString("species_name"));

				list.add(species);
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

}

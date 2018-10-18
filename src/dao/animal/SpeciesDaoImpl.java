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
	public List<Species> selectSpeciesAll() {
		
		List<Species> list = new ArrayList<>();
		
		sql = "SELECT * FROM species";
		
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

package dao.file.animal;

///dddddd
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.file.Animal_Filetb;
import util.DBConn;

public class Animal_FileDaoImpl implements Animal_FileDao {

	private Connection conn = DBConn.getConnection();
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	private String sql;

	@Override
	public void insertFiletb(Animal_Filetb animal_filetb) {
		sql = "INSERT INTO animal_filetb(FILENO, ANIMAL_CODE, FILE_ORIGINNAME, FILE_SAVENAME )";
		sql += " VALUES (?, ?, ?, ?)";

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, animal_filetb.getFileno());
			ps.setInt(2, animal_filetb.getAnimal_Code());
			ps.setString(3, animal_filetb.getFile_OriginName());
			ps.setString(4, animal_filetb.getFile_SaveName());

			
			
			ps.executeUpdate();

		} catch (SQLException e) {
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
	public void deleteFiletbByAnimalCode(Animal_Filetb animal_filetb) {

		sql = "DELETE FROM animal_filetb";
		sql += " WHERE animal_code=?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, animal_filetb.getAnimal_Code());

			ps.executeUpdate();

		} catch (SQLException e) {
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
	public List<Animal_Filetb> selectFiletbByAnimalCode(Animal_Filetb animal_filetb) {

		List<Animal_Filetb> list = new ArrayList<>();

		sql = "SELECT * FROM animal_filetb";
		sql += " WHERE boardno=?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, animal_filetb.getAnimal_Code());

			rs = ps.executeQuery();

			while (rs.next()) {

				animal_filetb = new Animal_Filetb();

				animal_filetb.setFileno(rs.getInt("fileno"));
				animal_filetb.setAnimal_Code(rs.getInt("animal_code"));
				animal_filetb.setFile_OriginName(rs.getString("file_originName"));
				animal_filetb.setFile_SaveName(rs.getString("file_saveName"));

				list.add(animal_filetb);

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

		return list;
	}

	@Override
	public int selectFileno() {

		int animalCode = 0;

		sql = "SELECT animal_filetb_seq.nextval FROM dual";

		try {
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			rs.next();

			animalCode = rs.getInt(1);

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

		return animalCode;
	}

	@Override
	public void deleteAnimalListFile(String codes) {

		sql = "DELETE FROM animal_filetb WHERE animal_code IN ("+ codes + ")";
		
		try {
			ps = conn.prepareStatement(sql);
			
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

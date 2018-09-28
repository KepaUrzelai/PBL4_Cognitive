package conexionBDyMIKROS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class DoctorDAO implements Interface_Doctor{

	Connection conexion;
    ConexionBDCognitive bd;
    Statement statement = null;
	
	public DoctorDAO(ConexionBDCognitive bd) {
		this.conexion=bd.getConexion();
		this.statement=bd.getStatement();
		this.bd = bd;
	}

	@Override
	public boolean comprobarDoctor(String nombre, String contrasena) {
		ResultSet result;
		String query;
		query = "select usuario, contraseña from doctor where usuario = '" + nombre+"' and contraseña='" +
				contrasena + "';";
		result = bd.select(query);
		try {
			while(result.next()) {
        	return true;
        	}
        	} catch (SQLException e) {
        		
		}
        return false;		
	}
	
}

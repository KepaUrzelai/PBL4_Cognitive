package conexionBDyMIKROS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import usuarioYcronometro.Usuario;

public class EjercitarDAO implements Interface_Ejercitar{
	
	Connection conexion;
    ConexionBDCognitive bd;
    Statement statement = null;
	
	public EjercitarDAO(ConexionBDCognitive bd) {
		this.conexion=bd.getConexion();
		this.statement=bd.getStatement();
		this.bd = bd;
	}

	@Override
	public void guardarEjercitar(String fecha, int actividadID, String horas, Usuario usuario) {
		ResultSet result;
		int ID = 0;
		String valores[];
    	valores = usuario.getNombre().split("[ ]");
    	String query;
    	query = "select pacienteID from paciente where nombre = '" + valores[0]+"' and apellido1='" +
  	          valores[1] + "' and apellido2 = '" + valores[2] + "';";
  	    result = bd.select(query);
  	    try {
			while(result.next()) {
				ID=result.getInt("pacienteID");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
  	    int hora=0;
  	    int minuto = 0;
  	    int segundo = Integer.parseInt(horas);
  	    while(segundo>=60) {
  	    	minuto++;
  	    	segundo=segundo-60;
  	    }
  	    while(minuto>=60) {
  	    	hora++;
  	    	minuto=minuto-60;
  	    }
        try{
          statement = conexion.createStatement();
          query = "insert into ejercitar values ("+ ID+","+actividadID + ",'" +hora+ ":" + minuto + ":" + segundo + "','" + fecha + "')";
          statement.execute(query);
        }catch(SQLException e){
          System.out.println("Error al meter horas");
          System.out.println(e.toString());
          
        }
		
	}

	
}

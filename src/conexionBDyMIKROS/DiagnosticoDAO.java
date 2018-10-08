package conexionBDyMIKROS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import usuarioYcronometro.Puntuaciones;
import usuarioYcronometro.PuntuacionesAlto;
import usuarioYcronometro.PuntuacionesBaja;
import usuarioYcronometro.PuntuacionesMedio;
import usuarioYcronometro.Usuario;

public class DiagnosticoDAO implements Interface_Diagnostico{

    Connection conexion;
    ConexionBDCognitive bd;
    Statement statement = null;
    ResultSet result;
    private static int ID=2;
    
	public DiagnosticoDAO(ConexionBDCognitive bd) {
		//bd= new ConexionBDCognitive(2);
		this.conexion=bd.getConexion();
		this.statement=bd.getStatement();
		this.bd = bd;
	}

	/*
	 * @params valores[] is separating de full name into name and adresses
	 * @params Puntuaciones is for getting the points
	 * @params usuario is the user 
	 * @params query is for making a search on the database.
	 * @return true or false if the statement is well executed.
	 */
	@Override
    public boolean guardarPuntuaciones(Usuario usuario){//AQUÍ SE GUARDAN LAS PUNTUACIONES DE UN USUARIO EN LA TABLA DIAGNOSTICO
        
		String valores[];
    	valores = usuario.getNombre().split("[ ]");
    	Puntuaciones p = usuario.getPuntuaciones();
    	p.calcularBaremos();
        try{
          statement = conexion.createStatement();
          String query= null;
          query= "insert into diagnosticar values ('" + usuario.getAno() + "-" + (usuario.getMes() + 1) + "-" + usuario.getDia() + "'," + 
          p.getcAtencion() + "," + p.getcMemoria() + "," + p.getcAgilidad()+","+p.getcRazVerbal()+","+p.getcRazMat() + 
          "," + p.getAtencionDirecto() + ","+ p.getAtencionInverso()+","+ p.getcMemoria()+ ","+ p.getAgilidadMental() + 
          "," + p.getRazVerbalAnimales()+ ","+ p.getRazVerbalAbstraccion()+ ","+ p.getRazMatCalculos()+"," + p.getRazMatProblemas()+
          "," + 1 + "," + "(select pacienteID from paciente where nombre = '" + valores[0]+"' and apellido1='" + 
          			   valores[1] + "' and apellido2 = '" + valores[2] + "')" +");";
          return statement.execute(query);
        }catch(SQLException e){
          System.out.println("Error al crear usuario");
          System.out.println(e.toString());
          return false;
        }
      }
	
	/*
	 /@params valores[] is separating de full name into name and adresses
	 /@params usuario is the user we want
	 /@params query is for making a search on the database.
	 /@params ID is the id of the user
	 /@return the function return a user.
	 */
	@Override
	public Usuario cargarPuntuaciones(String nombre) {//ESTA FUNCIÓN RETURNEA UN USUARIO CON SUS NIVELES EN LOS CAMPOS COGNITIVO
		Usuario usuario = null;
		int ID=0;
		String valores[];
    	valores = nombre.split("[ ]");
	          String query;
	          query = "select pacienteID, nombre, apellido1, apellido2, poblacion, fechaNac, escolaridad from paciente where nombre = '" + valores[0]+"' and apellido1='" +
	          valores[1] + "' and apellido2 = '" + valores[2] + "';";
	          result = bd.select(query);
        try {
        	while(result.next()) {
        	ID=result.getInt("pacienteID");
			usuario=(new Usuario(result.getString("nombre")+ " "+ result.getString("apellido1")+ " "+result.getString("apellido2"), 
					result.getString("poblacion"), result.getString("fechaNac"), result.getInt("escolaridad")));
        	}
        	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	        String query1;
	        query1 = "select fecha, ptAtencion, ptMemoria, ptAgilidad, ptRVerbal, ptMatematica from diagnosticar where pacienteID = (select pacienteID from paciente where"
	        		+ " nombre = '" + valores[0]+"' and apellido1='" + valores[1] + "' and apellido2 = '" + valores[2] + "') order by fecha desc limit 1;";
	        result = bd.select(query1);
	    try {
			while(result.next()) {
			if(usuario.getEscolaridad()==0) {
				try {
					usuario.setPuntuaciones(new PuntuacionesBaja(result.getInt("ptAtencion"), result.getInt("ptMemoria"), result.getInt("ptAgilidad"),
							result.getInt("ptMatematica"), result.getInt("ptRVerbal")));
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(usuario.getEscolaridad()==1) {
				try {
					usuario.setPuntuaciones(new PuntuacionesMedio(result.getInt("ptAtencion"), result.getInt("ptMemoria"), result.getInt("ptAgilidad"),
							result.getInt("ptMatematica"), result.getInt("ptRVerbal")));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				try {
					usuario.setPuntuaciones(new PuntuacionesAlto(result.getInt("ptAtencion"), result.getInt("ptMemoria"), result.getInt("ptAgilidad"),
							result.getInt("ptMatematica"), result.getInt("ptRVerbal")));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usuario;
	}
	
	/*
	 /@params valores[] is separating de full name into name and adresses
	 /@params usuario is the user we want
	 /@params query is for making a search on the database.
	 /@params ID is the id of the user
	 /@return the function return a user.
	 */
	@Override
	public Usuario cargarPuntuacionesDoctor(String nombre) {//ESTA FUNCIÓN RETURNEA UN USUARIO CON LAS PUNTUACIONES.
		Usuario usuario = null;
		int ID=0;
		String valores[];
    	valores = nombre.split("[ ]");
	          String query;
	          query = "select pacienteID, nombre, apellido1, apellido2, poblacion, fechaNac, escolaridad from paciente where nombre = '" + valores[0]+"' and apellido1='" +
	          valores[1] + "' and apellido2 = '" + valores[2] + "';";
	          result = bd.select(query);
        try {
        	while(result.next()) {
        	ID=result.getInt("pacienteID");
			usuario=(new Usuario(result.getString("nombre")+ " "+ result.getString("apellido1")+" "+ result.getString("apellido2"), 
					result.getString("poblacion"), result.getString("fechaNac"), result.getInt("escolaridad")));
        	}
        	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        /*	statement = conexion.createStatement();*/
	        String query1;
	        query1 = "select fecha, jAtencion1, jAtencion2, jMemoria, jAgilidad,"
	        		+ "jRazVerbal1, jRazVerbal2, jRazMat1, jRazMat2 from diagnosticar where pacienteID = " + ID +" order by fecha desc limit 1;";
	        result = bd.select(query1);
	    try {
			while(result.next()) {
			if(usuario.getEscolaridad()==0) {
				try {
					usuario.setPuntuaciones(new PuntuacionesBaja(result.getInt("jMemoria"), result.getInt("jAgilidad"), result.getInt("jAtencion1"),
							result.getInt("jAtencion2"), result.getInt("jRazMat1"), result.getInt("jRazMat2"), result.getInt("jRazVerbal1"), result.getInt("jRazVerbal2")));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(usuario.getEscolaridad()==1) {
				try {
					usuario.setPuntuaciones(new PuntuacionesMedio(result.getInt("jMemoria"), result.getInt("jAgilidad"), result.getInt("jAtencion1"),
							result.getInt("jAtencion2"), result.getInt("jRazMat1"), result.getInt("jRazMat2"), result.getInt("jRazVerbal1"), result.getInt("jRazVerbal2")));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				try {
					usuario.setPuntuaciones(new PuntuacionesAlto(result.getInt("jMemoria"), result.getInt("jAgilidad"), result.getInt("jAtencion1"),
							result.getInt("jAtencion2"), result.getInt("jRazMat1"), result.getInt("jRazMat2"), result.getInt("jRazVerbal1"), result.getInt("jRazVerbal2")));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usuario;
	}
	

	/*
	 /@params valores[] is separating de full name into name and adresses
	 /@params puntuacionesLista for saving the scores of the user
	 /@params usuario is the user we want
	 /@params query is for making a search on the database.
	 /@params ID is the id of the user
	 /@return return a list with all the historical scores of the user
	 */
	@Override
	public List<Puntuaciones> cargarPuntuacionesHistorial(String nombre) {
		List<Puntuaciones> puntuacionesLista;
		puntuacionesLista = new ArrayList<>();
		Usuario usuario = null;
		int ID=0;
		String valores[];
    	valores = nombre.split("[ ]");
        /*    statement = conexion.createStatement(); */
	          String query;
	          query = "select pacienteID, nombre, apellido1, apellido2, poblacion, fechaNac, escolaridad from paciente where nombre = '" + valores[0]+"' and apellido1='" +
	          valores[1] + "' and apellido2 = '" + valores[2] + "';";
	        /*  result = statement.executeQuery(query); */
	          result = bd.select(query);
        try {
        	while(result.next()) {
        	ID=result.getInt("pacienteID");
			usuario=(new Usuario(result.getString("nombre")+" " +result.getString("apellido1")+" " + result.getString("apellido2"), 
					result.getString("poblacion"), result.getString("fechaNac"), result.getInt("escolaridad")));
        	}
        	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        /*	statement = conexion.createStatement(); */
	        String query1;
	        query1 = "select fecha, ptAtencion, ptMemoria, ptAgilidad, ptRVerbal, ptMatematica from diagnosticar where pacienteID = " + ID +" order by fecha desc;";
	        result = bd.select(query1);
	    try {
			while(result.next()) {
			if(usuario.getEscolaridad()==0) {
				try {
					puntuacionesLista.add(new PuntuacionesBaja(result.getInt("ptAtencion"), result.getInt("ptMemoria"), result.getInt("ptAgilidad"),
							result.getInt("ptMatematica"), result.getInt("ptRVerbal"), result.getString("fecha")));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(usuario.getEscolaridad()==1) {
				try {
					puntuacionesLista.add(new PuntuacionesMedio(result.getInt("ptAtencion"), result.getInt("ptMemoria"), result.getInt("ptAgilidad"),
							result.getInt("ptMatematica"), result.getInt("ptRVerbal"), result.getString("fecha")));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				try {
					puntuacionesLista.add(new PuntuacionesAlto(result.getInt("ptAtencion"), result.getInt("ptMemoria"), result.getInt("ptAgilidad"),
							result.getInt("ptMatematica"), result.getInt("ptRVerbal"), result.getString("fecha")));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return puntuacionesLista;
	}
	
	
	/*
	 /@params valores[] is separating de full name into name and adresses
	 /@params nombre the name of the user
	 /@params query is for making a search on the database.
	 /@params contraseña we will store the password here
	 /@return if the user exits the function will return the password to compare it later.
	 */
	@Override
	public String comprobarPaciente(String nombre, JDialog dialog) {//AQUÍ SE COMPRUEBA SI EL PACIENTE EXISTE
		String valores[];
		String contraseña = null;
    	valores = nombre.split("[ ]");
	          String query;
	          query = "select pacienteID, nombre, apellido1, apellido2, poblacion, contraseña, fechaNac, escolaridad from paciente where nombre = '" + valores[0]+"' and apellido1='" +
	          valores[1] + "' and apellido2 = '" + valores[2] + "';";
	        /*  result = statement.executeQuery(query);*/
	          System.out.println(query);
	          result = bd.select(query);
        try {
        	while(result.next()) {
        	contraseña = result.getString("contraseña");
        	}
        	} catch (SQLException e) {
        		JOptionPane.showConfirmDialog(dialog, "Este usuario1 no existe", "Error",
    					JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
		}
        return contraseña;
	}
}
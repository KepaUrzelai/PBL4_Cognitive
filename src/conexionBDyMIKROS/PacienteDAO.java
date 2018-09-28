package conexionBDyMIKROS;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import usuarioYcronometro.Puntuaciones;
import usuarioYcronometro.Usuario;



public class PacienteDAO implements Interface_Paciente{

	Connection conexion;
    ConexionBDCognitive bd;
    Statement statement = null;
    ResultSet result;
    private static int ID=2;
    
  public PacienteDAO(ConexionBDCognitive bd) {
    this.conexion=bd.getConexion();
    this.statement=bd.getStatement();
    this.bd = bd;
  }
	
	
	@Override
	 public boolean createUser(Usuario usuario){
	    	String valores[];
	    	valores = usuario.getNombre().split("[ ]");
	        try{
	          statement = conexion.createStatement();
	          String query;
	          query = "insert into paciente(nombre, apellido1, apellido2, poblacion, contraseña, "
	          		+ "fechaNac, escolaridad, doctorID) values ('" + valores[0] +"','"+ valores[1] + "','" + valores[2] + "','" +
	        		  usuario.getPoblacion() + "','" + usuario.getContraseña() + "','" +usuario.getFechaNacimiento() + "','" + usuario.getEscolaridad() +"'," + 1 + ")";
	          return statement.execute(query);
	        }catch(SQLException e){
	          System.out.println("Error al crear usuario");
	          System.out.println(e.toString());
	          return false;
	        }
	      }


	@Override
	public void sumarTiempo(Usuario usuario, int tipoJuego, String tiempo) {
		ResultSet result;
		String campo;
		String anterior=null;
		campo = seleccionarCampo(tipoJuego);
		String valores[];
		String valores2[] = null;
    	valores = usuario.getNombre().split("[ ]");
        String query="select " +campo + " from paciente where nombre = '" + valores[0]+"' and apellido1='" + valores[1] + "' and apellido2 = '" + valores[2] + "';";
        result=bd.select(query);
		try {
			
			while(result.next() && result!=null) {
				anterior=result.getString(campo);
				if (anterior!=null) {
			 	valores2 = anterior.split("[:]");
				}else {
					valores2 = new String[3];
					valores2[0]="0";
					valores2[1]="0";
					valores2[2]="0";
				}
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			valores2[0]="0";
			valores2[1]="0";
			valores2[2]="0";
		}        	
		System.out.println(valores2[2]);
		int segundos = Integer.parseInt(valores2[2]) + Integer.parseInt(tiempo);
		int minuto =  Integer.parseInt(valores2[1]);
		int hora =  Integer.parseInt(valores2[0]);
		while (segundos >=60) {
			minuto++;
			segundos=segundos-60;
		}
		while (minuto >=60) {
			hora++;
			minuto=minuto-60;
		}
        query="update paciente set "+ campo + "= '" + hora + ":"+ minuto+ ":"+ segundos + "' where nombre = '" + valores[0]+"' and apellido1='" + valores[1] + "' and apellido2 = '" + valores[2] + "';";
        	        		
		bd.update(query);
		
	}


	private String seleccionarCampo(int tipo) {
		String campo;
		if(tipo==0) campo="tmpAtencion";
		else if(tipo==1) campo="tmpMemoria";
		else if(tipo==2) campo="tmpAgilidad";
		else if(tipo==3) campo="tmpRVerbal";
		else campo="tmpRMat";
		
		return campo;
	}
	
	@Override
	  public List<String> cargarUsers(int doctorID) {
	    List<String> pacientes = new ArrayList<>();
	    ResultSet result;
	    
	        String query;
	        query = "select nombre,apellido1,apellido2 from paciente where doctorId = '"+doctorID+"';";
	    result = bd.select(query);

	    try {
	    	while(result.next()) {
	    		pacientes.add(result.getString("apellido1")+" "+result.getString("apellido2")+", "+result.getString("nombre"));  
	    	}
	    } catch (SQLException e) {
	      // TODO Auto-generated catch block
	    	e.printStackTrace();
	    }
	    
	    return pacientes;
	  }


	  @Override
	  public String cogerHoras(String str, String nombre) {
	    ResultSet result;
	    String cadena[];
	    String tiempo = new String();
	    
	    cadena = nombre.split(" ");
	    
	    String query = "select "+str+" from paciente where pacienteID = "
	        +"(select pacienteID from paciente where nombre = '"+cadena[0]+"' and apellido1 = '"+cadena[1]+"' and apellido2 = '"+cadena[2]+"');";
	    
	    result = bd.select(query);

	    try {
	      while(result.next()) {
	        tiempo = result.getString(str);
	      }
	    } catch (SQLException e) {
	      System.out.println("No hay horas registradas en este campo");
	    }
	    return tiempo;
	  }

}

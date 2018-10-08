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
  /*This function is the constructor of the class PacienteDAO*/
  public PacienteDAO(ConexionBDCognitive bd) {
    this.conexion=bd.getConexion();
    this.statement=bd.getStatement();
    this.bd = bd;
  }
	
	/*This function creates a new user in the database
	/@params String valores[] is the array that stores each name username and lastname
	/params Usuario usuario is the structure with the whole information about the patient
	/@return statement.execute(query)); is a boolean type return that reaffirm the fact that the query has been executed
	/@return false the query has not been correctly executed
	*/
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

/*This function sums all the punctuation  
/@params ResultSet result stores the content of the query
/@params String campo i the cognitive field of the game 
/@params String anterior 
/@params String valores[] is the array that stores each name username and lastname
/@params String valores2[] is the array with the time of a game(the punctuation)
/@params String query the query to execute on the database
/@params Int segundos seconds 
/@params Int minutos minutes 
/@params Int horas hours 
*/
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

/*This function changes the number of the field of game(number) with the name of the field(string)
/@params String campo the different name of the fields(string)
/@return String campo the different name of the fields(string)

*/
	private String seleccionarCampo(int tipo) {
		String campo;
		if(tipo==0) campo="tmpAtencion";
		else if(tipo==1) campo="tmpMemoria";
		else if(tipo==2) campo="tmpAgilidad";
		else if(tipo==3) campo="tmpRVerbal";
		else campo="tmpRMat";
		
		return campo;
	}
/*This function search on the database a user by the doctor
/@params List<String> pacientes a list with the different pacients of a doctor
/@params ResultSet result stores the content of the query
/@return List<String> pacientes a list with the different pacients of a doctor
*/
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

package conexionBDyMIKROS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionBDCognitive {
    String db = "cognitive";
    String usuario;  
    String contraseña;
    String url = "jdbc:mysql://localhost/"+db+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    Connection conexion;
    Statement statement = null;
    ResultSet result;
    private static int ID=1;
    
    public ConexionBDCognitive(int i) {
    	if(i == 1) {
    		usuario = new String("paciente");
    		contraseña = new String("paciente");
    	}else if(i == 2) {
    		usuario = new String("doctor");
    		contraseña = new String("doctor");
    	}
    	else {
    		usuario= new String ("esencia");
    		contraseña = new String("talycual");
    	}
    	
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, usuario, contraseña);
            if (conexion != null) {
                statement = conexion.createStatement();
                System.out.println("Conectado a la BD " + db);
            } else
                System.out.println("Error al conectar");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }	
    }
  
    public String filtrarStr(String str) {
    	char[] arrayStr;
    	char[] tempArray;
    	tempArray = new char[100];
    	int lenght;
    	arrayStr = str.toCharArray();
    	lenght = arrayStr.length;
    	for(int i = 0; i<lenght; i++){
    		if(arrayStr[i] != ';') tempArray[i] = arrayStr[i];
    		else break;
    	}
    	return String.valueOf(tempArray);
   	}
    
   	public ResultSet select(String query){
        try{
          statement = conexion.createStatement();
          result = statement.executeQuery(query);
          return result;
        }catch(SQLException e){
          System.out.println("Error al ejecutar el comando select");
          System.out.println(e.toString());
          return null;
        }
      }
    
    public int update(String query){
        try{
          statement = conexion.createStatement();
          return statement.executeUpdate(query);
        }catch(SQLException e){
          System.out.println("Error al ejecutar el comando update");
          System.out.println(e.toString());
          return -1;
        }
      }
    
    public Interface_Paciente getPaciente() {
		return new PacienteDAO(this);	
    }
    
    public Interface_Diagnostico getDiagnostico() {
 		return new DiagnosticoDAO(this);	
     }
    
    public Interface_Ejercitar getEjercitar() {
 		return new EjercitarDAO(this);	
     }
    
    public Interface_Doctor getDoctor() {
 		return new DoctorDAO(this);	
     }
    
	public Connection getConexion() {
		return conexion;
	}

	public Statement getStatement() {
		return statement;
	}


	public int delete(String query){
        try{
          statement = conexion.createStatement();
          return statement.executeUpdate(query);
        }catch(SQLException e){
          System.out.println("Error al ejecutar el comando delete");
          System.out.println(e.toString());
          return -1;
        }
      } 
  
    public void close(){
        try{
        	if (statement!=null) {
        		statement.close();
        	}
            if (result!=null) {
            	result.close();
            }
            
        }catch(SQLException e){
          System.out.println("Error al cerrar");
          System.out.println(e.toString());
        }
      }
}

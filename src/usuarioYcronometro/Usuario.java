package usuarioYcronometro;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

import conexionBDyMIKROS.ConexionBDCognitive;
import conexionBDyMIKROS.DiagnosticoDAO;
import conexionBDyMIKROS.PacienteDAO;

public class Usuario implements Serializable{
	
	String nombre;
	String contraseña;
	String poblacion;
	Fecha fechaNacimiento;
	Puntuaciones puntuaciones;
	int escolaridad;
	Hora horasJugadas;
	int estado=0;
	File file;
	int dia, mes, ano;
	List<Puntuaciones> listaSesiones;
	int IDdoctor;
	String fecha;
	
	
	public Usuario(String nombre, String contraseña,String poblacion, Fecha fecha, int escolaridad) {
		
		this.nombre=nombre;
		this.contraseña=contraseña;
		this.fechaNacimiento=fecha;
		this.poblacion=poblacion;
		horasJugadas=new Hora(0,0,0);
		this.escolaridad=escolaridad;
		crearFichero();
		ficheroDatos();
		guardarDatos();
	}
	public Usuario(String nombre,String poblacion, String fecha, int escolaridad) {
		
		this.nombre=nombre;
		this.fecha=fecha;
		this.poblacion=poblacion;
		this.escolaridad=escolaridad;
		horasJugadas=new Hora(0,0,0);
	}
	
	public String getFecha() {
		return fecha;
	}
	public void setIDdoctor(int id) {
		this.IDdoctor=id;
	}
		
	public void setFechaDiag(int dia, int mes, int ano) {
		this.dia=dia;
		this.mes=mes;
		this.ano=ano;
	}
		
	public String getContraseña() {
		return contraseña;
	}
	
	public void cargarSesiones() {
		ConexionBDCognitive conexion = new ConexionBDCognitive(2);
		DiagnosticoDAO datos = new DiagnosticoDAO(conexion);
		listaSesiones = datos.cargarPuntuacionesHistorial(nombre);
	}
	
	public List<Puntuaciones> getListaSesiones() {
		return listaSesiones;
	}

	private void guardarDatos() {
		ConexionBDCognitive consulta;
		consulta= new ConexionBDCognitive(1);
		PacienteDAO paciente=(PacienteDAO) consulta.getPaciente();
		paciente.createUser(this);
		consulta.close();
		
	}

	public String getPoblacion() {
		return poblacion;
	}

	public int getEscolaridad() {
		return escolaridad;
	}

	public Fecha getFechaNacimiento() {
		return fechaNacimiento;
	}

	private void ficheroDatos() {//escribimos los datos del usuario en el fichero, las puntuaciones no.
		ObjectOutputStream out = null;
		
		try {
			out = new ObjectOutputStream ( new FileOutputStream("Usuarios/"+nombre+"/datos.txt"));
		
			out.writeObject(this);
			
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (out!=null){
				try { out.close(); } catch (IOException e) {}
			}
		}
		
	}
	
	public void ficheroPuntuacion() {//escribimos las puntuaciones en un fichero.
		ObjectOutputStream out = null;
		
		try {
			out = new ObjectOutputStream ( new FileOutputStream("Usuarios/"+nombre+"/puntuaciones.txt"));
			
			out.writeObject(this.getPuntuaciones());
			
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (out!=null){
				try { out.close(); } catch (IOException e) {}
			}
		}
		
		//de aqui en adelante se copian en la BD
		ConexionBDCognitive consulta;
		consulta= new ConexionBDCognitive(1);
		DiagnosticoDAO paciente=(DiagnosticoDAO) consulta.getDiagnostico();
		paciente.guardarPuntuaciones(this);
		consulta.close();
		
	}

	private void crearFichero() {
		file = new File("Usuarios/"+nombre);

		// if the directory does not exist, create it
		if (!file.exists()) {
		    System.out.println("creating directory: " + file.getName());
		    boolean result = false;

		    try{
		        file.mkdir();
		        result = true;
		    } 
		    catch(SecurityException se){
		        //handle it
		    }        
		    if(result) {    
		        System.out.println("DIR created");  
		    }
		}
		
	}
	
	public int getDia() {
		return dia;
	}

	public int getMes() {
		return mes;
	}

	public int getAno() {
		return ano;
	}

	public int getIDdoctor() {
		return IDdoctor;
	}

	public String getNombre() {
		return nombre;
	}

	public Puntuaciones getPuntuaciones() {
		return puntuaciones;
	}

	public void setPuntuaciones(Puntuaciones puntuaciones) {
		this.puntuaciones = puntuaciones;
	}
	
	public boolean comprobarContraseña(String contraseña) {
		if(this.contraseña.equals(contraseña)) return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", contraseña=" + contraseña + ", poblacion=" + poblacion
				+ ", fechaNacimiento=" + fechaNacimiento + ", escolaridad=" + escolaridad + "]";
	}


}

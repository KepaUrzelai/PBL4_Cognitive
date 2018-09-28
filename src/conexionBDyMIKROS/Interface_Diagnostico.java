package conexionBDyMIKROS;

import java.util.List;

import javax.swing.JDialog;

import usuarioYcronometro.Puntuaciones;
import usuarioYcronometro.Usuario;

public interface Interface_Diagnostico {
	public boolean guardarPuntuaciones(Usuario usuario);
	public Usuario cargarPuntuaciones(String nombre);
	public String comprobarPaciente(String nombre, JDialog dialog);
	public Usuario cargarPuntuacionesDoctor(String nombre);
	public  List<Puntuaciones> cargarPuntuacionesHistorial(String nombre);

}

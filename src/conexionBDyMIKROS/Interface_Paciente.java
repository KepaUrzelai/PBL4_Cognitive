package conexionBDyMIKROS;

import java.util.List;

import usuarioYcronometro.Usuario;

public interface Interface_Paciente {
	
	public boolean createUser(Usuario usuario);
	public void sumarTiempo(Usuario usuario, int tipoJuego, String tiempo);
	public List<String> cargarUsers(int doctorID);
	public String cogerHoras(String str, String nombre);
}

package mpDoctor;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;

import usuarioYcronometro.Puntuaciones;
import usuarioYcronometro.Usuario;

public class PerfilPacienteModeloSesion extends DefaultListModel<Puntuaciones>{
	private static final long serialVersionUID = 1L;
	List<Puntuaciones> lista;
	
	public PerfilPacienteModeloSesion(Usuario usuario){
		lista = new ArrayList<>();
		usuario.cargarSesiones();
		lista = usuario.getListaSesiones();
	}

	@Override
	public Puntuaciones getElementAt(int arg0) {
		// TODO Auto-generated method stub
		return lista.get(arg0);
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return lista.size();
	}
}

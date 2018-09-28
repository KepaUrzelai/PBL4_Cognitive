package mpDoctor;

import javax.swing.JFrame;

public class PerfilMain extends JFrame{
	private static final long serialVersionUID = 1L;
	
	public PerfilMain(String usuario) {
		this.setLocation(300, 20);
		this.setSize(750, 700);
		this.getContentPane().add(new PerfilPacienteVista(usuario));
		this.setVisible(true);
	}
	
}

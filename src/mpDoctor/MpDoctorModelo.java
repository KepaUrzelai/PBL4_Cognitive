package mpDoctor;


import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.JButton;

import conexionBDyMIKROS.ConexionBDCognitive;
import conexionBDyMIKROS.PacienteDAO;

public class MpDoctorModelo extends AbstractListModel<JButton>{
	private static final long serialVersionUID = 1L;

	List<JButton> listUsuarios;
	  MpDoctorControlador controlador;
	  MpDoctorVista vista;
	  ConexionBDCognitive conexion;
	  PacienteDAO datos;
	
	public MpDoctorModelo (MpDoctorControlador controlador) {
		this.controlador = controlador;
	    conexion = new ConexionBDCognitive(2);
	    datos = new PacienteDAO(conexion);
	    listUsuarios = new ArrayList<>();
	    this.inicializarConBD();
	}
	
	public String buscarUsuario(String str) {
	    for(JButton b : listUsuarios) {
	      if(b.getText().equals(str)) {
	        return b.getText();
	      }
	    }
	    return "fail";
	  }
	
	private void inicializarConBD() {
	    List<JButton> lista = new ArrayList<>();
	    List<String> pacientes = new ArrayList<>();
	    
	    pacientes = datos.cargarUsers(1);
	    
	    for(int i = 0; i < pacientes.size(); i++) {
	      lista.add(crearBotonUsuario(pacientes.get(i)));
	      listUsuarios.add(lista.get(i));
	    }
	  }
	
	public JButton crearBotonUsuario(String str) {
		JButton boton = new JButton();
		boton.setText(str);
		boton.setActionCommand("Usuario");
		boton.addActionListener(controlador);
		return boton;
	}

	@Override
	public JButton getElementAt(int index) {
	    return listUsuarios.get(index);
	  }

	  @Override
	  public int getSize() {
	    return listUsuarios.size();
	  }



}

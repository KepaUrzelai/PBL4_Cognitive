package mpDoctor;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import juegos.GestorJuegos;

public class MpDoctorControlador implements ListSelectionListener, ActionListener{
	MpDoctorVista vista;
	GestorJuegos gestor;
	
	
	public void setVista(MpDoctorVista vista) {
		this.vista = vista;
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
 
		if(vista.listUsuarios.getValueIsAdjusting()) return;
		switch(vista.listUsuarios.getSelectedValue().getActionCommand()) {
		case "Usuario":
			String[] str = new String[3];
			String cadena = new String();
			
			cadena = vista.listUsuarios.getSelectedValue().getText();
			//Formato: nombre apellido1 apellido2
			str = cadena.split(" ");
			cadena = str[2]+" "+str[0]+" "+str[1];
			cadena = cadena.replaceFirst("[\\s\\S]{0,1}$", "");

			@SuppressWarnings("unused") PerfilMain perfil = new PerfilMain(cadena);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "ATENCIÓN":
			vista.remove(vista.panelJuego);
			vista.getContentPane().add(vista.crearPanelAtencion(), BorderLayout.CENTER);
			vista.repaint();
			vista.revalidate();
			break;
		case "MEMORIA":
			vista.remove(vista.panelJuego);
			vista.getContentPane().add(vista.crearPanelMemoria(), BorderLayout.CENTER);
			vista.repaint();
			vista.revalidate();
			break;
		case "AGILIDAD MENTAL":
			vista.remove(vista.panelJuego);
			vista.getContentPane().add(vista.crearPanelAgil(), BorderLayout.CENTER);
			vista.repaint();
			vista.revalidate();
			break;
		case "RAZONAMIENTO MATEMÁTICO":
			vista.remove(vista.panelJuego);
			vista.getContentPane().add(vista.crearPanelMat(), BorderLayout.CENTER);
			vista.repaint();
			vista.revalidate();
			break;
		case "RAZONAMIENTO VERBAL":
			vista.remove(vista.panelJuego);
			vista.getContentPane().add(vista.crearPanelVerb(), BorderLayout.CENTER);
			vista.repaint();
			vista.revalidate();
			break;
		case "Juego Figuras":
			gestor= new GestorJuegos(0);
			break;
		case "Juego d's":
			gestor= new GestorJuegos(1);
			break;
		case "Ruta primer nivel":
			gestor= new GestorJuegos(2);
			break;
		case "Ruta nivel 2 (Colores)":
			gestor= new GestorJuegos(3);
			break;
		case "Juego de Parejas":
			gestor= new GestorJuegos(4);
			break;
		case "Juego de Luces":
			gestor= new GestorJuegos(5);
			break;
		case "Juego Simbolos":
			gestor= new GestorJuegos(6);
			break;
		case "Ejercicios matemáticos":
			gestor= new GestorJuegos(7);
			break;
		case "Adivina el operador":
			gestor= new GestorJuegos(8);
			break;
		case "Efecto Stroop":
			gestor= new GestorJuegos(9);
			break;
		case "Buscar": 
			System.out.println("Buscando usuario");
			@SuppressWarnings("unused") String usuario = new String(vista.txtBusqueda.getText());
			
			//
			
			break;
		}
		
	}

}

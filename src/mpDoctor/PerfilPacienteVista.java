package mpDoctor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

import conexionBDyMIKROS.ConexionBDCognitive;
import conexionBDyMIKROS.DiagnosticoDAO;
import usuarioYcronometro.Puntuaciones;
import usuarioYcronometro.Usuario;

public class PerfilPacienteVista extends JPanel{
	private static final long serialVersionUID = 1L;
	String nombre, apellido1, apellido2, poblacion, fechaNac; 
	int escolaridad;
	JTable tabla;
	JList<Puntuaciones> lista;
	PerfilPacienteModeloCampo modelo;	
	PerfilPacienteModeloSesion modelo2;
	TableRenderer trazador;
	PerfilPacienteModeloColumnasTabla columnas;
	Usuario usuario;
	
	public PerfilPacienteVista(String nombre) {
		//Cargar user:
		ConexionBDCognitive conexion = new ConexionBDCognitive(3);
		DiagnosticoDAO datos = new DiagnosticoDAO(conexion);
		usuario = datos.cargarPuntuacionesDoctor(nombre);
		String[] str = new String[3];
		
		this.nombre = usuario.getNombre();
		str = nombre.split(" ");
		
		this.nombre = str[0];
		this.apellido1 = str[1];
		this.apellido2 = str[2];
		this.poblacion = usuario.getPoblacion();
		this.fechaNac = usuario.getFecha();
		this.escolaridad = usuario.getEscolaridad();
		
		modelo2 = new PerfilPacienteModeloSesion(usuario);
		trazador = new TableRenderer();
		columnas = new PerfilPacienteModeloColumnasTabla (trazador);
		modelo = new PerfilPacienteModeloCampo(usuario,columnas);
		
		this.setLayout(new GridLayout(3,1));
		this.add(crearPanelInfo());
		this.add(crearPanelTabla());
		this.add(panelSesion());
		
	}
	
	public JPanel crearPanelInfo() {
		JPanel panel = new JPanel(new GridLayout(5,1));
		String str = new String();
		
		panel.add(crearLabel("Apellidos: "+apellido1+" "+apellido2));
		panel.add(crearLabel("Nombre: "+nombre));
		panel.add(crearLabel("F. Nacimiento: "+fechaNac));
		panel.add(crearLabel("Población: "+poblacion));
		
		if(escolaridad == 0) {
			str = "Baja";
		}else if(escolaridad == 1) {
			str = "Media";
		}else if(escolaridad == 2) {
			str = "Alta";
		}

		panel.add(crearLabel("Escolaridad "+str));
		panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		panel.setBackground(new Color(182,226,160));
		return panel;
	}
	
	public JLabel crearLabel(String str) {
		JLabel label = new JLabel(str);
		label.setFont(new Font("Monospaced", Font.BOLD, 20));
		label.setForeground(Color.DARK_GRAY);
		label.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
		return label;
	}
	
	public Component crearPanelTabla() {
		JScrollPane panel = new JScrollPane();
		tabla = new JTable(modelo,columnas);
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabla.setRowHeight(23);
		panel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel.setViewportView(tabla);
		panel.setSize(tabla.getWidth(), tabla.getHeight());
		return panel;
	}
	
	public JPanel panelSesion() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(crearPanelTitulo(), BorderLayout.NORTH);
		panel.add(crearPanelSesiones(), BorderLayout.CENTER);
		return panel;
	}
	
	private Component crearPanelTitulo() {
		JPanel panel = new JPanel();
		JLabel label = new JLabel("SESIONES DE DIAGNOSTICO");
		
		label.setFont(new Font("Monospaced", Font.BOLD, 18));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5,5,5,5),BorderFactory.createLoweredSoftBevelBorder()));
		panel.add(label);
		
		panel.setBackground(new Color(182,226,160));
		return panel;
	}

	public Component crearPanelSesiones() {
		JScrollPane panel = new JScrollPane();
		lista = new JList<Puntuaciones>();
		lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lista.setModel(modelo2);
		lista.setCellRenderer(new SesionRenderer());
		panel.setViewportView(lista);
		panel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		return panel;
	}
	
}

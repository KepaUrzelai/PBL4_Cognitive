package juegos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import conexionBDyMIKROS.ConexionBDCognitive;
import conexionBDyMIKROS.DiagnosticoDAO;
import conexionBDyMIKROS.EjercitarDAO;
import conexionBDyMIKROS.PacienteDAO;
import conexionmikros.LineaSerie;
import diagnostico.MiPanel;
import loginYmenu.VistaMenuPrincipal;
import usuarioYcronometro.Usuario;

public class GestorJuegos extends JFrame implements ActionListener, Observer{
	private static final long serialVersionUID = 1L;
	ImageIcon fondo;
	Color color;
	JButton botonJugar, botonMenu;
	String campo, titulo;
	JPanel panel, panelJuego;
	Usuario usuario;
	String nombreUsuario;
	Random random;
	//los juegos hay que definirlos antes para el observer
	Atencion01Ruta juegoRuta1;
	Atencion01RutaColores juegoRuta2;
	JuegoD2 juegoAtencion;
	JuegoFiguras juegoAtencionFiguras;
	
	MemoriaCartas juegoMemoriaCartas;
	JuegoLuces juegoMemoriaLuces;
	
	JuegoSimbolos juegoAgilidad;
	
	JuegoEfectoStroop juegoRazVerbal;
	JuegoMateOperacion juegoRazMat1;
	JuegoMate3 juegoRazMat2;
	LineaSerie lineaSerie;
	int tipoJuego;
	int actividadID;
	int dia, mes, ano;
	Calendar c;
	int tipoUsuario=0;
	
	
	public GestorJuegos(String nombre) {
		super("Bienvenido");
		inicializarJuegos();
		nombreUsuario=nombre;
		cargarUsuario();
		titulo = "Menu";
		fondo = new ImageIcon("imagenes/Fondo4d.png");
		campo = "Jugando :";
		color = new Color(230,150,0);//amarillo oscuro
		c= new GregorianCalendar();
		
		dia = c.get(Calendar.DATE);
		mes = c.get(Calendar.MONTH) + 1;
		ano = c.get(Calendar.YEAR);
		this.setSize(1280, 720);
		this.setContentPane(crearPanelVentana(crearPanelCentral()));
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public GestorJuegos(int idJuego) {
		super("Bienvenido");
		tipoUsuario=1;
		inicializarJuegos();
		titulo = "Menu";
		fondo = new ImageIcon("imagenes/Fondo4d.png");
		campo = "Jugando :";
		color = new Color(230,150,0);//amarillo oscuro
		this.setSize(1280, 720);
		this.setContentPane(crearPanelVentana(crearPanelCentral()));
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if(idJuego==0) {
			JOptionPane.showConfirmDialog(this, juegoAtencionFiguras.getExplicación(), "Ayuda",
					JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
			inicializarJuegos();
			this.titulo="Atención";
			panelJuego=juegoAtencionFiguras.getPanelPrincipal();
		}else if(idJuego==1) {
			JOptionPane.showConfirmDialog(this, juegoAtencion.getExplicación(), "Ayuda",
					JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
			inicializarJuegos();
			this.titulo="Atención";
			panelJuego=juegoAtencion.getPanelPrincipal();
		}else if(idJuego==2) {
			JOptionPane.showConfirmDialog(this, juegoRuta1.getExplicación(), "Ayuda",
					JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
			inicializarJuegos();
			this.titulo="Atención";
			panelJuego=juegoRuta1.getPanelPrincipal();
		}else if(idJuego==3) {
			JOptionPane.showConfirmDialog(this, juegoRuta2.getExplicación(), "Ayuda",
					JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
			inicializarJuegos();
			this.titulo="Atención";
			panelJuego=juegoRuta2.getPanelPrincipal();
		}else if(idJuego==4) {
			JOptionPane.showConfirmDialog(this, juegoMemoriaCartas.getExplicación(), "Ayuda",
					JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
			inicializarJuegos();
			this.titulo="Memoria";
			panelJuego=juegoMemoriaCartas.getPanelPrincipal();
		}else if(idJuego==5) {
			JOptionPane.showConfirmDialog(this, juegoMemoriaLuces.getExplicación(), "Ayuda",
					JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
			inicializarJuegos();
			this.titulo="Memoria";
			panelJuego=juegoMemoriaLuces.getPanelPrincipal();
		}else if(idJuego==6) {
			JOptionPane.showConfirmDialog(this, juegoAgilidad.getExplicación(), "Ayuda",
					JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
			inicializarJuegos();
			this.titulo="Agilidad";
			panelJuego=juegoAgilidad.getPanelPrincipal();
		}else if(idJuego==7) {
			JOptionPane.showConfirmDialog(this, juegoRazMat2.getExplicación(), "Ayuda",
					JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
			inicializarJuegos();
			this.titulo="Razonamiento Matemático";
			panelJuego=juegoRazMat2.getPanelPrincipal();
		}else if(idJuego==8) {
			JOptionPane.showConfirmDialog(this, juegoRazMat1.getExplicación(), "Ayuda",
					JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
			inicializarJuegos();
			this.titulo="Razonamiento Matemático";
			panelJuego=juegoRazMat1.getPanelPrincipal();
		}else{
			JOptionPane.showConfirmDialog(this, juegoRazVerbal.getExplicación(), "Ayuda",
					JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
			inicializarJuegos();
			this.titulo="Razonamiento Verbal";
			panelJuego=juegoRazVerbal.getPanelPrincipal();
		}
		this.setContentPane(crearPanelVentana(panelJuego));
		botonJugar.setEnabled(false);
		botonMenu.setEnabled(false);
		this.repaint();
		this.revalidate();
	}
	
	private void inicializarJuegos() {
		juegoRuta1=new Atencion01Ruta();
		juegoRuta2=new Atencion01RutaColores();
		juegoAtencion=new JuegoD2(1);
		juegoAtencionFiguras=new JuegoFiguras(1,new File("imagenes/cuadrado.jpg"), new File("imagenes/circulo.jpg"), new File("imagenes/triangulo.jpg"));		
		juegoMemoriaCartas=new MemoriaCartas();
		juegoAgilidad=new JuegoSimbolos();
		juegoRazVerbal=new JuegoEfectoStroop();
		juegoRazMat1=new JuegoMateOperacion(2);
		juegoMemoriaLuces=new JuegoLuces();
		juegoRazMat2=new JuegoMate3();
		juegoRuta1.addObserver(this);
		juegoRuta2.addObserver(this);
		juegoAtencion.addObserver(this);
		juegoAtencionFiguras.addObserver(this);
		juegoMemoriaCartas.addObserver(this);
		juegoAgilidad.addObserver(this);
		juegoRazVerbal.addObserver(this);
		juegoRazMat1.addObserver(this);
		juegoMemoriaLuces.addObserver(this);
		juegoRazMat2.addObserver(this);
	}

	private void cargarUsuario() {
	/*	ObjectInputStream in = null;
		ObjectInputStream puntuaciones = null;
		
		try {
			in = new ObjectInputStream ( new FileInputStream("Usuarios/"+nombreUsuario+"/datos.txt"));
			puntuaciones = new ObjectInputStream ( new FileInputStream("Usuarios/"+nombreUsuario+"/puntuaciones.txt"));
	
			try {
				usuario=(Usuario) in.readObject();
				usuario.setPuntuaciones((Puntuaciones)puntuaciones.readObject());
				System.out.println(usuario.getPuntuaciones());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (in!=null){
				try { in.close(); } catch (IOException e) {}
			}
		}*/
		ConexionBDCognitive consulta;
		consulta= new ConexionBDCognitive(1);
		DiagnosticoDAO diagnostico=(DiagnosticoDAO) consulta.getDiagnostico();
		usuario=diagnostico.cargarPuntuaciones(nombreUsuario);
		consulta.close();
	
	}

	private Container crearPanelVentana(Component panelCentral) {
		panel = new MiPanel(new BorderLayout(), this.fondo.getImage());
		panel.add(crearPanelSuperior(), BorderLayout.NORTH);
		panel.add(panelCentral, BorderLayout.CENTER);
		panel.add(crearPanelInferior(), BorderLayout.SOUTH);
		return panel;
	}

	private Component crearPanelSuperior() {
	
		JPanel panel = new JPanel(new BorderLayout());
		JLabel lCampo = new JLabel(this.campo);
		JLabel lTitulo = new JLabel(this.titulo);
		
		lCampo.setFont(new Font("Monospaced", Font.BOLD, 30));
		lCampo.setForeground(this.color);
		lCampo.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		lTitulo.setFont(new Font("Monospaced", Font.BOLD, 30));
		lTitulo.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		panel.add(lCampo, BorderLayout.WEST);
		panel.add(lTitulo, BorderLayout.CENTER);
		panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10,10,10,10), BorderFactory.createRaisedBevelBorder()));
		panel.setOpaque(false);
		return panel;
	}

	private Component crearPanelCentral() {
		JPanel panel = new JPanel(new BorderLayout());
		
		
		panel.setOpaque(false);
		
		return panel;
	}

	private Component crearPanelInferior() {
		JPanel panel = new JPanel(new BorderLayout(100,100));
		panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10,10,10,10),BorderFactory.createLoweredBevelBorder()));
		JLabel label2 = new JLabel(new ImageIcon("imagenes/logo1.png"));
		botonJugar = new JButton("Volver a Practicar");
		botonJugar.setFont(new Font("Monospaced", Font.BOLD, 35));
		botonJugar.addActionListener(this);
		botonJugar.setActionCommand("jugar");
		botonMenu = new JButton("Volver al Menú");
		botonMenu.setFont(new Font("Monospaced", Font.BOLD, 35));
		botonMenu.addActionListener(this);
		botonMenu.setActionCommand("menu");
		panel.add(botonJugar, BorderLayout.CENTER);
		panel.add(botonMenu,BorderLayout.EAST);
		panel.add(label2, BorderLayout.WEST);
		panel.setOpaque(false);
		return panel;
	}
	
	public void SeleccionarJuego(int nivel) {/*el usuario puede tener 4 niveles diferentes, basado en un formato de 
	niveles piramidal, y cada nivel te permite hacer un numero de ejercicios diferentes. Si el nivel es mas bajo, 
	los ejercicios serán mas sencillos y habrá menos variedad. */ 
		lineaSerie=new LineaSerie();
		random=new Random();
		int randomNum;
		if(nivel==0) {
			randomNum=random.nextInt(4);
			
			if (randomNum==0) {
				tipoJuego=0;
				actividadID=0;
				JOptionPane.showConfirmDialog(this, juegoAtencion.getExplicación(), "Ayuda",
						JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
				inicializarJuegos();
				this.titulo="Atención";
				panelJuego=juegoAtencion.getPanelPrincipal();
			}
			if (randomNum==1) {
				tipoJuego=0;
				actividadID=1;
				JOptionPane.showConfirmDialog(this, juegoAtencionFiguras.getExplicación(), "Ayuda",
						JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
				inicializarJuegos();
				this.titulo="Atención";
				panelJuego=juegoAtencionFiguras.getPanelPrincipal();
			}
			if (randomNum==2) {
				tipoJuego=0;
				actividadID=2;
				JOptionPane.showConfirmDialog(this, juegoRuta1.getExplicación(), "Ayuda",
						JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
				inicializarJuegos();
				this.titulo="Atención";
				panelJuego=juegoRuta1.getPanelPrincipal();
			}
			if (randomNum==3) {
				tipoJuego=0;
				actividadID=3;
				JOptionPane.showConfirmDialog(this, juegoRuta2.getExplicación(), "Ayuda",
						JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
				inicializarJuegos();
				this.titulo="Atención";
				panelJuego=juegoRuta2.getPanelPrincipal();
			}
			
		}
		else if(nivel==1) {
		
			randomNum=random.nextInt(8);
			if (randomNum==0) {
				tipoJuego=0;
				actividadID=0;
				JOptionPane.showConfirmDialog(this, juegoAtencion.getExplicación(), "Ayuda",
						JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);//esto es una explicación para el usuario
				inicializarJuegos();
				this.titulo="Atención";
				panelJuego=juegoAtencion.getPanelPrincipal();
			}
			if (randomNum==1) {
				tipoJuego=0;
				actividadID=1;
				JOptionPane.showConfirmDialog(this, juegoAtencionFiguras.getExplicación(), "Ayuda",
						JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
				inicializarJuegos();
				this.titulo="Atención";
				panelJuego=juegoAtencionFiguras.getPanelPrincipal();
			}
			if (randomNum==2) {
				tipoJuego=0;
				actividadID=2;
				JOptionPane.showConfirmDialog(this, juegoRuta1.getExplicación(), "Ayuda",
						JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
				inicializarJuegos();
				this.titulo="Atención";
				panelJuego=juegoRuta1.getPanelPrincipal();
			}
			if (randomNum==3) {
				tipoJuego=0;
				actividadID=3;
				JOptionPane.showConfirmDialog(this, juegoRuta2.getExplicación(), "Ayuda",
						JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
				inicializarJuegos();
				this.titulo="Atención";
				panelJuego=juegoRuta2.getPanelPrincipal();
			}
			if ((randomNum==4)||(randomNum==5)) {
				tipoJuego=1;
				actividadID=4;
				JOptionPane.showConfirmDialog(this, juegoMemoriaCartas.getExplicación(), "Ayuda",
						JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
				inicializarJuegos();
				this.titulo="Memoria";
				panelJuego=juegoMemoriaCartas.getPanelPrincipal();
			}
			if ((randomNum==6)||(randomNum==7)) {
				tipoJuego=1;
				actividadID=5;
				JOptionPane.showConfirmDialog(this, juegoMemoriaLuces.getExplicación(), "Ayuda",
						JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
				inicializarJuegos();
				this.titulo="Memoria";
				panelJuego=juegoMemoriaLuces.getPanelPrincipal();
			}
		}
		else if(nivel==2) {
			
			randomNum=random.nextInt(8);
			if (randomNum==0) {
				tipoJuego=0;
				actividadID=0;
				JOptionPane.showConfirmDialog(this, juegoAtencion.getExplicación(), "Ayuda",
						JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
				inicializarJuegos();
				this.titulo="Atención";
				panelJuego=juegoAtencion.getPanelPrincipal();
			}
			if (randomNum==1) {
				tipoJuego=0;
				actividadID=1;
				JOptionPane.showConfirmDialog(this, juegoAtencionFiguras.getExplicación(), "Ayuda",
						JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
				inicializarJuegos();
				this.titulo="Atención";
				panelJuego=juegoAtencionFiguras.getPanelPrincipal();
			}
			if (randomNum==2) {
				tipoJuego=0;
				actividadID=2;
				JOptionPane.showConfirmDialog(this, juegoRuta1.getExplicación(), "Ayuda",
						JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
				inicializarJuegos();
				this.titulo="Atención";
				panelJuego=juegoRuta1.getPanelPrincipal();
			}
			if (randomNum==3) {
				tipoJuego=0;
				actividadID=3;
				JOptionPane.showConfirmDialog(this, juegoRuta2.getExplicación(), "Ayuda",
						JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
				inicializarJuegos();
				this.titulo="Atención";
				panelJuego=juegoRuta2.getPanelPrincipal();
			}
			if (randomNum==4) {
				tipoJuego=1;
				actividadID=4;
				JOptionPane.showConfirmDialog(this, juegoMemoriaCartas.getExplicación(), "Ayuda",
						JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
				inicializarJuegos();
				this.titulo="Memoria";
				panelJuego=juegoMemoriaCartas.getPanelPrincipal();
			}
			if (randomNum==5) {
				tipoJuego=1;
				actividadID=5;
				JOptionPane.showConfirmDialog(this, juegoMemoriaLuces.getExplicación(), "Ayuda",
						JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
				inicializarJuegos();
				this.titulo="Memoria";
				panelJuego=juegoMemoriaLuces.getPanelPrincipal();
			}
			if ((randomNum==6)||(randomNum==7)) {
				tipoJuego=2;
				actividadID=6;
				JOptionPane.showConfirmDialog(this, juegoAgilidad.getExplicación(), "Ayuda",
						JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
				inicializarJuegos();
				this.titulo="Agilidad";
				panelJuego=juegoAgilidad.getPanelPrincipal();
			}
		}
		else if(nivel==3) {
			randomNum=random.nextInt(13);
			if (randomNum==0) {
				tipoJuego=0;
				actividadID=0;
				JOptionPane.showConfirmDialog(this, juegoAtencion.getExplicación(), "Ayuda",
						JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
				inicializarJuegos();
				this.titulo="Atención";
				panelJuego=juegoAtencion.getPanelPrincipal();
			}
			if (randomNum==1) {
				tipoJuego=0;
				actividadID=1;
				JOptionPane.showConfirmDialog(this, juegoAtencionFiguras.getExplicación(), "Ayuda",
						JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
				inicializarJuegos();
				this.titulo="Atención";
				panelJuego=juegoAtencionFiguras.getPanelPrincipal();
			}
			if (randomNum==2) {
				tipoJuego=0;
				actividadID=2;
				JOptionPane.showConfirmDialog(this, juegoRuta1.getExplicación(), "Ayuda",
						JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
				inicializarJuegos();
				this.titulo="Atención";
				panelJuego=juegoRuta1.getPanelPrincipal();
			}
			if (randomNum==3) {
				tipoJuego=0;
				actividadID=3;
				JOptionPane.showConfirmDialog(this, juegoRuta2.getExplicación(), "Ayuda",
						JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
				inicializarJuegos();
				this.titulo="Atención";
				panelJuego=juegoRuta2.getPanelPrincipal();
			}
			if (randomNum==4) {
				tipoJuego=1;
				actividadID=4;
				JOptionPane.showConfirmDialog(this, juegoMemoriaCartas.getExplicación(), "Ayuda",
						JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
				inicializarJuegos();
				this.titulo="Memoria";
				panelJuego=juegoMemoriaCartas.getPanelPrincipal();
			}
			if (randomNum==5) {
				tipoJuego=1;
				actividadID=5;
				JOptionPane.showConfirmDialog(this, juegoMemoriaLuces.getExplicación(), "Ayuda",
						JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
				inicializarJuegos();
				this.titulo="Memoria";
				panelJuego=juegoMemoriaLuces.getPanelPrincipal();
			}
			if (randomNum==6) {
				tipoJuego=2;
				actividadID=6;
				JOptionPane.showConfirmDialog(this, juegoAgilidad.getExplicación(), "Ayuda",
						JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
				inicializarJuegos();
				this.titulo="Agilidad";
				panelJuego=juegoAgilidad.getPanelPrincipal();
			}
			if((randomNum==7)||(randomNum==8)) {
				tipoJuego=3;
				actividadID=9;
				JOptionPane.showConfirmDialog(this, juegoRazVerbal.getExplicación(), "Ayuda",
						JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
				inicializarJuegos();
				this.titulo="Razonamiento Verbal";
				panelJuego=juegoRazVerbal.getPanelPrincipal();
			}
			if((randomNum==9)||(randomNum==10)) {
				tipoJuego=4;
				actividadID=7;
				JOptionPane.showConfirmDialog(this, juegoRazMat1.getExplicación(), "Ayuda",
						JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
				inicializarJuegos();
				this.titulo="Razonamiento Matemático";
				panelJuego=juegoRazMat1.getPanelPrincipal();
			}
			if((randomNum==11)||(randomNum==12)) {
				tipoJuego=4;
				actividadID=8;
				JOptionPane.showConfirmDialog(this, juegoRazMat2.getExplicación(), "Ayuda",
						JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
				inicializarJuegos();
				this.titulo="Razonamiento Matemático";
				panelJuego=juegoRazMat2.getPanelPrincipal();
			}
		}
		lineaSerie.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("jugar")) {
			SeleccionarJuego(calcularNivel());
			this.setContentPane(crearPanelVentana(panelJuego));
			botonJugar.setEnabled(false);
			botonMenu.setEnabled(false);
			this.repaint();
			this.revalidate();
		}
		else if(e.getActionCommand().equals("menu")) {
			VistaMenuPrincipal principal = new VistaMenuPrincipal(nombreUsuario);
			this.dispose();
		}
	}

	private int calcularNivel() {
		//usuario.getPuntuaciones().calcularBaremos();
		if(usuario.getPuntuaciones().getcAtencion()<2) {
			return 0;
		}
		else if(usuario.getPuntuaciones().getcMemoria()<2) {
			return 1;
		}
		else if(usuario.getPuntuaciones().getcAgilidad()<2) {
			return 2;
		}
		else return 3;		
	}

	@SuppressWarnings("deprecation")
	@Override
	public void update(Observable arg0, Object arg1) {
		if (tipoUsuario==1) {

			this.dispose();
		}
		else {
		String tiempo;
		lineaSerie.setJuegoTerminado(true);
		
		while((tiempo=lineaSerie.getTiempo())==null) {
			System.out.println("leyendo");
		}
	//	lineaSerie.stop();
	
		ConexionBDCognitive consulta;
		consulta= new ConexionBDCognitive(1);
		PacienteDAO paciente=(PacienteDAO) consulta.getPaciente();
		paciente.sumarTiempo(usuario, tipoJuego, tiempo);
		consulta.close();
		System.out.println("llega?");
		consulta= new ConexionBDCognitive(1);
		EjercitarDAO ejercitar = (EjercitarDAO) consulta.getEjercitar();
		ejercitar.guardarEjercitar(String.valueOf(ano + "-" + mes + "-" + dia ), actividadID, tiempo, usuario);
		consulta.close();
		
		botonJugar.setEnabled(true);
		botonMenu.setEnabled(true);
		}
		
	}
}

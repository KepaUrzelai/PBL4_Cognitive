package loginYmenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import diagnostico.PlantillaJuegos_SEMANAL;
import juegos.GestorJuegos;

//import org.jfree.ui.RefineryUtilities;

public class VistaMenuPrincipal implements ActionListener{

	JLabel label;
	Properties propiedades;
	JButton botonArriba, botonCentro, botonIzq, botonDer;
	JFrame ventana;
	Musica programa;
	ImageIcon fondo1, fondo2, fondo3, fondo4;
	File imagFile;
	int controlarFondos;
	Configuracion programaConf;
	Color verdeOscuro, marron, rojo, azul;
	ImageIcon iconoEscala1, iconoEscala2,iconoEscala3,iconoEscala4, imagenArriba, imagenCentro, imagenIzq, imagenDer;
	String nombreUsuario;
	
	public VistaMenuPrincipal(String nombreUsuario) {
		propiedades = new Properties();
		programa=new Musica();
		this.nombreUsuario=nombreUsuario;
		
        imagenArriba = new ImageIcon("imagenes/fondoArriba.png");
        imagenCentro = new ImageIcon("imagenes/fondoMedio.png");
        imagenDer = new ImageIcon("imagenes/fondoDrc.png");
        imagenIzq = new ImageIcon("imagenes/fondoIzq.png");
		verdeOscuro= new Color(0,150,0);
		marron = new Color(200,150,0);
		rojo = new Color(180,0,0);
		azul = new Color(0,0,150);
		controlarFondos=1;
		imagFile = new File("imagenes/logo1.bmp");
		ventana = new JFrame ("Menu Principal");
		fondo1 = new ImageIcon("imagenes/fondoArriba.png");
		fondo2 = new ImageIcon("imagenes/fondoMedio.png");
		fondo3 = new ImageIcon("imagenes/FondoIzq.png");
		fondo4 = new ImageIcon("imagenes/FondoDrc.png");
		ventana.setSize(1280, 720);
	
		try {
		      Image image = ImageIO.read(imagFile);
		      ventana.setIconImage(new ImageIcon(image).getImage());
		    } catch (IOException e) {
		      // TODO Auto-generated catch block
		      e.printStackTrace();
		    }
		
		ventana.setContentPane(crearPanelVentana());
		ventana.setVisible(true);
		ventana.setResizable(false);
		asignarIconos();		
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	
	}
	
	
	
	
	private void asignarIconos() {//aquí cuadramos las imagenes que se asignan a los botones para que quede bien, y ocupe todo el botón
		iconoEscala1 = new ImageIcon(imagenArriba.getImage().getScaledInstance(botonArriba.getWidth(), botonArriba.getHeight(), java.awt.Image.SCALE_DEFAULT));
		iconoEscala2 = new ImageIcon(imagenCentro.getImage().getScaledInstance(botonCentro.getWidth(), botonCentro.getHeight(), java.awt.Image.SCALE_DEFAULT));
		iconoEscala3 = new ImageIcon(imagenDer.getImage().getScaledInstance(botonDer.getWidth(), botonDer.getHeight(), java.awt.Image.SCALE_DEFAULT));
		iconoEscala4 = new ImageIcon(imagenIzq.getImage().getScaledInstance(botonIzq.getWidth(), botonIzq.getHeight(), java.awt.Image.SCALE_DEFAULT));
		
		botonArriba.setIcon(iconoEscala1);
		botonCentro.setIcon(iconoEscala2);
		botonDer.setIcon(iconoEscala3);
		botonIzq.setIcon(iconoEscala4);
	}


	private Container crearPanelVentana() {
		JPanel panel = new JPanel(new GridLayout(2,1));
		
		JPanel panel1 = new JPanel(new BorderLayout());
		botonArriba = new JButton();
		
		
		botonArriba.setBorder(BorderFactory.createEmptyBorder(0,200,0,200));
		botonArriba.setLayout(new BorderLayout());
		label = new JLabel("Empezar");

		JLabel icono = new JLabel(new ImageIcon("imagenes/comenzar.png"));
		
		
		botonArriba.add(icono, BorderLayout.EAST);
		try(FileInputStream in = new FileInputStream ("config.properties.txt")){
			propiedades.load(in);
			
			label.setFont(new Font ("Monospaced", Font.BOLD, Integer.parseInt(propiedades.getProperty("tamaño_letra1", "155"))));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		label.setForeground(verdeOscuro);
		label.setHorizontalAlignment(JLabel.CENTER);
		botonArriba.add(label, BorderLayout.CENTER);
		
		
		botonArriba.setActionCommand("empezar");
		botonArriba.addActionListener(this);
		panel1.add(botonArriba, BorderLayout.CENTER);
		panel1.setBorder(BorderFactory.createLineBorder(verdeOscuro,10));
		panel.add(panel1);
		panel.add(crearPanelInferior());
		
		return panel;
	}

	private Component crearPanelInferior() {
		JPanel panel = new JPanel(new GridLayout(2,1));
		JPanel panel1 = new JPanel(new BorderLayout());
		botonCentro = new JButton();
		botonCentro.setLayout(new BorderLayout());
		JLabel label = new JLabel("Perfil");
		JLabel icono = new JLabel(new ImageIcon("imagenes/person.png"));
		try(FileInputStream in = new FileInputStream ("config.properties.txt")){
			propiedades.load(in);
			
			label.setFont(new Font ("Monospaced", Font.BOLD, Integer.parseInt(propiedades.getProperty("tamaño_letra2", "100"))));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		label.setForeground(azul);
		label.setHorizontalAlignment(JLabel.CENTER);
		botonCentro.setBorder(BorderFactory.createEmptyBorder(0,380,0,380));
		botonCentro.add(label, BorderLayout.CENTER);
		botonCentro.add(icono, BorderLayout.EAST);
		
		botonCentro.setActionCommand("perfil");
		botonCentro.addActionListener(this);
		panel1.add(botonCentro, BorderLayout.CENTER);
		panel1.setBorder(BorderFactory.createLineBorder(azul,10));
		panel.add(panel1);
		panel.add(crearPanelCS());
		return panel;
	}

	private Component crearPanelCS() {
		JPanel panel = new JPanel(new BorderLayout());
		try(FileInputStream in = new FileInputStream ("config.properties.txt")){
			propiedades.load(in);
			
			panel.add(crearPanelBoton("Configuración", Integer.parseInt(propiedades.getProperty("tamaño_letra3", "80")),"conf"), BorderLayout.CENTER);
			panel.add(crearPanelBoton2("Salir ", Integer.parseInt(propiedades.getProperty("tamaño_letra3", "80")),"salir"), BorderLayout.EAST);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return panel;
	}

	private Component crearPanelBoton2(String string, int tamaño, String command) {
		JPanel panel = new JPanel(new BorderLayout());
		
		botonDer = new JButton();
		botonDer.setLayout(new BorderLayout());
		JLabel label = new JLabel(string);
		JLabel icono = new JLabel(new ImageIcon("imagenes/exit.png"));
		
		
		
		botonDer.add(icono, BorderLayout.EAST);
	//	icono.setPreferredSize(new Dimension(100,10));
		label.setFont(new Font ("Monospaced", Font.BOLD, tamaño));
		label.setForeground(rojo);
		label.setHorizontalAlignment(JLabel.CENTER);
		
		botonDer.add(label, BorderLayout.CENTER);
		
		botonDer.setActionCommand(command);
		botonDer.addActionListener(this);
		panel.add(botonDer, BorderLayout.CENTER);
		panel.setBorder(BorderFactory.createLineBorder(rojo,10));
		System.out.println();
		return panel;
	}

	private Component crearPanelBoton(String string, int tamaño, String command) {
		JPanel panel = new JPanel(new BorderLayout());
		
		botonIzq = new JButton();
		botonIzq.setBorder(BorderFactory.createEmptyBorder(0,50,0,50));
		botonIzq.setLayout(new BorderLayout());
		JLabel label = new JLabel(string);
		JLabel icono = new JLabel(new ImageIcon("imagenes/ajuste.png"));
		
		
		botonIzq.add(icono, BorderLayout.EAST);
		label.setFont(new Font ("Monospaced", Font.BOLD, tamaño));
		label.setForeground(marron);
		label.setHorizontalAlignment(JLabel.CENTER);
		botonIzq.add(label);
		
		botonIzq.setActionCommand(command);
		botonIzq.addActionListener(this);
		panel.add(botonIzq, BorderLayout.CENTER);
		panel.setBorder(BorderFactory.createLineBorder(marron,10));
	
		return panel;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "salir": 
			if(propiedades.getProperty("musica").equals("on")) {
				programa.player.stop();
			}
			programa.ventana.dispose();
			ventana.dispose();
			break;
		case "conf":
			if(propiedades.getProperty("musica").equals("on")) {
				programa.player.stop();
			}
			programa.ventana.dispose();
			programaConf = new Configuracion();
			ventana.dispose();
			break;
		case "perfil":
			if(propiedades.getProperty("musica").equals("on")) {
				programa.player.stop();
			}
			programa.ventana.dispose();
			VistaPerfil vistaPerfil = new VistaPerfil(nombreUsuario);
			ventana.dispose();
			break;
		case "empezar":
			EmpezarDialog empezarDlg = new EmpezarDialog(ventana);
			empezarDlg.setVisible(true);
			if(empezarDlg.isCerrar()) {//cerrar será true si ha seleccionado la opción practicar
				if(propiedades.getProperty("musica").equals("on")) {
					programa.player.stop();
				}
				programa.ventana.dispose();
				ventana.dispose();
				GestorJuegos juego = new GestorJuegos(nombreUsuario);
			}
			else {
				if(propiedades.getProperty("musica").equals("on")) {
					programa.player.stop();
				}
				programa.ventana.dispose();
				ventana.dispose();
				PlantillaJuegos_SEMANAL juego = new PlantillaJuegos_SEMANAL(nombreUsuario);
			}
			break;
		}
		 
		
	}

}

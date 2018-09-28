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
import java.io.ObjectInputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import conexionBDyMIKROS.ConexionBDCognitive;
import conexionBDyMIKROS.DiagnosticoDAO;
import diagnostico.MiPanel;
import usuarioYcronometro.Puntuaciones;
import usuarioYcronometro.Usuario;

public class VistaPerfil extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	File imagFile, bocadFile;
	int pAtencion;
	int pMemoria,pAgilidad,pMatematica;
	JFreeChart grafico;
	DefaultCategoryDataset datos = new DefaultCategoryDataset();
	Color azul;
	ImageIcon fondo, bocadillo;
	Usuario usuario;
	String nombreUsu;
	
	public VistaPerfil(String nombreusuario){
		super("Perfil del Usuario");	
		nombreUsu=nombreusuario;
		cargarUsuario();
		azul = new Color(0,0,150);
		fondo = new ImageIcon("imagenes/fondoTotal.png");
		imagFile = new File("imagenes/logo1.bmp");
		bocadFile = new File("imagenes/bocadillo.png");
		try {
			Image image = ImageIO.read(imagFile);
			Image image2 = ImageIO.read(bocadFile);
			this.setIconImage(new ImageIcon(image).getImage());
			bocadillo = new ImageIcon(image2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		pAtencion = usuario.getPuntuaciones().getcAtencion(); pMemoria = usuario.getPuntuaciones().getcMemoria(); pAgilidad = usuario.getPuntuaciones().getcAgilidad(); pMatematica = usuario.getPuntuaciones().getcRazMat();
		
		datos.addValue(pAtencion, "Puntuación", "Atención");
		datos.addValue(pMemoria, "Puntuación", "Memoria");
		datos.addValue(pAgilidad, "Puntuación", "Agilidad Mental");
		datos.addValue(pMatematica, "Puntuación", "Razonamientos");

		grafico = ChartFactory.createBarChart3D("", "0-Deficiente 1-Bajo 2-Medio 3-Alto", "Puntuación", datos, PlotOrientation.VERTICAL, false, true, false);
		StandardChartTheme chartTheme = (StandardChartTheme)org.jfree.chart.StandardChartTheme.createJFreeTheme();
		chartTheme.setRegularFont(new Font("Arial", Font.PLAIN, 17));
		chartTheme.setLargeFont(new Font("Monospaced", Font.BOLD, 26));
		chartTheme.apply(grafico);
		  //COLOR DE LAS COLUMNAS
		CategoryPlot plot =  (CategoryPlot) grafico.getPlot();
		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setSeriesPaint(0, azul);
		this.setSize(1280, 720);
		//this.setLocation(300, 200);
		this.setLayout(new BorderLayout());
		this.setContentPane(crearPanel());
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void cargarUsuario() {
	/*	ObjectInputStream in = null;
		ObjectInputStream puntuaciones = null;
		
		try {
			in = new ObjectInputStream ( new FileInputStream("Usuarios/"+nombreUsu+"/datos.txt"));
			puntuaciones = new ObjectInputStream ( new FileInputStream("Usuarios/"+nombreUsu+"/puntuaciones.txt"));
	
			try {
				usuario=(Usuario) in.readObject();
				usuario.setPuntuaciones((Puntuaciones)puntuaciones.readObject());
				usuario.getPuntuaciones().calcularBaremos();
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
		}
		*/
		ConexionBDCognitive consulta;
		consulta= new ConexionBDCognitive(1);
		DiagnosticoDAO diagnostico=(DiagnosticoDAO) consulta.getDiagnostico();
		usuario=diagnostico.cargarPuntuaciones(nombreUsu);
		consulta.close();
	}

	private Container crearPanel() {
		JPanel panel = new MiPanel(new BorderLayout(),fondo.getImage());
		panel.add(crearPanelTitulo(), BorderLayout.NORTH);
		panel.add(crearPanelDcha(), BorderLayout.EAST);
		panel.add(crearPanelIzq(), BorderLayout.WEST);
		return panel;
	}

	private Component crearPanelIzq() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(crearPanelDatos(), BorderLayout.NORTH);
		panel.add(crearPanelConsejo(), BorderLayout.CENTER);
		panel.setBorder(BorderFactory.createEmptyBorder(10,10,0,0));
		panel.setOpaque(false);
		return panel;
	}

	private Component crearPanelConsejo() {
		JPanel panel = new MiPanel(new BorderLayout(),bocadillo.getImage());
		String frase = escogerFrase();
		JLabel label = new JLabel(frase);
		label.setFont(new Font("Monospaced", Font.ITALIC, 27));
		label.setForeground(azul);
		label.setOpaque(false);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBorder(BorderFactory.createEmptyBorder(0,0,60,0));
		panel.add(label);
		panel.setOpaque(false);
		return panel;
	}

	private String escogerFrase() {
		Random random=new Random();
		int randomNum = random.nextInt(7);
		String frase;
		switch(randomNum) {
		case 0 : return frase="<html><body>¡Sigue así, ánimo!</body></html>";
		case 1 : return frase="<html><body>¡Concentrate y<br/> a por todas!</body></html>";
		case 2 : return frase="<html><body>¡Sigue trabajando<br/> duro!</body></html>";
		case 3 : return frase="<html><body>¡La constancia <br/>te ayudará!</body></html>";
		case 4 : return frase="<html><body>¡Avanzas<br/> adecuadamente,<br/> sigue así!</body></html>";
		case 5 : return frase="<html><body>¡El trabajo diario<br/> se verá recompensado!</body></html>";
		case 6 : return frase="<html><body>¡Ejercita tus<br/> habilidades!</body></html>";
		}
		return null;
	}

	private Component crearPanelDatos() {
		JPanel panel = new JPanel(new GridLayout(7,1));
		panel.add(crearLabelDato(" "));
		panel.add(crearLabelDato(usuario.getNombre()));
		panel.add(crearLabelDato(" "));
		panel.add(crearLabelDato(usuario.getFecha()));
		panel.add(crearLabelDato(" "));
		panel.add(crearLabelDato("Población: "+usuario.getPoblacion()));
		panel.add(crearLabelDato(" "));
		panel.setOpaque(false);
		panel.setBorder(BorderFactory.createLineBorder(azul,5,true));
		return panel;
	}

	private Component crearLabelDato(String contenido) {
		JLabel texto = new JLabel("  "+contenido+" ");
		texto.setFont(new Font("Monospaced", Font.BOLD, 30));
		texto.setForeground(azul);
		texto.setOpaque(false);
		return texto; 
	}

	private Component crearPanelDcha() {
		JPanel panel = new JPanel(new BorderLayout());
		JLabel titulo = new JLabel("Mi Grafico");
		ChartPanel graf = new ChartPanel(grafico);
		graf.setBorder(BorderFactory.createEmptyBorder(5,0,0,0));
		titulo.setOpaque(false);
		titulo.setFont(new Font("Monospaced", Font.BOLD,50));
		titulo.setForeground(azul);
		titulo.setHorizontalAlignment(JLabel.CENTER);
		titulo.setBorder(BorderFactory.createLineBorder(azul,5,true));
		panel.add(titulo, BorderLayout.NORTH);
		panel.add(graf,BorderLayout.CENTER);
		panel.add(crearBotonMenu(),BorderLayout.SOUTH);
		panel.setOpaque(false);
		panel.setBorder(BorderFactory.createEmptyBorder(0,0,0,10));
		return panel;
	}

	private Component crearBotonMenu() {
		JPanel panel = new JPanel(new BorderLayout());
		JButton boton = new JButton(" Volver al Menu ");
		boton.setFont(new Font("Monospaced", Font.BOLD, 25));
		boton.setForeground(azul);
		boton.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		boton.setActionCommand("Menu");
		boton.addActionListener(this);
		panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		panel.add(boton, BorderLayout.EAST);
		panel.setOpaque(false);
		return panel;
	}

	private Component crearPanelTitulo() {
		JPanel panel = new JPanel(new BorderLayout());
		JLabel titulo = new JLabel("MI PERFIL");
		titulo.setFont(new Font ("Monospaced", Font.BOLD, 80));
		titulo.setForeground(azul);
		titulo.setHorizontalAlignment(JLabel.CENTER);
		titulo.setOpaque(false);
		panel.add(titulo);
		panel.setOpaque(false);
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Menu")) {
			VistaMenuPrincipal principal = new VistaMenuPrincipal(nombreUsu);
			this.dispose();
		}
		
	}
}


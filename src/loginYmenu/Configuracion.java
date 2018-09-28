package loginYmenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import diagnostico.MiPanel;

public class Configuracion extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	JLabel lMusica,lDaltonico;
	JPanel pVentana,pCentral,pMusica,pLetra,pDaltonico;
	ImageIcon fondo;
	Properties propiedades;
	JRadioButton bPequena, bMediana, bGrande;
	Color azul;
	JRadioButton bOn1,bOff1,bOn2,bOff2;
	ButtonGroup grp,grp2;
	boolean musica;

	public Configuracion() {	//La opción de modo daltonico estará disponible en el próximo parche.
		
		super("Configuración");
		propiedades = new Properties();
		musica=true;
		this.setSize(1280,720);
		this.setLocation(50, 35);
		azul = new Color(0,0,150);
		fondo = new ImageIcon("imagenes/fondoTotal.png");
		this.setContentPane(crearPanelVentana());
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		inicializarBotones();
	}
	private void inicializarBotones() {//se carga el fichero properties.
		try(FileInputStream in = new FileInputStream ("config.properties.txt")){
			propiedades.load(in);
			
			if(propiedades.getProperty("musica").equals("on")) {
				bOn1.setSelected(true);
			}
			else bOff1.setSelected(true);
			
			if(propiedades.getProperty("tamaño_letra1").equals("155")) {
				bGrande.setSelected(true);
			}
			else if(propiedades.getProperty("tamaño_letra1").equals("130")) {
				bMediana.setSelected(true);
			}
			else bPequena.setSelected(true);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	private Container crearPanelVentana() {
		pVentana =new MiPanel(new BorderLayout(0,10), fondo.getImage());
		pVentana.add(crearPanelTitulo(),BorderLayout.NORTH);
		pVentana.add(crearPanelCentral(),BorderLayout.CENTER);
		pVentana.add(crearBotonMenu(), BorderLayout.SOUTH);
		return pVentana;
				}
	
	private Component crearBotonMenu() {
		JPanel panel = new JPanel(new BorderLayout());
		JButton boton = new JButton(" Volver al Menu ");
		boton.setFont(new Font("Monospaced", Font.BOLD, 25));
		boton.setForeground(azul);
		boton.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		boton.setActionCommand("Menu");
		boton.addActionListener(this);
		boton.setOpaque(false);
		panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		panel.add(boton, BorderLayout.EAST);
		panel.setOpaque(false);
		return panel;
	}
	private Container crearPanelMusica() {
		pMusica = new JPanel (new GridLayout(1, 3));

		lMusica = new JLabel("Música");
		grp = new ButtonGroup();
		bOn1 = new JRadioButton("On");
		bOff1 = new JRadioButton("Off");
		
		lMusica.setFont(new Font("Monospaced",Font.BOLD,30));
		lMusica.setForeground(azul);
		lMusica.setBorder(BorderFactory.createLineBorder(azul,5,true));
		lMusica.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(20,20,20,20)
				,BorderFactory.createLineBorder(azul,5,true)));
		lMusica.setHorizontalAlignment(JLabel.CENTER);
		lMusica.setOpaque(false);
		
		bOn1.setFont(new Font("Monospaced",Font.BOLD,40));
		bOn1.setForeground(Color.BLACK);
		bOff1.setFont(new Font("Monospaced",Font.BOLD,40));
		bOff1.setForeground(Color.BLACK);
		
		bOn1.setActionCommand("musica");
		bOn1.addActionListener(this);
		bOn1.setOpaque(false);
		bOff1.setActionCommand("musica");
		bOff1.addActionListener(this);
		bOff1.setOpaque(false);
		grp.add(bOn1);
		grp.add(bOff1);
		pMusica.add(lMusica);
		pMusica.add(bOn1);
		pMusica.add(bOff1);
		pMusica.setOpaque(false);
		
		return pMusica;
	}
	private Container crearPanelDaltonico() {
		pDaltonico = new JPanel (new GridLayout(1, 3));
		lDaltonico = new JLabel("Modo Dáltonico");
		grp2 = new ButtonGroup();
		bOn2 = new JRadioButton("On");
		
		bOff2 = new JRadioButton("Off");
		bOff2.setSelected(true);
		bOn2.setFont(new Font("Monospaced",Font.BOLD,40));
		bOn2.setForeground(Color.BLACK);
		bOn2.setOpaque(false);
		lDaltonico.setFont(new Font("Monospaced",Font.BOLD,30));
		lDaltonico.setForeground(azul);
		lDaltonico.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(20,20,20,20)
				,BorderFactory.createLineBorder(azul,5,true)));
		lDaltonico.setHorizontalAlignment(JLabel.CENTER);
		lDaltonico.setOpaque(false);
		bOff2.setFont(new Font("Monospaced",Font.BOLD,40));
		bOff2.setForeground(Color.BLACK);
		bOff2.setOpaque(false);
		grp2.add(bOn2);
		grp2.add(bOff2);
		
		pDaltonico.add(lDaltonico);
		pDaltonico.add(bOn2);
		pDaltonico.add(bOff2);
		pDaltonico.setOpaque(false);
		return pDaltonico;
		
	}
	private Container crearPanelLetra() {
		pLetra = new JPanel (new GridLayout(1, 4));
		JLabel lTamano = new JLabel ("Tamaño Letra");
		bPequena = new JRadioButton("Pequeña");
		bMediana = new JRadioButton("Mediana");
		bGrande = new JRadioButton("Grande");
		bGrande.setSelected(true);
		ButtonGroup grp3 = new ButtonGroup();
		
		lTamano.setFont(new Font("Monospaced",Font.BOLD,30));
		lTamano.setForeground(azul);
		lTamano.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(20,20,20,20)
				,BorderFactory.createLineBorder(azul,5,true)));
		lTamano.setHorizontalAlignment(JLabel.CENTER);
		lTamano.setOpaque(false);

		bPequena.setFont(new Font("Monospaced",Font.BOLD,25));
		bPequena.setForeground(Color.BLACK);
		bPequena.setOpaque(false);
		bMediana.setFont(new Font("Monospaced",Font.BOLD,30));
		bMediana.setForeground(Color.BLACK);
		bMediana.setOpaque(false);
		bGrande.setFont(new Font("Monospaced",Font.BOLD,40));
		bGrande.setForeground(Color.BLACK);
		bGrande.setOpaque(false);
		
		grp3.add(bPequena);
		grp3.add(bMediana);
		grp3.add(bGrande);
		
		pLetra.add(lTamano);
		pLetra.add(bPequena);
		pLetra.add(bMediana);
		pLetra.add(bGrande);
		pLetra.setOpaque(false);
		
		return pLetra;
	}
	
	private Container crearPanelCentral() {
		JPanel pCentral = new JPanel(new GridLayout(3,1,10,10));
		pCentral.add(crearPanelMusica());
		pCentral.add(crearPanelDaltonico());
		pCentral.add(crearPanelLetra());

		pCentral.setBorder(BorderFactory.createEmptyBorder(40,40,40,40));
		pCentral.setOpaque(false);
		return pCentral;
	}

	private Container crearPanelTitulo() {
		JLabel titulo = new JLabel("----- Configuración -----");
		JPanel pTitulo = new JPanel();		
		titulo.setFont(new Font("Monospaced",Font.BOLD,67));
		titulo.setForeground(azul);
		titulo.setHorizontalAlignment(JLabel.CENTER);
		titulo.setOpaque(false);
		pTitulo.add(titulo);
		pTitulo.setOpaque(false);
		return pTitulo;
	}
	
	@Override
	 public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Menu")) {
			cambiarPropiedades();
			VistaMenuPrincipal principal = new VistaMenuPrincipal(null);
			this.dispose();
		}
	   if(bOn1.isSelected()) {
	    try {
	     musica=true;
	    } catch (Exception e1) {
	     // TODO Auto-generated catch block
	     e1.printStackTrace();
	    }
	    }
	   else if(bOff1.isSelected()){
	    musica=false;
	   }

	  }
	  
	 private void cambiarPropiedades() {//se cambiara el fichero properties con la nueva configuración.
		 try(BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("config.properties.txt")))){
			if(bOn1.isSelected())	
			 out.write("musica=on\n");
			else out.write("musica=off\n");
			if(bGrande.isSelected()) {
				out.write("tamaño_letra1=155\ntamaño_letra2=100\ntamaño_letra3=80");
			}
			else if(bMediana.isSelected()) {
				out.write("tamaño_letra1=130\ntamaño_letra2=80\ntamaño_letra3=65");
			}
			else if(bPequena.isSelected()) {
				out.write("tamaño_letra1=115\ntamaño_letra2=60\ntamaño_letra3=50");
			}
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
	}
	public boolean isMusica() {
		return musica;
	}

}

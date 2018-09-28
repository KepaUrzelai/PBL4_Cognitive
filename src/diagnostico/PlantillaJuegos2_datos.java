package diagnostico;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class PlantillaJuegos2_datos extends JPanel {

	Image imagen;
	ImageIcon fondo;
	Color color;
	String campo, titulo;
	JTextField tNombre;
	JTextField tApellido1;
	JTextField tApellido2;
	JTextField tPoblacion;
	JTextField tAno;
	JTextField tMes;
	JTextField tDia;
	JPasswordField contra1, contra2;
	JRadioButton bBaja;
	JRadioButton bMedia;
	JRadioButton bAlta;

	public PlantillaJuegos2_datos() {
		super(new BorderLayout());
		titulo = " ";
		fondo = new ImageIcon("imagenes/fondo4d.png");
		campo = "Datos Personales: ";
		color = new Color(230,150,0);//amarillo oscuro
		
	    imagen = fondo.getImage();
		
		crearPanelVentana();
	}
	
	private void crearPanelVentana() {
	
		this.add(crearPanelCentral(), BorderLayout.CENTER);
		
	}
	

	private Component crearPanelCentral() {
		JPanel panel = new JPanel(new GridLayout(6,1));
		
		panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10,10,10,10), BorderFactory.createRaisedBevelBorder()));
		JLabel lDatosPersonales = new JLabel("  Datos Personales");
		lDatosPersonales.setFont(new Font("Monospaced",Font.BOLD,25));
		lDatosPersonales.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10,10,10,10), BorderFactory.createRaisedBevelBorder()));

		JLabel lFecha = new JLabel("  Fecha de Nacimiento");
		lFecha.setFont(new Font("Monospaced",Font.BOLD,25));
		lFecha.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10,10,10,10), BorderFactory.createRaisedBevelBorder()));

		JLabel lEscolaridad = new JLabel("  Nivel de Escolaridad");
		lEscolaridad.setFont(new Font("Monospaced",Font.BOLD,25));
		lEscolaridad.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10,10,10,10), BorderFactory.createRaisedBevelBorder()));


		panel.setBackground(Color.WHITE);
		panel.add(lDatosPersonales);
		panel.add(crearPanelDatosPersonales());
		panel.add(lFecha);
		panel.add(crearPanelFecha());
		panel.add(lEscolaridad);
		panel.add(crearPanelEscolaridad());
		panel.setOpaque(false);
		return panel;
	}

	private Container crearPanelDatosPersonales() {
		JPanel panel = new JPanel(new GridLayout(3,6,10,10));
		panel.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
		tNombre = new JTextField(5);
		JLabel lNombre = new JLabel("Nombre:");
		lNombre.setFont(new Font("Monospaced",Font.CENTER_BASELINE,17));
		lNombre.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		tApellido1 = new JTextField(5);
		JLabel lApellido1 = new JLabel("Primer Apellido:");
		lApellido1.setFont(new Font("Monospaced",Font.CENTER_BASELINE,17));
		lApellido1.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

		tApellido2 = new JTextField (5);
		JLabel lApellido2 = new JLabel("Segundo Apellido:");
		lApellido2.setFont(new Font("Monospaced",Font.CENTER_BASELINE,17));
		lApellido2.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

		tPoblacion = new JTextField(5);
		JLabel lPoblacion = new JLabel("Población:");
		lPoblacion.setFont(new Font("Monospaced",Font.CENTER_BASELINE,17));
		lPoblacion.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		contra1 = new JPasswordField (5);
		JLabel lcontra1 = new JLabel("Contraseña:");
		lcontra1.setFont(new Font("Monospaced",Font.CENTER_BASELINE,17));
		lcontra1.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

		contra2 = new JPasswordField(5);
		JLabel lcontra2 = new JLabel("Repite la contraseña:");
		lcontra2.setFont(new Font("Monospaced",Font.CENTER_BASELINE,17));
		lcontra2.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

		panel.add(lNombre);
		panel.add(tNombre);
		panel.add(lApellido1);
		panel.add(tApellido1);
		panel.add(lApellido2);
		panel.add(tApellido2);
		panel.add(lPoblacion);
		panel.add(tPoblacion);
		panel.add(lcontra1);
		panel.add(contra1);
		panel.add(lcontra2);
		panel.add(contra2);
		panel.setOpaque(false);
		return panel;
	}
	public JPasswordField getContra1() {
		return contra1;
	}

	private Container crearPanelFecha() {
		JPanel panel = new JPanel(new GridLayout(1,6,100,100));
		panel.setBorder(BorderFactory.createEmptyBorder(25,25,25,25));

		tAno = new JTextField(2);
		JLabel lAno = new JLabel("  Año:");
		lAno.setFont(new Font("Monospaced",Font.CENTER_BASELINE,17));
		lAno.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

		tMes = new JTextField(2);
		JLabel lMes = new JLabel("  Mes:");
		lMes.setFont(new Font("Monospaced",Font.CENTER_BASELINE,17));
		lMes.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

		tDia = new JTextField(4);
		JLabel lDia = new JLabel("  Día:");
		lDia.setFont(new Font("Monospaced",Font.CENTER_BASELINE,17));
		lDia.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

		panel.add(lDia);
		panel.add(tDia);
		panel.add(lMes);
		panel.add(tMes);
		panel.add(lAno);
		panel.add(tAno);
		panel.setOpaque(false);
		return panel;
	}
	
	private Container crearPanelEscolaridad() {
		JPanel panel = new JPanel (new GridLayout(1,3));
		panel.setBorder(BorderFactory.createEmptyBorder(25,25,25,25));

		bBaja = new JRadioButton("Baja (menor de 5 años)");
		bBaja.setFont(new Font("Monospaced",Font.CENTER_BASELINE,15));
		bBaja.setSelected(true);
		bMedia = new JRadioButton("Media (entre 5 y 12 años)");
		bMedia.setFont(new Font("Monospaced",Font.CENTER_BASELINE,15));

		bAlta = new JRadioButton("Alta (mayor de 12 años)");
		bAlta.setFont(new Font("Monospaced",Font.CENTER_BASELINE,15));
		
		ButtonGroup grp = new ButtonGroup();
		grp.add(bBaja);
		grp.add(bMedia);
		grp.add(bAlta);
		bBaja.setOpaque(false);
		bMedia.setOpaque(false);
		bAlta.setOpaque(false);
		panel.add(bBaja);
		panel.add(bMedia);
		panel.add(bAlta);
		panel.setOpaque(false);
		return panel;
	}

	public void setImagen(Image nuevaImagen) {
		imagen = nuevaImagen;
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D gr = (Graphics2D)g;
		if (imagen != null) {
			gr.drawImage(imagen, 0, 0, getWidth(), getHeight(),this);
		} else {
			setOpaque(true);
		}	
	}

	public int calcularescolaridad() {
		if(bBaja.isSelected()) {
			return 0;
		}
		else if(bMedia.isSelected()) {
			return 1;
		}
		else {
			return 2;
		}
	}


	public JPasswordField getContra2() {
		return contra2;
	}

	public JTextField gettNombre() {
		return tNombre;
	}

	public JTextField gettApellido1() {
		return tApellido1;
	}

	public JTextField gettApellido2() {
		return tApellido2;
	}

	public JTextField gettPoblacion() {
		return tPoblacion;
	}

	public JTextField gettAno() {
		return tAno;
	}

	public JTextField gettMes() {
		return tMes;
	}

	public JTextField gettDia() {
		return tDia;
	}

	public ImageIcon getFondo() {
		return fondo;
	}

	public Color getColor() {
		return color;
	}
	
	public String getCampo() {
		return campo;
	}

	public String getTitulo() {
		return titulo;
	}
}

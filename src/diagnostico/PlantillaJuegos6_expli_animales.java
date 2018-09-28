package diagnostico;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import usuarioYcronometro.Cronometro;

public class PlantillaJuegos6_expli_animales extends JPanel implements ActionListener {

	Image imagen;
	ImageIcon fondo;
	Color color;
	String campo, titulo;
	JButton comenzar;
	Cronometro crono;
	JTextField tResultado;
	
	public PlantillaJuegos6_expli_animales() {
		super(new BorderLayout());
		titulo = "Animales por minuto";
		fondo = new ImageIcon("imagenes/Fondo2d.png");
		campo = "Explicación y Juego : ";
		color = new Color(0,150,0);//verde
		
	    imagen = fondo.getImage();
		
		crearPanelVentana();
	}
	
	private void crearPanelVentana() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(crearPanelExplicacion(), BorderLayout.CENTER);
		panel.add(crearPanelResultado(), BorderLayout.SOUTH);
		panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0,10,0,10),BorderFactory.createRaisedBevelBorder()));
		panel.setOpaque(false);
		this.add(panel);
		
	}
	
	private Component crearPanelExplicacion() {
		JPanel panel = new JPanel(new BorderLayout());
		String texto = "<html><body>El paciente tiene un minuto para decir todos los animales que recuerde, cuantos más mejor.\r\n" + 
				" </body></html>";
		JLabel label = new JLabel(texto);
//		label.layo
		label.setFont(new Font("Monospaced",Font.CENTER_BASELINE,35));
		label.setForeground(Color.DARK_GRAY);		
		label.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));	
		panel.add(label, BorderLayout.CENTER);
	
		panel.add(crearBoton(), BorderLayout.SOUTH);
		
		panel.setOpaque(false);
		return panel;
	}

	private Component crearBoton() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(0,500,30,500));
		comenzar = new JButton("Comenzar");
		comenzar.setFont(new Font("Monospaced",Font.CENTER_BASELINE,35));
		comenzar.addActionListener(this);
		panel.add(comenzar);
		panel.setOpaque(false);
		return panel;
	}

	private Component crearPanelResultado() {
		JPanel panel = new JPanel(new GridLayout(1,2, 30, 30));
		panel.setBorder(BorderFactory.createEmptyBorder(0,0,40,0));
		JLabel lResultado = new JLabel("Resultado:");
		lResultado.setFont(new Font("Monospaced", Font.BOLD, 30));
		lResultado.setBorder(BorderFactory.createEmptyBorder(0,30,0,0));
		tResultado = new JTextField();
		tResultado.setFont(new Font("Monospaced", Font.BOLD, 30));
		panel.add(lResultado);
		panel.add(tResultado);
		crono = new Cronometro(1, 60);
		crono.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		crono.setOpaque(false);
		
		panel.add(crono);
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

	@Override
	public void actionPerformed(ActionEvent arg0) {
		crono.iniciar();
		comenzar.setEnabled(false);
	}

	public JTextField gettResultado() {
		return tResultado;
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

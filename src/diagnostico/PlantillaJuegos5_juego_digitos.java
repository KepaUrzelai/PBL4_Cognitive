package diagnostico;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

public class PlantillaJuegos5_juego_digitos extends JPanel {

	Image imagen;
	ImageIcon fondo;
	Color color;
	String campo, titulo;
	JTextField tResultadoDirectos, tResultadoInversos;
	String nivelDirectos, nivelInversos;
	int puntosDirectos, puntosInversos;

	
	public PlantillaJuegos5_juego_digitos() {
		super(new BorderLayout());
		titulo = "Los Dígitos";
		fondo = new ImageIcon("imagenes/Fondo1d.png");
		campo = "Juego : ";
		color = new Color(180,0,0);//rojo
		
	    imagen = fondo.getImage();
		
		crearPanelVentana();
	}
	
	private void crearPanelVentana() {
	
		this.add(crearPanelCentral(), BorderLayout.CENTER);
		
	}
	
	private Component crearPanelCentral(){
		JSplitPane panel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true,
				crearPanelDirectos(),crearPanelInversos());
		panel.setEnabled(false);
		panel.setDividerLocation(640);
		panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10,10,10,10), BorderFactory.createRaisedBevelBorder()));
		panel.setOpaque(false);
		return panel;
	}
	

	private Component crearPanelDirectos() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10,10,10,10), BorderFactory.createRaisedBevelBorder()));

		JLabel directos = new JLabel("DIRECTOS",JLabel.CENTER);
		directos.setFont(new Font("Monospaced",Font.BOLD,30));

		JLabel digitos1 = new JLabel("<html>4-7-3<br/>5-8-6<br/>6-1-5-3<br/>7-4-9-2<br/>2-7-1-3-4<br/>3-2-9-5-8<br/>1-3-7-2-4-9<br/>"
				+ "8-5-2-4-3-7<br/>9-6-4-1-8-3-5<br/>"
				+ "6-9-8-7-2-5-4<br/>3-5-7-6-1-8-2-9<br/>4-9-2-1-7-2-5-3-8"
				+ "<br/>2-6-3-5-8-1-7-9-4<br/>5-1-9-7-4-6-3-8-2</html>");
		digitos1.setFont(new Font("Monospaced",Font.BOLD,24));
		directos.setHorizontalAlignment(JLabel.CENTER);
		panel.add(directos,BorderLayout.NORTH);
		panel.add(digitos1,BorderLayout.WEST);
		panel.add(crearPanelResultadoDirectos(),BorderLayout.SOUTH);
		panel.setOpaque(false);
		return panel;
	}

	private Component crearPanelInversos() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10,10,10,10), BorderFactory.createRaisedBevelBorder()));
		
		JLabel inversos = new JLabel("INVERSOS",JLabel.CENTER);
		inversos.setFont(new Font("Monospaced",Font.BOLD,30));

		JLabel digitos1 = new JLabel("<html>5-2<br/>7-9<br/>2-6-3<br/>8-4-7<br/>6-1-4-9<br/>4-7-6-3<br/>3-5-8-2-6<br/>"
				+ "1-3-9-4-5<br/>9-8-1-5-3-2<br/>"
				+ "4-7-2-6-1-9<br/>8-6-5-7-2-4-3<br/>2-5-9-1-4-8-6"
				+ "<br/>7-4-6-8-9-3-1-2<br/>3-7-1-5-4-2-8-6</html>");
		digitos1.setFont(new Font("Monospaced",Font.BOLD,24));
		
		panel.add(inversos,BorderLayout.NORTH);
		panel.add(digitos1,BorderLayout.WEST);
		panel.add(crearPanelResultadoInversos(),BorderLayout.SOUTH);

		panel.setOpaque(false);
		return panel;
	}
	private Component crearPanelResultadoDirectos() {
		JPanel panel = new JPanel (new GridLayout(1, 2));
		panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10,10,10,10), BorderFactory.createLineBorder(Color.BLACK,3)));
		
		tResultadoDirectos = new JTextField("");
		
		JLabel lResultado = new JLabel("Resultado:");
		lResultado.setFont(new Font("Monospaced",Font.BOLD,20));
		
		panel.add(lResultado);
		panel.add(tResultadoDirectos);
		panel.setOpaque(false);
		return panel;

	}
	private Component crearPanelResultadoInversos() {
		JPanel panel = new JPanel (new GridLayout(1, 2));
		panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10,10,10,10), BorderFactory.createLineBorder(Color.BLACK,3)));
		
		tResultadoInversos = new JTextField("");
		
		JLabel lResultado = new JLabel("Resultado:");
		lResultado.setFont(new Font("Monospaced",Font.BOLD,20));
		
		panel.add(lResultado);
		panel.add(tResultadoInversos);
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

	public void calcularPuntuacion() {
		puntosDirectos=Integer.valueOf(tResultadoDirectos.getText());
		puntosInversos=Integer.valueOf(tResultadoInversos.getText());
	}

	public JTextField gettResultadoDirectos() {
		return tResultadoDirectos;
	}

	public JTextField gettResultadoInversos() {
		return tResultadoInversos;
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

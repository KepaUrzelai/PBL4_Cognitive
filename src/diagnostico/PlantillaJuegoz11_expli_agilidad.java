package diagnostico;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PlantillaJuegoz11_expli_agilidad extends JPanel {

	Image imagen;
	ImageIcon fondo;
	Color color;
	String campo, titulo;

	
	public PlantillaJuegoz11_expli_agilidad() {
		super(new BorderLayout());
		titulo = "Agilidad/Velocidad de pensamiento";
		fondo = new ImageIcon("imagenes/Fondo4d.png");
		campo = "Explicación : ";
		color = new Color(230,150,0);//amarillo oscuro
	    imagen = fondo.getImage();
		
		crearPanelVentana();
	}
	
	private void crearPanelVentana() {
		JPanel panel = new JPanel(new BorderLayout());
		String texto = "<html><body>Verás una imagen en la pantalla y tendrás que decir qué es. Intenta acertar en el menor tiempo posible."
				+ " <br/>Los resultados dependeran del tiempo. ¡SUERTE! </body></html>" ;

		JLabel label = new JLabel(texto);
		label.setFont(new Font("Monospaced",Font.CENTER_BASELINE,35));
		label.setForeground(Color.DARK_GRAY);
		label.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),BorderFactory.createEmptyBorder(0,40,0,10)));
		panel.add(label);
		panel.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
		panel.setOpaque(false);
		this.add(panel);
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

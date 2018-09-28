package diagnostico;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PlantillaJuegos3_expli_diag extends JPanel {

	Image imagen;
	ImageIcon fondo;
	Color color;
	String campo, titulo;

	
	public PlantillaJuegos3_expli_diag() {
		super(new BorderLayout());
		titulo = "El Diagnóstico";
		fondo = new ImageIcon("imagenes/Fondo4d.png");
		campo = "Explicación : ";
		color = new Color(230,150,0);//amarillo oscuro
		
	    imagen = fondo.getImage();
		
		crearPanelVentana();
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

	private void crearPanelVentana() {
		JPanel panel = new JPanel(new BorderLayout());
		String texto = "<html><body>Para completar el diagnóstico, vamos a plantearte una serie de ejercicios con los que determinaremos tus aptitudes y "
				+ "diseñaremos una ruta personalizada a seguir durante tu rehabilitación. </body></html>";
		JLabel label = new JLabel(texto);
//		label.layo
		label.setFont(new Font("Monospaced",Font.CENTER_BASELINE,35));
		label.setForeground(Color.DARK_GRAY);
		String texto2 = "<html><body>¡BUENA SUERTE! </body></html>";
		JLabel label2 = new JLabel(texto2);
		label2.setFont(new Font("Monospaced",Font.BOLD,35));
		label2.setForeground(Color.DARK_GRAY);
		panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0,10,0,10),BorderFactory.createRaisedBevelBorder()));
		label.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));
		label2.setBorder(BorderFactory.createEmptyBorder(0,20,40,0));
		panel.add(label, BorderLayout.CENTER);
		panel.add(label2, BorderLayout.SOUTH);
	
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

}

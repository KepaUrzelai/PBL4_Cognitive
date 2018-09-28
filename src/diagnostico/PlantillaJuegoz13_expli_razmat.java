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

public class PlantillaJuegoz13_expli_razmat extends JPanel {

	Image imagen;
	ImageIcon fondo;
	Color color;
	String campo, titulo;

	
	public PlantillaJuegoz13_expli_razmat() {
		super(new BorderLayout());
		titulo = "Razonamiento Matemático ";
		fondo = new ImageIcon("imagenes/Fondo1d.png");
		campo = "Explicación : ";
		color = color.DARK_GRAY;
	
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
	
		JPanel panel = new JPanel(new GridLayout(4,1));
		String texto = "<html><body>Calculos: </body></html>";
		String texto2 = "<html><body>Problemas aritmeticos: </body></html>";
		String texto3 = "<html><body>Opera los siguientes cálculos matemáticos en el menor tiempo posible." + 
				" </body></html>";
		String texto4 = "<html><body>Despues de los cálculos tendras problemas aritméticos que resolver."+
				" </body></html>";
		
		JLabel label = new JLabel(texto);
		JLabel label2 = new JLabel(texto2);
		JLabel label3 = new JLabel(texto3);
		JLabel label4 = new JLabel(texto4);

		label.setFont(new Font("Monospaced",Font.BOLD,34));
		label.setForeground(Color.BLACK);
		label2.setFont(new Font("Monospaced",Font.BOLD,34));
		label2.setForeground(Color.BLACK);
		label3.setFont(new Font("Monospaced",Font.CENTER_BASELINE,31));
		label3.setForeground(Color.DARK_GRAY);
		label4.setFont(new Font("Monospaced",Font.CENTER_BASELINE,31));
		label4.setForeground(Color.DARK_GRAY);
		
		panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0,10,0,10),BorderFactory.createRaisedBevelBorder()));
		label.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));
		label2.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));
		label3.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));
		label4.setBorder(BorderFactory.createEmptyBorder(0,20,20,0));
		
		panel.add(label);
		panel.add(label3);
		panel.add(label2);
		panel.add(label4);
	
	
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

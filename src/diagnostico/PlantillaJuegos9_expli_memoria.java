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

public class PlantillaJuegos9_expli_memoria extends JPanel {

	Image imagen;
	ImageIcon fondo;
	Color color;
	String campo, titulo;

	
	public PlantillaJuegos9_expli_memoria() {
		super(new BorderLayout());
		titulo = "Memoria";
		fondo = new ImageIcon("imagenes/Fondo3d.png");
		campo = "Explicación : ";
		color = new Color(0,0,150);//azul
	    imagen = fondo.getImage();
		
		crearPanelVentana();
	}
	
	private void crearPanelVentana() {
	
		JPanel panel = new JPanel(new GridLayout(6,1));
		String texto = "<html><body>Contar de 20 a 1: </body></html>";
		String texto1 = "<html><body>Días de la semana: </body></html>";
		String texto2 = "<html><body>Meses del año: </body></html>";
		String texto3 = "<html><body>Haz una cuenta atrás desde el veinte, en el menor tiempo posible.</body></html>";
		String texto4 = "<html><body>Recita los días de la semana, de principio a fín, en el menor tiempo posible.</body></html>";
		String texto5 = "<html><body>Recita los mese del año, de principio a fin, en el menor tiempo posible. </body></html>";
		JLabel label = new JLabel(texto);
		JLabel label1 = new JLabel(texto1);
		JLabel label2 = new JLabel(texto2);
		JLabel label3 = new JLabel(texto3);
		JLabel label4 = new JLabel(texto4);
		JLabel label5 = new JLabel(texto5);
		label.setFont(new Font("Monospaced",Font.BOLD,30));
		label.setForeground(Color.BLACK);
		label1.setFont(new Font("Monospaced",Font.BOLD,30));
		label1.setForeground(Color.BLACK);
		label2.setFont(new Font("Monospaced",Font.BOLD,30));
		label2.setForeground(Color.BLACK);
		label3.setFont(new Font("Monospaced",Font.CENTER_BASELINE,25));
		label3.setForeground(Color.DARK_GRAY);
		label4.setFont(new Font("Monospaced",Font.CENTER_BASELINE,25));
		label4.setForeground(Color.DARK_GRAY);
		label5.setFont(new Font("Monospaced",Font.CENTER_BASELINE,25));
		label5.setForeground(Color.DARK_GRAY);
		panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0,10,0,10),BorderFactory.createRaisedBevelBorder()));
		label.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));
		label1.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));
		label2.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));
		label3.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));
		label4.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));
		label5.setBorder(BorderFactory.createEmptyBorder(0,20,20,0));
		panel.add(label);
		panel.add(label3);
		panel.add(label1);
		panel.add(label4);
		panel.add(label2);
		panel.add(label5);
	
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

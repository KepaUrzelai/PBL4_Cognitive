package diagnostico;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PlantillaJuegos4_expli_digitos extends JPanel {

	Image imagen;
	ImageIcon fondo;
	Color color;
	String campo, titulo;

	
	public PlantillaJuegos4_expli_digitos() {
		super(new BorderLayout());
		titulo = "Los Dígitos";
		fondo = new ImageIcon("imagenes/Fondo1d.png");
		campo = "Explicación : ";
		color = new Color(180,0,0);//rojo
		
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
		JPanel panel = new JPanel(new GridLayout(5,1));
		String texto = "<html><body>Directos: </body></html>";
		String texto2 = "<html><body>Inversos: </body></html>";
		String texto3 = "<html><body>Repite la secuencia de dígitos que te lea el doctor en el orden que lo haga él. ¡A por ello!\r\n" + 
				" </body></html>";
		String texto4 = "<html><body>Repite la secuencia de dígitos que te lea el doctor en el orden opuesto. ¡Suerte!\r\n" + 
				" </body></html>";
		String texto5 = "<html><body>Resultado: El número de dígitos que el usuario haya llegado a repetir. </body></html>";
		JLabel label = new JLabel(texto);
		JLabel label2 = new JLabel(texto2);
		JLabel label3 = new JLabel(texto3);
		JLabel label4 = new JLabel(texto4);
		JLabel label5 = new JLabel(texto5);
		label.setFont(new Font("Monospaced",Font.BOLD,34));
		label.setForeground(Color.BLACK);
		label2.setFont(new Font("Monospaced",Font.BOLD,34));
		label2.setForeground(Color.BLACK);
		label3.setFont(new Font("Monospaced",Font.CENTER_BASELINE,31));
		label3.setForeground(Color.DARK_GRAY);
		label4.setFont(new Font("Monospaced",Font.CENTER_BASELINE,31));
		label4.setForeground(Color.DARK_GRAY);
		label5.setFont(new Font("Monospaced",Font.CENTER_BASELINE,31));
		label5.setForeground(Color.DARK_GRAY);
		panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0,10,0,10),BorderFactory.createRaisedBevelBorder()));
		label.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));
		label2.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));
		label3.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));
		label4.setBorder(BorderFactory.createEmptyBorder(0,20,20,0));
		label5.setBorder(BorderFactory.createEmptyBorder(40,20,20,0));
		panel.add(label);
		panel.add(label3);
		panel.add(label2);
		panel.add(label4);
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

}

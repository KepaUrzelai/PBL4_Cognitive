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

public class PlantillaJuegos7_expli_abstraccion extends JPanel {

	Image imagen;
	ImageIcon fondo;
	Color color;
	String campo, titulo;

	
	public PlantillaJuegos7_expli_abstraccion() {
		super(new BorderLayout());
		titulo = "Abstracción verbal";
		fondo = new ImageIcon("imagenes/Fondo2d.png");
		campo = "Explicación : ";
		color = new Color(0,150,0);//verde
		
	    imagen = fondo.getImage();
		
		crearPanelVentana();
	}
	
	private void crearPanelVentana() {
		JPanel panel = new JPanel(new GridLayout(3,1));
		String texto = "<html><body>Di la semejanza que se te ocurra entre el significado de las dos palabras que te dará el doctor."
				+ " Cuanto más abstracta sea la semejanza, más puntos conseguirás.</body></html>";
		String texto1 = "<html><body>Resultado: 0=Nada que ver, 1=Algun parecido, 2=La semejanza exacta.</body></html>";
		String texto2 = "<html><body>Ejemplo. Altavoz y micrófono: 0=Música, 1=Aparatos electrónicos, 2=Dispositivos electrónicos musicales.</body></html>";
		JLabel label = new JLabel(texto);
		JLabel label1 = new JLabel(texto1);
		JLabel label2 = new JLabel(texto2);

		label.setFont(new Font("Monospaced",Font.CENTER_BASELINE,35));
		label.setForeground(Color.DARK_GRAY);
		
		label1.setFont(new Font("Monospaced",Font.CENTER_BASELINE,27));
		label1.setForeground(Color.DARK_GRAY);
		
		label2.setFont(new Font("Monospaced",Font.CENTER_BASELINE,27));
		label2.setForeground(Color.DARK_GRAY);
		
		panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0,10,0,10),BorderFactory.createRaisedBevelBorder()));
		label.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));
		label1.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));
		label2.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));
		
		panel.add(label);
		panel.add(label1);
		panel.add(label2);
		
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

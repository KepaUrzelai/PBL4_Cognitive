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
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;

public class PlantillaJuegos8_juego_abstraccion extends JPanel{

	Image imagen;
	ImageIcon fondo;
	Color color;
	String campo, titulo;
	JLabel lTexto0,lTexto1,lTexto2,lTexto3;
	JRadioButton bZero0,bUno0,bDos0,bZero1,bUno1,bDos1,bZero2,bUno2,bDos2,bZero3,bUno3,bDos3;
	ButtonGroup grp0,grp1,grp2,grp3;
	int puntuacion;
	
	public PlantillaJuegos8_juego_abstraccion() {
		super(new BorderLayout());
		titulo = "Abstracción verbal";
		fondo = new ImageIcon("imagenes/Fondo2d.png");
		campo = "Juego : ";
		color = new Color(0,150,0);//verde
	    imagen = fondo.getImage();
	    puntuacion = 0;
		crearPanelVentana();
	}
	
	private void crearPanelVentana() {
	
		this.add(crearPanelCentral(), BorderLayout.CENTER);
		
	}
	

	private Component crearPanelCentral(){
		JSplitPane panel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true,
				crearPanelLabel(),crearPanelBotonesTOTAL());
		panel.setEnabled(false);
		panel.setBorder(BorderFactory.createRaisedBevelBorder());
		panel.setOpaque(false);
		return panel;
	}
	private Component crearPanelBotonesTOTAL() {
		JPanel panel = new JPanel(new GridLayout(4, 1));
		
		panel.add(crearPanelBotones0());
		panel.add(crearPanelBotones1());
		panel.add(crearPanelBotones2());
		panel.add(crearPanelBotones3());
		panel.setOpaque(false);
		return panel;
	}
	private Component crearPanelBotones0() {
		JPanel panel = new JPanel(new GridLayout(1,3,10,10));
		bZero0 = new JRadioButton("Cero");
		bZero0.setOpaque(false);
		bZero0.setFont(new Font("Monospaced",Font.BOLD,30));
		bUno0 = new JRadioButton("Uno");
		bUno0.setOpaque(false);
		bUno0.setFont(new Font("Monospaced",Font.BOLD,30));
		bDos0 = new JRadioButton("Dos");
		bDos0.setOpaque(false);
		bDos0.setFont(new Font("Monospaced",Font.BOLD,30));

		grp0 = new ButtonGroup();
		
		grp0.add(bZero0);
		grp0.add(bUno0);
		grp0.add(bDos0);
		
		panel.add(bZero0);
		panel.add(bUno0);
		panel.add(bDos0);
		panel.setOpaque(false);
		panel.setBorder(BorderFactory.createEmptyBorder(40,50,40,50));
		return panel;
	}
	private Component crearPanelBotones1() {
		JPanel panel = new JPanel(new GridLayout(1,3,10,10));
		bZero1 = new JRadioButton("Cero");
		bZero1.setOpaque(false);
		bZero1.setFont(new Font("Monospaced",Font.BOLD,30));
		bUno1 = new JRadioButton("Uno");
		bUno1.setOpaque(false);
		bUno1.setFont(new Font("Monospaced",Font.BOLD,30));
		bDos1 = new JRadioButton("Dos");
		bDos1.setOpaque(false);
		bDos1.setFont(new Font("Monospaced",Font.BOLD,30));

		grp1 = new ButtonGroup();
		
		grp1.add(bZero1);
		grp1.add(bUno1);
		grp1.add(bDos1);
		
		panel.add(bZero1);
		panel.add(bUno1);
		panel.add(bDos1);
		panel.setOpaque(false);
		panel.setBorder(BorderFactory.createEmptyBorder(40,50,40,50));
		return panel;
	}
	private Component crearPanelBotones2() {
		JPanel panel = new JPanel(new GridLayout(1,3,10,10));
		bZero2 = new JRadioButton("Cero");
		bZero2.setOpaque(false);
		bZero2.setFont(new Font("Monospaced",Font.BOLD,30));
		bUno2 = new JRadioButton("Uno");
		bUno2.setOpaque(false);
		bUno2.setFont(new Font("Monospaced",Font.BOLD,30));
		bDos2 = new JRadioButton("Dos");
		bDos2.setOpaque(false);
		bDos2.setFont(new Font("Monospaced",Font.BOLD,30));

		grp2 = new ButtonGroup();
		
		grp2.add(bZero2);
		grp2.add(bUno2);
		grp2.add(bDos2);
		
		panel.add(bZero2);
		panel.add(bUno2);
		panel.add(bDos2);
		panel.setOpaque(false);
		panel.setBorder(BorderFactory.createEmptyBorder(40,50,40,50));
		return panel;
	}
	private Component crearPanelBotones3() {
		JPanel panel = new JPanel(new GridLayout(1,3,10,10));
		bZero3 = new JRadioButton("Cero");
		bZero3.setOpaque(false);
		bZero3.setFont(new Font("Monospaced",Font.BOLD,30));
		bUno3 = new JRadioButton("Uno");
		bUno3.setOpaque(false);
		bUno3.setFont(new Font("Monospaced",Font.BOLD,30));
		bDos3 = new JRadioButton("Dos");
		bDos3.setOpaque(false);
		bDos3.setFont(new Font("Monospaced",Font.BOLD,30));

		grp3 = new ButtonGroup();
		
		grp3.add(bZero3);
		grp3.add(bUno3);
		grp3.add(bDos3);
		
		panel.add(bZero3);
		panel.add(bUno3);
		panel.add(bDos3);
		panel.setOpaque(false);
		panel.setBorder(BorderFactory.createEmptyBorder(40,40,40,40));
		return panel;
	}
	
	private Component crearPanelLabel() {
		JPanel panel = new JPanel (new GridLayout(4, 1,50,75));
		lTexto0 = new JLabel("Hacha - Sierra");
		lTexto0.setBorder(BorderFactory.createEmptyBorder(0,0,5,0));
		lTexto0.setFont(new Font("Monospaced",Font.BOLD,30));
		lTexto1 = new JLabel("Naranja - Plátano");
		lTexto1.setBorder(BorderFactory.createEmptyBorder(0,0,5,0));
		lTexto1.setFont(new Font("Monospaced",Font.BOLD,30));
		lTexto2 = new JLabel("Perro - León");
		lTexto2.setBorder(BorderFactory.createEmptyBorder(0,0,5,0));
		lTexto2.setFont(new Font("Monospaced",Font.BOLD,30));
		lTexto3 = new JLabel("Poema - Estatua");
		lTexto3.setBorder(BorderFactory.createEmptyBorder(0,0,5,0));
		lTexto3.setFont(new Font("Monospaced",Font.BOLD,30));
		panel.add(lTexto0);
		panel.add(lTexto1);
		panel.add(lTexto2);
		panel.add(lTexto3);
		panel.setOpaque(false);
		panel.setBorder(BorderFactory.createEmptyBorder(45,50,40,50));
		return panel;
	}
	
	public void calcularPuntuacion() {
			if(bUno0.isSelected()) {
				puntuacion++;
			}else if(bDos0.isSelected()) {
				puntuacion += 2;
			}
			if(bUno1.isSelected()) {
				puntuacion++;
			}else if(bDos1.isSelected()) {
				puntuacion += 2;
			}
			if(bUno2.isSelected()) {
				puntuacion++;
			}else if(bDos2.isSelected()) {
				puntuacion += 2;
			}
			if(bUno3.isSelected()) {
				puntuacion++;
			}else if(bDos3.isSelected()) {
				puntuacion += 2;
			}
	}
	
	public int getPuntuacion() {
		return this.puntuacion;
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

	public JRadioButton getbZero0() {
		return bZero0;
	}

	public JRadioButton getbUno0() {
		return bUno0;
	}

	public JRadioButton getbDos0() {
		return bDos0;
	}

	public JRadioButton getbZero1() {
		return bZero1;
	}

	public JRadioButton getbUno1() {
		return bUno1;
	}

	public JRadioButton getbDos1() {
		return bDos1;
	}

	public JRadioButton getbZero2() {
		return bZero2;
	}

	public JRadioButton getbUno2() {
		return bUno2;
	}

	public JRadioButton getbDos2() {
		return bDos2;
	}

	public JRadioButton getbZero3() {
		return bZero3;
	}

	public JRadioButton getbUno3() {
		return bUno3;
	}

	public JRadioButton getbDos3() {
		return bDos3;
	}

	public ButtonGroup getGrp0() {
		return grp0;
	}

	public ButtonGroup getGrp1() {
		return grp1;
	}

	public ButtonGroup getGrp2() {
		return grp2;
	}

	public ButtonGroup getGrp3() {
		return grp3;
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

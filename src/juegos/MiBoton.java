package juegos;


import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MiBoton extends JButton{
	
	String id;
	boolean estado;
	ImageIcon imagen;
	public MiBoton(String id) {
		super();
		this.id=id;
		this.estado=false;
		System.out.println("boton");
		this.setBackground(Color.BLACK);
		this.setIcon(imagen);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ImageIcon getImagen() {
		return imagen;
	}

	public void setImagen(ImageIcon imagen) {
		this.imagen = imagen;
	}

	public void cambiarFondo() {//se utiliza en el juego de las cartas
		
		this.setBackground(Color.WHITE);
	}
	public void cambiarFondo2() {//se utiliza en el juego de las cartas
		
		this.setIcon(new ImageIcon(""));
		this.setBackground(Color.BLACK);
	}
	
	public void conseguido() {//se utiliza en el juego de las cartas
		this.setBackground(Color.GREEN);
	}
	
	public void cambiarEstado() {//se utiliza en el juego de las cartas
		if(this.estado==false) {
			this.estado=true;			
		}
		else {
			this.estado=false;		
		}
	}

	public boolean isEstado() {
		return estado;
	}
}

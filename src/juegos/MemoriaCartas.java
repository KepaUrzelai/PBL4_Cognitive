package juegos;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MemoriaCartas extends Observable implements ActionListener {

	JPanel panel;
	JList<JButton> lBotones;
	List<Integer> numeros;//cada botón contiene un número
	List<ImageIcon> imagenes;//cada botón contiene una imagen
	ImageIcon vaca1, vaca2, croco1, croco2, leon1, leon2, perro1, perro2, gato1, gato2, oveja1, oveja2;
	List<MiBoton> botones;
	DefaultListModel<JButton> botonesLista;
	

	int kont; //registra los botones que hay clickados.
	MiBoton uno, dos; //registrará el primer botón y el segundo clickados, para así compararlos.
	int conseguido;//por cada acierto sumara uno.
	

	ImageIcon num;//se usa para ponerle una imagen a un botón, cuando este es clickado.
	int pos, pos2;//aqui se registran las posiciones de los botones.
	int numC;//para voltear otra vez los botones.
	
	final static String explicación="Esta práctica trata de hacer parejas hasta que no quede ninguna.\n";
	
	public String getExplicación() {
		return explicación;
	}
	
	public MemoriaCartas() {
		
		
		inicializarImagenes();
		crearPanelVentana();
	}
	
	public void inicializarImagenes() {
		vaca1=new ImageIcon("imagenes/faca.png");
		vaca2=new ImageIcon("imagenes/faca.png");
		croco1=new ImageIcon("imagenes/croco.png");
		croco2=new ImageIcon("imagenes/croco.png");
		leon1=new ImageIcon("imagenes/leon.png");
		leon2=new ImageIcon("imagenes/leon.png");
		perro1=new ImageIcon("imagenes/perro.png");
		perro2=new ImageIcon("imagenes/perro.png");
		gato1=new ImageIcon("imagenes/gato.png");
		gato2=new ImageIcon("imagenes/gato.png");
		oveja1=new ImageIcon("imagenes/oveja.png");
		oveja2=new ImageIcon("imagenes/oveja.png");
		imagenes=new ArrayList<>();
		imagenes.add(vaca1);
		imagenes.add(vaca2);
		imagenes.add(croco1);
		imagenes.add(croco2);
		imagenes.add(leon1);
		imagenes.add(leon2);
		imagenes.add(perro1);
		imagenes.add(perro2);
		imagenes.add(gato1);
		imagenes.add(gato2);
		imagenes.add(oveja1);
		imagenes.add(oveja2);
		Collections.shuffle(imagenes);
	}
	
	private void crearPanelVentana() {
		panel = new JPanel(new BorderLayout(10, 10));
		panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10,10,10,10), BorderFactory.createRaisedBevelBorder()));
		panel.setOpaque(false);

		panel.add(CrearPanelMenu(), BorderLayout.CENTER);
	}


	private Component CrearPanelMenu() {
		JPanel panel = new JPanel(new GridLayout (4,4));
		JScrollPane panel2 =  new JScrollPane( JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		botones= new ArrayList<>();
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
		for(int i=0;i<12;i++) {
			botones.add((MiBoton) crearBoton(i));
			panel.add(botones.get(i));
		}
		panel2.setViewportView(panel);
		
		return panel2;		

	}
	
	private JButton crearBoton(int i) {
		
		MiBoton boton = new MiBoton(String.valueOf(i));
		boton.addActionListener(this);
		boton.setActionCommand(String.valueOf(i));
		return boton;
		
	}

	public void actualizar() {
		if(numC==-1) {//se voltean los botones
			botones.get(pos).setIcon(new ImageIcon(""));
			botones.get(pos2).setIcon(new ImageIcon(""));
		}
		else {//se pone la imagen en el botón
		botones.get(pos).setIcon(num);
		}
		if((conseguido==5)&&(kont==2)) {//juego terminado. si hay 5 parejas correctas, y has pulsado otros dos botones, son los que quedan por lo cual estan correctos.
			this.setChanged();
			this.notifyObservers();
		}
		
		
	}
	
	public JPanel getPanelPrincipal() {
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand().equals("salir")) {
			System.exit(0);
		}
		if(kont==2) {//en caso de que haya dos pulsados, se comprueba si estan bien.			
			if(imagenes.get(botones.indexOf(uno)).getImage().equals(imagenes.get(botones.indexOf(dos)).getImage())) {

				uno.removeActionListener(this);//cada vez que se pulsa un botón, se desactiva
				dos.removeActionListener(this);
				uno.conseguido();
				dos.conseguido();
				uno.cambiarEstado();
				dos.cambiarEstado();
				conseguido();
				kont=0;
				conseguido++;
			}
			else {

				uno.addActionListener(this);
				dos.addActionListener(this);
				uno.cambiarEstado();
				uno.cambiarFondo2();
				dos.cambiarEstado();
				dos.cambiarFondo2();
				kont=0;
			}
		}
		else {
		int  i;
		kont=0;
		
		uno=new MiBoton("");
		dos=new MiBoton("");
		
		pillarNumero(e.getActionCommand(), imagenes.get(Integer.parseInt(e.getActionCommand())));
		MiBoton boton=(MiBoton) e.getSource();
		boton.removeActionListener(this);
		boton.cambiarEstado();
		boton.cambiarFondo();
		for(i=0;i<12;i++) {
			if(botones.get(i).isEstado()==true) {
				kont++;
				if(kont==1)uno=botones.get(i);
				else { 
					dos=botones.get(i);
				}
			}
		}
		}
		try {
		if((conseguido==5) || (imagenes.get(botones.indexOf(uno)).getImage().equals(imagenes.get(botones.indexOf(dos)).getImage()))) {
			if(kont==2) {

				
				if(imagenes.get(botones.indexOf(uno)).getImage().equals(imagenes.get(botones.indexOf(dos)).getImage())) {

					uno.removeActionListener(this);
					dos.removeActionListener(this);
					uno.conseguido();
					dos.conseguido();
					uno.cambiarEstado();
					dos.cambiarEstado();
					conseguido();
					kont=0;
					conseguido++;
				}
			}
		}
		}catch (IndexOutOfBoundsException e1){

		}
		
	}
	public void pillarNumero(String posicion,ImageIcon imagen) {

		num=imagen;
		pos=Integer.parseInt(posicion);
		actualizar();
	}
	
	public void conseguido() {
		actualizar();
	}
	
	public void cambiartexto(int pos2, int pos) {
		numC=-1;
		this.pos2=pos2;
		this.pos=pos;
		actualizar();
	}
}
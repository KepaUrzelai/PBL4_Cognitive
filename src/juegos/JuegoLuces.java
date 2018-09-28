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
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


public class JuegoLuces extends Observable implements ActionListener {
	JPanel panelPrincipal;
	List<Icon> colores;
	
	JLabel label;
	Timer timer;
	MiTarea miTarea;
	Random rand;
	int k=0;// k -> se utiliza para comparar el color seleccionado por el color en la posición k de la lista. 
	boolean terminado=false;
	int nivel, fallos;
	
	final static String explicación="Apareceran una serie de colores en la parte superior de la pantalla. Tendrá que\n"
			+ " repetir el orden de los colores pulsando los botones de abajo.";
	
	public String getExplicación() {
		return explicación;
	}
	
	public JuegoLuces() {
		rand = new Random();
		inicializar();
		miTarea=new MiTarea();
		nivel = 1;
		fallos = 0;
		panelPrincipal = new JPanel();
		panelPrincipal = crearPanelVentana();
		panelPrincipal.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10,10,10,10), BorderFactory.createRaisedBevelBorder()));
		panelPrincipal.setOpaque(false);
		
		panelPrincipal.setVisible(true);
		enseñarImagenes();
	}

	public JPanel getPanelPrincipal() {
		return panelPrincipal;
	}

	private void enseñarImagenes() {
		terminado = false;
		Collections.shuffle(colores);
		label.setIcon(colores.get(0));
		timer=new Timer(2000, miTarea);
		timer.start();
		
	}
	
	private void inicializar() {
		colores=new ArrayList<>();
		colores.add(new ImageIcon("imagenes/azul.jpg"));
		colores.add(new ImageIcon("imagenes/verde.jpg"));
		colores.add(new ImageIcon("imagenes/naranja.jpg"));
		
	}
	
	private void inicializar2() {//este juego es moldeable, por lo que se podrían poner otras imagenes, en vez de solo colores.
		colores=new ArrayList<>();
		colores.add(new ImageIcon("imagenes/sonriente.png"));
		colores.add(new ImageIcon("imagenes/normal.png"));
		colores.add(new ImageIcon("imagenes/triste.png"));
		
	}

	private JPanel crearPanelVentana() {
		JPanel panel=new JPanel(new GridLayout(2,1));
		panel.setOpaque(false);
		panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		panel.add(crearPanelColor());
		panel.add(crearPanelBotones());
		return panel;
	}

	private Component crearPanelBotones() {
		JPanel panel=new JPanel(new GridLayout(1,3,20,0));
		panel.setOpaque(false);
		panel.setBorder(BorderFactory.createEmptyBorder(0,0,50,0));
		for(Icon e:colores) {
			JButton boton=new JButton();
			boton.setIcon(e);
			boton.addActionListener(this);
			boton.setOpaque(true);
			panel.add(boton);
		}		
		return panel;
	}

	private Component crearPanelColor() {
		JPanel panel=new JPanel(new BorderLayout());
		panel.setOpaque(false);
		panel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
		label=new JLabel();
		label.setOpaque(true);
		label.setBackground(null);
		panel.add(label, BorderLayout.CENTER);
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(!terminado) return;
		JButton b=(JButton) e.getSource();
		if(b.getIcon().equals(colores.get(k)) && nivel < 5) {  
			k++;											   
			if(k==colores.size()) {
				JOptionPane.showMessageDialog(panelPrincipal, "Correcto","Correcto",JOptionPane.NO_OPTION);
				if (nivel < 3) {
					colores.add(colores.get(rand.nextInt(colores.size())));
					enseñarImagenes();
				}
				k = 0;
				nivel++;
			}
		}
		else if(fallos < 3){ 
			JOptionPane.showMessageDialog(panelPrincipal, "Fallaste","Incorrecto",JOptionPane.ERROR_MESSAGE);
			k=0;
			fallos++;
			if(fallos!=3) enseñarImagenes();			
		}
		
		if (nivel == 3) {
			JOptionPane.showMessageDialog(panelPrincipal, "Has Ganado","Correcto",JOptionPane.NO_OPTION);
			setChanged();
			notifyObservers();
		}		
		else if (fallos == 3) {
			
			JOptionPane.showMessageDialog(panelPrincipal, "Has perdido","Incorrecto",JOptionPane.ERROR_MESSAGE);
			setChanged();
			notifyObservers();
		}
		else {
		panelPrincipal.repaint();
		panelPrincipal.revalidate();
		}

	}
	
	
	private class MiTarea implements ActionListener{
		int j=1;

		@Override
		public void actionPerformed(ActionEvent e) {
			if(j<colores.size()) {
				label.setIcon(colores.get(j));
				label.repaint();
				j++;
			}
			else {
				timer.stop();
				label.setBackground(null);
				label.setIcon(null);
				j = 1;
				terminado=true;
			}
		}
	
	}

}

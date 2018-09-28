package juegos;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Atencion01RutaColores extends Observable implements ActionListener {

	JPanel panel;
	JTextField texto1;
	JButton boton;
	JLabel puntos;
	List<JLabel> numeros;
	List<JButton> botones;
	int kont;//contador para saber si esta dando al boton correcto y para seguir un orden=1,2,3,4,5....
	
	final static String explicación="Tendrá que seleccionar los botones de uno en uno en orden ascendente, empezando por el 1 verde,\n"
			+ "y turnando los colores. Esto es, después iría el 2 amarillo. Si falla tendrá que volver a empezar.";
	
	public String getExplicación() {
		return explicación;
	}
	
	public Atencion01RutaColores() {
		
		panel = new JPanel(new BorderLayout(10, 10));
		inicializarNumeros();
		crearPanelVentana();
	}

	public void inicializarNumeros() {
		numeros= new ArrayList<>();
		
		for (int i=1;i<11;i++) {
			JLabel label= new JLabel("     " + String.valueOf(i));
			label.setOpaque(false);
			numeros.add(label);
		}
		for (int i=1;i<11;i++) {
			JLabel label= new JLabel("     " + String.valueOf(i));
			label.setOpaque(false);
			numeros.add(label);
			}
	}
	
	private void crearPanelVentana() {
	
		panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10,10,10,10), BorderFactory.createRaisedBevelBorder()));
		panel.add(CrearPanelMenu(), BorderLayout.CENTER);
		panel.setOpaque(false);
	}


	private Component CrearPanelMenu() {
		JPanel panel = new JPanel(new GridLayout (7,7, 20, 20));
		botones= new ArrayList<>();
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
		for(int i=0;i<49;i++) {
			botones.add(crearBoton(String.valueOf(i)));
		}
		for(int j=0;j<49;j++) {
			try{//mientras haya numeros se iran añadiendo a los labels, cuando no queden no se añadira nada; uno verde, otro amarillo.
				botones.get(j).add(numeros.get(j), JButton.CENTER);
				botones.get(j).setFont(new Font("Monospaced", Font.BOLD, 20));
				if(j<10) {	
					botones.get(j).setBackground(Color.GREEN);
				}
				else botones.get(j).setBackground(Color.YELLOW);
			}catch (IndexOutOfBoundsException e){
				botones.get(j).add(new JLabel(""));
				botones.get(j).setVisible(false);
				botones.get(j).setEnabled(false);
			}
		}
		Collections.shuffle(botones);
		for(int l=0;l<49;l++) {
			panel.add(botones.get(l));
		}
		return panel;
		
		

	}
	
	private JButton crearBoton(String nombre) {
		
		JButton boton = new JButton();
		boton.addActionListener(this);
		boton.setActionCommand(nombre);
	//	boton.setOpaque(false);
	//	boton.setBackground(Color.WHITE);
		return boton;
		
	}

	public JTextField getTexto1() {
		return texto1;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		if(String.valueOf(kont).equals(e.getActionCommand())) {
			JButton boton=(JButton) e.getSource();
			boton.setVisible(false);
			boton.setEnabled(false);
			if(kont<10) {
				kont+=11;
			}
			else kont+=(-9);
			if(kont==10) {
				this.setChanged();
				this.notifyObservers();
			}
		}
		else {
			for(int i=0;i<49;i++) {
				if((botones.get(i).getBackground()==Color.GREEN) || (botones.get(i).getBackground()==Color.YELLOW)) {//solo los que tienes número se haran visibles. Si no hacemos esto, todos los botones se ven.
				botones.get(i).setEnabled(true);
				botones.get(i).setVisible(true);
				}
				kont=0;
			}
		}
		
	}

	public JPanel getPanelPrincipal() {
		return panel;
	}
}

package juegos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jfree.ui.RefineryUtilities;

import diagnostico.MiPanel;

public class JuegoMate3 extends Observable implements ActionListener{
	private static final long serialVersionUID = 1L;
	JPanel panelPrincipal;
	ImageIcon fondo;
	Color color;
	int i, result, respuestaCorrecta, kont;//variable i define las unidades que tiene la respuesta. Si kont llega a 10 se acaba.
	String campo, titulo;
	JLabel operacion, resultado;
	String[] strResultado;
	JTextField texto;
	
	final static String explicación="Apareceran operaciones matemáticas que usted tendrá que resolver.\n";
	
	public String getExplicación() {
		return explicación;
	}
	
	public JuegoMate3() {
		
		titulo = "Cálculos matemáticos";
		kont=0;
		i = 0;
		result = 0;
		strResultado = new String[3];
		crearPanelVentana();
	}

	private void crearPanelVentana() {
		panelPrincipal = new JPanel(new BorderLayout());
		panelPrincipal.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10,10,10,10), BorderFactory.createRaisedBevelBorder()));
		panelPrincipal.setOpaque(false);
		panelPrincipal.add(crearPanelSuperior(), BorderLayout.NORTH);
		panelPrincipal.add(crearPanelCentral(), BorderLayout.CENTER);
		panelPrincipal.add(crearPanelInferior(), BorderLayout.SOUTH);
		
	}

	public JPanel getPanelPrincipal() {
		return panelPrincipal;
	}

	private Component crearPanelSuperior() {
		JPanel panel = new JPanel(new BorderLayout());
		JLabel lCampo = new JLabel(this.campo);
		JLabel lTitulo = new JLabel(this.titulo);
		
		lCampo.setFont(new Font("Monospaced", Font.BOLD, 30));
		lCampo.setForeground(this.color);
		lCampo.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		lTitulo.setFont(new Font("Monospaced", Font.BOLD, 30));
		lTitulo.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		panel.add(lCampo, BorderLayout.WEST);
		panel.add(lTitulo, BorderLayout.CENTER);
		panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10,10,10,10), BorderFactory.createRaisedBevelBorder()));
		panel.setOpaque(false);
		return panel;
	}

	private Component crearPanelCentral() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(crearPanelOperacion(), BorderLayout.NORTH);
		panel.add(crearPanelNumerico(), BorderLayout.CENTER);
		panel.setOpaque(false);
		return panel;
	}
	private Component crearPanelOperacion() {
		JPanel panel = new JPanel( new FlowLayout());
		JLabel igual = new JLabel(" = ");
		igual.setFont(new Font("Monospaced", Font.BOLD, 70));
		igual.setForeground(this.color);
		
		operacion = new JLabel(generarOperacion());
		operacion.setFont(new Font("Monospaced", Font.BOLD, 70));
		operacion.setForeground(this.color);
		
		texto=new JTextField("   ");
		texto.setEditable(false);
		texto.setFont(new Font("Monospaced", Font.BOLD, 70));
		
		panel.add(operacion);
		panel.add(igual);
		panel.add(texto);
		panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		panel.setOpaque(false);
		return panel;
	}
	private Component crearPanelNumerico() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(panelNumCentral(), BorderLayout.CENTER);
		panel.add(panelNumDcha(), BorderLayout.EAST);
		panel.setBorder(BorderFactory.createEmptyBorder(10,30,10,30));
		panel.setOpaque(false);
		return panel;
	}

	private Component panelNumDcha() {
		JPanel panel = new JPanel(new BorderLayout());
		JButton enter = new JButton("  =  ");
		enter.setFont(new Font("Monospaced", Font.BOLD,50));
		enter.setForeground(this.color);
		enter.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		enter.setActionCommand("=");
		enter.addActionListener(this);
		
		JButton borrar = new JButton(" Borrar ");
		borrar.setFont(new Font("Monospaced", Font.BOLD,40));
		borrar.setForeground(this.color);
		borrar.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		borrar.setActionCommand("B");
		borrar.addActionListener(this);
		panel.add(enter,BorderLayout.CENTER);
		panel.add(borrar,BorderLayout.SOUTH);
		panel.setOpaque(false);
		return panel;
	}

	private Component panelNumCentral() {
		JPanel panel = new JPanel(new GridLayout(2,5));
		for(int j = 0; j < 10; j++) {
			panel.add(crearBotonNum(j));
		}
		panel.setBorder(BorderFactory.createEmptyBorder(0,10,0,30));
		panel.setOpaque(false);
		return panel;
	}
	private Component crearBotonNum(int i) {
		JButton boton = new JButton(String.valueOf(i));
		boton.setFont(new Font("Monospaced", Font.BOLD, 40));
		boton.setForeground(this.color);
		boton.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		boton.setActionCommand(String.valueOf(i));
		boton.addActionListener(this);
		boton.setOpaque(false);
		return boton;
	}
	private Component crearPanelInferior() {
		JPanel panel = new JPanel();
		
		panel.setBorder(BorderFactory.createLineBorder(color));
		panel.setOpaque(false);
		return panel;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(!e.getActionCommand().equals("=")) {
			switch(e.getActionCommand()) {//El switch case llena el string del resultado en el orden metido por el user
			case "0": strResultado[i] = e.getActionCommand(); i++;
				break;
			case "1": strResultado[i] = e.getActionCommand(); i++;
				break;
			case "2": strResultado[i] = e.getActionCommand(); i++;
				break;
			case "3": strResultado[i] = e.getActionCommand(); i++;
				break;
			case "4": strResultado[i] = e.getActionCommand(); i++;
				break;
			case "5": strResultado[i] = e.getActionCommand(); i++;
				break;
			case "6": strResultado[i] = e.getActionCommand(); i++;
				break;
			case "7": strResultado[i] = e.getActionCommand(); i++;
				break;
			case "8": strResultado[i] = e.getActionCommand(); i++;	
				break;
			case "9": strResultado[i] = e.getActionCommand(); i++;
				break;
			case "B": strResultado = null; i = 0;
				strResultado = new String[3];
				texto.setText("   ");
				break;
			}
		}
		if(strResultado[2]!=null) {//esto se utiliza para visualizar el resultado correctamente en el textField
			texto.setText(String.valueOf((Integer.parseInt(strResultado[0])*100)+(Integer.parseInt(strResultado[1])*10)+(Integer.parseInt(strResultado[2])*1)));
			}
			else if(strResultado[1]!=null) {
			texto.setText(" "+String.valueOf((Integer.parseInt(strResultado[0])*10)+(Integer.parseInt(strResultado[1])*1)));
			}
			else {
			texto.setText(" "+" "+String.valueOf((Integer.parseInt(strResultado[0])*1)));
			}
		
		if(e.getActionCommand().equals("=")) {
			int d = 100, d2 = 1000;
			switch(i) {
				case 1: result += Integer.valueOf(strResultado[0]); break;
				case 2: result = result + (Integer.valueOf(strResultado[0]) * 10) + Integer.valueOf(strResultado[1]);
					break;
				case 3: for(int f = 0; f < i; f++) {
					result = result + (Integer.valueOf(strResultado[f]) * d);
					d/= 10;
				} break;
				case 4: for(int f = 0; f < i; f++) {
					result = result + (Integer.valueOf(strResultado[f]) * d2);
					d2/= 10;
				} break;	
			}
			if(result == respuestaCorrecta) {				
				kont++;
				if(kont==10) {
					operacion.setText("Finalizado");
					texto.setVisible(false);
					this.setChanged();
					this.notifyObservers();		
				}
				else operacion.setText(generarOperacion()); 
			}else {
				System.out.println("Okerra"); //comprobar
			}
			
			strResultado = null; i = 0;
			strResultado = new String[3];
			result = 0;
			texto.setText("   ");
		}
	}

	private String generarOperacion() {
		int max, a, b;
		char operador = ' ';
		max = 20;
		
		a = getRandomInt(max) + 1;
		b = getRandomInt(max) + 1;
		
		switch(getRandomInt(3)) {
		case 0: operador = '+';
		this.respuestaCorrecta = a + b;
		break;
		case 1: operador = '-';
		if(a < b) {
			this.respuestaCorrecta = b - a;
			return b+" "+operador+" "+a;
		}
		this.respuestaCorrecta = a - b;
		break;
		case 2: operador = 'x';
		if(b > 10) {
			b -= 10;
		}
		this.respuestaCorrecta = a * b;
		break;
		}
		
		return a+" "+operador+" "+b;
	}
	private int getRandomInt(int max) {
		  return (int) Math.floor(Math.random() * Math.floor(max));
	}
}

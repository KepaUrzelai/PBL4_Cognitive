package juegos;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import usuarioYcronometro.Cronometro;

public class JuegoEfectoStroop extends Observable implements ActionListener{
	private static final long serialVersionUID = 1L;
	JButton botonAzul,botonRojo,botonVerde;
	Random random1;
	int contPalabras, valor2, valor1, temp1, temp2,contCronometro;
	JLabel label;
	Cronometro crono;
	JPanel panelPrincipal;
	
	final static String explicación="Esta práctica consta de tres partes. En la primera parte tendrá que seleccionar el color escrito. En la segunda, el\n"
			+ "color de las 'xxxx'. Y en la última el color del texto que aparezca. Tendrá 20 segundos para cada parte";
	
	public String getExplicación() {
		return explicación;
	}
	
	public JuegoEfectoStroop() {
		panelPrincipal = new JPanel();
		panelPrincipal.setOpaque(false);
		panelPrincipal.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10,10,10,10), BorderFactory.createRaisedBevelBorder()));
		panelPrincipal.add(crearPanelCentral());
		panelPrincipal.setVisible(true);
	}

	private Component crearPanelCentral() {
		JPanel panel = new JPanel(new GridLayout(3,1,20,20));
		panel.setOpaque(false);
		crono = new Cronometro(1,20);
		temp1 = temp2 = 1;
		crono.iniciar();
		contCronometro = 0;
		contPalabras=0;
		panel.add(crono);
		panel.add(crearPanelLabels());
		panel.add(crearPanelBotones());
		return panel;
	}
	
	public JPanel getPanelPrincipal() {
		return panelPrincipal;
	}
	private Component crearPanelBotones() {
		JPanel panel = new JPanel(new GridLayout(1,3,10,10));
		panel.setOpaque(false);
		botonAzul = new JButton("Azul");
		botonAzul.setBorder(BorderFactory.createEtchedBorder());
		botonAzul.setFont(new Font("Century Gothic",Font.BOLD,20));
		
		botonRojo = new JButton("Rojo");
		botonRojo.setBorder(BorderFactory.createEtchedBorder());
		botonRojo.setFont(new Font("Century Gothic",Font.BOLD,20));
		
		botonVerde = new JButton("Verde");
		botonVerde.setBorder(BorderFactory.createEtchedBorder());
		botonVerde.setFont(new Font("Century Gothic",Font.BOLD,20));

		
		botonAzul.addActionListener(this);
		botonAzul.setActionCommand("Azul");
		botonRojo.addActionListener(this);
		botonRojo.setActionCommand("Rojo");
		botonVerde.addActionListener(this);
		botonVerde.setActionCommand("Verde");
		
		
		panel.add(botonAzul);
		panel.add(botonRojo);
		panel.add(botonVerde);
		return panel;
	}

	private Component crearPanelLabels() {
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		label = new JLabel("Rojo");
		random1 = new Random();
		label.setForeground(Color.GRAY);
		label.setFont(new Font("Monospaced",Font.BOLD,40));
		panel.add(label);
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		contPalabras = contPalabras +1;
		if (contCronometro == 0) {
			if (e.getActionCommand().equals(label.getText())) {
				if(crono.getSeconds()==0) {
					crono.iniciar();
					contCronometro+=1;
				}
				cambiarLetra(contCronometro);
			}
		}
		else if(e.getActionCommand().equals("Rojo") && label.getForeground().equals(Color.RED)) {
			if(crono.getSeconds()==0) {
				crono.iniciar();
				contCronometro+=1;
			}
			cambiarLetra(contCronometro);
		}
		else if(e.getActionCommand().equals("Verde") && label.getForeground().equals(Color.GREEN)) {
			if(crono.getSeconds()==0) {
				crono.iniciar();
				contCronometro+=1;
			}
			cambiarLetra(contCronometro);
		}
		else if(e.getActionCommand().equals("Azul") && label.getForeground().equals(Color.BLUE)) {
			if(crono.getSeconds()==0) {
				crono.iniciar();
				contCronometro+=1;
			}
			cambiarLetra(contCronometro);
		}
		if (contCronometro == 3) {
			botonAzul.setEnabled(false);
			botonRojo.setEnabled(false);
			botonVerde.setEnabled(false);
			crono.detener();
			this.setChanged();
			this.notifyObservers();
		}
	}
		

	public void cambiarLetra(int contCronometro) {
		valor1 = random1.nextInt(3);
		valor2 = random1.nextInt(3);
	
		while (valor1 != valor2 && valor1 == temp1 && valor2 == temp2) {
			valor1 = random1.nextInt(3);
			valor2 = random1.nextInt(3);
		}
		temp1 = valor1;
		temp2 = valor2;
		switch (contCronometro) {
		case 0:
			label.setText(seleccionarPalabra(valor1));
			break;
		case 1:
			label.setText("xxxx");
			label.setForeground(seleccionarColores(valor2));
			break;
		case 2:
			label.setText(seleccionarPalabra(valor1));
			label.setForeground(seleccionarColores(valor2));
			break;
		default:
		break;
		}
	}
	public Color seleccionarColores(int valor) {
		
		switch(valor) {
		case 0:
			return Color.BLUE;
		case 1:
			return Color.RED;
		case 2:
			return Color.GREEN;
		default:
			return Color.BLUE;
		}
	}
	
	public String seleccionarPalabra(int valor ) {
		
		switch(valor) {
		case 0:
			return "Verde";
		case 1:
			return "Azul";
		case 2:
			return "Rojo";
		default:
			return "Rojo";
		}
	}
	
}



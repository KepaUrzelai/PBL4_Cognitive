package juegos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JuegoMateOperacion extends Observable{
	JButton [] botones;
	JLabel central;
	JPanel panelPrincipal;
	Random rand;
	int randNum1, randNum2, gen;//el primero número, el segundo número, y el gen se utiliza para que aleatoriamente se decida el operador.
	int ans;//la respuesta correcta
	int nivel, rangoMin, rangoMax;
	int kont;//cuando llegue a 10 acaba el juego
	
	final static String explicación="A continuación le apareceran varias operaciones matemáticas. A estas"
			+ " operaciones les falta\nel operador y su trabajo consiste en adivinar cual encaja en cada operación.";
	
	public String getExplicación() {
		return explicación;
	}


	public JuegoMateOperacion(int nivel) {
		
		panelPrincipal = new JPanel(new BorderLayout());
		kont = 0;
		central = new JLabel();
		central.setFont(new Font("Monospaced", Font.BOLD, 60));
        central.setHorizontalAlignment(JLabel.CENTER);
		this.nivel = nivel;
		setRange();
		panelPrincipal.add(panelBotones(), BorderLayout.SOUTH);
		panelPrincipal.add(crearOperacion(), BorderLayout.CENTER);
		
		panelPrincipal.setOpaque(false);
		panelPrincipal.setVisible(true);
	}
	
	
	public JPanel panelBotones() {
		JPanel panel = new JPanel(new GridBagLayout());
		botones = new JButton[4];
		panel.setOpaque(false);
		
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;//La columna en la que estÃ¡ 
        gbc.gridy = 0;//La fila en la que estÃ¡
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipadx = 20;
        gbc.ipady = 20;
		
		botones[0] = new JButton("+");
		botones[1] = new JButton("-");
		botones[2] = new JButton("x");
		botones[3] = new JButton("/");
		
		panel.add(botones[0], gbc);
		botones[0].setFont(new Font("Monospaced", Font.BOLD, 30));
		gbc.gridx++;
		panel.add(botones[1], gbc);
		botones[1].setFont(new Font("Monospaced", Font.BOLD, 30));
		gbc.gridx++;
		panel.add(botones[2], gbc);
		botones[2].setFont(new Font("Monospaced", Font.BOLD, 30));
		gbc.gridx++;
		panel.add(botones[3], gbc);
		botones[3].setFont(new Font("Monospaced", Font.BOLD, 30));
		gbc.gridx++;
		botones[0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            		if (ans == randNum1 + randNum2) {
            			correcto();
            		}
            }
		});
		botones[1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            		if (ans == randNum1 - randNum2) {
            			correcto();
            		}
            }
		});
		botones[2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            		if (ans == randNum1 * randNum2) {
            			correcto();
            		}
            }
		});
		botones[3].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            		if (ans == randNum1 / randNum2) {
            			correcto();
            		}
            }
		});
		return panel;
	}
	
	public void correcto() {
		kont++;
		if (kont == 10) {
			for(int i=0;i<=3;i++) {
			botones[i].setEnabled(false);
			}
			central.setText("");
			this.setChanged();
			this.notifyObservers();
		}
		else{
			panelPrincipal.add(crearOperacion(), BorderLayout.CENTER);
			panelPrincipal.revalidate();
			panelPrincipal.repaint();
		}
		
		
	}
	
	public JPanel getPanelPrincipal() {
		return panelPrincipal;
	}


	public JLabel crearOperacion() {
		rand = new Random();
		
		
        randNum1 = rand.nextInt(rangoMax) + rangoMin;//el primer dígito
		randNum2 = rand.nextInt(rangoMax) + rangoMin;//el segundo dígito
		gen = rand.nextInt(4);

		if (gen == 0) {
			ans = randNum1 + randNum2;
		}
		else if (gen == 1) {
			ans = randNum1 - randNum2;
		}
		else if (gen == 2) {
			ans = randNum1 * randNum2;
		}
		else if (gen == 3) {
			while (randNum1 % randNum2 != 0) {
				randNum1 = rand.nextInt(rangoMax)+rangoMin;
				randNum2 = rand.nextInt(rangoMax)+rangoMin;
			}
			ans = randNum1 / randNum2;
		}
		
		central.setText(randNum1 + " _ " + randNum2 + " = " + ans);
		
		
		return central;
	}
	
	public void setRange() {
		if (nivel == 1) {
			rangoMin = 1;
			rangoMax = 5;
		}
		else if (nivel == 2) {
			rangoMin = 1;
			rangoMax = 10;
		}
		else if (nivel == 3) {
			rangoMin = -10;
			rangoMax = 10;
		}
	}
	
	
}

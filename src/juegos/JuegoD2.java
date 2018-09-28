package juegos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;

import usuarioYcronometro.Cronometro;

public class JuegoD2 extends Observable implements ActionListener{
	JButton ok;
	JPanel panelPrincipal;
	JRadioButton [] botones;
	static String [] puntos = {"",".",":"};
	static String [] letras = {"d", "p"};
	int filas, columnas, dificultad;
	Random rand;
	Border border;
	Cronometro  cronometro;
	
	final static String explicación="Tendrá que seleccionar los 'd' que tengan dos puntos alrededor.\n"
			+ "Cuando crea que ha acabado, pulse 'continuar'.";
	
	public String getExplicación() {
		return explicación;
	}
	
	public JuegoD2(int nivel) {
		panelPrincipal = new JPanel(new BorderLayout());
		panelPrincipal.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10,10,10,10), BorderFactory.createRaisedBevelBorder()));
		panelPrincipal.setOpaque(false);
		dificultad = nivel;
		definirDificultad();
		ok = new JButton("Comprobar");
		panelPrincipal.add(ok, BorderLayout.SOUTH);
		panelPrincipal.add(crearBotones(), BorderLayout.CENTER);
		panelPrincipal.add(crearExplicacion(), BorderLayout.NORTH);
		
		ok.addActionListener(this);
		
		panelPrincipal.setVisible(true);
		panelPrincipal.setOpaque(false);
	}
	
	public JPanel crearBotones() {
		int kont = 0;
		JPanel panelC = new JPanel();
		panelC.setOpaque(false);
		rand = new Random();
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setOpaque(false);
		botones = new JRadioButton[filas * columnas];
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;//La columna en la que estÃ¡ 
        gbc.gridy = 0;//La fila en la que estÃ¡
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipadx = 20;
        gbc.ipady = 20;
        
        
        for(int i = 0; i < filas; i++) {
        	
        		for(int j = 0; j < columnas; j++) {
        			botones[kont] = new JRadioButton(puntos[rand.nextInt(3)] + letras[rand.nextInt(2)] + puntos[rand.nextInt(3)]);
        			botones[kont].setFont(new Font("Monospaced", Font.BOLD, 30));
        			botones[kont].setActionCommand(botones[kont].getText());
        			panel.add(botones[kont], gbc);
        			gbc.gridx++;
        			kont++;
        		}
        		gbc.gridx = 0;
        		gbc.gridy++;
        	}
        cronometro = new Cronometro(0, 0);
        panelC = cronometro;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 40;
        gbc.gridwidth = columnas;
        	panel.add(panelC, gbc);
        gbc.gridy = 0;
        cronometro.iniciar();
        
        return panel;	
	}
	
	
	public void actionPerformed(ActionEvent e) {
		int kont = 0;
		cronometro.detener();
		String comando = new String();
		ok.setEnabled(false);
		for(kont = 0; kont < columnas*filas; kont++) {
    			comando = botones[kont].getText();
    			if (botones[kont].isSelected()) {
    				if (comando.equals(".d.") || comando.equals(":d") || comando.equals("d:")) {
    					botones[kont].setForeground(Color.green);
    				}
    				else {
    					botones[kont].setForeground(Color.red);
    				}
    				panelPrincipal.revalidate();
    				panelPrincipal.repaint();
    			}
    			else if (!botones[kont].isSelected()) {
    				if (comando.equals(".d.") || comando.equals(":d") || comando.equals("d:")) {
    				botones[kont].setForeground(Color.red);
    				}
    			}
    		}
		kont++;
		this.setChanged();
		this.notifyObservers();
	}
	
	public JPanel getPanelPrincipal() {
		return panelPrincipal;
	}

	public void setPanelPrincipal(JPanel panelPrincipal) {
		this.panelPrincipal = panelPrincipal;
	}

	public JLabel crearExplicacion() {
		JLabel label = new JLabel();
		label.setFont(new Font("Monospaced", Font.BOLD, 30));
		label.setText("Marca las letras \"d\" que tengan dos puntos alrededor:"
				+ "\":d\" \"d:\" \".d.\"");
		return label;
	}
	
	public void definirDificultad() {
		if (dificultad == 1) {
			columnas = 5;
			filas = 2;
		}
		else if (dificultad == 2) {
			columnas =  5;
			filas = 5;
		}
		else if (dificultad == 3) {
			columnas = 7;
			filas = 7;
		}
	}
}
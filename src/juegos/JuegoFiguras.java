package juegos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import usuarioYcronometro.Cronometro;


public class JuegoFiguras extends Observable implements ActionListener{
	
	ImageIcon [] imagenes;
	JRadioButton [] botones;
	JPanel panelPrincipal;
	JButton ok;
	Cronometro cronometro;
	Random rand;
	int dificultad, columnas, filas;
	int numImagen;
	
	/*
	 * 	dificultad: 1 = facil
	 * 				2 = medio
	 * 				3 = dificil
	 * 	
	 * 	file1, file2, file3:  Un File() con la ruta de la imagen que se quiera usar
	 *  esto nos da muchas opciones de cara al futuro, para implementar diferentes juego
	 */
	final static String explicaciÛn="Tendr· que seleccionar las figuras que sean iguales a la indicada en la parte superior.\n"
			+ "Cuando crea que ha acabado, pulse 'continuar'.";
	
	public String getExplicaciÛn() {
		return explicaciÛn;
	}
	
	public JuegoFiguras(int dificultad, File file1, File file2, File file3) {
		panelPrincipal = new JPanel(new BorderLayout());
		panelPrincipal.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10,10,10,10), BorderFactory.createRaisedBevelBorder()));
		panelPrincipal.setOpaque(false);
		rand = new Random();
		cargarImagenes(file1, file2, file3);
		this.dificultad = dificultad;
		definirDificultad();
		numImagen = rand.nextInt(3);
		
		ok = new JButton("Comprobar");
		panelPrincipal.add(ok, BorderLayout.SOUTH);
		panelPrincipal.add(panelCentral(), BorderLayout.CENTER);
		panelPrincipal.add(panelExplicacion(), BorderLayout.NORTH);
		
		
		ok.addActionListener(this);
		
		panelPrincipal.setOpaque(false);
		panelPrincipal.setVisible(true);
	}
	
	
	public JPanel getPanelPrincipal() {
		return panelPrincipal;
	}


	public void setPanelPrincipal(JPanel panelPrincipal) {
		this.panelPrincipal = panelPrincipal;
	}


	public JPanel panelExplicacion() {
		JPanel panel = new JPanel(new GridBagLayout());
		JLabel label1 = new JLabel();
		JLabel label2 = new JLabel(imagenes[numImagen]);

		label1.setFont(new Font("Monospaced", Font.BOLD, 30));
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;//La columna en la que est√° 
        gbc.gridy = 0;//La fila en la que est√°
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipadx = 20;
        gbc.ipady = 20;
        
        label1.setText("Marca las figuras que sean iguales a esta: ");
        
        panel.add(label1, gbc);
        gbc.gridx++;
        panel.add(label2, gbc);
		
        panel.setOpaque(false);
		return panel;
	}
	
	public JPanel panelCentral() {
		int kont = 0;
		JPanel panel = new JPanel(new GridBagLayout());
		JPanel panelC;
		cronometro = new Cronometro(0, 0);
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;//La columna en la que est√° 
        gbc.gridy = 0;//La fila en la que est√°
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipadx = 20;
        gbc.ipady = 20;
        botones = new JRadioButton[filas*columnas];
        
        
        for(int i = 0; i < filas; i++) {
    		
    			for(int j = 0; j < columnas; j++) {//se crean los botones
    				botones[kont] = new JRadioButton(imagenes[rand.nextInt(3)]);
    				botones[kont].setHorizontalAlignment(JRadioButton.CENTER);
    				panel.add(botones[kont], gbc);
    				botones[kont].addActionListener(new ActionListener() {
    		            public void actionPerformed(ActionEvent e) {
    		            	
    		            	for(int i = 0; i < filas*columnas; i++) {
    		            		
    		        			if (botones[i].isSelected()) {
    		        				botones[i].setBorder (BorderFactory.createRaisedBevelBorder ());
    		        				botones[i].setBorderPainted (true);
    		        			}
    		        			if (!botones[i].isSelected()) {
    		        				botones[i].setBorder (BorderFactory.createRaisedBevelBorder ());
    		        				botones[i].setBorderPainted (false);
    		        			}
    		        		}
    		            }
    		        });
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
    
    panel.setOpaque(false);
		
	return panel;
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
	
	
	private void cargarImagenes(File file1, File file2, File file3) {
		
		imagenes = new ImageIcon[20];
		try {
	      	  imagenes[0] = new ImageIcon(ImageIO.read(file1));
	          imagenes[1] = new ImageIcon(ImageIO.read(file2));
	          imagenes[2] = new ImageIcon(ImageIO.read(file3));
	      } catch (IOException ex) {
	          ex.printStackTrace();
	      }
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int kont = 0;
		cronometro.detener();
		ok.setEnabled(false);
		for(kont = 0; kont < columnas*filas; kont++) {
    			
    			if (botones[kont].isSelected()) {//los seleccionados
    				if (botones[kont].getIcon().equals(imagenes[numImagen])) {//los que estan bien
    					botones[kont].setBackground(Color.green);
    				}
    				else {//los que estan mal
    					botones[kont].setBackground(Color.red);
    				}
    			}
    			else if (!botones[kont].isSelected()) {//los que no estan seleccionados
    				if (botones[kont].getIcon().equals(imagenes[numImagen])) {//los que deberian de estar seleccionados
    					botones[kont].setBorder (BorderFactory.createRaisedBevelBorder ());
    					botones[kont].setBorderPainted (true);
    					botones[kont].setBackground(Color.red);
    				}
    			}
    			botones[kont].setEnabled(false);

				panelPrincipal.revalidate();
				panelPrincipal.repaint();
    		}
		kont++;
		this.setChanged();
		this.notifyObservers();
	}
}

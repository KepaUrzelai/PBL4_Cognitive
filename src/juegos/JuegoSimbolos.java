package juegos;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import usuarioYcronometro.Cronometro;

public class JuegoSimbolos extends Observable implements ActionListener{
	
	JPanel panel;
	List<ImageIcon> simbolos;
	JLabel texto;
	JButton bSi, bNo;
	Cronometro crono;
	Random random;
	
	final static String explicación="Tendrá que indicar si las figuras que se encuentran a la izquierda de la imagen (en la zona grisacea),\n"
			+ "se encuentran entre las demas.";
	
	public String getExplicación() {
		return explicación;
	}
	
	public JuegoSimbolos() {
		panel=new JPanel(new GridLayout(3,1,10,0));
		panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10,10,10,10), BorderFactory.createRaisedBevelBorder()));
		panel.setOpaque(false);
		simbolos=new ArrayList<>();
		inicializar();
		random=new Random();
		crono = new Cronometro(1,120);
		crono.setOpaque(false);
		crono.iniciar();
		texto=new JLabel(simbolos.get(0));
		panel.add(crono);
		panel.add(texto);
		panel.add(crearPanelBotones());
	}
	
	public JPanel getPanelPrincipal() {
		return panel;
	}
	
	private Component crearPanelBotones() {
		JPanel panel=new JPanel(new GridLayout(1,2,20,0));
		panel.setBorder(BorderFactory.createEmptyBorder(0,100,50,100));
		panel.setOpaque(false);
		bSi=new JButton("Si");
		bSi.setFont(new Font("Century Gothic",Font.BOLD,20));
		bNo=new JButton("No");
		bNo.setFont(new Font("Century Gothic",Font.BOLD,20));
		bSi.addActionListener(this);
		bNo.addActionListener(this);
		panel.add(bSi);
		panel.add(bNo);
		
		return panel;
	}

	private void inicializar() {
		simbolos.add(new ImageIcon("imagenes/simbolos1.PNG"));
		simbolos.add(new ImageIcon("imagenes/simbolos2.PNG"));
		simbolos.add(new ImageIcon("imagenes/simbolos3.PNG"));
		simbolos.add(new ImageIcon("imagenes/simbolos4.PNG"));
		simbolos.add(new ImageIcon("imagenes/simbolos5.PNG"));
		simbolos.add(new ImageIcon("imagenes/simbolos6.PNG"));
		simbolos.add(new ImageIcon("imagenes/simbolos7.PNG"));
		simbolos.add(new ImageIcon("imagenes/simbolos8.PNG"));
		simbolos.add(new ImageIcon("imagenes/simbolos9.PNG"));
		simbolos.add(new ImageIcon("imagenes/simbolos10.PNG"));
		simbolos.add(new ImageIcon("imagenes/simbolos11.PNG"));
		simbolos.add(new ImageIcon("imagenes/simbolos12.PNG"));
		simbolos.add(new ImageIcon("imagenes/simbolos13.PNG"));
		simbolos.add(new ImageIcon("imagenes/simbolos14.PNG"));
		simbolos.add(new ImageIcon("imagenes/simbolos15.PNG"));
		simbolos.add(new ImageIcon("imagenes/simbolos16.PNG"));
		simbolos.add(new ImageIcon("imagenes/simbolos17.PNG"));
		simbolos.add(new ImageIcon("imagenes/simbolos18.PNG"));
		simbolos.add(new ImageIcon("imagenes/simbolos19.PNG"));
		Collections.shuffle(simbolos);
	}



	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(crono.getSeconds()!=0) {//se van poniendo imagenes, de uno en uno
			texto.setIcon(simbolos.get(random.nextInt(20)));
		}else {//se ha acabado el tiempo.
			texto.setIcon(null);
			bSi.setEnabled(false);
			bNo.setEnabled(false);
			this.setChanged();
			this.notifyObservers();
		}
		
		
	}

	

}

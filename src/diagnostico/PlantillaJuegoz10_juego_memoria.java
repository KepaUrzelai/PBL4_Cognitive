package diagnostico;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import usuarioYcronometro.Cronometro;

public class PlantillaJuegoz10_juego_memoria extends JPanel implements ActionListener{

	Image imagen;
	ImageIcon fondo;
	Color color;
	String campo, titulo;
	int i, puntuacion, kont;//i se utiliza a la hora de poner el enunciado, puntuacion el puntuaje total, y kont para saber si el ejericio a terminado.
	Cronometro cronometro;
	JLabel enunciado;
	JPanel panelBotones;
	JPanel panelEnunciado;
	JButton botonComenzar;
	String[] enunciados = {"Contar de 1 a 20", "Días de la semana", "Meses del año", "Finalizado"};

	
	public PlantillaJuegoz10_juego_memoria() {
		super(new BorderLayout());
		titulo = "Memoria";
		fondo = new ImageIcon("imagenes/Fondo3d.png");
		campo = "Juego : ";
		color = new Color(0,0,150);//azul
	    imagen = fondo.getImage();
	    i = 0;
		puntuacion = 0;
		kont= 0;
		crearPanelVentana();
	}

	private void crearPanelVentana() {
		
		this.add(crearPanelCentral(), BorderLayout.CENTER);
	
		
	}
	
	private Component crearPanelCentral() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(crearPanelEnunciado(), BorderLayout.CENTER);
		panel.add(crearPanelCrono(), BorderLayout.SOUTH);
		panel.setOpaque(false);
		return panel;
	}
	
	private Component crearPanelEnunciado() {
		panelEnunciado = new JPanel(new GridLayout(2,1,10,10));
		enunciado = new JLabel(enunciados[i]);
		enunciado.setFont(new Font("Monospaced", Font.BOLD, 70));
		enunciado.setForeground(this.color);
		enunciado.setBorder(BorderFactory.createEmptyBorder(10,300,10,60));
		
		panelEnunciado.add(enunciado);
		panelEnunciado.add(crearPanelBotones());
		panelEnunciado.setOpaque(false);
		
		return panelEnunciado;
	}

	private Component crearPanelBotones() {
		panelBotones = new JPanel(new GridLayout(1,2,10,10));
		botonComenzar = new JButton("Comenzar");
		botonComenzar.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		botonComenzar.setFont(new Font("Monospaced", Font.BOLD, 60));
		botonComenzar.setForeground(this.color);
		botonComenzar.setActionCommand("Comenzar");
		botonComenzar.addActionListener(this);
		panelBotones.add(botonComenzar);
		panelBotones.setBorder(BorderFactory.createEmptyBorder(10,40,10,40));
		panelBotones.setOpaque(false);
		return panelBotones;
	}

	private JButton crearBoton(String string) {
		JButton boton = new JButton(string);
		boton.setFont(new Font("Monospaced", Font.BOLD, 60));
		boton.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		boton.setForeground(this.color);
		boton.setActionCommand(string);
		boton.addActionListener(this);
		return boton;
	}

	private Component crearPanelCrono() {
		cronometro = new Cronometro(0,1);
		cronometro.setOpaque(false);
		cronometro.setBorder(BorderFactory.createEmptyBorder(40,0,10,0));
		return cronometro;
	}

	public void setImagen(Image nuevaImagen) {
		imagen = nuevaImagen;
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D gr = (Graphics2D)g;
		if (imagen != null) {
			gr.drawImage(imagen, 0, 0, getWidth(), getHeight(),this);
		} else {
			setOpaque(true);
		}	
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("Comenzar")) {
			cronometro.iniciar();
			panelBotones.remove(botonComenzar);
			panelBotones.add(crearBoton("Correcto"));
			panelBotones.add(crearBoton("Desistir"));
			panelBotones.repaint();
			panelBotones.revalidate();
			
		}
		
		else if(e.getActionCommand().equals("Correcto")) {
			kont++;
			this.puntuacion = calcularPuntuacion(cronometro.getSeconds());
			cronometro.detener();
			if(kont!=3) {
			enunciado.setText(enunciados[++i]);
			panelBotones.removeAll();
			panelBotones.add(botonComenzar);
			panelBotones.repaint();
			panelBotones.revalidate();			
			}	
		}else if(e.getActionCommand().equals("Desistir")) {
			kont++;
			cronometro.detener();
			if(kont!=3) {
			enunciado.setText(enunciados[++i]);
			panelBotones.removeAll();
			panelBotones.add(botonComenzar);
			panelBotones.repaint();
			panelBotones.revalidate();
			}		
		}
		
		if (kont==3){ 
			cronometro.detener();
			panelBotones.removeAll();
			enunciado.setText(enunciados[++i]);
			panelBotones.repaint();
			panelBotones.revalidate();
		}
		
		
	}
	
	public int getPuntuacion() {
		return puntuacion;
	}

	public int getKont() {
		return kont;
	}

	public int calcularPuntuacion(int segundos) {
			if((segundos > 0) && (segundos < 9)) {
				puntuacion = puntuacion + 2;
			}else if(segundos >= 9) {
				puntuacion = puntuacion + 1; 
			}
		return puntuacion;
	}
	
	public ImageIcon getFondo() {
		return fondo;
	}

	public Color getColor() {
		return color;
	}
	
	public String getCampo() {
		return campo;
	}

	public String getTitulo() {
		return titulo;
	}
	
}

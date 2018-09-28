package diagnostico;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
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
import javax.swing.JTextField;

import usuarioYcronometro.Cronometro;

public class PlantillaJuegoz14_juego_razmat extends JPanel implements ActionListener{

	Image imagen;
	ImageIcon fondo;
	Color color;
	String campo, titulo;
	JLabel operacion, resultado;
	int numEj, result, i, numAciertos, puntuacion, puntTotal;//variable i define las unidades que tiene la respuesta
	String[] strResultado;
	String[] ejercicios;
	JTextField texto;
	Cronometro cronometro;
	boolean finalizado;
	public PlantillaJuegoz14_juego_razmat() {
		super(new BorderLayout());
		finalizado=false;
		titulo = "Razonamiento Matemático (Calculos)";
		fondo = new ImageIcon("imagenes/Fondo1d.png");
		campo = "Juego : ";
		color = color.DARK_GRAY;
	    imagen = fondo.getImage();
	    strResultado = new String[3];
		ejercicios = new String[8];
		numAciertos = 0;
		result = 0;
		numEj = 1;
		i = 0;
		puntuacion=0;
		puntTotal=0;
		inicializarEjercicios();
		crearPanelVentana();
	}
	
	private void inicializarEjercicios() {
		ejercicios[0] = "4 + 5";
		ejercicios[1] = "9 - 2";
		ejercicios[2] = "3 x 6";
		ejercicios[3] = "7 x 8";
		ejercicios[4] = "18 : 3";
		ejercicios[5] = "28 : 4";
		ejercicios[6] = "17 + 14";
		ejercicios[7] = "43 - 38";
	}
	
	private void crearPanelVentana() {
	
		this.add(crearPanelCentral(), BorderLayout.CENTER);
		
	}
	

	private Component crearPanelCentral() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(crearPanelOperación(), BorderLayout.NORTH);
		panel.add(crearPanelNumerico(), BorderLayout.CENTER);
		panel.add(crearPanelCrono(), BorderLayout.SOUTH);
		
		panel.setOpaque(false);
		return panel;
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	private Component crearPanelOperación() {
		JPanel panel = new JPanel( new FlowLayout());
		JLabel igual = new JLabel(" = ");
		igual.setFont(new Font("Monospaced", Font.BOLD, 70));
		igual.setForeground(this.color);
		
		operacion = new JLabel(ejercicios[0]);
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


	private Component crearPanelCrono() {
		cronometro = new Cronometro(0,0);
		cronometro.iniciar();
		cronometro.setOpaque(false);
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
		if(!e.getActionCommand().equals("=")) {
			switch(e.getActionCommand()) { //El switch case llena el string del resultado en el orden metido por el user
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
			
			if(this.cotejarResultado(result, numEj)) {
				cronometro.getSeconds();
				puntTotal = puntTotal + calcularPuntuacion((int)cronometro.getSeconds());
				cronometro.iniciar();
				if (numEj==8) {
					finalizado=true;
					cronometro.detener();
					operacion.setText("Finalizado");
					texto.setVisible(false);
				}
				else {
				operacion.setText(ejercicios[numEj++]);
				}
			}else {
				System.out.println("Okerra"); //Comprobar
			}
			strResultado = null; i = 0;
			strResultado = new String[3];
			result = 0;
			texto.setText("   ");
		}
	}
	
	public int calcularPuntuacion(int segundos) {
		System.out.println(segundos);
		if((segundos > 0) && (segundos < 5)) {
			puntuacion = 3;
		}else if((segundos >= 5) && (segundos < 10)) {
			puntuacion = 2; 
		}else if((segundos >= 10) && (segundos < 30)) {
			puntuacion = 1;
		}else {
			puntuacion = 0;
		}
		return puntuacion;
	}
	
	private boolean cotejarResultado(int resultado, int numEj) {
		switch(numEj) {
		case 1: if (resultado == 9) return true;
		break;
		case 2: if (resultado == 7) return true;
		break;
		case 3: if (resultado == 18) return true;
		break;
		case 4: if (resultado == 56) return true;
		break;
		case 5: if (resultado == 6) return true;	
		break;
		case 6: if (resultado == 7) return true;	
		break;
		case 7: if (resultado == 31) return true;	
		break;
		case 8: if (resultado == 5) return true;	
		break;
		}
		return false;
	}

	public int getPuntTotal() {
		return puntTotal;
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

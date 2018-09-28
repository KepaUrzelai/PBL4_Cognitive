package diagnostico;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import loginYmenu.VistaMenuPrincipal;
import usuarioYcronometro.Fecha;
import usuarioYcronometro.Puntuaciones;
import usuarioYcronometro.PuntuacionesAlto;
import usuarioYcronometro.PuntuacionesBaja;
import usuarioYcronometro.PuntuacionesMedio;
import usuarioYcronometro.Usuario;

public class PlantillaJuegos_SEMANAL extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
    // el fondo, t�tulos, panel central y color se van cambiando
	ImageIcon fondo;		
	Color color;
	String campo, titulo;
	JPanel panel;
	JButton boton; // el bot�n siguiente de abajo a la derecha
	int kont; //para ir avanzando por el diagn�stico
	
	//Para recoger todos los datos que nos llevar�n a crear el perfil del paciente, definimos las clases y variables aqu�
	PlantillaJuegos5_juego_digitos datos3;
	PlantillaJuegos6_expli_animales datos4;
	PlantillaJuegos8_juego_abstraccion datos6;
	PlantillaJuegoz10_juego_memoria datos8;
	PlantillaJuegoz12_juego_agilidad datos10;
	PlantillaJuegoz14_juego_razmat datos12;
	PlantillaJuegoz15_juego_razmat2 datos13;
	Usuario usuario;
	String nombreUsuario;
	Fecha fecha;
	int escolaridad;
	int digitosD, digitosI;
	int animalesMinuto;
	int abstraccion;
	int memoria;
	int agilidad;
	int mat1, mat2;
	int dia, mes, ano;
	Calendar c;
	
	public PlantillaJuegos_SEMANAL(String nombreUsuario) {
		super("Bienvenido");
		this.nombreUsuario=nombreUsuario;
		cargarUsuario();
		kont=2;
		titulo = "Diagn�stico";
		fondo = new ImageIcon("imagenes/Fondo4d.png");
		campo = "Explicaci�n : ";
		color = new Color(230,150,0);//amarillo oscuro
		c= new GregorianCalendar();
		
		dia = c.get(Calendar.DATE);
		mes = c.get(Calendar.MONTH);
		ano = c.get(Calendar.YEAR);
		
		this.setSize(1280, 720);
		this.setContentPane(crearPanelVentana(crearPanelCentral()));
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void cargarUsuario() {
		ObjectInputStream in = null;
		
		try {
			in = new ObjectInputStream ( new FileInputStream("Usuarios/"+nombreUsuario+"/datos.txt"));
			try {
				usuario=(Usuario) in.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (in!=null){
				try { in.close(); } catch (IOException e) {}
			}
		}
	}
		

	private Container crearPanelVentana(Component panelCentral) {
		panel = new MiPanel(new BorderLayout(), this.fondo.getImage());
		panel.add(crearPanelSuperior(), BorderLayout.NORTH);
		panel.add(panelCentral, BorderLayout.CENTER);
		panel.add(crearPanelInferior(), BorderLayout.SOUTH);
		return panel;
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
		String texto = "<html><body>Para completar el diagn�stico, vamos a plantearte una serie de ejercicios con los que determinaremos tus aptitudes y "
				+ "dise�aremos una ruta personalizada a seguir durante tu rehabilitaci�n. </body></html>";
		
		JLabel label = new JLabel(texto);
		label.setFont(new Font("Monospaced",Font.CENTER_BASELINE,35));
		label.setForeground(Color.DARK_GRAY);
		label.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),BorderFactory.createEmptyBorder(0,40,0,10)));
		panel.add(label);
		panel.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
		panel.setOpaque(false);
		return panel;
	}

	private Component crearPanelInferior() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10,10,10,10),BorderFactory.createLoweredBevelBorder()));
		JLabel label= new JLabel(new ImageIcon("imagenes/siguiente.png"));
		JLabel label2 = new JLabel(new ImageIcon("imagenes/logo1.png"));
		boton= new JButton("");
		boton.setBorder(BorderFactory.createRaisedBevelBorder());
		boton.setIcon(fondo);
		boton.add(label);
		boton.setOpaque(false);
		boton.addActionListener(this);
		panel.add(boton, BorderLayout.EAST);
		panel.add(label2, BorderLayout.WEST);
		panel.setOpaque(false);
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
			
		switch(kont) {//cada vez que se la da al bot�n de siguiente el kont se va sumando y as� va pasando de panel en panel
		
		case 2:
			PlantillaJuegos4_expli_digitos datos2= new PlantillaJuegos4_expli_digitos();	
			this.fondo=datos2.getFondo();
			this.color=datos2.getColor();
			this.campo=datos2.getCampo();
			this.titulo=datos2.getTitulo();
			this.setContentPane(crearPanelVentana(datos2));		
			this.repaint();
			this.revalidate();
			kont++;
			break;
		case 3:
			datos3= new PlantillaJuegos5_juego_digitos();	
			this.fondo=datos3.getFondo();
			this.color=datos3.getColor();
			this.campo=datos3.getCampo();
			this.titulo=datos3.getTitulo();
			this.setContentPane(crearPanelVentana(datos3));		
			this.repaint();
			this.revalidate();
			kont++;
			break;
		case 4:
			try{
				digitosD=Integer.parseInt(datos3.gettResultadoDirectos().getText());
				digitosI=Integer.parseInt(datos3.gettResultadoInversos().getText());
			}catch (NumberFormatException e){
				JOptionPane.showConfirmDialog(this, "Introduzca solo numeros por favor", "Error",
						JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
				break;
			}
			datos4= new PlantillaJuegos6_expli_animales();	
			this.fondo=datos4.getFondo();
			this.color=datos4.getColor();
			this.campo=datos4.getCampo();
			this.titulo=datos4.getTitulo();
			this.setContentPane(crearPanelVentana(datos4));		
			this.repaint();
			this.revalidate();
			kont++;
			break;
		case 5:
			try{
				animalesMinuto=Integer.parseInt(datos4.gettResultado().getText());
			}catch (NumberFormatException e){
				JOptionPane.showConfirmDialog(this, "Introduzca solo numeros por favor", "Error",
						JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
				break;
			}
			PlantillaJuegos7_expli_abstraccion datos5= new PlantillaJuegos7_expli_abstraccion();	
			this.fondo=datos5.getFondo();
			this.color=datos5.getColor();
			this.campo=datos5.getCampo();
			this.titulo=datos5.getTitulo();
			this.setContentPane(crearPanelVentana(datos5));		
			this.repaint();
			this.revalidate();
			kont++;
			break;
		case 6:
			datos6= new PlantillaJuegos8_juego_abstraccion();	
			this.fondo=datos6.getFondo();
			this.color=datos6.getColor();
			this.campo=datos6.getCampo();
			this.titulo=datos6.getTitulo();
			this.setContentPane(crearPanelVentana(datos6));		
			this.repaint();
			this.revalidate();
			kont++;
			break;
		case 7:	
			int kont2=0;//Para saber si todos los buttongroup estan seleccionados
			if((datos6.getbZero0().isSelected())||(datos6.getbUno0().isSelected())||(datos6.getbDos0().isSelected())) {
				kont2++;
			}
			if((datos6.getbZero1().isSelected())||(datos6.getbUno1().isSelected())||(datos6.getbDos1().isSelected())) {
				kont2++;
			}
			if((datos6.getbZero2().isSelected())||(datos6.getbUno2().isSelected())||(datos6.getbDos2().isSelected())) {
				kont2++;
			}
			if((datos6.getbZero3().isSelected())||(datos6.getbUno3().isSelected())||(datos6.getbDos3().isSelected())) {
				kont2++;
			}
			if(kont2<=3) {
				
				JOptionPane.showConfirmDialog(this, "Ha dejado alguna opci�n sin seleccionar", "Error",
						JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
				break;
				
			}
			datos6.calcularPuntuacion();
			abstraccion=datos6.getPuntuacion();
			PlantillaJuegos9_expli_memoria datos7= new PlantillaJuegos9_expli_memoria();	
			this.fondo=datos7.getFondo();
			this.color=datos7.getColor();
			this.campo=datos7.getCampo();
			this.titulo=datos7.getTitulo();
			this.setContentPane(crearPanelVentana(datos7));		
			this.repaint();
			this.revalidate();
			kont++;
			break;
		case 8:
			datos8= new PlantillaJuegoz10_juego_memoria();	
			this.fondo=datos8.getFondo();
			this.color=datos8.getColor();
			this.campo=datos8.getCampo();
			this.titulo=datos8.getTitulo();
			this.setContentPane(crearPanelVentana(datos8));		
			this.repaint();
			this.revalidate();
			kont++;
			break;
		case 9:
			if(datos8.getKont()!=3) {//hay un contador definido para saber si el ejercicio esta acabado
				
				JOptionPane.showConfirmDialog(this, "Termina el ejercicio por favor", "Error",
						JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
				break;	
			}
			memoria=datos8.getPuntuacion();
			PlantillaJuegoz11_expli_agilidad datos9= new PlantillaJuegoz11_expli_agilidad();	
			this.fondo=datos9.getFondo();
			this.color=datos9.getColor();
			this.campo=datos9.getCampo();
			this.titulo=datos9.getTitulo();
			this.setContentPane(crearPanelVentana(datos9));		
			this.repaint();
			this.revalidate();
			kont++;
			break;
		case 10:
			datos10= new PlantillaJuegoz12_juego_agilidad();	
			this.fondo=datos10.getFondo();
			this.color=datos10.getColor();
			this.campo=datos10.getCampo();
			this.titulo=datos10.getTitulo();
			this.setContentPane(crearPanelVentana(datos10));		
			this.repaint();
			this.revalidate();
			kont++;
			break;
		case 11:
			if(!datos10.isFinalizado()) {//un booleano definido para saber si el ejercicio esta terminado o no
				JOptionPane.showConfirmDialog(this, "Termina el ejercicio por favor", "Error",
						JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
				break;	
			}
			agilidad=datos10.getPuntuacion();
			PlantillaJuegoz13_expli_razmat datos11= new PlantillaJuegoz13_expli_razmat();	
			this.fondo=datos11.getFondo();
			this.color=datos11.getColor();
			this.campo=datos11.getCampo();
			this.titulo=datos11.getTitulo();
			this.setContentPane(crearPanelVentana(datos11));	
			this.repaint();
			this.revalidate();
			kont++;
			break;
		case 12:
			datos12= new PlantillaJuegoz14_juego_razmat();	
			this.fondo=datos12.getFondo();
			this.color=datos12.getColor();
			this.campo=datos12.getCampo();
			this.titulo=datos12.getTitulo();
			this.setContentPane(crearPanelVentana(datos12));		
			this.repaint();
			this.revalidate();
			kont++;
			break;
		
		case 13:
			if(!datos12.isFinalizado()) {
				JOptionPane.showConfirmDialog(this, "Termina el ejercicio por favor", "Error",
						JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
				break;	
			}
			mat1=datos12.getPuntTotal();
			datos13= new PlantillaJuegoz15_juego_razmat2();	
			this.fondo=datos13.getFondo();
			this.color=datos13.getColor();
			this.campo=datos13.getCampo();
			this.titulo=datos13.getTitulo();
			this.setContentPane(crearPanelVentana(datos13));		
			this.repaint();
			this.revalidate();
			kont++;
			break;
			
		case 14:
			if(!datos13.isFinalizado()) {
				JOptionPane.showConfirmDialog(this, "Termina el ejercicio por favor", "Error",
						JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
				break;	
			}
			mat2=datos13.getPuntTotal();
			if(usuario.getEscolaridad()==0) {
				usuario.setPuntuaciones(new PuntuacionesBaja(memoria, agilidad, digitosD, digitosI, mat1, mat2, animalesMinuto, abstraccion ));
				}else if(usuario.getEscolaridad()==1) {
					usuario.setPuntuaciones(new PuntuacionesMedio(memoria, agilidad, digitosD, digitosI, mat1, mat2, animalesMinuto, abstraccion ));
				}else usuario.setPuntuaciones(new PuntuacionesAlto(memoria, agilidad, digitosD, digitosI, mat1, mat2, animalesMinuto, abstraccion ));
			usuario.setFechaDiag(dia, mes, ano);
			usuario.ficheroPuntuacion();//se escriben las puntuaciones en el fichero
			PlantillaJuegoz16_finalizado datos14= new PlantillaJuegoz16_finalizado();
			this.fondo=datos14.getFondo();
			this.color=datos14.getColor();
			this.campo=datos14.getCampo();
			this.titulo=datos14.getTitulo();
			this.setContentPane(crearPanelVentana(datos14));		
			this.repaint();
			this.revalidate();
			kont++;
			break;
		
		case 15:
			this.dispose();
			VistaMenuPrincipal vista= new VistaMenuPrincipal(usuario.getNombre());
			
			break;
	}		
}
}

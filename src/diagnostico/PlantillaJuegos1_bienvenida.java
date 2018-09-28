package diagnostico;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;

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

import java.util.Calendar;
import java.util.GregorianCalendar;

public class PlantillaJuegos1_bienvenida extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
 // el fondo, títulos, panel central y color se van cambiando
	ImageIcon fondo;		
	Color color;
	String campo, titulo;
	JPanel panel;
	JButton boton; // el botón siguiente de abajo a la derecha
	int kont; //para ir avanzando por el diagnóstico
	
	//Para recoger todos los datos que nos llevaran a crear el perfil del paciente
	PlantillaJuegos2_datos datos;
	PlantillaJuegos5_juego_digitos datos3;
	PlantillaJuegos6_expli_animales datos4;
	PlantillaJuegos8_juego_abstraccion datos6;
	PlantillaJuegoz10_juego_memoria datos8;
	PlantillaJuegoz12_juego_agilidad datos10;
	PlantillaJuegoz14_juego_razmat datos12;
	PlantillaJuegoz15_juego_razmat2 datos13;
	Usuario usuario;
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
	
	public PlantillaJuegos1_bienvenida() {
		super("Bienvenido");
		kont=0;
		titulo = "¿Quienes somos?";
		fondo = new ImageIcon("imagenes/Fondo4d.png");
		campo = "Bienvenida : ";
		color = new Color(230,150,0);//amarillo oscuro
		datos= new PlantillaJuegos2_datos();
		
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
		String texto = "<html><body>Bienvenido a COGNITIVE, esta aplicación será el suplemento que utilizarás para llevar a cabo tu rehabilitación cognitiva."
				+ " <br/>Aquí encontrarás diferentes actividades diseñadas para estimular aquellos campos que, mediante un diagnóstico previo, veamos más necesarias a estimular."
				+ " <br/>A continuación, rellena el formulario, para poder comenzar con el diagnóstico. </body></html>" ;
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
	
	private int comprobarContraYFecha() {
		System.out.println(String.valueOf(datos.getContra1().getPassword()) + " " + String.valueOf(datos.getContra2().getPassword()));
		if(!String.valueOf(datos.getContra1().getPassword()).equals(String.valueOf(datos.getContra2().getPassword()))) {
			System.out.println(String.valueOf(datos.getContra1().getPassword()) +  String.valueOf(datos.getContra2().getPassword()));
			return 1;
		}
		else if(!fecha.comprobar()) {
			return 2;
		}
		else return 0;
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
			
		switch(kont) {
		case 0: 
				
			this.fondo=datos.getFondo();
			this.color=datos.getColor();
			this.campo=datos.getCampo();
			this.titulo=datos.getTitulo();
			this.setContentPane(crearPanelVentana(datos));		
			this.repaint();
			this.revalidate();
			kont++;
			break;
		case 1:
			try{//comprobamos si los datos estan bien metidos
			escolaridad=datos.calcularescolaridad();
			fecha=new Fecha(Integer.parseInt(datos.gettDia().getText()), Integer.parseInt(datos.gettMes().getText()), Integer.parseInt(datos.gettAno().getText()));
			usuario=new Usuario(datos.gettNombre().getText()+ " "+ datos.gettApellido1().getText() +" "+datos.gettApellido2().getText(),datos.getContra1().getText(),datos.gettPoblacion().getText(),fecha,escolaridad  );
			}catch (NumberFormatException e){
				JOptionPane.showConfirmDialog(this, "Introduzca bien los datos por favor", "Error",
						JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
				break;
			}catch (InputMismatchException e){
				JOptionPane.showConfirmDialog(this, "Introduzca bien los datos por favor", "Error",
						JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
				break;
			}
			if(comprobarContraYFecha()==1) {
				JOptionPane.showConfirmDialog(this, "Las contraseñas no coinciden", "Error",
						JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
				break;
			}
			if(comprobarContraYFecha()==2) {//comprobamos si las contraseñas coinciden. Y si la fecha es correcta
				JOptionPane.showConfirmDialog(this, "La fecha es incorrecta", "Error",
						JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
				break;
			}
			PlantillaJuegos3_expli_diag datos1= new PlantillaJuegos3_expli_diag();	
			this.fondo=datos1.getFondo();
			this.color=datos1.getColor();
			this.campo=datos1.getCampo();
			this.titulo=datos1.getTitulo();
			this.setContentPane(crearPanelVentana(datos1));		
			this.repaint();
			this.revalidate();
			kont++;
			break;
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
				
				JOptionPane.showConfirmDialog(this, "Ha dejado alguna opción sin seleccionar", "Error",
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
			if(datos8.getKont()!=3) {//hay un contador definido para saber si el ejercicio esta acabado o no
				
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
			if(!datos10.isFinalizado()) {
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
			if(escolaridad==0) {
			usuario.setPuntuaciones(new PuntuacionesBaja(memoria, agilidad, digitosD, digitosI, mat1, mat2, animalesMinuto, abstraccion ));
			}else if(escolaridad==1) {
				usuario.setPuntuaciones(new PuntuacionesMedio(memoria, agilidad, digitosD, digitosI, mat1, mat2, animalesMinuto, abstraccion ));
			}else usuario.setPuntuaciones(new PuntuacionesAlto(memoria, agilidad, digitosD, digitosI, mat1, mat2, animalesMinuto, abstraccion ));
			usuario.setFechaDiag(dia, mes, ano);
			usuario.setIDdoctor(2);
			usuario.ficheroPuntuacion();//se escriben las puntuaciones en un fichero
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

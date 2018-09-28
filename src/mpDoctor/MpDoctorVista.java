package mpDoctor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MpDoctorVista extends JFrame implements Observer{
	private static final long serialVersionUID = 1L;
	MpDoctorModelo modelo;
	MpDoctorControlador controlador;
	AbstractAction opcPaciente, opcJuegos, opcSalir;
	JTextField txtBusqueda;
	JButton btnBusqueda;
	JScrollPane panelLista;
	JList<JButton> listUsuarios;
	UserRenderer renderer;
	JPanel panelUsuario;
	JPanel panelJuego;
	JPanel panelAten, panelMem, panelAgil, panelMat, panelVerb;
	
	public MpDoctorVista(MpDoctorModelo modelo, MpDoctorControlador controlador, UserRenderer renderer) {
		super("Menu Doctor");
		this.modelo = modelo;
		this.controlador = controlador;
		this.renderer = renderer;
		this.setLocation(300, 20);
		this.setSize(750, 700);
		
		controlador.setVista(this);
		this.crearAcciones();
		this.getContentPane().add(crearToolBar(), BorderLayout.NORTH);
		this.getContentPane().add(crearPanelUsuario(), BorderLayout.CENTER);
		this.setVisible(true);
	}
	

	public JToolBar crearToolBar() {
		JToolBar barra = new JToolBar();
		barra.setBorder(BorderFactory.createRaisedBevelBorder());
		JButton boton;
		boton = (JButton) barra.add(new JButton (opcPaciente));
		boton.setFont(new Font("Monospaced", Font.BOLD, 20));
		barra.add(Box.createHorizontalGlue());
		boton = (JButton) barra.add(new JButton (opcJuegos));
		boton.setFont(new Font("Monospaced", Font.BOLD, 20));
		barra.add(Box.createHorizontalStrut(100));
		boton = (JButton) barra.add(new JButton (opcSalir));
		boton.setFont(new Font("Monospaced", Font.BOLD, 20));

		return barra;
	}
	
	public JPanel crearPanelUsuario() {
		panelUsuario = new JPanel();
		panelUsuario.add(crearPanelSuperior(), BorderLayout.NORTH);
		panelUsuario.add(crearPanelLista(), BorderLayout.CENTER);
		panelUsuario.setBackground(new Color(228,221,86));
		return panelUsuario;
	}
	
	public JPanel crearPanelSuperior() {
		JPanel panel = new JPanel(new GridLayout(2,1));
		JLabel label = new JLabel("SEGUIMIENTO DE PACIENTES");
		
		label.setFont(new Font("Monospaced", Font.BOLD, 30));
		label.setForeground(Color.BLACK);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setOpaque(false);
		label.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		panel.add(label);
		panel.add(crearPanelBusqueda(), BorderLayout.NORTH);
		panel.setOpaque(false);
		return panel;
	}

	public JPanel crearPanelCampos() {
		panelJuego = new JPanel(new GridLayout(5,1));
		JButton boton = new JButton();
		
		boton = crearBoton("ATENCIÓN",1,45);
		panelJuego.add(boton);
		
		boton = crearBoton("MEMORIA",2,45);
		panelJuego.add(boton);
		
		boton = crearBoton("AGILIDAD MENTAL",3,45);
		panelJuego.add(boton);
		
		boton = crearBoton("RAZONAMIENTO MATEMÁTICO",4,45);
		panelJuego.add(boton);
		
		boton = crearBoton("RAZONAMIENTO VERBAL",5,45);
		panelJuego.add(boton);
		
		return panelJuego;
	}
	
	public JButton crearBoton(String str, int numCampo, int tamaño) {
		JButton boton = new JButton(str);
		Color color = null;
		
		boton.setFont(new Font("Monospaced", Font.BOLD, tamaño));
		boton.setActionCommand(str);
		boton.addActionListener(controlador);
		
		switch (numCampo) {
		case 1: color = new Color(180,0,0);//rojo - Atencion
			break;
		case 2: color = new Color(0,150,0);//verde - Memoria
			break;
		case 3: color = new Color(0,0,150);//azul - A. Mental
			break;
		case 4: color = new Color(230,150,0);//amarillo - R. Matemat.
			break;
		case 5: color = Color.DARK_GRAY;// R. Verbal
			break;
		}
		if(tamaño != 25) {
			boton.setForeground(color);
		}
		
		
		return boton;
	}
	
	public JPanel crearPanelAtencion() {
		panelAten = new JPanel(new GridLayout(5,1));
		JPanel panel = new JPanel();
		String[] str = {"Juego Figuras","Juego d's","Ruta primer nivel","Ruta nivel 2 (Colores)"};
		JButton btnAtras = new JButton(new ImageIcon("iconos/atras.png"));
		JLabel label = new JLabel("ATENCIÓN");
		
		btnAtras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				remove(panelAten);
				getContentPane().add(crearPanelCampos(), BorderLayout.CENTER);
				repaint();
				revalidate();	
			}
		});
		
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(new Font("Monospaced", Font.BOLD,40));
		label.setBorder(BorderFactory.createEmptyBorder(30,200,30,100));
		label.setForeground(new Color(180,0,0));
		
		panel.add(label, BorderLayout.CENTER);
		panel.add(btnAtras, BorderLayout.EAST);
		panelAten.add(panel);
		
		for(int i = 0; i < str.length; i++) {
			panelAten.add(crearBoton(str[i],1,25));
		}
		
		return panelAten;
	}
	
	public JPanel crearPanelMemoria() {
		panelMem = new JPanel(new GridLayout(5,1));
		JPanel panel = new JPanel();
		String[] str = {"Juego de Parejas","Juego de Luces"};
		JButton btnAtras = new JButton(new ImageIcon("iconos/atras.png"));
		JLabel label = new JLabel("MEMORIA");
		
		btnAtras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				remove(panelMem);
				getContentPane().add(crearPanelCampos(), BorderLayout.CENTER);
				repaint();
				revalidate();	
			}
		});
		
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(new Font("Monospaced", Font.BOLD,40));
		label.setBorder(BorderFactory.createEmptyBorder(30,200,30,100));
		label.setForeground(new Color(0,150,0));
		
		panel.add(label, BorderLayout.CENTER);
		panel.add(btnAtras, BorderLayout.EAST);
		panelMem.add(panel);
		
		for(int i = 0; i < str.length; i++) {
			panelMem.add(crearBoton(str[i],1,25));
		}
		
		return panelMem;
	}
	
	public JPanel crearPanelAgil() {
		panelAgil = new JPanel(new GridLayout(5,1));
		JPanel panel = new JPanel();
		String[] str = {"Juego Simbolos"};
		JButton btnAtras = new JButton(new ImageIcon("iconos/atras.png"));
		JLabel label = new JLabel("AGILIDAD MENTAL");
		
		btnAtras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				remove(panelAgil);
				getContentPane().add(crearPanelCampos(), BorderLayout.CENTER);
				repaint();
				revalidate();	
			}
		});
		
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(new Font("Monospaced", Font.BOLD,40));
		label.setBorder(BorderFactory.createEmptyBorder(30,110,30,90));
		label.setForeground(new Color(0,0,150));
		
		panel.add(label, BorderLayout.CENTER);
		panel.add(btnAtras, BorderLayout.EAST);
		panelAgil.add(panel);
		
		for(int i = 0; i < str.length; i++) {
			panelAgil.add(crearBoton(str[i],1,25));
		}
		
		return panelAgil;
	}
	
	public JPanel crearPanelMat() {
		panelMat = new JPanel(new GridLayout(5,1));
		JPanel panel = new JPanel();
		String[] str = {"Ejercicios matemáticos", "Adivina el operador"};
		JButton btnAtras = new JButton(new ImageIcon("iconos/atras.png"));
		JLabel label = new JLabel("RAZONAMIENTO MATEMÁTICO");
		
		btnAtras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				remove(panelMat);
				getContentPane().add(crearPanelCampos(), BorderLayout.CENTER);
				repaint();
				revalidate();	
			}
		});
		
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(new Font("Monospaced", Font.BOLD,40));
		label.setBorder(BorderFactory.createEmptyBorder(30,10,30,10));
		label.setForeground(new Color(230,150,0));
		
		panel.add(label, BorderLayout.CENTER);
		panel.add(btnAtras, BorderLayout.EAST);
		panelMat.add(panel);
		
		for(int i = 0; i < str.length; i++) {
			panelMat.add(crearBoton(str[i],1,25));
		}
		
		return panelMat;
	}
	
	public JTextField getTxtBusqueda() {
	    return txtBusqueda;
	  }
	
	public JPanel crearPanelVerb() {
		panelVerb = new JPanel(new GridLayout(5,1));
		JPanel panel = new JPanel();
		String[] str = {"Efecto Stroop"};
		JButton btnAtras = new JButton(new ImageIcon("iconos/atras.png"));
		JLabel label = new JLabel("RAZONAMIENTO VERBAL");
		
		btnAtras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				remove(panelVerb);
				getContentPane().add(crearPanelCampos(), BorderLayout.CENTER);
				repaint();
				revalidate();	
			}
		});
		
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(new Font("Monospaced", Font.BOLD,40));
		label.setBorder(BorderFactory.createEmptyBorder(30,10,30,20));
		label.setForeground(Color.DARK_GRAY);
		
		panel.add(label, BorderLayout.CENTER);
		panel.add(btnAtras, BorderLayout.EAST);
		panelVerb.add(panel);
		
		for(int i = 0; i < str.length; i++) {
			panelVerb.add(crearBoton(str[i],1,25));
		}
		
		return panelVerb;
	}
	
	public Component crearPanelBusqueda() {
		JPanel panel = new JPanel(new GridLayout(1,2,10,10));
		txtBusqueda = new JTextField(30);
		
		btnBusqueda = new JButton("Buscar");
		btnBusqueda.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		btnBusqueda.setFont(new Font("Monospaced", Font.BOLD, 20));
		btnBusqueda.setOpaque(false);
		btnBusqueda.addActionListener(new ActionListener() {
		      @Override
		      public void actionPerformed(ActionEvent arg0) {
		        // TODO Auto-generated method stub
		        String cadena;
		        String[] str = new String[3];
		        
		        cadena = modelo.buscarUsuario(getTxtBusqueda().getText());
		        System.out.println(cadena);
		        
		        if(cadena.equals("fail")) {
		          JOptionPane.showMessageDialog(null,"Paciente no encontrado");
		          return;
		        }else {
		          str = cadena.split(" ");
		          cadena = str[2]+" "+str[0]+" "+str[1];
		          cadena = cadena.replaceFirst("[\\s\\S]{0,1}$", "");
		          @SuppressWarnings("unused") PerfilMain perfil = new PerfilMain(cadena);
		        }
		      }
		      
		    });
		panel.add(txtBusqueda);
		panel.add(btnBusqueda);
		panel.setBorder(BorderFactory.createEmptyBorder(10,10,30,10));
		panel.setOpaque(false);
		return panel;
	}

	public Component crearPanelLista() {
		panelLista = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		listUsuarios = new JList<>();
		listUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listUsuarios.setModel(modelo);
		listUsuarios.setCellRenderer(renderer);
		listUsuarios.addListSelectionListener(controlador);
		panelLista.setViewportView(listUsuarios);
		
		return panelLista;
	}

	public void crearAcciones() {
		opcPaciente = new MiAccionPaciente(this, "REALIZAR SEGUIMIENTO", "Paciente", KeyEvent.VK_P);
		opcJuegos = new MiAccionJuegos(this, "BUSCAR EJERCICIO", "Juegos", KeyEvent.VK_J);
		opcSalir = new MiAccionSalir("SALIR", "Salir", KeyEvent.VK_ESCAPE);
	}
	
	public class MiAccionPaciente extends AbstractAction{
		private static final long serialVersionUID = 1L;
		MpDoctorVista vista;
		
		public MiAccionPaciente(MpDoctorVista vista, String texto, String descrip, Integer nemonic) {
			super(texto);
			this.vista = vista;
			this.putValue( Action.SHORT_DESCRIPTION, descrip);
			this.putValue(Action.MNEMONIC_KEY, nemonic);
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			vista.remove(panelJuego);
			vista.getContentPane().add(crearPanelUsuario(), BorderLayout.CENTER);
			vista.repaint();
			vista.revalidate();
		}
	}
	
	public class MiAccionJuegos extends AbstractAction{
		private static final long serialVersionUID = 1L;
		MpDoctorVista vista;
		
		public MiAccionJuegos(MpDoctorVista vista, String texto, String descrip, Integer nemonic) {
			super(texto);
			this.vista = vista;
			this.putValue( Action.SHORT_DESCRIPTION, descrip);
			this.putValue(Action.MNEMONIC_KEY, nemonic);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			vista.remove(panelUsuario);
			vista.getContentPane().add(crearPanelCampos(), BorderLayout.CENTER);
			vista.repaint();
			vista.revalidate();
		}
	}
	
	public class MiAccionSalir extends AbstractAction{
		private static final long serialVersionUID = 1L;
		public MiAccionSalir(String texto, String descrip, Integer nemonic) {
			super(texto);
			this.putValue( Action.SHORT_DESCRIPTION, descrip);
			this.putValue(Action.MNEMONIC_KEY, nemonic);
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			dispose();
			
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
}

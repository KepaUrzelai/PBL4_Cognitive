package chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class Chat extends JFrame implements accessChat, ActionListener{
	JTextField texto;
	JPanel panel;
	JLabel label;
	JList<String>lista;
	ListaMensajes modelo; //El modelo que se encargar de actualizar los mensajes
	
	public Chat(ListaMensajes modelo) {
	super("Chat");
	this.modelo = modelo;
	this.setSize(450,450);
	this.setContentPane(crearPanelVentana());
	this.setVisible(true);
	this.setResizable(false);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private HashMap<String ,accessUser> pacientes = new HashMap<String, accessUser>();
	
	public void sent (String origin, String destination, String msg) {
		if(pacientes.containsKey(origin) && pacientes.containsKey(destination)) {
			accessUser user = pacientes.get(destination);
			user.receive(origin, msg);
		} else {
			System.out.println("User doesn't exit !");
		}
	}
	public void register(accessUser user) {
		pacientes.put(user.getNombre(), user);	
	}
	
	
	private Container crearPanelVentana() {
		JPanel panel=new JPanel(new BorderLayout(0, 10));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		panel.add(crearPanelLista(), BorderLayout.CENTER);
		panel.add(crearPanelBoton(), BorderLayout.SOUTH);
		return panel;
	}
	
	private Component crearPanelBoton() {
		JPanel panel = new JPanel(new FlowLayout(0,10,0));
		JButton botonEnviar=new JButton("enviar");
		botonEnviar.addActionListener(this);
		texto=new JTextField();
		texto.setPreferredSize(new Dimension(300,30));
		panel.add(texto);
		panel.add(botonEnviar);
		return panel;
	}

	private Component crearPanelLista() {
		JScrollPane pImagen;
	    lista=new JList<>();
	    lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    lista.setModel(modelo);
	    //lista.setCellRenderer(renderer);

	    pImagen =  new JScrollPane( JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
	        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    
	    pImagen.setViewportView(lista);
	    
	    return pImagen;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		//modelo.addElement(new Mensaje(nombre,texto.getText()))
	    modelo.addElement(texto.getText());
	    texto.setText("");
	    
	}

}


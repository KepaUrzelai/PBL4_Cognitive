package chat;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class User implements accessUser{
private String nombre;
private accessChat chat;

	//Para crear el usuario es necesario decirle el chat del que formará parte
	public User(String nombre, accessChat chat) {

		this.nombre = nombre;
		this.chat = chat;
	}
	
	@Override
	public void receive(String origin, String msg) {
		String s = "El usuario " + origin + " te dice " + msg;
		System.out.println(nombre + ": " + s);

		
	}
	public void sent(String destination, String msg) {
		this.chat.sent(nombre,destination,msg);
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
	this.nombre  = nombre;
	}
	

}

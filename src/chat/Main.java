package chat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Main extends JFrame{
	JPanel panel;
	JTextField texto;
	static accessChat lChat;
	Chat chat;
	public Main() {
		super("Chat");
		ListaMensajes modelo = new ListaMensajes();
		Chat chat = new Chat(modelo);
		this.setSize(300,450);
		this.setContentPane(chat);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		Main main = new Main();
		
		accessUser paciente = new User("Paciente",lChat);
		lChat.register(paciente);
		
		accessUser medico = new User("Médico",lChat);
		lChat.register(medico);
		
		paciente.sent("Médico","Heyyyyy muy buenas a todos");
		medico.sent("Paciente","Aquí willyrex comentadoo");


		

	}

}

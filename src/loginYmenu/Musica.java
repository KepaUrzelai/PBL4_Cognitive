package loginYmenu;

import java.awt.BorderLayout;
import java.awt.Container;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Musica {//se decidió hacer un frame para la música para que en un fúturo se pueda implementar también en los juegos y poder apagarlo/encenderlo desde ahí
	final String FILENAME = "c.wav";
	SoundPlayer player;
	Properties propiedades;
	JFrame ventana;
	
	public Musica() {
		propiedades = new Properties();
		inicializarMusica();
		ventana = new JFrame ("");
		ventana.setSize(1, 1);
		ventana.setContentPane(crearPanelVentana());
		ventana.setVisible(true);
		ventana.setResizable(false);
		ventana.setLocation(-100,-100);		
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private Container crearPanelVentana() {
		JPanel panel=new JPanel(new BorderLayout());
		return panel;
	}

	public void inicializarMusica() {

		try(FileInputStream in = new FileInputStream ("config.properties.txt")){
			propiedades.load(in);
			
			if(propiedades.getProperty("musica").equals("on")) {
				player = new SoundPlayer(FILENAME);
				player.play();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		

	}

}

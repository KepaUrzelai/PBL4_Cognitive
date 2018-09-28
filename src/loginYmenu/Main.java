package loginYmenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import diagnostico.PlantillaJuegos1_bienvenida;

//import  Menua.LoginDialog;

public class Main {
	
	ImageIcon fondo1, fondo2, fondo3;

	public Main() {
		
		fondo1 = new ImageIcon("imagenes/Fondo1d.png");
		fondo2 = new ImageIcon("imagenes/Fondo2d.png");
		fondo3 = new ImageIcon("imagenes/Fondo3d.png");
		
		JLabel label1=new JLabel("Iniciar Sesi�n");
		label1.setHorizontalAlignment(JLabel.CENTER);
		label1.setFont(new Font("Monospaced",Font.BOLD ,35 ));
		JLabel label2=new JLabel("Crear Cuenta");
		label2.setHorizontalAlignment(JLabel.CENTER);
		label2.setFont(new Font("Monospaced",Font.BOLD ,35 ));
		JLabel label3=new JLabel("Salir");
		label3.setHorizontalAlignment(JLabel.CENTER);
		label3.setFont(new Font("Monospaced",Font.BOLD ,35 ));
	
		 final JFrame frame = new JFrame("Menu Login");
	        JButton btnLogin = new JButton("");
	   //     btnLogin.setBorder(BorderFactory.createLineBorder(Color.GREEN, 5));
	        btnLogin.setLayout(new BorderLayout());
	        btnLogin.add(label1, BorderLayout.CENTER);

	   //     btnLogin.setIcon(fondo1);
	        JButton btnCreate = new JButton("");
	    //    btnCreate.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
	        btnCreate.setLayout(new BorderLayout());
	        btnCreate.add(label2, BorderLayout.CENTER);
	   //     btnCreate.setIcon(fondo2);
	        JButton btnSalir = new JButton("");	        
	  //      btnSalir.setBorder(BorderFactory.createLineBorder(Color.RED, 5));
	        btnSalir.setLayout(new BorderLayout());
	        btnSalir.add(label3, BorderLayout.CENTER);
	  //      btnSalir.setIcon(fondo3);
	        JPanel panel = new JPanel();
        
        
        
        frame.setBounds(100, 100, 450, 300);
        
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;//La columna en la que está 
        gbc.gridy = 0;//La fila en la que está
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipadx = 20;
        gbc.ipady = 20;
        
        panel.add(btnLogin, gbc);
        gbc.gridy++;//Paso a la siguiente fila
        panel.add(btnCreate, gbc);
        gbc.gridy++;//Paso a la siguiente fila
        panel.add(btnSalir, gbc);
        
        frame.getContentPane().add(panel, BorderLayout.CENTER);
 
        btnLogin.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        LoginDialog2 loginDlg = new LoginDialog2(frame);
                        loginDlg.setVisible(true);
                        // if logon successfully
                        if(loginDlg.isSucceeded()){                  
                        	frame.dispose();
                        	if(loginDlg.getTipo()==0) {
                        	VistaMenuPrincipal principal = new VistaMenuPrincipal(loginDlg.getUsername());
                        	}
                        }
                    }
                });
        btnCreate.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        LoginDialog loginDlg = new LoginDialog(frame);
                        loginDlg.setVisible(true);
                        if(loginDlg.isSucceeded()){                  
                        	frame.dispose();
                      PlantillaJuegos1_bienvenida nuevoUsuario= new PlantillaJuegos1_bienvenida();
                        }
                    }
                });
        btnSalir.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                    }
                });
        
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(400,300);
        frame.setSize(500, 300);
        frame.setVisible(true);
        Dimension d = new Dimension(btnLogin.getSize());
        int a;
        a = (int) d.getWidth();
        btnLogin.setIcon(fondo1);
        btnCreate.setIcon(fondo2);
        btnSalir.setIcon(fondo3);
	}
	
	public static void main(String[] args) {

		Main programa = new Main();
	}
}


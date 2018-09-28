package loginYmenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;

import conexionBDyMIKROS.ConexionBDCognitive;
import conexionBDyMIKROS.DiagnosticoDAO;
import conexionBDyMIKROS.DoctorDAO;
import mpDoctor.MpDoctorControlador;
import mpDoctor.MpDoctorModelo;
import mpDoctor.MpDoctorVista;
import mpDoctor.UserRenderer;


public class LoginDialog2 extends JDialog {
    private static final long serialVersionUID = 1L;
    private JTextField tfUsername;
      private JPasswordField pfPassword;
      private JLabel lbUsername;
      private JLabel lbPassword;
      private JButton btnLogin;
      private JButton btnCancel;
      private JButton btnPaciente;
      private JButton btnDoctor;
      private boolean succeeded;
      String contraseña;
      //Usuario usuario;
      boolean confirm;
      Color azul;
      Color verde;
      int tipo;
  
      public LoginDialog2(Frame parent) {
        super(parent, "Logging", true);
        //super.setPreferredSize(new Dimension(350,300));
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        JPanel bp = new JPanel();
        JPanel top  = new JPanel();
        azul = new Color(0,0,150);
        verde = new Color(0,150,0);
        
        cs.fill = GridBagConstraints.HORIZONTAL;
    
        lbUsername = new JLabel("Usuario: ");
        lbUsername.setFont(new Font("Monospaced", Font.BOLD, 20));
        lbUsername.setForeground(Color.white);
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(lbUsername, cs);
    
        tfUsername = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(tfUsername, cs);
    
        lbPassword = new JLabel("Contraseña: ");
        lbPassword.setFont(new Font("Monospaced", Font.BOLD, 20));
        lbPassword.setForeground(Color.white);
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(lbPassword, cs);
    
        pfPassword = new JPasswordField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(pfPassword, cs);
        panel.setBorder(new LineBorder(Color.GRAY));
    
        btnLogin = new JButton("Entrar");
        //btnLogin.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        btnCancel = new JButton("Cancelar");
        //btnCancel.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        btnCancel.setFont(new Font("Monospaced", Font.BOLD, 20));
        btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				
			}
        	
        });
        
        btnLogin.setFont(new Font("Monospaced", Font.BOLD, 20));
        btnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(tipo==0) {
				confirm=true;
				cargarUsuario();       	
				if (confirm) {//si es usuario existe, se comprobará la contraseña.
        		try {
        		if(String.valueOf(pfPassword.getPassword()).equals(contraseña)) {
        			 JOptionPane.showMessageDialog(LoginDialog2.this,
                             "Hola " + getUsername() + "! Has iniciado sesion correctamente.",
                             "Login",
                             JOptionPane.INFORMATION_MESSAGE);
                     succeeded = true;
                     dispose();
        		} else {
                    JOptionPane.showMessageDialog(LoginDialog2.this,
                            "La contraseña no es correcta",
                            "Iniciar",
                            JOptionPane.ERROR_MESSAGE);
                    // reset username and password
                    tfUsername.setText("");
                    pfPassword.setText("");
                    succeeded = false;
 
                }
        		}catch (NullPointerException e1){
    				JOptionPane.showConfirmDialog(parent, "La contraseña no es correcta", "Error",
    						JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
    				confirm=false;   				
    			}
        	}
				
			}else {
				ConexionBDCognitive bd = new ConexionBDCognitive(2);
		  		DoctorDAO doctor= new DoctorDAO(bd);

		  		boolean comprobacion=doctor.comprobarDoctor(tfUsername.getText(),pfPassword.getText());
		  		bd.close();
		  		if(comprobacion) {
		  			iniciarDoctor();
		  			succeeded = true;
                    dispose();
		  		}else {
    				JOptionPane.showConfirmDialog(parent, "Los datos no son correctos", "Error",
    						JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
		  		}
			}
				
			}

			private void iniciarDoctor() {
				try {
				      //UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
				      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException e) {
				      // TODO Auto-generated catch block
				      e.printStackTrace();
				} catch (InstantiationException e) {
				      // TODO Auto-generated catch block
				      e.printStackTrace();
				} catch (IllegalAccessException e) {
				      // TODO Auto-generated catch block
				      e.printStackTrace();
				} catch (UnsupportedLookAndFeelException e) {
				      // TODO Auto-generated catch block
				      e.printStackTrace();
				}
				MpDoctorControlador controlador = new MpDoctorControlador();
				UserRenderer renderer = new UserRenderer();
				MpDoctorModelo modelo = new MpDoctorModelo(controlador);
				@SuppressWarnings("unused")
				MpDoctorVista vista = new MpDoctorVista(modelo,controlador,renderer);
				
			}
        	
        });
        
        btnPaciente = new JButton("Paciente");
        btnPaciente.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        btnPaciente.setFont(new Font("Monospaced", Font.BOLD, 20));
        btnPaciente.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        	top.setBackground(azul);
          	panel.setBackground(azul);
          	bp.setBackground(azul);
          	tipo=0;
        	}
          
        });
        
        btnDoctor = new JButton("Doctor");
        btnDoctor.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        btnDoctor.setFont(new Font("Monospaced", Font.BOLD, 20));
        btnDoctor.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          top.setBackground(verde);
          panel.setBackground(verde);
          bp.setBackground(verde);
          tipo=1;
        }
          
        });
        
        top.add(btnPaciente);
        top.add(btnDoctor); 
        bp.add(btnLogin);
        bp.add(btnCancel);
        
        top.setBackground(azul);
        top.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        panel.setBackground(azul);
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        bp.setBackground(azul);
        bp.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        getContentPane().add(top, BorderLayout.NORTH);
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);
    
        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
  }
      
      private void cargarUsuario() {
  		/*ObjectInputStream in = null;
  		
  		try {
  			in = new ObjectInputStream ( new FileInputStream("Usuarios/"+tfUsername.getText()+"/datos.txt"));
  	
  			
  				try {
  					usuario=(Usuario) in.readObject();
  				} catch (ClassNotFoundException e) {
  					// TODO Auto-generated catch block
  					e.printStackTrace();
  				}
  	
  			
  		} catch (FileNotFoundException e) {
  			
  			JOptionPane.showConfirmDialog(this, "Este usuario no existe", "Error",
  					JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
  			confirm=false;   
  		} catch (IOException e) {
  			JOptionPane.showConfirmDialog(this, "Este usuario1 no existe", "Error",
  					JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
  			confirm=false;   
  		}finally{
  			if (in!=null){
  				try { in.close(); } catch (IOException e) {}
  			}
  		}*/
  		
  		//ahora pa la BD
  		ConexionBDCognitive bd = new ConexionBDCognitive(1);
  		DiagnosticoDAO diagnostico= new DiagnosticoDAO(bd);

  		contraseña=diagnostico.comprobarPaciente(tfUsername.getText(), this);
  		bd.close();
  		
  		
  	}
  
  public JTextField getTfUsername() {
    return tfUsername;
  }
  
  
  public String getUsername() {
      return tfUsername.getText();
  }
  
  public String getPassword() {
      return new String(pfPassword.getPassword());
  }
  
  public boolean isSucceeded() {
      return succeeded;
  }

public int getTipo() {
	return tipo;
}
  
}

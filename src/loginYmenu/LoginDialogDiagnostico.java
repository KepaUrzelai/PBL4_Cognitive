package loginYmenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import conexionBDyMIKROS.ConexionBDCognitive;
import conexionBDyMIKROS.DiagnosticoDAO;
import usuarioYcronometro.Usuario;
 
public class LoginDialogDiagnostico extends JDialog {
 
	private static final long serialVersionUID = 1L;
	private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JLabel textoSeguridad;
    private JButton btnLogin;
    private JButton btnCancel;
    private boolean succeeded;
    Usuario usuario;
    boolean confirm;
    JPanel panel;
    String contraseña;
 
    public LoginDialogDiagnostico(Frame parent) {
        super(parent, "Login", true);
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
 
        cs.fill = GridBagConstraints.HORIZONTAL;
 
        lbUsername = new JLabel("Usuario: ");
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
 
        textoSeguridad = new JLabel("¡ Un doctor debe iniciar sesión para poder crear una nueva cuenta !");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(textoSeguridad, cs);
        
        btnLogin = new JButton("Entrar");

 
        btnLogin.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
            		confirm=true;
    				cargarUsuario();       	
            	if (confirm) {//si es usuario existe, se comprobará la contraseña.
            		try {
            		if(String.valueOf(pfPassword.getPassword()).equals(contraseña)) {
            			 JOptionPane.showMessageDialog(LoginDialogDiagnostico.this,
                                 "Hola " + getUsername() + "! Has iniciado sesion correctamente.",
                                 "Login",
                                 JOptionPane.INFORMATION_MESSAGE);
                         succeeded = true;
                         dispose();
            		} else {
                        JOptionPane.showMessageDialog(LoginDialogDiagnostico.this,
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
            }
        });
        btnCancel = new JButton("Cancelar");
        btnCancel.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        JPanel bp = new JPanel();
        bp.add(btnLogin);
        bp.add(btnCancel);
 
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);
 
        pack();
        setResizable(false);
        setLocationRelativeTo(parent);

    }
 
    public JPanel getPanel() {
		return panel;
	}

	public JTextField getTfUsername() {
		return tfUsername;
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
		ConexionBDCognitive bd = new ConexionBDCognitive(3);
		DiagnosticoDAO diagnostico= new DiagnosticoDAO(bd);

		contraseña=diagnostico.comprobarPaciente(tfUsername.getText(), this);
	
		
		
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
}
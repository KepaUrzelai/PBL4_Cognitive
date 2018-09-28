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
import conexionBDyMIKROS.DoctorDAO;
import usuarioYcronometro.Usuario;
 
public class LoginDialog extends JDialog {
 
	private static final long serialVersionUID = 1L;
	private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JButton btnLogin;
    private JButton btnCancel;
    private boolean succeeded;
    Usuario usuario;
    boolean confirm;
    JPanel panel;
    String contraseña;
 
    public LoginDialog(Frame parent) {
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
 
        btnLogin = new JButton("Entrar");

 
        btnLogin.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
				ConexionBDCognitive bd = new ConexionBDCognitive(2);
		  		DoctorDAO doctor= new DoctorDAO(bd);

		  		boolean comprobacion=doctor.comprobarDoctor(tfUsername.getText(),pfPassword.getText());
		  		bd.close();
		  		if(comprobacion) {
		  			succeeded = true;
                    dispose();
		  		}else {
    				JOptionPane.showConfirmDialog(parent, "Los datos no son correctos", "Error",
    						JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
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
        JOptionPane.showConfirmDialog(parent, "El doctor tendrá que meter sus datos para poder iniciar un diagnostico", "Aviso",
				JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);

    }
 
    public JPanel getPanel() {
		return panel;
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
}
package loginYmenu;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
 
public class EmpezarDialog extends JDialog implements ActionListener{
 

	private static final long serialVersionUID = 1L;
	private JButton btnPracticar;
    private JButton btnDiagnostico;
    JFrame ventana;
    boolean cerrar;
    public EmpezarDialog(Frame parent) {//un dialogo para saber si el usuario desea practicar, o debe de iniciar el diagnóstico.
        super(parent, "Empezar", true);
        ventana=(JFrame) parent;
        cerrar=false;
        JPanel panel = new JPanel(new GridLayout(2,1));
        btnPracticar=new JButton("Practicar");
        btnPracticar.addActionListener(this);
        btnDiagnostico=new JButton("Diagnóstico Semanal");
        btnDiagnostico.addActionListener(this);
        btnPracticar.setPreferredSize(new Dimension(450,80));
        btnPracticar.setFont(new Font("Monospaced", Font.BOLD, 30));
        btnPracticar.setActionCommand("practicar");
        btnDiagnostico.setPreferredSize(new Dimension(450,80));
        btnDiagnostico.setFont(new Font("Monospaced", Font.BOLD, 30));
        btnDiagnostico.setActionCommand("diag");
        panel.add(btnPracticar);
        panel.add(btnDiagnostico);
        this.setContentPane(panel);
        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
	
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("practicar")) {
			cerrar=true;
		}
		this.dispose();
		
		
	}

	public boolean isCerrar() {
		return cerrar;
	}
 
   
}
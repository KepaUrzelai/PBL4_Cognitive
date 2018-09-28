package mpDoctor;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import usuarioYcronometro.Puntuaciones;

public class SesionRenderer implements ListCellRenderer<Puntuaciones>{

	@Override
	public Component getListCellRendererComponent(JList<? extends Puntuaciones> arg0, Puntuaciones arg1,
			int arg2, boolean arg3, boolean arg4) {
		// TODO Auto-generated method stub
		JPanel panel = new JPanel();
		JLabel lb1 = new JLabel(""+arg1.getFecha());
		lb1.setHorizontalAlignment(JLabel.CENTER);
		lb1.setFont(new Font("Monospaced", Font.BOLD,20));
		lb1.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		
		JLabel lb2 = new JLabel(" Atención: "+arg1.getcAtencion()+"   Memoria: "+arg1.getcMemoria()+"   Agilidad Mental: "+arg1.getcAgilidad()
			+"   R. Matematico: "+arg1.getcRazMat()+"   R. Verbal: "+arg1.getcRazVerbal());
		lb2.setFont(new Font("Arial", Font.BOLD,15));
		panel.add(lb1);
		panel.add(lb2);
		panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		return panel;
	}


}

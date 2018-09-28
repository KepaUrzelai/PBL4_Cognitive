package mpDoctor;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class UserRenderer implements ListCellRenderer<JButton> {

	@Override
	public Component getListCellRendererComponent(JList<? extends JButton> arg0, JButton arg1, int index, boolean isSelected,
			boolean cellHasFocus) {
		JButton boton = new JButton();
		boton.setText("               "+arg1.getText()+"                 ");
		boton.setFont(new Font("Monospaced", Font.BOLD, 20));
		boton.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		boton.setOpaque(true);
		if(isSelected) {
			boton.setForeground(new Color(180,0,0));
			boton.setBackground(Color.GRAY);
		}else {
			boton.setForeground(Color.BLACK);
			boton.setBackground(Color.WHITE);
		}
		
		return  boton;
	}

}

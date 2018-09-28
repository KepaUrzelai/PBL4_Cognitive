package chat;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class Renderer implements ListCellRenderer<String>{
	String nombre;
	
	public Renderer(String nombre) {
		this.nombre=nombre;
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends String> list, String value, int index,
			boolean isSelected, boolean cellHasFocus) {
		JLabel label=new JLabel(value.toString());
		/*if(nombre==value.getNombre()){
			label.setHorizontalAlignment(JLabel.RIGHT);
			label.setForeground(Color.GREEN);
			
		}*/
		return label;
	}



}

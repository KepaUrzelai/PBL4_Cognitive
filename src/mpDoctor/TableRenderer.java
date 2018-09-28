package mpDoctor;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

@SuppressWarnings("serial")
public class TableRenderer extends DefaultTableCellRenderer{
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,int row,int col) {
	    JLabel c = (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
	    
	    c.setForeground(Color.DARK_GRAY);
	    c.setBorder(BorderFactory.createEmptyBorder(10,5,10,5));
	    
	    switch (row) {
	    case 0:
	    case 1: c.setBackground(new Color(98,176,255)); //Atencion
	      break;
	    case 2: c.setBackground(new Color(255,174,136)); //Memoria
	      break;
	    case 3: c.setBackground(new Color(204,204,153)); //A. mental
	      break;
	    case 4:
	    case 5: c.setBackground(new Color(191,255,128)); //R. matemat.
	      break;
	    case 6:
	    case 7: c.setBackground(new Color(255,189,255)); //R. verbal
	      break;
	    }
	    
	    switch (col){
	    case 0: c.setHorizontalAlignment(LEFT);
	      break;
	    case 1: c.setHorizontalAlignment(CENTER);
	      break;
	    case 2: c.setHorizontalAlignment(CENTER);
	      break;
	    }
	    
	    c.setFont(new Font("Arial", Font.BOLD,15));

	    return c;
	  }
	
}

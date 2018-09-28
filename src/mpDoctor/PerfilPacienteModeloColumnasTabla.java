package mpDoctor;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

@SuppressWarnings("serial")
public class PerfilPacienteModeloColumnasTabla extends DefaultTableColumnModel{
	TableRenderer trazador;
	
	public PerfilPacienteModeloColumnasTabla(TableRenderer trazador){
		super();
		this.trazador = trazador;
	    this.addColumn(crearColumna("EJERCICIO ESTIMULANTE",0,130));
	    this.addColumn(crearColumna("HORAS JUGADAS EN CAMPO",1,40));
	    this.addColumn(crearColumna("PUNTUACIÓN",2,20));
	}

	private TableColumn crearColumna(String texto, int indice, int ancho) {
		TableColumn columna = new TableColumn(indice,ancho);
		columna.setHeaderValue(texto);
		columna.setPreferredWidth(ancho);
		columna.setCellRenderer(trazador);
		return columna;
	}

}

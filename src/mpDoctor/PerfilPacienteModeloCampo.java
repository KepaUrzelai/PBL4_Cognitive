package mpDoctor;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import conexionBDyMIKROS.ConexionBDCognitive;
import conexionBDyMIKROS.PacienteDAO;
import usuarioYcronometro.Puntuaciones;
import usuarioYcronometro.Usuario;

public class PerfilPacienteModeloCampo extends AbstractTableModel{
	private static final long serialVersionUID = 1L;
	  List<FilaCampo> lista;
	  FilaCampo uno, dos, tres, cuatro, cinco, seis, siete, ocho;
	  PerfilPacienteModeloColumnasTabla columnas;
	  PacienteDAO d;
	  String tmpAtencion, tmpMemoria, tmpRMat, tmpRVerbal, tmpAgilidad;
	  
	  public PerfilPacienteModeloCampo(Usuario usuario,PerfilPacienteModeloColumnasTabla columnas) {
	    ConexionBDCognitive conexion = new ConexionBDCognitive(2);
	    d = new PacienteDAO(conexion);
	    tmpAtencion = d.cogerHoras("tmpAtencion", usuario.getNombre());
	    tmpMemoria = d.cogerHoras("tmpMemoria", usuario.getNombre());
	    tmpRMat = d.cogerHoras("tmpRMat", usuario.getNombre());
	    tmpRVerbal = d.cogerHoras("tmpRVerbal", usuario.getNombre());
	    tmpAgilidad = d.cogerHoras("tmpAgilidad", usuario.getNombre());
	    
	    lista = new ArrayList<>();
	    Puntuaciones puntuaciones;
	    puntuaciones = usuario.getPuntuaciones();
	    this.columnas = columnas;
	    inicializar(puntuaciones,usuario);
	  }
	  
	  
	  private void inicializar(Puntuaciones p, Usuario u) {
	    uno = new FilaCampo("Atención dígitos directos",p.getAtencionDirecto(),tmpAtencion);
	    dos = new FilaCampo("Atención dígitos inversos",p.getAtencionInverso(),tmpAtencion);
	    tres = new FilaCampo("Memoria",p.getMemoria(),tmpMemoria);
	    cuatro = new FilaCampo("Agilidad Mental",p.getAgilidadMental(),tmpAgilidad);
	    cinco = new FilaCampo("Problemas aritméticos",p.getRazMatProblemas(),tmpRMat);
	    seis = new FilaCampo("Cálculos matemáticos",p.getRazMatCalculos(),tmpRMat);
	    siete = new FilaCampo("Animales por minuto",p.getRazVerbalAnimales(),tmpRVerbal);
	    ocho = new FilaCampo("Abstracción",p.getRazVerbalAbstraccion(),tmpRVerbal);
	    
	    lista.add(uno);
	    lista.add(dos);
	    lista.add(tres);
	    lista.add(cuatro);
	    lista.add(cinco);
	    lista.add(seis);
	    lista.add(siete);
	    lista.add(ocho);
	  }


	  @Override
	  public int getColumnCount() {
	    return columnas.getColumnCount();
	  }

	  @Override
	  public int getRowCount() {
	    // TODO Auto-generated method stub
	    return lista.size();
	  }

	  
	  @Override
	  public Object getValueAt(int arg0, int arg1) {
	    // TODO Auto-generated method stub
	    return (arg1==0)?lista.get(arg0).getNombre():(arg1==1)?lista.get(arg0).getHoras():lista.get(arg0).getPuntuacion();
	  }
	  
}
package usuarioYcronometro;

import java.io.Serializable;

public abstract class Puntuaciones implements Serializable{
	int memoria;
	int agilidadMental;
	int atencionDirecto, atencionInverso;
	int razMatCalculos, razMatProblemas;
	int razVerbalAnimales, razVerbalAbstraccion;
	int cAtencion, cRazVerbal, cMemoria, cAgilidad, cRazMat; //0=deficiente, 1=bajo, 2=medio, 3=alto
	String fecha;
	
	public Puntuaciones(int memoria, int agilidadMental, int atencionDirecto, int atencionInverso,
			 int razMatCalculos, int razMatProblemas, int razVerbalAnimales, int razVerbalAbstraccion) {
		this.memoria=memoria;
		this.agilidadMental=agilidadMental;
		this.atencionDirecto=atencionDirecto;
		this.atencionInverso=atencionInverso;
		this.razMatCalculos=razMatCalculos;
		this.razMatProblemas=razMatProblemas;
		this.razVerbalAnimales=razVerbalAnimales;
		this.razVerbalAbstraccion=razVerbalAbstraccion;
		
	}
	
	public Puntuaciones(int atencion, int memoria, int agilidad, int razMat, int razVerb, String fecha) {
		this.cAtencion=atencion;
		this.cMemoria=memoria;
		this.cAgilidad=agilidad;
		this.cRazMat=razMat;
		this.cRazVerbal=razVerb;
		this.fecha=fecha;
	}

	
	public Puntuaciones(int atencion, int memoria, int agilidad, int razMat, int razVerb) {
		this.cAtencion=atencion;
		this.cMemoria=memoria;
		this.cAgilidad=agilidad;
		this.cRazMat=razMat;
		this.cRazVerbal=razVerb;
	}
	
	public abstract void calcularBaremos();
	@Override
	public String toString() {
		return "Puntuaciones [memoria=" + memoria + ", agilidadMental=" + agilidadMental + ", atencionDirecto="
				+ atencionDirecto + ", atencionInverso=" + atencionInverso + ", razMatCalculos=" + razMatCalculos
				+ ", razMatProblemas=" + razMatProblemas + ", razVerbalAnimales=" + razVerbalAnimales
				+ ", razVerbalAbstraccion=" + razVerbalAbstraccion + "]";
	}

	public String getFecha() {
		return fecha;
	}

	public int getcAtencion() {
		return cAtencion;
	}

	public int getcRazVerbal() {
		return cRazVerbal;
	}

	public int getcMemoria() {
		return cMemoria;
	}

	public int getcAgilidad() {
		return cAgilidad;
	}

	public int getcRazMat() {
		return cRazMat;
	}

	public int getMemoria() {
		return memoria;
	}

	public int getAgilidadMental() {
		return agilidadMental;
	}

	public int getAtencionDirecto() {
		return atencionDirecto;
	}

	public int getAtencionInverso() {
		return atencionInverso;
	}

	public int getRazMatCalculos() {
		return razMatCalculos;
	}

	public int getRazMatProblemas() {
		return razMatProblemas;
	}

	public int getRazVerbalAnimales() {
		return razVerbalAnimales;
	}

	public int getRazVerbalAbstraccion() {
		return razVerbalAbstraccion;
	}

	
}

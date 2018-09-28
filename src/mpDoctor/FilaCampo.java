package mpDoctor;

public class FilaCampo {
	 String nombre;
	 int puntuacion;
	 String horas;
	 
	 public FilaCampo(String nombre, int puntuacion, String horas) {
		 this.nombre = nombre;
		 this.puntuacion = puntuacion;
		 this.horas = horas; 
	 }

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public String getHoras() {
		return horas;
	}

}

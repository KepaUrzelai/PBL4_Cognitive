package diagnostico;

public class RegistroImagenes {//esta clase se utiliza en el diagnóstico de la agilidad mental
	int [] puntuacion;
	
	public RegistroImagenes(int segundos) {
		this.puntuacion = new int[7];
		this.puntuacion[0] = puntuar(segundos);
	}
	
	public void saveTime(int sec, int pos) {
		
		this.puntuacion[pos] = puntuar(sec);
	}
	
	public int puntuar(int sec) {
		int punt;
		
		if (sec < 3) {
			punt = 3;
		}
		else if ((sec >= 3) && (sec < 10)) {
			punt = 2;
		}
		else if ((sec >= 10) && (sec < 30)) {
			punt = 1;
		}
		else punt = 0;
		
		return punt;
	}
	public int media() {
		int n;
		n = 0;
		for(int i = 0; i < puntuacion.length; i++) {
			n = n + puntuacion[i];  
			System.out.println(n);
			System.out.println(puntuacion[i] + " .>" + i);
		}
		
		return n;
	}
}
package usuarioYcronometro;

import java.io.Serializable;

public class Hora implements Serializable{
	int hora;
	int minuto;
	int segundo;
	
	public Hora(int hora, int minuto, int segundo) {
		this.hora=hora;
		this.minuto=minuto;
	}
	
	public void sumarTiempo(int segundos) {
		segundo+=segundos;
		while(segundo>60) {
			segundo-=60;
			minuto++;
		}
		while(minuto>60) {
			minuto-=60;
			hora++;
		}
	}
	
	

}

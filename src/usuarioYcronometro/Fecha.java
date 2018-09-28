package usuarioYcronometro;

import java.io.Serializable;

public class Fecha implements Serializable{
	int dia;
	int mes;
	int a�o;
	
	public Fecha(int dia, int mes, int a�o) {
		this.dia=dia;
		this.mes=mes;
		this.a�o=a�o;
	}

	@Override
	public String toString() {
		return a�o + "-" + mes + "-" + dia;
	}
	
	public boolean comprobar() {//definimos las fechas posibles
		if((this.a�o<=1900) || (this.a�o>1978)) {
			return false;
		}
		if(this.mes>12) {
			return false;
		}
		if((this.mes==1)&&(this.dia>31)) {
			return false;
		}
		if((this.mes==2)&&(this.dia>28)) {
			return false;
		}
		if((this.mes==3)&&(this.dia>31)) {
			return false;
		}
		if((this.mes==4)&&(this.dia>30)) {
			return false;
		}
		if((this.mes==5)&&(this.dia>31)) {
			return false;
		}
		if((this.mes==6)&&(this.dia>30)) {
			return false;
		}
		if((this.mes==7)&&(this.dia>31)) {
			return false;
		}
		if((this.mes==8)&&(this.dia>31)) {
			return false;
		}
		if((this.mes==9)&&(this.dia>30)) {
			return false;
		}
		if((this.mes==10)&&(this.dia>31)) {
			return false;
		}
		if((this.mes==11)&&(this.dia>30)) {
			return false;
		}
		if((this.mes==12)&&(this.dia>31)) {
			return false;
		}
		return true;
		
	}

}

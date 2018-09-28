package conexionmikros;

import gnu.io.SerialPort;

public class LineaSerie extends Thread{
	String empezar="hasi";
	String terminar="bukatu";
	String tiempo;
	boolean juegoTerminado;
	SerialPort serialPort;
	
	public LineaSerie() {
		juegoTerminado=false;
		
	}

	@Override
	public void run() {
		Estado estado;
 		Context context = new Context();
 		estado=new Conectar();
 		context.setState(estado);
 		context.request();
 		serialPort=context.getSerialPort();
 		estado=new Escribir(serialPort, empezar);
 		context.setState(estado);
 		context.request();
 		while(!juegoTerminado) {
 			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 		}
 		estado=new Escribir(serialPort, terminar);
 		context.setState(estado);
 		context.request();
 		estado=new Leer(serialPort, tiempo, this);
 		context.setState(estado);
 		context.request();
 		estado=new Desconectar(serialPort);
 		context.setState(estado);
 		context.request();
	}

	
	
	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}

	public String getTiempo() {
		return tiempo;
	}

	public void setJuegoTerminado(boolean juegoTerminado) {
		this.juegoTerminado = juegoTerminado;
	}


}

package conexionmikros;

import gnu.io.SerialPort;

public class Context {
	private Estado estado;
	 
 	public void setState( Estado estado )
 	{
 		this.estado = estado;
 	}
 
 	public Estado getState()
 	{
 		return estado;
 	}
 
 	public void request()
 	{
 		estado.ejecuta();
 	}
 	
 	public SerialPort getSerialPort() {
 		return estado.getSerialPort();
 	}

}

package conexionmikros;

import gnu.io.SerialPort;

public class Desconectar implements Estado{
	SerialPort serialPort;
	
	public Desconectar(	SerialPort serialPort) {
		this.serialPort=serialPort;
	}

	@Override
	public void ejecuta() {
		serialPort.close();
	}

	@Override
	public SerialPort getSerialPort() {
		return serialPort;
	}

}

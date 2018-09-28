package conexionmikros;

import gnu.io.SerialPort;

public interface Estado {
	void ejecuta();
	SerialPort getSerialPort();
}

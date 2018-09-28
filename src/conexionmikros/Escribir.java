package conexionmikros;

import java.io.IOException;
import java.io.OutputStream;

import gnu.io.SerialPort;

public class Escribir implements Estado {
	SerialPort serialPort;
	String msg;
	public OutputStream out;
	
	public Escribir(SerialPort serialPort, String msg) {
		this.serialPort=serialPort;
		this.msg=msg;
		
	}

	@Override
	public void ejecuta() {
		try {
			out = serialPort.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try
        {                
            this.out.write(msg.getBytes());              
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        } 
	}

	@Override
	public SerialPort getSerialPort() {
		return serialPort;
	}

}

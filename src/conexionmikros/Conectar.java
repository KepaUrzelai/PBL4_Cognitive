package conexionmikros;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

public class Conectar implements Estado{
	SerialPort serialPort;

	@Override
	public void ejecuta() {
		CommPortIdentifier portIdentifier=encontrarPuerto();
		if ( portIdentifier.isCurrentlyOwned() ){
            System.out.println("Error puerta en uso");
        }else {
             CommPort commPort;
			try {
				commPort = portIdentifier.open("Mi programa",2000);
			     if ( commPort instanceof SerialPort ) {
		                serialPort = (SerialPort) commPort;
		                serialPort.setSerialPortParams(9600,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
			     }else {
		                System.out.println("Error este programa solo funciona con linea serie");
		            }
			} catch (PortInUseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedCommOperationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        }
		
	}
	
	   public SerialPort getSerialPort() {
		return serialPort;
	}

	public CommPortIdentifier encontrarPuerto()
	    {
	        java.util.Enumeration<CommPortIdentifier> portEnum = CommPortIdentifier.getPortIdentifiers();
	        while ( portEnum.hasMoreElements() ) 
	        {
	            CommPortIdentifier portIdentifier = portEnum.nextElement();
	            if (this.getPortTypeName(portIdentifier.getPortType()).equals("Serial")) {
	            	return portIdentifier;
	            }
	            System.out.println(portIdentifier.getName()  +  " - " +  getPortTypeName(portIdentifier.getPortType()) );
	        } 
	        return null;
	    }
	    
	    public String getPortTypeName ( int portType )
	    {
	        switch ( portType )
	        {
	            case CommPortIdentifier.PORT_I2C:
	                return "I2C";
	            case CommPortIdentifier.PORT_PARALLEL:
	                return "Parallel";
	            case CommPortIdentifier.PORT_RAW:
	                return "Raw";
	            case CommPortIdentifier.PORT_RS485:
	                return "RS485";
	            case CommPortIdentifier.PORT_SERIAL:
	                return "Serial";
	            default:
	                return "unknown type";
	        }
	    }

}

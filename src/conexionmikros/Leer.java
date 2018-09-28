package conexionmikros;

import java.io.IOException;
import java.io.InputStream;

import gnu.io.SerialPort;

public class Leer implements Estado {
	SerialPort serialPort;
	LineaSerie lineaSerie;
	String msg;
	public InputStream in;
	
	public Leer(SerialPort serialPort, String msg, LineaSerie lineaSerie) {
		this.serialPort=serialPort;
		this.msg=msg;
		this.lineaSerie=lineaSerie;
		
	}

	@Override
	public void ejecuta() {
		//int data;
		//byte[] buffer = new byte[1024];
		
		try {
			in = serialPort.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}/*
		try {
			int len = 0;
            while ( ( data = in.read()) > -1 )
            {
                if ( data == '\n' ) {
                	msg=buffer.toString();
                    break;
                }
                buffer[len++] = (byte) data;
            }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 System.out.print(new String(buffer,0,buffer.length));
		//System.out.println(msg);*/
		 byte[] buffer = new byte[1024];
		 boolean prueba=true;
	      //  int len = -1;
	       int data=-1;
	        try {
	        	
				/*while ( ( len = in.read(buffer)) > -1 ){
					if(len!=0) {
						System.out.println(new String(buffer,0,len));
						break;
					}
				}
				System.out.println("aaaaaaaaa");
				msg=new String(buffer,0,len);*/
	        	int len = 0;
	        	while(prueba) {
	            while ( ( data = in.read()) > -1 )
	            {
	                if ( data == '*' ) {
	                    prueba=false;
	                	break;
	                }
	                buffer[len++] = (byte) data;
	            }
	        	}
	            System.out.print(msg=new String(buffer,0,len));
	            System.out.println("comprobando");
	            //msg=new String(buffer,0,len);
				lineaSerie.setTiempo(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	@Override
	public SerialPort getSerialPort() {
		return serialPort;
	}

}

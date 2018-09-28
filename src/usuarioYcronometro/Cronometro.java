package usuarioYcronometro;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Cronometro extends JPanel {


    private JLabel timeLabel;
    int start;

    byte centiseconds = 0;
    byte seconds = 0;
    short minutes = 0;
    
    int direction;

    private DecimalFormat timeFormatter;

    private Timer timer;

    /* direccion:
     * 		0.Cronometro va hacia arriba empezando siempre desde 0;
     * 		1.Cronometro va hacia abajo empezando por start;
     * start:
     * 		Segundos con los que quieres que el programa empiece;
     */
    public Cronometro(int direction, int start) {
    	
    		super(new GridBagLayout());
    		this.start = start;
    		this.direction = direction;
    		this.setOpaque(false);
    		if (direction == 0) {
    			seconds = 0;
    		}
    		else seconds = (byte) start;
    		
        
        this.setLayout(new BorderLayout());
        
        add(crearPanelTiempo(), BorderLayout.CENTER);
        

        setVisible(true);
    }
    
    
    public JPanel crearPanelTiempo() {
    		JPanel panel = new JPanel();
    		
    		panel.setOpaque(false);
    		timeLabel = new JLabel();
        timeLabel.setFont(new Font("Monospaced", Font.BOLD, 60));
        timeLabel.setHorizontalAlignment(JLabel.CENTER);
        
        panel.add(timeLabel);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
     
        timeFormatter = new DecimalFormat("00");

        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            		if (direction == 0) {
            			centiseconds++;
                 
                			if (centiseconds == 99) {
                				seconds++;
                				centiseconds = 0;
                			} else if (seconds == 59) {
                				minutes++;
                				seconds = 0;
                				centiseconds = 0;
                    
                			}
            			}
            		else {
            			if (centiseconds > 0) {
                            centiseconds--;
                        } 
                        else {
                            if (seconds == 0 && minutes == 0) {
                                timer.stop();
                            } else if (seconds > 0) {
                                seconds--;
                                centiseconds = 99;
                            } else if (minutes > 0) {
                                minutes--;
                                seconds = 59;
                                centiseconds = 99;
                            }
                        }
            		}
                timeLabel.setText(timeFormatter.format(minutes) + ":"
                        + timeFormatter.format(seconds));
            }
        });

        timeLabel.setText(timeFormatter.format(minutes) + ":"
                + timeFormatter.format(seconds));
        
        
    		return panel;
    }
    

    
    public void iniciar() {
    		if(direction == 0) {
    			seconds = 0;
    		}
    		else {
    			seconds = (byte) start;	
    		}
    		centiseconds = 0;
    		minutes = 0;
    		timer.start();
    }

    public void detener() {
    		timer.stop();
    		System.out.println(minutes + ":" + String.format("%02d", seconds));
    		if(direction == 0) {
    			seconds = 0;
    		}
    		else {
    			seconds = (byte) start;	
    		}
    		centiseconds = 0;
    		minutes = 0;
    }

	public byte getCentiseconds() {
		return centiseconds;
	}


	public void setCentiseconds(byte centiseconds) {
		this.centiseconds = centiseconds;
	}


	public byte getSeconds() {
		return seconds;
	}


	public void setSeconds(byte seconds) {
		this.seconds = seconds;
	}


	public short getMinutes() {
		return minutes;
	}


	public void setMinutes(short minutes) {
		this.minutes = minutes;
	}
}
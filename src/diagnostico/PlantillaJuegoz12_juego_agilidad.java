package diagnostico;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import usuarioYcronometro.Cronometro;


public class PlantillaJuegoz12_juego_agilidad extends JPanel {

	Image imagen;
	ImageIcon fondo;
	Color color;
	String campo, titulo;
	JButton startButton;
	Cronometro cronometro;
	JPanel panel;
	Random rand;
	JLabel picLabel;
	ImageIcon [] imagenes;
	RegistroImagenes registro;
	int kont, puntuacion;//el kont va sumando las imagenes   
	boolean finalizado;

	
	public PlantillaJuegoz12_juego_agilidad() {
		super(new BorderLayout());
		finalizado=false;
		titulo = "Agilidad/Velocidad de pensamiento";
		fondo = new ImageIcon("imagenes/Fondo4d.png");
		campo = "Juego : ";
		color = new Color(230,150,0);//amarillo oscuro
	
	    imagen = fondo.getImage();
		
	    cronometro = new Cronometro(0, 0);
		rand = new Random();
		imagenes = new ImageIcon[20];
		picLabel = new JLabel();
		
		try {
      	  imagenes[0] = new ImageIcon(ImageIO.read(new File("imagenes/gato.jpg")));
          imagenes[1] = new ImageIcon(ImageIO.read(new File("imagenes/kanguro.jpg")));
          imagenes[2] = new ImageIcon(ImageIO.read(new File("imagenes/leon.jpg")));
          imagenes[3] = new ImageIcon(ImageIO.read(new File("imagenes/lobo.jpg")));
          imagenes[4] = new ImageIcon(ImageIO.read(new File("imagenes/camera.jpg")));
          imagenes[5] = new ImageIcon(ImageIO.read(new File("imagenes/oso.jpg")));
          imagenes[6] = new ImageIcon(ImageIO.read(new File("imagenes/perro.jpg")));
          imagenes[7] = new ImageIcon(ImageIO.read(new File("imagenes/pez.jpg")));
          imagenes[8] = new ImageIcon(ImageIO.read(new File("imagenes/vaca.jpg")));
          imagenes[9] = new ImageIcon(ImageIO.read(new File("imagenes/camion.jpg")));
          imagenes[10] = new ImageIcon(ImageIO.read(new File("imagenes/cuchara.jpg")));
          imagenes[11] = new ImageIcon(ImageIO.read(new File("imagenes/barco.jpg")));
          imagenes[12] = new ImageIcon(ImageIO.read(new File("imagenes/ordenador.jpg")));
          imagenes[13] = new ImageIcon(ImageIO.read(new File("imagenes/manzana.jpg")));
          imagenes[14] = new ImageIcon(ImageIO.read(new File("imagenes/coche.png")));
          imagenes[15] = new ImageIcon(ImageIO.read(new File("imagenes/arbol.png")));
      } catch (IOException ex) {
          ex.printStackTrace();
      }
        
        this.add(cronometro, BorderLayout.NORTH);
        
        startButton = new JButton("Empezar");
        startButton.setActionCommand("Empezar");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            		
            		if(cronometro.getCentiseconds() != 0) {
            		//	System.out.println(cronometro.getMinutes() + ":" + String.format("%02d", cronometro.getSeconds()));
            		}
            		
            		if(startButton.getActionCommand() == "Empezar") {
            			startButton.setText("Siguiente");
            			startButton.setActionCommand("Siguiente");
            		}
            		else {
            			if(registro == null) {
            				kont = 0;
            				registro = new RegistroImagenes(cronometro.getSeconds());
            			}
            			else {
            				kont++;
            				registro.saveTime(cronometro.getSeconds(), kont);
            			}
            		}
            		
            		
            		if (kont == 6) {
    					startButton.setEnabled(false);
    					finalizado=true;
    					cronometro.detener();
    					puntuacion = registro.media();
    				}
            		else {
            			cronometro.iniciar();
                
            			picLabel.setIcon(null);
            			picLabel = new JLabel(imagenes[rand.nextInt(15)]);
            			add(picLabel, BorderLayout.CENTER);
            			revalidate();
            			repaint();
            		}
            }
        });


        this.add(startButton, BorderLayout.SOUTH);		
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}


	

	public ImageIcon getFondo() {
		return fondo;
	}

	public Color getColor() {
		return color;
	}
	
	public String getCampo() {
		return campo;
	}

	public String getTitulo() {
		return titulo;
	}
	

	public void setImagen(Image nuevaImagen) {
		imagen = nuevaImagen;
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D gr = (Graphics2D)g;
		if (imagen != null) {
			gr.drawImage(imagen, 0, 0, getWidth(), getHeight(),this);
		} else {
			setOpaque(true);
		}	
	}

}

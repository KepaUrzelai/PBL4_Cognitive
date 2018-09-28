package usuarioYcronometro;

public class PuntuacionesAlto extends Puntuaciones{

	public PuntuacionesAlto(int memoria, int agilidadMental, int atencionDirecto, int atencionInverso,
			int razMatCalculos, int razMatProblemas, int razVerbalAnimales, int razVerbalAbstraccion) {
		super(memoria, agilidadMental, atencionDirecto, atencionInverso, razMatCalculos, razMatProblemas, razVerbalAnimales,
				razVerbalAbstraccion);
		// TODO Auto-generated constructor stub
	}
	
	public PuntuacionesAlto(int atencion, int memoria, int agilidad, int razmat, int razverb, String fecha) {
	super(atencion, memoria, agilidad, razmat, razverb, fecha);
	}
	
	public PuntuacionesAlto(int atencion, int memoria, int agilidad, int razmat, int razverb) {
	super(atencion, memoria, agilidad, razmat, razverb);
	}
	


	@Override
	public void calcularBaremos() {
			int dAtencion, iAtencion;//0=deficiente, 1=bajo, 2=medio, 3=alto. Esto no es el campo de atención. El campo tendra el nivel mas bajo de las dos puntuaciones de atencion
			if(this.atencionDirecto<=5) dAtencion=0;
			else if(this.atencionDirecto<=7) dAtencion=1;
			else if(this.atencionDirecto<=8) dAtencion=2;
			else dAtencion=3;
			if(this.atencionInverso<=4) iAtencion=0;
			else if(this.atencionInverso<=5) iAtencion=1;
			else if(this.atencionInverso<=6) iAtencion=2;
			else iAtencion=3;
			if((dAtencion==0)||(iAtencion==0)) cAtencion=0;//aqui se puntua el campo. Lo define la puntuación mas baja
			else if((dAtencion==1)||(iAtencion==1)) cAtencion=1;
			else if((dAtencion==2)||(iAtencion==2)) cAtencion=2;
			else cAtencion=3;
			
			int rAnimales, rAbstraccion;
			if(this.razVerbalAnimales<=14) rAnimales=0;
			else if(this.razVerbalAnimales<=17) rAnimales=1;
			else if(this.razVerbalAnimales<=24) rAnimales=2;
			else rAnimales=3;			
			if(this.razVerbalAbstraccion<=4) rAbstraccion=0;
			else if(this.razVerbalAbstraccion<=6) rAbstraccion=1;
			else if(this.razVerbalAbstraccion<=7) rAbstraccion=2;
			else rAbstraccion=3;
			if((rAnimales==0)||(rAbstraccion==0)) cRazVerbal=0;
			else if((rAnimales==1)||(rAbstraccion==1)) cRazVerbal=1;
			else if((rAnimales==2)||(rAbstraccion==2)) cRazVerbal=2;
			else cRazVerbal=3;
			
			if(this.memoria<=3) cMemoria=0;
			else if(this.memoria<=4) cMemoria=1;
			else if(this.memoria<=5) cMemoria=2;
			else cMemoria=3;
			
			if(this.agilidadMental<=19) cAgilidad=0;
			else if(this.agilidadMental<=20) cAgilidad=1;
			else cAgilidad=3;
			
			int mCalculos, mProblemas;
			if(this.razMatCalculos<=8) mCalculos=0;
			else if(this.razMatCalculos<=10) mCalculos=1;
			else if(this.razMatCalculos<=23) mCalculos=2;
			else mCalculos=3;
			if(this.razMatProblemas<=4) mProblemas=0;
			else if(this.razMatProblemas<=7) mProblemas=1;
			else if(this.razMatProblemas<=14) mProblemas=2;
			else mProblemas=3;
			if((mCalculos==0)||(mProblemas==0)) cRazMat=0;
			else if((mCalculos==1)||(mProblemas==1)) cRazMat=1;
			else if((mCalculos==2)||(mProblemas==2)) cRazMat=2;
			else cRazMat=3;
		
	}

}

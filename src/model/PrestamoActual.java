package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A")
public class PrestamoActual extends Prestamo {
	
	public PrestamoActual(){}
	
	public void devolver(){
		//
	}
	
	public void devolver(Denuncia denuncia){
		//
	}

}

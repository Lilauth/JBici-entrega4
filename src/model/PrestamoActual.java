package model;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A")
public class PrestamoActual extends Prestamo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PrestamoActual(){}
	
	public void devolver(){
		//
	}
	
	public void devolver(Denuncia denuncia){
		//
	}

}

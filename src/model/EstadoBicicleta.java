package model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 
 * @author lilauth
 *
 * "apta para el uso"
 * "en desuso"
 * "en reparacion"
 * "denunciada"
 */

@Entity
public class EstadoBicicleta {
	@Id
	private long id;
	private String descricpion;
	
	//para hibernate
	public EstadoBicicleta(){}
	
	public void setId(long id){
		this.id = id;
	}
	
	public long getId(){
		return this.id;
	}

	public String getDescricpion() {
		return descricpion;
	}

	public void setDescricpion(String descricpion) {
		this.descricpion = descricpion;
	}
	
	
}

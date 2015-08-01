package services;

import dao.AuditoriaDAOImpl;

public class EstadisticasService {
	
	private AuditoriaDAOImpl auditDAO;
	
	public EstadisticasService(){
		auditDAO = new AuditoriaDAOImpl();
	} 
	
	public long altasDeUsuario(long id){
		//retorna las altas de usuarios				
		return this.auditDAO.altasEnEntidad(id, "class model.Usuario");
	}
	
	public long modificacionesDeUsuario(long id){
		//retorna las altas de usuarios
		return this.auditDAO.modificacionesEnEntidad(id, "class model.Usuario");
	}
	
	public long bajasDeUsuario(long id){
		//retorna las altas de usuarios
		return this.auditDAO.bajasEnEntidad(id, "class model.Usuario");
	}
}

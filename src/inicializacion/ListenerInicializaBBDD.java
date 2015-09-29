package inicializacion;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import dao.*;
import model.Estacion;
import model.EstadoEstacion;
import model.Perfil;
import model.Usuario;

@WebListener
public class ListenerInicializaBBDD implements ServletContextListener {

    public ListenerInicializaBBDD() {
        // 
    }


    public void contextInitialized(ServletContextEvent arg0) {
    	
    	// USUARIOS ========================================
    	/*
    	 *  Creo un usuario Administrador por defecto (si no existe) admin@admin.com 
    	 *  y pass admin necesario para poder usar el sistema
    	 *  y otro usuario comun usuario@usuario.com y pass usuario
    	 */
    	
    	IBaseDAO<Usuario> daoUsuario = FactoryDAO.getUsuarioDAO();
    	Usuario user;
    	if (daoUsuario.listar().isEmpty()) {
    		// no hay usuarios en la BD
    		
    		user = new Usuario();    		
    		user.setNombre("Usuario");
    		user.setApellido("Admin");
    		user.setPerfil(Perfil.ADMINISTRADOR);
    		user.setActivo(true);
    		user.setEmail("admin@admin.com");
    		user.setPassword("admin");    		
    		
    		daoUsuario.persistir(user);
    		System.out.println("** Agregado usuario Admin ");
    		
    		user = new Usuario();    		
    		user.setPerfil(Perfil.USUARIO);
    		user.setActivo(true);
    		user.setNombre("Usuario");
    		user.setApellido("Comun");
    		user.setEmail("usuario@usuario.com");
    		user.setPassword("usuario");    		
    		
    		daoUsuario.persistir(user);
    		System.out.println("** Agregado usuario comun ");
    	}
    	
    	
    	IBaseDAO<Estacion> daoEstacion = FactoryDAO.getEstacionDAO();
    	if (daoEstacion.listar().isEmpty()){
    		Estacion pzaSanMartin = new Estacion();
    		pzaSanMartin.setCantEstacionamientos(40);    		
    		pzaSanMartin.setNombre("Plaza San Mart�n");
    		pzaSanMartin.setUbicacion(-34.918131995447354, -57.948323906781006);
    		pzaSanMartin.setEstado(EstadoEstacion.OPERATIVA);
    		daoEstacion.persistir(pzaSanMartin);
    		
    		Estacion pzaIslasMalvinas = new Estacion();
    		pzaIslasMalvinas.setCantEstacionamientos(40);    	
    		pzaIslasMalvinas.setNombre("Plaza Islas Malvinas");
    		pzaIslasMalvinas.setUbicacion(-34.9274944, -57.9611928);
    		pzaIslasMalvinas.setEstado(EstadoEstacion.OPERATIVA);
    		daoEstacion.persistir(pzaIslasMalvinas);
    		
    	}
    	
    	
		System.out.println("***** BBDD Inicializada *****");
    }

	
    public void contextDestroyed(ServletContextEvent arg0) {
        // 
    }
	
}

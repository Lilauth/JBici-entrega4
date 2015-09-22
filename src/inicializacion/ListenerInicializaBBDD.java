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
    	
    	// PERFILES ========================================   	
    	/*
    	 * Los perfiles van fijos y son 2: Administradores y Usuarios
    	 */
    	/*
    	IBaseDAO<Perfil> daoPerfil = FactoryDAO.getPerfilDAO();
    	Perfil perfil = new Perfil();
    	perfil.setId(1);
    	perfil.setDescripcion("ADMINISTRADOR");
    	daoPerfil.persistir(perfil);
    	
    	perfil = new Perfil();
    	perfil.setId(2);
    	perfil.setDescripcion("USUARIO");
    	daoPerfil.persistir(perfil);
    	*/
    	
    	// USUARIOS ========================================
    	/*
    	 *  Creo un usuario Administrador por defecto (si no existe) admin@admin.com.ar 
    	 *  y pass admin
    	 *  necesario para poder usar el sistema
    	 */
    	
    	IBaseDAO<Usuario> daoUsuario = FactoryDAO.getUsuarioDAO();
    	Usuario user;
    	if (daoUsuario.listar().isEmpty()) {
    		System.out.println("** Agregando usuario Admin ");
    		// no hay usuarios en la BD
    		user = new Usuario();    		
    		user.setPerfil(Perfil.ADMINISTRADOR);
    		user.setActivo(true);
    		user.setEmail("admin@admin.com.ar");
    		user.setPassword("admin");    		
    		
    		daoUsuario.persistir(user);
    	}
    	else {
    		System.out.println("Admin ya existe");
    	}
    	
    	IBaseDAO<Estacion> daoEstacion = FactoryDAO.getEstacionDAO();
    	if (daoEstacion.listar().isEmpty()){
    		Estacion pzaSanMartin = new Estacion();
    		pzaSanMartin.setCantEstacionamientos(40);
    		pzaSanMartin.setCantBiciletas(10);
    		pzaSanMartin.setNombre("Plaza San Martín");
    		pzaSanMartin.setUbicacion(-34.918131995447354, -57.948323906781006);
    		pzaSanMartin.setEstado(EstadoEstacion.OPERATIVA);
    		daoEstacion.persistir(pzaSanMartin);
    		
    		Estacion pzaIslasMalvinas = new Estacion();
    		pzaIslasMalvinas.setCantEstacionamientos(40);
    		pzaIslasMalvinas.setCantBiciletas(10);
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

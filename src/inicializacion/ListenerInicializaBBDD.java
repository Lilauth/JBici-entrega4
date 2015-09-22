package inicializacion;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import dao.*;
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
    	
    	
    	
		System.out.println("***** BBDD Inicializada *****");
    }

	
    public void contextDestroyed(ServletContextEvent arg0) {
        // 
    }
	
}

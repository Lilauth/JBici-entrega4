package dao;

import model.Bicicleta;
import model.Denuncia;
import model.Estacion;
import model.HistorialBicicleta;
import model.PrestamoActual;
import model.PrestamoHistorico;
import model.Usuario;
import connection.Conexion;

public class FactoryDAO {
	

	public static IUsuarioDAO getUsuarioDAO() {
		return new UsuarioDAOImpl(Usuario.class, Conexion.getEntityManager());
	}
	
	public static IBaseDAO<Estacion> getEstacionDAO(){
		return new BaseDAOImpl<Estacion>(Estacion.class, Conexion.getEntityManager());
	}
	
	public static IBaseDAO<Bicicleta> getBicicletaDAO(){
		return new BaseDAOImpl<Bicicleta>(Bicicleta.class, Conexion.getEntityManager());
	}
	
	public static IBaseDAO<HistorialBicicleta> getHistorialBicicletaDAO(){
		return new BaseDAOImpl<HistorialBicicleta>(HistorialBicicleta.class, Conexion.getEntityManager());
	}
	
	public static IPrestamoActualDAO getPrestamoActualDAO(){
		return new PrestamoActualDAOImpl(PrestamoActual.class, Conexion.getEntityManager());
	}
	
	public static IBaseDAO<PrestamoHistorico> getPrestamoHistoricoDAO(){
		return new BaseDAOImpl<PrestamoHistorico>(PrestamoHistorico.class, Conexion.getEntityManager());
	}
	
	public static IBaseDAO<Denuncia> getDenunciaDAO(){
		return new BaseDAOImpl<Denuncia>(Denuncia.class, Conexion.getEntityManager());
	}

}

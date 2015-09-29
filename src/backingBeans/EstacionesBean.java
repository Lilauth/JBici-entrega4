package backingBeans;

import java.io.Serializable;
import java.util.List;
import dao.FactoryDAO;
import model.Estacion;


public class EstacionesBean  implements Serializable {

	private static final long serialVersionUID = 1L;

	public EstacionesBean() {}
	
	public List<Estacion> getListaEstaciones() {
		return FactoryDAO.getEstacionDAO().listar();
	}
	
}

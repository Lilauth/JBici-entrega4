package backingBeans;

import java.io.Serializable;
import java.util.List;

import dao.FactoryDAO;
import model.Bicicleta;

public class BicicletasBean  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BicicletasBean() {}
	
	public List<Bicicleta> getListaBicicletas() {
		return FactoryDAO.getBicicletaDAO().listar();
	}
	

}

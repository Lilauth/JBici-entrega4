package backingBeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.ArrayDataModel;
import javax.faces.model.DataModel;

import dao.FactoryDAO;
import model.PrestamoActual;

public class PrestamosBean implements Serializable{
	/**
	 * 
	 * Bean que se utiliza para listar
	 * las reservas de bicicletas activas para el usuario logueado
	 * 
	 * */
	private static final long serialVersionUID = 1L;
	private List<PrestamoActual> listaPrestamosActuales;
	private DataModel<PrestamoActual> prestamosActualesList;
	
	public PrestamosBean(){ }

	public List<PrestamoActual> getListaPrestamosActuales() {
		return listaPrestamosActuales;
	}

	public void setListaPrestamosActuales(List<PrestamoActual> listaPrestamosActuales) {
		this.listaPrestamosActuales = listaPrestamosActuales;
	}

	public DataModel<PrestamoActual> getPrestamosActualesList() {
		this.setListaPrestamosActuales(FactoryDAO.getPrestamoActualDAO().listar());		
		this.prestamosActualesList = new ArrayDataModel<PrestamoActual>(this.listaPrestamosActuales.toArray(new PrestamoActual[this.listaPrestamosActuales.size()]));
		return prestamosActualesList;
	}

	public void setPrestamosActualesList(DataModel<PrestamoActual> prestamosActualesList) {
		this.prestamosActualesList = prestamosActualesList;
	}
	
}

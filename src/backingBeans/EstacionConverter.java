package backingBeans;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import dao.FactoryDAO;
import model.Estacion;

@FacesConverter(forClass=Estacion.class)
public class EstacionConverter implements Converter{

	@Override
	public Estacion getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		return FactoryDAO.getEstacionDAO().buscaPorID(Integer.parseInt(arg2));
		
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		return Long.toString(((Estacion) arg2).getId());		 
	}

}

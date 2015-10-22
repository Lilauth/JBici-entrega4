package backingBeans;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import dao.FactoryDAO;
import model.Bicicleta;

@FacesConverter(forClass=Bicicleta.class)
public class BicicletaConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		return FactoryDAO.getBicicletaDAO().buscaPorID(Integer.parseInt(arg2));
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		return Long.toString(((Bicicleta) arg2).getId());		
	}

}

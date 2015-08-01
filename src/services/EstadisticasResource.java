package services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


@Path("/estadisticas")
public class EstadisticasResource {
	EstadisticasService estService;
	/**@Context
	UriInfo uriInfo;
	@Context
	Request request;	*/
	
	public EstadisticasResource(){
		this.estService = new EstadisticasService();
	}
	
	@GET
	@Path("/user")
	@Produces(MediaType.APPLICATION_JSON )
	@Consumes(MediaType.TEXT_PLAIN)
	public List<EstadisticasPares> estDeUsuario(@QueryParam("id") long idUsuario) {
		
		EstadisticasPares altas;
		EstadisticasPares bajas;
		EstadisticasPares modificaciones;
		List<EstadisticasPares> lista = new ArrayList<EstadisticasPares>();
		
		System.out.println(idUsuario);
		altas = new EstadisticasPares();
		altas.setOp("altas");
		altas.setCantidad(this.estService.altasDeUsuario(idUsuario));
		lista.add(altas);
		
		bajas = new EstadisticasPares();
		bajas.setOp("bajas");
		bajas.setCantidad(this.estService.bajasDeUsuario(idUsuario));
		lista.add(bajas);
		
		modificaciones = new EstadisticasPares();
		modificaciones.setOp("modificaciones");
		modificaciones.setCantidad(this.estService.modificacionesDeUsuario(idUsuario));
		lista.add(modificaciones);
		
	    return lista;
	  }
	
	@GET
	@Path("/bici")
	@Produces(MediaType.TEXT_HTML)
	public String bicy() {
	    return "bicy";
	  }
	
}

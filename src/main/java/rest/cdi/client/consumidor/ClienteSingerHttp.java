package rest.cdi.client.consumidor;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import rest.cdi.client.entidad.Singer;

public interface ClienteSingerHttp {

	@GET
	@Path("/{id}")
	@Consumes(value = MediaType.APPLICATION_JSON)
	Singer buscarId(@PathParam("id") Integer idSinger);

	@GET
	@Consumes(value = MediaType.APPLICATION_JSON)
	List<Singer> listar();
}

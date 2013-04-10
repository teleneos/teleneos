package id.co.bonet.itu.noc;

import id.co.bonet.itu.noc.service.TelecentreService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.meruvian.yama.persistence.EntityListWrapper;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
@Path("/telecentre")
public class TelecentreRest {

	@Inject
	private TelecentreService service;

	@POST
	@Path("")
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	public Response telecentre(Telecentre telecentre) {
		service.save(telecentre);
		return Response.status(200).build();
	}

	@GET
	@Path("")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public EntityListWrapper<Telecentre> getall() {
		return service.all(0, 0);
	}
}

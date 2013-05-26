package id.co.bonet.itu.noc.log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


@Path("/log")
public class LogService {
	
	@POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response modifyJson(File input) {
		try {
			FileInputStream inputStream = new FileInputStream(input);
			System.out.println("FILE : "+inputStream.read());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Response.ok().entity(input).build();
    }

}

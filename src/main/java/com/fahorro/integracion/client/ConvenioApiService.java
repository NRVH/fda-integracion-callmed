package com.fahorro.integracion.client;

import com.fahorro.integracion.dto.ConvenioResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "entidad-core-api")
@ApplicationScoped
public interface ConvenioApiService {
    @GET
    @Path("/convenios/{idConvenio}")
    @Produces("application/json")
    ConvenioResponseDTO getConvenio(@PathParam("idConvenio") String idConvenio);

    @GET
    @Path("/convenios/claveCliente/{claveClienteCallmed}")
    @Produces("application/json")
    Response getConvenioByClaveCliente(@PathParam("claveClienteCallmed") String claveClienteCallmed);
}

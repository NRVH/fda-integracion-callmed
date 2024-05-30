package com.fahorro.integracion.client;

import com.fahorro.integracion.dto.RecetaApiResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "entidad-core-api")
@ApplicationScoped
public interface RecetaApiService {
    @GET
    @Path("/recetas/{nur}")
    @Produces("application/json")
    RecetaApiResponseDTO getRecetaByNur(@PathParam("nur") String nur);
}

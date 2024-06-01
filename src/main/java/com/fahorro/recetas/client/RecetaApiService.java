package com.fahorro.recetas.client;

import com.fahorro.recetas.dto.RecetaApiResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "entidad-core-api")
@ApplicationScoped
public interface RecetaApiService {

    @GET
    @Path("/recetas/{nur}")
    @Produces("application/json")
    RecetaApiResponseDTO getRecetaByNur(@PathParam("nur") String nur);

    @POST
    @Path("/recetas")
    @Consumes("application/json")
    @Produces("application/json")
    Response guardarReceta(RecetaApiResponseDTO.EntidadDTO.DataJsonDTO receta);
}

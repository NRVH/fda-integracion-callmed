package com.fahorro.recetas.client;

import com.fahorro.recetas.dto.SucursalApiResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "entidad-core-api")
@ApplicationScoped
public interface SucursalApiService {

    @GET
    @Path("/sucursales/{numSuc}")
    @Produces("application/json")
    SucursalApiResponseDTO getSucursalById(@PathParam("numSuc") String numSuc);
}

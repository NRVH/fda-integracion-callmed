package com.fahorro.integracion.client;

import com.fahorro.integracion.dto.ClienteApiResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "entidad-core-api")
@ApplicationScoped
public interface ClienteApiService {
    @GET
    @Path("/clientes/{clientId}")
    @Produces("application/json")
    ClienteApiResponseDTO getClienteById(@PathParam("clientId") String clientId);

    @GET
    @Path("/clientes/numero/{numCliente}")
    @Produces("application/json")
    ClienteApiResponseDTO getClienteByNumCliente(@PathParam("numCliente") String clientId);
}

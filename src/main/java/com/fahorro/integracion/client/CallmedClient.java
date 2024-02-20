package com.fahorro.integracion.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/SUSEReceta.svc")
@RegisterRestClient(configKey="callmed-api")
public interface CallmedClient {

    @POST
    @Path("/GetToken")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    String login(String payload);

    @POST
    @Path("/GetClaveCliente")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    String claveCliente(String payload);

    @POST
    @Path("/GetValReceta")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    String valReceta(String payload);

    @POST
    @Path("/GetValMedicamento")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    String valMedicamento(String payload);
}

package com.fahorro.integracion.resources;

import com.fahorro.integracion.dto.response.ErrorResponse;
import com.fahorro.integracion.exception.CallmedException;
import com.fahorro.integracion.service.RecetaNurService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.jboss.logging.Logger;

@Path("")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CallmedResource {

    private static final Logger log = Logger.getLogger(CallmedResource.class);

    RecetaNurService recetaNurService;

    @Inject
    public CallmedResource(RecetaNurService recetaNurService){
        this.recetaNurService = recetaNurService;
    }

    @GET
    @Path("/receta/{nur}")
    @Operation(summary = "Obtiene la información de una receta",
            description = "Devuelve los datos de la receta basados en el NUR proporcionado")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Receta encontrada y procesada"),
            @APIResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @APIResponse(responseCode = "404", description = "Datos no encontrados"),
            @APIResponse(responseCode = "500", description = "Error interno del servidor"),
            @APIResponse(responseCode = "503", description = "Servicio Api Callmed no disponible"),
            @APIResponse(responseCode = "504", description = "Timeout de comunicación con Api Callmed")
    })
    public Response getReceta(@PathParam("nur") String nur, @QueryParam("codigoSucursal") String codigoSucursal) {
        try
        {
            return Response.ok().entity(recetaNurService.processRecetaNur(nur, codigoSucursal)).build();
        }
        catch (CallmedException e)
        {
            log.error(e.getMessage());
            return Response.status(e.getCodeError()).entity(new ErrorResponse(
                    false,
                    e.getCodeError(),
                    e.getMessage())).build();
        }
        catch (Exception e) {
            log.error("Error inesperado: ", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorResponse(
                    false,
                    500,
                    "Error inesperado al procesar receta")).build();
        }
    }

    @GET
    @Path("/test")
    @Operation(summary = "Verifica la disponibilidad del servicio",
            description = "Devuelve un mensaje que indica que el servicio está disponible")
    public Response test() {
        return Response.status(Response.Status.OK).entity(new ErrorResponse(
                true,
                200,
                "fda integracion callmed version 1.0.0 is available!")).build();
    }
}

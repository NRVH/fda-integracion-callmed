package com.fahorro.recetas.resources;

import com.fahorro.recetas.dto.request.RequestSurtirRecetaNur;
import com.fahorro.recetas.dto.response.ErrorResponse;
import com.fahorro.recetas.exception.CallmedException;
import com.fahorro.recetas.service.RecetaNurService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.jboss.logging.Logger;

@Path("/api")
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
    public Response recetaNur(@PathParam("nur") String nur, @QueryParam("codigoSucursal") String codigoSucursal) {
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

    @POST
    @Path("/receta/{nur}/surtir")
    @Operation(summary = "Surtir receta identificada por NUR",
            description = "Devuelve los datos de la receta basados en el NUR proporcionado")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Receta surtida"),
            @APIResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @APIResponse(responseCode = "404", description = "Datos no encontrados"),
            @APIResponse(responseCode = "500", description = "Error interno del servidor"),
            @APIResponse(responseCode = "503", description = "Servicio Api Callmed no disponible"),
            @APIResponse(responseCode = "504", description = "Timeout de comunicación con Api Callmed")
    })
    public Response surtirRecetaNur(@PathParam("nur") String nur, @RequestBody RequestSurtirRecetaNur recetaNur)
    {
        try
        {
            recetaNur.setNur(nur);
            return Response.ok().entity(recetaNurService.processSurtirRecetaNur(recetaNur)).build();
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
                    "Error inesperado al procesar surtir receta nur")).build();
        }
    }

    @POST
    @Path("/receta/{nur}/surtir/{numeroCliente}")
    @Operation(summary = "Surtir receta identificada por NUR",
            description = "Devuelve los datos de la receta basados en el NUR proporcionado")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Receta surtida"),
            @APIResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @APIResponse(responseCode = "404", description = "Datos no encontrados"),
            @APIResponse(responseCode = "500", description = "Error interno del servidor"),
            @APIResponse(responseCode = "503", description = "Servicio Api Callmed no disponible"),
            @APIResponse(responseCode = "504", description = "Timeout de comunicación con Api Callmed")
    })
    public Response surtirRecetaNurNumeroCliente(@PathParam("nur") String nur,
                                    @PathParam("numeroCliente") String numeroCliente,
                                    @RequestBody RequestSurtirRecetaNur recetaNur)
    {
        try
        {
            recetaNur.setNur(nur);
            recetaNur.setNumCliente(Integer.valueOf(numeroCliente));
            return Response.ok().entity(recetaNurService.processSurtirRecetaNurNumeroCliente(recetaNur)).build();
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
                    "Error inesperado al procesar surtir receta nur numero cliente")).build();
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
                "Fahorro recetas version 1.0.0 is available!")).build();
    }
}

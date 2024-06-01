package com.fahorro.recetas.client;

import com.fahorro.recetas.dto.ConvenioProductoResponseDTO;
import com.fahorro.recetas.dto.ProductoApiResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "entidad-core-api")
@ApplicationScoped
public interface ProductoApiService {

    @GET
    @Path("/productos/{ean}")
    @Produces("application/json")
    ProductoApiResponseDTO getProductoByEAN(@PathParam("ean") String ean);

    @GET
    @Path("/convenio-producto/{idConvenio}/{productCode}")
    @Produces("application/json")
    ConvenioProductoResponseDTO getConvenioProducto(@PathParam("idConvenio") String idConvenio, @PathParam("productCode") String productCode);

    @GET
    @Path("/convenio-producto/convenio/{idConvenio}")
    @Produces("application/json")
    ConvenioProductoResponseDTO getProductoByConvenio(@PathParam("idConvenio") String idConvenio);
}

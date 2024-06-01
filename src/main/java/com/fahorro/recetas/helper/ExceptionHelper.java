package com.fahorro.recetas.helper;

import com.fahorro.recetas.exception.CallmedException;
import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

public class ExceptionHelper {

    private static final Logger log = Logger.getLogger(RespuestaGeneralHelper.class);

    public static void handleException(String nur, Exception e) throws CallmedException {
        if (e instanceof ProcessingException) {
            Throwable cause = e.getCause();
            if (cause instanceof SocketTimeoutException) {
                log.error("Timeout al comunicarse con Api Winstondata para NUR: " + nur, e);
                throw new CallmedException("Timeout de comunicación con Api Winstondata", 504, e); // 504 Gateway Timeout
            } else if (cause instanceof ConnectException) {
                log.error("No se pudo conectar con Api Winstondata para NUR: " + nur, e);
                throw new CallmedException("Servicio Api Winstondata no disponible", 503, e); // 503 Service Unavailable
            } else {
                log.error("Error de procesamiento al comunicarse con Api Winstondata para NUR: " + nur, e);
                throw new CallmedException("Error de comunicación con Api Winstondata", 500, e); // 500 Internal Server Error
            }
        } else if (e instanceof WebApplicationException w) {
            Response response = w.getResponse();
            Response.StatusType statusInfo = response.getStatusInfo();
            log.error("Respuesta HTTP " + statusInfo.getStatusCode() + ": " + statusInfo.getReasonPhrase() +
                    " al comunicarse con Api Winstondata para NUR: " + nur, e);
            throw new CallmedException("Error HTTP " + statusInfo.getStatusCode() + " desde Callmed", response.getStatus(), e);
        } else {
            log.error("Error inesperado al comunicarse con Callmed para NUR: " + nur, e);
            throw new CallmedException("Error inesperado en Callmed", 500, e); // 500 Internal Server Error
        }
    }
}

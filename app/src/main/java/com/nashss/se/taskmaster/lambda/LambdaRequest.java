package com.nashss.se.taskmaster.lambda;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

import static com.nashss.se.taskmaster.utils.NullUtils.ifNull;

public class LambdaRequest<T> extends APIGatewayProxyRequestEvent {
    protected final ObjectMapper MAPPER = new ObjectMapper();
    protected final Logger log = LogManager.getLogger();

    /**
     * Dserialize a T (aka 'requestClass`) from the body of the request.
     * @param requestClass The type that should be created from the body of this LambdaRequest
     * @return A new instance of T that contains data from the request body
     */
    public T fromBody(Class<T> requestClass) {
        log.info("Attempting to deserialize object from request body ({}).", requestClass.getSimpleName());
        try {
            MAPPER.registerModule(new JavaTimeModule());
            //MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            return MAPPER.readValue(super.getBody(), requestClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(
                    String.format("Unable to deserialize object from request body (%s).", requestClass.getSimpleName()),
                    e);
        }
    }

    /**
     * Use the given converter to create an instance of T from the request's query string.
     * @param converter Contains the conversion code
     * @return A instance of T that contains data from the request's query string
     */
    public T fromQuery(Function<Map<String, String>, T> converter) {
        log.info("Attempting to retrieve values from query string parameters.");
        Map<String, String> query = ifNull(super.getQueryStringParameters(), Map.of());
        return converter.apply(query);
    }

    /**
     * Use the given converter to create an instance of T from the request's path parameters.
     * @param converter Contains the conversion code
     * @return A instance of T that contains data from the request's path parameters
     */
    public T fromPath(Function<Map<String, String>, T> converter) {
        log.info("Attempting to retrieve values from path parameters.");
        Map<String, String> path = ifNull(super.getPathParameters(), Map.of());
        return converter.apply(path);
    }

    /**
     * Use the given converter to create an instance of T from the request's path parameters
     * and query string parameters.
     * @param converter Contains the conversion code
     * @return A instance of T that contains data from the request's path parameters
    */
    public T fromPathAndQuery(BiFunction<Map<String, String>, Map<String, String>, T> converter) {
        log.info("Attempting to retrieve values from path and query string parameters.");
        Map<String, String> path = ifNull(super.getPathParameters(), Map.of());
        Map<String, String> query = ifNull(super.getQueryStringParameters(), Map.of());
        return converter.apply(path, query);
    }
}

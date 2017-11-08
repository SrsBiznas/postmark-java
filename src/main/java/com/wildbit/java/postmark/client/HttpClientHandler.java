package com.wildbit.java.postmark.client;

import com.wildbit.java.postmark.client.data.DataHandler;
import com.wildbit.java.postmark.client.exception.*;

import javax.ws.rs.core.MultivaluedHashMap;
import java.io.IOException;

/**
 * Client class acts as handler between HTTP requests handler class and class which provides endpoints to call.
 * This class provides correct data for both sides and acts as controller.
 */
public class HttpClientHandler {

    private final HttpClient httpClient;
    protected final DataHandler dataHandler;
    private boolean secureConnection = true;

    protected HttpClientHandler(MultivaluedHashMap<String,Object> headers) {
        this.dataHandler = new DataHandler(true);
        httpClient = new HttpClient(headers);
    }

    protected HttpClientHandler(MultivaluedHashMap<String,Object> headers, boolean secureConnection) {
        this(headers);
        this.secureConnection = secureConnection;
    }
    
    /**
     *
     * Execute HTTP requests with no data sending required.
     *
     * @see #execute(HttpClient.REQUEST_TYPES, String, Object) for details
     */
    protected String execute(HttpClient.REQUEST_TYPES request_type, String url) throws PostmarkException, IOException {
        return execute(request_type, url, null);
    }

    /**
     *
     * Execute HTTP requests
     *
     * @param request_type HTTP request type
     * @param url HTTP request URL
     * @return HTTP response message
     * @throws PostmarkException Errors thrown by invalid or unhandled requests made to Postmark
     * @throws IOException Errors thrown by Data Handler
     *
     * @see HttpClient for details about HTTP request execution.
     */
    protected String execute(HttpClient.REQUEST_TYPES request_type, String url, Object data) throws PostmarkException, IOException {
        HttpClient.ClientResponse response = httpClient.execute(request_type, getSecureUrl(url), dataHandler.toJson(data));
        validateResponse(response);
        return response.getMessage();
    }

    public void setSecureConnection(boolean secureConnection) {
        this.secureConnection = secureConnection;
    }

    /**
     * @return HTTP client which processes HTTP requests
     */
    public HttpClient getHttpClient() {
        return httpClient;
    }

    /**
     * @return DataHandler which processes HTTP requests sent, and HTTP request responses
     */
    public DataHandler getDataHandler() { return dataHandler; }
    
    private String getSecureUrl(String url) {
        String urlPrefix = this.secureConnection ? "https://" : "http://";
        return urlPrefix + url;
    }

    /**
     *
     * Validates HTTP request responses.
     *
     * @param response HTTP request response
     * @throws PostmarkException in case invalid HTTP response is returned.
     */
    private void validateResponse(HttpClient.ClientResponse response) throws PostmarkException, IOException {
        int code = response.getCode();
        String message = response.getMessage();

        switch (code) {

            case 200: break;

            case 401:
                throw new InvalidAPIKeyException(dataHandler.formatErrorMessage(message));

            case 422:
                throw new InvalidMessageException(dataHandler.formatErrorMessage(message));

            case 500:
                throw new InternalServerException(dataHandler.formatErrorMessage(message));

            default:
                throw new UnknownException(message);

        }
    }

}
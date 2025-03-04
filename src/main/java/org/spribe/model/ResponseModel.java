package org.spribe.model;

import io.restassured.http.Headers;
import io.restassured.response.Response;
import lombok.Getter;

@Getter
public class ResponseModel<T> {
    private final Class<T> bodyType;
    private final Response raw;

    public ResponseModel(Response response, Class<T> bodyType) {
        this.raw = response;
        this.bodyType = bodyType;
    }

    public Headers getHeaders() {
        return raw.getHeaders();
    }

    public int getStatusCode() {
        return raw.statusCode();
    }

    public T getBody() {
        return raw.as(bodyType);
    }
}

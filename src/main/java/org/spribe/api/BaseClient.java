package org.spribe.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;


public abstract class BaseClient {

    protected Response post(String path, Object body) {
       return given()
                .contentType(ContentType.JSON)
                .body(body)
                .post(path);
    }

    protected Response get(String path, Map<String, Object> queryParams, Map<String, Object> pathParams) {
        return given()
                .contentType(ContentType.JSON)
                .queryParams(queryParams)
                .pathParams(pathParams)
                .get(path);
    }

    protected Response get(String path) {
        return get(path, new HashMap<>(), new HashMap<>());
    }

    protected Response patch(String path, Object body, Map<String, Object> pathParams) {
      return given()
                .contentType(ContentType.JSON)
                .pathParams(pathParams)
                .body(body)
                .patch(path);
    }

    protected Response patch(String path, Object body) {
        return patch(path, body, new HashMap<>());
    }

    protected Response delete(String path, Object body, Map<String, Object> pathParams) {
      return given()
                .contentType(ContentType.JSON)
                .pathParams(pathParams)
                .body(body)
                .delete(path);
    }
}

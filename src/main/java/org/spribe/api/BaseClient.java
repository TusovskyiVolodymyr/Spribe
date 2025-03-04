package org.spribe.api;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public abstract class BaseController {

    public Response post(String path, Object body, Object... params) {
       return given()
                .body(body)
                .post(path, params);
    }
}

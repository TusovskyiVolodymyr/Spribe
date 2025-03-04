package org.spribe.logging;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import lombok.extern.log4j.Log4j2;

import java.util.Optional;

@Log4j2
public class RestAssuredLogger implements Filter{

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpecification, FilterContext context) {

        String requestLog = "\n===== REQUEST =====\n" +
                "Request method:\t" + requestSpec.getMethod() + "\n" +
                "Request URI:\t" + requestSpec.getURI() + "\n" +
                "Headers:\t" + requestSpec.getHeaders() + "\n" +
                "Body:\t" + Optional.ofNullable(requestSpec.getBody()).orElse("{}") + "\n";

        log.info(requestLog);

        Response response = context.next(requestSpec, responseSpecification);

        String responseLog = "\n===== RESPONSE =====\n" +
                "Response status code:\t" + response.getStatusCode() + "\n" +
                "Headers:\t" + response.getHeaders() + "\n" +
                "Response body:\n" + response.getBody().asPrettyString() + "\n";

        log.info(responseLog);

        return response;
    }
}

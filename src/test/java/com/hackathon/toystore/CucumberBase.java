/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hackathon.toystore;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.response.MockMvcResponse;
import io.restassured.module.mockmvc.response.ValidatableMockMvcResponse;
import io.restassured.module.mockmvc.specification.MockMvcRequestAsyncSender;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import java.util.Map;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.FilterChainProxy;

@ContextConfiguration(classes = AircraftStoreApplication.class)
@WebAppConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class CucumberBase {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Autowired
    private WebApplicationContext context;
//    @Autowired
//    private FilterChainProxy filterChain;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private MockMvcRequestSpecification requestSpecification;
    private MockMvcRequestAsyncSender requestAsyncSender;
    private MockMvcResponse response;
    private ValidatableMockMvcResponse validatableResponse;

    @Test
    public void souldDoNothing() {
    }

    private MockMvcRequestSpecification given() {
        if (this.requestSpecification == null) {
            final MockMvc mvc = webAppContextSetup(context)/*.addFilters(filterChain)*/.build();
            this.requestSpecification = RestAssuredMockMvc.given().mockMvc(mvc).accept(MediaTypes.HAL_JSON_VALUE);

            this.requestAsyncSender = null;
            this.response = null;
            //SecurityContextHolder.clearContext();
        }
        return this.requestSpecification;
    }

    private MockMvcRequestAsyncSender when() {
        if (requestAsyncSender == null) {
            this.requestAsyncSender = given().when();
        }
        return this.requestAsyncSender;
    }

    private ValidatableMockMvcResponse then() {
        if (validatableResponse == null) {
            this.validatableResponse = response.then();
        }
        return this.validatableResponse;
    }

    @Given("^the client init a call$")
    public void given_client_init_a_call() throws Throwable {
        given();
    }

    @When("^the client calls (.+)$")
    public void when_client_calls(final String uri) throws Throwable {
        this.response = when().get(uri);
    }

    @Then("^the client receives status code of (\\d+)$")
    public void the_client_receives_status_code_of(final Integer statusCode) throws Throwable {
        then().statusCode(is(statusCode));
    }

    @And("^the client receives content type (.+)$")
    public void the_client_receives_content_type(final String contentType) throws Throwable {
        then().contentType(contentType);
    }

    @And("^the client receives body like a json file (.+)$")
    public void the_client_receives_body_like_a_json_file(final String jsonName) throws Throwable {
        final String jsonFileName = "response/" + jsonName + ".json";
        then().body(matchesJsonSchemaInClasspath(jsonFileName));

        final String json = Resources.toString(Resources.getResource(jsonFileName), Charsets.UTF_8);

        final Map map = then().extract().body().as(Map.class);
        final String result = objectMapper.writeValueAsString(map);
        JSONAssert.assertEquals(json, result, false);
    }

}

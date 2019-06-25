package com.services.pm.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.services.pm.product.model.Product;
import com.services.pm.product.model.enumeration.RatingValues;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {

    public static final String API_V1_ROOT_RELATIVE_PATH = "/api/v1";

    TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Before
    public void setup(){
       restTemplate = new TestRestTemplate();
    }

    @Test
    public void testProductList() throws JsonProcessingException, JSONException {

        ResponseEntity<ArrayList> response = restTemplate.getForEntity(String.join("/", getUrlWithPort(), API_V1_ROOT_RELATIVE_PATH,"/products"), ArrayList.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

        ObjectMapper mapper = new ObjectMapper();

        String resultJson = mapper
                .writer()
                .writeValueAsString(response.getBody());

        String expectedJson = "[{\"id\":1,\"name\":\"Iphone-5\",\"price\":500.0,\"rating\":3},{\"id\":2,\"name\":\"Samsung-S8\",\"price\":600.0,\"rating\":4}]";

        JSONAssert.assertEquals(expectedJson, resultJson, false);
    }

    @Test
    public void testSaveProduct() throws URISyntaxException, JsonProcessingException {
        Product product = new Product.ProductBuilder()
                .withName("Iphone 5")
                .withPrice(500)
                .withRating(RatingValues.FOUR.getValue())
                .build();

        String url = String.join("/", getUrlWithPort(), API_V1_ROOT_RELATIVE_PATH, "/products");
        URI uri = new URI(url);

        /*final ObjectMapper mapper = new ObjectMapper();
        final HttpEntity requestEntity = new HttpEntity(mapper.writeValueAsString(product));*/

        ResponseEntity<Product> response = restTemplate.postForEntity(uri, product, Product.class);

        assertThat(HttpStatus.CREATED, equalTo(response.getStatusCode()));

        //Product savedProduct = response.getBody();

        //assertThat(savedProduct, notNullValue());

        //assertThat(savedProduct.getName(), equalTo("Iphone 5"));
    }

    String getUrlWithPort(){
        return String.format("https://localhost:%d", port);
    }
}

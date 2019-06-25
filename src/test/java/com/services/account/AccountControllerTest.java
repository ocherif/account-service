package com.services.account;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.services.account.model.Account;
import org.hamcrest.Matchers;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AccountControllerTest {

    public static final String API_V1_ROOT_RELATIVE_PATH = "/api/v1";

    TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Before
    public void setup(){
        restTemplate = new TestRestTemplate();
    }

    @Test
    public void testAccountList() throws JsonProcessingException, JSONException {

        ResponseEntity<ArrayList> response = restTemplate.getForEntity(String.join("/", getUrlWithPort(), API_V1_ROOT_RELATIVE_PATH,"/accounts"), ArrayList.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

        ObjectMapper mapper = new ObjectMapper();

        String resultJson = mapper
                .writer()
                .writeValueAsString(response.getBody());

        String expectedJson = "[{\"id\":1,\"name\":\"Toto\",\"position\":80000.0},{\"id\":2,\"name\":\"Tata\",\"position\":600000.0}]";

        System.out.println(resultJson);

        JSONAssert.assertEquals(expectedJson, resultJson, false);
    }

    @Test
    public void testSaveProduct() throws URISyntaxException, JsonProcessingException {
        Account account = new Account.AccountBuilder()
                .withName("Titi")
                .withPosition(new BigDecimal(45000))
                .build();

        String url = String.join("/", getUrlWithPort(), API_V1_ROOT_RELATIVE_PATH, "/accounts");
        URI uri = new URI(url);

        ResponseEntity<Account> response = restTemplate.postForEntity(uri, account, Account.class);

        assertThat(HttpStatus.CREATED, equalTo(response.getStatusCode()));
    }

    @Test
    public void testCheckCustomerPosition(){
        Map<String, Object> params = new HashMap<>();
        params.put("id", 1L);
        params.put("creditAmount", new BigDecimal(5000));
        params.put("creditTermAmount", new BigDecimal(200));

        ResponseEntity<Boolean> response = restTemplate.getForEntity(String.join("/", getUrlWithPort(), API_V1_ROOT_RELATIVE_PATH,"accounts/verify/{id}/{creditAmount}/{creditTermAmount}"), Boolean.class, params);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

        assertThat(response.getBody(), Matchers.is(true));
    }

    String getUrlWithPort(){
        return String.format("https://localhost:%d", port);
    }
}

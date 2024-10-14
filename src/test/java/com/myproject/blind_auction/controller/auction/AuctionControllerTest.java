package com.myproject.blind_auction.controller.auction;

import com.myproject.blind_auction.BlindAuctionApplication;
import com.myproject.blind_auction.dto.AuctionRequest;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = BlindAuctionApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuctionControllerTest  {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    private String createURLWithPort(String uri) {
        return "http://localhost:"+ port + uri;
    }

    @Test
    public void testBaseURI() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/auctions"),
                HttpMethod.GET, entity, String.class);
        String actual = response.getBody().toString();
        String expected = "Greetings from Auctions team!";
        assertTrue(actual.equals(expected));
    }

    @Test
    public void testAuctionCreate() {
        AuctionRequest auctionRequest = new AuctionRequest();
        auctionRequest.setDescription("Apple");
        auctionRequest.setStartingPrice(BigDecimal.valueOf(10.0));

        HttpEntity<AuctionRequest> entity = new HttpEntity<>(auctionRequest,headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/auctions/create-auction"),
                HttpMethod.POST, entity, String.class);
        String actual = response.getBody().toString();
        System.out.println(actual);
        assertTrue(actual.contains("Apple"));
    }





}

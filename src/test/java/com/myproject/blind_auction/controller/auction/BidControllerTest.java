package com.myproject.blind_auction.controller.auction;

import com.myproject.blind_auction.BlindAuctionApplication;
import com.myproject.blind_auction.dto.AuctionRequest;
import com.myproject.blind_auction.dto.BidRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = BlindAuctionApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BidControllerTest {
    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    private String createURLWithPort(String uri) {
        return "http://localhost:"+ port + uri;
    }


    @Test
    public void testPlaceBid() {

        AuctionRequest auctionRequest = new AuctionRequest();
        auctionRequest.setDescription("Apple");
        auctionRequest.setStartingPrice(BigDecimal.valueOf(10.0));

        HttpEntity<AuctionRequest> entity = new HttpEntity<>(auctionRequest,headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/auctions/create-auction"),
                HttpMethod.POST, entity, String.class);
        String actual = response.getBody().toString();



        BidRequest bidRequest = new BidRequest();
        bidRequest.setBidPrice(BigDecimal.valueOf(15.0));
        HttpEntity<BidRequest> entity1 = new HttpEntity<>(bidRequest,headers);
        String tempURI = "/auctions/"+ 2 + "/place-bid";

        ResponseEntity<String> response1 = restTemplate.exchange(
                createURLWithPort(tempURI),
                HttpMethod.POST, entity1, String.class);

        String actual1 = response1.getBody().toString();
        assertTrue(actual1.contains("15.0"));
    }
}

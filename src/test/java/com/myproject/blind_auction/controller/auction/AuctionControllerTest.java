package com.myproject.blind_auction.controller.auction;

import com.myproject.blind_auction.BlindAuctionApplication;
import com.myproject.blind_auction.dto.AuctionRequest;
import com.myproject.blind_auction.dto.BidRequest;
import com.myproject.blind_auction.dto.ConcludeAuctionRequest;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.springframework.security.oauth2.jwt.Jwt;

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
        assertTrue(actual.contains("Apple"));
    }

    @Test
    public void testConcludeAuction() {

        //Create Auction
        AuctionRequest auctionRequest = new AuctionRequest();
        auctionRequest.setDescription("Apple");
        auctionRequest.setStartingPrice(BigDecimal.valueOf(10.0));

        HttpEntity<AuctionRequest> entity = new HttpEntity<>(auctionRequest,headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/auctions/create-auction"),
                HttpMethod.POST, entity, String.class);
        String actual = response.getBody().toString();


        //Place Bid-1
        BidRequest bidRequest = new BidRequest();
        bidRequest.setBidPrice(BigDecimal.valueOf(15.0));
        HttpEntity<BidRequest> entity1 = new HttpEntity<>(bidRequest,headers);
        String tempURI = "/auctions/"+ 3 + "/place-bid";

        ResponseEntity<String> response1 = restTemplate.exchange(
                createURLWithPort(tempURI),
                HttpMethod.POST, entity1, String.class);
        String actual1 = response1.getBody().toString();


        //Place Bid-2
        BidRequest bidRequest1 = new BidRequest();
        bidRequest1.setBidPrice(BigDecimal.valueOf(12.0));
        HttpEntity<BidRequest> entity2 = new HttpEntity<>(bidRequest1,headers);
        String tempURI2 = "/auctions/"+ 3 + "/place-bid";

        ResponseEntity<String> response2 = restTemplate.exchange(
                createURLWithPort(tempURI2),
                HttpMethod.POST, entity2, String.class);

        String actual2 = response2.getBody().toString();


        //ConcludeAuction
        ConcludeAuctionRequest concludeAuctionRequest = new ConcludeAuctionRequest();
        concludeAuctionRequest.setAuctionId(3);
        HttpEntity<ConcludeAuctionRequest> entity3 = new HttpEntity<>(concludeAuctionRequest,headers);
        String tempURI3 = "/auctions/conclude-auction";

        ResponseEntity<String> response3 = restTemplate.exchange(
                createURLWithPort(tempURI3),
                HttpMethod.POST, entity3, String.class);

        String actual3 = response3.getBody().toString();
        assertTrue(actual3.contains("SOLD"));
    }




}

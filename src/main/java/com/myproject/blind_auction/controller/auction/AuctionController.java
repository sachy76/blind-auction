package com.myproject.blind_auction.controller.auction;

import com.myproject.blind_auction.dto.BidRequest;
import com.myproject.blind_auction.dto.ConcludeAuctionRequest;
import com.myproject.blind_auction.model.auction.Bid;
import com.myproject.blind_auction.repository.AuctionRepository;
import com.myproject.blind_auction.service.impl.AuctionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.myproject.blind_auction.service.interfaces.AuctionService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import com.myproject.blind_auction.model.auction.Bidding;
import com.myproject.blind_auction.dto.BiddingRequest;
import org.springframework.web.bind.annotation.GetMapping;
import com.myproject.blind_auction.model.auction.Auction;

import java.util.List;


@RestController
//@RequestMapping("/auctions")
@RequiredArgsConstructor
public class AuctionController {

    @Autowired
    AuctionService auctionService;

    @GetMapping("/auctions")
    public String index(@AuthenticationPrincipal Jwt jwt ) {
        return "Hello "+ jwt.getSubject() +". Greetings from Auctions team!";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/auctions/create-auction", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Bidding> createBiddingAuction(@RequestBody BiddingRequest biddingRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body((Bidding) auctionService.createAuction(biddingRequest));
    }

    @GetMapping("/auctions/get-all-auctions")
    public List<Auction> getAllAuctions() {
        return auctionService.getAllAuctions();
    }

    @RequestMapping(method = RequestMethod.POST, path="/auctions/conclude-auction", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Auction> concludeAuction (@RequestBody ConcludeAuctionRequest concludeAuctionRequest) {
        return auctionService.concludeAuction(concludeAuctionRequest);
    }

}

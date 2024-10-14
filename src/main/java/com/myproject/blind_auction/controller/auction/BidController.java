package com.myproject.blind_auction.controller.auction;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.myproject.blind_auction.model.auction.Bid;
import org.springframework.http.ResponseEntity;
import com.myproject.blind_auction.dto.BidRequest;
import com.myproject.blind_auction.service.interfaces.BidService;

@RestController
@RequiredArgsConstructor
public class BidController {

    @Autowired
    private BidService bidService;

    @RequestMapping(method = RequestMethod.POST, path = "/auctions/{auctionId}/place-bid", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Bid> createBid (@PathVariable Long auctionId,@RequestBody BidRequest bidRequest){
        return new ResponseEntity<Bid>(bidService.createBid(bidRequest, auctionId), HttpStatus.CREATED);

    }


}

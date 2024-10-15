package com.myproject.blind_auction.controller.auction;

import com.myproject.blind_auction.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
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
    public ResponseEntity<Bid> createBid (@AuthenticationPrincipal Jwt jwt, @PathVariable Long auctionId, @RequestBody BidRequest bidRequest){
        User user = new User();
        user.setUserName(jwt.getSubject().toString());
        return new ResponseEntity<Bid>(bidService.createBid(user, bidRequest, auctionId), HttpStatus.CREATED);

    }


}

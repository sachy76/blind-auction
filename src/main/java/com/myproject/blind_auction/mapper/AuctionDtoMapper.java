package com.myproject.blind_auction.mapper;

import com.myproject.blind_auction.model.auction.Auction;
import com.myproject.blind_auction.dto.AuctionRequest;
import com.myproject.blind_auction.dto.BiddingRequest;
import com.myproject.blind_auction.model.auction.Bidding;

public class AuctionDtoMapper {

    private AuctionDtoMapper() {
    }

    public static Auction mapAuctionRequestToAuction(AuctionRequest auctionRequest) {
        BiddingRequest biddingRequest = (BiddingRequest) auctionRequest;
        return Bidding.builder()
                .description(biddingRequest.getDescription())
                .startingPrice(biddingRequest.getStartingPrice())
                .build();
    }
}

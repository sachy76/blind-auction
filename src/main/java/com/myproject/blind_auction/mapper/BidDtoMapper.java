package com.myproject.blind_auction.mapper;

import com.myproject.blind_auction.dto.BidRequest;
import com.myproject.blind_auction.model.auction.Bid;

public class BidDtoMapper {
    private BidDtoMapper() {
    }

    public static BidRequest mapToBidRequest(Bid bid){
        return BidRequest.builder()
                .bidPrice(bid.getBidPrice())
                .build();
    }

    public static Bid mapToBid(BidRequest bidRequest){
        return Bid.builder()
                .bidPrice(bidRequest.getBidPrice())
                .build();
    }

}

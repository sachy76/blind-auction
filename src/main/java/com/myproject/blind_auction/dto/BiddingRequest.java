package com.myproject.blind_auction.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;
import java.math.BigDecimal;


@Data
@NoArgsConstructor
public class BiddingRequest extends AuctionRequest {

    @Builder
    public BiddingRequest(String description, BigDecimal startingPrice) {
        super(description, startingPrice);
    }
}

package com.myproject.blind_auction.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuctionRequest {
    private String description;
    private BigDecimal startingPrice;
}

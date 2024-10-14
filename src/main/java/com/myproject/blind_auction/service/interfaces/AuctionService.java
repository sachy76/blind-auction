package com.myproject.blind_auction.service.interfaces;

import com.myproject.blind_auction.dto.AuctionRequest;
import com.myproject.blind_auction.model.auction.Auction;

import java.util.List;

public interface AuctionService {
    Auction createAuction(AuctionRequest auctionRequest);
    List<Auction> getAllAuctions();
}

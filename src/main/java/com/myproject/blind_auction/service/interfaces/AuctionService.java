package com.myproject.blind_auction.service.interfaces;

import com.myproject.blind_auction.dto.AuctionRequest;
import com.myproject.blind_auction.dto.ConcludeAuctionRequest;
import com.myproject.blind_auction.dto.User;
import com.myproject.blind_auction.model.auction.Auction;

import java.util.List;

public interface AuctionService {
    Auction createAuction(User user, AuctionRequest auctionRequest);

    List<Auction> getAllAuctions(User user);

    List<Auction> concludeAuction(User user, ConcludeAuctionRequest concludeAuctionRequest);

}

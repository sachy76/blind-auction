package com.myproject.blind_auction.service.interfaces;

import com.myproject.blind_auction.dto.User;
import com.myproject.blind_auction.model.auction.Bid;
import com.myproject.blind_auction.dto.BidRequest;

public interface BidService {
    public Bid createBid(User user, BidRequest bidRequest, Long auctionId);
}

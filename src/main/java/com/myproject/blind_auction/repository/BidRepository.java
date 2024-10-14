package com.myproject.blind_auction.repository;

import com.myproject.blind_auction.model.auction.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BidRepository extends JpaRepository<Bid, Long> {

    List<Bid> findByBiddingAuctionId(Long biddingId);
}

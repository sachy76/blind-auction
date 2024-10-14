package com.myproject.blind_auction.repository;

import org.springframework.stereotype.Repository;
import com.myproject.blind_auction.model.auction.Auction;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Repository
public interface AuctionRepository extends PagingAndSortingRepository<Auction, Long>,
        JpaSpecificationExecutor<Auction>, JpaRepository<Auction, Long> {

}

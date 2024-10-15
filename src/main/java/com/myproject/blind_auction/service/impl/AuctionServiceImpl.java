package com.myproject.blind_auction.service.impl;

import com.myproject.blind_auction.dto.ConcludeAuctionRequest;
import com.myproject.blind_auction.dto.User;
import com.myproject.blind_auction.exceptions.NotAllowedException;
import com.myproject.blind_auction.exceptions.NotFoundException;
import com.myproject.blind_auction.model.auction.Auction;
import com.myproject.blind_auction.model.auction.Bid;
import com.myproject.blind_auction.model.auction.Bidding;
import com.myproject.blind_auction.repository.BidRepository;
import com.myproject.blind_auction.service.interfaces.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.myproject.blind_auction.repository.AuctionRepository;
import com.myproject.blind_auction.mapper.AuctionDtoMapper;
import com.myproject.blind_auction.dto.AuctionRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class AuctionServiceImpl implements AuctionService {

    @Autowired
    private final AuctionRepository auctionRepository;

    @Autowired
    private final BidRepository bidRepository;

    public Auction createAuction(User user, AuctionRequest auctionRequest) {
        Auction auction = AuctionDtoMapper.mapAuctionRequestToAuction(auctionRequest);
        if (!user.getUserName().equals("seller-1")){
            throw new NotAllowedException(user.getUserName() + " is not authorised to create auctions");
        }
        auction.setAuctionStatus("OPEN");
        return auctionRepository.save(auction);
    }

    @Transactional(readOnly = true)
    public List<Auction> getAllAuctions(User user) {
        return auctionRepository.findAll();
    }

    @Transactional
    public List<Auction> concludeAuction(User user, ConcludeAuctionRequest concludeAuctionRequest) {
        if (!user.getUserName().equals("seller-1")){
            throw new NotAllowedException(user.getUserName() + " is not authorised to create auctions");
        }
        long auctionId = concludeAuctionRequest.getAuctionId();
        Bidding bidding = (Bidding) auctionRepository.findById(auctionId).orElseThrow(
                () -> new NotFoundException(
                        "Auction with id " + auctionId + " not found"));
        List<Bid> bids = bidding.getBids();
        Bid bid = bid = bids.getFirst();
        for (Bid value : bids) {
            if (value.getBidPrice().compareTo(bid.getBidPrice()) > 0) {
                bid = value;
            }
        }
        bid.setBidWinner(true);
        bidRepository.save(bid);
        Auction auction = auctionRepository.findById(auctionId).orElseThrow(
                () -> new NotFoundException(
                        "Auction with id " + auctionId + " not found"));
        auction.setAuctionStatus("SOLD");
        auctionRepository.save(auction);

        return auctionRepository.findAll();
    }
}

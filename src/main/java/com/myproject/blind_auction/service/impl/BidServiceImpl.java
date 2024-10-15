package com.myproject.blind_auction.service.impl;

import com.myproject.blind_auction.dto.BidRequest;
import com.myproject.blind_auction.dto.User;
import com.myproject.blind_auction.exceptions.IncorrectPriceException;
import com.myproject.blind_auction.exceptions.NotAllowedException;
import com.myproject.blind_auction.exceptions.NotFoundException;
import com.myproject.blind_auction.model.auction.Bid;
import com.myproject.blind_auction.model.auction.Bidding;
import com.myproject.blind_auction.repository.AuctionRepository;
import com.myproject.blind_auction.repository.BidRepository;
import com.myproject.blind_auction.service.interfaces.BidService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.myproject.blind_auction.mapper.BidDtoMapper;

@Service
@RequiredArgsConstructor
@Transactional
public class BidServiceImpl implements BidService {

    @Autowired
    private final BidRepository bidRepository;

    @Autowired
    private final AuctionRepository auctionRepository;

    public Bid createBid(User user, BidRequest bidRequest, Long auctionId) {
        if (user.getUserName().equals("buyer-1")  || user.getUserName().equals("buyer-2")){
            Bidding bidding = (Bidding) auctionRepository.findById(auctionId).orElseThrow(
                    () -> new NotFoundException(
                            "Auction with id " + auctionId + " not found"));
            Bid bid = BidDtoMapper.mapToBid((bidRequest));

            if (!"OPEN".equals(bidding.getAuctionStatus())){
                throw new NotAllowedException("Bid against non OPEN auction is not allowed");
            }
            if (bid.getBidPrice().compareTo(bidding.getStartingPrice()) <= 0 ){
                throw new IncorrectPriceException("Bid price must be greater than offer price");
            }

            bidding.setCurrentPrice(bid.getBidPrice());
            bidding.addBid(bid);
            auctionRepository.save(bidding);
            return bidding.getBids().get(bidding.getBids().size() - 1);
        }
        else{
            throw new NotAllowedException(user.getUserName() + " is not authorised to place bids on auctions");
        }
    }

}

package com.myproject.blind_auction.service.impl;

import com.myproject.blind_auction.model.auction.Auction;
import com.myproject.blind_auction.service.interfaces.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.myproject.blind_auction.repository.AuctionRepository;
import com.myproject.blind_auction.mapper.AuctionDtoMapper;
import com.myproject.blind_auction.dto.AuctionRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class AuctionServiceImpl implements AuctionService {

    @Autowired
    private final AuctionRepository auctionRepository;

    public Auction createAuction(AuctionRequest auctionRequest) {
        Auction auction = AuctionDtoMapper.mapAuctionRequestToAuction(auctionRequest);
        auction.setAuctionStatus("OPEN");
        return auctionRepository.save(auction);
    }

    @Transactional(readOnly = true)
    public List<Auction> getAllAuctions() {
        return auctionRepository.findAll();
    }
}

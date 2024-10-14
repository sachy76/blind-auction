package com.myproject.blind_auction.model.auction;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.math.BigDecimal;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.BatchSize;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("bidding")
public class Bidding extends Auction{


    private BigDecimal currentPrice;

    @OneToMany(mappedBy = "bidding", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 2)
    private List<Bid> bids;

    @Builder
    public Bidding(Long id, BigDecimal startingPrice, String description,BigDecimal currentPrice) {
        super(id,"bidding",description,startingPrice);
        this.currentPrice = currentPrice;
        this.bids = new ArrayList<>();
    }
    public void addBid(Bid bid) {
        bids.add(bid);
        bid.setBidding(this);
    }

}

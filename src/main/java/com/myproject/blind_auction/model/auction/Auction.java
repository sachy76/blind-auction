package com.myproject.blind_auction.model.auction;

import java.math.BigDecimal;

import com.myproject.blind_auction.model.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import static com.myproject.blind_auction.properties.Constants.MAX_DESCRIPTION_LENGTH;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Auction extends AbstractEntity {

    @Id
    @GeneratedValue(generator = "auction-sequence-generator")
    @GenericGenerator(
            name = "auction-sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "auction_sequence"),
                    @Parameter(name = "initial_value", value = "2"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private Long auctionId;

    @Column(name = "auction_type", insertable = false, updatable = false)
    private String auctionType;

    @Size(min = 1, max = MAX_DESCRIPTION_LENGTH, message = "Description must be provided")
    private String description;

    private BigDecimal startingPrice;

}

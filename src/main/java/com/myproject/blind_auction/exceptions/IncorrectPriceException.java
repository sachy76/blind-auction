package com.myproject.blind_auction.exceptions;

public class IncorrectPriceException extends RuntimeException {

    public IncorrectPriceException(String message) {
        super(message);
    }

}
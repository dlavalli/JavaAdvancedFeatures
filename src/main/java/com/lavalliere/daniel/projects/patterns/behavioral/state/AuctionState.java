package com.lavalliere.daniel.projects.patterns.behavioral.state;

public interface AuctionState {
    void startAuction(Auction auction);
    void closeAuction(Auction auction);
    void placeBid();
}
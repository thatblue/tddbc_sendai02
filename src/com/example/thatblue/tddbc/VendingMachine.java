package com.example.thatblue.tddbc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VendingMachine {
    
    private List<Integer> insertedCoins = new ArrayList<Integer>();
    
    private Set<Integer> enableCoins = new HashSet<Integer>();
    
    private StockOfJuice stockOfJuice = new StockOfJuice();
    
    public VendingMachine() {
        enableCoins.add(10);
        enableCoins.add(50);
        enableCoins.add(100);
        enableCoins.add(500);
        enableCoins.add(1000);
    }
    
    public int getTotalAmount() {
        int totalAmount = 0;
        for(Integer coin:insertedCoins) {
            totalAmount += coin;
        }
        return totalAmount;
    }

    public void insert(int money) {
        if(!isEnableCoin(money)){
            throw new IllegalArgumentException("invalid money!");
        }
        insertedCoins.add(money);
    }

    public List<Integer> refund() {
        List<Integer> refundCoins = insertedCoins;
        insertedCoins = new ArrayList<Integer>();
        return refundCoins;
    }
    
    private boolean isEnableCoin(int money){
        return enableCoins.contains(money);
    }

    public String getJuiceName() {
        return stockOfJuice.getName();
    }

    public Integer getJuicePrice() {
        return stockOfJuice.getPrice();
    }

    public Integer getJuiceStockCount() {
        return stockOfJuice.getStockCount();
    }

}

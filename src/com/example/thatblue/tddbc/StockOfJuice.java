package com.example.thatblue.tddbc;

public class StockOfJuice {

    private String name;
    
    private int price;
    
    private int stockCount;
    
    public StockOfJuice() {
        this.name = "コーラ";
        this.price = 120;
        this.stockCount = 5;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getStockCount() {
        return stockCount;
    }
}

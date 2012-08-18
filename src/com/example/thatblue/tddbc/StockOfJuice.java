package com.example.thatblue.tddbc;

/**
 * ジュースの在庫情報クラス
 * 
 * @author thatblue
 */
public class StockOfJuice {

    /** ジュースの名称 */
    private String name;
    
    /** 金額 */
    private int price;
    
    /** 在庫数 */
    private int stockCount;
    
    /** デフォルトコンストラクタ */
    public StockOfJuice() {
        this.name = "コーラ";
        this.price = 120;
        this.stockCount = 5;
    }

    /**
     * ジュースの名称を取得します
     * 
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * 金額を取得します
     * 
     * @return price
     */
    public int getPrice() {
        return this.price;
    }

    /**
     * 在庫数を取得します
     * 
     * @return stockCount
     */
    public int getStockCount() {
        return this.stockCount;
    }
    
    /**
     * 在庫を1つ減らします
     */
    public void decrementStock() {
        this.stockCount--;
    }
}

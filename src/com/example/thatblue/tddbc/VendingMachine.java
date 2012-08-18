package com.example.thatblue.tddbc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 自動販売機クラス
 * 
 * @author thatblue
 */
public class VendingMachine {

    /** 投入金額 */
    private List<Integer> insertedCoins = new ArrayList<Integer>();

    /** 投入可能なコイン/お札 */
    private static List<Integer> enableCoins = Arrays.asList(1000, 500, 100, 50, 10);

    /** ジュースの在庫 */
    private StockOfJuice stockOfJuice = new StockOfJuice();

    /** 売り上げ金額 */
    private int saleProceeds = 0;

    /**
     * 投入金額の合計を取得します
     * 
     * @return 投入金額の合計
     */
    public int getTotalAmount() {
        int totalAmount = 0;
        for (Integer coin : insertedCoins) {
            totalAmount += coin;
        }
        return totalAmount;
    }

    /**
     * お金を投入します
     * 
     * @param money
     *            投入金額
     */
    public void insert(int money) {
        if (!isEnableCoin(money)) {
            throw new IllegalArgumentException("invalid money!");
        }
        insertedCoins.add(money);
    }

    /**
     * 投入したお金を払い戻します
     * 
     * @return 払い戻されたコイン/お札
     */
    public List<Integer> refund() {
        List<Integer> refundCoins = insertedCoins;
        insertedCoins = new ArrayList<Integer>();
        return refundCoins;
    }

    /**
     * 利用可能なコイン/お札かどうかを判断します
     * 
     * @param money
     *            確認したいコイン/お札の金額
     * @return true:利用可能 false:利用不可
     */
    private static boolean isEnableCoin(int money) {
        return enableCoins.contains(money);
    }

    /**
     * 在庫として持っているジュースの名称を取得します
     * 
     * @return　ジュースの名称
     */
    public String getJuiceName() {
        return stockOfJuice.getName();
    }

    /**
     * 在庫として持っているジュースの金額を取得します
     * 
     * @return ジュースの金額
     */
    public Integer getJuicePrice() {
        return stockOfJuice.getPrice();
    }

    /**
     * 保持しているジュースの在庫数を取得します
     * 
     * @return ジュースの在庫数
     */
    public Integer getJuiceStockCount() {
        return stockOfJuice.getStockCount();
    }

    /**
     * 投入金額・在庫数の観点からジュースが購入可能かを取得します
     * 
     * @return true: 購入可能 false:購入不可
     */
    public boolean enableToBuy() {
        return this.getTotalAmount() >= this.getJuicePrice() && this.getJuiceStockCount() > 0;
    }

    /**
     * 自動販売機の売り上げ金額を取得します
     * 
     * @return 売り上げ金額
     */
    public int getSaleProceeds() {
        return this.saleProceeds;
    }

    /**
     * ジュースを購入します
     */
    public void purchase() {
        if (!this.enableToBuy()) {
            return;
        }

        // 売り上げを増やす
        this.saleProceeds += this.getJuicePrice();

        // 在庫を減らす
        this.stockOfJuice.decrementStock();

        // 投入したお金から差し引くために、除去するコインの枚数と除去後に戻す必要のある金額(ex:150円投入して120円のものを買った後の30円)を計算する
        int priceOfremainder = 0;
        int countOfRemoveCoins = 0;
        for (Integer coin : this.insertedCoins) {
            countOfRemoveCoins++;
            priceOfremainder += coin;
            if (priceOfremainder >= this.getJuicePrice()) {
                break;
            }
        }
        // 戻す必要のある金額
        priceOfremainder -= this.getJuicePrice();

        // 売り上げ金額を下回る枚数までコインを削除する
        for (int i = 0; i < countOfRemoveCoins; i++) {
            insertedCoins.remove(0);
        }

        // ちょうど差し引くことが出来ればこのまま処理終了
        if (priceOfremainder == 0) {
            return;
        }

        // 売り上げ金額より多く差し引いた分を戻す
        for (int coin : enableCoins) {
            int countOfCoinsToReturn = priceOfremainder / coin;
            if (countOfCoinsToReturn > 0) {
                for (int i = 0; i < countOfCoinsToReturn; i++) {
                    insert(coin);
                    priceOfremainder -= coin;
                }
            }
        }
    }
}

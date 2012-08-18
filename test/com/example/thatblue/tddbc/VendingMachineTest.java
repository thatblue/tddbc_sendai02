package com.example.thatblue.tddbc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

/**
 * 自動販売機のテスト
 * 
 * @author thatblue
 * @see <a href="http://devtesting.jp/tddbc/?TDDBC大阪2.0/課題">TDDBC課題</a>
 */
public class VendingMachineTest {

    public static class 投入金額0円 {
        private VendingMachine vendingMachine = null;

        @Before
        public void before() {
            vendingMachine = new VendingMachine();
        }

        @Test
        public void 初期状態で合計金額が0円であること() {
            assertThat(vendingMachine.getTotalAmount(), is(0));
        }

        @Test
        public void お金が投入できること() throws Exception {
            vendingMachine.insert(10);
            vendingMachine.insert(50);
            vendingMachine.insert(100);
            vendingMachine.insert(500);
            vendingMachine.insert(1000);
            assertThat(vendingMachine.getTotalAmount(), is(1660));
        }

        @Test
        public void 規定外のお金が投入された場合に返却されること() throws Exception {
            try {
                vendingMachine.insert(1);
                fail("おかしな金額を入れているので、例外が起きないとダメ");
            } catch (IllegalArgumentException e) {
                assertThat(vendingMachine.getTotalAmount(), is(0));
            }
        }

        @Test
        public void ジュースの情報が取得出来る() throws Exception {
            assertThat(vendingMachine.getJuiceName(), is("コーラ"));
            assertThat(vendingMachine.getJuicePrice(), is(120));
            assertThat(vendingMachine.getJuiceStockCount(), is(5));

        }

        @Test
        public void お金を投入していない時点ではコーラを購入出来ないこと() throws Exception {
            assertThat(vendingMachine.enableToBuy(), is(false));
        }

        @Test
        public void 何も購入していないので売り上げ金額が0円であること() throws Exception {
            assertThat(vendingMachine.getSaleProceeds(), is(0));
        }
    }

    public static class 投入金額100円 {
        private VendingMachine vendingMachine = null;

        @Before
        public void before() {
            vendingMachine = new VendingMachine();
            vendingMachine.insert(100);
        }

        @Test
        public void お金が足りない時はコーラを購入出来ないこと() throws Exception {
            assertThat(vendingMachine.enableToBuy(), is(false));
        }

        @Test
        public void お金が足りずジュースが購入できないこと() throws Exception {
            vendingMachine.purchase();
            assertThat(vendingMachine.getSaleProceeds(), is(0));
            assertThat(vendingMachine.getJuiceStockCount(), is(5));
            assertThat(vendingMachine.refund(), is(Arrays.asList(100)));
        }
    }

    static class 投入金額120円 {
        private VendingMachine vendingMachine = null;

        @Before
        public void before() {
            vendingMachine = new VendingMachine();
            vendingMachine.insert(100);
            vendingMachine.insert(10);
            vendingMachine.insert(10);
        }

        @Test
        public void 投入した金額を払い戻しできること() throws Exception {
            assertThat(vendingMachine.refund(), is(Arrays.asList(100, 10, 10)));
            assertThat(vendingMachine.getTotalAmount(), is(0));
        }

        @Test
        public void お金がちょうどの金額なのでコーラを購入できること() throws Exception {
            vendingMachine.insert(100);
            vendingMachine.insert(10);
            vendingMachine.insert(10);
            assertThat(vendingMachine.enableToBuy(), is(true));
        }

        @Test
        public void ちょうどの金額でジュースが購入できたこと() throws Exception {
            vendingMachine.insert(100);
            vendingMachine.insert(10);
            vendingMachine.insert(10);
            vendingMachine.purchase();
            assertThat(vendingMachine.getSaleProceeds(), is(120));
            assertThat(vendingMachine.getJuiceStockCount(), is(4));
            assertThat(vendingMachine.getTotalAmount(), is(0));
        }
    }

    public static class 投入金額150円 {
        private VendingMachine vendingMachine = null;

        @Before
        public void before() {
            vendingMachine = new VendingMachine();
            vendingMachine.insert(100);
            vendingMachine.insert(50);
        }

        @Test
        public void 購入金額よりも多くお金を投入しているのでコーラを購入できること() throws Exception {
            assertThat(vendingMachine.enableToBuy(), is(true));
        }
    }

    public static class 投入金額1000円 {
        private VendingMachine vendingMachine = null;

        @Before
        public void before() {
            vendingMachine = new VendingMachine();
            vendingMachine.insert(1000);
        }

        @Test
        public void 購入金額よりも多く投入してジュースが購入できたこと() throws Exception {
            vendingMachine.purchase();
            assertThat(vendingMachine.getSaleProceeds(), is(120));
            assertThat(vendingMachine.getJuiceStockCount(), is(4));
            assertThat(vendingMachine.getTotalAmount(), is(880));
        }

    }

    public static class 投入金額120円かつジュース在庫なし {
        private VendingMachine vendingMachine = null;

        @Before
        public void before() {
            vendingMachine = new VendingMachine();
            vendingMachine.insert(1000);
            vendingMachine.purchase();
            vendingMachine.purchase();
            vendingMachine.purchase();
            vendingMachine.purchase();
            vendingMachine.purchase();
            vendingMachine.refund();
            vendingMachine.insert(100);
            vendingMachine.insert(10);
            vendingMachine.insert(10);
        }

        @Test
        public void 在庫がなくなったらジュースが購入できないこと() throws Exception {
            assertThat(vendingMachine.enableToBuy(), is(false));
        }
    }

}

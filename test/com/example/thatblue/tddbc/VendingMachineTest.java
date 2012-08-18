package com.example.thatblue.tddbc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

public class VendingMachineTest {

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
    public void 投入した金額を払い戻しできること() throws Exception {
        vendingMachine.insert(100);
        vendingMachine.insert(100);
        vendingMachine.insert(10);
        assertThat(vendingMachine.refund(), is(Arrays.asList(100,100,10)));
        assertThat(vendingMachine.getTotalAmount(), is(0));
    }
    
    @Test
    public void 規定外のお金が投入された場合に返却されること() throws Exception {
        try {
            vendingMachine.insert(1);
            fail("おかしな金額を入れているので、例外が起きないとダメ");
        } catch (IllegalArgumentException e) {
            assertThat(vendingMachine.getTotalAmount(),is(0));
        }
    }
    
    @Test
    public void ジュースの情報が取得出来る() throws Exception {
        assertThat(vendingMachine.getJuiceName(), is("コーラ"));
        assertThat(vendingMachine.getJuicePrice(), is(120));
        assertThat(vendingMachine.getJuiceStockCount(), is(5));
        
    }
    
}

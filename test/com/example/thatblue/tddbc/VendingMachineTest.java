package com.example.thatblue.tddbc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class VendingMachineTest {

    private VendingMachine vendingMachine = null; 
    
    @Before
    public void before() {
        vendingMachine = new VendingMachine();
    }
    
    @Test
    public void ‰Šúó‘Ô‚Å‡Œv‹àŠz‚ª0‰~‚Å‚ ‚é‚±‚Æ() {
        assertThat(vendingMachine.getTotalAmount(), is(0));
    }

    @Test
    public void ‚¨‹à‚ª“Š“ü‚Å‚«‚é‚±‚Æ() throws Exception {
        vendingMachine.insert(10);
        vendingMachine.insert(50);
        vendingMachine.insert(100);
        vendingMachine.insert(500);
        vendingMachine.insert(1000);
        assertThat(vendingMachine.getTotalAmount(), is(1660));
    }
    
    @Test
    public void “Š“ü‚µ‚½‹àŠz‚ğ•¥‚¢–ß‚µ‚Å‚«‚é‚±‚Æ() throws Exception {
        vendingMachine.insert(100);
        vendingMachine.insert(100);
        vendingMachine.insert(10);
        assertThat(vendingMachine.refund(), is(Arrays.asList(100,100,10)));
        assertThat(vendingMachine.getTotalAmount(), is(0));
    }
    
    @Test
    public void ‹K’èŠO‚Ì‚¨‹à‚ª“Š“ü‚³‚ê‚½ê‡‚É•Ô‹p‚³‚ê‚é‚±‚Æ() throws Exception {
        try {
            vendingMachine.insert(1);
            fail("‚¨‚©‚µ‚È‹àŠz‚ğ“ü‚ê‚Ä‚¢‚é‚Ì‚ÅA—áŠO‚ª‹N‚«‚È‚¢‚Æƒ_ƒ");
        } catch (IllegalArgumentException e) {
            assertThat(vendingMachine.getTotalAmount(),is(0));
        }
    }
    
    
}

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
    public void ������Ԃō��v���z��0�~�ł��邱��() {
        assertThat(vendingMachine.getTotalAmount(), is(0));
    }

    @Test
    public void �����������ł��邱��() throws Exception {
        vendingMachine.insert(10);
        vendingMachine.insert(50);
        vendingMachine.insert(100);
        vendingMachine.insert(500);
        vendingMachine.insert(1000);
        assertThat(vendingMachine.getTotalAmount(), is(1660));
    }
    
    @Test
    public void �����������z�𕥂��߂��ł��邱��() throws Exception {
        vendingMachine.insert(100);
        vendingMachine.insert(100);
        vendingMachine.insert(10);
        assertThat(vendingMachine.refund(), is(Arrays.asList(100,100,10)));
        assertThat(vendingMachine.getTotalAmount(), is(0));
    }
    
    @Test
    public void �K��O�̂������������ꂽ�ꍇ�ɕԋp����邱��() throws Exception {
        try {
            vendingMachine.insert(1);
            fail("�������ȋ��z�����Ă���̂ŁA��O���N���Ȃ��ƃ_��");
        } catch (IllegalArgumentException e) {
            assertThat(vendingMachine.getTotalAmount(),is(0));
        }
    }
    
    
}

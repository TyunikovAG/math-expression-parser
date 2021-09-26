package ru.tyunikovag.interview;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyBigNumberTest {

    @Test
    public void add() {
        MyBigNumber b1 = new MyBigNumber("753159456852");
        MyBigNumber b2 = new MyBigNumber("1654613684");
        long l = Long.parseLong(b1.add(b2).toString());

        Assert.assertEquals("Yahoo!!!", 754814070536L, l);
    }

    @Test
    public void sub() {
        MyBigNumber b1 = new MyBigNumber("753159456852");
        MyBigNumber b2 = new MyBigNumber("1654613684");
        long l = Long.parseLong(b1.sub(b2).toString());

        Assert.assertEquals("Yahoo!!!", 751504843168L, l);
    }
}
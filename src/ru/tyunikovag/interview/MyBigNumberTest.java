package ru.tyunikovag.interview;

import org.junit.Assert;
import org.junit.Test;

public class MyBigNumberTest {

    @Test
    public void removeLeadZeros(){
        String s = "000123456";
        Assert.assertEquals("Oops", "123456", MyBigNumber.removeLeadZeros(s));
    }

    @Test
    public void removeLeadZerosAllZeros(){
        String s = "00000000000";
        Assert.assertEquals("Oops", "0", MyBigNumber.removeLeadZeros(s));
    }

    @Test
    public void removeLeadZerosZerosInside(){
        String s = "00000505000";
        Assert.assertEquals("Oops", "505000", MyBigNumber.removeLeadZeros(s));
    }

    @Test
    public void addWithBothPositive() {
        MyBigNumber b1 = new MyBigNumber("753159456852");
        MyBigNumber b2 = new MyBigNumber("1654613684");
        long l = Long.parseLong(b1.add(b2).toString());

        Assert.assertEquals("Yahoo!!!", 754814070536L, l);
    }

    @Test
    public void addWithBothNegative() {
        MyBigNumber b1 = new MyBigNumber("-753159456852");
        MyBigNumber b2 = new MyBigNumber("-1654613684");
        long l = Long.parseLong(b1.add(b2).toString());

        Assert.assertEquals("Yahoo!!!", -754814070536L, l);
    }

    @Test
    public void addWithDifferentSignNegativeBig() {
        MyBigNumber b1 = new MyBigNumber("-753159456852");
        MyBigNumber b2 = new MyBigNumber("1654613684");
        long l = Long.parseLong(b1.add(b2).toString());

        Assert.assertEquals("Yahoo!!!", -751504843168L, l);
    }

    @Test
    public void addWithDifferentSignPositiveBig() {
        MyBigNumber b1 = new MyBigNumber("753159456852");
        MyBigNumber b2 = new MyBigNumber("-1654613684");
        long l = Long.parseLong(b1.add(b2).toString());

        Assert.assertEquals("Yahoo!!!", 751504843168L, l);
    }

    @Test
    public void sub() {
        MyBigNumber b1 = new MyBigNumber("753159456852");
        MyBigNumber b2 = new MyBigNumber("1654613684");
        long l = Long.parseLong(b1.sub(b2).toString());

        Assert.assertEquals("Yahoo!!!", 751504843168L, l);
    }
}
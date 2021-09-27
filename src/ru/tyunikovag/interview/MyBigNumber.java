package ru.tyunikovag.interview;

import org.junit.Assert;
import org.junit.Test;

interface BigNumber extends Comparable {
    BigNumber add(BigNumber bigNumber);

    BigNumber sub(BigNumber bigNumber);
}

public class MyBigNumber implements BigNumber {

    private final String value;
    private final char sign;

    public MyBigNumber(String value) {
        if (!value.isEmpty() && (value.charAt(0) == '-' || value.charAt(0) == '+')) {
            sign = value.charAt(0);
            this.value = value.substring(1);
        } else {
            this.value = value;
            this.sign = '+';
        }
    }

    private String addByModule(MyBigNumber another) {
        String s1;
        String s2;
        if (compareByModule(another) >= 0) {
            s1 = this.value;
            s2 = fillByZeros(another, s1);
        } else {
            s1 = another.value;
            s2 = fillByZeros(this, s1);
        }

        StringBuilder result = new StringBuilder();
        int rem = 0;
        for (int i = s1.length() - 1; i >= 0; i--) {
            int n1 = Character.getNumericValue(s1.charAt(i));
            int n2 = Character.getNumericValue(s2.charAt(i));
            String r = String.valueOf(n1 + n2 + rem);
            if (r.length() == 2) {
                result.append(r.charAt(1));
                rem = Character.getNumericValue(r.charAt(0));
            } else {
                result.append(r);
                rem = 0;
            }
        }
        result.reverse();
        return removeLeadZeros(result.toString());
    }

    private String subByModule(MyBigNumber another) {
        String s1;
        String s2;
        if (compareByModule(another) >= 0) {
            s1 = this.value;
            s2 = fillByZeros(another, s1);
        } else {
            s1 = another.value;
            s2 = fillByZeros(this, s1);
        }
        StringBuilder result = new StringBuilder();
        int rem = 0;
        for (int i = s1.length() - 1; i >= 0; i--) {
            int n1 = Character.getNumericValue(s1.charAt(i));
            int n2 = Character.getNumericValue(s2.charAt(i));
            if (n1 - rem >= n2) {
                int m = n1 - rem - n2;
                result.append(m);
                rem = 0;
            } else {
                int m = n1 - rem + 10 - n2;
                result.append(m);
                rem = 1;
            }
        }
        result.reverse();
        return removeLeadZeros(result.toString());
    }

    public static String removeLeadZeros(String s) {
        if (!s.contains("0")) {
            return s;
        } else {
            int pos = s.length() - 1;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != '0') {
                    pos = i;
                    break;
                }
            }
            return s.substring(pos);
        }
    }

    @Override
    public BigNumber add(BigNumber bigNumber) {
        MyBigNumber another = castToMyBigNumber(bigNumber);
        if (this.sign == another.sign) {
            String s = addByModule(another);
            if (s.equals("0")) {
                return new MyBigNumber("0");
            } else {
                return new MyBigNumber(this.sign + s);
            }
        } else {
            String s = subByModule(another);
            if (s.equals("0")) {
                return new MyBigNumber("0");
            } else {
                char sign = this.compareByModule(another) >= 0 ? this.sign : another.sign;
                return new MyBigNumber(sign + s);
            }
        }
    }

    @Override
    public BigNumber sub(BigNumber bigNumber) {
        MyBigNumber another = castToMyBigNumber(bigNumber);
        char sign = another.sign == '+' ? '-' : '+';
        MyBigNumber arg = new MyBigNumber(sign + another.value);
        return (add(arg));
    }

    @Override
    public int compareTo(Object o) {
        MyBigNumber another = (MyBigNumber) o;
        if (this.sign == '-' && another.sign == '+') return -1;
        if (this.sign == '+' && another.sign == '-') return 1;
        if (this.sign == '-' && another.sign == '-') {
            return -compareByModule(another);
        }
        if (this.sign == '+' && another.sign == '+') {
            return compareByModule(another);
        }
        return 0;
    }

    private int compareByModule(MyBigNumber another) {
        if (another.value.length() != this.value.length()) {
            return this.value.length() - another.value.length();
        } else {
            for (int i = 0; i < this.value.length(); i++) {
                if (this.value.charAt(i) != another.value.charAt(i)) {
                    return this.value.charAt(i) - another.value.charAt(i);
                }
            }
        }
        return 0;
    }

    private MyBigNumber castToMyBigNumber(BigNumber bigNumber) {
        try {
            return (MyBigNumber) bigNumber;
        } catch (ClassCastException exception) {
            throw new IllegalArgumentException("Another number do not contain a value String");
        }
    }

    private String fillByZeros(MyBigNumber another, String s1) {
        return String.format("%" + s1.length() + "s", another.value).replace(' ', '0');
    }

    @Override
    public String toString() {
        return sign == '+' ? value : sign + value;
    }

    public static void main(String[] args) {
//        System.out.println(new Integer(-100).compareTo(new Integer(-500)));
        MyBigNumber b1 = new MyBigNumber("-38027450742057608221309764383410169802626");
        MyBigNumber b2 = new MyBigNumber("-38027450742057608221309764383410169802626");
        b1.sub(b2);
        System.out.println(b1.compareTo(b2));
    }
}

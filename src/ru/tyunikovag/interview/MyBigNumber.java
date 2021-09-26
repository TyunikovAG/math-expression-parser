package ru.tyunikovag.interview;

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

    @Override
    public BigNumber add(BigNumber bigNumber) {
        MyBigNumber another;
        try {
            another = (MyBigNumber) bigNumber;
        } catch (ClassCastException exception) {
            throw new IllegalArgumentException("Another number do not contain a value String");
        }
        String s1;
        String s2;
        if (this.value.length() >= another.value.length()) {
            s1 = this.value;
            s2 = another.value;
        } else {
            s1 = another.value;
            s2 = this.value;
        }
        StringBuilder result = new StringBuilder();
        int diff = s1.length() - s2.length();
        int rem = 0;
        for (int i = s1.length() - 1; i >= diff; i--) {
            int n1 = Character.getNumericValue(s1.charAt(i));
            int n2 = Character.getNumericValue(s2.charAt(i - diff));
            String r = String.valueOf(n1 + n2 + rem);
            if (r.length() == 2) {
                result.append(r.charAt(1));
                rem = Character.getNumericValue(r.charAt(0));
            } else {
                result.append(r);
                rem = 0;
            }
        }
        for (int i = s1.length() + 1 - diff; i >= 0 ; i--) {
            int n1 = Character.getNumericValue(s1.charAt(i));
            String r = String.valueOf(n1 + rem);
            if (r.length() == 2) {
                result.append(r.charAt(1));
                rem = Character.getNumericValue(r.charAt(0));
            } else {
                result.append(r);
                rem = 0;
            }
        }
        result.reverse();
        return new MyBigNumber(result.toString());
    }

    @Override
    public BigNumber sub(BigNumber bigNumber) {
        return null;
    }

    @Override
    public int compareTo(Object o) {
        MyBigNumber another = (MyBigNumber) o;
        if (this.sign == '-' && another.sign == '+') return -1;
        if (this.sign == '+' && another.sign == '-') return 1;
        if (this.sign == '-' && another.sign == '-') {
            if (another.value.length() != this.value.length()) {
                return another.value.length() - this.value.length();
            } else {
                for (int i = 0; i < this.value.length(); i++) {
                    if (this.value.charAt(i) != another.value.charAt(i)) {
                        return another.value.charAt(i) - this.value.charAt(i);
                    }
                }
            }
        }
        if (this.sign == '+' && another.sign == '+') {
            if (another.value.length() != this.value.length()) {
                return this.value.length() - another.value.length();
            } else {
                for (int i = 0; i < this.value.length(); i++) {
                    if (this.value.charAt(i) != another.value.charAt(i)) {
                        return this.value.charAt(i) - another.value.charAt(i);
                    }
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
//        System.out.println(new Integer(-100).compareTo(new Integer(-100)));
        MyBigNumber b1 = new MyBigNumber("456789");
        MyBigNumber b2 = new MyBigNumber("89");
        b1.add(b2);
        System.out.println(b1.compareTo(b2));
    }
}

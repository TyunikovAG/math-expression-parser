package ru.tyunikovag.interview;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import java.util.function.*;
import java.math.*;

public class MathParser {

    public static void main(String[] args) {
        System.out.println(calculate("12*(3+2*(7+56*(1+428)))"));
//        System.out.println(calculate("17-2*3"));
    }

    static int calculate(String mathString) {
        // enter your code
        return calcFromPolish(toPolishStack(mathString));
    }

    private static int calcFromPolish(Stack<String> stack) {
        Stack<String> resulStack = new Stack<>();
        while (!stack.empty()) {
            String s = stack.pop();
            if (s.matches("\\d+")) {
                resulStack.push(s);
            } else if (s.matches("[\\+\\-\\*\\/]")) {
                int a = Integer.parseInt(resulStack.pop());
                int b = Integer.parseInt(resulStack.pop());
                switch (s) {
                    case "+": {
                        resulStack.push(String.valueOf(a + b));
                        break;
                    }
                    case "-": {
                        resulStack.push(String.valueOf(a - b));
                        break;
                    }
                    case "*": {
                        resulStack.push(String.valueOf(a * b));
                        break;
                    }
                    case "/": {
                        resulStack.push(String.valueOf(a / b));
                    }
                }
            }
        }
        return Integer.parseInt(resulStack.pop());
    }

    private static Stack<String> toPolishStack(String mathString) {
        final String DIGIT_MATCHER = "\\d+";
        final String SYMBOLS_MATCHER = "[\\+\\-\\*\\/]";
        final String MATH_SPLITTER = "(?<=\\d)(?=\\D)|(?<=\\D)(?=\\d)|(?<=\\*)(?<=\\*)|(?<=\\))(?<=\\))";

        Stack<String> temp = new Stack<>();
        Stack<String> stack = new Stack<>();
        String[] split = mathString.split(MATH_SPLITTER);
//        char[] chars = mathString.toCharArray();

        for (int i = 0; i < split.length; i++) {
            String curr = split[i];
            if (curr.matches(DIGIT_MATCHER)) {
                stack.push(curr);
                continue;
            }
            if (curr.equals("(")) {
                temp.push(curr);
                continue;
            }
            if (curr.equals(")")) {
                while (!temp.peek().equals("(")) {
                    stack.push(temp.pop());
                }
                temp.pop();
                continue;
            }
            if (curr.equals("+") || curr.equals("-")) {
                if (!temp.isEmpty()) {
                    while (temp.peek().equals("*") || temp.peek().equals("/")) {
                        stack.push(temp.pop());
                    }
                }
                temp.push(curr);
            } else if (curr.equals("*") || curr.equals("/")) {
                temp.push(curr);
            }
        }
        while (!temp.empty()) {
            stack.push(temp.pop());
        }
        Collections.reverse(stack);
        return stack;
    }
}

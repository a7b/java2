package hackathon;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ShuntingYard {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        
        while (true) {
            // Read
            System.out.print("> "); // Optional 
            String expression = keyboard.nextLine();
            // Evaluate
            double result = evaluate(expression);
            // Print
            System.out.println(result);
        }
    }

    // Check out Stack
    private static ArrayList <Double> numbers;
    private static ArrayList <String> operators;
    private static HashMap <String, Double> symbols = new HashMap <String, Double> ();
    
    // Evaluate the expression
    private static double evaluate(String expression) {
        numbers = new ArrayList <Double> ();
        operators = new ArrayList <String> ();
        String symbol = null;
        
        if (expression.contains("=")) {
            symbol = expression.substring(0, expression.indexOf("=")).trim();
            expression = expression.substring(expression.indexOf("=") + 1);
        }
        
        // For each part of the expression
        for (String part :
                // The "sanitized" expression
                expression.replaceAll(" ", "")
                          .split("(?<=[-+*/()])|(?=[-+*/()])") ) {
            
            // Skip empty strings
            if (part.length() == 0) {
            }
            else if (symbols.containsKey(part)) {
                numbers.add(symbols.get(part));
            }
            // Case 1: it's an operator
            else if (isOperator(part)) {
                while (canReduce(part)) {
                    reduce();
                }
                if (part.equals(")")) {
                    operators.remove(operators.size() - 1);
                }
                else {
                    operators.add(part);
                }
            }
            // Otherwise, it's a number
            else {
                numbers.add(toDouble(part));
            }
        }
        // Reduce until all operators are used up
        while (operators.size() > 0) {
            reduce();
        }
        
        if (symbol != null) {
            symbols.put(symbol, numbers.get(0));
        }
        
        return numbers.get(0);
    }
    
    // Can a reduction be done?
    private static boolean canReduce(String op) {
        if (operators.size() == 0) {
            return false;
        }
        String lastOp = operators.get(operators.size() - 1);
        // If I am adding a closed parenthese to the operator list
        if (op.equals(")")) {
            // Two cases: open parentheses or not
            if (lastOp.equals("(")) {
                return false;
            }
            else {
                return true;
            }
        }
        return getPrecedence(lastOp) >= getPrecedence(op);
    }
    
    private static void reduce() {
        double b = numbers.remove(numbers.size() - 1);
        double a = numbers.remove(numbers.size() - 1);
        String op = operators.remove(operators.size() - 1);
        
        if (op.equals("+")) {
            numbers.add(a + b);
        }
        if (op.equals("-")) {
            numbers.add(a - b);
        }
        if (op.equals("*")) {
            numbers.add(a * b);
        }
        if (op.equals("/")) {
            numbers.add(a / b);
        }
    }

    private static int getPrecedence(String op) {
        if (op.equals("(")) {
            return 0;
        }
        if (op.equals("+") || op.equals("-")) {
            return 1;
        }
        if (op.equals("*") || op.equals("/")) {
            return 2;
        }
        return 0;
    }
    
    // True or false, is it an operator?
    private static boolean isOperator(String op) {
        // All the cases where you return true
        if (op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/") || op.equals("(") || op.equals(")")) {
            return true;
        }
        return false;
    }
    
    // Challenge: try parsing integers yourself
    private static double toDouble(String number) {
        return Double.parseDouble(number);
    }

}

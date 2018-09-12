package Chapter1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.io.*;
import java.util.Scanner;
import java.util.Stack;

/**
 * Trace two-stack algorithm of Dijkstra
 */

public class Evaluate {
    public static void main(String[] args) throws FileNotFoundException {
        Stack<String> ops = new Stack<>();
        Stack<Double> vals = new Stack<>();
        Scanner scanner = new Scanner(System.in);


        while (!scanner.hasNext()) {
            String s = scanner.next();
            if (s.equals("(")) ;
            else if (s.equals("+")) ops.push(s);
            else if (s.equals("-")) ops.push(s);
            else if (s.equals("*")) ops.push(s);
            else if (s.equals("/")) ops.push(s);
            else if (s.equals("sqrt")) ops.push(s);
            else if (s.equals(")")) {
                String op = ops.pop();
                double v = vals.pop();
                if (op.equals("+")) v = vals.pop() + v;
                else if (op.equals("-")) v = vals.pop() - v;
                else if (op.equals("*")) v = vals.pop() * v;
                else if (op.equals("/")) v = vals.pop() / v;
                else if (op.equals("sqrt")) v = Math.sqrt(v);
                vals.push(v);
            } else vals.push(Double.parseDouble(s));
        }
        System.out.println(vals.pop());

    }
}

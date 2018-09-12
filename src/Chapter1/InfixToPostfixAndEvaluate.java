package Chapter1;

import edu.princeton.cs.algs4.StdOut;

public class InfixToPostfixAndEvaluate {
    public static void main(String[] args) {
        LinkedStack<String> stack = new LinkedStack<>();
        LinkedStack<Double> stack2 = new LinkedStack<>();
        String[] strings = {"3", "6", "/"};

//        while (!StdIn.isEmpty()) {
//            String str = StdIn.readString();
//            if ("+".equals(str) || "*".equals(str) || "/".equals(str) || "%".equals(str) || "-".equals(str)) {
//                stack.push(str);
//
//            } else if (")".equals(str)) {
//                StdOut.print(stack.pop() + " ");
//
//            } else if ("(".equals(str)) {
//                StdOut.print("");
//
//            } else {
//                StdOut.print(str + " ");
//
//            }
//        }
//        StdOut.println();

        for (int i = 0; i < strings.length; i++) {
            if (strings[i].equals("+")) {
                stack2.push(stack2.pop() + stack2.pop());
            } else if (strings[i].equals("*")) {
                stack2.push(stack2.pop() * stack2.pop());
            } else if (strings[i].equals("/")) {
                stack2.push(stack2.pop() / stack2.pop());
            } else if (strings[i].equals("-")) {
                stack2.push(stack2.pop() - stack2.pop());
            } else if (strings[i].equals("%")) {
                stack2.push(stack2.pop() % stack2.pop());
            } else {
                stack2.push(Double.parseDouble(strings[i]));
            }
        }
        StdOut.println(stack2.pop());
    }
}

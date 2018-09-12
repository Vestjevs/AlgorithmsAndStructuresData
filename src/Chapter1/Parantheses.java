package Chapter1;

public class Parantheses {
    public static final char LEFT_PARAN = '(';
    public static final char RIGHT_PARAN = ')';
    public static final char LEFT_BRACE = '{';
    public static final char RIGHT_BRACE = '}';
    public static final char LEFT_BRACKET = '[';
    public static final char RIGHT_BRACKET = ']';

    public static boolean isBalanced(String str) {
        LinkedStack<Character> stack = new LinkedStack<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == LEFT_PARAN) stack.push(LEFT_PARAN);
            if (str.charAt(i) == LEFT_BRACE) stack.push(LEFT_BRACE);
            if (str.charAt(i) == LEFT_BRACKET) stack.push(LEFT_BRACKET);

            if (str.charAt(i) == RIGHT_PARAN) {
                if (stack.isEmpty()) return false;
                if (stack.pop() != LEFT_PARAN) return false;
            }
            else if (str.charAt(i) == RIGHT_BRACE) {
                if (stack.isEmpty()) return false;
                if (stack.pop() != LEFT_BRACE) return false;

            }
            else if (str.charAt(i) == RIGHT_BRACKET) {
                if (stack.isEmpty()) return false;
                if (stack.pop() != LEFT_BRACKET) return false;
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String str = "{({}(([])))}";
        System.out.println(isBalanced(str));
    }
}

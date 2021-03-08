package class11_Recursion;

import java.util.Stack;

/**
 * @author mingyan wang
 * @date 2021/3/8 11:17 AM
 */
public class Code04_ReverseStackUsingRecursion {
    public static void reverseStack(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int last = f(stack);
        reverseStack(stack);
        stack.push(last);
    }

    public static int f(Stack<Integer> stack) {
        int last = 0;
        Integer num = stack.pop();
        if (stack.isEmpty()) {
            last = num;
            return last;
        }
        int result = f(stack);
        last = result;
        stack.push(num);
        return last;
    }

}

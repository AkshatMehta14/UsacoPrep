import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class NearestSmallerValues {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));


        int n = Integer.parseInt(in.readLine());
        int[] nums = new int[n];


        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }


        Stack<Integer> stack = new Stack<>();
        StringBuilder output = new StringBuilder();


        for (int i = 0; i < n; i++) {

            while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
                stack.pop();
            }


            if (stack.isEmpty()) {
                output.append("0 ");
            } else {
                output.append(stack.peek() + 1).append(" ");
            }


            stack.push(i);
        }

        System.out.println(output.toString().trim());
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Advertisement {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
        int n  = Integer.parseInt(in.readLine());          int[] heights = new int[n];
        

        StringTokenizer tokenizer = new StringTokenizer(in.readLine()); 
        for (int i = 0; i < n; i++) {
            heights[i] = Integer.parseInt(tokenizer.nextToken()); 
        }
        

        System.out.println(largestRectangleArea(heights));
    }

    public static long largestRectangleArea(int[] heights) {
      
        Stack<Integer> stack = new Stack<>();
        long maxArea = 0;  
        int n = heights.length;
        int i = 0;
        
        while (i < n) {
            if (stack.isEmpty() || heights[i] >= heights[stack.peek()]) {
                stack.push(i++);
            } else {
                int top = stack.pop();
                long height = heights[top];
                long width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
        }
        
        while (!stack.isEmpty()) {
            int top = stack.pop();
            long height = heights[top];
            long width = stack.isEmpty() ? i : i - stack.peek() - 1;
            maxArea = Math.max(maxArea, height * width);
        }
        
        return maxArea;
    }
}

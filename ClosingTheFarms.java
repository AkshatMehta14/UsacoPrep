import java.io.*;
import java.util.*;

public class ClosingTheFarms {
    public static boolean[] visited;
    public static ArrayList<ArrayList<Integer>> adj;
    public static boolean[] removed;

    public static void main(String[] args) throws IOException {
        // Reading input from "closing.in"
        BufferedReader in = new BufferedReader(new FileReader("closing.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("closing.out")));

        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // Initialize adjacency list
        adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        // Read in the edges
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(in.readLine());
            int barn1 = Integer.parseInt(st.nextToken()) - 1;
            int barn2 = Integer.parseInt(st.nextToken()) - 1;
            adj.get(barn1).add(barn2);
            adj.get(barn2).add(barn1);
        }

        // Order of barns to close
        int[] order = new int[n];
        for (int i = 0; i < n; i++) {
            order[i] = Integer.parseInt(in.readLine()) - 1;
        }

        // Initialize visited and removed arrays
        visited = new boolean[n];
        removed = new boolean[n];

        // Check initial connectivity
        if (isConnected(n)) {
            out.println("YES");
        } else {
            out.println("NO");
        }

        // Remove barns one by one and check connectivity
        for (int i = 0; i < n - 1; i++) {
            removed[order[i]] = true; // Mark barn as removed
            if (isConnected(n - i - 1)) {
                out.println("YES");
            } else {
                out.println("NO");
            }
        }

        // Close file handlers
        in.close();
        out.close();
    }

    // Check if the farm is fully connected given the current state
    public static boolean isConnected(int remaining) {
        // Reset visited array
        visited = new boolean[visited.length];

        // Find the first unremoved barn to start DFS
        int start = -1;
        for (int i = 0; i < removed.length; i++) {
            if (!removed[i]) {
                start = i;
                break;
            }
        }

        if (start == -1) return false; // No barns left

        // Perform DFS
        int counter = dfs(start);

        // Check if all remaining barns are connected
        return counter == remaining;
    }

    // DFS to count the number of reachable nodes
    public static int dfs(int root) {
        if (visited[root] || removed[root]) return 0;

        visited[root] = true;
        int count = 1;

        for (int neighbor : adj.get(root)) {
            count += dfs(neighbor);
        }

        return count;
    }
}

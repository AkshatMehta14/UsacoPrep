import java.io.*;
import java.util.*;

public class FlightRoutesCheck {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // Adjacency list for the graph and its transpose
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        ArrayList<ArrayList<Integer>> transpose = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
            transpose.add(new ArrayList<>());
        }

        // Read edges
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            adj.get(a).add(b);
            transpose.get(b).add(a); // Reverse the edge for transpose
        }

        // Check forward reachability
        boolean[] visited = new boolean[n];
        dfs(0, adj, visited);
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                System.out.println("NO");
                System.out.println((1) + " " + (i + 1));
                return;
            }
        }

        // Check backward reachability (using transpose)
        Arrays.fill(visited, false);
        dfs(0, transpose, visited);
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                System.out.println("NO");
                System.out.println((i + 1) + " " + (1));
                return;
            }
        }

        // If both checks passed
        System.out.println("YES");
    }

    // DFS function
    public static void dfs(int node, ArrayList<ArrayList<Integer>> graph, boolean[] visited) {
        if (visited[node]) return;
        visited[node] = true;
        for (int neighbor : graph.get(node)) {
            dfs(neighbor, graph, visited);
        }
    }
}

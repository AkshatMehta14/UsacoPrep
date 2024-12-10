import java.io.*;
import java.util.*;

public class ConnectingTwoBarns {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            List<List<Integer>> edges = new ArrayList<>();
            for (int i = 0; i < n; i++) edges.add(new ArrayList<>());

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(in.readLine());
                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;
                edges.get(a).add(b);
                edges.get(b).add(a);
            }

            int[] component = new int[n];
            for (int i = 0; i < n; i++) component[i] = i;

            // Find connected components using DFS
            for (int i = 0; i < n; i++) {
                if (component[i] == i) {
                    dfs(edges, component, i, i);
                }
            }

            // If barn 1 and barn N are in the same component, cost is 0
            if (component[0] == component[n - 1]) {
                sb.append("0\n");
                continue;
            }

            List<List<Integer>> componentToVertices = new ArrayList<>();
            for (int i = 0; i < n; i++) componentToVertices.add(new ArrayList<>());

            for (int i = 0; i < n; i++) {
                componentToVertices.get(component[i]).add(i);
            }

            long[] srccost = new long[n];
            long[] dstcost = new long[n];
            Arrays.fill(srccost, Long.MAX_VALUE);
            Arrays.fill(dstcost, Long.MAX_VALUE);

            // Calculate minimum distances for source and destination components
            int srcIdx = 0, dstIdx = 0;
            for (int i = 0; i < n; i++) {
                while (srcIdx < componentToVertices.get(component[0]).size()) {
                    int srcVertex = componentToVertices.get(component[0]).get(srcIdx);
                    srccost[component[i]] = Math.min(srccost[component[i]], Math.abs(i - srcVertex));
                    if (srcVertex < i) srcIdx++;
                    else break;
                }
                if (srcIdx > 0) srcIdx--;

                while (dstIdx < componentToVertices.get(component[n - 1]).size()) {
                    int dstVertex = componentToVertices.get(component[n - 1]).get(dstIdx);
                    dstcost[component[i]] = Math.min(dstcost[component[i]], Math.abs(i - dstVertex));
                    if (dstVertex < i) dstIdx++;
                    else break;
                }
                if (dstIdx > 0) dstIdx--;
            }

            long ans = Long.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if (srccost[i] != Long.MAX_VALUE && dstcost[i] != Long.MAX_VALUE) {
                    ans = Math.min(ans, srccost[i] * srccost[i] + dstcost[i] * dstcost[i]);
                }
            }

            sb.append(ans).append("\n");
        }

        System.out.print(sb);
    }

    private static void dfs(List<List<Integer>> edges, int[] component, int curr, int id) {
        for (int neighbor : edges.get(curr)) {
            if (component[neighbor] != id) {
                component[neighbor] = id;
                dfs(edges, component, neighbor, id);
            }
        }
    }
}

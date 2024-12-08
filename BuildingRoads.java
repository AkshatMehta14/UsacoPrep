import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class BuildingRoads {

public static boolean[] visited; 
public static ArrayList<ArrayList<Integer>> adj; 

	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken()); 
		int m = Integer.parseInt(st.nextToken());
		visited = new boolean[n];
		adj = new ArrayList<>(n);
		for(int i = 0; i < n; i++) {
			adj.add(new ArrayList<Integer>()); 
		}
		for(int i = 0; i < m; i++) {
			StringTokenizer c = new StringTokenizer(in.readLine());
			int city1 = Integer.parseInt(c.nextToken())-1; 
			int city2 = Integer.parseInt(c.nextToken())-1;
			adj.get(city1).add(city2); 
			adj.get(city2).add(city1); 
		}
		ArrayList<Integer> startingNodes = new ArrayList<Integer>(); 
		for(int i = 0; i < n; i++) {
			if(visited[i] == true) {
				continue; 
			}
			dfs(i); 
			startingNodes.add(i);
		}
		int k = startingNodes.size()-1; 
		System.out.println(k);
		for(int i = 0; i< startingNodes.size()-1; i++) {
			System.out.println(startingNodes.get(i)+1 + " " + (startingNodes.get(i+1)+1));
		}
		
		
	}
	
	public static void dfs(int root) {
		if(visited[root]) {
			return; 
		}
		visited[root] = true; 
		for(int i = 0; i < adj.get(root).size(); i++) {
			dfs(adj.get(root).get(i)); 
		}
	}

}

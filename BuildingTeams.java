import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BuildingTeams {
	//we are doing bitpartiness so we are using "colors" like blue and red that alternate
	public static int[] colors; 
	public static ArrayList<ArrayList<Integer>> adj; 
	public static boolean imp = false; 

	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(in.readLine()); 
		int n = Integer.parseInt(st.nextToken()); 
		int m = Integer.parseInt(st.nextToken()); 
		
		colors = new int[n];
		adj = new ArrayList<ArrayList<Integer>>(); 
		for(int i = 0; i < n; i++) {
			adj.add(new ArrayList<Integer>());
		}
		for(int i = 0; i < m; i++) {
			StringTokenizer st2 = new StringTokenizer(in.readLine());
			int ship1 = Integer.parseInt(st2.nextToken())-1;
			int ship2 = Integer.parseInt(st2.nextToken())-1;
			adj.get(ship1).add(ship2);
			adj.get(ship2).add(ship1);
		}
		for(int i = 0; i < n; i++) {
			if(colors[i] != 0) {
				continue;
			}
			dfs(i, 2);
			if(imp == true) {
				System.out.println("IMPOSSIBLE"); 
				break;
				
			}
		}
		
		if(imp == false) {
			String p = "";
			for(int i = 0; i < colors.length-1; i++) {
				p += colors[i] + " "; 
			}
			p += colors[colors.length-1]; 
			System.out.println(p);
		}
	}
	
	public static void dfs(int root, int newColor) {
		if(imp == true) {
			return; 
		}
		if(colors[root] == newColor) {
			return;
		}
		
		if(colors[root] != 0) {
			imp = true; 
			
		}
		
		colors[root] = newColor; 
		for(int i : adj.get(root)) {
			dfs(i, 3-newColor); 
		}
		
	}

}

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MooCast {
	public static boolean[] visited;
	public static ArrayList<ArrayList<Integer>> adj; 
	public static int maxSend = 0; 
	public static int maxSendFinal = 0; 

	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new FileReader("moocast.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("moocast.out")));
        int n = Integer.parseInt(in.readLine());
        String[] coors = new String[n];
        for(int i = 0; i < n; i++) {
        	String coor = in.readLine();
        	coors[i] = coor; 
        }
        
        adj = new ArrayList<ArrayList<Integer>>(); 
        
        for(int i = 0; i < n; i++) {
        	adj.add(new ArrayList<Integer>());
        }
        
        for(int i = 0; i < n; i++) {
        	for(int j = i; j < n; j++) {
        		if(i == j) {
        			continue; 
        		}
        		StringTokenizer st = new StringTokenizer(coors[i]); 
        		StringTokenizer st2 = new StringTokenizer(coors[j]); 
        		int x1, x2, y1, y2, p1, p2; 
        		x1 = Integer.parseInt(st.nextToken());
        		y1 = Integer.parseInt(st.nextToken());
        		p1 = Integer.parseInt(st.nextToken());
        		
        		x2 = Integer.parseInt(st2.nextToken());
        		y2 = Integer.parseInt(st2.nextToken());
        		p2 = Integer.parseInt(st2.nextToken());
        		
        		int distanceSquared = (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);
        		if (p1 * p1 >= distanceSquared) {
        		    adj.get(i).add(j);
        		}
        		if (p2 * p2 >= distanceSquared) {
        		    adj.get(j).add(i);
        		}

        		
      
        	}
        }
        
        for(int i = 0; i < n; i++) {
        	visited = new boolean[n]; 
        	if(visited[i] == true)
        		continue; 
        	dfs(i);
        	if(maxSend > maxSendFinal) {
        		maxSendFinal = maxSend; 
        	}
        	maxSend = 0;
        }
        out.println(maxSendFinal); 
        out.flush();
        out.close();
	}
	
	public static void dfs(int root) {
		if(visited[root] == true) {
			return; 
		}
		visited[root] = true; 
		maxSend++; 
		for(int neighbor : adj.get(root)) {
			dfs(neighbor); 
		}
	}

}

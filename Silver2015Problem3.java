

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Silver2015Problem3 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("bcount.in"));
		PrintWriter pw = new PrintWriter(new FileWriter("bcount.out"));
		String line1 = in.readLine();
		StringTokenizer s = new StringTokenizer(line1); 
		int n = Integer.parseInt(s.nextToken()); 
		int q = Integer.parseInt(s.nextToken()); 
		int[] holsteins = new int[n+1]; 
		int[] guernseys = new int[n+1]; 
		int[] jerseys = new int[n+1]; 
		for(int i = 1; i < n+1; i++) {
			int num = Integer.parseInt(in.readLine()); 
			if(num == 1) {
				holsteins[i] = 1 + holsteins[i-1]; 
				guernseys[i] = guernseys[i-1];
				jerseys[i] = jerseys[i-1];
			}else if(num == 2) {
				guernseys[i] = 1 + guernseys[i-1]; 
				holsteins[i] = holsteins[i-1];
				jerseys[i] = jerseys[i-1];
			}else if(num == 3){
				jerseys[i] = 1 + jerseys[i-1]; 
				holsteins[i] = holsteins[i-1];
				guernseys[i] = guernseys[i-1];
			}
		}
		
		for(int i = 0; i < q; i++) {
			String lin2 = in.readLine();
			StringTokenizer z = new StringTokenizer(lin2); 
			int a = Integer.parseInt(z.nextToken());
			int b = Integer.parseInt(z.nextToken());
            //pw.println(holsteins[b]);
            //pw.println(holsteins[a-1]);
			int ones = holsteins[b] - holsteins[a-1]; 
			int twos = guernseys[b] - guernseys[a-1]; 
			int threes = jerseys[b] - jerseys[a-1]; 
			pw.println(ones + " " + twos + " " + threes);
		}
		pw.close();
	}
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BracketsMatching {

	public static void main(String[] args) throws IOException{
		BufferedReader in  = new BufferedReader(new InputStreamReader(System.in)); 
		String[] brackets = in.readLine().split(" "); 
		Stack bop = new Stack(); 
		for(int i = 0; i < brackets.length; i++) {
			String bracket = brackets[i]; 
			if(bracket.equals("(")) {
				bop.push(i); 
			}else {
				int openingIndex = (int) bop.pop();
				int closingIndex = i; 
				System.out.println("Opening Index: " + openingIndex +  " Closing Index: " + closingIndex);
			}
		}
	}

}

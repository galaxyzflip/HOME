import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class sdf {

	public static void main(String[] args) {
		/*StringTokenizer st = new StringTokenizer("1:10 2:9 3:8 4:7 5:6 1:9 10:8 2:7 3:6 4:5 1:8 9:7 10:6"
				+ " 2:5 3:4 1:7 8:6 9:5 10:4 2:3 1:6 7:5 8:4 9:3 10:2"
				+ " 1:5 6:4 7:3 8:2 9:10 1:4 5:3 6:2 7:10 8:9 1:3 4:2 5:10"
				+ " 6:9 7:8 1:2 3:10 4:9 5:8 6:7");*/
		
		
		StringTokenizer st = new StringTokenizer("2:9   3:8   4:7   5:6   1:9   2:7   3:6   4:5   1:8   9:7   2:5   3:4"
				+ " 1:7   8:6   9:5   2:3   1:6   7:5   8:4   9:3   1:5   6:4   7:3   8:2"
				+ " 1:4   5:3   6:2   8:9   1:3   4:2   6:9   7:8   1:2   4:9   5:8   6:7");
		
		Set set = new HashSet();
		
		int count=0;
		
		while(st.hasMoreTokens()) {
			String tok = st.nextToken();
			System.out.println(tok);
			count++;
			set.add(tok);
			
		}
		System.out.println();System.out.println();System.out.println();
		System.out.println(count);
		System.out.println(set.size());
	}
	
	

}


//




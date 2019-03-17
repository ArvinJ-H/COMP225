package lectures;

public class BinarySearch {
	static int binarySearch(char A[], char key) {
	      int f= 0;
	      int l= A.length;//int variable name is not clear
	while(f < l) {
	  int mid= (l-f)/2;
	  int x= A[mid];
	        if (x == key) return mid;
	        if (x < key) f= mid;
	        else l= mid;
	}
	
	return f; 
	}
}
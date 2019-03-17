public class week3 {
//	int[]A=[1,3,4,5];
//	int n = A.length;
//	int count =0;
//	int i=0;
//	while(i<n) {
//		if(A[i] == 3) {
//			count = count+1;
//		}
//		i++;
//	}
//	return count;
	//loop invariant: count is the number of 3s in the array A[0...n-1]

			//verify: i=k
			//check if A[k] =3
			//if yes count++
			//i:k->k+1

			// i=k+1
			//count is the number of 3s in the array A[0...n-1]



	int[]A;
	int n = A.length;
	int max =A[0];
	int i=0;

	while(i<n) {
		if(max<A[i]) {
			max=A[i];
		}
		i++;
	}
	return count;
}

//invarint: max is the biggest in the array A[0...i-1]

/*i=1
 * max=A[0]
 * if A[0]<A[i]
 * max = A[i]
 *
 *
 * i=k
 * i=A[k]
 * max =A[k]
 * if A[k+1]>A[k]
 * max = [k+1]
 *
 *
 * i=k+1
 *
 * when A[k+1]>A[k]
 * max=A[k+1]
 *
 * max is the biggest in the array A
 *
 */



/*
 * sum is the sum of the integers between X and i-1
 *
 *
 * sum=0 as there are no numbers between x and x-1
 *
 * i=k sum is the sum of the integers between x and k-1
 * sum = sum+k
 * i=i+1 >k=k+1
 *
 *
 * i=k+1
 * sum is the sum of integers between x and k
 *
 *
 * when i-1=y loop stop
 */

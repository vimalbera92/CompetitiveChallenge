import java.util.Scanner;

/*The Maximum Subarray
Given an array A of N elements, find the maximum possible sum of a

 1. Contiguous subarray
 2. Non-contiguous (not necessarily contiguous) subarray.
Empty subarrays/subsequences should not be considered.

Input Format

First line of the input has an integer T. T cases follow. Each test case begins with an integer N. In the next line, N integers follow representing the elements of array A.

Constraints:

    1 <= T <= 10
    1 <= N <= 10^5
    -10^4 <= a_i <= 10^4
The subarray and subsequences you consider should have at least one element.
Output Format

Two, space separated, integers denoting the maximum contiguous and non-contiguous subarray. At least one integer should be selected and put into the subarrays (this may be required in cases where all elements are negative).

Sample Input

2 
4 
1 2 3 4
6
2 -1 2 3 4 -5
Sample Output

10 10
10 11
Explanation

In the first case: The max sum for both contiguous and non-contiguous elements is the sum of ALL the elements (as they are all positive). In the second case: [2 -1 2 3 4] --> This forms the contiguous sub-array with the maximum sum. For the max sum of a not-necessarily-contiguous group of elements, simply add all the positive elements.

*/
public class ContiguousSubArray {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int testCase = sc.nextInt();
		while (testCase > 0) {
			int n = sc.nextInt();
			int contiguousSum = Integer.MIN_VALUE, nonContiguousSum = 0;
			int prevSum = Integer.MIN_VALUE;
			while (n > 0) {
				int temp = sc.nextInt();
				if (temp > 0) {
					nonContiguousSum += temp;
				}
				if (prevSum < 0) {
					prevSum = (prevSum < temp) ? temp : prevSum;
				} else {
					prevSum += temp;
				}
				if (prevSum > contiguousSum) {
					contiguousSum = prevSum;
				}
				n--;
			}
			if (nonContiguousSum == 0) {
				nonContiguousSum = contiguousSum;
			}
			System.out.println(contiguousSum + " " + nonContiguousSum);
			testCase--;
		}
		sc.close();

	}
}

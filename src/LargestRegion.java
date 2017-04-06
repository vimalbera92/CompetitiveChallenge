
/**
 * You are given a matrix with m rows and n columns of cells, each of which
 * contains either 1 or 0. Two cells are said to be connected if they are
 * adjacent to each other horizontally, vertically, or diagonally. The connected
 * and filled (i.e. cells that contain a 1) cells form a region. There may be
 * several regions in the matrix. Find the number of cells in the largest region
 * in the matrix.
 * 
 * Input Format There will be three parts of the input: The first line will
 * contain m, the number of rows in the matrix. The second line will contain n,
 * the number of columns in the matrix. This will be followed by the matrix
 * grid: the list of numbers that make up the matrix.
 * 
 * Output Format Print the length of the largest region in the given matrix.
 * 
 * Constraints
 * 
 * 0 < m < 10
 * 0 < n < 10
 * Sample Input:
 * 
 * 4 
 * 4 
 * 1 1 0 0 
 * 0 1 1 0 
 * 0 0 1 0 
 * 1 0 0 0 
 * 
 * Sample Output:
 * 5
 *  
 * Task: Write the complete program to find the number of cells in the largest
 * region.
 * 
 * Explanation
 * 
 * X X 0 0 
 * 0 X X 0 
 * 0 0 X 0 
 * 1 0 0 0 
 * 
 * The X characters indicate the largest
 * connected component, as per the given definition. There are five cells in
 * this component.
 * 
 * 
 * @author vbera
 *
 */

import java.awt.Point;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class LargestRegion {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int row = sc.nextInt();
		int col = sc.nextInt();
		int[][] input = new int[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				input[i][j] = sc.nextInt();
			}
		}

		int ans = 0;
		Set<Point> max = new HashSet<Point>();
		Set<Point> visited = new HashSet<Point>();
		for (int i = 0; i < input.length; i += 1) {
			for (int j = 0; j < input[0].length; j += 1) {
				if (input[i][j] == 1 && !visited.contains(new Point(i, j))) {
					max = findLargestRegion(i, j, input, new HashSet<Point>());
					visited.addAll(max);
					if (max.size() > ans) {
						ans = max.size();
					}
				}
			}
		}
		System.out.println(ans);
		sc.close();
	}

	private static Set<Point> findLargestRegion(int i, int j, int[][] input, Set<Point> processedPoint) {
		Set<Point> toBeProcessed = new HashSet<Point>();
		input[i][j] = -1;
		processedPoint.add(new Point(i, j));
		if (i - 1 >= 0 && i - 1 < input.length) {
			if (j - 1 >= 0 && j - 1 < input[0].length) {
				if (input[i - 1][j - 1] == 1)
					toBeProcessed.add(new Point(i - 1, j - 1));
			}
			if (j >= 0 && j < input[0].length) {
				if (input[i - 1][j] == 1)
					toBeProcessed.add(new Point(i - 1, j));
			}
			if (j + 1 >= 0 && j + 1 < input[0].length) {
				if (input[i - 1][j + 1] == 1)
					toBeProcessed.add(new Point(i - 1, j + 1));
			}
		}
		if (i + 1 >= 0 && i + 1 < input.length) {
			if (j - 1 >= 0 && j - 1 < input[0].length) {
				if (input[i + 1][j - 1] == 1)
					toBeProcessed.add(new Point(i + 1, j - 1));
			}
			if (j >= 0 && j < input[0].length) {
				if (input[i + 1][j] == 1)
					toBeProcessed.add(new Point(i + 1, j));
			}
			if (j + 1 >= 0 && j + 1 < input[0].length) {
				if (input[i + 1][j + 1] == 1)
					toBeProcessed.add(new Point(i + 1, j + 1));
			}
		}
		if (i >= 0 && i < input.length) {
			if (j - 1 >= 0 && j - 1 < input[0].length) {
				if (input[i][j - 1] == 1)
					toBeProcessed.add(new Point(i, j - 1));
			}
			if (j + 1 >= 0 && j + 1 < input[0].length) {
				if (input[i][j + 1] == 1)
					toBeProcessed.add(new Point(i, j + 1));
			}
		}
		for (Point p : toBeProcessed) {
			if (!processedPoint.contains(p)) {
				processedPoint.addAll(findLargestRegion(p.x, p.y, input, processedPoint));
			}
		}
		return processedPoint;
	}
}

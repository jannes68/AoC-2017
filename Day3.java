import java.util.stream.IntStream;

public class Day3 {

	/*

	--- Day 3: Spiral Memory ---
	
	You come across an experimental new kind of memory stored on an infinite two-dimensional grid.
	
	Each square on the grid is allocated in a spiral pattern starting at a location marked 1 and then counting up while spiraling outward. For example, the first few squares are allocated like this:
	
	17  16  15  14  13
	18   5   4   3  12
	19   6   1   2  11
	20   7   8   9  10
	21  22  23---> ...
	While this is very space-efficient (no squares are skipped), requested data must be carried back to square 1 (the location of the only access port for this memory system) by programs that can only move up, down, left, or right. They always take the shortest path: the Manhattan Distance between the location of the data and square 1.
	
	For example:
	
	Data from square 1 is carried 0 steps, since it's at the access port.
	Data from square 12 is carried 3 steps, such as: down, left, left.
	Data from square 23 is carried only 2 steps: up twice.
	Data from square 1024 must be carried 31 steps.
	How many steps are required to carry the data from the square identified in your puzzle input all the way to the access port?
	
	Your puzzle input is 368078.
	
	
	*/
	
	
	enum Dir {
		R, L, U, D
	}

	static Dir dir = Dir.R;
	static int xPos = 0;
	static int yPos = 0;
	static int xMin = 0;
	static int xMax = 0;
	static int yMin = 0;
	static int yMax = 0;
	static Map<Integer,Map<Integer,Integer>> = new HashMa
	public static void main(String[] args) {

		IntStream.rangeClosed(2, 368078).forEach(v -> {
			switch (dir) {
			case R:
				xPos++;
				if (xPos > xMax) {
					dir = Dir.U;
					xMax = xPos;
				}
				break;
			case L:
				xPos--;
				if (xPos < xMin) {
					dir = Dir.D;
					xMin = xPos;
				}
				break;
			case U:
				yPos++;
				if (yPos > yMax) {
					dir = Dir.L;
					yMax = yPos;
				}
				break;
			case D:
				yPos--;
				if (yPos < yMin) {
					dir = Dir.R;
					yMin = yPos;
				}
				break;

			}

		});
		System.out.println(Math.abs(xPos) + Math.abs(yPos));
	}

}

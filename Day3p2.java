import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Day3p2 {

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
	
--- Part Two ---

	As a stress test on the system, the programs here clear the grid and then store the value 1 in square 1. Then, in the same allocation order as shown above, they store the sum of the values in all adjacent squares, including diagonals.
	
	So, the first few squares' values are chosen as follows:
	
	Square 1 starts with the value 1.
	Square 2 has only one adjacent filled square (with value 1), so it also stores 1.
	Square 3 has both of the above squares as neighbors and stores the sum of their values, 2.
	Square 4 has all three of the aforementioned squares as neighbors and stores the sum of their values, 4.
	Square 5 only has the first and fourth squares as neighbors, so it gets the value 5.
	Once a square is written, its value does not change. Therefore, the first few squares would receive the following values:
	
	147  142  133  122   59
	304    5    4    2   57
	330   10    1    1   54
	351   11   23   25   26
	362  747  806--->   ...
	What is the first value written that is larger than your puzzle input?	

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
	static Map<Integer,Map<Integer,Integer>> posMap = new HashMap<>();
	
	static Integer getVal(Integer xPos, Integer yPos) {
		Map<Integer,Integer> yPosMap = posMap.get(xPos);
		if(yPosMap!=null && yPosMap.containsKey(yPos)) {
			return yPosMap.get(yPos);
		} else {
			return 0;
		}
	}

	static void setVal(Integer xPos, Integer yPos, Integer val) {
		Map<Integer,Integer> yPosMap = posMap.get(xPos);
		if(yPosMap==null ) {
			yPosMap = new HashMap<>();
			posMap.put(xPos,yPosMap);
		} 
		yPosMap.put(yPos, val);
	}
	
	public static void main(String[] args) {

		setVal(0,0,1);
		
		int val = 0;
		while(val <= 368078) {
			switch (dir) {
			case R:
				xPos++;
				if (xPos > xMax) {
					dir = Dir.U;
					xMax = xPos;
				}
				val = getVal(xPos-1,yPos);
				val = val + getVal(xPos-1,yPos+1);
				val = val + getVal(xPos,yPos+1);
				val = val + getVal(xPos+1,yPos+1);
				
				break;
			case L:
				xPos--;
				if (xPos < xMin) {
					dir = Dir.D;
					xMin = xPos;
				}
				val = getVal(xPos+1,yPos);
				val = val + getVal(xPos-1,yPos-1);
				val = val + getVal(xPos,yPos-1);
				val = val + getVal(xPos+1,yPos-1);
				break;
			case U:
				yPos++;
				if (yPos > yMax) {
					dir = Dir.L;
					yMax = yPos;
				}
				val = getVal(xPos,yPos-1);
				val = val + getVal(xPos-1,yPos+1);
				val = val + getVal(xPos-1,yPos);
				val = val + getVal(xPos-1,yPos-1);
				break;
			case D:
				yPos--;
				if (yPos < yMin) {
					dir = Dir.R;
					yMin = yPos;
				}
				val = getVal(xPos,yPos+1);
				val = val + getVal(xPos+1,yPos+1);
				val = val + getVal(xPos+1,yPos);
				val = val + getVal(xPos+1,yPos-1);
				break;

			}
			setVal(xPos,yPos,val);
//			System.out.println(val + "(" + xPos + "," + yPos + ")");

		};
		System.out.println(val);
	}

}

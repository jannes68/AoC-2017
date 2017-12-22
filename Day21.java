import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Day21 {

	/*
	
	*/

static List<String> ruleData = Arrays.asList(
		"../.. => .../.##/##.", //
		"#./.. => .##/.##/#..", //
		"##/.. => ..#/.../###", //
		".#/#. => #.#/..#/##.", //
		"##/#. => .#./.#./..#", //
		"##/## => #.#/#../###", //
		".../.../... => ..../#.../.##./..#.", //
		"#../.../... => ####/#.##/##.#/..#.", //
		".#./.../... => ..##/..##/..##/..##", //
		"##./.../... => ..../..#./##../##.#", //
		"#.#/.../... => ##.#/..../####/...#", //
		"###/.../... => .#.#/.###/.#../.#.#", //
		".#./#../... => .###/#.#./...#/##..", //
		"##./#../... => #.##/#.../####/###.", //
		"..#/#../... => ####/...#/...#/#.##", //
		"#.#/#../... => .#../##../..##/..#.", //
		".##/#../... => .#../..##/..../.##.", //
		"###/#../... => #.../..#./.#.#/#..#", //
		".../.#./... => #.#./.#.#/.###/...#", //
		"#../.#./... => ###./.#../...#/.#..", //
		".#./.#./... => ##.#/.#../#..#/##..", //
		"##./.#./... => #..#/...#/.#.#/###.", //
		"#.#/.#./... => .##./#.../#..#/.###", //
		"###/.#./... => .#.#/##.#/..../##.#", //
		".#./##./... => ##.#/#.##/.#.#/#.##", //
		"##./##./... => #.##/..#./..#./.##.", //
		"..#/##./... => ..../#.../..#./..##", //
		"#.#/##./... => .##./####/####/####", //
		".##/##./... => #.##/####/#.##/#..#", //
		"###/##./... => .#../.###/##../...#", //
		".../#.#/... => ...#/...#/#.##/####", //
		"#../#.#/... => ..#./..#./###./.##.", //
		".#./#.#/... => .##./##../.###/.#.#", //
		"##./#.#/... => #.#./.#../.##./...#", //
		"#.#/#.#/... => ##.#/..##/#.../##.#", //
		"###/#.#/... => ..##/##../.#.#/..##", //
		".../###/... => .#../#.../.##./....", //
		"#../###/... => ..##/..##/...#/.##.", //
		".#./###/... => #..#/..#./#.#./..##", //
		"##./###/... => #.##/.#../##.#/##.#", //
		"#.#/###/... => ####/###./.##./...#", //
		"###/###/... => #..#/#.##/..../.##.", //
		"..#/.../#.. => #.#./.#../##../..#.", //
		"#.#/.../#.. => ##.#/####/##../.#.#", //
		".##/.../#.. => ####/##../#..#/..#.", //
		"###/.../#.. => ##../..#./####/##.#", //
		".##/#../#.. => ##../#.#./###./..##", //
		"###/#../#.. => ..../.#../#..#/...#", //
		"..#/.#./#.. => ..#./...#/.###/.#.#", //
		"#.#/.#./#.. => ###./..../#.#./###.", //
		".##/.#./#.. => ####/#.##/.#.#/.#..", //
		"###/.#./#.. => ###./#.##/##../####", //
		".##/##./#.. => ##.#/..##/..#./.#..", //
		"###/##./#.. => ##.#/.##./.###/.##.", //
		"#../..#/#.. => #.../###./##.#/#..#", //
		".#./..#/#.. => ..##/.###/...#/..#.", //
		"##./..#/#.. => ##../#.#./...#/.#..", //
		"#.#/..#/#.. => ..#./###./##../.###", //
		".##/..#/#.. => #.../.##./..../#.#.", //
		"###/..#/#.. => .#.#/#.##/#.##/..#.", //
		"#../#.#/#.. => ..##/..##/#.../####", //
		".#./#.#/#.. => #.../...#/..../..##", //
		"##./#.#/#.. => ###./..##/.#../.##.", //
		"..#/#.#/#.. => ...#/..##/..#./.#..", //
		"#.#/#.#/#.. => #.#./.#../..../##..", //
		".##/#.#/#.. => ..#./.###/##.#/....", //
		"###/#.#/#.. => #.##/..##/...#/##..", //
		"#../.##/#.. => #.#./##../###./.#.#", //
		".#./.##/#.. => .###/#..#/.##./....", //
		"##./.##/#.. => .#.#/.#../.###/.##.", //
		"#.#/.##/#.. => .#../..##/###./#.##", //
		".##/.##/#.. => ##../.##./..#./.#..", //
		"###/.##/#.. => .#.#/..#./#..#/.###", //
		"#../###/#.. => #.##/#..#/.#.#/#.#.", //
		".#./###/#.. => #.../#..#/#.../.#.#", //
		"##./###/#.. => ##../####/##../.###", //
		"..#/###/#.. => #.../..../####/##.#", //
		"#.#/###/#.. => ...#/..../...#/..##", //
		".##/###/#.. => .#../####/#.##/.#..", //
		"###/###/#.. => ###./.#.#/#.../##..", //
		".#./#.#/.#. => ...#/##../####/...#", //
		"##./#.#/.#. => ####/#..#/###./#.##", //
		"#.#/#.#/.#. => .###/#..#/..#./...#", //
		"###/#.#/.#. => ###./.###/##.#/###.", //
		".#./###/.#. => #..#/#.../..#./####", //
		"##./###/.#. => #.../..../#..#/..##", //
		"#.#/###/.#. => #..#/.#.#/#.../##..", //
		"###/###/.#. => .#.#/..../.#.#/#.##", //
		"#.#/..#/##. => .#../..##/...#/###.", //
		"###/..#/##. => .###/..#./##.#/##.#", //
		".##/#.#/##. => ####/#.##/.##./##..", //
		"###/#.#/##. => #..#/#..#/####/#.##", //
		"#.#/.##/##. => .###/#.#./#..#/.#.#", //
		"###/.##/##. => #.#./#.#./#.##/..##", //
		".##/###/##. => ####/###./##.#/##.#", //
		"###/###/##. => ##../..##/#.#./#...", //
		"#.#/.../#.# => .#../###./.###/##.#", //
		"###/.../#.# => ..../.#.#/#..#/##..", //
		"###/#../#.# => ..#./#.../.##./...#", //
		"#.#/.#./#.# => ...#/#.../##.#/.##.", //
		"###/.#./#.# => ..../..../#.#./##.#", //
		"###/##./#.# => .#../...#/...#/###.", //
		"#.#/#.#/#.# => ...#/#.../##../.###", //
		"###/#.#/#.# => #.../...#/.#../#.##", //
		"#.#/###/#.# => ..../.##./..../##..", //
		"###/###/#.# => .##./.#.#/#.##/.##.", //
		"###/#.#/### => #.#./####/.##./.##.", //
		"###/###/### => .#.#/..##/#.##/.##."  //
		);	
	
	static class Pattern { 
		Set<Integer> hashes = new HashSet<>();
		String replace;
		int size;
		
		Pattern(String fullRow) {
			String[] parts = fullRow.split("=>");
			createHashes(parts[0].trim());
			replace = parts[1].trim();
		}

		Matrix findMatch(Matrix src) {
			if(src.getSize()==this.size) {
				if(hashes.contains(src.getHash())) {
					return new Matrix(replace);
				}
			}
			return null;
		}
		
		void  createHashes(String rows) {
			int[][] mx;
			String[] row = rows.trim().split("/");
			size = row.length;
			mx = new int[size][size];
			for(int r= 0; r<row.length;r++) {
				for(int c = 0;c <  row[r].length(); c++) {
					mx[r][c] = row[r].charAt(c) == '#' ? 1: 0;
				}
			}
			
			if(mx[0].length==2) {
				// Row vise
				// low at top right
				hashes.add(mx[0][1]*1 + mx [0][0]*2  + mx[1][1]*4 + mx [1][0]*8);
				// low at top left
				hashes.add(mx[0][0]*1 + mx [0][1]*2  + mx[1][0]*4 + mx [1][1]*8);
				// low at low right
				hashes.add(mx[1][1]*1 + mx [1][0]*2  + mx[0][1]*4 + mx [0][0]*8);
				// low at low left
				hashes.add(mx[1][0]*1 + mx [1][1]*2  + mx[0][0]*4 + mx [0][1]*8);
				
				// Col vise
				// low at top right
				hashes.add(mx[0][1]*1 + mx [1][1]*2  + mx[0][0]*4 + mx [1][0]*8);
				// low at top left
				hashes.add(mx[0][0]*1 + mx [1][0]*2  + mx[0][1]*4 + mx [1][1]*8);
				// low at low right
				hashes.add(mx[1][1]*1 + mx [0][1]*2  + mx[1][0]*4 + mx [0][0]*8);
				// low at low left
				hashes.add(mx[1][0]*1 + mx [0][0]*2  + mx[1][1]*4 + mx [0][1]*8);
			} else {
				// Row vise
				// low at top right
				hashes.add(mx[0][2]*1 + mx [0][1]*2 +mx [0][0]*4 +  mx[1][2]*8 + mx [1][1]*16 +mx [1][0]*32 + mx[2][2]*64 + mx [2][1]*128 +mx [2][0]*256);
				// low at top left
				hashes.add(mx[0][0]*1 + mx [0][1]*2 +mx [0][2]*4 +  mx[1][0]*8 + mx [1][1]*16 +mx [1][2]*32 + mx[2][0]*64 + mx [2][1]*128 +mx [2][2]*256);
				// low at low right
				hashes.add(mx[2][2]*1 + mx [2][1]*2 +mx [2][0]*4 +  mx[1][2]*8 + mx [1][1]*16 +mx [1][0]*32 + mx[0][2]*64 + mx [0][1]*128 +mx [0][0]*256);
				// low at low left
				hashes.add(mx[2][0]*1 + mx [2][1]*2 +mx [2][2]*4 +  mx[1][0]*8 + mx [1][1]*16 +mx [1][2]*32 + mx[0][0]*64 + mx [0][1]*128 +mx [0][2]*256);
				
				// Col vise
				// low at top right
				hashes.add(mx[0][2]*1 + mx [1][2]*2 +mx [2][2]*4 +  mx[0][1]*8 + mx [1][1]*16 +mx [2][1]*32 + mx[0][0]*64 + mx [1][0]*128 +mx [2][0]*256);
				// low at top left
				hashes.add(mx[0][0]*1 + mx [1][0]*2 +mx [2][0]*4 +  mx[0][1]*8 + mx [1][1]*16 +mx [2][1]*32 + mx[0][2]*64 + mx [1][2]*128 +mx [2][2]*256);
				
				// low at low right
				hashes.add(mx[2][2]*1 + mx [1][2]*2 +mx [0][2]*4 +  mx[2][1]*8 + mx [1][1]*16 +mx [0][1]*32 + mx[2][0]*64 + mx [1][0]*128 +mx [0][0]*256);
				// low at low left
				hashes.add(mx[2][0]*1 + mx [1][0]*2 +mx [0][0]*4 +  mx[2][1]*8 + mx [1][1]*16 +mx [0][1]*32 + mx[2][2]*64 + mx [1][2]*128 +mx [0][2]*256);
			
			}
		}
		
	}
	
	static class Matrix {
		int[][] mx;
		
		Matrix(String rows) {
			String[] row = rows.trim().split("/");
			mx = new int[row.length][];
			for(int r= 0; r<row.length;r++) {
				mx[r] = new int[row.length];
				for(int c = 0;c <  row[r].length(); c++) {
					mx[r][c] = row[r].charAt(c) == '#' ? 1: 0;
				}
			}
		}
		
		Matrix(int size) {
			mx = new int[size][];
			for(int r= 0; r<size;r++) {
				mx[r] = new int[size];
			}
		}

		int getHash() {
			if(mx[0].length==2) {
				// Row vise
				// low at top right
				return mx[0][1]*1 + mx [0][0]*2  + mx[1][1]*4 + mx [1][0]*8;
			} else {
				// Row vise
				// low at top right
				return mx[0][2]*1 + mx [0][1]*2 +mx [0][0]*4 +  mx[1][2]*8 + mx [1][1]*16 +mx [1][0]*32 + mx[2][2]*64 + mx [2][1]*128 +mx [2][0]*256;
			}
		}
		
		int count() {
			int count = 0;
			for(int r = 0; r<getSize();r++) {
				for(int c = 0; c<getSize();c++) {
					 count += mx[r][c];
				}
			}
			return count;
		}
		
		Matrix[][] split() {
			int partSize;
			if(getSize()%2==0) {
				partSize = 2;
			} else if(getSize()%3==0) {
				partSize = 3;
			} else {
				throw new IllegalStateException();
			}
			int numOfParts = getSize()/partSize;
			Matrix[][] res = new Matrix[numOfParts][numOfParts];
			for(int partRow = 0; partRow<numOfParts; partRow++) {
				for(int partCol=0; partCol<numOfParts;partCol++) {
					Matrix m = new Matrix(partSize);
					res[partRow][partCol] = m;
					for(int subRow = 0;subRow<partSize;subRow++) {
						for(int subCol= 0;subCol<partSize;subCol++) {
							m.mx[subRow][subCol] = mx[partRow*partSize+subRow][partCol*partSize+subCol]; 
						}
					}
					
				}
			}
			return res;
		}
		
		static Matrix join(Matrix[][] mxArr) {
			int sourceSize = mxArr[0][0].getSize();
			int dstSize = sourceSize * mxArr.length;
			Matrix m = new Matrix(dstSize);
			for (int dstR = 0; dstR < dstSize; dstR++) {
				for (int dstC = 0; dstC < dstSize; dstC++) {
					m.mx[dstR][dstC] = mxArr[dstR / sourceSize][dstC / sourceSize].mx[dstR % sourceSize][dstC
							% sourceSize];
				}
			}
			return m;
		}
		
		int getSize() {
			return mx.length;
		}
		
		@Override
		public String toString() {
			StringBuilder res = new StringBuilder();
			for(int r= 0; r<mx.length;r++) {
				int[] row = mx[r];
				for(int c = 0;c <  row.length; c++) {
					res.append("" + row[c]);
				}
				res.append("\n");
				
				
			}
			return res.toString();
		}
	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();

		List<Pattern> patterns = ruleData.stream().map(rd -> new Pattern(rd)).collect(Collectors.toList());
		
		Matrix matrix = new Matrix(".#./..#/###");
		
		for(int cnt=0; cnt<18;cnt++) {
			
			Matrix[][] matrixArray = matrix.split();
			
			for(int r = 0; r<matrixArray.length;r++) {
				for(int c = 0; c<matrixArray[0].length;c++) {
					Matrix subMatrix = matrixArray[r][c];
					int hash = subMatrix.getHash();
					matrixArray[r][c] = patterns.stream().map(p -> p.findMatch(subMatrix)).filter(lm -> lm != null).findFirst().get();
				}
			}
			matrix = Matrix.join(matrixArray);
		}
		
		
		System.out.println( "Part2: " + matrix.count() ); //2301762
		
		System.out.println("time: " + (System.currentTimeMillis() - start));
	}

}

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day22 {


	static List<String> data = Arrays.asList(

			"#.##.###.#.#.##.###.##.##", //
			".##.#.#.#..####.###.....#", //
			"...##.....#..###.#..#.##.", //
			"##.###.#...###.#.##..##.#", //
			"###.#.###..#.#.##.#.###.#", //
			".###..#.#.####..##..#..##", //
			"..###.##..###.#..#...###.", //
			"........##..##..###......", //
			"######...###...###...#...", //
			".######.##.###.#.#...###.", //
			"###.##.###..##..#..##.##.", //
			".#.....#.#.#.#.##........", //
			"#..#..#.#...##......#.###", //
			"#######.#...#..###..#..##", //
			"#..#.###...#.#.#.#.#....#", //
			"#.#####...#.##.##..###.##", //
			"..#..#..#.....#...#.#...#", //
			"###.###.#...###.#.##.####", //
			".....###.#..##.##.#.###.#", //
			"#..#...######.....##.##.#", //
			"###.#.#.#.#.###.##..###.#", //
			"..####.###.##.#.###..#.##", //
			"#.#....###....##...#.##.#", //
			"###..##.##.#.#.##..##...#", //
			"#.####.###.#...#.#.....##"  //
			
			);

	static class Field {
		Map<Integer, Map<Integer, Character>> mx = new HashMap<>();
		int size;

		Field(List<String> rows) {
			size = rows.get(0).length();
			for (int y = 0; y < size; y++) {
				String row = rows.get(y);
				for (int x = 0; x < size; x++) {
					setAtPos(x, y, row.charAt(x) == '#' ? 'I' : 'C');
				}
			}
		}

		void setAtPos(int x, int y, char c) {
			if (!mx.containsKey(x)) {
				mx.put(x, new HashMap<>());
			}
			mx.get(x).put(y, c);
		}

		char getAtPos(int x, int y) {
			if (mx.containsKey(x) && mx.get(x).containsKey(y)) {
				return mx.get(x).get(y);
			} else {
				return 'C';
			}
		}

		void setAtPos(Pos pos, char c) {
			setAtPos(pos.x, pos.y, c);
		}

		char getAtPos(Pos pos) {
			return getAtPos(pos.x, pos.y);
		}

		int getSize() {
			return size;
		}

	}

	static class Pos {
		public int x;
		public int y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public Pos move(Dir dir) {
			return new Pos(this.x + dir.dx, this.y + dir.dy);
		}
	}

	enum Dir {
		U(0, -1), D(0, 1), R(1, 0), L(-1, 0);

		int dx;
		int dy;
		Function<Character, Dir> func;

		Dir(int dx, int dy) {
			this.dx = dx;
			this.dy = dy;
		}
		static {
			U.func = c -> { switch(c) { case 'C':  return Dir.L; case 'W': return Dir.U; case 'I': return Dir.R; case 'F': return Dir.D; default: throw new IllegalArgumentException(); } };
			D.func = c -> { switch(c) { case 'C':  return Dir.R; case 'W': return Dir.D; case 'I': return Dir.L; case 'F': return Dir.U; default: throw new IllegalArgumentException(); } };
			R.func = c -> { switch(c) { case 'C':  return Dir.U; case 'W': return Dir.R; case 'I': return Dir.D; case 'F': return Dir.L; default: throw new IllegalArgumentException(); } };
			L.func = c -> { switch(c) { case 'C':  return Dir.D; case 'W': return Dir.L; case 'I': return Dir.U; case 'F': return Dir.R; default: throw new IllegalArgumentException(); } };
		}

		Dir getNewDir(char c) {
			return func.apply(c);
		}

	}
	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		Field field = new Field(data);
		Pos pos = new Pos(field.getSize() / 2, field.getSize() / 2);
		Dir dir = Dir.U;
		int burstCount = 0;
		
		for (int step = 0; step < 10000000; step++) {
			char curStat = field.getAtPos(pos);
			dir = dir.getNewDir(curStat);
			switch(curStat) {
			case 'C':
				field.setAtPos(pos,'W');
				break;
			case 'W':
				field.setAtPos(pos,'I');
				burstCount++;
				break;
			case 'I':
				field.setAtPos(pos,'F');
				break;
			case 'F':
				field.setAtPos(pos,'C');
				break;
			}
			pos = pos.move(dir);
		}

		
		System.out.println("Part2 " + burstCount); // 2511456
		
		System.out.println("time: " + (System.currentTimeMillis() - start));
		
	}

}

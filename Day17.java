import java.util.LinkedList;

public class Day17 {


	static LinkedList<Integer> list = new LinkedList<>();

	public static void main(String[] args) {
		// Part1
		{
			long start = System.currentTimeMillis();

			int step = 354;
			int pos = 0;
			list.add(Integer.valueOf(0));
			for (int i = 1; i <= 2017; i++) {
				pos = (pos + step) % list.size();
				if (i == 1) {
					pos += 1;
				}
				list.add(pos, Integer.valueOf(i));
				pos++;
			}

			Integer res = list.get(list.indexOf(Integer.valueOf(2017)) + 1);

			System.out.println("Part1:" + res); // 2000
			System.out.println("time: " + (System.currentTimeMillis() - start));
		}

		// Part2
		{
			long start = System.currentTimeMillis();
			{
				int step = 354;
				int pos = 0;
				int zeroPos = 1;
				int zeroFollower = 1;
				int listSize = 2;
				for (int i = 2; i <= 50000000; i++) {
					pos = (pos + step) % listSize;
					if (pos == zeroPos) {
						zeroFollower = i;
					} else if (pos < zeroPos) {
						zeroPos++;
					}
					pos++;
					listSize++;
				}

				System.out.println("Part2:" + zeroFollower); // 10242889
				System.out.println("time: " + (System.currentTimeMillis() - start));
			}

		}

	}

}

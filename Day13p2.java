import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day13p2 {

	/*
	--- Day 13: Packet Scanners ---
	
	 */

	static class Layer {
		private int range;
		private int scanPos = 0;
		private int dir = 1;

		private int scanPosCopy;
		private int dirCopy;
		
		Layer(int range) {
			this.range = range-1;
		}
		
		void cycle() {
			scanPos += dir;
			if(scanPos == range || scanPos == 0) {
				dir *= -1;
			}
		}
		
		void storeState() {
			scanPosCopy = scanPos;
			dirCopy = dir;
		}

		void restoreState() {
			 scanPos = scanPosCopy;
			dir = dirCopy;
		}

		boolean getHit() {
			return scanPos==0;
		}
	}
	
	static Map<Integer,Layer> posToLayer= new HashMap<>();
	
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();

		// Init data
		posToLayer = data.stream().map(r -> r.split(":")).collect(Collectors.toMap(a0 -> Integer.valueOf(a0[0]),
				a1 -> new Layer(Integer.valueOf(a1[1].trim()))));
		int maxPos = posToLayer.keySet().stream().mapToInt(i -> i).max().getAsInt();

		boolean failed;
		int delay = 0;
		
		do {
			
			// store state
			posToLayer.values().stream().forEach(l -> l.storeState());
			// check if passage
			failed = false;
			for (int myPos = 0; myPos <= maxPos && !failed; myPos++) {
				if (posToLayer.containsKey(myPos) && posToLayer.get(myPos).getHit()) {
					failed = true;
				} else {
					posToLayer.values().stream().forEach(l -> l.cycle());
				}
			}
			
			// restore state and cycle
			posToLayer.values().stream().forEach(l -> {
				l.restoreState();
				l.cycle();
			});
			delay++;
			
			// indicate progress
			if(delay%500000==0) {
				System.out.println(delay);
			}
			
		} while (failed);
		delay--;
		
		System.out.println("delay: " + delay + " run time: " + (System.currentTimeMillis()-startTime )); 


	}

	static List<String> data = Arrays.asList(
			"0: 3",
			"1: 2",
			"2: 4",
			"4: 4",
			"6: 5",
			"8: 6",
			"10: 6",
			"12: 8",
			"14: 6",
			"16: 6",
			"18: 8",
			"20: 12",
			"22: 8",
			"24: 8",
			"26: 9",
			"28: 8",
			"30: 8",
			"32: 12",
			"34: 20",
			"36: 10",
			"38: 12",
			"40: 12",
			"42: 10",
			"44: 12",
			"46: 12",
			"48: 12",
			"50: 12",
			"52: 12",
			"54: 14",
			"56: 14",
			"58: 12",
			"62: 14",
			"64: 14",
			"66: 14",
			"68: 14",
			"70: 14",
			"72: 14",
			"74: 14",
			"76: 14",
			"78: 14",
			"80: 18",
			"82: 17",
			"84: 14"	
			);
	
	
}

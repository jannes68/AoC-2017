import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day14p2 {

	/*
	
	
	*/
	

	static List<Integer> list = IntStream.rangeClosed(0, 255).mapToObj(s -> s).collect(Collectors.toList());
	
	
	static Integer getVal(int pos) {
		return list.get(pos % list.size());
	}

	static Integer setVal(int pos, Integer val) {
		return list.set(pos % list.size(),val);
	}
	
	static void reverse(int start, int len) {
		int hLen = len / 2;
		for( int i = 0; i<hLen; i++) {
			int sPos = start + i;
			int ePos = start + len - 1 -i;
			Integer v1 = getVal(sPos);
			Integer v2 = getVal(ePos);
			setVal(sPos, v2);
			setVal(ePos, v1);
		}
	}
	
	static Integer densForSlice(List<Integer> slice) {
		int val = slice.get(0);
		for(int i=1; i<slice.size(); i++) {
			val ^= slice.get(i);
		}
		return val;
	}
	
	static List<Integer> dense(List<Integer> sparseHash) {
		List<Integer> res = new ArrayList<>();
		for(int i = 0; i<16; i++) {
			res.add( densForSlice(sparseHash.subList(i*16, i*16+16) ) );
		}
		return res;
	}
	
	
	public static void main(String[] args) {
		
		List<String> hashRows = new ArrayList<>();
		
		for (int row = 0; row < 128; row++) {
			list = IntStream.rangeClosed(0, 255).mapToObj(s -> s).collect(Collectors.toList());
			List<Integer> extraLenghts = Arrays.asList(17, 31, 73, 47, 23);
			//String lengthStr = "wenycdww-" + row;
			String lengthStr = "flqrgnkx-" + row;
			
			List<Integer> lenghts = lengthStr.codePoints().mapToObj(c -> c).collect(Collectors.toList());
			lenghts.addAll(extraLenghts);

			int curPos = 0;
			int skipSize = 0;

			for (int round = 0; round < 64; round++) {
				for (Integer length : lenghts) {
					reverse(curPos, length);
					curPos += length + skipSize;
					skipSize++;
				}
			}
			List<Integer> denseHash = dense(list);
			String result = denseHash.stream().map(v -> String.format("%02X", v)).collect(Collectors.joining());
			hashRows.add(result);
		}		
		
		final int rowSize = 128;
		
		Region[] prevRegionRow = new Region[rowSize];
		for( int rowIdx = 0; rowIdx<hashRows.size();rowIdx++) {
			String hashRow = hashRows.get(rowIdx);
			
			BigInteger curRow = new BigInteger(hashRow,16);
			Region[] curRegionRow = new Region[rowSize];
			
			Region curRegion = null;
			for(int i = 0;i<rowSize;i++) {
				if(curRow.testBit(i)) {
					if(curRegion==null) {
						curRegion = new Region();
					} 
					curRegionRow[i]=curRegion;
					// Check for region above
					if(prevRegionRow[i]!=null && curRegion.id != prevRegionRow[i].id) {
						// use same id for this region
						curRegion.id = prevRegionRow[i].id; 
						Region.idCnt--;
					}
				} else {
					curRegion = null;
				}
			}
			prevRegionRow = curRegionRow;
	}	
		
		System.out.println(Region.idCnt); //1242
		
		
	}
	
	static class Region {
		static int idCnt = 0;
		public Integer id;
		Region() {
			this.id = idCnt++;
		}
		
	}
	
	static boolean isBitSet(String hex, int bitNum) {
		return new BigInteger(hex, 16).testBit(bitNum);
	}
	
}

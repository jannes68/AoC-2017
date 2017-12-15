import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;import javax.swing.RowFilter;

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
		
		
		for (int row = 0; row < 128; row++) {
			list = IntStream.rangeClosed(0, 255).mapToObj(s -> s).collect(Collectors.toList());
			List<Integer> extraLenghts = Arrays.asList(17, 31, 73, 47, 23);
			String lengthStr = "wenycdww-" + row;
			
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
		
		for(int cr = 0; cr<hashRows.size(); cr++) {
			Integer[] rRow = new Integer[rowColSize];
			regionArray[cr] = rRow;
		}
		
		for(int x = 0; x<rowColSize; x++) {
			for(int y = 0; y<hashRows.size();y++) {
				if(checkPos(x,y)) {
					regionCnt++;
				}
			}
		}
		
		System.out.println(regionCnt); //1128
		
		
	}	
	static final int rowColSize = 128;
	static int regionCnt = 0;
	static List<String> hashRows = new ArrayList<>();
	static Integer[][] regionArray = new Integer[rowColSize][];

	static boolean isBitSet(int x, int y) {
		if(x>=0&&x<rowColSize && y>=0 && y<hashRows.size()) {
			return isBitSet(hashRows.get(y),x);
		} else {
			return false;
		}
	}
	
	static boolean checkPos(int x, int y) {
		if(x>=0&&x<rowColSize && y>=0 && y<hashRows.size()) {
			if(regionArray[x][y] == null && isBitSet(x,y)) {
				regionArray[x][y] = regionCnt;
				checkPos(x+1,y);
				checkPos(x-1,y);
				checkPos(x,y+1);
				checkPos(x,y-1);
				return true;
			}
		}
		return false;
	}
	
	static boolean isBitSet(String hex, int bitNum) {
		return new BigInteger(hex, 16).testBit(bitNum);
	}
	
}

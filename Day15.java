import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Day15 {

	/*
--- Day 15: Dueling Generators ---

Here, you encounter a pair of dueling generators. The generators, called generator A and generator B, 
are trying to agree on a sequence of numbers. However, one of them is malfunctioning, and so the sequences don't always match.

As they do this, a judge waits for each of them to generate its next value, compares the lowest 16 bits of both values, 
and keeps track of the number of times those parts of the values match.

The generators both work on the same principle. To create its next value, a generator will take the previous value it produced, 
multiply it by a factor (generator A uses 16807; generator B uses 48271), 
and then keep the remainder of dividing that resulting product by 2147483647. 
That final remainder is the value it produces next.

To calculate each generator's first value, it instead uses a specific starting value as its "previous value" 
(as listed in your puzzle input).

For example, suppose that for starting values, generator A uses 65, while generator B uses 8921. 
Then, the first five pairs of generated values are:

--Gen. A--  --Gen. B--
   1092455   430625591
1181022009  1233683848
 245556042  1431495498
1744312007   137874439
1352636452   285222916

To get a significant sample, the judge would like to consider 40 million pairs. 
(In the example above, the judge would eventually find a total of 588 pairs that match in their lowest 16 bits.)
	*/

	static class Generator {
		static final BigInteger divisor = BigInteger.valueOf(2147483647L);
		BigInteger prev;
		BigInteger factor;

		Generator(long seed, long factor) {
			this.prev = BigInteger.valueOf(seed);
			this.factor = BigInteger.valueOf(factor);
		}

		BigInteger generate() {
			prev = prev.multiply(factor);
			return prev.mod(divisor);
		}
	}
	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		BigInteger filter = BigInteger.valueOf(0xffff);
		Generator a = new Generator(65,16807);
		Generator b = new Generator(8921,48271);
		long counter = 0;
		for(int i= 0; i< 40000000; i++) {
//	for(int i= 0; i< 10000; i++) {
			if(a.generate().and(filter).equals(b.generate().and(filter))) {
				counter++;
			}
		}
	System.out.println(counter);
	System.out.println("time: " + (System.currentTimeMillis()-start));
		
		
	}	

	
	
}

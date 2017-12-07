import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day1p2 {

	/*
	--- Day 1: Inverse Captcha ---
	
	The night before Christmas, one of Santa's Elves calls you in a panic. "The printer's broken! We can't print the Naughty or Nice List!" By the time you make it to sub-basement 17, there are only a few minutes until midnight. "We have a big problem," she says; "there must be almost fifty bugs in this system, but nothing else can print The List. Stand in this square, quick! There's no time to explain; if you can convince them to pay you in stars, you'll be able to--" She pulls a lever and the world goes blurry.
	
	When your eyes can focus again, everything seems a lot more pixelated than before. She must have sent you inside the computer! You check the system clock: 25 milliseconds until midnight. With that much time, you should be able to collect all fifty stars by December 25th.
	
	Collect stars by solving puzzles. Two puzzles will be made available on each day millisecond in the advent calendar; the second puzzle is unlocked when you complete the first. Each puzzle grants one star. Good luck!
	
	You're standing in a room with "digitization quarantine" written in LEDs along one wall. The only door is locked, but it includes a small interface. "Restricted Area - Strictly No Digitized Users Allowed."
	
	It goes on to explain that you may only leave by solving a captcha to prove you're not a human. Apparently, you only get one millisecond to solve the captcha: too fast for a normal human, but it feels like hours to you.
	
	The captcha requires you to review a sequence of digits (your puzzle input) and find the sum of all digits that match the next digit in the list. The list is circular, so the digit after the last digit is the first digit in the list.
	
	For example:
	
	1122 produces a sum of 3 (1 + 2) because the first digit (1) matches the second digit and the third digit (2) matches the fourth digit.
	1111 produces 4 because each digit (all 1) matches the next.
	1234 produces 0 because no digit matches the next.
	91212129 produces 9 because the only digit that matches the next one is the last digit, 9.
	What is the solution to your captcha?
	
	Your puzzle answer was 1203.
	
	The first half of this puzzle is complete! It provides one gold star: *
	
	--- Part Two ---
	
	You notice a progress bar that jumps to 50% completion. Apparently, the door isn't yet satisfied, but it did emit a star as encouragement. The instructions change:
	
	Now, instead of considering the next digit, it wants you to consider the digit halfway around the circular list. That is, if your list contains 10 items, only include a digit in your sum if the digit 10/2 = 5 steps forward matches it. Fortunately, your list has an even number of elements.
	
	For example:
	
	1212 produces 6: the list contains 4 items, and all four digits match the digit 2 items ahead.
	1221 produces 0, because every comparison is between a 1 and a 2.
	123425 produces 4, because both 2s match each other, but no other digit has a match.
	123123 produces 12.
	12131415 produces 4.
	What is the solution to your new captcha?	
	*/
	public static void main(String[] args) {
		String data = "31813174349235972159811869755166343882958376474278437681632495222499211488649543755655138842553867246131245462881756862736922925752647341673342756514856663979496747158241792857625471323535183222497949751644488277317173496124473893452425118133645984488759128897146498831373795721661696492622276282881218371273973538163779782435211491196616375135472517935481964439956844536136823757764494967297251545389464472794474447941564778733926532741752757865243946976266426548341889873514383464142659425122786667399143335772174973128383869893325977319651839516694295534146668728822393452626321892357192574444856264721585365164945647254645264693957898373214897848424966266582991272496771159583715456714645585576641458358326521858518319315233857473695712238323787254556597566461188452279853766184333696344395818615215846348586541164194624371353556812548945447432787795489443312941687221314432694115847863129826532628228386894683392352799514942665396273726821936346663485499159141368443782475714679953213388375939519711591262489869326145476958378464652451441434846382474578535468433514121336844727988128998543975147649823215332929623574231738442281161294838499441799996857746549441142859199799125595761724782225452394593514388571187279266291364278184761833324476838939898258225748562345853633364314923186685534864178665214135631494876474186833392929124337161222959459117554238429216916532175247326391321525832362274683763488347654497889261543959591212539851835354335598844669618391876623638137926893582131945361264841733341247646125278489995838369127582438419889922365596554237153412394494932582424222479798382932335239274297663365164912953364777876187522324991837775492621675953397843833247525599771974555545348388871578347332456586949283657613841414576976542343934911424716613479249893113961925713317644349946444271959375981158445151659431844142242547191181944395897963146947935463718145169266129118413523541222444997678726644615185324461293228124456118853885552279849917342474792984425629248492847827653133583215539325866881662159421987315186914769478947389188382383546881622246793781846254253759714573354544997853153798862436887889318646643359555663135476261863";

		final int step = data.length() / 2;
		long sum = IntStream.range(0, data.length())
				.mapToObj((i) -> new Integer[] { Integer.parseInt(Character.toString(data.charAt(i))),
						Integer.parseInt(Character.toString(i + step < data.length() ? data.charAt(i + step)
								: data.charAt(i + step - data.length()))) })
				.filter(a -> a[0] == a[1]).collect(Collectors.summingLong(a -> a[0]));

		System.out.println(sum); // 1203
	}

}

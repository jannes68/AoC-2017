import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class Day18 {

	public static void main(String[] args) {

		data.stream().forEach(r -> {
			String inStr = r.substring(0, 3);
			String arg = r.substring(4);
			Instr instr = null;
			if ("set".equalsIgnoreCase(inStr)) {
				instr = new ISet(arg,r);
			} else if ("add".equalsIgnoreCase(inStr)) {
				instr = new IAdd(arg,r);
			} else if ("mul".equalsIgnoreCase(inStr)) {
				instr = new IMul(arg,r);
			} else if ("mod".equalsIgnoreCase(inStr)) {
				instr = new IMod(arg,r);
			} else if ("snd".equalsIgnoreCase(inStr)) {
				instr = new ISnd(arg,r);
			} else if ("rcv".equalsIgnoreCase(inStr)) {
				instr = new IRcv(arg,r);
			} else if ("jgz".equalsIgnoreCase(inStr)) {
				instr = new IJgz(arg,r);
			} else {
				System.out.println(inStr);
				throw new IllegalArgumentException();
			}
			program.add(instr);
		});

		while (pc >= 0 && pc < program.size()) {
			long prevPc = pc;
			Instr instr = program.get((int)pc);
			instr.exec();
			String regs = regMap.keySet().stream().map(k-> "" + k + "->" + regMap.get(k)).collect(Collectors.joining(","));
			System.out.println("PC:" + prevPc + ": " + instr.getOrgCmdString() + "  -> PC:" + pc);
			System.out.println(regs + " =>" + sound);
			System.out.println("");
		}

		System.out.println("Part 1: " + foundSound); // not 191

	}

	static Map<String, Long> regMap = new HashMap<>();
	static Long sound = null;
	static Long foundSound = null;
	static long pc = 0;

	static long getReg(String regName) {
		if (!regMap.containsKey(regName)) {
			regMap.put(regName, Long.valueOf(0));
		}
		return regMap.get(regName);
	}

	static void setReg(String regName, long val) {
		regMap.put(regName, val);
	}

	static void incPC(long cnt) {
		pc += cnt;
	}

	static abstract class Instr {
		String orgCmdStrg;
		String registerA;
		String registerB;
		Long valueB;

		abstract void exec();

		String getOrgCmdString() {
			return this.orgCmdStrg;
		}
		
		Instr(String row, String orgCmdString) {
			this.orgCmdStrg = orgCmdString;
			String[] parts = row.split(" ");
			registerA = parts[0];
			if (parts.length > 1) {
				if (parts[1].matches("^-?[0-9]+")) {
					valueB = Long.valueOf(parts[1]);
				} else {
					registerB = parts[1];
				}
			}
		}

	}

	static List<Instr> program = new ArrayList<>();

	static class ISet extends Instr {

		ISet(String arg, String orgCmdString) {
			super(arg,orgCmdString);
		}

		public void exec() {
			if (registerB != null) {
				setReg(registerA, getReg(registerB));
			} else {
				setReg(registerA, valueB);
			}
			incPC(1);
		}

	}

	static class IAdd extends Instr {

		IAdd(String arg, String orgCmdString) {
			super(arg, orgCmdString);
		}

		public void exec() {
			if (registerB != null) {
				setReg(registerA, getReg(registerA) + getReg(registerB));
			} else {
				setReg(registerA, getReg(registerA) + valueB);
			}
			incPC(1);
		}

	}

	static class IMul extends Instr {
		IMul(String arg, String orgCmdString) {
			super(arg,orgCmdString);
		}

		public void exec() {
			if (registerB != null) {
				setReg(registerA, getReg(registerA) * getReg(registerB));
			} else {
				setReg(registerA, getReg(registerA) * valueB);
			}
			incPC(1);
		}

	}

	static class IMod extends Instr {
		IMod(String arg, String orgCmdString) {
			super(arg,orgCmdString);
		}

		public void exec() {
			if (registerB != null) {
				setReg(registerA, getReg(registerA) % getReg(registerB));
			} else {
				setReg(registerA, getReg(registerA) % valueB);
			}
			incPC(1);
		}

	}

	static class ISnd extends Instr {
		ISnd(String arg, String orgCmdString) {
			super(arg,orgCmdString);
		}

		public void exec() {
			sound = getReg(registerA);
			incPC(1);
		}

	}

	static class IRcv extends Instr {
		IRcv(String arg, String orgCmdString) {
			super(arg,orgCmdString);
		}

		public void exec() {
			if (getReg(registerA) != 0) {
				foundSound = sound;
				incPC(program.size()+1);
			}
			incPC(1);
		}

	}

	static class IJgz extends Instr {
		IJgz(String arg, String orgCmdString) {
			super(arg, orgCmdString);
		}

		public void exec() {
			if (getReg(registerA) > 0) {
				if (registerB != null) {
					incPC(getReg(registerB));
				} else {
					incPC(valueB);
				}
			} else {
				incPC(1);
			}

		}

	}

	static List<String> data = Arrays.asList(
			"set i 31",
			"set a 1",
			"mul p 17",
			"jgz p p",
			"mul a 2",
			"add i -1",
			"jgz i -2",
			"add a -1",
			"set i 127",
			"set p 316",
			"mul p 8505",
			"mod p a",
			"mul p 129749",
			"add p 12345",
			"mod p a",
			"set b p",
			"mod b 10000",
			"snd b",
			"add i -1",
			"jgz i -9",
			"jgz a 3",
			"rcv b",
			"jgz b -1",
			"set f 0",
			"set i 126",
			"rcv a",
			"rcv b",
			"set p a",
			"mul p -1",
			"add p b",
			"jgz p 4",
			"snd a",
			"set a b",
			"jgz 1 3",
			"snd b",
			"set f 1",
			"add i -1",
			"jgz i -11",
			"snd a",
			"jgz f -16",
			"jgz a -19"

	);

}

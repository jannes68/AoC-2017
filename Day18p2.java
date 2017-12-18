import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Collectors;

public class Day18p2 {

	public static void main(String[] args) {
		Queue<Long> qP0in = new LinkedList<>();
		Queue<Long> qP1in = new LinkedList<>();

		Program p0 = new Program();
		p0.init(data, qP0in, qP1in, 0);

		Program p1 = new Program();
		p1.init(data, qP1in, qP0in, 1);

		int p0wCnt = 0;
		int p1wCnt = 0;
		long cnt = 0;
		while (p0wCnt < 2 || p1wCnt < 2) {
			
			do {
				cnt++;
				
			} while(p0.step());
			System.out.println("----- end 0 step: " + cnt + "-> " + p1.getSendCnt() + " q0:" + qP0in.size() + " q1:" + qP1in.size() );
			do {
				cnt++;
			} while(p1.step());
			System.out.println("----- end 1 step: " + cnt + "-> " + p1.getSendCnt() + " q0:" + qP0in.size() + " q1:" + qP1in.size() );
			p1wCnt = p1.step() ? 0 : p1wCnt + 1;
			p0wCnt = p0.step() ? 0 : p0wCnt + 1;
			cnt++;
		}

		System.out.println("Count:" + p1.getSendCnt());

	}

	static class Program {
		Map<String, Long> regMap = new HashMap<>();
		long id = 0;
		long pc = 0;
		long sendCnt = 0;
		List<Instr> instructions = new ArrayList<>();
		Queue<Long> inQueue;
		Queue<Long> outQueue;

		boolean step() {
			if (pc >= 0 && pc < instructions.size()) {
				long prevPc = pc;
				Instr instr = instructions.get((int) pc);
				boolean ret = instr.exec(this);
//				String regs = regMap.keySet().stream().map(k-> "" + k + "->" + regMap.get(k)).collect(Collectors.joining(","));
//				System.out.println("PC:" + prevPc + ": " + instr.getOrgCmdString() + "  -> PC:" + pc + " => " + regs);
				return ret;
			}
			return false;
		}

		void init(List<String> data, Queue<Long> inQueue, Queue<Long> outQueue, int id) {
			this.id = id;
			setReg("p", id);
			setReg("1",1);
			this.inQueue = inQueue;
			this.outQueue = outQueue;
			data.stream().forEach(r -> {
				String inStr = r.substring(0, 3);
				String arg = r.substring(4);
				Instr instr = null;
				if ("set".equalsIgnoreCase(inStr)) {
					instr = new ISet(arg, r);
				} else if ("add".equalsIgnoreCase(inStr)) {
					instr = new IAdd(arg, r);
				} else if ("mul".equalsIgnoreCase(inStr)) {
					instr = new IMul(arg, r);
				} else if ("mod".equalsIgnoreCase(inStr)) {
					instr = new IMod(arg, r);
				} else if ("snd".equalsIgnoreCase(inStr)) {
					instr = new ISnd(arg, r);
				} else if ("rcv".equalsIgnoreCase(inStr)) {
					instr = new IRcv(arg, r);
				} else if ("jgz".equalsIgnoreCase(inStr)) {
					instr = new IJgz(arg, r);
				} else {
					System.out.println(inStr);
					throw new IllegalArgumentException();
				}
				instructions.add(instr);
			});
		}

		long getSendCnt() {
			return this.sendCnt;
		}

		long getReg(String regName) {
			if (!regMap.containsKey(regName)) {
				regMap.put(regName, Long.valueOf(0));
			}
			return regMap.get(regName);
		}

		void setReg(String regName, long val) {
			regMap.put(regName, val);
		}

		void incPC(long cnt) {
			pc += cnt;
		}
	}

	static abstract class Instr {
		String orgCmdStrg;
		String registerA;
		String registerB;
		Long valueB;

		abstract boolean exec(Program pgm);

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

	static class ISet extends Instr {

		ISet(String arg, String orgCmdString) {
			super(arg, orgCmdString);
		}

		public boolean exec(Program pgm) {
			if (registerB != null) {
				pgm.setReg(registerA, pgm.getReg(registerB));
			} else {
				pgm.setReg(registerA, valueB);
			}
			pgm.incPC(1);
			return true;
		}

	}

	static class IAdd extends Instr {

		IAdd(String arg, String orgCmdString) {
			super(arg, orgCmdString);
		}

		public boolean exec(Program pgm) {
			if (registerB != null) {
				pgm.setReg(registerA, pgm.getReg(registerA) + pgm.getReg(registerB));
			} else {
				pgm.setReg(registerA, pgm.getReg(registerA) + valueB);
			}
			pgm.incPC(1);
			return true;
		}

	}

	static class IMul extends Instr {
		IMul(String arg, String orgCmdString) {
			super(arg, orgCmdString);
		}

		public boolean exec(Program pgm) {
			if (registerB != null) {
				pgm.setReg(registerA, pgm.getReg(registerA) * pgm.getReg(registerB));
			} else {
				pgm.setReg(registerA, pgm.getReg(registerA) * valueB);
			}
			pgm.incPC(1);
			return true;
		}

	}

	static class IMod extends Instr {
		IMod(String arg, String orgCmdString) {
			super(arg, orgCmdString);
		}

		public boolean exec(Program pgm) {
			if (registerB != null) {
				pgm.setReg(registerA, pgm.getReg(registerA) % pgm.getReg(registerB));
			} else {
				pgm.setReg(registerA, pgm.getReg(registerA) % valueB);
			}
			pgm.incPC(1);
			return true;
		}

	}

	static class ISnd extends Instr {
		ISnd(String arg, String orgCmdString) {
			super(arg, orgCmdString);
		}

		public boolean exec(Program pgm) {
			pgm.outQueue.add(pgm.getReg(registerA));
			pgm.sendCnt++;
			pgm.incPC(1);
			return true;
		}

	}

	static class IRcv extends Instr {
		IRcv(String arg, String orgCmdString) {
			super(arg, orgCmdString);
		}

		public boolean exec(Program pgm) {
			if (pgm.inQueue.isEmpty()) {
				return false;
			} else {
				pgm.setReg(registerA, pgm.inQueue.remove());
				pgm.incPC(1);
				return true;
			}
		}

	}

	static class IJgz extends Instr {
		IJgz(String arg, String orgCmdString) {
			super(arg, orgCmdString);
		}

		public boolean exec(Program pgm) {
			if (pgm.getReg(registerA) > 0) {
				if (registerB != null) {
					pgm.incPC(pgm.getReg(registerB));
				} else {
					pgm.incPC(valueB);
				}
			} else {
				pgm.incPC(1);
			}
			return true;
		}

	}

	static List<String> data = Arrays.asList(

			"set i 31", //		1
			"set a 1", //		2
			"mul p 17", //		3
			"jgz p p", //		4 	
			"mul a 2", //		5
			"add i -1", //		6	
			"jgz i -2", //		7
			"add a -1", //		8
			"set i 127", //		9
			"set p 316", //		10
			"mul p 8505", //	11 
			"mod p a", //		12	
			"mul p 129749", //	13			
			"add p 12345", //	14	
			"mod p a", //		15	
			"set b p", //		16	
			"mod b 10000", //	17	
			"snd b", //			18	
			"add i -1", //		19	
			"jgz i -9", //		20 
			"jgz a 3", //		21 
			"rcv b", //			22
			"jgz b -1", //		23
			"set f 0", //		24 
			"set i 126", //		25
			"rcv a", //			26
			"rcv b", //			27
			"set p a", //		28
			"mul p -1", //		29
			"add p b", //		30
			"jgz p 4", //		31
			"snd a", //			32
			"set a b", //		33
			"jgz 1 3", //		34
			"snd b", //			35
			"set f 1", //		36
			"add i -1", //		37
			"jgz i -11", //		38
			"snd a", //			39
			"jgz f -16", //		40
			"jgz a -19" //		41
	);
	
}

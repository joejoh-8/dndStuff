package dndStuffRandom;

import java.util.Random;

public class DnDrand {
	private Random rand;
	public String[] generators;

	public DnDrand() {

		rand = new Random();
		generators = new String[3];
		generators[0] = "Jojo's bizzare generator";
		generators[1] = "Standard";
		generators[2] = "Weakling";
	}

	private int[] sort(int[] temp) {
		int l = temp.length;
		for (int i = 0; i < l - 1; i++) {
			for (int j = 0; j < l - 1 - i; j++) {
				if (temp[j] < temp[j + 1]) {
					int t = temp[j];
					temp[j] = temp[j + 1];
					temp[j + 1] = t;
				}
			}
		}
		return temp;
	}

	private int[] Jojo() {
		int[] temp = new int[6];
		for (int i = 0; i < 6; i++) {
			temp[i] = rand.nextInt(16) + 3;
		}
		return sort(temp);
	}

	private int[] Standard() {
		int[] temp = new int[7];
		for (int i = 0; i < 7; i++) {
			temp[i] = rand.nextInt(6) + rand.nextInt(6) + rand.nextInt(6) + 3;
		}
		return sort(temp);
	}

	private int[] Weak() {
		int[] temp = new int[7];
		for (int i = 0; i < 7; i++) {
			int[] stat = new int[4];
			for (int j = 0; j < 4; j++) {
				stat[j] = rand.nextInt(6) + 1;
			}
			stat = sort(stat);
			temp[i] = stat[0] + stat[1] + stat[2];
		}
		return sort(temp);
	}

	public int[] Generate(int gen, long seed) {
		if (seed != 0) {
			rand.setSeed(seed);
		}
		switch (gen) {
		case 0:
			return Jojo();
		case 1:
			return Standard();
		case 2:
			return Weak();
		}
		return null;
	}
}

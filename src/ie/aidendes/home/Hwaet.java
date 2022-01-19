package ie.aidendes.home;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

public class Hwaet {

	public ArrayList<Character> getKnown() {
		return known;
	}

	public void setKnown(ArrayList<Character> known) {
		this.known = known;
	}

	public ArrayList<Character> getCorrect() {
		return correct;
	}

	public void setCorrect(ArrayList<Character> correct) {
		this.correct = correct;
	}

	public ArrayList<char[]> getBlock() {
		return block;
	}

	public void setBlock(ArrayList<char[]> block) {
		this.block = block;
	}

	public HashSet<Character> getWrong() {
		return wrong;
	}

	public void setWrong(HashSet<Character> wrong) {
		this.wrong = wrong;
	}

	ArrayList<Character> known;
	ArrayList<Character> correct;
	ArrayList<char[]> block;
	HashSet<Character> wrong;

	/**
	 * New Instance clears the settings.
	 */
	public Hwaet() {
		this.setBlock(null);
		this.setCorrect(null);
		this.setKnown(null);
		this.setWrong(null);
	}

	/**
	 * The intermediate function. 
	 * 
	 * Provides a list of strings matching detailed criteria
	 * @throws IOException
	 */
	public void printWords() throws IOException {
		HashSet<String> top = new HashSet<String>();
		String tlist = "./top.txt";
		BufferedReader tops = new BufferedReader(new FileReader(tlist));
		tops.lines().forEach(line -> {
			top.add(line);
		});
		tops.close();

		
		TreeSet<String> suggest = checkWords();
		for (String str : suggest) {
			if (top.contains(str)) {
				str = "\033[1;96m" + str + "\033[0m";
			}
			System.out.println(str);
		}
	}

	/**
	 * @return TreeSet of Strings matching criteria
	 * @throws IOException
	 */
	public TreeSet<String> checkWords() throws IOException {
		ArrayList<char[]> wordList = wordList();

		ArrayList<char[]> corrects = new ArrayList<char[]>();

		// reduces the dataset to match known values.
		// 
		// First step is determining how many known values exist
		String test = new String();
		for (char x : this.getCorrect()) {
			if (!(x == '.'))
				test += x;
		}
		int len = test.length();

		
		for (char[] crc : wordList) {
			int addCorrect = 0;
			for (int i = 0; i <= 4; i++) {
				if (crc[i] == this.getCorrect().get(i) // || this.getCorrect().get(i) == '.'
						) {
					addCorrect++;
				}
			}
			if (addCorrect == len) {
				corrects.add(crc);
			}
		}

		// Reduce the dataset yet further by removing any words with known incorrect letters.
		TreeSet<String> finals = new TreeSet<String>();
		for (char[] word : corrects) {
			boolean lastCheck = true;

			for (int j = 0; j <= 4; j++) {
				char[] blk = this.getBlock().get(j);
				String sweet = new String(blk);
				String q = Character.toString(word[j]);
				if (sweet.contains(q)) {
					lastCheck = false;
				}
			}
			if (lastCheck)
				finals.add(new String(word));
		}
		return finals;
	}

	/**
	 * @return words as ArrayList<char[]> to facilitate checking
	 * @throws IOException
	 */
	private final ArrayList<char[]> wordList() throws IOException {
		ArrayList<char[]> temp = new ArrayList<char[]>();
		String wlist = "./wordlist.txt";
		BufferedReader file = new BufferedReader(new FileReader(wlist));
		file.lines().forEach(line -> {
			char[] chars = line.toCharArray();
			ArrayList<Character> nn = new ArrayList<Character>();
			for (char v : chars) {
				nn.add(v);
			}
			boolean addw = true;
			for (char c : chars) {
				if (this.getWrong().contains(c)) {
					addw = false;
				}
			}
			if (!nn.containsAll(this.getKnown())) {
				addw = false;
			}
			if (addw) {
				temp.add(chars);
			}
		});
		file.close();
		return temp;
	}
}

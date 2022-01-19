package ie.aidendes.home;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Menu {

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

	private Scanner s = new Scanner(System.in);

	public void runMenu() throws IOException {

		boolean correctB = true;
		while (correctB) {
			System.out.println("Hello. Please enter the letters you know the position of, using . for gaps.");
			String cn = s.next();
			ArrayList<Character> cha = new ArrayList<Character>();
			char[] cr = cn.toCharArray();
			for (char c : cr) {
				cha.add(c);
			}
			this.setCorrect(cha);
			correctB = false;
		}

		boolean knownB = true;
		while (knownB) {
			System.out.println("Please enter the letters you know are present");
			String kn = s.next();
			ArrayList<Character> kha = new ArrayList<Character>();
			char[] cr = kn.toCharArray();
			for (char c : cr) {
				kha.add(c);
			}
			this.setKnown(kha);
			knownB = false;
		}
		
		boolean wrongB = true;
		while (wrongB) {
			System.out.println("Please enter the letters you know are not in the solution");
			String wn = s.next();
			HashSet<Character> wha = new HashSet<Character>();
			char[] cr = wn.toCharArray();
			for (char c : cr) {
				wha.add(c);
			}
			this.setWrong(wha);
			wrongB = false;
		}

		boolean blockB = true;
		while (blockB) {
			ArrayList<char[]> tset = new ArrayList<char[]>();
			for (int i = 1; i <= 5; i++) {
				boolean st = true;
				while (st) {
					System.out.println("Enter the letters which should not be in position " + i + ". Enter . if null");
					String bl = s.next();
					char[] blk = bl.toCharArray();
					tset.add(blk);
					st = false;
				}
			}
			this.setBlock(tset);
			blockB = false;
		}

		Hwaet hw = new Hwaet();
		hw.setBlock(block);
		hw.setCorrect(correct);
		hw.setKnown(known);
		hw.setWrong(wrong);
		System.out.println("Starting to process your words...");

		System.out
				.println("\nWords meeting the criteria follow\n"
						+ "(Words in the top 20,000 english words are highlighted)\n");
		hw.printWords();

	}

}

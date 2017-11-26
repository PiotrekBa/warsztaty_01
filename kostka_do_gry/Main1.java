package kostka_do_gry;

import java.util.Random;
import java.util.Scanner;

public class Main1 {

	public static void main(String[] args) {
		System.out.println("Progrma symuluje rzut kośćmi.\n" + "wprowadź odpowiedni kod rzutu według wzoru np.\n"
				+ "2D20+10 - 2 rzuty kością D10 + 10(opcjonalnie)");
		System.out.print("Twój rzut to: ");
		int[] values = new int[3];
		values = null;
		while (values == null) {
			values = getCode();
		}
		int score = draft(values[0], values[1], values[2]);
		System.out.println("Twój wynik to " + score);

	}

	static int[] getCode() {
		int[] values = new int[3];
		Scanner scan = new Scanner(System.in);
		String throwDice;

		try {
			int value1, value2 , value3;
			throwDice = scan.nextLine();
			throwDice = throwDice.trim();
			if (throwDice.contains("D")) {
				int UpD = 0;
				UpD = throwDice.indexOf('D');
				String d = throwDice.substring(0, UpD);

				if (d.equals("")) {
					value1 = 1;
				} else {
					value1 = Integer.parseInt(d);
				}
				values[0] = value1;
				int multiplier = 0;
				// if (throwDice.contains("+") || throwDice.contains("-")) {
				int plusMinus;
				if (throwDice.contains("+")) {
					plusMinus = throwDice.indexOf('+');
					multiplier = 1;
					value2 = Integer.parseInt(throwDice.substring(UpD + 1, plusMinus));
				} else if (throwDice.contains("-")) {
					plusMinus = throwDice.indexOf('-');
					multiplier = -1;
					value2 = Integer.parseInt(throwDice.substring(UpD + 1, plusMinus));
				} else {
					plusMinus = UpD;
					value2 = Integer.parseInt(throwDice.substring(UpD + 1, throwDice.length()));
				}
				if (diceType(value2)) {
					values[1] = value2;
					value3 = Integer.parseInt(throwDice.substring(plusMinus + 1, throwDice.length()));
					values[2] = multiplier * value3;
				} else {
					values = null;
					System.out.println("Niepoprawny format rzutu. Podaj jeszcze raz: ");
				}
			} else {
				values = null;
				System.out.println("Niepoprawny format rzutu. Podaj jeszcze raz: ");
			}

		} catch (NumberFormatException e) {
			System.out.println("Niepoprawny format rzutu. Podaj jeszcze raz: ");
			values = null;
		}
		return values;

	}

	static boolean diceType(int value2) {
		if (value2 == 3 || value2 == 4 || value2 == 6 || value2 == 8 || value2 == 10 || value2 == 12 || value2 == 20
				|| value2 == 100) {
			return true;
		} else {
			return false;
		}
	}

	static int draft(int count, int type, int modifier) {
		// ma byc symulacja kazdego rzutu
		int score = 0;

		for (int i = 0; i < count; i++) {
			Random r = new Random();
			int ran = r.nextInt(type) + 1;
			score += ran;
		}
		score += modifier;
		return score;
	}
}

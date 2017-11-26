package gra_w_zgadywanie_liczb_2;

import java.util.Scanner;

public class Main1 {

	public static void main(String[] args) {
		System.out.println("Pomyśl liczbę od 0 do 1000, " + "a ja ją zgadne w max. 10 próbach");
		int min = 0;
		int max = 1000;
		boolean winner = false;
		int guessCount = 0;
		while (!winner && guessCount < 10) {
			int guess = ((max - min) / 2) + min;
			System.out.println("Zgaduję: " + guess);
			System.out.println("Czy zgadłem: ");
			System.out.println("1. za dużo");
			System.out.println("2. za mało");
			System.out.println("3. zgadłeś");
			int ans = getNumber();
			while (!(ans >= 1 && ans <= 3)) {
				ans = getNumber();
			}
			if (ans == 1) {
				max = guess;
				guessCount ++;
			} else if (ans == 2) {
				min = guess;
				guessCount ++;
			} else {
				winner = true;
			}
		}
		if (winner) {
			System.out.println("Wygrałem");
		} else {
			System.out.println("Nie uszukuj");
		}

	}

	static int getNumber() {
		Scanner scan = new Scanner(System.in);
		while (!scan.hasNextInt()) {
			scan.nextLine();
			System.out.println("To nie jest liczba");
		}
		int get = scan.nextInt();
		return get;
	}

}

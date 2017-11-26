package gra_w_zgadywanie_liczb;

import java.util.Random;
import java.util.Scanner;

public class Main1 {

	public static void main(String[] args) {
		Random r = new Random();
		int ran = r.nextInt(101);
		
		while (true) {
			System.out.println("Zgadnij liczbę");
			int g = getNumber();
			if (g == ran) {
				System.out.println("Zgadłeś");
				break;
			} else if (g < ran) {
				System.out.println("Za mało");
			} else {
				System.out.println("Za dużo");
			}
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
